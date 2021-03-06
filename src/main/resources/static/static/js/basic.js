var userGrade;  //年级
var userMajor;  //专业
var userMail;  //身份证号码
var userEducation; //学历
var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));  //初始URL
$(document).ready(function(){
    $("#btn3").click(function(){
        /*		$("#step").hide();
                $("#step2").show();*/
        /*	$(".processorBox li").eq(3).addclass("current");*/
    });
});



//基本信息注册
function btn1(){

    var userName = $("#userName").val();
    var passWord = $("#password").val();
    var passWord2 = $("#password2").val();
    var phone = $("#phone").val();
    var userWX = $("#userWX").val();
    userMail = $("#userMail").val();

    if(userName == ""){
        alert("用户名尚未填写")
    }else if(userMail == ""){
        alert("用户邮箱尚未填写");
    }else if(userWX == ""){
        alert("微信号未输入");
    }else if(phone == ""){
        alert("手机号尚未填写");
    } else if(passWord == "" ){
        alert("密码尚未填写");
    } else if(passWord !== passWord2) {
        alert("两次密码输入不一致，请重新输入");
    }
    else if(check_pwd()== false){
        alert("密码输入格式有误");
    }
    else{
        $.ajax({
            url:BASE_PATH + "/user/login",
            type: "post",
            dateType: "json",
            async: false,
            data: {"userName": userName, "password": passWord, "userMail": userMail, "phone": phone,"userWX":userWX},
            success: function f(data) {
                var result = data.result;
                if(result == 1){
                    if(!(check_pwd()&&check_pwd2())) return;
                    $("#step1_frm").fadeOut(300);
                    $("#step2").fadeIn(500);
                    $(".processorBox li").removeClass("current")
                    $(".processorBox li").eq(1).addClass("current");
                    $("#Mail").val(data.userMail);
                }
                else{
                    event.preventDefault();
                    alert(result);
                }
            },
            error: function f(data) {
                alert("lose");
            }
        })
    }

}

//第二步：手机验证码登陆
function btnContact(event){

    var Code = $("#code").val();
    // $.ajax({
    //     BASE_PATH + "/user/determine",
    //     type: "post",
    //     dateType: "json",
    //     data: {"Code": Code},
    //     async: false,
    //     success: function f(data) {
    //         if(data == 1){
    //             document.getElementById('step2').style.display='none';
    //             document.getElementById('step3').style.display='block';
    //         }
    //         else{
    //             event.preventDefault();
    //             alert("验证码错误")
    //         }
    //     },
    //     error: function f() {
    //         alert("lose");
    //     }
    // })
                document.getElementById('step2').style.display='none';
                document.getElementById('step3').style.display='block';
}

//第二步 邮箱验证码登陆
function btn2(){
    var Code = $("#code").val();
    $.ajax({
        url:BASE_PATH + "/user/determine",
        type: "post",
        dateType: "json",
        data: {"Code": Code},
        async: false,
        success: function f(data) {
            if(data == 1){
                $("#step2").fadeOut(300);
                $("#step3").fadeIn(500);
                $(".processorBox li").removeClass("current")
                $(".processorBox li").eq(2).addClass("current");
            }
            else{
                event.preventDefault();
                alert("验证码错误")
            }
        },
        error: function f() {
            alert("lose");
        }
    })
}


function btn3(){
    document.getElementById('div2').style.display='none';  
}


