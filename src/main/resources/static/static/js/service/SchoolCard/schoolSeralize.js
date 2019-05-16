jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    if($(document).attr("title") == "校友卡"){
        var artType = 4;
    }
    else if($(document).attr("title") == "校友返校活动"){
        var artType = 5;
    }

    //加载列表新闻
    $.ajax({
        url: BASE_PATH + "/news/serviceListSeralize",
        type: "post",
        dateType: "json",
        data: {"artType":artType},
        async: false,
        success: function f(data) {
            for(var i = 0; i<3; i++){
                $("#service_title_"+(i+1)).text(data[i].artTitle);
                $("#service_time_"+(i+1)).text(data[i].artTime);
                $("#service_img_"+(i+1)).attr("src",data[i].artImage);
                $("#service_summary_"+(i+1)).text(data[i].artSummary);
            }
        },
        error: function f() {
            alert("lose");
        }
    });

    // $.ajax({
    //     BASE_PATH + "/news/announcement",
    //     type: "post",
    //     dateType: "json",
    //     data: {},
    //     async: false,
    //     success: function f(data) {
    //         for(var i = 0; i<4; i++){
    //             $("#department_announcement_time_"+(i+1)).text(data.list[i].artTime);
    //             $("#department_announcement_title_"+(i+1)).text(data.list[i].artTitle);
    //         }
    //     },
    //     error: function f() {
    //     }
    // });
});