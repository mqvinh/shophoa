<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<c:if test="${lid==1}">
		<c:set value="${ItemC.id_type}" var="id"></c:set>
	</c:if>
	<c:if test="${lid==2}">
		<c:set value="${ItemC.id_species}" var="id"></c:set>
	</c:if>
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li><a
			href="${pageContext.request.contextPath}/san-pham/${lid}-${id}">${ItemC.name}</a>
			<span class="divider">/</span></li>
		<li class="active">${Item.name}</li>
	</ul>
	<div id="thongbao" style="text-align: center;"></div>
	<div class="row">
		<div id="gallery" class="span3" style="text-align: center;">
			<img src="${defines.FILE_PICTURE_URL}/${Item.picture}"
				class="img-thumbnail" style="width: 100%" alt="${Item.name}" />

			<div class="btn-toolbar">
				<div class="btn-group">
					<span class="btn"><i class="icon-zoom-in"></i><a
						href="${defines.FILE_PICTURE_URL}/${Item.picture}"
						title="${Item.name}"> Xem Full</a></span>
				</div>
			</div>
		</div>
		<div class="span6">
			<h3>${Item.name}</h3>
			<!-- <small>- (14MP, 18x Optical Zoom) 3-inch LCD</small> -->
			<hr class="soft" />
			<form class="form-horizontal qtyFrm" method="post"
				action="javascript:void(0)">
				<div class="control-group">
					<label class="control-label"><span>Giá:
							${decimalformat.change(Item.price)} VNĐ</span></label>
					<div class="controls">
						<input style="width: 80px" type="number" class="span1"
							id="soluong" placeholder="Số lượng" value="1" name="soluong" />
							<form:errors path="objItem.name" cssStyle="color: red"></form:errors>
						<button onclick="addHoa(${Item.id})" type="submit"
							class="btn btn-large btn-primary pull-right">
							Thêm vào giỏ hàng <i class=" icon-shopping-cart"></i>
						</button>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><span>Số lượng còn:
							${Item.number} </span></label>
				</div>
			</form>
			<script type="text/javascript">
			var sumP=${sumProShop};
			var sum=${sumMoney};
			var id_array='${chuoiId}';
			
			function addHoa(aid) {
				var asoluong = $('#soluong').val();
				var soluongcon=${Item.number};
				if(asoluong>soluongcon){
					$('#thongbao').html('<div class="alert alert-danger alert-dismissable"'
							+'style="text-align: center;">'
							+'<button aria-hidden="true" data-dismiss="alert" class="close"'
								+'type="button">×</button>'
							+'Số hoa còn không đủ!!!'
						+'</div>');
				}else if(asoluong<0){
					$('#thongbao').html('<div class="alert alert-danger alert-dismissable"'
							+'style="text-align: center;">'
							+'<button aria-hidden="true" data-dismiss="alert" class="close"'
								+'type="button">×</button>'
							+'Số lượng hoa không được âm!!!'
						+'</div>');
				}else{
				var id_check='-'+'${Item.id}'+'-';
				if(id_array.indexOf(id_check)==-1){sumP=sumP+1;id_array=id_array+id_check;};
				sum=sum+asoluong*${Item.price};
				$.ajax({
					url : '${pageContext.request.contextPath}/gio-hang/them',
					type: 'POST', 
					dataType: 'html', 
					data: {id: aid,soluong :asoluong},
					beforeSend: function() {
						$('#thongbao').html('<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/loading.gif" alt="" />');
					},
					success: function(data) { 
						$('#thongbao').html('<div class="alert alert-success alert-dismissable"'
								+'style="text-align: center;">'
								+'<button aria-hidden="true" data-dismiss="alert" class="close"'
									+'type="button">×</button>'
								+'Thêm thành công!!!'
							+'</div>');
						$('#ajaxCart').html('<a id="myCart" href="${pageContext.request.contextPath}/gio-hang">'
											+'<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico-cart.png"'
											+'alt="cart">My shop [ '+sumP+' ]<span '
											+'class="badge badge-warning pull-right">'+sum+' VNĐ</span></a>');
						$('#mycartTop').html('<div class="pull-right">'
								+'<span class="btn btn-mini">'+sum+' VNĐ</span>'
								+'<a href="${pageContext.request.contextPath}/gio-hang">'
								+'<span class="btn btn-mini btn-primary">'
								+'<i class="icon-shopping-cart icon-white"></i> [ '+sumP+' ] sản phẩm trong shop của bạn!!! </span> </a>'
								+'</div>');
					}
				});
				}
				return false;
		
			}
