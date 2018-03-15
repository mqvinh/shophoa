<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!--Less styles -->
<!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->

<script src="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/jquery.js" type="text/javascript"></script>
	<script src="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/google-code-prettify/prettify.js"></script>
	
<%-- <script src="${defines.ADMIN_TEMPLATE_URL}/js/jquery-2.1.1.min.js"></script> --%>
<script src="${defines.ADMIN_TEMPLATE_URL}/js/validation/dist/jquery.validate.min.js"></script>
<script src="${defines.ADMIN_TEMPLATE_URL}/js/validation/dist/additional-methods.js"></script>
<script src="${defines.ADMIN_TEMPLATE_URL}/js/javascript.js"></script>
	<script src="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/bootshop.js"></script>
    <script src="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/jquery.lightbox-0.5.js"></script>


<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/bootshop/bootstrap.min.css"
	media="screen" />
<link href="${defines.SHOPHOA_TEMPLATE_URL}/themes/css/base.css"
	rel="stylesheet" media="screen" />
<!-- Bootstrap style responsive -->
<link
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/css/bootstrap-responsive.min.css"
	rel="stylesheet" />
<link href="${defines.SHOPHOA_TEMPLATE_URL}/themes/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->
<link
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/js/google-code-prettify/prettify.css"
	rel="stylesheet" />
<!-- fav and touch icons -->
<link rel="shortcut icon"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css" id="enject"></style>
</head>
<body>
	<div id="header">
		<div class="container">
			<div id="welcomeLine" class="row">
				<div class="span6">
					Welcome!<strong> User</strong>
				</div>
				<div class="span6">
					<div id="mycartTop">
						<div class="pull-right">
							<span class="btn btn-mini">${sumMoney} VNĐ</span> <a
								href="${pageContext.request.contextPath}/gio-hang"> <span
								class="btn btn-mini btn-primary"> <i
									class="icon-shopping-cart icon-white"></i> [ ${sumProShop} ]
									sản phẩm trong shop của bạn!!!
							</span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<!-- Navbar ================================================== -->
			<div id="logoArea" class="navbar">
				<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
					class="btn btn-navbar"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="navbar-inner">
					<a class="brand" href="${pageContext.request.contextPath}/"><img
						src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/logo.png"
						alt="Bootsshop" /></a>
					<form class="form-inline navbar-search" method="post"
						action="${pageContext.request.contextPath}/seach">
						<input value="${textSeach}" id="" class="" type="text" name="textSeach" placeholder="Nhập tên hoa"/> <select name="lid"
							class="srchTxt" >
							<option value="1">Loại hoa</option>
							<option value="2">Kiểu hoa</option>
						</select>
						<button type="submit" id="submitButton" class="btn btn-primary">Tìm</button>
					</form>
					<c:set value="class='active'" var="atindex"></c:set>
					<c:if test="${index eq 'index' }">
						<c:set value="class='active'" var="atindex"></c:set>
					</c:if>
					<c:if test="${index eq 'gioithieu' }">
						<c:set value="class='active'" var="atgioithieu"></c:set>
						<c:set value="" var="atindex"></c:set>
					</c:if>
					<c:if test="${index eq 'lienhe' }">
						<c:set value="class='active'" var="atlienhe"></c:set>
						<c:set value="" var="atindex"></c:set>
					</c:if>
					<ul id="topMenu" class="nav navbar-default" style="margin-left: 5px">
						<li ${atindex}><a href="${pageContext.request.contextPath}/">Trang
								chủ</a></li>
						<li ${atgioithieu}><a
							href="${pageContext.request.contextPath}/gioi-thieu">Giới
								thiệu</a></li>
						<li ${atlienhe}><a
							href="${pageContext.request.contextPath}/lien-he">Liên hệ</a></li>
						<c:if test="${(ItemU==null) || (ItemU.role==4)}">
							<li class=""><a
								href="${pageContext.request.contextPath}/dang-nhap-pl"
								style="padding-right: 0"><span
									class="btn btn-large btn-success">Đăng Nhập</span></a></li>
						</c:if>
						<c:if test="${(ItemU!=null) && (ItemU.role!=4)}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Tài khoản <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="${pageContext.request.contextPath}/profile-pl">Thông tin tài khoản</a></li>
									<li><a href="${pageContext.request.contextPath}/don-hang-pl">Đơn đặt hàng</a></li>
									<li><a href="${pageContext.request.contextPath}/dang-xuat-pl">Thoát</a></li>
								</ul></li>
						</c:if>

					</ul>
				</div>
			</div>
		</div>
	</div>