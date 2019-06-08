<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs" >
        <div title="用户反馈信息" class="tableDiv">
            <form action="${pageContext.request.contextPath}/manager/feedBackPage" method="post" id="inputForm">
                <table>
                    <thead>
                    <tr>
                        <th >反馈编号</th>
                        <th >反馈内容</th>
                        <th >反馈用户</th>
                        <th >反馈时间</th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:forEach items="${pageBean.list}" var="feedBack" >
                        <tr>
                            <td>
                                    ${feedBack.feedbackId}
                            </td>
                            <td>
                                    ${feedBack.feedbackContext}
                            </td>
                            <td>
                                    ${feedBack.createBy}
                            </td>
                            <td>
                                    ${feedBack.createTimeStr}
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