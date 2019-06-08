<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/main.css"/>
<script class="jsbin"
        src="${pageContext.request.contextPath}/js/main.js"></script>
<jsp:include page="header.jsp" flush="false"/>

<style>
    .black_overlay {
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: #7E7E7E;
        z-index: 1001;
        -moz-opacity: 0.8;
        opacity: .80;
        filter: alpha(opacity=88);
    }

    .white_content {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 55%;
        height: 55%;
        padding: 20px;
        /*border: 10px solid orange;*/
        background-color: white;
        z-index: 1002;
        overflow: auto;
    }

    .close {
        float: right;
    }

    .detailDiv {
        margin-top: 5%;
    }

    .top {
        width: 100%;
        height: 45%;
        background-image: url(${pageContext.request.contextPath}/image/img/bgtop.jpg);
        background-repeat: no-repeat;
        /*background-color: #3a87ad;*/
    }

    .toptext {
        width: 80%;
        font-size: 14px;
        margin-left: 10px;
    }

    .toparea {
        position: absolute;
        width: 36%;
        top: 17%;
        left: 15%;
        /*background: #000;*/
        padding: 20px 30px;
        color: #fff;
        background-color: rgba(0, 0, 0, 0.2);
    }
</style>
<div class="top">
    <div class="toparea">
        <div class="toptext">徐州绿健乳业有限责任公司是中国乳制品工业协会常务理事单位，
            江苏省奶业协会副理事长单位。公司下辖乳品饮料有限公司和绿健奶蛋蛋食品有限公司、绿健餐饮企业管理有限公司、
            绿健澳大利亚投资公司及30家参、控股牧场，二十余家鲜奶吧。现饲养优质荷斯坦奶牛2万余头，液体奶产品达十大系列50余个品种。
        </div>
    </div>
</div>
<div style="width: 100%; height: 5%; font-size: 16px;">
    <hr>
    <c:choose>
        <c:when test="${noticeList != null}">
            <ul>
                <c:forEach items="${noticeList}" var="notice">
                    <li style="float: left; margin-right: 20%">
                        <a href="#" onclick="openDialog(${notice.noticeId})">${notice.noticeTitle}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            暂无公告
        </c:otherwise>
    </c:choose>
</div>
<br>
<div>
    <div>
        <form action="${pageContext.request.contextPath}/" method="post"
              id="inputForm">
            <c:forEach items="${pageBean.list}" var="product">
            <div class="productDiv">
            <b style="font-size: 15px">${product.productName}</b><br>
                <img src="/file/${product.imgName}"
                onclick="toProduct(${product.productId})"><br> <B
                style="color: red">￥${product.productPrice}</B>
    </div>
    </c:forEach>
    <hr style="width: 100%;margin-top:10px;"/>

    <%@ include file="../taglib/page.jsp" %>
    <%@ include file="../taglib/taglib.jsp" %>
    </form>
</div>
</div>
<div id="light" class="white_content">
    <div class="close">
        <a class="button small" href="javascript:void(0)"
           onclick="closeDialog()"> 关闭</a>
    </div>

    <div id="showDetail" class="detailDiv"></div>

</div>
<div id="fade" class="black_overlay"></div>
</body>
</html>
