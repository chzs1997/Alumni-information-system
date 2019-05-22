jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    $.ajax({
        url: BASE_PATH + "/donation/donationSerialize",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0; i<8; i++){
                $("#donation_time_"+(i+1)).text(data.list[i].artTime);
                $("#donation_img_"+(i+1)).attr("src",data.list[i].artImage);
                $("#donation_title_"+(i+1)).text(data.list[i].artTitle);
                $("#donation_detail_"+(i+1)).attr("href","news_detail.html?newsType=2&artId="+data.list[i].artId);
            }
        },
        error: function f() {
        }
    });

});