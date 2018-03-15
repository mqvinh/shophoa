<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="header-main">
	<div class="header-left">
		<div class="logo-name">
			<a href="index.html">
				<h1>SHOP</h1> <!--<img id="logo" src="" alt="Logo"/>-->
			</a>
		</div>
		<!--search-box-->
		<c:if test="${donhang eq 'donhang'}">
		<div class="search-box">
			<form action="" method="post">
				<input type="text" placeholder="Nhập Id đơn hàng..." required="" name="idSeach"> 
				<input type="submit" value="" name="seach">
			</form>
		</div>
		</c:if>
		<c:if test="${nguoidung eq 'nguoidung'}">
		<div class="search-box" style="width: 250px">
			<form action="" method="post">
				<select class="form-control" name="selectS"> 
					<option value="0">--Chọn mục tìm kiếm--</option>
					<option value="1">Theo Id</option>
					<option value="2">Theo Role</option>
				</select>
				<input type="text" placeholder="..." required="true" name="textSeach" value=""> 
				<input type="submit" value="" name="textSeachbt">
			</form>
		</div>
		</c:if>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<div class="header-right">
		<div class="profile_details_left">
			<!--notifications of menu start -->
			<ul class="nofitications-dropdown">
				<li class="dropdown head-dpdn"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-bell"></i><span
						class="badge">${SumProNum}</span></a>
					<ul class="dropdown-menu">
						<li>
							<div class="notification_header">
								<h3>Bạn có ${SumProNum} hoa đã hết hàng</h3>
							</div>
						</li>
						<c:forEach items="${lItemProNum}" var="ItemProNum">
						<li><a href="${pageContext.request.contextPath}/admincp/hoa/sua/${ItemProNum.id}">
								<div class="user_img">
									<img src="${defines.FILE_PICTURE_URL}/${ItemProNum.picture}" alt="">
								</div>
								<div class="notification_desc">
									<p>${ItemProNum.name}</p>
									<p>
										<span>Kích để cập nhật!!!</span>
									</p>
								</div>
								<div class="clearfix"></div>
						</a></li>
						</c:forEach>
					</ul></li>
				
				<li class="dropdown head-dpdn"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-tasks"></i><span
						class="badge blue1">${sumEx}</span></a>
					<ul class="dropdown-menu">
						<li>
							<div class="notification_header">
								<h3>Bạn có ${sumEx} đơn đặt hàng mới</h3>
							</div>
						</li>
						<c:forEach items="${ItemExNotView}" var="ExNotView">
						<li><a href="${pageContext.request.contextPath}/admincp/don-hang/chi-tiet/${ExNotView.id}">
								<div class="notification_desc">
									<p>${ExNotView.name_user}</p>
									<p>
										<span>Kích để xem!!!</span>
									</p>
								</div>
								<div class="clearfix"></div>
						</a></li>
						</c:forEach>
					</ul></li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<!--notification menu end -->
		<%--  user: ${ ItemU.username} --%>
		<c:if test="${!(ItemU.username eq null)}">
		<div class="profile_details">
			<ul>
				<li class="dropdown profile_details_drop"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false">
						<div class="profile_img">
							<span class="prfil-img"><img style="width: 50px; height: 50px"
								src="${defines.FILE_PICTURE_URL}/${ItemU.picture}" alt="">
							</span>
							<div class="user-name">
								<p>${ItemU.fullname}</p>
								<span>${ItemU.rname}</span>
							</div>
							<i class="fa fa-angle-down lnr"></i> <i
								class="fa fa-angle-up lnr"></i>
							<div class="clearfix"></div>
						</div>
				</a>
					<ul class="dropdown-menu drp-mnu">
						<!-- <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li> -->
						<li><a
							href="${pageContext.request.contextPath}/admincp/profile"><i
								class="fa fa-user"></i> Tài khoản</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"><i
								class="fa fa-sign-out"></i>Đăng xuất</a></li>
					</ul></li>
			</ul>
		</div>
		</c:if>
		<div class="clearfix"></div>
		
	</div>
	<div class="clearfix"></div>
</div>
<!--heder end here-->
<!-- script-for sticky-nav -->
<script>
	$(document).ready(function() {
		var navoffeset = $(".header-main").offset().top;
		$(window).scroll(function() {
			var scrollpos = $(window).scrollTop();
			if (scrollpos >= navoffeset) {
				$(".header-main").addClass("fixed");
			} else {
				$(".header-main").removeClass("fixed");
			}
		});

	});
</script>
<!-- /script-for sticky-nav -->
