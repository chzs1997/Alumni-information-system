$(function() {
	$(".select2 p").click(function(e) {
		$(".select2").toggleClass('open');
		e.stopPropagation();
	});

	$(".content2 .select2 ul li").click(function(e) {
		var _this = $(this);
		$(".select2 > p").text(_this.attr('data-value'));
		_this.addClass("Selected").siblings().removeClass("Selected");
		$(".select2").removeClass("open");
		e.stopPropagation();
	});

	$(document).on('click', function() {
		$(".select2").removeClass("open");
	})

});