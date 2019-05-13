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
function insertChe(){
	var option = $(":checked");
	var checkedId = "";
	for ( var i = 0, len = option.length; i < len; i++) {
		checkedId += option[i].value + ",";
	}
	$.ajax({
		type : "post",
		url : '${basePath }ShopRecommendation/insertTwoSR.action',
		data : {
			"sId" : checkedId,
			"sp_level" : "2"
		},
		dataType : "json",
		success : function(map) {
			if(map.msg == "1"){
				alert("推荐二级店铺添加成功");
			}else if(map.msg == "2"){
				alert("推荐二级店铺失败.....");
			}else if(map.msg == "3"){
				alert("该店铺存在异常无法添加.....");
			}else{
				alert("原推荐店铺存在异常无法添加.....");
			}
			window.location.href = "${basePath }StoreBackstage/selectAllStoreXian.action?level=2"
		}
	});
}
</script>
<title>二级推荐店铺</title>
</head>
<style>
.goodlist2 .floatl {
	position: relative;
}
</style>
<body>
	<div class="content">
		<div class="clearfix goodlist2">
		<c:forEach items="${list }" var="s" >
			<div class="floatl">
				<input type="checkbox" class="check" value="${s.s_id }" >
				<div class="shopimg">
					<img src="${picturePath }${s.picture }" class="goodimg1">
				</div>
				<div class="shopmess">
					<h5>店铺名称：${s.s_name }</h5>
				</div>
			</div>
			</c:forEach>
		</div>
		<div style="text-align: center;">
			<input type="button" value="保存" class="cfm3" onclick="insertChe()" >
		</div>
	</div>
	<script>
	$('.huoqu', parent.document).click(function(){
		var cc = $('.see', parent.document).val();//搜索内容
		window.location.href = "${basePath }StoreBackstage/selectLikeLevelXian.action?searchVal="+cc+"&levels=2";
	})
	</script>
</body>
</html>