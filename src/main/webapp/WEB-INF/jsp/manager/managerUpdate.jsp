<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="修改管理员">
            <form action="/product/manager/editManager" method="post">
                <input type="hidden" name="managerId" value=${manager.managerId}>
                管理员用户名:<input type="text" id="managerName" name="managerName" value="${manager.managerName}"
                              readonly="readonly"><br><br>
                管理员密码:<input type="text" id="password" name="password" placeholder="不输入默认为原密码"><br><br>
                <select id="status" name="status">
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
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