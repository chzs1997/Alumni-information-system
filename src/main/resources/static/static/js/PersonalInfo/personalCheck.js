jQuery(document).ready(function($) {

    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

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
                }
            }
        },
        error: function f() {
        }
    });
});