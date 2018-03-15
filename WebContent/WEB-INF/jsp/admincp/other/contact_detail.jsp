<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Nội dung liên hệ</span>
			<!-- Form -->
			<form class="form-horizontal">
				<div class="float-left">
					<a href="${pageContext.request.contextPath}/admincp/lien-he" class="btn btn_1 btn-default">
						<i class="fa fa-warning"></i> Quay lại
					</a>
					<a class="btn btn_1 btn-default" href="${pageContext.request.contextPath}/admincp/lien-he/xoa/${Item.id}">
						<i class="fa fa-trash"></i> Xóa tin này
					</a>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div><br>
				<div class="form-group">
					<label for="fullname" class="col-sm-2 control-label">Fullname:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="fullname" name="fullname" value="${Item.fullname}"
							placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">Email:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="email" name="email" value="${Item.email}"
							placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label">Phone:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="phone" name="phone" value="${Item.phone}"
							placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="date" class="col-sm-2 control-label">Ngày gửi:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="date" name="date" value="${Item.date}"
							placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label for="preview" class="col-sm-2 control-label">Nội dung:</label>
					<div class="col-sm-8">
						<textarea id="preview" name="preview" class="form-control" rows="5" placeholder="Nhập mô tả">${Item.preview}</textarea>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