</script>
			<hr class="soft" />
			<p>${Item.preview}</p>
			<a class="btn btn-small pull-right" href="#detail">Xem thêm</a> <br
				class="clr" /> <a href="#" name="detail"></a>
			<hr class="soft" />
		</div>

		<div class="span9">
			<ul id="productDetail" class="nav nav-tabs">
				<li class="active"><a href="#home" data-toggle="tab">Chi
						tiết</a></li>
				<li><a href="#profile" data-toggle="tab">Sản phẩm khác</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade active in" id="home">
					<h4>Thông tin chi tiết</h4>

					<p>${Item.detail}</p>
				</div>
				<div class="tab-pane fade" id="profile">
					<div id="myTab" class="pull-right">
						<a href="#listView" data-toggle="tab"><span
							class="btn btn-large"><i class="icon-list"></i></span></a> <a
							href="#blockView" data-toggle="tab"><span
							class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
					</div>
					<br class="clr" />
					<hr class="soft" />
					<div class="tab-content">
						<div class="tab-pane" id="listView">
						
							<c:if test="${lid==1}">
								<c:forEach items="${lType}" var="ItemTylq">
									<c:if test="${ItemTylq.id_type==pid}">
										<c:set value="${slugU.makeSlug(ItemTylq.name)}" var="slug"></c:set>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${lid==2}">
								<c:forEach items="${lSpecies}" var="ItemSplq">
									<c:if test="${ItemSplq.id_species==pid}">
										<c:set value="${slugU.makeSlug(ItemSplq.name)}" var="slug"></c:set>
									</c:if>
								</c:forEach>
							</c:if>
							
							<c:forEach items="${lItemLq}" var="ItemLq">
								<c:set value="${slugU.makeSlug(ItemLq.name)}" var="slugm"></c:set>
								<c:set value="1" var="dem"></c:set>
								<c:if test="${Item.id!=ItemLq.id && dem<=3}">
									<div class="row">
										<div class="span2">
											<img src="${defines.FILE_PICTURE_URL}/${ItemLq.picture}"
												alt="" />
										</div>
										<div class="span4">
											<h3>${ItemLq.name}</h3>
											<hr class="soft" />
											<h5>${ItemLq.name}</h5>
											<p>${ItemLq.preview}</p>
											<a class="btn btn-small pull-right"
												href="${pageContext.request.contextPath}/${slug}-${lid}-${pid}/${slugm}-${ItemLq.id}">Xem
												chi tiết</a> <br class="clr" />
										</div>
										<div class="span3 alignR">
											<form class="form-horizontal qtyFrm">
												<h3>Giá: ${ItemLq.price}</h3>
												<label class="checkbox"> <input type="checkbox">
													Chọn
												</label><br /> <a href="product_details.html"
													class="btn btn-large btn-primary"> Thêm vào giỏ <i
													class=" icon-shopping-cart"></i></a> <a
													href="product_details.html" class="btn btn-large"><i
													class="icon-zoom-in"></i></a>

											</form>
										</div>
									</div>
									<hr class="soft" />
									<c:set value="${dem+1}" var="dem"></c:set>
								</c:if>
							</c:forEach>
						</div>
						<div class="tab-pane active" id="blockView">
							<ul class="thumbnails">
								<c:forEach items="${lItemLq}" var="ItemLq">
									<c:set value="1" var="dem"></c:set>
									<c:if test="${Item.id!=ItemLq.id && dem<=3}">
										<li class="span3">
											<div class="thumbnail">
												<a
													href="${pageContext.request.contextPath}/${slug}-${lid}-${pid}/${slugm}-${ItemLq.id}"><img
													style="width: 160px; height: 160px"
													src="${defines.FILE_PICTURE_URL}/${ItemLq.picture}" alt="" /></a>
												<div class="caption">
													<h5>${ItemLq.name}<br> <a class="btn btn-primary"
															style="text-align: center;" href="#">Giá:
															${ItemLq.price} VNĐ</a>
													</h5>

													<h4 style="text-align: center">
														<a class="btn"
															href="${pageContext.request.contextPath}/${slug}-${lid}-${pid}/${slugm}-${ItemLq.id}">
															<i class="icon-zoom-in"></i>Xem
														</a> <a class="btn" href="#">Thêm vào giỏ<i
															class="icon-shopping-cart"></i></a>
													</h4>
												</div>
											</div>
										</li>
										<c:set value="${dem+1}" var="dem"></c:set>
									</c:if>
								</c:forEach>
							</ul>
							<hr class="soft" />
						</div>
					</div>
					<br class="clr">
				</div>
			</div>
		</div>

	</div>
</div>
