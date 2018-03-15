<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div id="carouselBlk">
	<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
			<%-- <div class="item active">
				<div class="container">
					<a href="register.html"><img style="width: 100%"
						src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/carousel/1.png"
						alt="special offers" /></a>
					<div class="carousel-caption">
						<h4>Second Thumbnail label</h4>
						<p>Cras justo odio, dapibus ac facilisis in, egestas eget
							quam. Donec id elit non mi porta gravida at eget metus. Nullam id
							dolor id nibh ultricies vehicula ut id elit.</p>
					</div>
				</div>
			</div> --%>
			<c:set value="1" var="cl"></c:set>
			<c:forEach items="${lSlide}" var="Item">
			<c:if test="${cl==1}">
				<div class="item active">
					<div class="container">
						<a href="register.html"><img style="width: 1170px; height: 480px"
							src="${defines.FILE_PICTURE_URL}/${Item.picture}"
							alt="" /></a>
						<div class="carousel-caption">
							<p>${Item.preview}</p>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${cl!=1}">
				<div class="item">
				<div class="container">
					<a href="register.html"><img style="width: 1170px; height: 480px"
						src="${defines.FILE_PICTURE_URL}/${Item.picture}"
						alt="" /></a>
					<div class="carousel-caption">
						<p>${Item.preview}</p>
					</div>
				</div>
			</div>
			</c:if>
			<c:set value="2" var="cl"></c:set>
			</c:forEach>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
</div>