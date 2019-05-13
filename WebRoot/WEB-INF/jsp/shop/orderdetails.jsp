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
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
<title>店铺详情</title>
</head>
<body style="position: relative; height: 100%;">
	<div class="title clearfix">
		<a href="${basePath }StoreBackstage/jumpShopdetails.action?s_id=${s_id }"><h2 class="floatl isshop">店铺信息</h2></a>
		<a href="${basePath }OrderInformation/selectBysId.action?s_id=${s_id }"><h2 class="floatl active orderlist">订单列表</h2></a>
	</div>
	<div class="content">
		<div class="search" style="height: 63px; padding: 0;"></div>
		<div class="ordermessage">
			<div class="img_contain">
			<c:forEach items="${oidCom }" var="oc">
				<img src="${picturePath }${oc.c_first_figure }" width="49%" height="100%" />
			</c:forEach>
			</div>
			<h5>店铺名称：<span>${selectByOid.s_name }</span></h5>
			<h5>联系方式：<span>${selectByOid.s_mobile }</span></h5>
			<h5>买家名称：<span>${selectByOid.auBuyerNick }</span></h5>
			<h5>买家联系方式：<span>${selectByOid.auMobile }</span></h5>
			<h5>订单编号：<span>${selectByOid.o_number }</span></h5>
			<h5>下单时间：<span>${selectByOid.o_time }</span></h5>
			<h5>订单金额：<span>${selectByOid.o_amount }</span></h5>
			<h5>实付金额：<span>${selectByOid.amount_paid }</span></h5>
			<h6>缘分：<span>使用${selectByOid.ermanent_integral_bonus }积分</span></h6>
			<p>积分：<span>使用${selectByOid.time_limited_integration }积分</span></p>
		</div>
	</div>
</body>
</html>