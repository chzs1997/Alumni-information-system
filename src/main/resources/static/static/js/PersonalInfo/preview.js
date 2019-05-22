function handleFiles(obj,id) {file = document.getElementById("icon");
var files = obj.files; 
img = new Image(); 
if(window.URL){ 
img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
img.onload = function(e) 
{ window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL 
} }
file.src=img.src; //上传文件 
var fd = new FormData(),//实例化一个表单 
xhr = new XMLHttpRequest();fd.append('headimg', files[0]);//追加图片元素 
xhr.open('post', 'user.php?act=act_edit_img');//请求方式，请求地址 xhr.send(fd);
}
