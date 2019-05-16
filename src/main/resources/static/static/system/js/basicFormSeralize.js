var obj = window.document.location;
var BASE_PATH = obj.href.substring(0, obj.href.indexOf(obj.pathname));
$("#newsEdit").on("click",function () {
    var new_title = $("#exampleInputEmail1").val();
    var new_content = $("#exampleTextarea").val();
    var stage= $('#type input[name="stage"]:checked').val();
    var new_type =$("#exampleSelect1 option:selected").val();
    var img = $("#preview").attr("src");
    var result = CheckPost();
    if(result == true){
        $.ajax({
            url:BASE_PATH + "/news/fileUpload",
            type: "post",
            dateType: "json",
            async: false,
            data: {"img": img, "new_title": new_title, "stage": stage,"new_type":new_type,"new_content":new_content},
            success: function f(data) {
                alert("新闻编辑成功");
                window.location.href = "sysbasic-form.html";
            },
            error: function f(data) {
                alert("lose");
            }
        })

    }
    else {
        event.stopPropagation();
    }
});

$("#infoEdit").on("click",function () {
    var type = $('#infoType input[name="serviceType"]:checked').val();
    var souvenirTime = $("#infoTime").val();
    var infoHref = $("#infoHref").val();
    var infoAddress = $("#infoAddress").val();
    var infoContact = $("#infoContact").val();

    var title = $("#infoTitle").val();
    var content = $("#infoIntroduction").val();
    var img = $("#preview2").attr("src");
    var result = CheckService();

    if(infoAddress == ""){
        infoAddress = "暂无";
    }
    if(infoContact == ""){
        infoContact = "暂无";
    }
    if(souvenirTime == ""){
        souvenirTime = "暂无"
    }
    if(infoHref == ""){
        infoHref = "暂无";
    }

    if(result == true){
        $.ajax({
            url:BASE_PATH + "/administrator/fileUpload",
            type: "post",
            dateType: "json",
            async: false,
            data: {"img": img, "type": type, "infoAddress": infoAddress,"infoContact":infoContact,"souvenirTime":souvenirTime,"infoHref":infoHref,"title":title,"content":content},
            success: function f(data) {
                alert("服务编辑成功");
                window.location.href = "sysbasic-form.html";
            },
            error: function f(data) {
                alert("lose");
            }
        })

    }
    else {
        event.stopPropagation();
    }
});


$("#infoType input").on("click",function () {
    if($("#infoType input:radio:checked").val() == "纪念品"){
        $("#souvenirTime").css("display","block");
        $("#companyHref").css("display","none");
        $("#entertainmentInfo").css("display","none");
    }
    else if($("#infoType input:radio:checked").val() == "企业"){
        $("#souvenirTime").css("display","none");
        $("#companyHref").css("display","block");
        $("#entertainmentInfo").css("display","none");
    }
    else{
        $("#souvenirTime").css("display","none");
        $("#companyHref").css("display","none");
        $("#entertainmentInfo").css("display","block");
    }
})

function CheckPost() {
    var title = $("#exampleInputEmail1").val();
    var content = $("#exampleTextarea").val();
    var stage= $('#type input[name="stage"]:checked').val();
    var fileInput = $("#file").get(0).files[0];
    if(title == ""){
        alert("新闻标题未填");
        event.stopPropagation();
        return false;
    }
    else if(content == ""){
        alert("新闻内容未填");
        event.stopPropagation();
        return false;
    }
    else if(stage == null){
        alert("标签未填");
        event.stopPropagation();
        return false;
    }
    else if(stage == null){
        alert("标签未填");
        event.stopPropagation();
        return false;
    }
    else if(!fileInput){
        alert("请上传文件");
        event.stopPropagation();
        return false;
    }
    return true;
}

function CheckService() {
    var type = $('#infoType input[name="serviceType"]:checked').val();
    var title = $("#infoTitle").val();
    var content = $("#infoIntroduction").val();
    var fileInput = $("#file2").get(0).files[0];
    if(type == null){
        alert("服务类型未填");
        event.stopPropagation();
        return false;
    }
    else if(title == ""){
        alert("名称未填");
        event.stopPropagation();
        return false;
    }
    else if(content == ""){
        alert("内容未填");
        event.stopPropagation();
        return false;
    }
    else if(!fileInput){
        alert("请上传文件");
        event.stopPropagation();
        return false;
    }
    return true;
}
