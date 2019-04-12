//事件上报处理滚动
var speed=50;
var marquee=document.getElementById("marquee");
var marqueeheight = $('#marqueearea').height() ; //滚动区域高度
var scorll=document.getElementById("scorll");
var marqueearea=document.getElementById("marqueearea");
marquee.innerHTML=scorll.innerHTML; //克隆scorll为marquee
function Marquee1(){
    //当滚动至scorll与marquee交界时
    if(marquee.offsetTop-marqueearea.scrollTop-marqueeheight<=0){
        marqueearea.scrollTop-=scorll.offsetHeight; //marqueearea跳到最顶端
    }else{
        marqueearea.scrollTop++;
    }
}
var MyMar1=setInterval(Marquee1,speed);//设置定时器
//鼠标移上时清除定时器达到滚动停止的目的
marqueearea.onmouseover=function() {clearInterval(MyMar1)};
//鼠标移开时重设定时器
marqueearea.onmouseout=function(){MyMar1=setInterval(Marquee1,speed)};