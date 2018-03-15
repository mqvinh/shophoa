<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Thông
				tin tài khoản</span>
			<!-- Form -->
			<c:if test="${param['msg'] eq 'up'}">
				<div class="alert alert-success alert-dismissable"
					style="text-align: center;">
					<button aria-hidden="true" data-dismiss="alert" class="close"
						type="button">×</button>
					Cập nhật thành công!!!
				</div>
			</c:if>
			<form class="form-horizontal" action="" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="username"
							name="username" disabled="disabled" value="${User.username}"
							placeholder="Nhập Username">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password
						mới:</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="Nhập Password mới">
					</div>
				</div>
				<div class="form-group">
					<label for="rpassword" class="col-sm-2 control-label">Nhập
						lại password mới:</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="rpassword"
							name="rpassword" placeholder="Nhập lại password mới">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Role:</label>
					<div class="col-sm-4">
						<select class="form-control" name="role">
							<option>---Chọn quyền---</option>
							<c:forEach items="${Role}" var="Item">
								<c:if test="${User.role==Item.role}">
									<c:set value="selected='selected'" var="sl"></c:set>
								</c:if>
								<c:if test="${User.role!=Item.role}">
									<c:set value="" var="sl"></c:set>
								</c:if>
								<option ${sl} value="${Item.role}">${Item.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Hình ảnh:</label>
					<div class="col-sm-8">
						<p>
							<img style="width: 200px; height: 200px"
								src="${defines.FILE_PICTURE_URL}/${User.picture}" alt="..."
								class="img-thumbnail">
						</p>
						<button type="submit" class="btn btn-default">Xóa ảnh</button>
						<input type="file" class="filestyle" name="picturem"
							data-buttonText="Select a File">
					</div>
				</div>
				<div class="form-group">
					<label for="fullname" class="col-sm-2 control-label">Fullname:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="fullname"
							name="fullname" value="${User.fullname}"
							placeholder="Nhập Fullname">
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 control-label">Address:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="address"
							name="address" value="${User.address}" placeholder="Nhập Address">
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">Email:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="email"
							name="email" value="${User.email}" placeholder="Nhập Email">
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label">Phone:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="phone" name="phone"
							value="${User.phone}" placeholder="Nhập Phone">
					</div>
				</div>
				<div class="form-group">
					<div>
						<button type="submit" class="btn btn-default">Cập nhật</button>
						<button type="reset" class="btn btn-default">Nhập lại</button>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
<script>
	$('#file-fr').fileinput({
		language : 'fr',
		uploadUrl : '#',
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ]
	});
	$('#file-es').fileinput({
		language : 'es',
		uploadUrl : '#',
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ]
	});
	$("#file-0").fileinput({
		'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ]
	});
	$("#file-1").fileinput({
		uploadUrl : '#', // you must set a valid URL here else you will get an error
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
		overwriteInitial : false,
		maxFileSize : 1000,
		maxFilesNum : 10,
		//allowedFileTypes: ['image', 'video', 'flash'],
		slugCallback : function(filename) {
			return filename.replace('(', '_').replace(']', '_');
		}
	});
	/*
	 $(".file").on('fileselect', function(event, n, l) {
	 alert('File Selected. Name: ' + l + ', Num: ' + n);
	 });
	 */
	$("#file-3").fileinput(
			{
				showUpload : false,
				showCaption : false,
				browseClass : "btn btn-primary btn-lg",
				fileType : "any",
				previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				overwriteInitial : false,
				initialPreviewAsData : true,
				initialPreview : [
						"http://lorempixel.com/1920/1080/transport/1",
						"http://lorempixel.com/1920/1080/transport/2",
						"http://lorempixel.com/1920/1080/transport/3", ],
				initialPreviewConfig : [ {
					caption : "transport-1.jpg",
					size : 329892,
					width : "120px",
					url : "{$url}",
					key : 1
				}, {
					caption : "transport-2.jpg",
					size : 872378,
					width : "120px",
					url : "{$url}",
					key : 2
				}, {
					caption : "transport-3.jpg",
					size : 632762,
					width : "120px",
					url : "{$url}",
					key : 3
				}, ],
			});
	$("#file-4").fileinput({
		uploadExtraData : {
			kvId : '10'
		}
	});
	$(".btn-warning").on('click', function() {
		var $el = $("#file-4");
		if ($el.attr('disabled')) {
			$el.fileinput('enable');
		} else {
			$el.fileinput('disable');
		}
	});
	$(".btn-info").on('click', function() {
		$("#file-4").fileinput('refresh', {
			previewClass : 'bg-info'
		});
	});
	/*
	 $('#file-4').on('fileselectnone', function() {
	 alert('Huh! You selected no files.');
	 });
	 $('#file-4').on('filebrowse', function() {
	 alert('File browse clicked for #file-4');
	 });
	 */
	$(document)
			.ready(
					function() {
						$("#test-upload").fileinput({
							'showPreview' : false,
							'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ],
							'elErrorContainer' : '#errorBlock'
						});
						$("#kv-explorer")
								.fileinput(
										{
											'theme' : 'explorer',
											'uploadUrl' : '#',
											overwriteInitial : false,
											initialPreviewAsData : true,
											initialPreview : [
													"http://lorempixel.com/1920/1080/nature/1",
													"http://lorempixel.com/1920/1080/nature/2",
													"http://lorempixel.com/1920/1080/nature/3", ],
											initialPreviewConfig : [ {
												caption : "nature-1.jpg",
												size : 329892,
												width : "120px",
												url : "{$url}",
												key : 1
											}, {
												caption : "nature-2.jpg",
												size : 872378,
												width : "120px",
												url : "{$url}",
												key : 2
											}, {
												caption : "nature-3.jpg",
												size : 632762,
												width : "120px",
												url : "{$url}",
												key : 3
											}, ]
										});
						/*
						 $("#test-upload").on('fileloaded', function(event, file, previewId, index) {
						 alert('i = ' + index + ', id = ' + previewId + ', file = ' + file.name);
						 });
						 */
					});
</script>
