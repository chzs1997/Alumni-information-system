var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
jQuery(document).ready(function($) {
    $.ajax({
        url:BASE_PATH + "/administrator/detectState",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
        $("#managerName").val(data.managerName);
        $("#managerName").attr("managerId",data.managerId);
        },
        error: function f(data) {
            alert("lose");
        }
    });
});

<!--管理员密码修改-->
function alterPassword() {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var managerPasswordConfirm = $("#managerPasswordConfirm").val();
    var managerPassword = $("#managerPassword").val();
    var managerId = $("#managerName").attr("managerId");
    if(check_pwd()==false){
        alert("密码格式有误，请输入正确格式");
        event.stopPropagation();
    }
    else if(check_pwd2()==false){
        alert("两次输入密码不一致");
        event.stopPropagation();
    }
    else{
        $.ajax({
            url:BASE_PATH + "/administrator/alterPassword",
            type: "post",
            dateType: "json",
            data: {"managerId": managerId, "managerPassword": managerPassword},
            async: false,
            success: function f(data) {
               if(data == 1){
                   alert("修改成功");
                   window.location.href = "syslogin.html";
               }
            },
            error: function f(data) {
                alert("lose");
            }
        });
    }

}

function check_pwd() {
    var pwd = document.getElementById("managerPassword").value;
    var regPwd =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/;
    if (pwd == "" || pwd.trim() == "") {
        document.getElementById("err_pwd").innerHTML = "请输入密码";
        return false;
    } else if (!regPwd.test(pwd)) {
        document.getElementById("err_pwd").innerHTML = "请输入正确格式";
        return false;
    } else {
        document.getElementById("err_pwd").innerHTML = "";
        return true;
    }
}



function check_pwd2() {
    var pwd = document.getElementById("managerPassword").value;
    var pwd2 = document.getElementById("managerPasswordConfirm").value;
    if (pwd2 == "" || pwd2.trim() == "") {
        document.getElementById("err_pwd2").innerHTML = "请输入密码";
        return false;
    } else if(pwd2!=pwd) {
        document.getElementById("err_pwd2").innerHTML = "输入密码不一致";
        return false;
    } else {
        document.getElementById("err_pwd2").innerHTML = "";
        return true;
    }
}