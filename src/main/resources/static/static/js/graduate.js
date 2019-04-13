$(function () {
    $(".select3 p").click(function(e) {
        $(".select3").toggleClass('open');
        e.stopPropagation();
    });

    $(".content3 .select3 ul li").click(function(e) {
        var _this = $(this);
        $(".select3 > p").text(_this.attr('data-value'));
        _this.addClass("Selected").siblings().removeClass("Selected");
        $(".select3").removeClass("open");
        e.stopPropagation();
    });

    $(document).on('click', function() {
        $(".select3").removeClass("open");
    })

})