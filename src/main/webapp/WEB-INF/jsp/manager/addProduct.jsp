<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include   page="header.jsp" flush="false"/>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="添加商品" class="addProductDiv">
            <form action="/product/manager/addProduct" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>商品名称:</td>
                        <td>
                            <input type="text" id="productName" name="productName">
                        </td>
                    </tr>
                    <tr>
                        <td>商品价格:</td>
                        <td>
                            <input type="text" id="productPrice" name="productPrice">
                        </td>
                    </tr>
                    <tr>
                        <td>商品图片:</td>
                        <td>
                            <input type="file" id="productFile" name="productFile">
                        </td>
                    </tr>
                    <tr>
                        <td>商品描述:</td>
                        <td>
                            <input type="text" id="productRemark" name="productRemark">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input class="button blue medium" type="submit" value="添加">
                        </td>
                    </tr>
                </table>
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