$(document).ready(function () {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var url = window.location.href.split("?");
    if(url.length>1){
        var theRequest = new Object();//theRequest为i获取的参数集合
        var strs = url[1].split('&');
    }
    var charcterType =strs[0].split("=")[1];
    var charcterId =strs[1].split("=")[1];

    //确定何种风采
    if(charcterType == "3"){
        $("#characterStyleStar").text("校友之星");
        $("#deedTitle").text("校友事迹");
    }
    else{
        $("#characterStyleStar").text("学生之星");
        $("#deedTitle").text("学生事迹");

    }
    $.ajax({
        url:BASE_PATH + "/CharacterStyle/jump",
        type: "post",
        dateType: "json",
        data: {"characterId": charcterId},
        async: false,
        success: function f(data) {
            var title = data.characterTitle;
            var content = data.characterContent;
            var img = data.characterImg;
            $("#CharacterTitle").text(title);
            $("#CharacterContent").text(content);
            $("#CharacterImg").attr("src",img);
            $.ajax(
                BASE_PATH + "/CharacterStyle/selectSimilar",
                {type: "post",
                dateType: "json",
                data: {"characterId": charcterId},
                timeout: 10000,
                headers:{
        	        'Content-Type':'application/x-www-form-urlencoded'
                },
                success: function f(data) {
                    for(var i = 0;i < 4;i++){
                        $("#RelationTeacher_img"+i).attr("src",data.list[i].characterPicture);
                        $("#RelationTeacher_introduction"+i).text(data.list[i].characterIntroduction);
                    }
                },
                error: function f(data) {
                    alert("lose");
                }
            });
            $.ajax(
                BASE_PATH + "/CharacterStyle/hotPerson",
                {type: "post",
                dateType: "json",
                data: {"charcterType": charcterType},
                timeout: 10000,
                headers:{
        	       'Content-Type':'application/x-www-form-urlencoded'
                },
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

function inter(e) {
    var id = e.id;
    var characterId = $("#"+id).attr("characterId");
    window.location.href = "single.html?characterType="+charType+"&characterId="+characterId;
}