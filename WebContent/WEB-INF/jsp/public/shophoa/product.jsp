
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">${CName}</li>
	</ul>
	<h3>
		${CName} <small class="pull-right"> Hiện có ${sum} sản phẩm</small>
	</h3>
	<hr class="soft" />
	<p>
		Nhiều mẫu hoa đẹp, giá cả cạnh tranh, mua hàng dễ dàng,  <a href="#"
			style="color: red; font-size: 20px">đăng ký tài khoản</a> để được
		hưởng ưu đãi
	</p>
	<hr class="soft" />

	<form class="form-horizontal span6">
		<div class="control-group">
			
			<div class="btn-group m-r-sm mail-hidden-options"
				style="display: inline-block; margin: 0px; padding: 0px">
				<div class="btn-group">
					<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						aria-expanded="false"><i class="fa fa-eye"></i> Hiển thị <span
						class="caret"></span></a>
					<ul class="dropdown-menu dropdown-menu-left" role="menu">
						<li><a
							href="?h=6">6 sản phẩm</a></li>
						<li><a
							href="?h=12">12 sản phẩm</a></li>
						<li><a
							href="?h=24">24 sản phẩm</a></li>
						<li><a
							href="?h=48">48 sản phẩm</a></li>
						<li><a
							href="?h=${sum}">Tất cả</a></li>
					</ul>
				</div>
			</div>&nbsp;&nbsp;&nbsp; <span class="text-muted m-r-sm">Hiển thị ${rowcount} của
				${sum} </span>
		</div>
	</form>


	<div id="myTab" class="pull-right">
		<a href="#listView" data-toggle="tab"><span class="btn btn-large"><i
				class="icon-list"></i></span></a> <a href="#blockView" data-toggle="tab"><span
			class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
	</div>
	<br class="clr" />
	<div class="tab-content">
		<div class="tab-pane" id="listView">
			<c:forEach items="${lItem}" var="Item">
				<c:set value="${slugU.makeSlug(CName)}" var="slug"></c:set>
				<c:set value="${slugU.makeSlug(Item.name)}" var="slugm"></c:set>
				<div class="row">
					<div class="span2">
						<img src="${defines.FILE_PICTURE_URL}/${Item.picture}" alt="" />
					</div>
					<div class="span4">
						<h3>${Item.name}</h3>
						<hr class="soft" />
						<h5>${Item.name}</h5>
						<p>${Item.preview}</p>
						<a class="btn btn-small pull-right" href="${pageContext.request.contextPath}/${slug}-${lid}-${CId}/${slugm}-${Item.id}">Xem
							chi tiết</a> <br class="clr" />
					</div>
					<div class="span3 alignR">
						<form class="form-horizontal qtyFrm">
							<h3>Giá: ${decimalformat.change(Item.price)}</h3>
							<label class="checkbox"> <input type="checkbox">
								Chọn
							</label><br /> <a href="product_details.html"
								class="btn btn-large btn-primary"> Thêm vào giỏ <i
								class=" icon-shopping-cart"></i></a> <a href="product_details.html"
								class="btn btn-large"><i class="icon-zoom-in"></i></a>

						</form>
					</div>
				</div>
				<hr class="soft" />
			</c:forEach>
		</div>

		<div class="tab-pane  active" id="blockView">
			<ul class="thumbnails">
				<c:forEach items="${lItem}" var="Item">
				<c:set value="${slugU.makeSlug(CName)}" var="slug"></c:set>
				<c:set value="${slugU.makeSlug(Item.name)}" var="slugm"></c:set>
					<li class="span3">
						<div class="thumbnail">
							<a href="${pageContext.request.contextPath}/${slug}-${lid}-${CId}/${slugm}-${Item.id}"><img
								style="width: 160px; height: 160px"
								src="${defines.FILE_PICTURE_URL}/${Item.picture}" alt="" /></a>
							<div class="caption">
								<h5>${Item.name}<br>
									<a class="btn btn-primary" style="text-align: center;" href="#">Giá:
										${decimalformat.change(Item.price)} VNĐ</a>
								</h5>

								<h4 style="text-align: center">
									<a class="btn" href="${pageContext.request.contextPath}/${slug}-${lid}-${CId}/${slugm}-${Item.id}"> <i
										class="icon-zoom-in"></i>Xem
									</a> <a class="btn" href="#">Thêm vào giỏ<i
										class="icon-shopping-cart"></i></a>
								</h4>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			<hr class="soft" />
		</div>
	</div>

	<div class="pagination">
		<ul>
			<c:if test="${page>1}">
				<c:set value="?h=${h}&p=${page-1}" var="urlPrevious"></c:set>
			</c:if>
			<c:if test="${page==1}">
				<c:set value="#" var="urlPrevious"></c:set>
			</c:if>
			<li><a href="${urlPrevious}">&lsaquo;</a></li>

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
			<li><a href="#">&rsaquo;</a></li>
		</ul>
	</div>
	<br class="clr" />
</div>
