package com.product.controller;

import com.product.model.Car;
import com.product.model.Manager;
import com.product.model.Notice;
import com.product.model.Product;
import com.product.model.ProductExample;
import com.product.model.User;
import com.product.service.ICarService;
import com.product.service.IManagerService;
import com.product.service.INoticeService;
import com.product.service.IOrderService;
import com.product.service.IProductService;
import com.product.service.IUserService;
import com.product.util.MD5Util;
import com.product.util.StringUtil;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BaseController extends Base {
    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IManagerService managerService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ICarService carService;

    /**后台首页*/
    @RequestMapping("/manager")
    public String manager() {
        return "manager/login";
    }

    /**管理员登录*/
    @RequestMapping("/managerLogin")
    public String login(ModelMap map, HttpServletRequest request) {
        String managerName = request.getParameter("managerName");
        String password = request.getParameter("password");
        if (StringUtil.isEmpty(managerName) || StringUtil.isEmpty(password)) {
            map.addAttribute("msg", "请输入相关信息");
            return "manager/login";
        }
        Manager manager = managerService.getManager(managerName, MD5Util.getMD5(password));
        if (manager == null) {
            map.addAttribute("msg", "账户或密码不正确");
            return "manager/login";
        }else if (manager.getStatus() != 1){
            map.addAttribute("msg", "账户当前不可用");
            return "manager/login";
        }
        setManagerSession(manager, request);
        return "redirect:/manager/main";
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }



    @RequestMapping({"/index","/"})
    public String index(ModelMap map,HttpServletRequest request,Product product){
        PageBean<Product> pageBean = productService.getProductPage(product);
        List<Notice> list = noticeService.selectNoticeLimit();
        User user = getUser(request);
        if(user != null){
            List<Car> userCar = carService.getUserCar(user.getUserId());
            map.addAttribute("cars",userCar!=null?userCar.size():null);
        }
        map.addAttribute("noticeList",list.size()<1?null:list);
        map.addAttribute("pageBean",pageBean);
        return "user/index";
    }

    @RequestMapping("/userToLogin")
    public String userToLogin(){
        return "user/login";
    }


    @RequestMapping("/userLogin")
    public String login(ModelMap map, HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)){
            map.addAttribute("msg","参数不得为空");
            return "user/login";
        }
        //用户名查询用户
        User user = userService.getUserByUserName(userName);
        if(user == null){
            map.addAttribute("msg","用户名不存在");
            return "user/login";
        }
        if(!user.getPassword().equals(MD5Util.getMD5(password))){
            map.addAttribute("msg","密码错误");
            return "user/login";
        }
        setUserSession(user,request);
        return "redirect:/index";
    }



    @RequestMapping("/userRegister")
    public String register(ModelMap map, HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        String pwdTwo = request.getParameter("pwdTwo");
        //非空校验
        if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(nickName) || StringUtil.isEmpty(password) || StringUtil.isEmpty(pwdTwo)){
           map.addAttribute("msg","参数不得为空");
           return "user/register";
        }
        //查收userName是否存在
        User user = userService.getUserByUserName(userName);
        if(user != null){
            map.addAttribute("msg","用户名已存在");
            return "user/register";
        }
        //密码验证
        if(!password.equals(pwdTwo)){
            map.addAttribute("msg","两次输入密码不一致");
            return "user/register";
        }
        user = new User();
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setPassword(MD5Util.getMD5(password));
        userService.addUser(user);
        setUserSession(user,request);
        return "redirect:/index";
    }


    @RequestMapping("product")
    public String getProduct(ModelMap map,HttpServletRequest request){
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Product product = productService.getById(productId);
        map.addAttribute("product",product);
        return "user/product";
    }

    @RequestMapping(value = "userNotice",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userNotice(Integer noticeId){
        Notice notice = noticeService.selectNoticeById(noticeId);
        return notice.getNoticeContext();
    }
}
