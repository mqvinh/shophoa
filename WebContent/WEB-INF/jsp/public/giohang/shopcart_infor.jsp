<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Thông tin thanh toán</li>
	</ul>
	<h3>
		Thông tin thanh toán<a href="${pageContext.request.contextPath}/"
			class="btn small pull-right btn-success"><i
			class="icon-arrow-left"></i> Mua hàng </a>
	</h3>
	<hr class="soft" />
	<form action="${pageContext.request.contextPath}/gio-hang/xac-nhan"
		method="post">
		<fieldset>
			<h5>Địa chỉ giao hàng</h5>
			<div class="control-group">
				<label>Họ tên: ${ItemU.fullname}</label>
			</div>
			<div class="control-group">
				<label>Địa chỉ: ${ItemU.address}</label>
			</div>
			<hr class="soft" />
			<h5>Chọn phương thức thanh toán</h5>
			<c:if test="${param['msg'] eq 'error_pay' }">
				<div class="alert alert-block alert-error fade in">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong>Hãy chọn phương thức thanh toán</strong>
				</div>
			</c:if>
			<c:forEach items="${lItemPay}" var="Pay">
				<div class="control-group">
					<div class="controls">
						<label class="radio"> <input type="radio" name="id_pay"
							id="optionsRadios1" value="${Pay.id}"> ${Pay.name}
						</label>
					</div>
				</div>
			</c:forEach>
			<div class="control-group">
				<h5>Thông tin thêm:</h5>
				<textarea style="width: 100%" rows="5" name="info"></textarea>
			</div>
		</fieldset>
		<button type="submit" class="btn btn-small btn-danger">
			Tiếp tục <i class="icon-arrow-right"></i>
		</button>
		<a href="javaScript:window.history.back();"
			class="btn btn-small pull-right btn-danger"><i
			class="icon-arrow-left"></i>Quay lại </a>
	</form>
</div>
