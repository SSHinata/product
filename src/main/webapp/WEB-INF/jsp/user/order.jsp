<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script class="jsbin" src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<jsp:include page="header.jsp" flush="false"/>
<style>
    .payButton{
        color: white;background-color: red;border-radius: 4px;border: none;font-size: 30px;margin-top: 2%;
    }
</style>
<div>
    <div style="margin-top: 1%">
        <div style="background-color: #EAF8FF;font-size: 18px">
            订单编号:${order.orderCode}
        </div>
        <table rules="rows" style="width: 94%;margin-left: 3%;background-color: #F2F2F2;text-align: center">
            <thead>
            <tr>
                <td>商品信息</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.items}" var="item">
                <tr>
                    <td>
                        <div>
                            <div style="float: left">
                                <img src="/file/${item.imgName}" style="width: 100px">
                            </div>
                            <div style="float: left;margin-top: 35px;margin-left: 30%;">
                                    ${item.productName}
                            </div>
                        </div>
                    </td>
                    <td>${item.price}</td>
                    <td>${item.number}</td>
                    <td>${item.subtotal}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div style="float: right;margin-right: 5%;font-size: 30px;text-align: center">
        <div>
            <span style="color: red">总金额:${order.sumMoney}</span>
        </div>
        <div>
            <form id="orderFrm" action="/product/userHome/userPay" method="post">
                <input type="hidden" name="orderId" value="${order.ordersId}">
                <input id="payButton" type="button"  class="payButton" onclick="pay()" value="立即付款">
            </form>
        </div>
    </div>
</div>


</body>
</html>