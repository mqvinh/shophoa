<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<!--inner block start here-->
<div class="inner-block">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div style="text-align: center;" class="panel-heading">
			<span
				style="font-size: 30px; font-weight: bold; font-family: serif; color: #68ae00;">Quản
				lý đơn hàng</span>
		</div>
		<!-- Table -->
		<c:if test="${param['msg'] eq 'them'}">
			<div class="alert alert-success alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Thêm thành công!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'xoa'}">
			<div class="alert alert-success alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Xóa thành công!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'idloi'}">
			<div class="alert alert-danger alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Hãy nhập đúng định dạng Id!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'sua'}">
			<div class="alert alert-success alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Sửa thành công!!!
			</div>
		</c:if>
		<div id="thongbao" style="text-align: center;"></div>
		<form action="" method="post">
			<div class="mailbox-border" style="text-align: center;">
				<div class="mail-toolbar clearfix" style="text-align: left;">
					<div class="float-left">
						<a href="javascript:void(0)" id="chkAll"
							class="btn btn_1 btn-default"> <i class="fa fa-warning"></i>
							Chọn tất cả
						</a>
						<button onclick="return valDels()" class="btn btn_1 btn-default">
							<i class="fa fa-trash"></i> Xóa chọn
						</button>
						<div class="clearfix"></div>
					</div>
					<div class="float-right">
						<div style="float: right;">
							<span class="text-muted m-r-sm">Hiển thị ${rowcount} của
								${sum}</span>
							<div class="btn-group m-r-sm mail-hidden-options"
								style="display: inline-block; margin: 0px; padding: 0px">
								<div class="btn-group">
									<a class="btn btn-default dropdown-toggle"
										data-toggle="dropdown" aria-expanded="false"><i
										class="fa fa-eye"></i> Hiển thị <span class="caret"></span></a>
									<ul class="dropdown-menu dropdown-menu-left" role="menu">
										<li><a
											href="${pageContext.request.contextPath}/admincp/don-hang?h=5">5
												đơn hàng</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/don-hang?h=10">10
												đơn hàng</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/don-hang?h=20">20
												đơn hàng</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/don-hang?h=50">50
												đơn hàng</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/don-hang?h=100">100
												đơn hàng</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<table class="table tab-border" style="text-align: left;"
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="hidden-xs"></th>
							<th>ID</th>
							<th>Khách hàng</th>
							<th>Ngày tạo</th>
							<th>TT thanh toán</th>
							<th>TT kích hoạt</th>
							<th>TT gửi hàng</th>
							<th>PT thanh toán</th>
							<th>Thông tin thêm</th>
							<th>Tên KH</th>
							<th>Đại chỉ nhận</th>
							<th width="120px">Chức năng</th>
						</tr>
						<c:forEach items="${lItem}" var="Item">
							<c:set value="${Item.id}" var="id"></c:set>
							<c:set var="urlDetail"
								value="${pageContext.request.contextPath}/admincp/don-hang/chi-tiet/${id }"></c:set>
							<c:set var="urlDel"
								value="${pageContext.request.contextPath}/admincp/don-hang/xoa/${id }"></c:set>
							<tr>
								<td class="hidden-xs"><input type="checkbox" value="${id}"
									name="delChoose" class="checkbox"></td>
								<td>${Item.id}</td>
								<td>${Item.name_role}</td>
								<td>${Item.date}</td>
								<td><c:if test="${Item.status_pay==0}">
										<div id="ex${id}1">
											<a onclick="getchange(${id},1)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>
										</div>
									</c:if> <c:if test="${Item.status_pay==1}">
										<div id="ex${id}1">
											<a onclick="getchange(${id},1)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>
										</div>
									</c:if></td>
								<td><c:if test="${Item.status_active==0}">
										<div id="ex${id}2">
											<a onclick="getchange(${id},2)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>
										</div>
									</c:if> <c:if test="${Item.status_active==1}">
										<div id="ex${id}2">
											<a onclick="getchange(${id},2)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>
										</div>
									</c:if></td>
								<td><c:if test="${Item.status_ship==0}">
										<div id="ex${id}3">
											<a onclick="getchange(${id},3)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>
										</div>
									</c:if> <c:if test="${Item.status_ship==1}">
										<div id="ex${id}3">
											<a onclick="getchange(${id},3)" href="javascript: void(0)"><img
												style="width: 20px; height: 20px" alt=""
												src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>
										</div>
									</c:if></td>
								<td>${Item.name_pay}</td>
								<td>${Item.more_infor}</td>
								<td>${Item.name_user}</td>
								<td>${Item.address_user}</td>
								<td><a href="${urlDetail}" class="hvr-shadow-radial"
									style="padding: 4px; margin: 0px">Chi tiết</a> <a
									href="${urlDel}" class="hvr-shadow-radial"
									style="padding: 4px; margin: 0px">Xóa</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav aria-label="navigation"
					style="padding-top: 0px; margin-top: 0px">
					<ul class="pagination">
						<c:if test="${page>1}">
							<c:set value="?h=${h}&p=${page-1}" var="urlPrevious"></c:set>
						</c:if>
						<c:if test="${page==1}">
							<c:set value="#" var="urlPrevious"></c:set>
						</c:if>
						<li><a href="${urlPrevious}" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>

						<c:forEach begin="1" end="${sumpage }" var="i">
							<c:set value="?h=${h}&p=${i}" var="urlPage"></c:set>
							<c:if test="${page==i}">
								<c:set value="class='active'" var="cl"></c:set>
							</c:if>
							<c:if test="${page!=i}">
								<c:set value="" var="cl"></c:set>
							</c:if>
							<li ${cl }><a href="${urlPage}">${i}</a></li>
						</c:forEach>

						<c:if test="${page<sumpage}">
							<c:set value="?h=${h}&p=${page+1}" var="urlNext"></c:set>
						</c:if>
						<c:if test="${page==sumpage}">
							<c:set value="#" var="urlNext"></c:set>
						</c:if>
						<li><a href="${urlNext}" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	function getchange(aid,acheck) {
		var cl="#ex"+aid+acheck;
		$.ajax({
			url : '${pageContext.request.contextPath}/admincp/activeEx',
			type: 'POST', 
			dataType: 'html', 
			data: {id: aid, check: acheck},
			beforeSend: function() {
				$(cl).html('<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/loading.gif" alt="" />');
			},
			success: function(data) {
				switch (data)
				{
				    case '111' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',1)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT thanh toán. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '110' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',1)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT thanh toán. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '011' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',1)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT kích hoạt. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '010' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',1)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT kích hoạt. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '121' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',2)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT kích hoạt. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '120' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',2)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT kích hoạt. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '021' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',2)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT kích hoạt. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '020' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',2)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT kích hoạt. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '131' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',3)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT gửi hàng. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '130' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',3)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công TT gửi hàng. ID '+aid+' !!!'
							+'</div>');
				        break;
				    }
				    case '031' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',3)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT gửi hàng. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '030' : {
				    	$(cl).html('<a  onclick="getchange('+aid+',2)" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại TT gửih hàng. ID '+aid+' !!! thử lại sau'
							+'</div>');
				        break;
				    }
				    case '000' : {
				    	$(cl).html('<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/warning.png">');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Lỗi cơ sở dữ liệu!!! làm mới lại trình duyệt và thử lại'
							+'</div>');
				        break;
				    }
				}
			}
		});
	}
	
		var i=0;
		$('#chkAll').click(function(event) {
			  if(i==0) {
			      // Iterate each checkbox
			      $('input[type="checkbox"][name="delChoose"]').each(function() {
			          this.checked = true;
			      });
			      i=1;
			      $('#chkAll').html("<i class='fa fa-warning'></i> Bỏ chọn");
			  }
			  else {
				  $('input[type="checkbox"][name="delChoose"]').each(function() {
			          this.checked = false;
			      });
				  i=0;
				  $('#chkAll').html("<i class='fa fa-warning'></i> Chọn tất cả");
			  }
			});
		function valDels()
		{
		    var checkedAtLeastOne = false;
		    $('input[type="checkbox"][name="delChoose"]').each(function() {
		        if ($(this).is(":checked")) {
		            checkedAtLeastOne = true;
		        }
		    });
		    
		    if (checkedAtLeastOne == true){
				return confirm('Bạn có muốn xóa các hoa đã chọn?');
		    } else {
		    	alert('Vui lòng chọn ít nhất 1 hoa để xóa');
		    	return false;
		    }
		}
		</script>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
