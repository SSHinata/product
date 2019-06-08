<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script class="jsbin" src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
<jsp:include page="header.jsp" flush="false"/>
<style>
    .button {
        display: inline-block;
        outline: none;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
        padding: .5em 2em .55em;
        text-shadow: 0 1px 1px rgba(0,0,0,.3);
        -webkit-border-radius: .5em;
        -moz-border-radius: .5em;
        border-radius: .5em;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
        -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
        box-shadow: 0 1px 2px rgba(0,0,0,.2);
    }
    .button:hover {
        text-decoration: none;
    }
    .button:active {
        position: relative;
        top: 1px;
    }

    .blue {
        color: #d9eef7;
        border: solid 1px #0076a3;
        background: #0095cd;
        background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
        background: -moz-linear-gradient(top,  #00adee,  #0078a5);
        filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#00adee', endColorstr='#0078a5');
    }
    .blue:hover {
        background: #007ead;
        background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
        background: -moz-linear-gradient(top,  #0095cc,  #00678e);
        filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#0095cc', endColorstr='#00678e');
    }
    .blue:active {
        color: #80bed6;
        background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee));
        background: -moz-linear-gradient(top,  #0078a5,  #00adee);
        filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#0078a5', endColorstr='#00adee');
    }
    .small {
        font-size: 11px;
        padding: .2em 1em .275em;
    }
    input{
        border: 1px solid #ccc;
        padding: 7px 0px;
        border-radius: 3px;
        padding-left:5px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
    }
    input:focus{
        border-color: #66afe9;
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
    }
    #feedBack{
        width: 40%;
    }

</style>
<hr>
<div style="display: inline">
    <div style="float: left">
    <a href="#" class="button blue" onclick="showPwd()">修改密码</a><br><br>
    <a href="#" class="button blue" onclick="showFeedBack()">反馈留言</a>
    </div>
    <div style="border: #00aeef 1px dashed;float: left;margin-left: 3%;width: 85%;height: 13%">
        <div id="feedBackDiv" style="display: none;margin-left: 3%;margin-top: 3%">
            留言:<input type="text" id="feedBack" name="feedBack" >&nbsp;&nbsp;&nbsp;
            <a href="#" onclick="toFeedBack()" class="button blue small">留言</a>
        </div>
        <div id="updPwdDiv" style="display:none;margin-left: 3%;margin-top: 3%">
            新密码:<input id="pwd" type="password" name="feedBack" >&nbsp;&nbsp;&nbsp;

            确认密码:<input id="subPwd" type="password" name="feedBack" >&nbsp;&nbsp;&nbsp;
            <a href="#" onclick="toUpdatePwd()" class="button blue small">修改</a>
        </div>
    </div>
</div>
</body>
</html>