<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" >
    <div class="easyui-tabs" fit="true" border="false" id="tabs" >
        <div title="商品信息" class="tableDiv">
            <div style="text-align: left">
                <a class="button blue" href="/product/manager/toAddProduct">添加商品</a>
            </div>
            <form action="${pageContext.request.contextPath}/manager/productPage" method="post" id="inputForm">
            <table>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>图片</th>
                    <th>价格</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageBean.list}" var="product">
                    <tr>
                        <td>
                                ${product.productName}
                        </td>
                        <td>
                            <img src="/file/${product.imgName}" style="height: 50px">
                        </td>
                        <td>
                                ${product.productPrice}
                        </td>
                        <td>
                            <c:if test="${product.productStatus ==1 }">
                                上架
                            </c:if>
                            <c:if test="${product.productStatus ==0 }">
                                <span style="color:red;">下架</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${product.productStatus ==1 }">
                                <a href="javascript:void(0)" onclick="updateStatus(${product.productId},0)">下架</a>
                            </c:if>
                            <c:if test="${product.productStatus ==0 }">
                               <a href="javascript:void(0)" onclick="updateStatus(${product.productId},1)">上架</a>
                            </c:if>
                            /<a href="/product/manager/toUpdateProduct?productId=${product.productId}">修改</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <%@ include file="../taglib/page.jsp"%>
                <%@ include file="../taglib/taglib.jsp"%>
            </form>
        </div>
    </div>
</div>
<jsp:include page="left.jsp" flush="false"/>
</body>
<script type="text/javascript">
    function updateStatus(productId,status) {
        $.ajax({
            type: "post",
            url: "/product/manager/upAndDown",
            data: {productId:productId,status:status},
            success: function(data){
                if(data ==1){
                    alert("操作成功");
                    window.location.href = "/product/manager/productPage";
                }else {
                    alert("操作失败")
                }
            },error:function () {
                alert("error")
            }
        });
    }
</script>
</html>