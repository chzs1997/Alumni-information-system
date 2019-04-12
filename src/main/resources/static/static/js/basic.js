var userGrade;  //年级
var userMajor;  //专业
var userMail;  //身份证号码
var userEducation; //学历

$(document).ready(function(){
    /*	var i=$(".processorBox li").index();*/
    $("#nextBtn").click(function(){
        if(!(check_pwd()&&check_pwd2())) return;
        $("#step1_frm").fadeOut(300);
        $("#step2").fadeIn(500);
        $(".processorBox li").removeClass("current")
        $(".processorBox li").eq(1).addClass("current");
    });
    $("#nextBtn2").click(function(){
        $("#step2").fadeOut(300);
        $("#step3").fadeIn(500);
        $(".processorBox li").removeClass("current")
        $(".processorBox li").eq(2).addClass("current");
    });
    $("#btn3").click(function(){
        /*		$("#step").hide();
                $("#step2").show();*/
        /*	$(".processorBox li").eq(3).addclass("current");*/
    });
});



//初步登陆
function btn1(){
 //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var userName = $("#userName").val();
    var passWord = $("#password").val();
    var passWord2 = $("#password2").val();
    var phone = $("#phone").val();
    userMail = $("#userMail").val();

    if(userName == ""){
        alert("用户名尚未填写")
    }else if(passWord == "" ){
        alert("密码尚未填写");
    } else if(phone == ""){
        alert("手机号尚未填写");
    } else if(userMail == ""){
        alert("用户邮箱尚未填写");
    } else if(passWord !== passWord2) {
        alert("两次密码输入不一致，请重新输入");
    }
    else{
        $.ajax({
            url: BASE_PATH + "/user/login",
            type: "post",
            dateType: "json",
            data: {"userName": userName, "password": passWord, "userMail": userMail, "phone": phone},
            async: false,
            success: function f(data) {
                var result = data
                if(result == 1){
                    document.getElementById('step1').style.display='none';
                    document.getElementById("step2").style.display='block';
                }
                else{
                    alert("信息有误，请重新注册")
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
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var Code = $("#code").val();
    // $.ajax({
    //     url: BASE_PATH + "/user/determine",
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
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var Code = $("#code").val();
    $.ajax({
        url: BASE_PATH + "/user/determine",
        type: "post",
        dateType: "json",
        data: {"Code": Code},
        async: false,
        success: function f(data) {
            if(data == 1){
                document.getElementById('step2').style.display='none';
                document.getElementById('step3').style.display='block';
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
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    // var userNickName = $("#nickname").val();  //用户别名
    //性别
    var userGender = $(".frm_control_group").children("input:checked").val();
    //专业
    var major = $(".select > p").text();;
    //年级
    var grade = $(".select1 > p").text();;
    //学历
    var education = $(".select2 > p").text();
    var u_mail = userMail;
    var province =$("#province").children("option:selected").val();
    var city =$("#city").children("option:selected").val();
    var birthplaceProvince =$("#birthplaceProvince").children("option:selected").val();
    var birthplaceCity =$("#birthplaceCity").children("option:selected").val();
    var workPlace = $("#workPlace").val();
    var workPosition = $("#workPosition").val();
    var userAddress = province+"省"+city+"市";
    var userBirthPlace = birthplaceProvince+"省"+birthplaceCity+"市";
    $.ajax({
        url: BASE_PATH + "/user/add_info",
        type: "post",
        dateType: "json",
        data: {"userMail": u_mail
              ,"userGender":userGender
              ,"userGrade":grade
              ,"userBirthPlace":userBirthPlace
              ,"userEducation":education
              ,"userMajor":major
              ,"userAddress":userAddress
              ,"userCompany":workPlace
              ,"userPosition":workPosition},
        async: false,
        success: function f(data) {
            if(data == 1){
                alert("注册成功");
                window.location.href = "http://localhost:8080/HomePage";
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


/*
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

    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var Contact = $("#Contact").val();
    $.ajax({
        url: BASE_PATH + "/user/sendMessage",
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




/*
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
