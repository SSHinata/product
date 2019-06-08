<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs" >
        <div title="用户信息" class="tableDiv">
            <form action="${pageContext.request.contextPath}/manager/userPage" method="post" id="inputForm">
            <table>
                <thead>
                <tr>
                    <th >用户编号</th>
                    <th >用户名</th>
                    <th >昵称</th>
                    <th >注册时间</th>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:forEach items="${pageBean.list}" var="user" >
                    <tr>
                        <td>
                                ${user.userId}
                        </td>
                        <td>
                                ${user.userName}
                        </td>
                        <td>
                                ${user.nickName}
                        </td>
                        <td>
                            ${user.createTimeStr}
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
</html>