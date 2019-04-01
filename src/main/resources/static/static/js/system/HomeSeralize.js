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
});