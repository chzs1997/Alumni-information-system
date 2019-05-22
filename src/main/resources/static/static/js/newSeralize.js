jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var newsType;
    var url = window.location.href.split("?");
    if(url.length>1){
        var theRequest = new Object();//theRequest为i获取的参数集合
        var strs = url[1].replace("#","").split('&');
        if(strs == ""){
            window.location.href = "news.html?artType=1"
        }
        newsType=strs[0].split("=")[1];  //类型 1.学院新闻 2.校友新闻
    }
    else{
        window.location.href = "news.html?artType=1"
    }
    //新闻
    $.ajax({
        url: BASE_PATH + "/news/newsSerialize",
        type: "post",
        dateType: "json",
        data: {"newsType":newsType},
        async: false,
        success: function f(data) {
            for(var i = 0; i<10; i++){
                $("#department_news_time_"+(i+1)).text(data.list[i].artTime);
                $("#department_news_image_"+(i+1)).attr("src",data.list[i].artImage);
                $("#department_news_title_"+(i+1)).text(data.list[i].artTitle);
                $("#department_news_title_"+(i+1)).attr("href","news_detail.html?newsType="+newsType+"&artId="+data.list[i].artId);
            }
        },
        error: function f() {
        }
    });

    //公告
    $.ajax({
        url:BASE_PATH + "/news/announcement",
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