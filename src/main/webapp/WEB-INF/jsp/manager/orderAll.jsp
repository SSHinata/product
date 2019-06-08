<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include page="header.jsp" flush="false"/>
<style>
    table{
        width: 100%;
        text-align: center;
    }
    button{
        border-radius: 4px;border: none;
    }
</style>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="订单信息" class="ordersInfo">
            <form action="${pageContext.request.contextPath}/manager/orderPage" method="post" id="inputForm">
            <table>
                <thead>
                <tr>
                    <th>订单号</th>
                    <th>用户</th>
                    <th>创建时间</th>
                    <th>状态</th>
                    <th>付款时间</th>
                    <th>订单总价</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageBean.list}" var="order">
                    <tr>
                        <td>
                                ${order.orderCode}
                        </td>
                        <td>
                                ${userMap[order.userId]}
                        </td>
                        <td>
                            ${order.createTimeStr}
                        </td>
                        <td>
                            <c:if test="${order.status == 1}">
                                <span style="color: red">未付款</span>
                            </c:if>
                            <c:if test="${order.status == 2}">
                                已付款
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.status != 1}" >
                                ${order.payTimeStr}
                            </c:if>
                        </td>
                        <td>
                           ${order.sumMoney}
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
    function sendGood(orderId) {
        if(window.confirm("确定发货？")){
            $.ajax({
                type: "post",
                url: "/admin/sendGood",
                data: {orderId:orderId},
                success: function(data){
                    if(data ==1){
                        alert("发货成功");
                        window.location.href = "/admin/orderAll";
                    }else {
                        alert("发货失败")
                    }
                },error:function () {
                    alert("error")
                }
            });
        }
    }
</script>
</html>