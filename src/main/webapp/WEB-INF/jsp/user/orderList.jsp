<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<jsp:include page="header.jsp" flush="false"/>
<style>
    .payButton{
        float: right;margin-right: 3%;color: white;background-color: red;border-radius: 4px;border: none;font-size: 16px;
    }
    .receiveButton{
        float: right;margin-right: 3%;color: white;background-color: green;border-radius: 4px;border: none;font-size: 16px;
    }
    .lajitong{
        float: right;margin-right: 1%;
    }
</style>
<div style="width: 100%">
    <c:choose>
        <c:when test="${orderList != null}">
            <c:forEach items="${orderList}" var="order">
                <div style="margin-top: 3%;border: solid #C6E5FF 1px">
                    <div style="background-color: #EAF8FF;font-size: 18px">
                        <span style="margin-right: 20%">订单编号:${order.orderCode}</span>
                        <span style="margin-right: 20%">
                    <c:if test="${order.status == 1}">
                        <span style="color: red">未付款</span>
                    </c:if>
                    <c:if test="${order.status == 2}">
                        已付款
                    </c:if>
                    <c:if test="${order.status == 3}">
                        已发货
                    </c:if>
                    <c:if test="${order.status == 4}">
                        已完成
                    </c:if>
                </span>
                        <c:if test="${order.status != 1}">
                            <span>付款时间:${order.payTimeStr}</span>
                        </c:if>
                        <img src="${pageContext.request.contextPath}/image/img/lajitong.png" class="lajitong" onclick="deleteOrder(${order.ordersId})">
                        <c:if test="${order.status == 1}">
                            <button class="payButton" onclick="ListPay(${order.ordersId})">立即付款</button>
                        </c:if>
                        <c:if test="${order.status == 3}">
                            <button class="receiveButton" onclick="receiveGood(${order.ordersId})">确认收货</button>
                        </c:if>
                    </div>
                    <br>
                    <table rules="rows" style="width: 97%;margin-left: 3%;background-color: #F2F2F2">
                        <tbody>
                        <c:forEach items="${order.items}" var="item" varStatus="number">
                            <tr style="">
                                <td style="width: 35%">
                                    <div>
                                        <div style="float: left">
                                            <img src="/file/${item.imgName}" style="width: 80px">
                                        </div>
                                        <div style="float: left;margin-left: 5%">
                                                ${item.productName}
                                        </div>
                                        </div>
                                    </div>
                                </td>
                                <td style="width: 15%">${item.price}</td>
                                <td style="width: 20%">${item.number}</td>
                                <td style="width: 20%;color: red">￥ ${item.subtotal}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="margin-left: 75%;color: red;font-size: 30px">
                        总金额:￥ ${order.sumMoney}
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div style="width: 100%;text-align: center;font-size: 50px;margin-top: 5%">
                无订单信息
            </div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>