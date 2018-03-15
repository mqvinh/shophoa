<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span class="divider">/</span></li>
		<li class="active">Đăng ký</li>
	</ul>
	<h3>Đăng ký tài khoản</h3>
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
		<form class="form-horizontal form_dangky" action="${pageContext.request.contextPath}/dang-ky-pl" method="post" enctype="multipart/form-data">
			<h4 class="alert alert-info">Chúng tôi cần một số thông tin cá nhân của bạn !!!</h4>
			<div class="control-group">
				<label class="control-label" for="inputFname1">Tên đầy đủ của bạn: <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="inputFname1" placeholder="Tên đầy đủ của bạn" name="fullname">
					<form:errors path="objItem.fullname" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">Ảnh đại diện: <sup>*</sup> </label>
				<div class="controls">
					<p><img style="width: 200px; height: 200px" src="${defines.FILE_PICTURE_URL}/notimg.jpg" alt="..." class="img-thumbnail"></p>
					<button type="submit" class="btn btn-default">Xóa ảnh</button><br>
					<input type="file" class="filestyle" name="picturem"
						data-buttonText="Select a File">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputLnam">Tên đăng nhập <sup>*</sup></label><br>
				<div class="controls">
					<input type="text" id="inputLnam" placeholder="Tên đăng nhập" name="username"><input
							 type="hidden" type="text" value="" id="check_id"><input  type="hidden"
							type="text" value="0" id="check">
					<form:errors path="objItem.username" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">Mật khẩu <sup>*</sup></label>
				<div class="controls">
					<input type="password" id="password" placeholder="Nhập mật khẩu" name="password"><input  type="hidden"
							type="text" value="0" id="check"> 
					<form:errors path="objItem.password" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="rpassword">Nhập lại mật khẩu <sup>*</sup></label>
				<div class="controls">
					<input type="password" id="rpassword" placeholder="Nhập lại mật khẩu " name="rpassword">
					<form:errors path="objItem.rpassword" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input_email">Email <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="input_email" placeholder="Email" value="" name="email"><input  type="hidden"
							type="text" value="0" id="checke">
					<form:errors path="objItem.email" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="address">Địa chỉ <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="address" placeholder="Địa chỉ" value="" name="address" >
					<form:errors path="objItem.address" cssStyle="color: red"></form:errors>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Số điện thoại <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="phone" placeholder="Số điện thoại" name="phone">
					<form:errors path="objItem.phone" cssStyle="color: red"></form:errors>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					 <input
						class="btn btn-large btn-success" type="submit" value="Đăng ký" />
					<a class="btn btn-large btn-success" href="javaScript:window.history.back();">Quay lại</a>
				</div>
			</div>
		</form>
	</div>

</div>
