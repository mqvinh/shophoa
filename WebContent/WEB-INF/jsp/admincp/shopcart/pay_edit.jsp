<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Sửa thanh toán</span>
			<!-- Form -->
			<form class="form-horizontal" action="${pageContext.request.contextPath}/admincp/thanh-toan/sua/${Item.id}" method="post">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Tên thanh toán:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="name" value="${Item.name}" name="name"
							placeholder="Nhập tên thanh toán">
							<form:errors path="objItem.name" cssStyle="color: red"></form:errors>
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
