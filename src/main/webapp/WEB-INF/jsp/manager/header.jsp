<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script class="jsbin" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/button.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<style>
    article, aside, figure, footer, header, hgroup,
    menu, nav, section {
        display: block;
    }

    .west {
        width: 200px;
        padding: 10px;
    }

    .north {
        height: 30px;
    }

    #loginOut {
        text-decoration: none;
        color: orangered;
    }

    #title {
        text-decoration: none;
        color: greenyellow;
    }

</style>
<html>
<head>
    <title>首页</title>
</head>
<body class="easyui-layout">
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(${pageContext.request.contextPath}/image/img/layout_arrows.png) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
  <span style="float:right; padding-right:20px;" class="head">
    欢迎 ${sessionScope.manager.managerName}
    <a href="/product/manager/logout" id="loginOut">安全退出</a>
  </span>
    <span style="padding-left:10px; font-size: 16px; ">
    <img src="${pageContext.request.contextPath}/image/img/blocks.gif" width="20" height="20" align="absmiddle"/> <a
            href="/product/manager/main" id="title">产品后台管理系统</a>
  </span>
</div>
