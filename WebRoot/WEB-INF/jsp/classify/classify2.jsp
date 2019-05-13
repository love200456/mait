<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>二级分类广告</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
</head>
<body>
<div class="title clearfix">
 <a href="${basePath }AdvertisingCategory/jumpClassify.action?rank=1"><h2 class="floatl isshop">一级分类管理</h2></a>
 <a href="${basePath }AdvertisingCategory/jumpClassify.action?rank=2"><h2 class="floatl orderlist active">二级分类管理</h2></a>
 <!-- <div class="floatr clasearch"><input class="see" type="text" placeholder="请输入搜索内容">
 <input type="button" class="huoqu" value="搜索"><input class="seaint" type="text" value="" style="display: none;"/></div> -->
</div>
<div class="content2">
<div class="search2 clearfix">
<div class="floatl">
  <a class="curr second_link" href="javascript:void(0)" _href="${basePath }AdvertisingCategory/selectAC.action?level=2">二级分类广告</a>
  <a class="second_link" href="javascript:void(0)" _href="${basePath }StoreBackstage/selectAllStoreXian.action?level=2">二级推荐店铺</a>
</div>
</div>
 <div class="contain2">
		<div class="little_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="auto" frameborder="0" src="${basePath }AdvertisingCategory/selectAC.action?level=2" id="iframe" width="100%; height=100%;" class="secondiframe"></iframe>
		</div>
</div>
<script>
 $(".second_link").click(function(){
	 $(this).addClass("curr");
	 $(this).siblings().removeClass("curr");
	 var val = $(this).attr("_href");
    $(".secondiframe").attr("src",val);
	 	 if($(this).html()=="二级分类广告")
	     {
	   	   $(".clasearch").css("display","none");
	     }
	 else if($(this).html()=="二级推荐店铺")
		 {
			$(".clasearch").css("display","block");
		 }
 });	
</script>
</div>
</body>
</html>