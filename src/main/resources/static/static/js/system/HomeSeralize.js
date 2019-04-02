jQuery(document).ready(function ($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

    $.ajax({
        url: BASE_PATH + "/donation/detectState",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
           var donationAmountLastMonth = data.donationAmountLastMonth;
           var userAmountLastMonth = data.userAmountLastMonth;
           var donationAmountTotal = data.donationAmountTotal;
           var userAmountTotal = data.userAmountTotal;
           $("#donationAmountLastMonth").text("RMB "+donationAmountLastMonth);
           $("#userAmountLastMonth").text(userAmountLastMonth);
           $("#donationAmountTotal").text("RMB "+donationAmountTotal);
           $("#userAmountTotal").text(userAmountTotal)
        },
        error: function f() {
        }
    });

    $.ajax({
        url: BASE_PATH + "/manager/findUserLoginLog",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
           for(var i = 0;i<9;i++){
               $("#login_name_"+(i+1)).text(data.list[i].userName);
               $("#login_time_"+(i+1)).text(data.list[i].loginTime);
           }
        },
        error: function f() {
        }
    });

    $.ajax({
        url: BASE_PATH + "/manager/findManagerLoginLog",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0;i<3;i++){
                if(data.list[i].managerGender == "女"){
                    $("#manager_gender_"+(i+1)).attr("src","static/static/img/touxaing2.jpg");
                }
                $("#manager_name_"+(i+1)).text(data.list[i].managerName);
                $("#manager_identity_"+(i+1)).text(data.list[i].managerIdentity);
            }
        },
        error: function f() {
        }
    });
});