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
<title>财务管理</title>
</head>
<body style="min-width:1000px">
	<div class="title clearfix">
		<h2 class="floatl active">财务列表</h2>
	</div>
	<div class="content">
		<div class="search clearfix">
			<form action="${basePath }FinanceStatistics/selectTime.action" method="post">
				<div class="floatl">
					<h3 class="laydate-icon" id="start" style="float:left; width:180px; height:38px;text-align:center;border:1px solid #6498CA; font-size: 14px; line-height: 38px; color: #fff; background: #C8DEFC; margin-right: 20px;">----请选择日期----</h3>
					<h3 class="laydate-icon" id="end" style="float:left; width:180px; height:38px;text-align:center;border:1px solid #6498CA; font-size: 14px; line-height: 38px; color: #fff; background: #C8DEFC;">----请选择日期----</h3>
					<div class="floatl">
						<input type="submit" style="margin-top:10px;"  value="确认"/>
						<input type="hidden" value="" id="strtime" name="strtime">
						<input type="hidden" value="" id="endtime" name="endtime" style="display: block;">
					</div>
				</div>
			</form>
			<div class="floatr">
				<form action="${basePath }FinanceStatistics/selectLikePage.action" method="post">
					<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入店铺名称">
					<input type="submit" class="huoqu" value="搜索">
				</form>
			</div>
		</div>
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选  <input type="checkbox" value="0" onclick="allcheck(this)"/></th>
					<th>店铺名称</th>
					<th>联系方式</th>
					<th>订单时间</th>
					<th>订单编号</th>
					<th>积分抵扣</th>
					<th>缘分抵扣</th>
					<th>缘分(全缘分购买)</th>
					<th>商品总额</th>
					<th>实际付款额</th>
				</tr>
				<c:forEach items="${list }" var="fs" varStatus="l">
					<tr>
						<td>${l.index + 1 }</td>
						<td><input type="checkbox" value="${fs.fs_id }" /></td>
						<td>${fs.s_name }</td>
						<td>${fs.s_mobile }</td>
						<td>${fs.fs_time }</td>
						<td>${fs.o_number }</td>
						<td>${fs.time_limited_integration }</td>
						<td>${fs.ermanent_integral_bonus }</td>
						<td>${fs.full_integral_purchase }</td>
						<td>${fs.c_unit_price }*${fs.ocl_num }</td>
						<td>${fs.fs_turnover }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt;</span>
				<span>${curPage+1 }</span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt;</span>
				<em>共 ${pages } 页</em>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div class="adddiv">
				<span>积分抵扣合计：${time_limited_sum }</span>&nbsp;&nbsp;
				<span>缘分抵扣合计：${ermanent_integral_sum }</span>&nbsp;&nbsp;
				<span>商品总额合计：${price_sum }</span>&nbsp;&nbsp;
				<span>实际付款合计：${fs_turnover_sum }</span>
			</div>
			<div class="btnlist">
				<input class="delete" type="button" value="删除" onclick="deleteFS()" />
			</div>
		</footer>
	</div>
	<script type="text/javascript" src="../js/laydate.dev.js"></script>
	<script type="text/javascript" src="../js/data.js"></script>
	<script>
		/*获取日期*/
		setInterval(function() {
			var strtime = document.getElementById("start").innerHTML;
			var endtime = document.getElementById("end").innerHTML;
			var aa = $("#strtime").val(strtime);
			$("#endtime").val(endtime);
		}, 0);
	</script>
	<script>
	function pageFen(page) {
		if(page >= 0){
			if(page < "${pages}"){
				if("${tiaojian}" == "1"){
					window.location.href="${basePath }FinanceStatistics/selectPage.action?curPage=" + page;
				}else if("${tiaojian}" == "2"){
					window.location.href="${basePath }FinanceStatistics/selectLikePage.action?searchVal=${searchVal}&curPage=" + page;
				}else{
					window.location.href="${basePath }FinanceStatistics/selectTime.action?strtime=${strtime}&endtime=${endtime}&curPage=" + page;
				}
			}else{
				alert("当前是最后一页");
			}
		}else{
			alert("当前是第一页");
		}
	}
	function deleteFS(){
		if (confirm('确认要删除吗？')) {
			var option = $(":checked");
			var checkedId = "";
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			$.ajax({
				type : "post",
				url : '${basePath }FinanceStatistics/delectFS.action',
				data : {
					"checkedId" : checkedId
				},
				dataType : "json",
				success : function(map) {
					if (map.msg == "1") {
						alert("财务信息删除成功！！！");
					}else{
						alert("财务信息删除失败！！！请刷新页面重试......");
					}
					window.location.href = "${basePath }FinanceStatistics/selectPage.action"
				}
			});
		}
	}
	/*全选 */
	   function allcheck(obj)
	   { 
	      var isCheck = $(obj).prop("checked");
	      if(isCheck==true)
	      {
	       $("input[type=checkbox]").prop("checked",true);
	      }
	      else
	      {
	       $("input[type=checkbox]").prop("checked",false);
	      }
	      
	 
	   }
	</script>
</body>
</html>