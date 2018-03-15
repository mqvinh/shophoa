<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div id="sidebar" class="span3">
	<div class="well well-small">
		<div id="ajaxCart">
			<a id="myCart" href="${pageContext.request.contextPath}/gio-hang">
			<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/ico-cart.png"
			alt="cart">My shop [ ${sumProShop} ]<span
			class="badge badge-warning pull-right">${sumMoney} VNĐ</span></a>
		</div>
	</div>
	<c:set value="style='display: none'" var="kieu"></c:set>
	<c:set value="" var="loai"></c:set>
	<c:if test="${lid==1}">
		<c:set value="style='display: none'" var="kieu"></c:set>
		<c:set value="" var="loai"></c:set>
	</c:if>
	<c:if test="${lid==2}">
		<c:set value="" var="kieu"></c:set>
		<c:set value="style='display: none'" var="loai"></c:set>
	</c:if>
	<h3 class="well well-small"
		style="padding-bottom: 0px; margin-bottom: 0px">Danh mục sản phẩm</h3>
	<ul id="sideManu" class="nav nav-tabs nav-stacked">
		<li class="subMenu "><a href="javascript: void(0)"> Loại hoa
				[${sumPro}] </a>
			<ul ${loai}>
				<c:forEach items="${lType}" var="ItemP">
				<c:set value="${slugU.makeSlug(ItemP.name)}" var="slug"></c:set>
					<c:set value="0" var="check"></c:set>
					<c:forEach items="${lNType}" var="ItemNP">
						<c:if test="${ItemP.id_type==ItemNP.id_type }">
						<li><a href="${pageContext.request.contextPath}/${slug}-1-${ItemNP.id_type}"><i class="icon-chevron-right"></i>${ItemNP.name} (${ItemNP.num})</a></li>
						<c:set value="1" var="check"></c:set>
						</c:if>
					</c:forEach>
					<c:if test="${check==0}">
					<li><a href="${pageContext.request.contextPath}/${slug}-1-${ItemP.id_type}"><i class="icon-chevron-right"></i>${ItemP.name} (0)</a></li>
					</c:if>
				</c:forEach>
			</ul></li>
		<li class="subMenu" ><a href="javascript: void(0)"> Kiểu
				hoa [${sumPro}]</a>
			<ul ${kieu}>
				<c:forEach items="${lSpecies}" var="ItemS">
				<c:set value="${slugU.makeSlug(ItemS.name)}" var="slug"></c:set>
					<c:set value="0" var="check"></c:set>
					<c:forEach items="${lNSpecies}" var="ItemNS">
						<c:if test="${ItemS.id_species==ItemNS.id_species }">
							<li><a  href="${pageContext.request.contextPath}/${slug}-2-${ItemNS.id_species}"><i
								class="icon-chevron-right"></i>${ItemNS.name} (${ItemNS.num})</a></li>
							<c:set value="1" var="check"></c:set>
						</c:if>
					</c:forEach>
					<c:if test="${check==0}">
						<li><a class="active" href="${pageContext.request.contextPath}/${slug}-2-${ItemS.id_species}"><i
								class="icon-chevron-right"></i>${ItemS.name} (0)</a></li>
					</c:if>
				</c:forEach>
			</ul></li>
			
	</ul>
	<br />
	<c:forEach items="${ItemAdver}" var="Itemad">
	<div class="thumbnail">
		<img
			src="${defines.FILE_PICTURE_URL}/${Itemad.picture}"
			alt="${Itemad.picture}" />
		<div class="caption">
			<h4 style="text-align: center">
				<a class="btn" href="${Itemad.link}"> <i
					class="icon-zoom-in"></i>Click here</a>
			</h4>
		</div>
	</div>
	<br />
	</c:forEach>
	<div class="thumbnail">
		<img
			src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/payment_methods.png"
			title="Bootshop Payment Methods" alt="Payments Methods">
		<div class="caption">
			<h5>Payment Methods</h5>
		</div>
	</div>
</div>
