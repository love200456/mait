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
<html style="overflow-x:hidden;">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<script>
	function insertCro() {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }ComRecommendation/insertOneCR.action',
			data : {
				"cId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("推荐商品添加成功");
				} else if (map.msg == "2") {
					alert("推荐商品失败.....");
				} else if (map.msg == "3") {
					alert("该商品存在异常无法添加.....");
				} else {
					alert("原推荐商品存在异常无法添加.....");
				}
				window.location.href = "${basePath }ComRecommendation/selectComAll.action"
			}
		});
	}
</script>
<title>一级推荐商品</title>
</head>
<style>
.goodlist2 .floatl {
	position: relative;
}
</style>
<body>
	<div class="content">
		<div class="clearfix goodlist2">
			<c:forEach items="${list }" var="cr">
				<div class="floatl">
					<input type="checkbox" class="check" value="${cr.c_id }">
					<div class="shopimg">
						<img src="${picturePath }${cr.c_first_figure }" class="goodimg1">
					</div>
					<div class="shopmess">
						<h4>店铺名称：${cr.s_name }</h4>
						<h5>商品名称：${cr.c_name }</h5>
						<h5>商品单价：${cr.c_unit_price }</h5>
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="text-align: center;">
			<input type="button" value="保存" class="cfm3" onclick="insertCro()">
		</div>
	</div>
	<script>
		// 通过选择店铺更新商品
		$('.first', parent.document).change(function() {
			var shop_opt = $('.first', parent.document).val();//店铺ID
			if(shop_opt=="--请选择店铺--"){
				window.location.href = "${basePath }ComRecommendation/selectComAll.action";
			}else{
				window.location.href = "${basePath }ComRecommendation/selectBysId.action?sId="+shop_opt;
			}
		})
		// 通过商品名称进行搜索
		$('.huoqu', parent.document).click(function() {
			var aa = $('.seaint', parent.document).val();
			if (aa == 1) {
				var cc = $('.see', parent.document).val();//搜索内容
				window.location.href = "${basePath }ComRecommendation/selectCoLike.action?searchVal="+cc;
			} else {
				alert("商品名称输入有误.....");
			}
		})
	</script>
</body>
</html>