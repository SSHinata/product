<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script class="jsbin"
        src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/main.css"/>
<html>
<head>
    <title>产品</title>
</head>
<body style="width: 80%; margin-left: 10%">
<div style="height: 45px;font-size: 20px; padding-top: 10px;">
		<span style="float: left"> <a href="/product/index"> 首页</a>
		</span> <span style="float: right"> <c:choose>
    <c:when test="${sessionScope.user != null}">
        欢迎 <a href="/product/userHome/toHome">${sessionScope.user.nickName}</a>
        | &nbsp; <a href="/product/userHome/userOrder"> 我的订单</a>
        | &nbsp; <a href="/product/userHome/userCar"> 购物车 <c:if
            test="${cars != null}">
        <span style="color: red">${cars}</span>
    </c:if>
					</a>
        | &nbsp; <a href="/product/userHome/logout" id="loginOut">安全退出</a>
    </c:when>
    <c:otherwise>
        <a href="/product/toLogin"> 亲!&nbsp;请登录 </a>
    </c:otherwise>
</c:choose>
		</span>
</div>
</body>
</html>