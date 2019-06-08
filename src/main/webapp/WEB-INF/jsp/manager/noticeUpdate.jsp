<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="修改公告">
            <form action="/product/manager/editNotice" method="post">
                <input type="hidden" name="noticeId" value=${notice.noticeId}>
                公告标题:<input type="text" id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle}"><br><br>
                公告内容:<textarea type="text" id="noticeContext" name="noticeContext" >${notice.noticeContext}</textarea><br><br>
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