jQuery(document).ready(function($) {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));

    $.ajax({
        url:BASE_PATH + "/souvenir/souvenirSerialize",
        type: "post",
        dateType: "json",
        data: {},
        async: false,
        success: function f(data) {
            for(var i = 0; i<9; i++){
                $("#souvenirImage_"+(i+1)).attr("src",data.list[i].souvenirImage);
                $("#souvenirName_"+(i+1)).text(data.list[i].souvenirName);
                $("#souvenirMeaning_"+(i+1)).text(data.list[i].souvenirMeaning);
                $("#souvenirPraiseCounts_"+(i+1)).text(data.list[i].souvenirPraiseCounts);
                $("#souvenirPraiseCounts_"+(i+1)).prev().attr("id",data.list[i].souvenirId);
            }
        },
        error: function f() {
        }
    });


});

//对纪念品点赞
function praise(e) {
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var souvenirPraise = 0;
    var souvenirId = e.id;
    if(e.style.color == "red"){
        e.style.color = "#747474";
    }
    else{
        e.style.color = "red";
    }
    if($(e).hasClass("fa-thumbs-o-up") && e.style.color=="red"){
        souvenirPraise = 1;
    }
    //点赞
    $.ajax({
        url:BASE_PATH + "/souvenir/praiseAdd",
        type: "post",
        dateType: "json",
        data: {"souvenirId":souvenirId,"souvenirPraise":souvenirPraise},
        async: false,
        success: function f(data) {
            var souvenirPraiseCounts = data.souvenirPraiseCounts;
            $(e).next().text(souvenirPraiseCounts);
        },
        error: function f() {
            alert("lose");
        }
    });
}