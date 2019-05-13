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
function pageFen(page) {
	if(page >= 0){
		if(page < "${pages}"){
			if("${tiaojian}" == "true"){
				window.location.href="${basePath }AuditingGoods/selectAuditGoods.action?curPage=" + page;
			}else{
				window.location.href="${basePath }AuditingGoods/SelectLike.action?searchVal=${searchVal}&curPage=" + page;
			}
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
function delectAuGoods(){
	if (confirm('确认要通过？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }AuditingGoods/delectAuditGoods.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("商品已通过！！！");
				}else{
					alert("商品未被通过！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }AuditingGoods/selectAuditGoods.action"
			}
		});
	}
}
function deleteAg(){
	if (confirm('确认不通过？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }AuditingGoods/deleteAg.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("商品未被通过！！！");
				}else{
					alert("商品未被通过未实现！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }AuditingGoods/selectAuditGoods.action"
			}
		});
	}
}
</script>
<title>商品审核</title>
</head>
<body>
	<div class="title clearfix">
		<h2 class="floatl active">审核信息</h2>
	</div>
	<div class="content">
		<form action="${basePath }AuditingGoods/SelectLike.action" method="post">
			<div class="search">
				<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入商品名称"> <input type="submit" value="搜索">
			</div>
		</form>
		<div class="clearfix goodlist">
			<c:forEach items="${list }" var="ag">
				<div class="floatl">
					<div class="shopimg">
						<img src="${picturePath }${ag.first_figure }">
						<input type="checkbox" value="${ag.ag_id }" />
					</div>
					<div class="shopmess">
						<h5>商品名称：${ag.ag_name }</h5>
						<h5>商品单价：${ag.unit_price }</h5>
						<h5>店铺名称： ${ag.s_name }</h5>
						<p>店铺联系方式：${ag.s_mobile }</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt;</span>
				<span>${curPage+1 }</span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt;</span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="unaduiting" type="button" value="不通过" onclick="deleteAg()" />
				<input class="aduiting" type="button" onclick="delectAuGoods()" value="通过" />
			</div>
		</footer>
	</div>
</body>
</html>