jQuery(document).ready(function($){
    $.ajax(
        BASE_PATH + "/user/check/",
        {type: "post",
        dateType: "json",
        data: {"userName": userName, "password": passWord},
        timeout: 10000,
        headers:{
        	'Content-Type':'application/x-www-form-urlencoded'
        },
        success: function f(data) {
            var result = data.userName;
            if(result != null){
                $('.cd-user-modal').removeClass('is-visible');
                $(".cd-signin").hide();
                $("#myMessage").text(result);
            }
            else{
                alert("用户名或密码错误，请重新登陆")
            }
        },
        error: function f(data) {
            alert("lose");
        }
    });
});