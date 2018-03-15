<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Sửa người dùng</span>
			<!-- Form -->
			<form class="form-horizontal form_user_edit" action="${pageContext.request.contextPath}/admincp/nguoi-dung/sua/${Item.id}" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="username" name="username" value="${Item.username}"
							placeholder="Nhập Username"><input hidden="hidden"
							type="text" value="0" id="check"> <input
							hidden="hidden" type="text" value="${Item.id}" id="check_id">
							<form:errors path="objItem.username" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password:</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="password" name="password" value=""
							placeholder="Nhập Password">
							<form:errors path="objItem.password" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="rpassword" class="col-sm-2 control-label">Nhập lại password:</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="rpassword" name="rpassword"
							placeholder="Nhập lại password">
							<form:errors path="objItem.rpassword" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Role:</label>
					<div class="col-sm-4">
						<select class="form-control" name="role">
							<option value="0">---Chọn quyền---</option>
							<c:forEach items="${Role}" var="ItemR">
								<c:if test="${Item.role==ItemR.role }">
									<c:set value='selected="selected"' var="sl"></c:set>
								</c:if>
								<c:if test="${Item.role!=ItemR.role }">
									<c:set value='' var="sl"></c:set>
								</c:if>
								<option ${sl} value="${ItemR.role}">${ItemR.name }</option>
							</c:forEach>
						</select>
						<form:errors path="objItem.role" cssStyle="color: red"></form:errors>
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
					<label for="fullname" class="col-sm-2 control-label">Fullname:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="fullname" name="fullname" value="${Item.fullname}"
							placeholder="Nhập Fullname">
							<form:errors path="objItem.fullname" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 control-label">Address:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="address" name="address" value="${Item.address}"
							placeholder="Nhập Address">
							<form:errors path="objItem.address" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">Email:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="email" name="email" value="${Item.email}"
							placeholder="Nhập email"><input hidden="hidden"
							type="text" value="0" id="checke">
							<form:errors path="objItem.email" cssStyle="color: red"></form:errors>
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label">Phone:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="phone" name="phone" value="${Item.phone}"
							placeholder="Nhập Phone">
							<form:errors path="objItem.phone" cssStyle="color: red"></form:errors>
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
<script type="text/javascript">
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
			url : '${pageContext.request.contextPath}/admincp/delanh',
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
	</script>
