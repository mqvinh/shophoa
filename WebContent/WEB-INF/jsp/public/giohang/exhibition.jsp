<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9 ">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Đơn đặt hàng</li>
	</ul>
</div>
<div class="span9 " style="text-align: center;">

	<h3>Lịch sử mua hàng của bạn</h3>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Khách hàng</th>
				<th>Ngày tạo</th>
				<th>TT thanh toán</th>
				<th>TT kích hoạt</th>
				<th>TT gửi hàng</th>
				<th>PT thanh toán</th>
				<th>Thông tin thêm</th>
				<th>Tên KH</th>
				<th>Đại chỉ nhận</th>
				<th width="120px">Chức năng</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ItemEx}" var="Item">
				<tr>
					<td>${Item.id}</td>
					<td>${Item.name_role}</td>
					<td>${Item.date}</td>
					<td>
						<c:if test="${Item.status_pay==0}">
						<div id="ex${id}1">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png">
						</div>
						</c:if>
						<c:if test="${Item.status_pay==1}">
						<div id="ex${id}1">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png">
						</div>
						</c:if>
					</td>
					<td>
						<c:if test="${Item.status_active==0}">
						<div id="ex${id}2">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png">
						</div>
						</c:if>
						<c:if test="${Item.status_active==1}">
						<div id="ex${id}2">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png">
						</div>
						</c:if>
					</td>
					<td>
						<c:if test="${Item.status_ship==0}">
						<div id="ex${id}3">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png">
						</div>
						</c:if>
						<c:if test="${Item.status_ship==1}">
						<div id="ex${id}3">
							<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png">
						</div>
						</c:if>
					</td>
					<td>${Item.name_pay}</td>
					<td>${Item.more_infor}</td>
					<td>${Item.name_user}</td>
					<td>${ItemU.address}</td>
					<td><a href="${pageContext.request.contextPath}/don-hang-pl/chi-tiet-${Item.id}" class="btn" style="padding: 4px; margin: 0px">Chi
							tiết</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">
		<ul>
			<c:if test="${page>1}">
				<c:set value="?p=${page-1}" var="urlPrevious"></c:set>
			</c:if>
			<c:if test="${page==1}">
				<c:set value="#" var="urlPrevious"></c:set>
			</c:if>
			<li><a href="${urlPrevious}">&lsaquo;</a></li>

			<c:forEach begin="1" end="${sumpage }" var="i">
				<c:set value="?p=${i}" var="urlPage"></c:set>
				<c:if test="${page==i}">
					<c:set value="class='active'" var="cl"></c:set>
				</c:if>
				<c:if test="${page!=i}">
					<c:set value="" var="cl"></c:set>
				</c:if>
				<li ${cl }><a href="${urlPage}">${i}</a></li>
			</c:forEach>

			<c:if test="${page<sumpage}">
				<c:set value="?p=${page+1}" var="urlNext"></c:set>
			</c:if>
			<c:if test="${page==sumpage}">
				<c:set value="#" var="urlNext"></c:set>
			</c:if>
			<li><a href="#">&rsaquo;</a></li>
		</ul>
	</div>

</div>
