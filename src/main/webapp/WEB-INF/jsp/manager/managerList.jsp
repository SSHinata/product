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
        <div title="管理员信息" class="tableDiv">
            <div style="text-align: left">
                <a class="button blue" href="/product/manager/toAddManager">添加管理员</a>
            </div>
            <form action="${pageContext.request.contextPath}/manager/managerPage" method="post" id="inputForm">
                <table>
                    <thead>
                    <tr>
                        <th >管理员编号</th>
                        <th >管理员用户名</th>
                        <th >状态</th>
                        <th >创建时间</th>
                        <th >创建人</th>
                        <th >操作</th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:forEach items="${pageBean.list}" var="manager" >
                        <tr>
                            <td>
                                    ${manager.managerId}
                            </td>
                            <td>
                                    ${manager.managerName}
                            </td>
                            <td>
                                <c:if test="${manager.status ==1 }">
                                    可用
                                </c:if>
                                <c:if test="${manager.status ==0 }">
                                    <span style="color:red;">不可用</span>
                                </c:if>
                            </td>
                            <td>
                                    ${manager.createTimeStr}
                            </td>
                            <td>
                                    ${manager.createBy}
                            </td>
                            <td>
                                <c:if test="${manager.managerName != 'admin'}">
                                    <a href="/product/manager/toEditManager?managerId=${manager.managerId}">修改</a>|
                                    <a href="#" onclick="deleteManager(${manager.managerId}, ${manager.managerName})">删除</a>
                                </c:if>
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
<script>
    function deleteManager(managerId,managerName) {
        var name = '${sessionScope.manager.managerName}';
        if(name == managerName){
            alert("不可以自己删除自己");
            return false;
        }
        if(window.confirm("确认删除该管理员")){
            $.ajax({
                type: "post",
                url: "/product/manager/deleteManager",
                data: {managerId:managerId},
                success: function(data){
                    if(data ==1){
                        alert("操作成功");
                        window.location.href = "/product/manager/managerPage";
                    }else {
                        alert("操作失败")
                    }
                },error:function () {
                    alert("error")
                }
            });
        }

    }
</script>
</html>