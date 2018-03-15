<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Chi tiết đơn hàng có mã ${id_exhibition}</span>
		</div>
		<!-- Table -->
		<div class="mailbox-border" style="text-align: center;">
			<div class="mail-toolbar clearfix" style="text-align: left;">
				<div class="float-left">
					<a href="${pageContext.request.contextPath}/admincp/don-hang" class="btn btn_1 btn-default">
						<i class="fa fa-warning"></i> Quay lại
					</a>
					<a href="${pageContext.request.contextPath}/admincp/don-hang/xoa/${id_exhibition}" class="btn btn_1 btn-default">
						<i class="fa fa-trash"></i> Xóa đơn hàng này
					</a>
					<div class="clearfix"></div>
				</div>
			</div>
			<table class="table tab-border" style="text-align: left;" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<th>ID</th>
						<th>Tên hoa</th>
						<th>Đơn giá</th>
						<th>Số lượng</th>
						<th>Thành tiền (VNĐ)</th>
					</tr>
					<c:set value="0" var="tt"></c:set>
					<c:forEach items="${lItem}" var="Item">
						<c:set value="${Item.id}" var="id_Item"></c:set>
					<tr>
						<td>${id_Item}</td>
						<td>${Item.name_product}</td>
						<td>${Item.price_product}</td>
						<td>${Item.number}</td>
						<td>${Item.price_product*Item.number}</td>
						<c:set value="${tt+Item.price_product*Item.number}" var="tt"></c:set>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="4">Tổng tiền (VNĐ)</td>
						<td>${tt}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
