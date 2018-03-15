<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span class="divider">/</span></li>
		<li class="active">Thông tin</li>
	</ul>
	<h3>Thông tin khách hàng</h3>
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
		<form style="width: 100%;" class="form-horizontal" action="${pageContext.request.contextPath}/thongtin-mua" method="post">
			<h4 class="alert alert-info">Chúng tôi cần một số thông tin cá nhân của bạn !!!</h4>
			
			<div style="width: 100%;" class="control-group">
				<label class="control-label" for="inputFname1">Tên đầy đủ của bạn: <sup>*</sup></label>
				<div class="controls">
					<input style="width: 40%;" type="text" id="inputFname1" placeholder="Tên đầy đủ của bạn" name="fullname">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input_email">Email <sup>*</sup></label>
				<div class="controls" >
					<input style="width: 40%;" type="text" id="input_email" placeholder="Email" value="" name="email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Số điện thoại <sup>*</sup></label>
				<div class="controls">
					<input style="width: 40%;" type="text" id="phone" placeholder="Số điện thoại" name="phone">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="input_email">Địa chỉ giao hàng<sup>*</sup></label>
				<div class="controls">
					<input style="width: 60%;" type="text" id="input_email" placeholder="Địa chỉ" value="" name="address" >
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					 <input
						class="btn btn-large btn-success" type="submit" value="Tiếp tục" />
					<a class="btn btn-large btn-success" href="javaScript:window.history.back();">Quay lại</a>
				</div>
			</div>
		</form>
	</div>

</div>
