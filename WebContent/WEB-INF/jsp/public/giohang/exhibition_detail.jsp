<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9 ">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath}/don-hang-pl">Đơn đặt
				hàng</a> <span class="divider">/</span></li>
		<li class="active">Chi tiết đơn hàng</li>
	</ul>
</div>
<div class="span9 " style="text-align: center;">

	<h3>Chi tiết đơn đặt hàng</h3>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên hoa</th>
				<th>Đơn giá</th>
				<th>Số lượng</th>
				<th>Thành tiền (VNĐ)</th>
			</tr>
		</thead>
		<tbody>
			<c:set value="0" var="tongtien"></c:set>
			<c:forEach items="${lItem}" var="Item">
				<tr>
					<td>${Item.id}</td>
					<td>${Item.name_product}</td>
					<td>${Item.price_product}</td>
					<td>${Item.number}</td>
					<td>${Item.number*Item.price_product}</td>
				</tr>
				<c:set value="${tongtien+Item.number*Item.price_product}"
					var="tongtien"></c:set>
			</c:forEach>
			<tr>
				<td colspan="4">Tổng tiền (VNĐ)</td>
				<td>${tongtien}</td>
			</tr>
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
