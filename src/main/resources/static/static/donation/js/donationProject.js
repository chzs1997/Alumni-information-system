var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
jQuery(document).ready(function($) {
    $.ajax({
        url:BASE_PATH + "/user/getInfo",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            var applicantName = data.userName;
            var donationContact = data.phone;
            var donationMail = data.userMail;
            $("#applicantName").val(applicantName);
            $("#donationContact").val(donationContact);
            $("#donationMail").val(donationMail);
        },
        error: function f() {
            alert("lose");
        }
    });
});

$("#butt").on("click",function () {
    var applicantName = $("#applicantName").val();
    var donationAmount = $("#donationAmount").val();
    var donationMajor = $("#donationMajor").val();
    var applicantContact = $("#donationContact").val();
    var applicantMail = $("#donationMail").val();
    var applicantPurpose = $('#applicantPurpose input[name = "purpose"]:checked').val();


    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

    if(applicantName == ""){
        alert("申请人姓名尚未填写");
    }
    else if(donationAmount == ""){
        alert("捐赠金额尚未填写")
    }
    else if(donationMajor == ""){
        alert("捐赠专业尚未填写");
    }
    else if(applicantContact == ""){
        alert("手机/电话尚未填写");
    }
    else if(applicantMail == ""){
        alert("电子邮箱尚未填写");
    }
    else if(applicantPurpose == null){
        alert("希望资金使用的方向尚未填写");
    }
    else {
        $.ajax({
            url: BASE_PATH + "/user/projectApplicant",
            type: "post",
            dateType: "json",
            data: {
                "applicantName": applicantName,
                "donationAmount": donationAmount,
                "donationMajor": donationMajor,
                "applicantContact": applicantContact,
                "applicantMail": applicantMail,
                "applicantPurpose": applicantPurpose
            },
            async: false,
            success: function f() {
                alert("立项成功");
                window.location.href = "Donation.html";
            },
            error: function f() {
                alert("lose");
            }
        });
    }
})