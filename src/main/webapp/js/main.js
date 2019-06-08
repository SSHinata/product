function toProduct(productId) {
    window.location.href = "/product/product?productId="+productId;
}
function toCar(productId) {
    $.ajax({
        type: "post",
        url: "/product/userHome/addCar",
        data: {productId:productId},
        success: function(data){
            if( data == null || data == ""){
                window.location.href="userToLogin";
            }else if(data ==1){
                alert("添加成功");
            }else {
                alert("添加失败");
            }
        },error:function () {
            alert("toCar-error");
        }
    });
}

function toSubmit(productId) {
    window.location.href="/product/userHome/productSubmit?productId="+productId;
}

function ListPay(orderId) {
    if(window.confirm('是否确认付款')){
        $.ajax({
            type: "post",
            url: "/product/userHome/userListPay",
            data: {orderId:orderId},
            success: function(data){
                if(data == 1 ){
                    alert("付款成功");
                    window.location.href="/product/userHome/userOrder";
                }else {
                    alert("付款失败");
                }
            },error:function () {
                alert("listPay-error");
            }
        })
    }
}
function deleteOrder(orderId) {
    if(window.confirm('确定删除该订单信息')){
        $.ajax({
            type: "post",
            url: "/product/userHome/deleteOrder",
            data: {orderId:orderId},
            success: function(data){
                if(data ==1){
                    alert("删除成功");
                    window.location.href="/product/userHome/userOrder";
                }else {
                    alert("删除失败");
                }
            },error:function () {
                alert("delOrder-error");
            }
        })
    }
}
function receiveGood(orderId) {
    if(window.confirm('确认收货？')){
        $.ajax({
            type: "post",
            url: "/product/userHome/receiveGood",
            data: {orderId:orderId},
            success: function(data){
                if(data ==1){
                    alert("操作成功");
                    window.location.href="/product/userHome/userOrder";
                }else {
                    alert("操作失败");
                }
            },error:function () {
                alert("收货-error");
            }
        })
    }
}

function pay() {
    if(window.confirm('是否确认付款？')){
        $("#orderFrm").submit();
    }
}

function numberChange(text,number, carId) {
    if(number == null || number == ''){
        $(text).val(1);
        number =1;
    }
    $.ajax({
        type: "post",
        url: "/product/userHome/updateCarNumber",
        data: {number:number,carId:carId},
        success: function(data){
            if(data ==1){
                $(text).val(number);
                var gettds = $(text).parent().siblings();
                var getprice = $(gettds).eq(1).text();
                var allprice = getprice * number;
                $(gettds).eq(2).find('b').html(allprice.toFixed(2));
            }else {
                alert("操作失败");
            }
        },error:function () {
            alert("修改购物车数量-error");
        }
    })
}
function down(btn,carId,type) {
    var text = $(btn).next();
    var acount = parseInt($(text).val());
    if(acount == 1){
        return false;
    }
    $.ajax({
        type: "post",
        url: "/product/userHome/updateCarNumber",
        data: {carId:carId,type:type},
        success: function(data){
            if(data ==1){
                var text = $(btn).next();
                var acount = parseInt($(text).val());
                $(text).val(acount-=1);
                var gettds = $(text).parent().siblings();
                var getprice = $(gettds).eq(1).text();
                var allprice = getprice * acount;
                $(gettds).eq(2).find('b').html(allprice.toFixed(2));
            }else {
                alert("操作失败");
            }
        },error:function () {
            alert("数量- -error");
        }
    });
}
function up(btn,carId,type) {
    var text = $(btn).prev();
    var acount = parseInt($(text).val());
    if(acount == 99){
        return false;
    }
    $.ajax({
        type: "post",
        url: "/product/userHome/updateCarNumber",
        data: {carId:carId,type:type},
        success: function(data){
            if(data ==1){
                var text = $(btn).prev();
                var acount = parseInt($(text).val());
                $(text).val(acount+=1);
                var gettds = $(text).parent().siblings();
                var getprice = $(gettds).eq(1).text();
                var allprice = getprice * acount;
                $(gettds).eq(2).find('b').text(allprice.toFixed(2));
            }else {
                alert("操作失败");
            }
        },error:function () {
            alert("数量+ -error");
        }
    });
}

function deleteCar() {
    if(window.confirm('是否确认清空购物车？')){
        window.location.href="/product/userHome/deleteUserAll";
    }
}

function deleteCarOne(carId) {
    if(window.confirm("是否确认删除?")){
        window.location.href="/product/userHome/deleteOne?carId="+carId;
    }
}
function carPay() {
    if(window.confirm('是否确认全部购买？')){
        window.location.href="/product/userHome/carSubmit";
    }
}

function toFeedBack() {
    var conText = $("#feedBack").val().trim();
    if(conText == ''){
        alert("请输入相关内容");
        return false;
    }
    $.ajax({
        type: "post",
        url: "/product/userHome/toFeedBack",
        data: {conText:conText},
        success: function(data){
            if(data ==1){
                alert("留言成功");
                $("#feedBack").val("");
            }else {
                alert("留言失败");
            }
        },error:function () {
            alert("error");
        }
    });
}
function toUpdatePwd() {
    var pwd = $("#pwd").val().trim();
    var subPwd = $("#subPwd").val().trim();
    if(pwd == "" || subPwd == ""){
        alert("内容不可为空!");
        return false;
    }else if(pwd != subPwd){
        alert("两次密码输入不一致!");
        return false;
    }
    $.ajax({
        type: "post",
        url: "/product/userHome/updatePwd",
        data: {password:pwd},
        success: function(data){
            if(data ==1){
                alert("修改成功");
                window.location.href="/product/toLogin";
            }else {
                alert("修改失败");
            }
        },error:function () {
            alert("error");
        }
    });

}
function showPwd() {
    $("#feedBackDiv").hide();
    $("#updPwdDiv").show();
}
function showFeedBack() {
    $("#feedBackDiv").show();
    $("#updPwdDiv").hide();
}
function openDialog(noticeId){
    //先清理下内容
    $("#showDetail").html("");
    $.ajax({
        type: "post",
        url: "/product/userNotice",
        data: {noticeId:noticeId},
        dataType:"text",
        success: function(data){
            $("#showDetail").append(data);
        },error:function () {
            alert("error");
        }
    });
    document.getElementById('light').style.display='block';
    document.getElementById('fade').style.display='block'
}
function closeDialog(){
    document.getElementById('light').style.display='none';
    document.getElementById('fade').style.display='none'
}
