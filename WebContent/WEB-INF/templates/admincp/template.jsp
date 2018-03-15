<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>

<tiles:insertAttribute name="header"></tiles:insertAttribute>
<!-- Header End====================================================================== -->

<div class="page-container" >
	<div class="left-content">
		<div class="mother-grid-inner">
			<!--topbar start here-->
			<tiles:insertAttribute name="topbar"></tiles:insertAttribute>

			<!--inner block start here-->
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
			<!--inner block end here-->

			<!-- Footer ================================================================== -->
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
			<!-- End Footer ================================================================== -->
		</div>
	</div>
	<!--leftbar-->
	<tiles:insertAttribute name="leftbar"></tiles:insertAttribute>