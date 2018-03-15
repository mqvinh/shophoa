<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span class="divider">/</span></li>
		<li class="active">Forget password?</li>
	</ul>
	<h3>Bạn quên mật khẩu?</h3>
	<hr class="soft" />

	<div class="row">
		<div class="span9" style="min-height: 900px">
			<div class="well">
				<h5>Lấy lại mật khẩu</h5>
				<br /> Hãy điền địa chỉ Email mà bạn đã đăng ký chúng tôi sẽ gửi lại mật khẩu mới vào email của bạn.<br />
				<br />
				<br />
				<c:if test="${param['msg'] eq 'loi'}">
					<div class="alert alert-danger alert-dismissable"
						style="text-align: center;">
						<button aria-hidden="true" data-dismiss="alert" class="close"
							type="button">×</button>
						Lỗi hãy nhập lại chính xác địa chỉ email của bạn!!!
					</div>
				</c:if>
				<c:if test="${param['msg'] eq 'loi_tk'}">
					<div class="alert alert-danger alert-dismissable"
						style="text-align: center;">
						<button aria-hidden="true" data-dismiss="alert" class="close"
							type="button">×</button>
						Email không chính xác hoặc chưa đăng ký tài khoản!!!
					</div>
				</c:if>
				<c:if test="${param['msg'] eq 'loi_update'}">
					<div class="alert alert-danger alert-dismissable"
						style="text-align: center;">
						<button aria-hidden="true" data-dismiss="alert" class="close"
							type="button">×</button>
						Lỗi hệ thống hãy thử lại!!!
					</div>
				</c:if>
				<c:if test="${param['msg'] eq 'laypass'}">
					<div class="alert alert-success alert-dismissable"
						style="text-align: center;">
						<button aria-hidden="true" data-dismiss="alert" class="close"
							type="button">×</button>
						Thành công hãy kiểm tra email của bạn trong thời gian sớm nhất!!!
					</div>
				</c:if>
				<form method="post" action="${pageContext.request.contextPath}/lay-pass">
					<div class="control-group">
						<label class="control-label" for="inputEmail1">E-mail
							address</label>
						<div class="controls">
							<input class="span3" type="text" id="inputEmail1" name="email"
								placeholder="Email">
						</div>
					</div>
					<div class="controls">
						<button type="submit" class="btn block">Lấy lại mật khẩu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>
