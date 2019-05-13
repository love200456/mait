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
<script type="text/javascript" src="../js/jquery.js"></script>
<title>积分管理</title>
</head>
<style>
 .cfm{margin-top:0;}
 .Bomb_box4{
	position: fixed;
	height: 100%;
	width: 100%;
	top: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.5);
	display: none;
	overflow-y: auto;
	z-index: 10;
}
</style>
<body>
	<div class="title clearfix">
		<h2 class="floatl active">积分列表</h2>
	</div>
	<div class="content">
		<form action="${basePath }AverageUser/selectLikeLP.action" method="post">
			<div class="search">
				<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入手机号"> <input type="submit" value="搜索">
			</div>
		</form>
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选  <input type="checkbox" onclick="allcheck(this)"/></th>
					<th>用户名</th>
					<th>联系方式</th>
					<th>现金积分(永久积分)</th>
					<th>生活费积分(限时积分)</th>
					<th>获得积分详情</th>
					<th>备注</th>
				</tr>
				<c:forEach items="${list }" var="lp" varStatus="l">
					<tr>
						<td>${l.index + 1 }
						<input type="hidden" class="inid" id="id" value="${lp.auId }" />
						</td>
						<td><input type="checkbox" value="${lp.auId }" />
						</td>
						<td>${lp.auBuyerNick }</td>
						<td>${lp.auMobile }</td>
						<td>${lp.permanent_points }</td>
						<td>${lp.limit_integral }</td>
						<td><input class="look" type="button" value="查看" />
						</td>
						<td><input type="text" class="beizhu" value="${lp.integral_remark }" onchange="gai(this)"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<footer class="clearfix">
			<div class="page floatl">
				<span class="hui" onclick="pageFen(${curPage-1 })">&lt;</span>
				<span> ${curPage+1 } </span>
				<span class="spcurr" onclick="pageFen(${curPage+1 })">&gt;</span>
				<em>共 ${pages } 页</em>
			</div>
			<div class="btnlist">
				<input class="give" type="button" id="give_permanent" value="送积分" />
				<input class="give" type="button" id="give_permanent_new" value="新送积分" />
					<c:if test="${sessionScope.saRole == '0' }">
						<input class="give" type="button" id="give_limit" value="送缘分积分" />
					</c:if>
			</div>
		</footer>
	</div>
	<div class="Bomb_box">
		<img class="close" id="close_box" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>送积分</h3>
			<div class="down">
				<div class="details">
					<div class=" phase3">
						<p>请输入积分金额：</p>
						<input id="permanent" type="text" class="intetemp" value="" />
					</div>
					<div class=" phase3">
						<p>请输入积分有效期：</p>
						<input id="term" type="text" class="intetime" value="0000-00-00" />
					</div>
				</div>
				<input type="button" class="confirm" id="confirm_permanent" value="确定" />
			</div>
		</div>
	</div>
		
	<div class="Bomb_box2">
		<img class="close" id="close_box2" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>
				<button class="tempdiv">现金</button>
				<button class="everdiv">生活费</button>
			</h3>
			<div class="inter">
				<div class="temporary"></div>
				<div class="forever" style="display: none;"></div>
			</div>
		</div>
	</div>
	<div class="Bomb_box3">
		<img class="close" id="close_box3" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>送缘分积分</h3>
			<div class="down">
				<div class="details">
					<div class=" phase3">
						<p>请输入缘分金额：</p>
						<input id="limit" type="text" class="inteforever" value="" />
					</div>
				</div>
				<input type="button" class="confirm" id="confirm_limit" value="确定" />
			</div>
		</div>
	</div>
	
	<div class="Bomb_box4">
		<img class="close" id="close_box4" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails">
			<h3>送积分新</h3>
			<div class="down">
				<div class="details">
					<div class=" phase3">
						<p>现金积分金额：</p>
						<input id="cash" type="text" class="intetemp" value="" />
					</div>
					<div class=" phase3">
						<p>生活费积分金额：</p>
						<input id="expenses" type="text" class="intetemp" value="" />
					</div>
				</div>
				<input type="button" class="confirm" id="confirm_permanent_new" value="确定" />
			</div>
		</div>
	</div>
	
	<input type="hidden" class="hiddenid" />
	<script>
	/*获取备注 */
	    function gai(obj)
	   {
	     var a = obj.value;
	     var auId = $(obj).parent().siblings().find("input[type=checkbox]").val();
	     $.ajax({
			type : "post",
			url : '${basePath }AverageUser/modifyIntegralRemarks.action',
			data : {
				"auId" : auId,
				"integralremarks" : a
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("添加备注成功！！！");
				}else{
					alert("添加备注失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }AverageUser/selectauLP.action"
			}
		});
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
	$(function(){
		$("#give_permanent").click(function() {
			$(".Bomb_box").css("display", "block");
		});
		$("#give_limit").click(function() {
			$(".Bomb_box3").css("display", "block");
		});
		// 批量赠送积分
		$("#confirm_permanent").click(function() {
			var option = $(":checked");
			var checkedId = "";
			//拼接所有选中的id
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			var permanent = $("#permanent").val();
			var term = $("#term").val();
			var limit = 0;
			if(permanent != ""){
				if(term != ""){
//					if(limit != ""){
						$(".Bomb_box").css("display", "none");
						$.ajax({
							type : "post",
							url : '${basePath }PerInteStatistics/insertPL.action',
							data : {
								"checkedId" : checkedId,
								"pis_get" : limit,
								"lis_get" : permanent,
								"lis_term" : term
							},
							dataType : "json",
							success : function(map) {
								if (map.msg == "1") {
									alert("积分赠送成功！！！");
								}else if(map.msg == "2"){
									alert("积分赠送失败！！！请刷新页面重试......");
								}else if(map.msg == "3"){
									alert("缘分赠送失败！！！请刷新页面重试......");
								}else{
									alert("积分赠送失败！！！请刷新页面重试......");
								}
								window.location.href = "${basePath }AverageUser/selectauLP.action"
							}
						});
//					}else{
//						alert("缘分有效期不能为空......");
//					}
				}else{
					alert("积分有效期不能为空......");
				}
			}else{
				alert("积分不能为空......");
			}
		});
		
		$("#give_permanent_new").click(function() {
			var option = $(":checked");
			var checkedId = "";
			//拼接所有选中的id
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			
			if(checkedId==""){
				alert("请选择用户!");
				return;
			}
			
			$(".Bomb_box4").css("display", "block");
		});
		// 批量赠送积分
		$("#confirm_permanent_new").click(function() {
			var option = $(":checked");
			var checkedId = "";
			//拼接所有选中的id
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			
			if(checkedId==""){
				alert("请选择用户!");
				return;
			}
			var cash = $("#cash").val();
			var expenses = $("#expenses").val();
			if(cash=="" || !$.isNumeric(cash)){
				alert("请输入现金积分金额!");
				return;
			}
			if(expenses=="" || !$.isNumeric(expenses)){
				alert("请输入消费方式积分金额!");
				return;
			}
			$.ajax({
				type : "post",
				url : '${basePath }PerInteStatistics/insertPLN.action',
				data : {
					"checkedId" : checkedId,
					"cash" : cash,
					"expenses" : expenses
				},
				dataType : "json",
				success : function(map) {
					if (map.msg == "1") {
							alert("积分赠送成功！！！");
					}else if(map.msg == "2"){
							alert("积分赠送失败！！！请刷新页面重试......");
					}else if(map.msg == "3"){
							alert("缘分赠送失败！！！请刷新页面重试......");
					}else{
							alert("积分赠送失败！！！请刷新页面重试......");
					}
						window.location.href = "${basePath }AverageUser/selectauLP.action"
				}
			});
//					
		});
		
		
		
		// 批量赠送缘分积分
		$("#confirm_limit").click(function() {
			var option = $(":checked");
			var checkedId = "";
			//拼接所有选中的id
			for ( var i = 0, len = option.length; i < len; i++) {
				checkedId += option[i].value + ",";
			}
			var permanent = 0;
			var term = "0000-00-00";
			var limit = $("#limit").val();
//			if(permanent != ""){
				if(term != ""){
					if(limit != ""){
						$(".Bomb_box3").css("display", "none");
						$.ajax({
							type : "post",
							url : '${basePath }PerInteStatistics/insertPL.action',
							data : {
								"checkedId" : checkedId,
								"pis_get" : limit,
								"lis_get" : permanent,
								"lis_term" : term
							},
							dataType : "json",
							success : function(map) {
								if (map.msg == "1") {
									alert("积分赠送成功！！！");
								}else if(map.msg == "2"){
									alert("积分赠送失败！！！请刷新页面重试......");
								}else if(map.msg == "3"){
									alert("缘分赠送失败！！！请刷新页面重试......");
								}else{
									alert("积分赠送失败！！！请刷新页面重试......");
								}
								window.location.href = "${basePath }AverageUser/selectauLP.action"
							}
						});
					}else{
						alert("缘分有效期不能为空......");
					}
				}else{
					alert("积分有效期不能为空......");
				}
//			}else{
//				alert("积分不能为空......");
//			}
		});
		$("#close_box").click(function() {
			$(".Bomb_box").css("display", "none");
		});
		$("#close_box2").click(function() {
			$(".Bomb_box2").css("display", "none");
		});
		$("#close_box3").click(function() {
			$(".Bomb_box3").css("display", "none");
		});
		
		$("#close_box4").click(function() {
			$(".Bomb_box4").css("display", "none");
		});
		// 此为查看用户限时积分事件
		$(".look").click(function aa() {
			var inid = $(this).parent().siblings().find('.inid').val();
			$(".hiddenid").val(inid);
			$.ajax({
				type : "post",
				url : '${basePath }LimInteStatisties/selectByLI.action',
				data : {
					"auId" : inid
				},
				dataType : "json",
				success : function(rus) {
					var pa = rus.msg;
					if(pa != "1"){
						var l = rus.permanent;
						var ex = rus.exist;
						var temporary = "";
						if(ex == "0"){
							temporary += "<div class='tit'><div><h5><b>"+l+"</b>分</h5><p>剩余积分</p></div></div><div class='tempdetails temp2'>";
							var list = rus.selectByauId;
							if(list.length>0){
								for(var i=0 ; i<list.length ; i++){
									if(list[i].s_name == "无"){
										temporary += "<div class='clearfix templist'><div class='floatl'><p>本日获得积分：<span>+"+list[i].lis_get+"</span>分</p></div><div class='floatr'>"+list[i].lis_time+"</div></div>";
									}else{
										temporary += "<div class='clearfix templist'><div class='floatl'><p>消费店铺： "+list[i].s_name+"</p><p>购买商品："+list[i].c_name+"</p><p>消费积分：<span>-"+list[i].lis_consumption+"</span>分</p></div><div class='floatr'>"+list[i].lis_time+"</div></div>";
									}
								}
								temporary += "</div><input type='button' class='cfm' value='确定' />";
							}
						}else{
							temporary += "<div class='tit'><div><h5><b>0</b>分</h5><p>剩余积分</p></div></div><div class='tempdetails temp2'>";
							temporary += "</div><input type='button' class='cfm' value='确定' />";
						}
						$(".temporary").html(temporary);
						$(".temporary").css("display", "block");
			            $(".forever").css("display", "none");
			            $(".tempdiv").css("background", "#fff");
			            $(".tempdiv").css("color", "#3d8cda");
		            	$(".everdiv").css("background", "#3d8cda");
			            $(".everdiv").css("color", "#fff");
						$(".Bomb_box2").css("display", "block");
						
					}else{
						alert("该用户暂无积分使用信息!!");
					}
				}
			});
		});
		$(".cfm").live("click",function(){
			$(".Bomb_box2").css("display", "none");
		});
		$(".close").click(function() {
			$(".Bomb_box2").css("display", "none");
		});
		// 限时积分点击事件
		$(".tempdiv").click(function() {
			$(".temporary").css("display", "block");
			$(".forever").css("display", "none");
			$(this).css("background", "#fff");
			$(this).css("color", "#3d8cda");
			$(".everdiv").css("background", "#3d8cda");
			$(".everdiv").css("color", "#fff");
		});
		$(".everdiv").click(function() {// 永久积分点击事件
			var auIds = $(".hiddenid").val();
			$.ajax({
				type : "post",
				url : '${basePath }PerInteStatistics/selectByauIdPIxin.action',
				data : {
					"auId" : auIds
				},
				dataType : "json",
				success : function(rus) {
					var pa = rus.msg;
					if(pa != "1"){
						var l = rus.limit;
						var forever = "";
						forever += "<div class='tit'><div><h5><b>"+l+"</b>分</h5><p>剩余缘分</p></div></div><div class='tempdetails'>"
						var list = rus.selectByauId;
						if(list.length>0){
							for(var i=0 ; i<list.length ; i++){
								if(list[i].s_name == "无"){
									forever += "<div class='clearfix templist'><div class='floatl'><p>本日获得缘分：<span>+"+list[i].pis_get+"</span>分</p></div><div class='floatr'>"+list[i].pis_time+"</div></div>";
								}else{
									forever += "<div class='clearfix templist'><div class='floatl'><p>本日获得缘分：<span>+"+list[i].pis_get+"</span>分</p><p>消费店铺： "+list[i].s_name+"</p><p>购买商品："+list[i].c_name+"</p><p>消费缘分：<span>-"+list[i].pis_consumption+"</span>分</p></div><div class='floatr'>"+list[i].pis_time+"</div></div>";
								}
							}
							forever += "</div><button class='cfm'>确定</button>";
						}
						$(".forever").html(forever);
						$(".forever").css("display", "block");
						$(".temporary").css("display", "none");
						$(".everdiv").css("background", "#fff")
						$(".everdiv").css("color", "#3d8cda");
						$(".tempdiv").css("background", "#3d8cda")
						$(".tempdiv").css("color", "#fff");
					}else{
						alert("该用户暂无缘分使用信息!!");
					}
				}
			});
		});
	})
	</script>
	<script>
		function pageFen(page) {
			if (page >= 0) {
				if (page < "${pages}") {
					if ("${tiaojian}" == "true") {
						window.location.href = "${basePath }AverageUser/selectauLP.action?curPage=" + page;
					} else {
						window.location.href = "${basePath }AverageUser/selectLikeLP.action?searchVal=${searchVal }&curPage=" + page;
					}
				} else {
					alert("当前是最后一页");
				}
			} else {
				alert("当前是第一页");
			}
		}
		/*function delectInte(){
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
					url : '${basePath }PerInteStatistics/delectInte.action',
					data : {
						"checkedId" : checkedId
					},
					dataType : "json",
					success : function(map) {
						if (map.msg == "1") {
							alert("删除成功！！！");
							window.location.href = "${basePath }AverageUser/selectauLP.action";
						}else{
							alert("删除失败！！请刷新页面重试......");
							window.location.href = "${basePath }AverageUser/selectauLP.action";
						}
					}
				});
			}
		}*/
	</script>
</body>
</html>