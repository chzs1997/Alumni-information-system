jQuery(document).ready(function($) {

    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    $.ajax({
        url: BASE_PATH + "/manager/detectState",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var managerName = data.managerName;
            if(managerName == "尚未登陆"){
                out_login();
            }
            else{
                var managerName = managerName;
                var gender = data.managerGender;
                var identity = data.managerIdentity;
                if(managerName != null){
                    $("#username_navigator").text(managerName);
                    $("#username_right").text(managerName);
                    $("#userIdentity_right").text(identity);
                }
            }
        },
        error: function f() {
        }
    });
});

<!--管理员登录-->
function login() {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var managerName = $("#managerName").val();
    var managerPassword = $("#managerPassword").val();
    $.ajax({
        url: BASE_PATH + "/manager/login",
        type: "post",
        dateType: "json",
        data: {"managerName": managerName, "managerPassword": managerPassword},
        async: false,
        success: function f(data) {
            var result = data.managerName;
            var gender = data.managerGender;
            if(result != null){
                window.location.href = "http://localhost:8080/sysindex.html";
            }
            else{
                alert("用户名或密码错误，请重新登陆")
            }
        },
        error: function f(data) {
            alert("lose");
        }
    });
}


function out_login(){
    $(".cd-signin").show();
    $("#top1_register").show();
    $(".touxiang").attr("src","../static/static/img/touxaing.jpg");
    $("#homeOptions").css("display","none");
    $(".yourName").text("");
    $(".wid").text("");
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    $.ajax({
        url: BASE_PATH + "/manager/logout",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f() {
            return true;
        },
        error: function f() {
            alert("lose");
        }
    })

}