<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
<div class="login_box">
    <div class="login_l_img"><a href="/product/index"><img src="${pageContext.request.contextPath}/image/img/login-img.png"/></a></div>
    <div class="login">
        <div class="login_logo"><a href="#"><img src="${pageContext.request.contextPath}/image/img/login_logo.png"/></a></div>
        <div class="login_name">
            <p>欢迎登录</p>
        </div>
        <form action="/product/userLogin" method="post">
            <input name="userName" type="text" id="userName" placeholder="用户名" MAXLENGTH="10" oninput="value=value.replace(/[^\d]/g,'')">
            <input name="password" type="password" id="password" placeholder="密码"/>
            <div style="height: 20px">
                <c:if test="${msg != null}">
                    <span>${msg}</span>
                </c:if>
            </div>
            <input value="登录" style="width:100%;" type="submit">
        </form>
        <a href="/product/toRegister">去注册</a>
    </div>
</div>