$(document).ready(function(){
    var obj = window.document.location;
    var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
    var num=0;
    var title = $(document).attr("title");
    if(title == "Home"){
        num = 15;
    }
    else{
        num = 10;
    }
    var myDate = new Date();//获取系统当前时间

    $.ajax({
        url:BASE_PATH + "/donation/donationShow",
        type: "post",
        dateType: "json",
        data: {"num": num},
        async: false,
        success: function f(data) {
            $("#donationTime").text(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
            var html = '';
            for(var i=0;i<data.length;i++){
                html +=setDiv(data[i]);
            }
            document.getElementById('scorll').innerHTML = html;
        },
        error: function f() {
            alert("lose");
        }
    })
})

function setDiv(item){
    var div = '<div class="message"><div class="message__text"><span>'
              +item.applicantName
              +'***</span><span>'
              +item.collective
              +'***</span><span>'
              +item.donationAmount
              +'***</span><span>'
              +item.donationTime
              +'</span></div></div>'
    return div;
}