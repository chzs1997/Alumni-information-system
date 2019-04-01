
//初始化内容
$(document).ready(function () {
    //获取basePath，测试的话就能用就完了
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    for(var i = 1;i<=12;i++){
        $("#HotTeacher"+i).attr("characterId",i);
    }
    $.ajax({
        url: BASE_PATH + "/CharacterStyle/withdraw",
        type: "post",
        dateType: "json",
        data: {"styleType": 1},
        async: false,
        success: function f(data) {
              for(var i = 0;i < 4;i++){
                  // var time = new Date
                  $("#teacher_"+i+"_img").attr("src", data.list[i].characterPicture);
                  $("#teacher_"+i+"_name").text(data.list[i].characterName);
                  $("#teacher_"+i+"_name").attr("characterId",data.list[i].characterId);
                  $("#teacher_"+i+"_introduction").text( data.list[i].characterIntroduction);
              }
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


function inter1() {
    $("#teacher_0_name").attr("href","single.html?Id="+$("#teacher_0_name").attr("characterId"));
}

function inter2() {
    $("#teacher_1_name").attr("href","single.html?Id="+$("#teacher_1_name").attr("characterId"));
}

function inter3() {
    $("#teacher_2_name").attr("href","single.html?Id="+$("#teacher_2_name").attr("characterId"));
}

function inter4() {
    $("#teacher_3_name").attr("href","single.html?Id="+$("#teacher_3_name").attr("characterId"));
}


function findTeacher1(){
    $("#HotTeacher1").attr("href","single.html?Id="+$("#HotTeacher1").attr("characterId"));
}

function findTeacher2(){
    $("#HotTeacher2").attr("href","single.html?Id="+$("#HotTeacher2").attr("characterId"));
}

function findTeacher3(){
    $("#HotTeacher3").attr("href","single.html?Id="+$("#HotTeacher3").attr("characterId"));
}

function findTeacher4(){
    $("#HotTeacher4").attr("href","single.html?Id="+$("#HotTeacher4").attr("characterId"));
}

function findTeacher5(){
    $("#HotTeacher5").attr("href","single.html?Id="+$("#HotTeacher5").attr("characterId"));
}

function findTeacher6(){
    $("#HotTeacher6").attr("href","single.html?Id="+$("#HotTeacher6").attr("characterId"));
}

function findTeacher7(){
    $("#HotTeacher7").attr("href","single.html?Id="+$("#HotTeacher7").attr("characterId"));
}

function findTeacher8(){
    $("#HotTeacher8").attr("href","single.html?Id="+$("#HotTeacher8").attr("characterId"));
}

function findTeacher9(){
    $("#HotTeacher9").attr("href","single.html?Id="+$("#HotTeacher9").attr("characterId"));
}

function findTeacher10(){
    $("#HotTeacher10").attr("href","single.html?Id="+$("#HotTeacher10").attr("characterId"));
}

function findTeacher11(){
    $("#HotTeacher11").attr("href","single.html?Id="+$("#HotTeacher11").attr("characterId"));
}

function findTeacher12() {
    $("#HotTeacher12").attr("href", "single.html?Id=" + $("#HotTeacher12").attr("characterId"));
}


