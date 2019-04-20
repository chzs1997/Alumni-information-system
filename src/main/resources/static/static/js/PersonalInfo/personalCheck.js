var userId;
jQuery(document).ready(function($) {
    var BASE_PATH = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname));
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

                    var workProvince = personAddress.split("省")[0];
                    var workCity = personAddress.split("省")[1].split("市")[0];

                    $("#province2 option[value="+workProvince+"]").attr("selected",true);
                    $("#city2 option[value = '请选择城市']").text(workCity);

                    $("#personCompany").val(personCompany);
                    $("#personPosition").val(personPosition);

                    $("#icon").attr("src",personImage);
                }
            }
        },
        error: function f() {
        }
    });
});

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
                   window.location.href = "http://172.17.108.131:8080/PersonalInfo";
               }
        },
        error: function f() {
            alert("修改失败");
        }
    });
})