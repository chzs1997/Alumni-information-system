$(function() {
    $("#xg2").click(function() {
	$(".ge_photo").removeAttr("disabled");
	});
    $("#xg3").click(function() {
	$(".ge_photo").attr("disabled","disabled");
	});
    $("#btn1").click(function(){
            $("input").removeAttr("disabled","disabled");
            $("select").removeAttr("disabled","disabled");
    });
	$("#btn2").click(function(){
            $("input").attr("disabled","disabled");
            $("select").attr("disabled","disabled");
    });
	
});