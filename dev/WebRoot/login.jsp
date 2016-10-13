<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>用户登录</TITLE><LINK 
href="css/login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
$(function(){
	
});

function login(){
	var userid = document.getElementById("TxtUserName").value;
	var pwd = document.getElementById("TxtPassword").value;
	var verifyCode = document.getElementById("verifyCode").value;
	var msg = document.getElementById("div_msg");
	if(userid=="" || pwd=="" || verifyCode==""){
		msg.innerText = "用户名密码验证码不能为空";
	}else{
		msg.innerText = " ";
		var data = "&user.UId="+userid+"&user.UPwd="+pwd+"&verifyCode="+verifyCode;
		$.ajax({
			type: "POST",
			url: "login.action",
			data: data,
			dataType: "json",
			success: function(json){
				if(json.success){
					window.location.href = "view/index2.jsp";
				}else{
					msg.innerText = json.msg;
				}
			}
			,error: function(json){
				alert("登录验证异常");
			}
		});
	}
}
function initApp() {
	try {
		window.parent.userOut(true);
	} catch(e){}
}
</script>
</HEAD>
<BODY id=userlogin_body onload="initApp();">
<DIV></DIV>

<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id=user_main>
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
	<div id="div_msg" style="color: red; padding-left: 60px;">&nbsp;</div>
    <UL>
      <LI class=user_main_text>用户名 </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=TxtUserName 
      maxLength=50 name=TxtUserName value="admin"> </LI></UL>
    <UL>
      <LI class=user_main_text>密码</LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=TxtPassword 
      type=password name=TxtPassword value="123456"> </LI></UL>
    <UL>
      <LI class=user_main_text>验证码</LI>
      <LI class=user_main_input>
      	 <input name="verifyCode" id="verifyCode" class=TxtValidateCodeCssClass maxlength="4" size="4"  title="请输入验证码" />
	     <img src="verifyCode.jsp" onload="$.ajax({url:'getVerifyCode.jsp', cache:false, success:function(text){$('#verifyCode').val(text);}});" ondblclick="this.src='verifyCode.jsp?timestamp='+Math.random();" title="˫��ͼƬ,ˢ����֤��" style="margin-top:0px; MARGIN-BOTTOM: 0px; MARGIN-LEFT: -25px">
      </LI>
    </UL>
      </DIV>
      </LI>
    <LI class=user_main_r>
    <INPUT class=IbtnEnterCssClass id=IbtnEnter 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    onclick='login()' 
    type=image src="images/user_botton.gif" name=IbtnEnter> </LI></UL>
  <DD id=user_bottom>
  <UL>
    <LI class=user_bottom_l></LI>
    <LI class=user_bottom_c><SPAN style="MARGIN-TOP: 40px"></SPAN> </LI>
    <LI class=user_bottom_r></LI></UL></DD></DL></DIV><SPAN id=ValrUserName 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrPassword 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrValidateCode 
style="DISPLAY: none; COLOR: red"></SPAN>
<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

<DIV></DIV>
</FORM></BODY>
<script type="text/javascript">
document.getElementById("TxtUserName").focus();
</script>
</HTML>
