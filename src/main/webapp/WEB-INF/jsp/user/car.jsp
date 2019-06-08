<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script class="jsbin" src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<jsp:include page="header.jsp" flush="false"/>
<style>
    input{
        width: 8%;
    }
</style>
<div>
    <c:choose>
        <c:when test="${carList != null}">
            <table class="carTable" style="width: 100%;text-align: center;">
                <thead>
                <tr>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>金额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="goods">
                <c:forEach items="${carList}" var="car">
                    <tr>
                        <td style="width: 40%">
                            <div>
                                <div style="float: left;margin-left: 3%">
                                    <img src="/file/${car.imgName}" style="width: 150px">
                                </div>
                                <div style="float: left;margin-left: 6%;margin-top: 60px;">
                                        ${car.productName}
                                </div>
                            </div>
                        </td>
                        <td style="width: 10%">
                                ${car.price}
                        </td>
                        <td style="width: 30%">
                            <button onclick="down(this,${car.carId},'down')">-</button>
                            <input type="text" name="number" value="${car.number}" MAXLENGTH="2" onchange="numberChange(this,this.value,${car.carId})" oninput="value=value.replace(/[^\d]/g,'')">
                            <button onclick="up(this,${car.carId},'add')">+</button>
                        </td>
                        <td style="width: 10%">
                            <span style="color: red;">￥ <b>${car.price * car.number}</b></span>
                        </td>
                        <td style="width: 10%">
                            <a href="#" onclick="deleteCarOne(${car.carId})">删除</a>
                        </td>
                    </tr>
                </c:forEach>

                <tr class="buttonClass">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><button onclick="deleteCar()">立即清空</button></td>
                    <td><button onclick="carPay()">全部购买</button></td>
                </tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
           <div style="width: 100%;text-align: center;font-size: 50px;margin-top: 5%">
               购物车为空
           </div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>