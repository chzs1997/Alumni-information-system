var charType;
//初始化内容
$(document).ready(function () {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

    var url = window.location.href.split("?");
    if(url.length>1){
        var theRequest = new Object();//theRequest为i获取的参数集合
        var strs = url[1].split('&');
    }
    var charcterType =strs[0].split("=")[1];  //类型 2：学生 3.校友
    charType = charcterType;
    //热门人物风采
    for(var i = 1;i<=12;i++){
        $("#HotPerson"+i).attr("characterId",i);
    }
    //确定何种风采
    if(charcterType == "3"){
        $("#characterStyleTitle").text("校友风采");
        $("#characterStyleStar").text("校友之星");
        $("#deedTitle").text("校友事迹");
    }
    else{
        $("#characterStyleTitle").text("学生风采");
        $("#characterStyleStar").text("学生之星");
        $("#deedTitle").text("学生事迹");

    }
    $.ajax({
        url:BASE_PATH + "/CharacterStyle/withdraw",
        type: "post",
        dateType: "json",
        data: {"charcterType": charcterType},
        async: false,
        success: function f(data) {
              for(var i = 0;i < 4;i++){
                  // var time = new Date
                  $("#character_"+i+"_img").attr("src", data.list[i].characterPicture);
                  $("#character_"+i+"_name").text(data.list[i].characterName);
                  $("#character_"+i+"_name").attr("characterId",data.list[i].characterId);
                  $("#character_"+i+"_introduction").text( data.list[i].characterIntroduction);
              }
            $.ajax({
                url:BASE_PATH + "/CharacterStyle/hotPerson",
                type: "post",
                dateType: "json",
                data: {"charcterType": charcterType},
                async: false,
                success: function f(data) {
                    for(var i = 0;i < 15;i++){
                        // var time = new Date
                        $("#HotPerson"+i).attr("characterId", data.list[i].characterId);
                        $("#HotPerson"+i).text(data.list[i].characterName);
                    }

                },
                error: function f(data) {
                    alert("lose");
                }
            });
        },
        error: function f(data) {
            alert("lose");
        }
    });
});

/**
 * 时间戳格式化函数
 * @param  {string} format    格式
 * @param  {int}    timestamp 要格式化的时间 默认为当前时间
 * @return {string}           格式化的时间字符串
 */
function formatDate(now) {
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    var date=now.getDate();
    var hour=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}


function inter(e) {
    var id = e.id;
    var characterId = $("#"+id).attr("characterId");
    window.location.href = "single.html?characterType="+charType+"&characterId="+characterId;
}



