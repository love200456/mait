<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String picturePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	application.setAttribute("basePath", basePath);
	application.setAttribute("picturePath", picturePath);
%>

<!doctype html>
<html style="overflow-x:hidden; height:100%;">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>分类管理</title>
</head>
<body style=" height: 100%;">
	<c:if test="${judge == '1' }">
		<form action="${basePath }AdvertisingCategory/insertAC.action?levels=2" method="post" enctype="multipart/form-data">
			<div class="content">
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain11">
						<img src="../img/liezi.png" id="view11">
						<input type="file" name="file1" onchange="javascript:upload11();" id="uploader11">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input name="sId1" type="text" class="shopname1" />
						<button class="sea1" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain12">
						<img src="../img/liezi.png" id="view12">
						<input type="file" name="file2" onchange="javascript:upload12();" id="uploader12">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input name="sId2" type="text" class="shopname2" />
						<button class="sea2" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain13">
						<img src="../img/liezi.png" id="view13">
						<input type="file" name="file3" onchange="javascript:upload13();" id="uploader13">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input name="sId3" type="text" class="shopname3" />
						<button class="sea3" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain14">
						<img src="../img/liezi.png" id="view14">
						<input type="file" name="file4" onchange="javascript:upload14();" id="uploader14">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input name="sId4" type="text" class="shopname4" />
						<button class="sea4" type="button">搜索</button>
					</div>
				</div>
				<div style="text-align: center;">
					<input type="submit" value="保存" class="cfm3">
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${judge == '2' }">
		<div class="content">
			<form action="${basePath }AdvertisingCategory/updateAC.action?levels=2" method="post" enctype="multipart/form-data">
