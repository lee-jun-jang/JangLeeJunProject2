<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.JWTokens"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<style>
.custom-bg {
margin-top: 20px;
    margin-bottom: 7px; 
}
      h1 {
            font-weight: bold;
        }

</style>
<head>

<%@ include file="/template/Config.jsp"%>
<title>Login.jsp</title>

</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<%@ include file="/template/Header.jsp"%>
			<!-- 컨텐츠 시작 -->
			<div class="p-5 custom-bg  custom-container">
				</i><h1>로그인</h1>
			</div>
			<fieldset class="rounded-3 p-3">
				<legend class="float-none w-auto px-3"></legend>
				<form action="<c:url value="/Dataroom/Login.ict"/>" method="post">
					<div class="row">
						<div class="col-4">
							<input type="text" class="form-control" placeholder="아이디 입력" name="username" id="username" >
						</div>
						<div class="col-4">
							<input type="password" class="form-control" placeholder="비밀번호 입력" name="password" id="password">
						</div>
						<div class="col-1">
							<button type="submit" class="btn btn-custom">확인</button>
						</div>
					</div>
				</form>
				  <div class="d-flex ${empty errorMsg ? 'd-none' : 'd-block'}" id="errorMsg">
                    <div class="alert alert-success alert-dismissible my-2 w-50">
                        <button type="button" class="btn-custom" data-bs-dismiss="alert"></button>
                        <strong>로그인 실패!</strong> ${errorMsg}
                    </div>
                </div>
			</fieldset>
			<!-- 컨텐츠 끝 -->
			<%@ include file="/template/Footer.jsp"%>
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
        if (!form.username.value.trim()) {
            alert("아이디를 입력하세요");
            e.preventDefault(); 
            return;
        } 

        //비밀번호 유효성 검사
        if (!form.password.value.trim()) {
            alert("비밀번호를 입력하세요");
            e.preventDefault(); 
            return;
        
}
});
});
</script>
</html>
