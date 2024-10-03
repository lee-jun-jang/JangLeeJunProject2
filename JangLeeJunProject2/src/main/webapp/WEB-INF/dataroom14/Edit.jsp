<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.custom-bg {
	margin-top: 20px;
	margin-bottom: 20px;
}

h1 small {
	font-weight: bold;
}
</style>
<jsp:include page="/template/Config.jsp" />
<title>Edit.jsp</title>

</head>
<body>
	<div class="container">
		<div class="container-fluid">

			<jsp:include page="/template/Header.jsp" />

			<!-- 컨텐츠 시작 -->
			<div class="p-5 custom-bg">
				<h1>
					자료실 <small>수정</small>
				</h1>
			</div>
			<c:if test="${! empty errorMsg}">
				<div class="d-flex" id="errorMsg">
					<div class="alert alert-success alert-dismissible my-2 ">
						<button type="button" class="btn-custom" data-bs-dismiss="alert"></button>
						<strong>업로드 실패!</strong> ${errorMsg }
					</div>
				</div>
			</c:if>
			<form action="<c:url value="/Dataroom/Edit.ict"/>" method="post">
				<input type="hidden" name="id" value="${record.id }" />
				<div class="mb-3 mt-3">
					<label for="username" class="form-label">아이디</label> <br /> <span>${sessionScope.username}</span>
					<input type="hidden" name="username"
						value="${sessionScope.username}" />
				</div>
				<div class="mb-3">
					<label for="title" class="form-label">제목</label> <input type="text"
						class="form-control" id="title" placeholder="제목을 입력하세요"
						name="title" value="${record.title }">
				</div>
				<label for="content" class="form-label">내용</label>
				<textarea placeholder="내용을 입력하세요" class="form-control" rows="5"
					id="content" name="content">${record.content }</textarea>
				<button type="submit" class="btn btn-custom mt-2">수정</button>
			</form>


			<!-- 컨텐츠 끝 -->
			<%--@ include file="/template/Footer.jsp" --%>
			<jsp:include page="/template/Footer.jsp" />
		</div>
		<!-- container-fluid -->
	</div>
	<!--container  -->

</body>
</html>
