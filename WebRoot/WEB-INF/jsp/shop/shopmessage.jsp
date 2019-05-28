<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<title>店铺详情</title>
<script>
function updateIntegral(){
	var s_id = $("#sId").val();
	var integral_setting = $("#integral_setting").val();
	var offset_setting = $("#offset_setting").val();
	var tocIds = $("#tocId").val();
	var ttcIds = $("#ttcId").val();
	var deductiblePercentage = $("#deductible_percentage").val();
	$.ajax({
		type : "post",
		url : '${basePath }StoreBackstage/updayeStoreIntegral.action',
		data : {
			"s_id" : s_id,
			"integral_setting" : 0.1,
			"offset_setting" : 1.0,
			"toc_id" : tocIds,
			"ttc_id" : ttcIds,
			"deductible_percentage" : deductiblePercentage
		},
		dataType : "json",
		success : function(map) {
			var sId = map.s_id;
			if (map.msg == "1") {
				alert("设置成功!!");
			}else{
				alert("设置有误,请重新设置......");
			}
			window.location.href = "${basePath }StoreBackstage/selectBysIdPage.action?s_id="+sId;
		}
	});
}
</script>
<script>
// 二级联动
$(function() {
	$(".selec1").change(function() {
		var aa =$(this).val();
		$.ajax({
			//改为接口全路径
			url : '${basePath }TitleTwoClassMobile/selectByToid.action',
			data : {
				tocId : aa
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				var selectTwo=result.selectTwo;
				//相册下拉列表添加数据 
				var sid = document.getElementById("ttcId");
				//清空下拉列表
				sid.length = 1;
				for (var i = 0; i < selectTwo.length; i++) {
					//添加option---先显示的值，后value值
					sid.add(new Option(selectTwo[i].ttc_name, selectTwo[i].ttc_id));
				}
			}
		});
	});
});
</script>
</head>
<body style="position: relative; height: 100%;">
	<div class="shopmessage">
		<div class="img_contain">
			<img src="${picturePath }${bysId.picture }" alt="${picturePath }${bysId.picture }" />
		</div>
		<h5>店铺名称：<span>${bysId.s_name }</span></h5>
		<h5>联系方式：<span>${bysId.s_mobile }</span></h5>
		<h5>开店时间：<span>${bysId.open_time }</span></h5>
		<h5>店铺描述：<a title="${bysId.describe }">${bysId.describe }</a></h5>
		<h5>店铺地址：<a title="${bysId.s_address }">${bysId.s_address }</a></h5>
<!-- 	
		<h5 style="color:#3d8dd9">积分设置：<a>0.1</a></h5>
		<h5 style="color:#3d8dd9">抵扣设置：<a>1.0</a></h5>
 -->
		<h5 style="color:#3d8dd9">抵扣比率：<a>${bysId.deductible_percentage}%</a></h5>
		<!-- 
			<input type="hidden" id="deductible_percentage" value="0.5"/>
		 -->
		<input type="hidden" id="sId" value="${bysId.s_id }"/>
		<h5><p class="floatl">分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：</p>
			<select class="floatl selec1" style="display:block;" name="tocId" id="tocId">
				<option value="0">请选择</option>
				<c:forEach items="${selectOne }" var="to">
					<c:if test="${to.toc_id eq bysId.toc_id}">
						<option value="${to.toc_id }" selected>${to.toc_name }</option>
					</c:if>
					<c:if test="${to.toc_id ne bysId.toc_id}">
						<option value="${to.toc_id }">${to.toc_name }</option>
					</c:if>
				</c:forEach>
			</select> <select class="floatl selec2" style="display:block;" name="ttcId" id="ttcId">
				<option value="0">请选择</option>
				<c:forEach items="${selectTwo }" var="slt" varStatus="status">
					<c:if test="${slt.ttc_id eq bysId.ttc_id}">
						<option value="${slt.ttc_id}" selected>${slt.ttc_name}</option>
					</c:if>
					<c:if test="${slt.ttc_id ne bysId.ttc_id}">
						<option value="${slt.ttc_id}">${slt.ttc_name}</option>
					</c:if>
				</c:forEach>
			</select> <input type="button" onclick="updateIntegral()" value="确定" />
		</h5>
	</div>
</body>
</html>