
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span
			class="divider">/</span></li>
		<li class="active">Giới thiệu</li>
	</ul>
	<p>
		Là một trong những <a href="${pageContext.request.contextPath}/" style="color: red; font-weight: bold;">SHOP HOA</a> Lớn nhất VIỆT NAM, chugs tôi tự hào mang đến cho bạn những sản phẩm tốt nhất!!!:)))
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
							href="?h=5">5 tin</a></li>
						<li><a
							href="?h=10">10 tin</a></li>
						<li><a
							href="?h=20">20 tin</a></li>
						<li><a
							href="?h=50">50 tin</a></li>
						<li><a
							href="?h=${sum}">Tất cả</a></li>
					</ul>
				</div>
			</div>&nbsp;&nbsp;&nbsp; <span class="text-muted m-r-sm">Hiển thị ${rowcount} của
				${sum} </span>
		</div>
	</form>

	<br class="clr" />
	<div >
		<div  class=" active" id="listView">
			<c:forEach items="${lItem}" var="Item">
				<c:set value="${slugU.makeSlug(Item.name)}" var="slug"></c:set>
				<div class="row" >
					<div class="span2">
						<img src="${defines.FILE_PICTURE_URL}/${Item.picture}" alt="" />
					</div>
					<div class="span7" >
						<h3>${Item.name}</h3>
						<hr class="soft" />
						<h5><a style="color: blue;" href="${pageContext.request.contextPath}/gioi-thieu/${slug}-${Item.id}">${Item.name}</a></h5>
						<p>${Item.preview}</p>
						<a class="btn btn-small pull-right" href="${pageContext.request.contextPath}/gioi-thieu/${slug}-${Item.id}">Chi tiết</a> <br class="clr" />
					</div>
				</div>
				<hr class="soft" />
			</c:forEach>
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
