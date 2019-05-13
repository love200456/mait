<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<title>提现管理</title>
<script>

function pageFen(page) {
	if(page >= 1){
		if(page < "${pages}"){
			window.location.href="${basePath }Withdraw/selectAll.action?curPage=" + page;
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
		<h2 class="floatl active">提现列表</h2>
	</div>
	<div class="content">
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选  <input type="checkbox" value="0" onclick="allcheck(this)"/></th>
					<th>用户名</th>
					<th>用户ID</th>
					<th>金额</th>
					<th>提现时间</th>
				</tr>
				<c:forEach items="${list}" var="ele" varStatus="l">
					<tr>
						<td>${l.index+1 }</td>
						<td><input type="checkbox" value="${ele.id}" /></td>
						<td class="name">${ele.userName}</td>
						<td class="phone">${ele.userID}</td>
						<td>${ele.money}</td>
						<td><fmt:formatDate value="${ele.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt; </span>
				<span> ${curPage} </span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt; </span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
			</div>
		</footer>
	</div>
	<script>
	/*全选 */
	   function allcheck(obj){
	      var isCheck = $(obj).prop("checked");
	      if(isCheck==true){
	       $("input[type=checkbox]").prop("checked",true);
	      }else{
	       $("input[type=checkbox]").prop("checked",false);
	      }
	   }
	  
	</script>
</body>
</html>