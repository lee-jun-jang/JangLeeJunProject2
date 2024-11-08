<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Write.jsp</title>

</head>
<body>
	<div class="container">
		<div class="container-fluid">
			
			<jsp:include page="/template/Header.jsp" />
			
			<!-- 컨텐츠 시작 -->
			<div class="p-5 custom-bg custom-container">
				<h1>자료실 <small>등록</small></h1>
			</div>
			<form action="<c:url value='/Dataroom/Write.ict'/>" method="post">
				<div class="mb-3 mt-3">
                    <label for="username" class="form-label">아이디</label> <br/>
                    <span>${sessionScope.username}</span>
                    <input type="hidden" name="username" value="${sessionScope.username}"/>
                </div>           
				<div class="mb-2 mt-3">
					<label for="title" class="form-label">제목</label> <input
						type="text" class="form-control" id="title"
						placeholder="제목을 입력하세요" name="title" value="${empty param.title ?"":param.title }">
				</div>	
				<label for="content" class="form-label">내용</label>
				<textarea placeholder="내용을 입력하세요" class="form-control" rows="5" id="content" name="content">${empty param.content ?"":param.content}</textarea>
				<button type="submit" class="btn btn-custom mt-2">등록</button>
			</form>
                </div>
			<!-- 컨텐츠 끝 -->
			<%--@ include file="/template/Footer.jsp" --%>
			<jsp:include page="/template/Footer.jsp" />
		</div>
		<!-- container-fluid -->
	</div>
	<!--container  -->
	
</body>
<script>
window.addEventListener("DOMContentLoaded", function(e) {
	
	//DOM API 사용
	var form = document.querySelector("form");

	form.addEventListener("submit", function(e) {
		   //아이디 유효성 검사
        if (!form.title.value.trim()) {
            alert("제목을 입력하세요");
            e.preventDefault(); 
            return;
        } 

        //비밀번호 유효성 검사
        if (!form.content.value.trim()) {
            alert("내용을 입력하세요");
            e.preventDefault(); 
            return;
        
}
});
});
</script>
</html>
