<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html style="overflow-x: hidden;">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
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
/*翻页 */
function pageFen(page) {
	if(page >= 0){
		if(page < "${pages}"){
			if("${tiaojian}" == "true"){
				window.location.href="${basePath }EmployManagement/selectAll.action?curPage=" + page;
			}else{
				window.location.href="${basePath }EmployManagement/selectLike.action?searchVal=${searchVal}&curPage=" + page;
			}
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
/*审核 */
function auditEmpInfo(){
	if (confirm('确认要审核？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }EmployManagement/auditEmpInfo.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("审核完成！！！");
				}else{
					alert("审核失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }EmployManagement/selectAll.action";
			}
		});
	}
}

/*删除 */
function deleteEmpInfo(){
	if (confirm('确认要删除？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }EmployManagement/deleteEmpInfo.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("删除完成！！！");
				}else{
					alert("删除失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }EmployManagement/selectAll.action";
			}
		});
	}
}

</script>
<title>招聘管理</title>
</head>
<body>
	<div class="title clearfix">
		<h2 class="floatl active">招聘信息</h2>
	</div>
	<div class="content">
		<form action="${basePath }EmployManagement/selectLike.action"
			method="post">
			<div class="search">
				<input class="see" type="text" name="searchVal" id="searchVal"
					placeholder="请输入职位名称"> <input type="submit" value="搜索">
			</div>
		</form>
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选 <input type="checkbox" value="0"
						onclick="allcheck(this)" /></th>
					<th>职位</th>
					<th>描述</th>
					<th>薪资</th>
					<th>发布日期</th>
					<th>联系人</th>
					<th>联系电话</th>
					<th>状态</th>
				</tr>
				<c:forEach items="${list}" var="emp" varStatus="empstatus">
					<tr>
						<td>${l.index+1 }</td>
						<td class="empid"><input type="checkbox"
							value="${emp.emp_id}" /></td>
						<td class="position">${emp.position}</td>
						<td class="describe">${emp.position_describe}</td>
						<td>${emp.salary}</td>
						<td>${emp.release_date}</td>
						<td>${emp.contacts}</td>
						<td>${emp.contacts_tel}</td>
						<td>
							<c:if test="${emp.emp_flag == '0'}">
								未审核
							</c:if>
							<c:if test="${emp.emp_flag == '1'}">
								已审核
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt;</span> <span>${curPage+1 }</span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt;</span> <em>共
					${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="aduiting" type="button" onclick="auditEmpInfo()"
					value="审核" />
				<input class="delete" type="button" onclick="deleteEmpInfo()"
					value="删除" />
			</div>
		</footer>
	</div>
	<div class="Bomb_box">
		<form action="${basePath }EmployManagement/modifyEmpInfo.action"
			method="post">
			<img class="close" src="../img/close.png"
				style="position: absolute; right: 0; left: 562px; margin: auto; top: 51px;">
			<div class="desdetails">
				<h3>描述修改</h3>
				<div class="down">

					<div class="details">
						<div class="phase">
							<span>职位：</span><span class="pospop"></span>
						</div>
						<div class="phase2">
							<span style="vertical-align: top">描述：</span>
							<textarea class="despop" style="height: 200px"
								name="position_describe">
							</textarea>
						</div>
					</div>
					<input type="hidden" class="hiddenempid" name="emp_id" />
					<input type="button" class="confirm" value="确定" />
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		/*修改 */
		$(".position").click(function() {
			var empid = $(this).siblings(".empid").children().first().val();
			var position = $(this).html();
			var describe = $(this).siblings(".describe").html();
			$(".hiddenempid").val(empid);
			$(".pospop").html(position);
			$(".despop").html(describe);
			$(".Bomb_box").css("display", "block");
		});
		$(".close").click(function() {
			$(".Bomb_box").css("display", "none");
		});
		$(".confirm").click(function() {
			$(".Bomb_box").css("display", "none");
			var empid = $(".hiddenempid").val();
			var despop = $(".despop").val();
			$.ajax({
				type : "post",
				url : '${basePath }EmployManagement/modifyEmpInfo.action',
				data : {
					"emp_id" : empid,
					"position_describe" : despop
				},
				dataType : "json",
				success : function(map) {
					if(map.msg == "1"){
						alert("更新招聘信息成功!!");
					}else{
						alert("更新招聘信息失败!!");
					}
					window.location.href = "${basePath }EmployManagement/selectAll.action";
				}
			});
		});
	</script>
</body>
</html>