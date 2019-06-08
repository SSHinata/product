<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
<div class="login_box">
    <div class="login_l_img"><a href="/product/index"><img src="${pageContext.request.contextPath}/image/img/login-img.png" /></a></div>
    <div class="login">
        <div class="loginDiv">
        <div class="login_name" >
            <p>用户注册</p>
        </div>
        <form action="/product/userRegister" method="post">
            <input name="userName" type="text" id="userName"  placeholder="用户名" oninput="value=value.replace(/[^\d]/g,'')">
            <input name="nickName" type="text"  id="nickName" placeholder="昵称"/>
            <input name="password" type="password"  id="password" placeholder="密码"/>
            <input name="pwdTwo" type="password"  id="pwdTwo" placeholder="确认密码"/>
            <div style="height: 20px">
                <c:if test="${msg != null}">
                    <span >${msg}</span>
                </c:if>
            </div>
            <input value="注册" style="width:100%;" type="submit">
        </form>
        <a href="/product/toLogin">去登录</a>
        </div>
    </div>
</div>