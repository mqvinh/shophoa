<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Sửa
				hoa</span>
			<!-- Form -->
			<form class="form-horizontal form_product" action="${pageContext.request.contextPath}/admincp/hoa/sua/${Item.id}" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Tên hoa:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="name" name="name"
							value="${Item.name}" placeholder="Nhập tên hoa">
							<form:errors path="objItem.name" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="kieu" class="col-sm-2 control-label">Kiểu hoa:</label>
					<div class="col-sm-4">
						<select class="form-control" name="id_species" id="kieu">
							<option>---Chọn kiểu hoa---</option>
							<c:forEach items="${lSpecies}" var="species">
								<c:if test="${Item.id_species==species.id_species}">
									<c:set value='selected="selected"' var="sl"></c:set>
								</c:if>
								<c:if test="${Item.id_species!=species.id_species}">
									<c:set value='' var="sl"></c:set>
								</c:if>
								<option ${sl} value="${species.id_species}">${species.name}</option>
							</c:forEach>
						</select>
						<form:errors path="objItem.id_species" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="loai" class="col-sm-2 control-label">Loại hoa:</label>
					<div class="col-sm-4">
						<select class="form-control" name="id_type" id="loai">
							<option>---Chọn loại hoa---</option>
							<c:forEach items="${lType}" var="type">
								<c:if test="${Item.id_type==type.id_type}">
									<c:set value='selected="selected"' var="sl"></c:set>
								</c:if>
								<c:if test="${Item.id_type!=type.id_type}">
									<c:set value='' var="sl"></c:set>
								</c:if>
								<option ${sl} value="${type.id_type}">${type.name}</option>
							</c:forEach>
						</select>
						<form:errors path="objItem.id_type" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="mota" class="col-sm-2 control-label">Mô tả:</label>
					<div class="col-sm-8">
						<textarea name="preview" id="mota" class="form-control" rows="5" placeholder="Nhập mô tả">${Item.preview}</textarea>
						<form:errors path="objItem.preview" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="giaban" class="col-sm-2 control-label">Giá bán:</label>
					<div class="col-sm-8">
						<input type="number" class="form-control" id="giaban" value="${Item.price}" name="price"
							placeholder="Nhập giá bán">
							<form:errors path="objItem.price" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="soluong" class="col-sm-2 control-label">Số lượng:</label>
					<div class="col-sm-8">
						<input type="number" class="form-control" id="soluong" value="${Item.number}" name="number"
							placeholder="Nhập giá bán">
							<form:errors path="objItem.number" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="hinhanh" class=" col-sm-2 control-label">Hình ảnh:</label>
					<div class="col-sm-8">
						<div id="your-files">
						<img style="width: 200px; height: 200px" src="${defines.FILE_PICTURE_URL}/${Item.picture}" alt="..." class="img img-thumbnail">
						</div>
						<a href="javascript: void(0)" onclick="delanh('${Item.picture}',${Item.id})" class="btn btn-default">Xóa ảnh</a>
						<input type="file" class="file filestyle" id="fileToUpload" name="picturem"
							data-buttonText="Select a File" onchange='get_hinh(this,"${defines.FILE_PICTURE_URL}/${Item.picture}")'> 
					</div>
				</div>
				<div class="form-group">
					<label for="chitiet" class="col-sm-2 control-label">Chi tiết:</label>
					<div class="col-sm-8">
						<textarea id="chitiet" class="ckeditor form-control" name="detail" rows="5">${Item.detail}</textarea>
						<form:errors path="objItem.detail" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<div>
						<button type="reset" class="btn btn-default">Nhập lại</button>
						<button type="submit" class="btn btn-default">Sửa</button>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
<script>
//xóa ảnh
function delanh(picture, id) {
	confirm('Bạn có chắc muốn xóa ảnh?');
	var s="";
	if(document.getElementById("fileToUpload").value==""){
		s=picture;
	}else{
		s=document.getElementById("fileToUpload").files[0]['name'];
	}
	document.getElementById("fileToUpload").value="";
	$.ajax({
		url : '${pageContext.request.contextPath}/admincp/delanhPro',
		type : 'post',
		cache : false,
		data : {
			// Dữ liệu gửi đi
			as:s,
			apicture : picture,
			aid: id,
		},
		success : function(data) {
			$('#your-files').html(data);

		},
		error : function() {
			// Xử lý nếu có lỗi
			alert("co loi ");
		}
	});
}


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
