jQuery(document).ready(function () {
    const artId = parseInt(window.location.href.split("?")[1].split("=")[1]);
    const obj = window.document.location;
    const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    const div = document.getElementById("test1");

    //主题内容
    $.ajax({
        url: BASE_PATH + "/news/newsDetailSeralize",
        type: "post",
        dateType: "json",
        data: {"artId":artId},
        async: false,
        success: function f(data) {
            var title = data.artTitle;
            var time = data.artTime;
            var content = data.artContent;

            //标题
            $("#news_detail_title").text(title);

            //时间
            $("#news_detail_time").text(time);

            //内容
            $("#test1").append(content);
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
                var artId = data[i].artId;
                //标题
                $("#newest_news_title_"+(i+1)).text(title);

                //时间
                $("#newest_news_time_"+(i+1)).text(time);


                //链接
                $("#newest_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

            for(var i=4;i<8;i++){
                var title = data[i].artTitle;
                var artId = data[i].artId;
                //标题
                $("#oldest_news_title_"+(i+1)).text(title);


                //链接
                $("#oldest_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

            for(var i=8;i<12;i++){
                var title = data[i].artTitle;
                var artId = data[i].artId;
                //标题
                $("#focus_news_title_"+(i+1)).text(title);

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
                var artId = data[i].artId;
                //标题
                $("#relation_news_title_"+(i+1)).text(title);

                //时间
                $("#relation_news_time_"+(i+1)).text(time);


                //链接
                $("#relation_news_title_"+(i+1)).attr("href","news_detail.html?artId="+artId);
            }

        },
        error: function f() {
        }
    });
})

// function toggleColor(e) {
//     if(e.style.color == "red"){
//         e.style.color = "#747474";
//     }
//     else{
//         e.style.color = "red";
//     }
// }

// $("#commentButton").on("click",function () {
//     var commentName = $("#commentName").val();
//     var commentMail = $("#commentMail").val();
//     var commentContent = $("#commentContent").val();
//     const artId = parseInt(window.location.href.split("?")[1].split("=")[1]);
//     const obj = window.document.location;
//     const BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
//     //添加评论
//     $.ajax({
//         url: BASE_PATH + "/news/addComments",
//         type: "post",
//         dateType: "json",
//         data: {"commentName":commentName,"commentMail":commentMail,"commentContent":commentContent,"artId":artId},
//         async: false,
//         success: function f(data) {
//             if(data == 1){
//                 alert("注册成功");
//                 window.location.href = "news_detail.html?artId="+artId;
//             }
//         },
//         error: function f() {
//         }
//     });
// })