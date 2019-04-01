jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

    $.ajax({
        url: BASE_PATH + "/news/newsSeralize",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0; i<10; i++){
                $("#department_news_time_"+(i+1)).text(data.list[i].artTime);
                $("#department_news_title_"+(i+1)).text(data.list[i].artTitle);
                $("#department_news_title_"+(i+1)).attr("href","news_detail.html?artId="+data.list[i].artId);
            }
        },
        error: function f() {
        }
    });

    $.ajax({
        url: BASE_PATH + "/news/announcement",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0; i<4; i++){
                $("#department_announcement_time_"+(i+1)).text(data.list[i].artTime);
                $("#department_announcement_title_"+(i+1)).text(data.list[i].artTitle);
                $("#department_announcement_title_"+(i+1)).attr("href","announcement_detail.html?artId="+data.list[i].artId);;
            }
        },
        error: function f() {
        }
    });
});