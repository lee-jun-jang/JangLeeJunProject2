<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%--@ include file="/template/Config.jsp" --%>
<jsp:include page="/template/Config.jsp" />
<title>View.jsp</title>
<style>
th.bg-dark.text-white {
	text-align: center;
}

.custom-bg {
	margin-top: 20px;
	margin-bottom: 20px;
}

.rounded-table {
	margin-top: 20px;
}

h1 small {
	font-weight: bold;
}
</style>

</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<%--@ include file="/template/Header.jsp" --%>
			<jsp:include page="/template/Header.jsp" />

			<!-- 컨텐츠 시작 -->
			<div class="p-5 custom-bg custom-container">
				<h1>
					자료실 <small>상세</small>
				</h1>
			</div>

			<table class="table table-hover rounded-table">
				<tbody>
					<tr>
						<th class=" w-25 ">번호</th>
						<td>${record.id }</td>
					</tr>
					<tr>
						<th class=" w-25 ">올린이</th>
						<td>${record.username }</td>
					</tr>
					<tr>
						<th class=" w-25 ">등록일</th>
						<td>${record.postDate }</td>
					</tr>
					<tr>
						<th class=" w-25 ">제목</th>
						<td>${record.title }</td>
					</tr>
					<tr>
						<th class=" " colspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="2">${record.content }</td>
					</tr>

				</tbody>
			</table>

			<!-- 수정/삭제/목록 컨트롤 버튼 -->
			<div class="text-center">
				<c:choose>
					<c:when test="${sessionScope.username == record.username}">
						<form id="editForm" action="<c:url value='/Dataroom/Edit.ict' />"
							method="post" style="display: none;">
							<input type="hidden" name="id" value="${record.id }" /> <input
								type="hidden" name="title" value="${record.title }" /> <input
								type="hidden" name="content" value="${record.content }" />
						</form>
						<a href="#" class="btn btn-custom edit-button">수정</a>
						<a href="#" class="btn btn-custom delete-button">삭제</a>
						<a href="<c:url value='/Dataroom/List.ict' />"
							class="btn btn-custom">목록</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/Dataroom/List.ict' />"
							class="btn btn-custom">목록</a>
					</c:otherwise>
				</c:choose>
			</div>


			<!-- 컨텐츠 끝 -->
			<%--@ include file="/template/Footer.jsp" --%>
			<jsp:include page="/template/Footer.jsp" />
		</div>
		<!-- container-fluid -->
	</div>
	<!--container  -->

	
	<script>
		//수정 버튼 클릭시 이벤트 처리
		     var editButton = document.querySelector('.edit-button');
        editButton.onclick = (e) => {
            e.preventDefault();
            if (confirm('수정 하시겠습니까?')) {
            	window.location.href = '<c:url value="/Dataroom/Edit.ict?id=${record.id}" />';
            }
        };

        var deleteButton = document.querySelector('.delete-button');
        deleteButton.onclick = (e) => {
            e.preventDefault();
            if (confirm('정말로 삭제 하시겠습니까?')) {
                window.location.href = '<c:url value="/Dataroom/Delete.ict?id=${record.id}" />';
            }
        };
	</script>
</body>
</html>
