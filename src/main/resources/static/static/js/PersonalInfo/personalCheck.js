var userId;  //用户ID
var userState = 0; //当前用户状态
var $form_modal = $('.cd-user-modal'),
    $form_login = $form_modal.find('#cd-login'),
    $form_signup = $form_modal.find('#cd-signup'),
    $form_modal_tab = $('.cd-switcher'),
    $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
    $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
    $main_nav = $('.cd-signin');
jQuery(document).ready(function($) {
    detectLoginState();
    if(userState == 0){
        alert("当前尚未登陆，请登陆后再查看个人资料");
        //弹出窗口
        $main_nav.removeClass('is-visible');
        $form_modal.addClass('is-visible');
        login_selected();
        $('.cd-close-form').show();

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
    }
});


//信息修改
$("#btn2").on("click",function () {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));

    //姓名
    var userName = $("#personName").val();
    //性别
    var userGender = $('#personGender input[name = "sex"]:checked').val();

    //籍贯省
    var province =$("#province").children("option:selected").val();

    //籍贯市
    var city =$("#city option:selected").text();

    //联系方式
    var phone = $("#personPhone").val();

    //校友邮箱
    var userMail = $("#personMail").val();

    //校友学号
    var userStudentId = $("#personStudentId").val();

    //专业
    var major = $("#personMajor option:selected").text();

    //年级
    var grade = $("#personGrade option:selected").text();

    //学历
    var education = $("#personEducation option:selected").text();

    //校友毕业年份
    var userGraduateYear = $("#personGraduateYear").val();

    //校友学号
    var userHeadTeacher = $("#personHeadTeacher").val();

    //工作地点
    var province2 = $("#province2 option:selected").text();

    var city2 = $("#city2 option:selected").text();

    //工作公司
    var userCompany = $("#personCompany").val();

    //工作职位
    var userPosition =  $("#personPosition").val();

    //头像
    var userImage = $("#icon").attr("src");

    $.ajax({
        url: BASE_PATH + "/user/updateMessage",
        type: "post",
        dateType: "json",
        data: {"userName":userName,
               "userGender":userGender,
               "userBirthPlace":province+"省"+city+"市",
               "phone":phone,
               "userMail":userMail,
               "userStudentId":userStudentId,
               "userMajor":major,
               "userGrade":grade,
               "userEducation":education,
               "userGraduateYear":userGraduateYear,
               "userHeadTeacher":userHeadTeacher,
               "userAddress":province2+"省"+city2+"市",
               "userCompany":userCompany,
               "userPosition":userPosition,
               "userImage":userImage,
               "userId":userId
        },
        async: false,
        success: function f(data) {
               if(data==1){
                   alert("修改成功");
                   window.location.href = "PersonalInfo.html";
               }
        },
        error: function f() {
            alert("修改失败");
        }
    });
})

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


//登陆状态检测+信息填充
function detectLoginState() {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
    //检验是否已登陆
    $.ajax({
        url: BASE_PATH + "/user/detectState",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            userState = 1; //当前是已登录状态
            $.ajax({
                url: BASE_PATH + "/user/personalCheck",
                type: "post",
                dateType: "json",
                data: {},
                async: false,
                success: function f(data) {
                    var userName = data.userName;
                    if(userName == ""){
                        alert("您的信息为空，请登陆后查看");
                    }
                    else{
                        userId = data.userId;
                        var personName = userName;
                        var personPhone = data.phone;
                        var personGender = data.gender;
                        var personBirthplace = data.birthPlace;
                        var personMail = data.mail;
                        var personStudentId = data.studentId;
                        var personEducation = data.education;
                        var personGrade = data.grade;
                        var personMajor = data.major;
                        var personCompany = data.company;
                        var personAddress = data.userAddress;
                        var personPosition = data.position;
                        var personImage = data.userImage;
                        var personGraduateYear = data.graduateYear;
                        var personHeadTeacher = data.headTeacher;
                        if(personName != null){
                            $("#personName").val(personName);

                            if(personGender == "男"){
                                $("#personGender input[value = '男']").attr("checked","true");
                            }
                            else{
                                $("#personGender input[value = '女']").attr("checked","true");
                            }

                            var homeProvince = personBirthplace.split("省")[0];
                            var homeCity = personBirthplace.split("省")[1].split("市")[0];
                            $("#province option[value="+homeProvince+"]").attr("selected",true);
                            $("#city option[value = '请选择城市']").text(homeCity);


                            $("#personPhone").val(personPhone);
                            $("#personMail").val(personMail);

                            $("#personStudentId").val(personStudentId);
                            $("#personEducation option[value = "+personEducation +"]").attr("selected",true);


                            $("#personGrade option[value = "+personGrade +"]").attr("selected",true);
                            $("#personMajor option[value = "+personMajor +"]").attr("selected",true);

                            $("#personGraduateYear").val(personGraduateYear);
                            $("#personHeadTeacher").val(personHeadTeacher);

                            var workProvince = personAddress.split("省")[0];
                            var workCity = personAddress.split("省")[1].split("市")[0];

                            $("#province2 option[value="+workProvince+"]").attr("selected",true);
                            $("#city2 option[value = '请选择城市']").text(workCity);

                            $("#personCompany").val(personCompany);
                            $("#personPosition").val(personPosition);

                            $("#icon").attr("src",personImage);

                            var Integrity = 0;
                            $.ajax({
                                url: BASE_PATH +"/manager/getUserIntegrity",
                                type: "post",
                                dateType: "json",
                                data: {"userId": userId},
                                async: false,
                                success: function f(data) {
                                    Integrity = data;
                                    $("#0").circleChart({
                                        size: 100,
                                        value: Integrity,
                                        text: 0,
                                        onDraw: function(el, circle) {
                                            circle.text(Math.round(circle.value) + "%");
                                        }
                                    });
                                },
                                error: function f() {
                                }
                            })
                        }
                    }
                },
                error: function f() {
                }
            });
        },
        error: function f() {
        }
    });
}

