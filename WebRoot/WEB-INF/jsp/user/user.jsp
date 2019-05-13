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
<title>用户管理</title>
<script>
function updateState(auIds){
	$.ajax({
		type : "post",
		url : '${basePath }AverageUser/updateauIdentity.action',
		data : {
			"auId" : auIds
		},
		dataType : "json",
		success : function(map) {
			if(map.msg == "1"){
				alert("该用户已成为商家");
			}else{
				alert("该用户未成为商家,请刷新页面.....");
			}
			window.location.href = "${basePath }AverageUser/selectAll.action"
		}
	});
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
			url : '${basePath }BatchDelete/delectauId.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("删除用户成功！！！");
				}else{
					alert("删除用户失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }AverageUser/selectAll.action"
			}
		});
	}
}
function pageFen(page) {
	if(page >= 0){
		if(page < "${pages}"){
			if("${tiaojian}" == "true"){
				window.location.href="${basePath }AverageUser/selectAll.action?curPage=" + page;
			}else{
				window.location.href="${basePath }AverageUser/selectLike.action?searchVal=${searchVal}&curPage=" + page;
			}
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
</script>
</head>
<body style="min-width:1000px">
	<div class="title clearfix">
		<h2 class="floatl active">用户列表</h2>
	</div>
	<div class="content">
		<form action="${basePath }AverageUser/selectLike.action" method="post">
			<div class="search">
				<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入手机号"> <input type="submit" value="搜索">
			</div>
		</form>
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选  <input type="checkbox" value="0" onclick="allcheck(this)"/></th>
					<th>用户名</th>
					<th>账号</th>
					<th>登录密码</th>
					<th>备注</th>
					<th>用户权限</th>
				</tr>
				<c:forEach items="${list }" var="au" varStatus="l">
					<tr>
						<td>${l.index+1 }</td>
						<td><input type="checkbox" value="${au.auId }" />
						</td>
						<td class="name">${au.auBuyerNick }</td>
						<td class="phone">${au.auMobile }</td>
						<td>${au.auPassword }</td>
						<td> <input type="text" class="beizhu" value="${au.remarks }" onchange="gai(this)"/> </td>
						<c:if test="${au.auIdentity =='1' }">
						<td><input type="button" value="成为商家" onclick="updateState(${au.auId })" />
						</td>
						</c:if>
						<c:if test="${au.auIdentity =='2' }">
						<td><span style="background:#999; width:100px; line-height:30px;height:30px; color:#fff; text-align:center;display:block;margin:auto;border-radius:5px;font-size:13px;">已为商家</span>
						</td>
						</c:if>
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
				<input class="delete" type="button" value="删除" onclick="delectAu()" />
			</div>
		</footer>
	</div>
	<div class="Bomb_box">
		<img class="close" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>用户详情</h3>
			<div class="down">
				<div class="details">
					<div class=" phase">
						<span>用户名：</span><span class="namemess"></span>
					</div>
					<div class=" phase2">
						<span>联系方式：</span><span class="nummess"></span>
					</div>
				</div>
				<input type="button" class="confirm" value="确定" />
			</div>
		</div>
	</div>
	<script>
	/*全选 */
	   function allcheck(obj)
	   { 
	      var isCheck = $(obj).prop("checked");
	      if(isCheck==true){
	       $("input[type=checkbox]").prop("checked",true);
	      }else{
	       $("input[type=checkbox]").prop("checked",false);
	      }
	   }
	   /*获取备注 */
	    function gai(obj)
	   {
	     var a = obj.value;
	     var auId = $(obj).parent().siblings().find("input[type=checkbox]").val();
	     $.ajax({
			type : "post",
			url : '${basePath }AverageUser/modifyRemarks.action',
			data : {
				"auId" : auId,
				"resarks" : a
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("用户添加备注成功！！！");
				}else{
					alert("用户添加备注失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }AverageUser/selectAll.action"
			}
		});
	   }
		$(".name").click(function() {
			var isname = $(this).html();
			var isnum = $(this).siblings(".phone").html();
			$(".namemess").html(isname);
			$(".nummess").html(isnum);
			$(".Bomb_box").css("display", "block");
		});
		$(".close").click(function() {
			$(".Bomb_box").css("display", "none");
		});
		$(".confirm").click(function() {
			$(".Bomb_box").css("display", "none");
		})
	</script>
</body>
</html>