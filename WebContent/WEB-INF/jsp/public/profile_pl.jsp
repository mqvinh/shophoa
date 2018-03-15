<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Thông tin tài khoản</li>
	</ul>
	<h3>Cập nhật thông tin tài khoản của bạn</h3>
	<div class="well">
		<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
		<form class="form-horizontal" 
			action="${pageContext.request.contextPath}/profile-pl" method="post"
			enctype="multipart/form-data">
			<c:if test="${param['msg'] eq 'sua'}">
				<h4 class="alert alert-success">Cập nhật thành công!!!</h4>
			</c:if>
			<c:if test="${param['msg'] eq 'sua_loi'}">
				<div class="alert alert-danger">Cập nhật thất bại!!!</div>
			</c:if>
			<div class="control-group">
				<label class="control-label" for="inputFname1">Tên đầy đủ
					của bạn: <sup>*</sup>
				</label>
				<div class="controls">
					<input value="${ItemU.fullname}" type="text" id="inputFname1" 
						placeholder="Tên đầy đủ của bạn" name="fullname">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Ảnh đại diện: <sup>*</sup>
				</label>
				<div class="controls">
					<p>
						<img style="width: 200px; height: 200px"
							src="${defines.FILE_PICTURE_URL}/${ItemU.picture}" alt="..."
							class="img-thumbnail">
					</p>
					<button type="submit" class="btn btn-default">Xóa ảnh</button>
					<br> <input type="file" class="filestyle" name="picturem"
						data-buttonText="Select a File">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputLnam">Tên đăng nhập <sup>*</sup></label>
				<div class="controls">
					<input disabled="disabled" value="${ItemU.username}" type="text"
						id="inputLnam" placeholder="Tên đăng nhập" name="username">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="Password">Mật khẩu <sup>*</sup></label>
				<div class="controls">
					<input type="password" id="Password" placeholder="Mật khẩu mới"
						name="password">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="rPassword">Nhập lại mật
					khẩu <sup>*</sup>
				</label>
				<div class="controls">
					<input type="password" id="rPassword"
						placeholder="Nhập lại mật khẩu mới" name="rpassword">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input_email">Email <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="input_email" placeholder="Email"
						value="${ItemU.email}" name="email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input_email">Địa chỉ <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="input_email" placeholder="Địa chỉ"
						value="${ItemU.address}" name="address">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Số điện thoại <sup>*</sup></label>
				<div class="controls">
					<input value="${ItemU.phone}" type="text" id="phone"
						placeholder="Số điện thoại" name="phone">
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<input class="btn btn-large btn-success" type="submit"
						value="Cập nhật" /> <a class="btn btn-large btn-success"
						href="javaScript:window.history.back();">Quay lại</a>
				</div>
			</div>
		</form>
	</div>

</div>
