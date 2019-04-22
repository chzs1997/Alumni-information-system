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
                    $("#username_bottom").text(managerName);
                    $("#userIdentity_right").text(identity);
                }
            }
        },
        error: function f() {
            alert("当前尚未登陆，请先登陆");
            window.location.href = "syslogin.html";
        }
    });
});
