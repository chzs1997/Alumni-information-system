
<!--管理员登录-->
function login() {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var managerName = $("#managerName").val();
    var managerPassword = $("#managerPassword").val();
    $.ajax({
        url:BASE_PATH + "/manager/login",
        type: "post",
        dateType: "json",
        data: {"managerName": managerName, "managerPassword": managerPassword},
        async: false,
        success: function f(data) {
            var result = data.managerName;
            var gender = data.managerGender;
            if(result != null){
                window.location.href = "sysindex.html";
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
        url:BASE_PATH + "/manager/logout",
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