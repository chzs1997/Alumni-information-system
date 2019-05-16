$(function() {
    $("#xg2").click(function() {
	$(".ge_photo").removeAttr("disabled");
	});
    $("#xg3").click(function() {
	$(".ge_photo").attr("disabled","disabled");
	});
    $("#btn1").click(function(){
            $(".ka3 input").removeAttr("disabled","disabled");
            $(".ka3 select").removeAttr("disabled","disabled");
            $("#personPhone").removeAttr("disabled","disabled");
        $("#personMail").removeAttr("disabled","disabled");
    });


	$("#btn2").click(function(){
            $("input").attr("disabled","disabled");
            $("select").attr("disabled","disabled");
    });
	
});