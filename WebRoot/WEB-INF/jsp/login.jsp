<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<!--[if lt IE 10 ]>
  <script type="text/javascript">
         alert('温馨提示:您的浏览器版本过低，请升级到ie10以上或更换360（极速模式）,Chrome,firefox等浏览器获得完整的浏览效果，谢谢！');
         window.opener=null;
         window.open('', '_self', ''); 
         window.close();
</script>
<![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>弥勒缘后台管理系统</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body style="background:#f1f2f3;">
	<div class="login">
		<div class="logincont">
			<img src="../img/vip.png">
			<input type="username" id="saMobile" placeholder="请输入手机号" class="login_num" />
			<input type="password" placeholder="请输入密码" id="saPassword" class="login_password" />
			<input type="button" value="登录" class="loginbtn login" onclick="loginJudgment()" />
		</div>
	</div>
	<script src="../js/jquery-3.1.0.min.js"></script>
	<script>
		function loginJudgment() {
			if ($(".login_num").val() == "" || $(".login_password").val() == "") {
				alert("手机号或密码不能为空！")
				return false;
			} else if (!$(".login_num").val().match(/^1(3|4|5|7|8)\d{9}$/)) {
				alert("手机号码格式不正确！");
				$(".phone-num").val("").focus();
				return false;
			} else {
				var saMobiles = $("#saMobile").val();
				var saPasswords = $("#saPassword").val();
				$.ajax({
							type : "post",
							url : '${basePath }SysAdmin/loginJudgment.action',
							data : {
								"saMobile" : saMobiles,
								"saPassword" : saPasswords
							},
							dataType : "json",
							success : function(map) {
								if (map.msg == "1") {
									window.location.href = "${basePath }SysAdmin/jumpIndex.action"
								} else {
									alert("该账户未被注册,请核对账户.........");
									window.location.href = "${basePath }SysAdmin/jumpLogin.action"
								}
							}
						});
			}
			return true;
		}
		
		
/* 		$.ajax({
	        type : "get",  
	        async:false,  
	        url : "http://192.168.1.131:8888/pm/base/json.act?sid=1494&busiId=101",
	        dataType : "jsonp",//数据类型为jsonp  
	        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数  
	        success : function(data){
	        	console.log(data);
	        },  
	        error:function(){  
	            alert('fail');  
	        }  
	    });   */
		
		
	</script>
</body>
</html>