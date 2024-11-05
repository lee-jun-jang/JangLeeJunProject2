<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 어느 컨트롤러에서 왔는지(포워드 되었는지)에 따라 분기 -->
<c:choose>
	<c:when test="${WHERE=='INS'}">
		<c:set var="successMessage" value="입력성공 했어요"/>
		<c:set var="failureMessage" value="입력실패 했어요"/>
		<c:set var="successUrl" value="/Dataroom/List.ict"/>
	</c:when>
	<c:when test="${WHERE=='EDT'}">
		<c:set var="successMessage" value="수정성공 했어요"/>
		<c:set var="failureMessage" value="수정실패 했어요"/>
		<c:set var="successUrl" value="/Dataroom/View.ict?id=${param.id}"/>
	</c:when>
	<c:otherwise>
		<c:set var="successMessage" value="삭제성공 했어요"/>
		<c:set var="failureMessage" value="삭제실패 했어요"/>
		<c:set var="successUrl" value="/Dataroom/List.ict"/>	
	</c:otherwise>
</c:choose>






