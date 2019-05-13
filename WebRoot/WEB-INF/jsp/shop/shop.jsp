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
<title>店铺管理</title>
<script>
function pageFen(page) {
	if(page >= 0){
		if(page < "${pages}"){
			if("${tiaojian}" == "true"){
				window.location.href="${basePath }StoreBackstage/selectAllPage.action?curPage=" + page;
			}else{
				window.location.href="${basePath }StoreBackstage/selectStoreLike.action?searchVal=${searchVal}&curPage=" + page;
			}
		}else{
			alert("当前是最后一页");
		}
	}else{
		alert("当前是第一页");
	}	
}
function delectSt(){
	if (confirm('确认要删除吗？')) {
		var option = $(":checked");
		var checkedId = "";
		for ( var i = 0, len = option.length; i < len; i++) {
			checkedId += option[i].value + ",";
		}
		$.ajax({
			type : "post",
			url : '${basePath }BatchDelete/delectSto.action',
			data : {
				"checkedId" : checkedId
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("删除店铺成功！！！");
				}else{
					alert("删除店铺失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }StoreBackstage/selectAllPage.action"
			}
		});
	}
}
</script>
</head>
<body style="min-width:1000px">
	<div class="title clearfix">
		<h2 class="floatl active">店铺列表</h2>
	</div>
	<div class="content">
	<form action="${basePath }StoreBackstage/selectStoreLike.action" method="post" >
		<div class="search">
			<input class="see" type="text" name="searchVal" id="searchVal" placeholder="请输入店铺名称">
			<input type="submit" value="搜索">
		</div>
	</form>
		<div class="tab">
			<table>
				<tr>
					<th>序号</th>
					<th>全选  <input type="checkbox" value="0" onclick="allcheck(this)"/></th>
					<th>店铺名称</th>
					<th>联系方式</th>
					<th>店铺地址</th>
					<th>支付二维码</th>
					<th>备注</th>
					<th>店铺详情</th>
				</tr>
				<c:forEach items="${list }" var="s" varStatus="l">
					<tr>
						<td>${l.index+1 }</td>
						<td><input type="checkbox" value="${s.s_id }" />
						</td>
						<td>${s.s_name }</td>
						<td>${s.s_mobile }</td>
						<td><p class="shop-address">${s.s_address }</p></td>
						<td><button class="erweima" onclick="winpop(${s.s_id })">查看</button>
							<img style="display: none;" src="${s.payment_code }">
						</td>
						<td><input type="text" class="beizhu" value="${s.shop_note }" onchange="gai(this)"/></td>
						<td><a href="${basePath }StoreBackstage/jumpShopdetails.action?s_id=${s.s_id }" class="shopde">查看详情</a>
						</td>
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
				<input class="delete" type="button" value="删除" onclick="delectSt()" />
			</div>
		</footer>
	</div>
	<script>
	/*获取备注 */
	    function gai(obj)
	   {
	     var a = obj.value;
	     var sId = $(obj).parent().siblings().find("input[type=checkbox]").val();
	     $.ajax({
			type : "post",
			url : '${basePath }StoreBackstage/modifyShopRemarksById.action',
			data : {
				"s_id" : sId,
				"shop_note" : a
			},
			dataType : "json",
			success : function(map) {
				if (map.msg == "1") {
					alert("添加备注成功！！！");
				}else{
					alert("添加备注失败！！！请刷新页面重试......");
				}
				window.location.href = "${basePath }StoreBackstage/selectAllPage.action"
			}
		});
	   }
 	/*弹出小窗口*/
 function winpop(s){
 	window.open("${basePath }StoreBackstage/selectOR.action?s_id="+s+"","","top=100,left=100,width=300,height=200");
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