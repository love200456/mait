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
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
</head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<body>
	<div class="title clearfix">
		<a href="${basePath }StoreBackstage/jumpShopdetails.action?s_id=${s_id }"><h2 class="floatl isshop">店铺信息</h2> </a>
		<a href="${basePath }OrderInformation/selectBysId.action?s_id=${s_id }"><h2 class="floatl active orderlist">订单列表</h2> </a>
	</div>
	<div class="content2">
		<div class="search clearfix">
			<form action="${basePath }OrderInformation/selectByTime.action" method="post">
				<div class="floatl">
					<h3 class="laydate-icon" id="start" style="float:left; width:180px; height:38px;text-align:center;border:1px solid #6498CA; font-size: 14px; line-height: 38px; color: #fff; background: #C8DEFC; margin-right: 20px;">----请选择日期----</h3>
					<h3 class="laydate-icon" id="end" style="float:left; width:180px; height:38px;text-align:center;border:1px solid #6498CA; font-size: 14px; line-height: 38px; color: #fff; background: #C8DEFC;" />----请选择日期---- </h3>
					<div class="floatl">
						<input type="submit" value="确认" style="margin-top:10px;" />
						<input type="hidden" value="" name="strtime" id="strtime">
						<input type="hidden" value="" id="endtime" name="endtime" style="display: block;">
						<input type="hidden" name="sId" id="sId" value="${s_id }" />
					</div>
				</div>
			</form>
		</div>
		<div class="tab">
			<table>
				<tr>
					<th colspan="2">序号</th>
					<th>商品名称</th>
					<th>用户名称</th>
					<th>下单时间</th>
					<th>订单总金额</th>
					<th>缘分</th>
					<th>积分</th>
					<th>缘分购买(全缘分购买)</th>
					<th>订单详情</th>
				</tr>
				<c:forEach items="${list }" var="oi" varStatus="l">
					<tr>
						<td>${l.index + 1 }<input type="hidden" id="sId" value="${s_id }" />
						</td>
						<td><input type="checkbox" value="${oi.o_id }" /></td>
						<td>${oi.c_name }</td>
						<td>${oi.auBuyerNick }</td>
						<td>${oi.o_time }</td>
						<td>${oi.o_amount }</td>
						<td>${oi.use_limit_integral }</td>
						<td>${oi.use_permanent_points }</td>
						<td>${oi.full_integral_purchase }</td>
						<td><a href="${basePath }OrderInformation/selectByoId.action?o_id=${oi.o_id }" class="shopde">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
			<script>
				$(".isshop").click(function() {
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
					$(".content").css("display", "block");
					$(".content2").css("display", "none");
				});
				$(".orderlist").click(function() {
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
					$(".content").css("display", "none");
					$(".content2").css("display", "block");
				})
			</script>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt; </span>
				<span> ${curPage+1 } </span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt; </span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="delete" type="button" value="删除" onclick="delectOI()" />
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
 		var aa =$("#strtime").val(strtime);
 		$("#endtime").val(endtime);
	}, 0);
	</script>
	<script>
	// 分页显示信息
	function pageFen(page) {
		if(page >= 0){
			if(page < "${pages}"){
				if("${tiaojian}" == "true"){
					window.location.href="${basePath }OrderInformation/selectBysId.action?curPage=" + page + "&s_id=${s_id }";
				}else{
					<%String strtime = (String) request.getAttribute("strtime"); String endtime = (String) request.getAttribute("endtime");%>
					var x ="<%=strtime%>";
					var y ="<%=endtime%>";
					window.location.href="${basePath }OrderInformation/selectByTime.action?strtime=" + x + "&endtime=" + y + "&sId=${s_id }&curPage=" + page;
				}
			}else{
				alert("当前是最后一页");
			}
		}else{
			alert("当前是第一页");
		}	
	}
	// 批量删除订单信息
	function delectOI(){
		var sIds = $("#sId").val();
		if (confirm('确认要删除吗？')) {
			// 获取所有选中的checked框
			var option = $(":checked");
			var checkedId = "";
			//拼接所有选中的id
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			$.ajax({
				type : "post",
				url : '${basePath }OrderInformation/delectOiCom.action',
				data : {
					"checkedId" : checkedId
				},
				dataType : "json",
				success : function(map) {
					if (map.msg == "1") {
						alert("订单删除成功！！！");
					}else{
						alert("订单删除失败！！！请刷新页面重试......");
					}
					window.location.href = "${basePath }OrderInformation/selectBysId.action?s_id=" + sIds
				}
			});
		}
	}
	</script>
</body>
</html>