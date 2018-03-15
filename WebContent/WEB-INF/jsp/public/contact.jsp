<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<hr class="soften">
<h1>Visit us</h1>
<hr class="soften" />
<div class="row">
	<div class="span4">
		<h4>Địa chỉ liên hệ</h4>
		<p>
			K1/89 - Ngô Sỹ Liên - Hòa Khánh Bắc - Liên Chiểu - TP.Đà Nẵng
		</p>
	</div>

	<div class="span4">
		<h4>Thời gian mở của</h4>
		<h5>Tất cả các ngày trong tuần 24/7</h5>
	</div>
	<div class="span4">
		<h4>Gửi liên hệ cho chúng tôi</h4>
		<form id="formlienhe" class="form-horizontal form_contact"
			action="javascript: void(0)">
			<fieldset style="width: 100%">
				<div class="control-group">
					<div id="thongbao" style="text-align: center;"></div>
				</div>
				<div class="control-group">

					<input style="width: 100%" id="fullname" name="fullname" type="text"
						placeholder="fullname" class="input-xlarge" />

				</div>
				<div class="control-group">

					<input style="width: 100%" id="email" name="email" type="text"
						placeholder="email" class="input-xlarge" />

				</div>
				<div class="control-group">

					<input style="width: 100%" id="phone" name="phone" type="text" placeholder="phone"
						class="input-xlarge" />

				</div>
				<div class="control-group">
					<textarea style="width: 100%" name="preview" rows="5" id="preview" class="input-xlarge"></textarea>

				</div>

				<button onclick="send()" class="btn btn-large" type="submit">Gửi</button>

			</fieldset>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$(".form_contact").validate({
		rules: {
			fullname: {
				required: true,
			},
			preview:{
				required: true,
				minlength: 6,
			},
			email:{
				required: true,
				email: true,
			},
			phone:{
				required: true,
				digits: true,
			}
			
		},
		messages: {
			fullname: {
				required: "Vui lòng nhập fullname",
			},
			preview:{
				required: "Hãy nhập nội dung",
				minlength: "Tối thiểu 6 ký tự"
			},
			email:{
				required: "Hãy nhập email",
				email: "Hãy nhập đúng email",
			},
			phone:{
				required: "Hãy nhập số điện thoại",
				digits: "Hãy nhập đúng số điện thoại",
			}
		}
	});
});	
	function send() {
		var ahoten = $('#fullname').val();
		var aemail = $('#email').val();
		var aphone = $('#phone').val();
		var apreview = $('#preview').val();
		if(ahoten==""||aemail==""||aphone==""||apreview.length<=6){
			return false;
		}else{
		$.ajax({
			url : '${pageContext.request.contextPath}/sendcontact',
			type : 'POST',
			cache : false,
			data : {
				fullname : ahoten,
				email : aemail,
				phone : aphone,
				preview : apreview
			},
			beforeSend : function() {
				$('#thongbao')
						.html(
								'<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/loading.gif" alt="" />');
			},
			success : function(data) {
				// Success message
				$('#thongbao')
						.html(
								'<div class="alert alert-success alert-dismissable"'
						+'style="text-align: center;">'
										+ '<button aria-hidden="true" data-dismiss="alert" class="close"'
							+'type="button">×</button>'
										+ 'Gửi thành công!!!'
										+ '</div>');
				//clear all fields
				$('#formlienhe').trigger("reset");
			},
			error : function() {
				// Fail message
				$('#thongbao')
						.html(
								'<div class="alert alert-danger alert-dismissable"'
						+'style="text-align: center;">'
										+ '<button aria-hidden="true" data-dismiss="alert" class="close"'
							+'type="button">×</button>'
										+ 'Gửi thất bại!!!' + '</div>');
				//clear all fields
				$('#formlienhe').trigger("reset");
			}
		});
		}
	}
</script>

<div class="row">
	<div class="span12">
		<iframe style="width: 100%; height: 300; border: 0px" scrolling="no"
			src="https://maps.google.co.uk/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=18+California,+Fresno,+CA,+United+States&amp;aq=0&amp;oq=18+California+united+state&amp;sll=39.9589,-120.955336&amp;sspn=0.007114,0.016512&amp;ie=UTF8&amp;hq=&amp;hnear=18,+Fresno,+California+93727,+United+States&amp;t=m&amp;ll=36.732762,-119.695787&amp;spn=0.017197,0.100336&amp;z=14&amp;output=embed"></iframe>
		<br /> <small><a
			href="https://maps.google.co.uk/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=18+California,+Fresno,+CA,+United+States&amp;aq=0&amp;oq=18+California+united+state&amp;sll=39.9589,-120.955336&amp;sspn=0.007114,0.016512&amp;ie=UTF8&amp;hq=&amp;hnear=18,+Fresno,+California+93727,+United+States&amp;t=m&amp;ll=36.732762,-119.695787&amp;spn=0.017197,0.100336&amp;z=14"
			style="color: #0000FF; text-align: left">View Larger Map</a></small>
	</div>
</div>
