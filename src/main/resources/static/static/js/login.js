var obj = window.document.location;
var url = window.location.href;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
var userState = 0;  //当前用户状态   0：未登录  1：已登录
jQuery(document).ready(function($) {
    var     $form_modal = $('.cd-user-modal'),
            $form_login = $form_modal.find('#cd-login'),
            $form_signup = $form_modal.find('#cd-signup'),
            $form_modal_tab = $('.cd-switcher'),
            $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
            $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
            $main_nav = $('.cd-signin');

    //弹出窗口
    $main_nav.on('click', function(event) {

        $main_nav.removeClass('is-visible');
        $form_modal.addClass('is-visible');
        ($(event.target).is('.cd-signup')) ? signup_selected() : login_selected();
        $('.cd-close-form').show();
    });

    //关闭弹出窗口
    $('.cd-user-modal').on('click', function(event) {
        if ($(event.target).is('.cd-close-form')) {
            $form_modal.removeClass('is-visible');
        }
    });
    //使用Esc键关闭弹出窗口
    $(document).keyup(function(event) {
        if (event.which == '27') {
            $form_modal.removeClass('is-visible');
        }
    });

    //切换表单
    $form_modal_tab.on('click', function(event) {
        event.preventDefault();
        ($(event.target).is($tab_login)) ? login_selected() : signup_selected();
    });

    function login_selected() {
        $form_login.addClass('is-selected');
        $form_signup.removeClass('is-selected');
        $tab_login.addClass('selected');
        $tab_signup.removeClass('selected');
        $(".cd-switcher").children("li").removeClass("on");
        $(".cd-switcher").children("li").eq(0).addClass("on");
    }

    function signup_selected() {
        $form_login.removeClass('is-selected');
        $form_signup.addClass('is-selected');
        $tab_login.removeClass('selected');
        $tab_signup.addClass('selected');
        $(".cd-switcher").children("li").removeClass("on");
         $(".cd-switcher").children("li").eq(1).addClass("on");
    }
    //登陆状态检测
    $.ajax({
        url:BASE_PATH + "/user/detectState",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var userName = data.userName;
            if(userName == "尚未登陆"){
                out_login();
            }
            else{
                userState = 1;
                var result = userName;
                var phone = data.phone;
                var gender = data.userGender;
                var userImage = data.userImage;
                if(result != null){
                    $('.cd-user-modal').removeClass('is-visible');
                    $(".cd-signin").hide();
                    $("#top1_register").hide();
                    $(".yourName").text(result);
                    $(".wid").text(phone);
                    if(userImage == null){
                        if(gender == '女'){
                            $(".touxiang").attr("src","static/static/img/touxaing2.jpg");
                        }
                        else{
                            $(".touxiang").attr("src","static/static/img/touxaing.jpg");
                        }
                    }
                    else{
                        $(".touxiang").attr("src",userImage);
                    }
                }
            }
        },
        error: function f() {
        }
    });
});


function navagator_list() {
    $("#homeOptions").fadeToggle(1000);
}

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

    var userMail = $("#signup-username").val();
    $.ajax({
        url:BASE_PATH + "/user/getCheckCode",
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

//用户名+密码登陆
function login_1() {
    var userName = $("#signin-username").val();
    var passWord = $("#signin-password").val();
    $.ajax({
        url:BASE_PATH + "/user/check",
        type: "post",
        dateType: "json",
        data: {"userName": userName, "password": passWord},
        async: false,
        success: function f(data) {
            if(data.result == 1){
                var userName = data.userName;
                var phone = data.phone;
                var gender = data.gender;
                var userImage = data.userImage;
                $('.cd-user-modal').removeClass('is-visible');
                $(".cd-signin").hide();
                $("#top1_register").hide();  //注册隐藏
                $(".yourName").text(userName);
                $(".wid").text(phone);
                if(userImage == null){
                    if(gender == '女'){
                        $(".touxiang").attr("src","static/static/img/touxaing2.jpg");
                    }
                    else{
                        $(".touxiang").attr("src","static/static/img/touxaing.jpg");
                    }
                }
                else{
                    $(".touxiang").attr("src",userImage);
                }
            }
            else{
                alert(data.result);
                event.preventDefault();
            }
        },
        error: function f(data) {
            alert("lose");
        }
    });
}

//邮箱验证码登陆
function login_2() {
    var Code = $("#signup-email").val();
    var userMail = $("#signup-username").val();
    $.ajax({
        url:BASE_PATH + "/user/loginByMail",
        type: "post",
        dateType: "json",
        data: {"userMail":userMail,"Code": Code},
        async: false,
        success: function f(data) {
            if(data.result == 1){
                var userName = data.userName;
                var phone = data.phone;
                var gender = data.gender;
                var userImage = data.userImage;
                    $('.cd-user-modal').removeClass('is-visible');
                    $(".cd-signin").hide();
                    $("#top1_register").hide();
                    $(".yourName").text(userName);
                    $(".wid").text(phone);
                    if(userImage == null){
                        if(gender == '女'){
                            $(".touxiang").attr("src","static/static/img/touxaing2.jpg");
                        }
                        else{
                            $(".touxiang").attr("src","static/static/img/touxaing.jpg");
                        }
                    }
                    else{
                        $(".touxiang").attr("src",userImage);
                    }
            }
            else{
                event.preventDefault();
                alert(data.result);
            }
        },
        error: function f(data) {
            alert("lose");
        }
    });
}

//退出登陆
function out_login(){
    $(".cd-signin").show();
    $("#top1_register").show();
    $(".touxiang").attr("src","static/static/img/touxaing.jpg");
    $("#homeOptions").css("display","none");
    $(".yourName").text("");
    $(".wid").text("");

    var title = $(document).attr("title");

    $.ajax({
        url:BASE_PATH + "/user/logout",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f() {
            if(title == "个人信息"||title=="我的捐赠"||title=="我的行程"||title=="推荐校友"){
                window.location.href = "index.html"
            }
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

//控制是否能进入我的资料界面
$(".hl_nav > .nav_list li").eq(6).children("a:eq(1)").on("click",function () {
    if(userState == 1){
        window.location.href = "PersonalInfo.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后查看个人资料");
    }
})

//控制是否能进入我的行程界面
$(".hl_nav > .nav_list li").eq(6).children("a:eq(2)").on("click",function () {
    if(userState == 1){
        window.location.href = "PersonalStroke.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后查看个人行程");
    }
})

//控制是否能进入我的捐赠界面
$(".hl_nav > .nav_list li").eq(6).children("a:eq(3)").on("click",function () {
    if(userState == 1){
        window.location.href = "PersonalDonation.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后查看个人捐赠");
    }
})

//控制是否能进入我的捐赠界面
$(".hl_nav > .nav_list li").eq(6).children("a:eq(4)").on("click",function () {
    if(userState == 1){
        window.location.href = "PersonalRecommend.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后查看相关校友推荐");
    }
})

//非登陆状态下不能立项
$("#applicateProject").on("click",function () {
    if(userState == 1){
        window.location.href = "donationProject.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后申请立项");
    }
});

//非登陆状态下不能点赞
$("#likeIcon").on("click",function () {
    if(userState == 1){
        praise(this);
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后点赞");
    }
});
