<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/1/7
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body.css"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
</head>
<div class="container">
    <section id="content">
        <form action="/product/managerLogin" method="post">
            <h1>管理员登录</h1>
            <div>
                <input type="text" placeholder="用户名" required="" id="managerName"  name="managerName"/>
            </div>
            <div>
                <input type="password" placeholder="密码" required="" id="password" name="password" />
            </div>
            <div class="">
                <c:if test="${msg != null}">
                    <span style="color: red">${msg}</span>
                </c:if>
                <span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
            </div>
            <div>
                <input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>

            </div>
        </form>
        <div class="button">
            <a href="/product">网站首页</a>
            <span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
        </div>
    </section>
</div>


<br><br><br><br>
</body>
</html>
