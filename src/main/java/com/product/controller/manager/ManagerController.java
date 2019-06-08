package com.product.controller.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.product.controller.Base;
import com.product.model.Feedback;
import com.product.model.Manager;
import com.product.model.Notice;
import com.product.model.Orders;
import com.product.model.Product;
import com.product.model.ProductExample;
import com.product.model.User;
import com.product.service.IFeedBackService;
import com.product.service.IManagerService;
import com.product.service.INoticeService;
import com.product.service.IOrderService;
import com.product.service.IProductService;
import com.product.service.IUserService;
import com.product.util.MD5Util;
import com.product.util.StringUtil;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController extends Base {
    @Value("${file.product.img}")
    private String filePath;

    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IFeedBackService feedBackService;
    @Autowired
    private IManagerService managerService;

    /**
     * 登出
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("manager");
        return "manager/login";
    }

    /**
     * 管理员主页
     */
    @RequestMapping("main")
    public String main() {
        return "manager/main";
    }

    /**
     * 去商品添加页面
     */
    @RequestMapping("toAddProduct")
    public String toAddProduct(ModelMap map) {
        return "manager/addProduct";
    }


    /**
     * 商品添加
     */
    @RequestMapping("addProduct")
    public String addProduct(ModelMap map, HttpServletRequest request, MultipartFile productFile) throws IOException {
        String productName = request.getParameter("productName");
        String productPriceStr = request.getParameter("productPrice");
        String productRemark = request.getParameter("productRemark");

        if (StringUtil.isEmpty(productName) || StringUtil.isEmpty(productPriceStr)
                || StringUtil.isEmpty(productRemark)) {
            map.addAttribute("msg", "请完善相关信息");
            return "manager/addProduct";
        }
        if (productFile == null) {
            map.addAttribute("msg", "请选择图片");
            return "manager/addProduct";
        }
        BigDecimal price;
        try {
            //保留2位小数,直接删除多余小数
            price = new BigDecimal(productPriceStr).setScale(2, BigDecimal.ROUND_DOWN);
        } catch (Exception e) {
            map.addAttribute("msg", "参数错误");
            return "manager/addProduct";
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setRemark(productRemark);
        product.setProductStatus(1);
        product.setImgUrl(filePath);
        try {
            productService.add(product, productFile);
        } catch (Exception e) {
            map.addAttribute("msg", "添加出现异常");
            return "manager/addProduct";
        }

        /**可以修改部分*/
        map.addAttribute("msg", "添加成功");
        return "manager/addProduct";
    }


    /**
     * 去商品修改页
     */
    @RequestMapping("toUpdateProduct")
    public String toProductUpd(ModelMap map, HttpServletRequest request) {
        String productId = request.getParameter("productId");
        Product product = productService.getById(Integer.parseInt(productId));
        map.addAttribute("product", product);
        String url = request.getSession().getServletContext().getRealPath("/product");
        System.out.println(url);
        return "manager/productUpdate";
    }

    /**
     * 商品修改
     */
    @RequestMapping("updateProduct")
    public String productUpd(ModelMap map, HttpServletRequest request, MultipartFile productFile) {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productPriceStr = request.getParameter("productPrice");
        String productRemark = request.getParameter("productRemark");
        Product product = productService.getById(Integer.parseInt(productId));
        map.addAttribute("product", product);
        if (StringUtil.isEmpty(productName) || StringUtil.isEmpty(productPriceStr)
                || StringUtil.isEmpty(productRemark)) {
            map.addAttribute("msg", "请完善相关信息");
            return "manager/productUpdate";
        }
        BigDecimal price;
        try {
            price = new BigDecimal(productPriceStr).setScale(2, BigDecimal.ROUND_DOWN);
        } catch (Exception e) {
            map.addAttribute("msg", "参数错误");
            return "manager/productUpdate";
        }

        product.setProductName(productName);
        product.setProductPrice(price);
        product.setRemark(productRemark);
        try {
            productService.updateProduct(product, productFile);
        } catch (Exception e) {
            map.addAttribute("msg", "添加出现异常");
            return "manager/productUpdate";
        }

        return "redirect:/manager/productPage";
    }

    /**
     * 商品上下架
     */
    @RequestMapping("upAndDown")
    @ResponseBody
    public Integer upAndDown(HttpServletRequest request) {
        String productId = request.getParameter("productId");
        String status = request.getParameter("status");
        Product product = new Product();
        product.setProductId(Integer.parseInt(productId));
        product.setProductStatus(Integer.parseInt(status));
        Integer integer = productService.update(product);
        return integer;
    }


    /**
     * 商品遍历
     */
    @RequestMapping("productPage")
    public String getProductAll(ModelMap map, Product product) {
        PageBean<Product> pageBean = productService.getProductPageByManager(product);
        map.addAttribute("pageBean", pageBean);
        return "manager/productList";
    }


    /**
     * 用户信息
     */
    @RequestMapping("userPage")
    public String userAll(ModelMap map, User user) {
        PageBean<User> pageBean = userService.getUserPage(user);
        map.addAttribute("pageBean", pageBean);
        return "/manager/userAll";
    }

    /**
     * 查看订单
     */
    @RequestMapping("orderPage")
    public String orderAll(ModelMap map, Orders orders) {
        PageBean<Orders> pageBean = orderService.selectOrderPage(orders);
        List<User> allUser = userService.getAllUser();
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        for (User user : allUser) {
            hashMap.put(user.getUserId(), user.getUserName().toString());
        }
        map.addAttribute("pageBean", pageBean);
        map.addAttribute("userMap", hashMap);
        return "manager/orderAll";
    }

    //发货
    @RequestMapping("sendGood")
    @ResponseBody
    public Integer sendGood(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        int i = orderService.update(Integer.parseInt(orderId), 3);
        return i;
    }

    /**
     * 查看公告
     */
    @RequestMapping("noticePage")
    public String noticeAll(ModelMap modelMap, Notice notice) {
        PageBean<Notice> pageBean = noticeService.selectNoticePage(notice);
        modelMap.addAttribute("pageBean", pageBean);
        return "manager/noticeList";
    }

    /**
     * 去添加公告
     */
    @RequestMapping("toAddNotice")
    public String toAddNotice() {
        return "manager/noticeAdd";
    }

    /**
     * 添加公告
     */
    @RequestMapping("addNotice")
    public String addNotice(ModelMap modelMap, Notice notice, HttpServletRequest request) {
        if (StringUtil.isEmpty(notice.getNoticeTitle()) || StringUtil.isEmpty(notice.getNoticeContext())) {
            modelMap.addAttribute("msg", "内容不可为空");
            return "manager/noticeAdd";
        }
        notice.setCreateBy(getManager(request).getManagerName());
        notice.setCreateTime(new Date());
        int i = noticeService.addNotice(notice);
        if (i > 0) {
            modelMap.addAttribute("msg", "添加成功");
        } else {
            modelMap.addAttribute("msg", "添加失败");
        }
        return "manager/noticeAdd";
    }

    /**
     * 去修改公告
     */
    @RequestMapping("toEditNotice")
    public String toEditNotice(ModelMap modelMap, Integer noticeId) {
        Notice notice = noticeService.selectNoticeById(noticeId);
        modelMap.addAttribute("notice", notice);
        return "manager/noticeUpdate";
    }

    /**
     * 修改公告
     */
    @RequestMapping("editNotice")
    public String editNotice(ModelMap modelMap, Notice notice) {
        int i = noticeService.updateNotice(notice);
        if (i > 0) {
            modelMap.addAttribute("msg", "修改成功");
        } else {
            modelMap.addAttribute("msg", "修改失败");
        }
        return "manager/noticeUpdate";
    }


    /**
     * 查看反馈
     */
    @RequestMapping("feedBackPage")
    public String feedBackAll(ModelMap modelMap, Feedback feedback) {
        PageBean<Feedback> pageBean = feedBackService.selectFeedBackPage(feedback);
        modelMap.addAttribute("pageBean", pageBean);
        return "manager/feedBackList";
    }

    /**
     * 去添加管理员
     */

    @RequestMapping("toAddManager")
    public String toAddManager() {
        return "/manager/managerAdd";
    }

    /**
     * 添加管理员
     */
    @RequestMapping("addManager")
    public String addManager(ModelMap modelMap, Manager manager, HttpServletRequest request) {
        if (StringUtil.isEmpty(manager.getManagerName()) || StringUtil.isEmpty(manager.getPassword())) {
            modelMap.addAttribute("msg", "参数异常");
        } else {
            List<Manager> list = managerService.getManagerByName(manager.getManagerName());
            if (list != null && list.size() > 0) {
                modelMap.addAttribute("msg", "添加失败！管理员用户名已存在");
            } else {
                manager.setCreateBy(getManager(request).getManagerName());
                manager.setCreateTime(new Date());
                manager.setPassword(MD5Util.getMD5(manager.getPassword()));
                int i = managerService.insertManager(manager);
                if (i > 0) {
                    modelMap.addAttribute("msg", "添加成功");
                } else {
                    modelMap.addAttribute("msg", "添加失败");
                }
            }
        }
        return "/manager/managerAdd";
    }

    /**
     * 去修改管理员
     */
    @RequestMapping("toEditManager")
    public String toEditManager(ModelMap modelMap, Integer managerId) {
        Manager manager = managerService.selectManagerById(managerId);
        modelMap.addAttribute("manager", manager);
        return "manager/managerUpdate";
    }

    /**
     * 修改管理员
     */
    @RequestMapping("editManager")
    public String editManager(ModelMap modelMap, Manager manager) {
        if (StringUtil.isEmpty(manager.getPassword())) {
            manager.setPassword(null);
        }
        manager.setPassword(MD5Util.getMD5(manager.getPassword()));
        int i = managerService.updateManager(manager);
        if (i > 0) {
            modelMap.addAttribute("msg", "修改成功");
        } else {
            modelMap.addAttribute("msg", "修改失败");
        }
        return "manager/managerUpdate";
    }

    /**
     * 查看管理员
     */
    @RequestMapping("managerPage")
    public String managerPage(ModelMap modelMap, Manager manager) {
        PageBean<Manager> pageBean = managerService.selectManagerPage(manager);
        modelMap.addAttribute("pageBean", pageBean);
        return "manager/managerList";
    }

    @RequestMapping("deleteManager")
    @ResponseBody
    public int deleteManager(Integer managerId) {
        int i = managerService.deleteById(managerId);
        return i;
    }
}
