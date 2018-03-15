<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Đăng nhập</span>
			<!-- Form -->
			<form class="form-horizontal" action="j_spring_security_admin" method="post" >
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username:</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="username" name="username"
							placeholder="Nhập Username">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password:</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="Nhập Password">
					</div>
				</div>
				
				<div class="form-group">
					<div>
						<button type="submit" class="btn btn-default">Đăng nhập</button>
						<button type="reset" class="btn btn-default">Nhập lại</button>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div class="clearfix"></div>
</div>