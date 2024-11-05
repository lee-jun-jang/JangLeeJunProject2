<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%--@ include file="/template/Config.jsp" --%>
<jsp:include page="/template/Config.jsp" />
<title>List.jsp</title>
<style>
.custom-bg {
margin-top: 20px;
    margin-bottom: 7px; 
}
      h1 small {
            font-weight: bold;
        }
        .no-underline {
    text-decoration: none;
    color:#3A3A3A;
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
					자료실 <small>목록</small>
				</h1>
			</div>
			<div class="my-2 text-end">
				<a href="<c:url value="/Dataroom/Write.ict"/>"
					class="btn btn-custom">자료 등록</a>
			</div>
			<table class="table table-hover text-center rounded-table">
				<thead class="custom-bg">
					<tr>
						<th class="col-1">번호</th>
						<th class="col-auto">제목</th>
						<th class="col-2">올린이</th>
						<th class="col-2">등록일</th>
					</tr>
				</thead>
				<tbody class="custom-bg">
					<c:forEach var="record" items="${records}" varStatus="loop">
						<tr>
							<td>${totalRecordCount -(((nowPage-1)*pageSize)+loop.index)}</td>
							<td><a href="<c:url value='/Dataroom/View.ict?id=${record.id}'/>" class="no-underline">${record.title}</a></td>
							<td>${record.username}</td>
							<td>${record.postDate}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty records}">
						<tr>
							<td colspan="4">아무 글도 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<!-- 페이징 출력 -->
			${paging}
			<!-- 컨텐츠 끝 -->
			<jsp:include page="/template/Footer.jsp" />
		</div>
		<!-- container-fluid -->
	</div>
	<!--container  -->

</body>
</html>
