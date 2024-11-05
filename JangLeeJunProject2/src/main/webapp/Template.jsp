<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%--@ include file="/template/Config.jsp" --%>
		<jsp:include page="/template/Config.jsp"/>
		<title>Template.jsp</title>
	
	</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<%--@ include file="/template/Header.jsp" --%>	
			<jsp:include page="/template/Header.jsp"/>		
			<!-- 컨텐츠 시작 -->	
			<div class="p-5 custom-bg text-white">
				<h1>제목</h1>
			</div>	
			<fieldset class="border rounded-3 p-3">
				<legend class="float-none w-auto px-3">카테고리</legend>
				<h1 class="display-6">제목입니다</h1>
				<ul class="list-group list-group-numbered">
					<li class="list-group-item">아이템</li>				
				</ul>
			</fieldset>		
			<!-- 컨텐츠 끝 -->
			<%--@ include file="/template/Footer.jsp" --%>
			<jsp:include page="/template/Footer.jsp"/>		
		</div><!-- container-fluid -->
	</div><!--container  -->
</body>
</html>
			