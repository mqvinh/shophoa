<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="sidebar-menu">
	<div class="logo">
		<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span>
		</a> <a href="#"> <span id="logo"></span> <!--<img id="logo" src="" alt="Logo"/>-->
		</a>
	</div>
	<div class="menu">
		<ul id="menu">
			<li id="menu-home"><a
				href="${pageContext.request.contextPath}/admincp"><i
					class="fa fa-home"></i><span>Trang chủ</span></a></li>
			<li id="menu-home"><a href="javascript: void(0)"><i
					class="fa fa-asterisk"></i><span>Sản phẩm</span><span
					class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
					<li id="menu-academico-avaliacoes"><a
						href="${pageContext.request.contextPath}/admincp/hoa">Hoa</a></li>
					<li id="menu-academico-boletim"><a
						href="${pageContext.request.contextPath}/admincp/kieu-hoa">Kiểu
							hoa</a></li>
					<li id="menu-academico-boletim"><a
						href="${pageContext.request.contextPath}/admincp/loai-hoa">Loại
							hoa</a></li>
				</ul></li>
			<li><a
				href="${pageContext.request.contextPath}/admincp/don-hang"><i
					class="fa fa-pencil"></i><span>Đơn hàng</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/admincp/thanh-toan"><i
					class="fa fa-usd"></i><span>Thanh toán</span></a></li>
			<%-- <li><a href="javascript: void(0)"><i class="fa fa-envelope"></i><span>Hộp
						thư</span><span class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
					<li id="menu-academico-avaliacoes"><a
						href="${pageContext.request.contextPath}/admincp/hop-thu">Thư</a></li>
					<li id="menu-academico-boletim"><a
						href="${pageContext.request.contextPath}/admincp/hop-thu/gui">Soạn
							thư</a></li>
				</ul></li> --%>
			<li><a href="javascript: void(0)"><i class="fa fa-user"></i><span>Người
						dùng</span><span class="fa fa-angle-right" style="float: right"></span></a>
				<ul id="menu-academico-sub">
					<li id="menu-academico-avaliacoes"><a
						href="${pageContext.request.contextPath}/admincp/nguoi-dung">Người
							dùng</a></li>
					<li id="menu-academico-boletim"><a
						href="${pageContext.request.contextPath}/admincp/nguoi-dung/cap-bac">Cấp
							bậc</a></li>
				</ul></li>
			<li><a
				href="${pageContext.request.contextPath}/admincp/gioi-thieu"><i
					class="fa fa-flash"></i><span>Giới thiệu</span></a></li>
			<li><a href="${pageContext.request.contextPath}/admincp/lien-he"><i
					class="fa fa-phone"></i><span>Liên hệ</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/admincp/quang-cao"><i
					class="fa fa-info"></i><span>Quảng cáo</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/admincp/slide"><i
					class="fa fa-film"></i><span>Slide</span></a></li>
		</ul>
	</div>
</div>
<div class="clearfix"></div>
</div>
<!--slide bar menu end here-->
<script>
	var toggle = true;

	$(".sidebar-icon").click(
			function() {
				if (toggle) {
					$(".page-container").addClass("sidebar-collapsed")
							.removeClass("sidebar-collapsed-back");
					$("#menu span").css({
						"position" : "absolute"
					});
				} else {
					$(".page-container").removeClass("sidebar-collapsed")
							.addClass("sidebar-collapsed-back");
					setTimeout(function() {
						$("#menu span").css({
							"position" : "relative"
						});
					}, 400);
				}
				toggle = !toggle;
			});
</script>
<!--scrolling js-->
<script src="${defines.ADMIN_TEMPLATE_URL}/js/jquery.nicescroll.js"></script>
<script src="${defines.ADMIN_TEMPLATE_URL}/js/scripts.js"></script>
<!--//scrolling js-->
<script src="${defines.ADMIN_TEMPLATE_URL}/js/bootstrap.js">
	
</script>
<!-- mother grid end here-->
</body>
</html>
