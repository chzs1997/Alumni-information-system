function check_pwd() {
  var pwd = document.getElementById("password").value;
  var regPwd =/^[a-zA-Z0-9]{6,10}$/;
  if (pwd == "" || pwd.trim() == "") {
    document.getElementById("err_pwd").innerHTML = "请输入密码";
    return false;
  } else if (!regPwd.test(pwd)) {
    document.getElementById("err_pwd").innerHTML = "请输入正确格式";
    return false;
  } else {
    document.getElementById("err_pwd").innerHTML = "";
    return true;
  }
}



function check_pwd2() {
  var pwd = document.getElementById("password").value;
  var pwd2 = document.getElementById("password2").value;
  if (pwd2 == "" || pwd2.trim() == "") {
    document.getElementById("err_pwd2").innerHTML = "请输入密码";
    return false;
  } else if(pwd2!=pwd) {
    document.getElementById("err_pwd2").innerHTML = "输入密码不一致";
    return false;
  } else {
    document.getElementById("err_pwd2").innerHTML = "";
    return true;
  }
}

