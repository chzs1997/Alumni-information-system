const obj = window.document.location;
const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
//点击发送短信验证码
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function send(){
    curCount = count;
    //设置button效果，开始计时
    $("#btn").attr("disabled", "true");
    $("#btn").val(curCount + "秒");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次

    var userMail = $("#Mail").val();
    $.ajax({
        url: BASE_PATH + "/user/getCheckCode",
        type: "post",
        dateType: "json",
        data: {"userMail": userMail},
        async: false,
        success: function f() {
            return true;
        },
        error: function f() {
            alert("lose");
        }
    })
}

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btn").removeAttr("disabled");//启用按钮
        $("#btn").val("重新发送");
    }
    else {
        curCount--;
        $("#btn").val(curCount + "秒");
    }
}