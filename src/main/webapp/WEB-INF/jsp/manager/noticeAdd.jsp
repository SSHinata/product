<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include   page="header.jsp" flush="false"/>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="添加公告" class="addProductDiv">
            <form action="/product/manager/addNotice" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>公告标题:</td>
                        <td>
                            <input type="text" id="noticeTitle" name="noticeTitle">
                        </td>
                    </tr>
                    <tr>
                        <td>公告内容:</td>
                        <td>
                            <textarea type="text" id="noticeContext" name="noticeContext"></textarea>
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