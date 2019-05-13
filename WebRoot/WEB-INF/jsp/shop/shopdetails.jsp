<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html style="overflow-x:hidden;"height: 100%; >
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<title>店铺管理</title>
</head>
<body style="min-width:1000px; position: relative; height: 100%; width: 100%;">
	<div class="title clearfix">
		<a href="${basePath }StoreBackstage/jumpShopdetails.action?s_id=${s_id }"><h2 class="floatl active isshop">店铺信息</h2> </a>
		<a href="${basePath }OrderInformation/selectBysId.action?s_id=${s_id }"><h2 class="floatl orderlist">订单列表</h2> </a>
	</div>
	<div class="content">
		<div class="search2 clearfix">
			<div class="floatl">
				<a class="curr shop_link" href="javascript:void(0)" _href="${basePath }StoreBackstage/selectBysIdPage.action?s_id=${s_id }">店铺详情</a>
				<a class="shop_link" href="javascript:void(0)" _href="${basePath }Commodity/selectBysIdFen.action?s_id=${s_id }">店铺商品</a>
			</div>
			<form action="${basePath }Commodity/selectLikeFen.action?s_id=${s_id }" method="post">
				<div class="floatr issousuo" style="display: none;">
					<input class="see" type="text" placeholder="请输入商品名称">
					<input type="submit" class="huoqu" value="搜索">
				</div>
			</form>
		</div>
		<div class="contain3">
			<div id="iframe_box" class="Hui-article">
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<iframe scrolling="no" frameborder="0" src="${basePath }StoreBackstage/selectBysIdPage.action?s_id=${s_id }" id="iframe" width="100%; height=100%;"></iframe>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../js/laydate.dev.js"></script>
	<script>
		$(".shop_link").click(function() {
			$(this).addClass("curr");
			$(this).siblings().removeClass("curr");
			var val = $(this).attr("_href");
			$(".show_iframe iframe").attr("src", val);
			if ($(this).html() == "店铺详情") {
				$(".issousuo").css("display", "none");
			} else if ($(this).html() == "店铺商品") {
				$(".issousuo").css("display", "block");
			}
		})
	</script>
</body>
</html>