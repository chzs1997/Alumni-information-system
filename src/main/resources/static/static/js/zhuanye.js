$(function() {

	//专业
	$(".select4 p").click(function(e) {
		$(".select4").toggleClass('open');
		e.stopPropagation();
	});

	$(".content4 .select4 ul li").click(function(e) {
		var _this = $(this);
		$(".select4 > p").text(_this.attr('data-value'));
		_this.addClass("Selected").siblings().removeClass("Selected");
		$(".select4").removeClass("open");
		e.stopPropagation();
	});

	$(document).on('click', function() {
		$(".select4").removeClass("open");
	})

});