<%-- 				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain1">
						<img src="${picturePath }${selectAC.get(0).ac_picture }" id="view1">
						<input type="file" name="file5" onchange="javascript:upload21();" id="upload1">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input type="text" name="sIds1" class="shopname1" value="${selectAC.get(0).s_id }" />
						<input type="hidden" name="acId1" value="${selectAC.get(0).ac_id }" />
						<button class="sea1" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain2">
						<img src="${picturePath }${selectAC.get(1).ac_picture }" id="view2">
						<input type="file" name="file6" onchange="javascript:upload22();" id="upload2">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input type="text" name="sIds2" class="shopname2" value="${selectAC.get(1).s_id }" />
						<input type="hidden" name="acId2" value="${selectAC.get(1).ac_id }" />
						<button class="sea2" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain3">
						<img src="${picturePath }${selectAC.get(2).ac_picture }" id="view3">
						<input type="file" name="file7" onchange="javascript:upload23();" id="upload3">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input type="text" name="sIds3" class="shopname3" value="${selectAC.get(2).s_id }" />
						<input type="hidden" name="acId3" value="${selectAC.get(2).ac_id }" />
						<button class="sea3" type="button">搜索</button>
					</div>
				</div>
				<div class="advclass">
					<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
					<div class="upload_contain" id="upload_contain4">
						<img src="${picturePath }${selectAC.get(3).ac_picture }" id="view4">
						<input type="file" name="file8" onchange="javascript:upload24();" id="upload4">
					</div>
					<div class="searchshop">
						<span>店铺链接：</span>
						<input type="text" name="sIds4" class="shopname4" value="${selectAC.get(3).s_id }" />
						<input type="hidden" name="acId4" value="${selectAC.get(3).ac_id }" />
						<button class="sea4" type="button">搜索</button>
					</div>
				</div> --%>
								<c:if test="${fn:length(selectAC) < '1' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain1">
							<img src="../img/liezi.png" id="view1">
							<input type="file" name="file5" onchange="javascript:upload21();" id="upload1">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span> <input type="text" name="sIds1" class="shopname1"/>
							<input type="hidden" name="acId1"/>
							<button type="button" class="sea1">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) >= '1' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain1">
							<img src="${picturePath }${selectAC.get(0).ac_picture }" id="view1">
							<input type="file" name="file5" onchange="javascript:upload21();" id="upload1">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span> <input type="text" name="sIds1" class="shopname1" value="${selectAC.get(0).s_id }" />
							<input type="hidden" name="acId1" value="${selectAC.get(0).ac_id }" />
							<button type="button" class="sea1">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) < '2' }">
	 				<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain2">
							<img src="../img/liezi.png" id="view2">
							<input type="file" name="file6" onchange="javascript:upload22();" id="upload2">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds2" class="shopname2"/>
							<input type="hidden" name="acId2"/>
							<button type="button" class="sea2">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) >= '2' }">
	 				<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain2">
							<img src="${picturePath }${selectAC.get(1).ac_picture }" id="view2">
							<input type="file" name="file6" onchange="javascript:upload22();" id="upload2">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds2" class="shopname2" value="${selectAC.get(1).s_id }" />
							<input type="hidden" name="acId2" value="${selectAC.get(1).ac_id }" />
							<button type="button" class="sea2">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) < '3' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain3">
							<img src="../img/liezi.png" id="view3">
							<input type="file" name="file7" onchange="javascript:upload23();" id="upload3">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds3" class="shopname3"/>
							<input type="hidden" name="acId3"/>
							<button type="button" class="sea3">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) >= '3' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain3">
							<img src="${picturePath }${selectAC.get(2).ac_picture }" id="view3">
							<input type="file" name="file7" onchange="javascript:upload23();" id="upload3">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds3" class="shopname3" value="${selectAC.get(2).s_id }" />
							<input type="hidden" name="acId3" value="${selectAC.get(2).ac_id }" />
							<button type="button" class="sea3">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) < '4' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain4">
							<img src="../img/liezi.png" id="view4">
							<input type="file" name="file8" onchange="javascript:upload24();" id="upload4">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds4" class="shopname4"/>
							<input type="hidden" name="acId4"/>
							<button type="button" class="sea4">搜索</button>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(selectAC) >= '4' }">
					<div class="advclass">
						<p>&lt;请您上传比例为【宽/高=2/1】的图片&gt;</p>
						<div class="upload_contain" id="upload_contain4">
							<img src="${picturePath }${selectAC.get(3).ac_picture }" id="view4">
							<input type="file" name="file8" onchange="javascript:upload24();" id="upload4">
						</div>
						<div class="searchshop">
							<span>店铺链接：</span>
							<input type="text" name="sIds4" class="shopname4" value="${selectAC.get(3).s_id }" />
							<input type="hidden" name="acId4" value="${selectAC.get(3).ac_id }" />
							<button type="button" class="sea4">搜索</button>
						</div>
					</div>
				</c:if>
				<div style="text-align: center;">
					<input type="submit" value="保存" class="cfm3">
				</div>
			</form>
		</div>
	</c:if>
	<div class="Bomb_box">
		<img class="close" src="../img/close.png" style="position: absolute; right: 0; left: 562px; margin: auto;top: 51px;">
		<div class="userdetails2">
			<h3>搜索店铺</h3>
			<div class="down">
				<form>
					<div class="details">
						<div class=" phase5">
							<input type="text" placeholder="请输入店铺名称" id="shopname" />
							<input type="button" value="搜索" class="clsearch" />
							<input type="hidden" class="low" />
							<button type="button" class="qr">确认</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="../js/upload.js"></script>
	<script type="text/javascript" src="../js/uploader.js"></script>
	<script>
		$(".sea1").click(function() {
			$(".Bomb_box").css("display", "block");
			$(".low").val("1");
		});
		$(".sea2").click(function() {
			$(".Bomb_box").css("display", "block");
			$(".low").val("2");
		});
		$(".sea3").click(function() {
			$(".Bomb_box").css("display", "block");
			$(".low").val("3");
		});
		$(".sea4").click(function() {
			$(".Bomb_box").css("display", "block");
			$(".low").val("4");
		});
		//搜索店铺名
		$(".clsearch").click(function() {
			var sName = $("#shopname").val();//获取搜索内容
			$.ajax({
				type : "post",
				url : '${basePath }StoreBackstage/selectBysName.action',
				data : {
					"sName" : sName
				},
				dataType : "json",
				success : function(map) {
					var sId = map.csId;
					if (map.msg == "1") {
						$("#shopname").val(sId);
					} else {
						alert("店铺名输入有误......");
					}
				}
			});
		})
		$(".qr").click(function() {
			$(".Bomb_box").css("display", "none");
			if ($(".low").val() == 1) {
				var name = $("#shopname").val();
				$(".shopname1").val(name);
			} else if ($(".low").val() == 2) {
				var name = $("#shopname").val();
				$(".shopname2").val(name);
			} else if ($(".low").val() == 3) {
				var name = $("#shopname").val();
				$(".shopname3").val(name);
			} else if ($(".low").val() == 4) {
				var name = $("#shopname").val();
				$(".shopname4").val(name);
			}
		});
		$(".close").click(function(){
		  $(".Bomb_box").css("display", "none");
		})
	</script>
	<script>
		function upload21(avalue) {
			var docObj = document.getElementById("upload1");
			var imgObjPreview = document.getElementById("view1");
			if (docObj.files && docObj.files[0]) {
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '100%';
				imgObjPreview.style.height = '100%';
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("upload_contain1");
				localImagId.style.width = "200px";
				localImagId.style.height = "200px";
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'block';
				document.selection.empty();
			}
			return true;
		}
		function upload22(avalue) {
			var docObj = document.getElementById("upload2");
			var imgObjPreview = document.getElementById("view2");
			if (docObj.files && docObj.files[0]) {
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '100%';
				imgObjPreview.style.height = '100%';
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("upload_contain2");
				localImagId.style.width = "200px";
				localImagId.style.height = "200px";
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'block';
				document.selection.empty();
			}
			return true;
		}
		function upload23(avalue) {
			var docObj = document.getElementById("upload3");
			var imgObjPreview = document.getElementById("view3");
			if (docObj.files && docObj.files[0]) {
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '100%';
				imgObjPreview.style.height = '100%';
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("upload_contain3");
				localImagId.style.width = "200px";
				localImagId.style.height = "200px";
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'block';
				document.selection.empty();
			}
			return true;
		}
		function upload24(avalue) {
			var docObj = document.getElementById("upload4");
			var imgObjPreview = document.getElementById("view4");
			if (docObj.files && docObj.files[0]) {
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '100%';
				imgObjPreview.style.height = '100%';
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("upload_contain4");
				localImagId.style.width = "200px";
				localImagId.style.height = "200px";
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'block';
				document.selection.empty();
			}
			return true;
		}
	</script>
	<script>
	$(function(){
		<% String s2 = (String)request.getAttribute("msg");%>
		var s ="<%=s2 %>";
		if(s == "null" ){
		}else{
			alert(s);
		}
	});
	</script>
</body>
</html>