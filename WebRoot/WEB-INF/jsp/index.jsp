<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!DOCTYPE html>
<html style="height:100%; overflow-y:hidden;">
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
<meta charset="utf-8">
<title>弥勒缘管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
</head>
<body style="position: relative; height: 100%; min-width:1300px">
	<!-- head -->
	<header class="clearfix">
		<div class="floatl">
			<img src="../img/3.png" />
			<h1>弥勒缘管理系统</h1>
		</div>
		<a href="${basePath }SysAdmin/exitSystem.action" class="floatr">退出系统</a>
	</header>
	<div class="navwrap">
		<div class="floatl nav">
			<div class="admin">
				<img src="../img/admin.jpg" alt="" />
				<h3>系统管理员</h3>
			</div>
			<div class="nav_menu">
				<a class="link active" href="javascript:void(0)" _href="${basePath }AverageUser/selectAll.action">
				<img src="../img/user.png"><span>用户管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }AverageUser/selectauLP.action">
				<img src="../img/inter.png"><span>积分管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }StoreBackstage/selectAllPage.action">
				<img src="../img/shop1.png"><span>店铺管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }FinanceStatistics/selectPage.action">
				<img src="../img/money.png"><span>财务管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }PushMessage/selectAllPage.action">
				<img src="../img/message.png"><span>消息管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }AdvertisingCategory/jumpClassify.action?rank=1">
				<img src="../img/classify.png"><span>分类管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }AuditingGoods/selectAuditGoods.action">
				<img src="../img/aduting.png"><span>商品审核</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }EmployManagement/selectAll.action">
				<img src="../img/emp.png"><span>招聘管理</span> </a>
				<a class="link" href="javascript:void(0)" _href="${basePath }Withdraw/selectAll.action">
				<img src="../img/inter.png"><span>提现管理</span> </a>
				
			</div>
		</div>
	</div>
	<!--container-->
	<div class="container floatl">
		<!--iframe_box-->
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="auto" frameborder="0" src="${basePath }AverageUser/selectAll.action" id="iframe" width="100%; height=100%;"></iframe>
			</div>
		</div>
		<script>
			$(function() { $(".link").click( function() {
					$(this).addClass('active').siblings().removeClass('active');
					var val = $(this).attr("_href");
					$(".show_iframe iframe").attr("src", val);
				});
			})
		</script>
	</div>
</body>
</html>