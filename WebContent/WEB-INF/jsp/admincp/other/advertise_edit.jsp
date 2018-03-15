<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Sửa quảng cáo</span>
			<!-- Form -->
			<form class="form-horizontal" action="${pageContext.request.contextPath}/admincp/quang-cao/sua/${Item.id}" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="link" class="col-sm-2 control-label">Link:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="link" name="link" value="${Item.link}"
							placeholder="Nhập link">
					</div>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Trạng thái:</label>
					<div class="col-sm-4">
					<c:set value="" var="sl1"></c:set>
					<c:set value="selected='selected'" var="sl2"></c:set>
					<c:if test="${Item.status==1}">
						<c:set value="selected='selected'" var="sl1"></c:set>
						<c:set value="" var="sl2"></c:set>
					</c:if>
						<select class="form-control">
							<option>---Chọn trạng thái---</option>
							<option ${sl1} value="1">Kích hoạt</option>
							<option ${sl2} value="0">Chưa kích hoạt</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="picturem" class="col-sm-2 control-label">Hình ảnh:</label>
					<div class="col-sm-8">
						<p><img style="width: 200px; height: 200px" src="${defines.FILE_PICTURE_URL}/${Item.picture}" alt="..." class="img-thumbnail"></p>
						<button type="submit" class="btn btn-default">Xóa ảnh</button>
						<input type="file" class="filestyle" id="picturem" name="picturem"
							data-buttonText="Select a File">
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
