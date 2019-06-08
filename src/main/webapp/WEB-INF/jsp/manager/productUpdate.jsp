<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="修改商品">
            <form action="/product/manager/updateProduct" method="post" enctype="multipart/form-data">
                <input type="hidden" name="productId" value=${product.productId}>
                商品名称:<input type="text" id="productName" name="productName" value="${product.productName}"><br><br>
                商品价格:<input type="text" id="productPrice" name="productPrice" value="${product.productPrice}"><br><br>
                商品图片:<br><img src="/file/${product.imgName}" style="width: 500px;"><br><br>
                <input type="file" id="productFile" name="productFile"><br><br>
                商品描述:<input type="text" id="productRemark" name="productRemark" value="${product.remark}"><br><br>
                <input class="button blue medium" type="submit" value="修改">
                <c:if test="${msg != null }">
                    <span style="color: red">${msg}</span>
                </c:if>
            </form>
        </div>
    </div>
</div>
<jsp:include page="left.jsp" flush="false"/>
</body>
</html>