<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<div class="span9" id="mainCol">
	<ul class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/">Trang chủ</a> <span class="divider">/</span></li>
		<li><a href="${pageContext.request.contextPath}/gioi-thieu/">Giới thiệu</a> <span class="divider">/</span></li>
		<li class="active">${Item.name}</li>
	</ul>
	<h3>${Item.name}</h3>
	<hr class="soft" />
	<p>${Item.detail}</p>
</div>
