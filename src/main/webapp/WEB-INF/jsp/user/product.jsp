<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<script class="jsbin" src="${pageContext.request.contextPath}/js/main.js"></script>
<jsp:include page="header.jsp" flush="false"/>
<div>
    <div class="productDetailDiv">
        <div class="productDetailDiv1">
            <img src="/file/${product.imgName}" onclick="toProduct(${product.productId})"><br>
        </div>
        <div class="productDetailDiv2">
            <div style="height: 350px;font-size: 30px">
                商品名称: ${product.productName}<br>

                商品价格: <B style="color: red">￥${product.productPrice}</B><br>

                商品描述: ${product.remark}<br>
            </div>
            <div class="action">
                 <button onclick="toSubmit(${product.productId})">立即购买</button>
                 <button onclick="toCar(${product.productId})">添加购物车</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
