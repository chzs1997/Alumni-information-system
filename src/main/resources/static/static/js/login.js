const obj = window.document.location;
const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
var userState = 0;
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
        if ($(event.target).is($form_modal) || $(event.target).is('.cd-close-form')) {
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


//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
    return this.each(function() {
        // If this function exists...
        if (this.setSelectionRange) {
            // ... then use it (Doesn't work in IE)
            // Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
            var len = $(this).val().length * 2;
            this.setSelectionRange(len, len);
        } else {
            // ... otherwise replace the contents with itself
            // (Doesn't work in Google Chrome)
            $(this).val($(this).val());
        }
    });
};

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
            var result = data.userName;
            var phone = data.phone;
            var gender = data.gender;
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
            else{
                alert("用户名或密码错误，请重新登陆")
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

function out_login(){
    $(".cd-signin").show();
    $("#top1_register").show();
    $(".touxiang").attr("src","static/static/img/touxaing.jpg");
    $("#homeOptions").css("display","none");
    $(".yourName").text("");
    $(".wid").text("");
    $.ajax({
        url:BASE_PATH + "/user/logout",
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
        alert("你当前尚未登陆，请登陆后查看个人资料");
    }
})

//控制是否能进入我的捐赠界面
$(".hl_nav > .nav_list li").eq(6).children("a:eq(3)").on("click",function () {
    if(userState == 1){
        window.location.href = "PersonalDonation.html"
    }
    else{
        event.preventDefault();
        alert("你当前尚未登陆，请登陆后查看个人资料");
    }
})