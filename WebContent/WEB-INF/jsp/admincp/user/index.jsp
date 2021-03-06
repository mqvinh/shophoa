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
				lý người dùng</span>
		</div>
		<!-- Table -->
		<c:if test="${param['msg'] eq 'select_loi'}">
			<div class="alert alert-danger alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Hãy chọn mục muốn tìm!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'textSeach_id'}">
			<div class="alert alert-danger alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Nhập đúng định dạng Id là số nguyên dương!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'them'}">
			<div class="alert alert-success alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Thêm thành công!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'them_loi_admin'}">
			<div class="alert alert-danger alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Không thể thêm admin!!!
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'xoa_loi_admin'}">
			<div class="alert alert-danger alert-dismissable"
				style="text-align: center;">
				<button aria-hidden="true" data-dismiss="alert" class="close"
					type="button">×</button>
				Không thể xóa admin!!!
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
						<a
							href="${pageContext.request.contextPath}/admincp/nguoi-dung/them"
							class="btn btn_1 btn-default"> <i class="fa fa-plus"></i>
							Thêm người dùng
						</a>
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
									<c:if test="${!(textSeach eq null)}">
										<c:set value="&textSeach=${textSeach}&selectS=${selectS}" var="ts"></c:set>
									</c:if>
										<li><a
											href="${pageContext.request.contextPath}/admincp/nguoi-dung?h=5${ts}">5
												user</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/nguoi-dung?h=10${ts}">10
												user</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/nguoi-dung?h=20${ts}">20
												user</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/nguoi-dung?h=50${ts}">50
												user</a></li>
										<li><a
											href="${pageContext.request.contextPath}/admincp/nguoi-dung?h=100${ts}">100
												user</a></li>
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
							<th>Username</th>
							<th>Picture</th>
							<th>Role</th>
							<th>Fullname</th>
							<th>Address</th>
							<th>Phone</th>
							<th>Enable</th>
							<th>Chức năng</th>
						</tr>
						<c:forEach items="${lItem}" var="Item">
							<c:set value="${Item.id}" var="id"></c:set>
							<c:set var="urlEdit"
								value="${pageContext.request.contextPath}/admincp/nguoi-dung/sua/${id }"></c:set>
							<c:set var="urlDel"
								value="${pageContext.request.contextPath}/admincp/nguoi-dung/xoa/${id }"></c:set>
							<tr>
								<td class="hidden-xs"><input type="checkbox" value="${id}"
									name="delChoose" class="checkbox"></td>
								<td>${id}</td>
								<td><a href="${urlEdit}">${Item.username}</a></td>
								<td><img style="width: 50px; height: 50px"
									src="${pageContext.request.contextPath}/files/${Item.picture}"
									alt="..." class="img-thumbnail"></td>
								<td>${Item.rname}</td>
								<td>${Item.fullname}</td>
								<td>${Item.address}</td>
								<td>${Item.phone}</td>
								<td>
									<c:if test="${Item.enabled==0}">
										<div id="user${id}">
										<a  onclick="getchange(${id})" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>
										</div>
									</c:if>
									<c:if test="${Item.enabled==1}">
										<div id="user${id}">
										<a  onclick="getchange(${id})" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>
										</div>
									</c:if>
								</td>
								<td><a href="${urlEdit}" class="hvr-shadow-radial"
									style="padding: 4px; margin: 0px">Sửa</a> <a href="${urlDel}"
									class="hvr-shadow-radial" style="padding: 4px; margin: 0px">Xóa</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav aria-label="navigation"
					style="padding-top: 0px; margin-top: 0px">
					<ul class="pagination">
						<c:if test="${page>1}">
							<c:set value="?h=${h}&p=${page-1}${ts}" var="urlPrevious"></c:set>
						</c:if>
						<c:if test="${page==1}">
							<c:set value="#" var="urlPrevious"></c:set>
						</c:if>
						<li><a href="${urlPrevious}" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>

						<c:forEach begin="1" end="${sumpage }" var="i">
							<c:set value="?h=${h}&p=${i}${ts}" var="urlPage"></c:set>
							<c:if test="${page==i}">
								<c:set value="class='active'" var="cl"></c:set>
							</c:if>
							<c:if test="${page!=i}">
								<c:set value="" var="cl"></c:set>
							</c:if>
							<li ${cl }><a href="${urlPage}">${i}</a></li>
						</c:forEach>

						<c:if test="${page<sumpage}">
							<c:set value="?h=${h}&p=${page+1}${ts}" var="urlNext"></c:set>
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
		function getchange(aid) {
			var cl="#user"+aid;
			$.ajax({
				url : '${pageContext.request.contextPath}/admincp/activeUs',
				type: 'POST', 
				dataType: 'html', 
				data: {id: aid},
				beforeSend: function() {
					$(cl).html('<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/loading.gif" alt="" />');
				},
				success: function(data) {
					if(data==11){ 
						$(cl).html('<a  onclick="getchange('+aid+')" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/check.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công ID '+aid+' !!!'
							+'</div>');
					}else if(data==10){
						$(cl).html('<a  onclick="getchange('+aid+')" href="javascript: void(0)"><img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/noncheck.png"></a>');
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thành công ID '+aid+' !!!'
							+'</div>');
					}else{
						$(cl).html('<img style="width: 20px; height: 20px" alt="" src="${defines.ADMIN_TEMPLATE_URL}/images/warning.png">');
						$('#thongbao').html('<div class="alert alert-danger alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thay đổi thất bại ID '+aid+' !!! Kiểm tra lại thao tác hoặc làm mới trình duyệt.'
							+'</div>');
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
				return confirm('Bạn có muốn xóa các dòng đã chọn?');
		    } else {
		    	alert('Vui lòng chọn ít nhất 1 dòng để xóa');
		    	return false;
		    }
		}
		</script>
	<div class="clearfix"></div>
</div>
<!--inner block end here-->
