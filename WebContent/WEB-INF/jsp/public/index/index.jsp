<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<div class="well well-small">
		<h4>
			Các sản phẩm tại shop <small class="pull-right">Hơn 200 loại
				hoa khác nhau</small>
		</h4>
		<div class="row-fluid">
			<div id="featured" class="carousel slide">
				<div class="carousel-inner">
					<c:set value="0" var="demK">
					</c:set>
					<c:set value="active" var="at">
					</c:set>
					<c:forEach begin="1" step="4" end="${sumPro}">
						<c:if test="${demK!=0}">
							<c:set value="" var="at">
							</c:set>
						</c:if>
						<div class="item ${at}">
							<ul class="thumbnails">
								<c:set value="1" var="dem">
								</c:set>
								<c:forEach items="${lProduct}" var="ItemPro">
									<c:set value="${slugU.makeSlug(ItemPro.name)}" var="slugm"></c:set>
									<c:forEach items="${lType}" var="ItemType">
										<c:if test="${ItemPro.id_type==ItemType.id_type }">
											<c:set value="${slugU.makeSlug(ItemType.name)}" var="slug"></c:set>
											<c:set value="${slugU.makeSlug(ItemType.id_type)}" var="pid"></c:set>
										</c:if>
									</c:forEach>
									<c:if test="${dem>(demK*4) && dem<=((demK+1)*4) }">
										<li class="span3">
											<div class="thumbnail">
												<a href="${pageContext.request.contextPath}/${slug}-1-${pid}/${slugm}-${ItemPro.id}"><img
													style="width: 160px; height: 160px"
													src="${defines.FILE_PICTURE_URL}/${ItemPro.picture}" alt=""></a>
												<div class="caption">
													<h5>${ItemPro.name}</h5>
													<h5>Giá: ${decimalformat.change(ItemPro.price)} (VNĐ)</h5>
													<h4>
														<a class="btn" href="${pageContext.request.contextPath}/${slug}-1-${pid}/${slugm}-${ItemPro.id}">Chi tiết</a>
													</h4>
												</div>
											</div>
										</li>
									</c:if>
									<c:set value="${dem+1}" var="dem"></c:set>
								</c:forEach>
							</ul>
						</div>
						<c:set value="${demK+1}" var="demK">
						</c:set>
					</c:forEach>

				</div>
				<a class="left carousel-control" href="#featured" data-slide="prev">‹</a>
				<a class="right carousel-control" href="#featured" data-slide="next">›</a>
			</div>
		</div>
	</div>
	<h4>Sản phẩm bán chạy</h4>
	<ul class="thumbnails">
		<c:set value="1" var="i"></c:set>
		<c:forEach items="${lProductSortBuy}" var="ItemSortBuy">
			<c:set value="${slugU.makeSlug(ItemSortBuy.name)}" var="slugm"></c:set>
			<c:forEach items="${lType}" var="ItemType">
				<c:if test="${ItemSortBuy.id_type==ItemType.id_type }">
					<c:set value="${slugU.makeSlug(ItemType.name)}" var="slug"></c:set>
					<c:set value="${slugU.makeSlug(ItemType.id_type)}" var="pid"></c:set>
				</c:if>
			</c:forEach>
			<c:if test="${i<=6}">
				<li class="span3">
					<div class="thumbnail">
						<span><img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico/top${i}.png"/></span><a href="${pageContext.request.contextPath}/${slug}-1-${pid}/${slugm}-${ItemSortBuy.id}"><img
							style="width: 160px; height: 160px"
							src="${defines.FILE_PICTURE_URL}/${ItemSortBuy.picture}" alt="" /></a>
						<div class="caption">
							<h5>${ItemSortBuy.name}</h5>
							<!-- <p>Lorem Ipsum is simply dummy text.</p> -->

							<h4 style="text-align: center">
								<a class="btn btn-primary" href="javascript:void(0)">Giá: ${decimalformat.change(ItemSortBuy.price)} VNĐ</a><br> <a
									class="btn" href="${pageContext.request.contextPath}/${slug}-1-${pid}/${slugm}-${ItemSortBuy.id}"> <i
									class="icon-zoom-in"></i>Xem</a> <a class="btn" href="${pageContext.request.contextPath}/${slug}-1-${pid}/${slugm}-${ItemSortBuy.id}"><i class="icon-shopping-cart"></i> Thêm vào giỏ
								</a>
							</h4>
						</div>
					</div>
				</li>
			</c:if>
			<c:set value="${i+1}" var="i"></c:set>
		</c:forEach>
	</ul>

</div>