var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
$(document).ready(function(){

    //首页校友风采新闻加载
    $.ajax({
        url:BASE_PATH + "/news/homeNews",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0;i<4;i++){
                $("#homeImg_"+(i+1)).attr("src",data[i].artImage);
                $("#homeName_"+(i+1)).text(data[i].characterName);
                $("#homeIntro_"+(i+1)).text(data[i].characterIntroduction);
                $("#homeIntro_"+(i+1)).attr("href","news_detail.html?newsType=2&artId="+data[i].artId);
                $("#news_"+(i+1)).text(data[i].artTitle);
            }
        },
        error: function f() {
            alert("lose");
        }
    });

    //首页学院新闻（分标签）
    $.ajax({
        url:BASE_PATH + "/news/homeNewsByLabel",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0;i<9;i++){
                $("#img_"+(i+1)).attr("src",data[i].artImage);
                $("#label_"+(i+1)).text(data[i].artLabel1);
                $("#title_"+(i+1)).text(data[i].artTitle);
                $("#title_"+(i+1)).parent('.news__link').attr("href","news_detail.html?newsType=1&artId="+data[i].artId);
            }
        },
        error: function f() {
            alert("lose");
        }
    });


});