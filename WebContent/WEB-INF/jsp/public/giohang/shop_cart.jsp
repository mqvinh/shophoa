<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Giỏ hàng</li>
	</ul>
	<h3>
		Giỏ hàng [ <small>${sumProShop} Sản phẩm </small>]<a
			href="${pageContext.request.contextPath}/"
			class="btn small pull-right btn-success"><i
			class="icon-arrow-left"></i> Mua hàng </a>
	</h3>
	<hr class="soft" />
	<c:if test="${param['msg'] eq 'donhang'}">
		<h4 class="alert alert-success">Qúy khách đã mua hàng thành
			công!!!</h4>
		<hr class="soft" />
	</c:if>
	<c:if test="${param['msg'] eq 'donhang_loi'}">
		<div class="alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>Thất bại kiểm tra lại số lượng mua và cập nhật lại giỏ hoa của bạn !!!</div>
		<hr class="soft" />
	</c:if>
	<c:if test="${param['msg'] eq 'giohang' }">
		<div class="alert alert-block alert-error fade in">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<strong>Giỏ hàng rỗng</strong> mua hàng để tiếp tục...
		</div>
		<hr class="soft" />
	</c:if>
	<c:if test="${Hoaerror_max!=null}">
		<div class="alert alert-danger">Chú ý Hoa: 
		<c:forEach items="${Hoaerror_max}" var="tenhoa_max">
			${tenhoa_max},
		</c:forEach>
		có số lượng mua lớn hơn số lượng còn...Không thể cập nhật hoa này
		</div>
		<hr class="soft" />
	</c:if>
	<c:if test="${Hoaerror_min!=null}">
		<div class="alert alert-danger">Chú ý Hoa: 
		<c:forEach items="${Hoaerror_min}" var="tenhoa_min">
			${tenhoa_min},
		</c:forEach>
		có số lượng mua âm...Không thể cập nhật hoa này
		</div>
		<hr class="soft" />
	</c:if>
	<form action="" method="post">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Tên hoa</th>
					<th>Hình ảnh</th>
					<th>Loại hoa</th>
					<th>Kiểu hoa</th>
					<th>Số lượng</th>
					<th>Đơn giá</th>
					<th>Thành tiền</th>
					<th>Chức năng</th>
				</tr>
			</thead>
			<tbody>
				<c:set value="0" var="tongtien"></c:set>
				<c:forEach items="${lItemShop}" var="ItemShop">
					<tr>
						<td><a href="#">${ItemShop.name}<br> <span style="color: red">(Còn: ${ItemShop.number})</span></a></td>
						<td><img width="60"
							src="${defines.FILE_PICTURE_URL}/${ItemShop.picture}" alt="" /></td>
						<td><a href="#">${ItemShop.tname }</a></td>
						<td><a href="#">${ItemShop.sname }</a></td>
						<td>
							<div class="input-append">
								<input class="span1 ${ItemShop.id }" style="max-width: 70px"
									placeholder="1" id="soluong-${ItemShop.id}" size="16"
									type="number" name="soluong" value="${ItemShop.soluong }">
								<%-- <button class="btn" type="button" onclick="up(${ItemShop.id })">
									<i class="icon-minus"></i>
								</button>
								<button class="btn" type="button"
									onclick="down(${ItemShop.id })">
									<i class="icon-plus"></i>
								</button> --%>
							</div>
						</td>
						<td>${ItemShop.price }VNĐ</td>
						<td>${ItemShop.price*ItemShop.soluong }VNĐ</td>
						<td>
							<div class="checkbox">
								<label> <input type="checkbox" value="${ItemShop.id}"
									name="xoahang"> Xóa
								</label>
							</div>
						</td>
					</tr>
					<c:set value="${tongtien+ItemShop.price*ItemShop.soluong }"
						var="tongtien"></c:set>
				</c:forEach>
				<tr>
					<td colspan="6" style="text-align: right"><strong>Tổng
							tiền</td>
					<td class="label label-important" style="display: block"><strong>
							${tongtien } VNĐ </strong></td>
				</tr>
			</tbody>
		</table>
		<script type="text/javascript">
		function up(id) {
			var url='.'+id;
			var num=$(url).val()-1;
			$(url).attr({
				'value': num,
				}); 
		}
		function down(id) {
			var url='.'+id;
			var num=$(url).val()-(-1);
			$(url).attr({
				'value': num,
				});
		}
	</script>
		<button class="btn btn-small btn-danger">
			<i class="icon-refresh"></i> Cập nhật
		</button>
		<a href="${pageContext.request.contextPath}/gio-hang/thong-tin"
			class="btn btn-small pull-right btn-danger">Thanh toán <i
			class="icon-arrow-right"></i></a>
	</form>
</div>
