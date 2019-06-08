package com.product.controller.user;

import com.product.controller.Base;
import com.product.model.Car;
import com.product.model.Feedback;
import com.product.model.Notice;
import com.product.model.Orders;
import com.product.model.User;
import com.product.service.ICarService;
import com.product.service.IFeedBackService;
import com.product.service.INoticeService;
import com.product.service.IOrderService;
import com.product.service.IUserService;
import com.product.util.MD5Util;
import com.product.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userHome/")
public class UserHomeController extends Base{

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICarService  carService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IFeedBackService feedBackService;

    @Autowired
    private IUserService userService;

    @RequestMapping
    public String userMain(){
        return "user/login";
    }

    //用户退出
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "user/login";
    }

    //用户进入购物车
    @RequestMapping("userCar")
    public String getUserCar(ModelMap map, HttpServletRequest request){
        User user = getUser(request);
        List<Car> carList = carService.getUserCar(user.getUserId());
        map.addAttribute("carList",carList);
        return "user/car";
    }

    //用户查看定单
    @RequestMapping("userOrder")
    public String getUserOrder(ModelMap map,HttpServletRequest request){
        User user = getUser(request);
        List<Orders> orderList = orderService.getUserOrder(user.getUserId());
        map.addAttribute("orderList",orderList.size() > 0?orderList:null);
        return "user/orderList";
    }

    //添加商品进购物车
    @RequestMapping("addCar")
    @ResponseBody
    public Integer addCar(HttpServletRequest request){
        User user = getUser(request);
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Integer i = carService.add(user.getUserId(), productId);
        return i;
    }

    //修改购物车数量
    @RequestMapping("updateCarNumber")
    @ResponseBody
    public Integer updateCarNumber(HttpServletRequest request){
        String type = request.getParameter("type");
        Integer carId = Integer.valueOf(request.getParameter("carId"));
        //type为空  直接修改数量
        if(StringUtil.isEmpty(type)){
            Integer number = Integer.valueOf(request.getParameter("number"));
            Integer update = carService.update(carId, number);
            return update;
        }else if(type.equals("add")){
            Car car = carService.getCarById(carId);
            Integer update = carService.update(carId, car.getNumber() + 1);
            return update;
        }else if(type.equals("down")){
            Car car = carService.getCarById(carId);
            if(car.getNumber() < 2){
                return 0;
            }
            Integer update = carService.update(carId, car.getNumber() - 1);
            return update;
        }
        return 0;
    }

    //删除购物车中的商品
    @RequestMapping("deleteOne")
    public String deleteOne(HttpServletRequest request){
        Integer carId = Integer.valueOf(request.getParameter("carId"));
        carService.deleteOne(carId);
        return "redirect:/userHome/userCar";
    }

    //清空购物车
    @RequestMapping("deleteUserAll")
    public String deleteUserAll(HttpServletRequest request){
        User user = getUser(request);
        carService.deleteUserAll(user.getUserId());
        return "redirect:/userHome/userCar";
    }

    //购物车结算
    @RequestMapping("carSubmit")
    public String userSubmit(ModelMap map,HttpServletRequest request){
        User user = getUser(request);
        Orders order = orderService.carAdd(user.getUserId());
        map.addAttribute("order",order);
        return "user/order";
    }

    //商品页直接购买
    @RequestMapping("productSubmit")
    public String productSubmit(ModelMap map,HttpServletRequest request){
        String productId = request.getParameter("productId");
        User user = getUser(request);
        Orders order = orderService.productAdd(user.getUserId(), Integer.parseInt(productId));
        map.addAttribute("order",order);
        return "user/order";
    }

    //支付
    @RequestMapping("userPay")
    public String userPay(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        orderService.update(Integer.parseInt(orderId),2);
        return "redirect:/userHome/userOrder";
    }

    //订单列表支付
    @RequestMapping("userListPay")
    @ResponseBody
    public Integer userListPay(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        int update = orderService.update(Integer.parseInt(orderId), 2);
        return update;
    }


    //删除订单
    @RequestMapping("deleteOrder")
    @ResponseBody
    public Integer deleteOrderById(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        Integer integer = orderService.deleteOrderById(Integer.parseInt(orderId));
        return integer;
    }

    //反馈
    @RequestMapping("userFeedBack")
    @ResponseBody
    public Integer userFeedBack(ModelMap modelMap,HttpServletRequest request){
        String conText = request.getParameter("conText");
        if(StringUtil.isEmpty(conText)){
            return 1;
        }
        Feedback feedback = new Feedback();
        User user = getUser(request);
        feedback.setCreateBy(user.getUserName());
        feedback.setFeedbackContext(conText);
        int i = feedBackService.addFeedBack(feedback);
        return 1;
    }

    //转到注册页面
    @RequestMapping("toEditUser")
    public String toEditUser(ModelMap modelMap,HttpServletRequest request){
        User user = getUser(request);
        modelMap.addAttribute("user",user);
        return "user/editUser";
    }

    //用户注册
    @RequestMapping("editUser")
    public String editUser(ModelMap modelMap, HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        if(StringUtil.isEmpty(nickName)|| StringUtil.isEmpty(password)){
            modelMap.addAttribute("msg","请完善相关信息");
        }else {
            User user = new User();
            user.setUserId(userId);
            user.setNickName(nickName);
            user.setPassword(MD5Util.getMD5(password));
            int i =userService.updateUserInfo(user);
            if(i > 0){
                request.getSession().setAttribute("user",user);
                return "redirect:/";
            }else {
                modelMap.addAttribute("msg","修改失败");
            }
        }
        return "user/editUser";
    }

    //转到个人主页
    @RequestMapping("toHome")
    public String toHome(){
        return "user/home";
    }

    //用户修改密码
    @RequestMapping("updatePwd")
    @ResponseBody
    public int  updatePwd(HttpServletRequest request){
        String password = request.getParameter("password");
        if(StringUtil.isEmpty(password)){
            return 0;
        }
        User user = new User();
        user.setUserId(getUser(request).getUserId());
        user.setPassword(MD5Util.getMD5(password));
        return userService.updateUserInfo(user);
    }

    @RequestMapping("updateNickName")
    @ResponseBody
    public int updateNickName(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        if(StringUtil.isEmpty(nickName)){
            return 0;
        }
        User user = new User();
        user.setUserId(getUser(request).getUserId());
        user.setNickName(nickName);
        int i;
        i = userService.updateUserInfo(user);
        if(i > 0){
            request.getSession().setAttribute("user",user);
        }
        return i;
    }

    @RequestMapping("toFeedBack")
    @ResponseBody
    public int toFeedBack(HttpServletRequest request){
        String conText = request.getParameter("conText");
        if(StringUtil.isEmpty(conText)){
            return 0;
        }
        Feedback feedback = new Feedback();
        feedback.setFeedbackContext(conText);
        feedback.setCreateBy(getUser(request).getUserName());
        feedback.setCreateTime(new Date());
        return feedBackService.addFeedBack(feedback);
    }
}
