<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Đăng nhập</li>
	</ul>
	<h2>Đăng nhập</h2>
	<hr class="soft" />
	<c:if test="${param['msg'] eq 'them'}">
		<h4 class="alert alert-success">Đăng kí thành công. Đăng nhập
			để tiếp tục mua hàng!!!</h4>
	</c:if>
	<c:if test="${param['msg'] eq 'them_loi'}">
		<div class="alert alert-danger">Đăng kí thất bại vui lòng đăng ký lại!!!</div>
	</c:if>
	<div class="row">
		<div class="span4">
			<div class="well">
				<h3>Đăng ký</h3>
				<form action="${pageContext.request.contextPath}/dang-nhap-pl"
					method="post">
					<h5>Bạn chưa có tài khoản!!!</h5>
					<div class="alert alert-success">Khuyến khích!!! đăng ký tài
						khoản mới để được chúng tôi chăm sóc tốt hơn</div>
					<div class="control-group">
						<div class="controls">
							<label class="radio"> <input type="radio" name="choose"
								id="optionsRadios1" value="1" checked=""> Mua hàng không
								cần đăng ký!!!
							</label>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="radio"> <input type="radio"
								checked="checked" name="choose" id="optionsRadios1" value="2"
								checked=""> Đăng ký tài khoản mới!!!
							</label>
						</div>
					</div>
					<div class="control-group">
						<input type="submit" class="btn" value="Tiếp tục">
					</div>
				</form>

			</div>
		</div>
		<div class="span1">&nbsp;</div>
		<div class="span4">
			<div class="well">
				<h3>Đăng nhập</h3>
				<h5>Bạn đã có tài khoản!!!</h5>
				<c:if test="${param['msg'] eq 'error'}">
					<div class="alert alert-danger alert-dismissable"
						style="text-align: center;">
						<button aria-hidden="true" data-dismiss="alert" class="close"
							type="button">×</button>
						Tên đăng nhập hoặc password không đúng!!!
					</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/dang-nhap-pl" method="post">
					<div class="control-group">
						<label class="control-label" for="inputEmail1">Username</label>
						<div class="controls">
							<input class="span3" type="text" id="inputEmail1" name="username"
								placeholder="Username">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword1">Password</label>
						<div class="controls">
							<input type="password" class="span3" id="inputPassword1"
								name="password" placeholder="Password">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">Đăng nhập</button>
							<a href="${pageContext.request.contextPath}/lay-pass">Quên password?</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>
