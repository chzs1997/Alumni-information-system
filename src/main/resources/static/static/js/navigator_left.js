$(document).ready(function () {

    //左部顶端导航栏

    $('.mobile-nav-button').hover(
function () {
    $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(1)" ).toggleClass( "mobile-nav-button__line--1");
    $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(2)" ).toggleClass( "mobile-nav-button__line--2");
    $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(3)" ).toggleClass( "mobile-nav-button__line--3");

    $('.mobile-menu').toggleClass('mobile-menu--open');
},function () {
            event.stopPropagation();
        }
    )

    $(".w3_menu").hover(
        function () {
            event.stopPropagation();
        },
        function () {
            $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(1)" ).toggleClass( "mobile-nav-button__line--1");
            $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(2)" ).toggleClass( "mobile-nav-button__line--2");
            $( ".mobile-nav-button .mobile-nav-button__line:nth-of-type(3)" ).toggleClass( "mobile-nav-button__line--3");

            $('.mobile-menu').toggleClass('mobile-menu--open');
        }
    )
});

