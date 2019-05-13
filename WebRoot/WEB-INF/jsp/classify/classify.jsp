<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html style="overflow-x:hidden;height: 100%;">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<title>分类管理</title>
</head>
<body style="min-width:1000px; position: relative; height: 100%;">
	<div class="title clearfix">
		<a href="${basePath }AdvertisingCategory/jumpClassify.action?rank=1">
		<h2 class="floatl active isshop">一级分类管理</h2>
		</a> <a href="${basePath }AdvertisingCategory/jumpClassify.action?rank=2">
		<h2 class="floatl orderlist">二级分类管理</h2>
		</a>
		<!-- <div class="floatr clasearch">
			<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入搜索内容">
			<input type="button" class="huoqu" value="搜索" onclick="searchValue()" >
			<input class="seaint" type="hidden" name="substance" id="substance" value="" />
		</div> -->
	</div>
	<div class="content">
		<div class="search2 clearfix">
			<div class="floatl">
				<a class="curr first_link" href="javascript:void(0)" _href="${basePath }AdvertisingCategory/selectAC.action?level=1">一级分类广告</a>
				<a class="first_link" href="javascript:void(0)" _href="${basePath }ComRecommendation/selectComAll.action">一级推荐商品</a>
				<a class="first_link" href="javascript:void(0)" _href="${basePath }StoreBackstage/selectAllStoreXian.action?level=1">一级推荐店铺</a>
			</div>
			<div class="floatr">
				<select class="first">
					<option>--请选择店铺--</option>
					<c:forEach items="${slesctAllPage }" var="s">
						<option value="${s.s_id }">${s.s_name }</option>
					</c:forEach>
				</select>
				<input class="seleint" type="text" value="" style="display: none;" />
			</div>
		</div>
		<div class="contain2">
			<div class="little_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="auto" frameborder="0" src="${basePath }AdvertisingCategory/selectAC.action?level=1" class="firstiframe" id="iframe" width="100%; height=100%;"></iframe>
			</div>
		</div>
	</div>
	<script>
		$(".first_link").click(function() {
			$(this).addClass("curr");
			$(this).siblings().removeClass("curr");
			var val = $(this).attr("_href");
			$(".firstiframe").attr("src", val);
			if ($(this).html() == "一级分类广告") {
				$(".clasearch").css("display", "none");
				$("select").css("display", "none");
			} else if ($(this).html() == "一级推荐商品") {
				$(".clasearch").css("display", "block");
				$("select").css("display", "block");
				$(".seaint").val("1");
			} else if ($(this).html() == "一级推荐店铺") {
				$(".clasearch").css("display", "block");
				$("select").css("display", "none");
				$(".seaint").val("2");
			}

		});
	</script>
</body>
</html>