<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Xác nhận thanh toán</li>
	</ul>
	<h3>
		Quý khách vui lòng xác nhận lại thông tin<a
			href="${pageContext.request.contextPath}/"
			class="btn small pull-right btn-success"><i
			class="icon-arrow-left"></i> Mua hàng </a>
	</h3>
	<hr class="soft" />
	<form action="${pageContext.request.contextPath}/gio-hang/thanh-toan" method="post">
		<fieldset>
			<h5>Địa chỉ giao hàng</h5>
			<div class="control-group">
				<label>Họ tên: ${ItemU.fullname}</label>
			</div>
			<div class="control-group">
				<label>Địa chỉ: ${ItemU.address}</label>
			</div>
			<hr class="soft" />
			<h5>Sản phẩm mua</h5>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Tên hoa</th>
						<th>Hình ảnh</th>
						<th>Số lượng</th>
						<th>Đơn giá</th>
						<th>Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:set value="0" var="tongtien"></c:set>
					<c:forEach items="${lItemShop}" var="ItemShop">
						<tr>
							<td><a href="#">${ItemShop.name}</a></td>
							<td><img width="60"
								src="${defines.FILE_PICTURE_URL}/${ItemShop.picture}" alt="" /></td>
							<td><a href="#">${ItemShop.soluong }</a></td>
							<td>${ItemShop.price }VNĐ</td>
							<td>${ItemShop.price*ItemShop.soluong }VNĐ</td>
						</tr>
						<c:set value="${tongtien+ItemShop.price*ItemShop.soluong }"
							var="tongtien"></c:set>
					</c:forEach>
					<tr>
						<td colspan="4" style="text-align: right"><strong>Tổng
								tiền</td>
						<td class="label label-important" style="display: block"><strong>
								${tongtien } VNĐ </strong></td>
					</tr>
				</tbody>
			</table>
			<hr class="soft" />
			<h5>Phương thức thanh toán</h5>
			<div class="control-group">
				<div class="controls">
					<label class="radio">
						${ItemPay.name} </label>
				</div>
			</div>
			<hr class="soft" />
			<div class="control-group">
				<h5>Thông tin thêm:</h5>
				<textarea style="width: 100%" rows="5" disabled="disabled">${infor}</textarea>
			</div>
		</fieldset>
		<button type="submit" class="btn btn-small btn-danger">
			Thanh toán <i class="icon-arrow-right"></i>
		</button>
		<a href="javaScript:window.history.back();"
			class="btn btn-small pull-right btn-danger"><i
			class="icon-arrow-left"></i>Quay lại </a>
	</form>
</div>
