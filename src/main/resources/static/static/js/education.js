$(function () {
    $(".select1 p").click(function(e) {
        $(".select1").toggleClass('open');
        e.stopPropagation();
    });

    $(".content1 .select1 ul li").click(function(e) {
        var _this = $(this);
        $(".select1 > p").text(_this.attr('data-value'));
        _this.addClass("Selected").siblings().removeClass("Selected");
        $(".select1").removeClass("open");
        e.stopPropagation();
    });

    $(document).on('click', function() {
        $(".select1").removeClass("open");
    })

})