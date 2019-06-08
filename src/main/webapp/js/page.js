
//分页的显示与隐藏
$("#xifenye").click(function(a){
	$("#uljia").empty();
	$("#xab").toggle();
	//显示出的一共多少页
	var uljia=$("#uljia");
	var page=parseInt($("#xiye").html());//获取当前的页数
	var pages=parseInt($("#mo").html());//获取当前的总页数
	for(var i=1;i<=pages;i++) 
	{
		var H="<li  onclick='fl("+i+","+pages+")'>"+i+"</li>";//添加一共多少页和点击事件
		
		uljia.append(H);
	}
	scrolltop(page);
})
//点击分页显示的方法
function fl(p1,p2){
		//var p=p1;
		$("#xiye").empty();
		$("#xiye").html(p1);//给显示的页数赋值
		
		}
//分页的的上一页和下一页
function topclick(pageNum){
	var v=document.getElementById("xiye");
	var num=v.innerHTML;
	if(num>1)
	{
		num--;
		v.innerHTML=num;
		var hei=25*num-25;
		$("#xab").scrollTop(hei);
		var paths = document.getElementById("inputForm").action;
		var path = paths+"?pageNum="+pageNum;  
		$('#inputForm').attr("action", path).submit();
	}
}
function downclick(pageNum){
	var pages=parseInt($("#mo").html());//获取当前的总页数
	var v=$("#xiye");
	var num=parseInt(v.html());
	if(num < pages){
		num = ++num;
		v.html(num);
		scrolltop(num);
		var paths = document.getElementById("inputForm").action;
		var path = paths+"?pageNum="+pageNum;  
		$('#inputForm').attr("action", path).submit();
		}
	}
//分页的的首页和未页
function first(){
	var v=document.getElementById("xiye");
	v.innerHTML=1;
	scrolltop(v.innerHTML);
	var paths = document.getElementById("inputForm").action;
	var path = paths+"?pageNum=1";  
	$('#inputForm').attr("action", path).submit();
}

function last(pageNum){
	var v=document.getElementById("xiye");
	var l=document.getElementById("mo");
	v.innerHTML=l.innerHTML;
	scrolltop(v.innerHTML);
	var paths = document.getElementById("inputForm").action;
	var path = paths+"?pageNum="+pageNum;  
	$('#inputForm').attr("action", path).submit();
}
//滚动条
function scrolltop(top){
	var hei=25*top-25;
	$("#xab").scrollTop(hei);
	}