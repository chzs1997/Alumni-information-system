jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var url = window.location.href.split("?");
    if(url.length>1){
        var theRequest = new Object();//theRequest为i获取的参数集合
        var strs = url[1].split('&');
    }
    var artType =strs[0].split("=")[1];
    var artId =strs[1].split("=")[1];

    //初始化评论
    $.ajax({
        url: BASE_PATH + "/news/commentsSeralize",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
             for(var i = 0;i<4;i++){
                 $("#comment_content_"+(i+1)).parent().attr("commentId",data[i].commentId);
                 $("#comment_name_"+(i+1)).text(data[i].commentName);
                 $("#comment_content_"+(i+1)).text(data[i].commentContent);
             }
        },
        error: function f() {
        }
    });


    //主体内容
    $.ajax({
        url: BASE_PATH + "/news/newsDetailSeralize",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
            var newsViewCounts = data.artViewCounts;
            var title = data.artTitle;
            var time = data.artTime;
            var img = data.artImage;
            var label1 = data.artLabel1;
            var label2 = data.artLabel2;
            var label3 = data.artLabel3;
            var label4 = data.artLabel4;

            //标题
            $("#news_detail_title").text(title);

            //时间
            $("#news_detail_time").text(time);


            //标签
            $("#news_detail_label1").text(label1);

            //标签
            $("#news_detail_label2").text(label2);

            //标签
            $("#news_detail_label3").text(label3);

            //标签
            $("#news_detail_label4").text(label4);

            //浏览次数
            $("#newsViewCounts").text(newsViewCounts+"次");

            //如果是学院新闻
            if(artType == 1){
                //内容
                $("#test1").html(data.artContent);

                for(var i =0;i<$("#test1 img").length;i++){
                    $("#test1 img").eq(i).attr("src","http://glxy.xtu.edu.cn"+$("#test1 img").eq(i).attr("src"))

                }
            }
            //如果是校友新闻
            else if(artType == 2){
               $("#test1 img").attr("src",img);

               var content = data.artContent.split(/\s+/);

               for(var i =0;i<content.length;i++){
                   var html = "<p>"+content[i]+"</p>"
                   $("#content").append(html);
               }
            }
        },
        error: function f() {
         }
    });

    //附加内容
    $.ajax({
        url: BASE_PATH + "/news/newsExtra",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
            for(var i=0;i<4;i++){
                var title = data[i].artTitle;
                var time = data[i].artTime;
                var img = data[i].artImage;
                var artId = data[i].artId;
                //标题
                $("#newest_news_title_"+(i+1)).text(title);

                //时间
                $("#newest_news_time_"+(i+1)).text(time);

                //图片
                $("#newest_news_img_"+(i+1)).attr("src",img);

                //链接
                $("#newest_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

            for(var i=4;i<8;i++){
                var title = data[i].artTitle;
                var img = data[i].artImage;
                var artId = data[i].artId;
                //标题
                $("#oldest_news_title_"+(i+1)).text(title);

                //图片
                $("#oldest_news_img_"+(i+1)).attr("src",img);

                //链接
                $("#oldest_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

            for(var i=8;i<12;i++){
                var title = data[i].artTitle;
                var img = data[i].artImage;
                var artId = data[i].artId;
                //标题
                $("#focus_news_title_"+(i+1)).text(title);

                //图片
                $("#focus_news_img_"+(i+1)).attr("src",img);

                //链接
                $("#focus_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }
        },
        error: function f() {
        }
    });

    //相关新闻
    $.ajax({
        url: BASE_PATH + "/news/newsRelation",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
            for(var i=0;i<4;i++){
                var title = data[i].artTitle;
                var time = data[i].artTime;
                var img = data[i].artImage;
                var artId = data[i].artId;
                //标题
                $("#relation_news_title_"+(i+1)).text(title);

                //时间
                $("#relation_news_time_"+(i+1)).text(time);

                //图片
                $("#relation_news_img_"+(i+1)).attr("src",img);

                //链接
                $("#relation_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

        },
        error: function f() {
        }
    });

    //点赞评论数量
    $.ajax({
        url: BASE_PATH + "/news/coutsSeralize",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
            var artShareCounts = data.artShareCounts;
            var artViesCounts = data.artViesCounts;
            var artPraiseCounts = data.artPraiseCounts;
            $("#praiseCounts").text(artPraiseCounts);
            $("#shareCounts").text(artShareCounts);
            $("#commentsCounts").text(artViesCounts);
        },
        error: function f() {
        }
    })
});

function toggleColor(e) {
    const obj = window.document.location;
    const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var commentTread = 0;
    var commentPraise = 0;
    var commentId = e.parentNode.parentNode.getAttribute("commentId");
    if(e.style.color == "red"){
        e.style.color = "#747474";
    }
    else{
        e.style.color = "red";
    }
    if($(e).hasClass("fa-thumbs-o-up") && e.style.color=="red"){
            commentPraise = 1;

        }
    if($(e).hasClass("fa-thumbs-o-down"&& e.style.color=="red")){
            commentTread = 1;
        }
    //点踩
    $.ajax({
        url: BASE_PATH + "/news/addCounts",
        type: "post",
        dateType: "json",
        data: {"commentId":commentId,"commentPraise":commentPraise,"commentTread":commentTread},
        async: false,
        success: function f(data) {
            if(data == 1){
            }
        },
        error: function f() {
            alert("lose");
        }
    });
}

function praise(e) {
    const obj = window.document.location;
    const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var newsPraise = 0;
    const artId = parseInt(window.location.href.split("?")[1].split("=")[1]);
    if(e.style.color == "red"){
        e.style.color = "#747474";
    }
    else{
        e.style.color = "red";
    }
    if($(e).hasClass("fa-thumbs-o-up") && e.style.color=="red"){
        newsPraise = 1;
    }
    //点踩
    $.ajax({
        url: BASE_PATH + "/news/praiseAdd",
        type: "post",
        dateType: "json",
        data: {"artId":artId,"newsPraise":newsPraise},
        async: false,
        success: function f(data) {
            var artPraiseCounts = data.artPraiseCounts;
            $("#praiseCounts").text(artPraiseCounts);
        },
        error: function f() {
            alert("lose");
        }
    });
}

$("#commentButton").on("click",function () {
    var commentName = $("#commentName").val();
    var commentMail = $("#commentMail").val();
    var commentContent = $("#commentContent").val();
    const artId = parseInt(window.location.href.split("?")[1].split("=")[1]);
    const obj = window.document.location;
    const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    //添加评论
    $.ajax({
        url: BASE_PATH + "/news/addComments",
        type: "post",
        dateType: "json",
        data: {"commentName":commentName,"commentMail":commentMail,"commentContent":commentContent,"artId":artId},
        async: false,
        success: function f(data) {
            if(data == 1){
                alert("评论成功");
                window.location.href = "news_detail.html?artId="+artId;
            }
        },
        error: function f() {
        }
    });
});