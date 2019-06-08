<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" flush="false"/>
<div region="center" title="">
    <div class="easyui-tabs" fit="true" border="false" id="tabs" >
        <div title="公告信息" class="tableDiv">
            <div style="text-align: left">
                <a class="button blue" href="/product/manager/toAddNotice">添加公告</a>
            </div>
            <form action="${pageContext.request.contextPath}/manager/noticePage" method="post" id="inputForm">
                <table>
                    <thead>
                    <tr>
                        <th >公告编号</th>
                        <th >公告标题</th>
                        <th >创建人</th>
                        <th >创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:forEach items="${pageBean.list}" var="notice" >
                        <tr>
                            <td>
                                    ${notice.noticeId}
                            </td>
                            <td>
                                    ${notice.noticeTitle}
                            </td>
                            <td>
                                    ${notice.createBy}
                            </td>
                            <td>
                                    ${notice.createTimeStr}
                            </td>
                            <td>
                                    <a href="/product/manager/toEditNotice?noticeId=${notice.noticeId}">修改</a>
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