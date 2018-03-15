<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>

<tiles:insertAttribute name="header"></tiles:insertAttribute>
<!-- Header End====================================================================== -->

<c:if test="${index eq 'index' }">
	<tiles:insertAttribute name="slide"></tiles:insertAttribute>
</c:if>

<div id="mainBody">
	<div class="container">
			<c:choose>
				<c:when test="${contact eq 'contact' }">
					<!-- Content ================================================== -->
					<tiles:insertAttribute name="content"></tiles:insertAttribute>
					<!-- Content end=============================================== -->
				</c:when>
				<c:otherwise>
					<div class="row">
						<!-- Leftbar ================================================== -->
						<tiles:insertAttribute name="leftbar"></tiles:insertAttribute>
						<!-- Leftbar end=============================================== -->

						<!-- Content ================================================== -->
						<tiles:insertAttribute name="content"></tiles:insertAttribute>
						<!-- Content end=============================================== -->
					</div>
				</c:otherwise>
			</c:choose>
	</div>
</div>

<!-- Footer ================================================================== -->
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
