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
function insertPM(){
	var pmContent = $(".area").val();
	var pmIds = $("#pmId").val();
	if(pmIds != ""){
		$.ajax({
		type : "post",
		url : '${basePath }PushMessage/updatePM.action',
		data : {
			"pm_content" : pmContent,
			"pm_id" : pmIds
		},
		dataType : "json",
		success : function(map) {
			if(map.msg == "1"){
				alert("消息修改成功");
			}else{
				alert("消息修改失败.....");
			}
			window.location.href = "${basePath }PushMessage/selectAllPage.action"
		}
		});
	}else{
		$.ajax({
		type : "post",
		url : '${basePath }PushMessage/insertPM.action',
		data : {
			"pm_content" : pmContent
		},
		dataType : "json",
		success : function(map) {
			if(map.msg == "1"){
				alert("新增消息成功");
			}else{
				alert("新增消息失败.....");
			}
			window.location.href = "${basePath }PushMessage/selectAllPage.action"
		}
		});
	}
 	
}
function delectAu(){
	if (confirm('确认要删除吗？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }PushMessage/doFalseDelete.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("删除信息成功！！！");
				}else{
					alert("删除信息失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }PushMessage/selectAllPage.action"
			}
		});
	}
}
function fand(id){
	if(confirm('确认要发送吗？')){
		$.ajax({
			type : "post",
			url : '${basePath }UserMessage/insertAllUM.action',
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("信息发送成功！！！");
				}else if (map.msg == "2"){
					alert("信息发送失败！！！请刷新页面重试......");
				}else if (map.msg == "3"){
					alert("请查看是否存在用户");
				}else {
					alert("该消息不存在！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }PushMessage/selectAllPage.action"
			}
		});
	}
}

function pageFen(page) {
	if(page >= 0){
		if(page < "${pages}"){
			window.location.href="${basePath }PushMessage/selectAllPage.action?curPage=" + page;
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
</script>
<title>消息管理</title>
</head>
<body>
	<div class="title clearfix">
		<h2 class="floatl active">消息列表</h2>
	</div>
	<div class="content">
		<div class="tab">
			<table>
				<tr>
					<th colspan="2">序号</th>
					<th>发布时间</th>
					<th>发布内容</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${list }" var="pm" varStatus="l">
					<tr>
						<td>${l.index+1 }</td>
						<td><input type="checkbox" value="${pm.pm_id }" /></td>
						<td>${pm.pm_time }</td>
						<td class="metd"><p class="sendme">${pm.pm_content }</p></td>
						<td><input type="button" value="发送" onclick="fand('${pm.pm_id }')" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt; </span>
				<span> ${curPage+1 } </span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt; </span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="give" type="button" value="发布消息" />
				<input class="delete" type="button" onclick="delectAu()" value="删除" />
			</div>
		</footer>
	</div>
	<div class="Bomb_box">
		<img class="close" src="../img/close.png" style="position: absolute; right: 0; left: 560px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>发布内容</h3>
			<div class="down">
				<div class="details">
					<div class=" phase4">
						<p>请输入发布内容：</p>
						<textarea class="area"></textarea>
						<span id="text-count">限100字以内</span>
						<input type="hidden" id="pmId" value="" />
					</div>
				</div>
				<input type="button" class="confirm" value="确定" onclick="insertPM()" />
			</div>
		</div>
	</div>
	<script>
		$(".give").click(function() {
			$(".Bomb_box").css("display", "block");
			$(".area").val(" ");
			$("#pmId").val("");
		});
		$(".sendme").click(function() {
			$(".Bomb_box").css("display", "block");
			var thisId = $(this).parent().siblings().find('input').val();
			$("#pmId").val(thisId);
			var thisMessage = $(this).html();
			$(".area").val(thisMessage);
		});
		$(".close").click(function() {
			$(".Bomb_box").css("display", "none");
		});
		$(".confirm").click(function() {
			$(".Bomb_box").css("display", "none");
			var area = $(".area").val();
		});
	</script>
	<script type="text/javascript">
		/*字数限制*/
		$(".area").on("input propertychange", function() {
			var $this = $(this), _val = $this.val(), count = "";
			if (_val.length > 100) {
				$this.val(_val.substring(0, 100));
			}
			count = 100 - $this.val().length;
			$("#text-count").text("剩余" + count + "字");
		});
	</script>
</body>
</html>