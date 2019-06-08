<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery分页样式</title>

<style>
*{margin:0px; padding:0px;}
body{margin:0 auto; font-size:12px; color:#666; font-family:"微软雅黑", Simsun;}
li{list-style:none;}
img{border:none;}
a, a:visited{text-decoration:none;}

.tong{ background:url(../images/images/sy-toubjing_03.gif) repeat-x 0px -11px; width:100%; height:40px;}
.wrap{ width:auto; max-width:320px; margin:0 auto;}
.center{ width:103%; margin:0 auto;}

/***************分页******************/
.fenye{ float:left; margin-top:10px;}
.fenye ul{ float:left; margin-left:32px; }
.fenye ul li{ float:left; margin-left:5px;padding: 4px 6px; border:1px solid #ccc;  font-weight:bold; cursor:pointer; color:#999;}
.fenye ul li a{ color:#999;}
.fenye ul li.xifenye{ width:38px; text-align:center; float:left; position:relative;cursor: pointer;}
.fenye ul li .xab{ float:left; position:absolute; width:39px; border:1px solid #ccc; height:123px; overflow-y: auto;overflow-x: hidden;top:-125px; background-color: #fff; display:inline;left:-1px; width:50px;}
.fenye ul li .xab ul{ margin-left:0; padding-bottom:0;}
.fenye ul li .xab ul li{ border:0; padding:4px 0px; color:#999; width:34px; margin-left:0px; text-align:center;}

</style>

</head>

<body>

<!--下一页--->
<div class="wrap">
    <div class="fenye">
    	
    	<ul>
        	<li id="first" onclick="first()">首页</li>
            <li id="top" onclick="topclick(${pageBean.pageNum-1})">上一页</li>
            <li class="xifenye" id="xifenye">
            	<a id="xiye">${pageBean.pageNum }</a>/<a id="mo">${pageBean.pages }</a>
                <div class="xab" id="xab" style="display:none">
                	<ul id="uljia">
                    	
                    </ul>
                </div>
            </li>
            <li id="down" onclick="downclick(${pageBean.pageNum+1})">下一页</li>
            <li id="last" onclick="last(${pageBean.pages })">末页</li>
        </ul>
    </div>
</div>
</body>
</html>