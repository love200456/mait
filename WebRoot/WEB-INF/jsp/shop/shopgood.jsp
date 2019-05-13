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
<title>店铺商品</title>
<script>
function pageFen(page) {
	var sIds = $("#sId").val();
	if(page >= 0){
		if(page < "${pages}"){
			if("${tiaojian}" == "true"){
				window.location.href="${basePath }Commodity/selectBysIdFen.action?s_id=" + sIds + "&curPage=" + page;
			}else{
				window.location.href="${basePath }Commodity/selectLikeFen.action?s_id=" + sIds + "&searchVal=${searchVal}&curPage=" + page;
			}
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
function deleteCom(){
	var sIds = $("#sId").val();
	if (confirm('确认要删除吗？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }Commodity/deleteCommodity.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("商品删除成功！！！");
				}else{
					alert("商品删除失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }Commodity/selectBysIdFen.action?s_id=" + sIds
			}
		});
	}
}
</script>
</head>
<style>
.goodlist2 .floatl {
	position: relative;
}
</style>
<body>
	<div class="content">
		<div class="clearfix goodlist2">
			<c:forEach items="${list }" var="c" varStatus="l">
				<div class="floatl">
					<input type="checkbox" class="check" value="${c.c_id }" >
					<input type="hidden" id="sId" value="${c.s_id }" />
					<div class="shopimg">
						<img src="${picturePath }${c.c_first_figure }" class="goodimg1">
					</div>
					<div class="shopmess">
						<h5>商品名称：<span class="goodname1">${c.c_name }</span>
						</h5>
						<h5>商品单价：<span class="goodsale1">${c.c_unit_price }</span>
						</h5>
						<input class="goodme1" type="text" value="${c.c_introduce }" style="display: none">
					</div>
				</div>
			</c:forEach>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt; </span>
				<span> ${curPage+1 } </span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt; </span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="delete" type="button" onclick="deleteCom()" value="删除" />
			</div>
		</footer>
	</div>
	<div class="Bomb_box3">
		<img class="close" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>商品详情</h3>
			<div class="down">
				<div class="details">
					<div class="gim">
						<img class="goodimg2" src="" />
					</div>
					<div class=" phase clearfix">
						<span>商品名称：</span><span class="goodname2"></span>
					</div>
					<div class=" phase clearfix">
						<span>商品价格：</span><span class="goodsale2"></span>
					</div>
					<div class="phase clearfix">
						<p class="floatl">商品描述：</p>
						<p class="goodme2 floatl"></p>
					</div>
				</div>
				<input type="button" class="confirm" value="确定" style="margin-top: 50px;" />
			</div>
		</div>
	</div>
	<script>
 $(".goodimg1").click(function(){
	 var goodimg = $(this).attr("src");
	 var goodname = $(this).parent().siblings().find('.goodname1').html();
	 var goodsale = $(this).parent().siblings().find('.goodsale1').html();
	 var goodmessage = $(this).parent().siblings().find('.goodme1').val();
	 $(".goodimg2").attr("src",goodimg);
	 $(".goodname2").html(goodname);
	 $(".goodsale2").html(goodsale);
	 $(".goodme2").html(goodmessage);
	 $(".Bomb_box3").css("display","block");
 });
$(".close").click(function(){
	$(".Bomb_box3").css("display","none");
});
$(".confirm").click(function(){
	$(".Bomb_box3").css("display","none");
})
</script>
</body>
</html>