//第三步：添加后续信息
function btn_add(){
    //性别
    var userGender = $(".frm_control_group").children("input:checked").val();
    //专业
    var major = $(".select4 > p").text();;
    //年级
    var grade = $(".select2 > p").text();;
    //学历
    var education = $(".select1 > p").text();

    //毕业年份
    var graduateYear = $(".select3 > p").text();

    var u_mail = userMail;
    var province =$("#province").children("option:selected").val();
    var city =$("#city").children("option:selected").val();
    var birthplaceProvince =$("#birthplaceProvince").children("option:selected").val();
    var birthplaceCity =$("#birthplaceCity").children("option:selected").val();
    var workPlace = $("#workPlace").val();
    var workPosition = $("#workPosition").val();
    var headTeacher = $("#headTeacher").val();
    var userAddress = province+"省"+city+"市";
    var userBirthPlace = birthplaceProvince+"省"+birthplaceCity+"市";

    var prompt = "";
    if(userGender != "男" && userGender != "女"){
        prompt = "您的性别未填";
        alert(prompt);
    }
    else if(birthplaceProvince == "请选择省份"){
        prompt = "您的籍贯未填";
        alert(prompt);
    }
    else if(education == "选择选项"){
        prompt = "您的学历未填";
        alert(prompt);
    }
    else if(grade == "选择选项"){
        prompt = "您的年级未填";
        alert(prompt);
    }
    else if(graduateYear == "选择选项"){
        prompt = "您的毕业年份未填";
        alert(prompt);
    }
    else if(major == "选择选项"){
        prompt = "您的专业未填";
        alert(prompt);
    }
    else if(headTeacher == ""){
        prompt = "您的班主任未填";
        alert(prompt);
    }
    else if(province == "请选择省份"){
        prompt = "您的工作(学习)地点未填";
        alert(prompt);
    }
    else if(workPlace == ""){
        prompt = "您的工作(学习)单位未填";
        alert(prompt);
    }
    else if(workPosition==""){
        prompt = "您的工作(学习)职位未填";
        alert(prompt);
    }
    else {
        $.ajax({
            url: BASE_PATH + "/user/add_info",
            type: "post",
            dateType: "json",
            data: {
                "userMail": u_mail
                , "userGender": userGender
                , "userGrade": grade
                , "userBirthPlace": userBirthPlace
                , "userEducation": education
                , "userMajor": major
                , "userGraduateYear": graduateYear
                , "userHeadTeacher": headTeacher
                , "userAddress": userAddress
                , "userCompany": workPlace
                , "userPosition": workPosition
            },
            async: false,
            success: function f(data) {
                if (data == 1) {
                    alert("注册成功");
                    window.location.href = "index.html";
                } else {
                    event.preventDefault();
                    alert("信息有误");
                }
            },
            error: function f() {
                alert("lose");
            }
        })
    }
}


/**
*
* 发送验证码
* */

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

    var Contact = $("#Contact").val();
    $.ajax({
        url:BASE_PATH + "/user/sendMessage",
        type: "post",
        dateType: "json",
        data: {"phone": Contact},
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




/**
* 验证工作
*
* */

//邮箱格式限定
function checkMail() {
    var myemail = $("#userMail").val();
    var myReg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;

    if (myReg.test(myemail)) {
        $("#phone").removeAttr("disabled");
        return true;
    } else {
        var tip = "邮箱格式不对!";
        alert(tip);
    $("#phone").attr("disabled","disabled");
    return false;
}
}

//身份证验证
function checkIdentity(){
        var code = $("#userIdNumber").val();
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;

        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
            tip = "身份证号格式错误";
            pass = false;
        }

        else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        if(!pass) {
            alert(tip);
            $("#phone").attr("disabled","disabled");
        }
        else{
            $("#phone").removeAttr("disabled");
        }
        return pass;
    }

//手机号码格式
function phoneCheck(){
    var obj = $("#phone").val();
    var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if(!reg.test(obj)) {
        alert("手机号输入格式错误");
        $("#password").attr("disabled","disabled");
    }
    else{
        $("#password").removeAttr("disabled");
    }
}

//密码检验
function check_pwd() {
    var pwd = document.getElementById("password").value;
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
    var pwd = document.getElementById("password").value;
    var pwd2 = document.getElementById("password2").value;
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

function keyLogin(){
    if(event.keyCode==13){
      $("#cd-login .full-width2").click();
    }
}
