<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include   page="header.jsp" flush="false"/>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="添加管理员" class="addProductDiv">
            <form action="/product/manager/addManager" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>管理员用户名:</td>
                        <td>
                            <input type="text" id="managerName" name="managerName">
                        </td>
                    </tr>
                    <tr>
                        <td>管理员密码:</td>
                        <td>
                            <input type="text" id="password" name="password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            状态:
                        </td>
                        <td>
                            <select id="status" name="status">
                                <option value="1">可用</option>
                                <option value="0">不可用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input  class="button blue medium" type="submit" value="添加">
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