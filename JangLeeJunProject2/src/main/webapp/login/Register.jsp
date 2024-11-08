<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.custom-bg {
margin-top: 20px;
    margin-bottom: 7px; 
}
      h1 {
            font-weight: bold;
        }
   
</style>
<meta charset="UTF-8">
<%@ include file="/template/Config.jsp"%>
<title>Register.jsp</title>
</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<%@ include file="/template/Header.jsp"%>
			<!-- 컨텐츠 시작 -->
			<div class="p-5 custom-bg  custom-container">
				<h1>회원가입</h1>
			</div>
			<fieldset class="rounded-3 p-3 ">
				<legend class="float-none w-auto px-3"></legend>

				<form action="<c:url value="/Dataroom/Register.ict"/>" method="post">
					<div class="mb-3">
						<label for="username" class="form-label">아이디</label> <input
							type="text" class="form-control" id="username"
							placeholder="아이디 입력" name="username">
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">비밀번호</label> <input
							type="password" class="form-control" id="password"
							placeholder="비밀번호 입력" name="password">
					</div>
					<div class="mb-3 mt-3">
						<label for="name" class="form-label">이름</label> <input type="text"
							class="form-control" id="name" placeholder="이름 입력" name="name">
					</div>

					<div class="mb-3">
						<label class="form-label">성별</label>
						<div class="d-flex">
							<div class="form-check">
								<input type="radio" class="form-check-input" id="radio1"
									name="gender" value="남자"> <label
									class="form-check-label" for="radio1">남자</label>
							</div>
							<div class="form-check mx-3">
								<input type="radio" class="form-check-input" id="radio2"
									name="gender" value="여자"> <label
									class="form-check-label" for="radio2">여자</label>
							</div>

						</div>
						<div class="my-3">
							<label class="form-label">관심사항</label>
							<div class="d-flex">
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="check1"
										name="inter" value="정치"> <label
										class="form-check-label" for="check1">정치</label>
								</div>
								<div class="form-check mx-3">
									<input type="checkbox" class="form-check-input" id="check2"
										name="inter" value="경제"> <label
										class="form-check-label" for="check2">경제</label>
								</div>
								<div class="form-check">
									<input type="checkbox" class="form-check-input" id="check3"
										name="inter" value="스포츠"> <label
										class="form-check-label" for="check3">스포츠</label>
								</div>
								<div class="form-check mx-3">
									<input type="checkbox" class="form-check-input" id="check4"
										name="inter" value="연예"> <label
										class="form-check-label" for="check4">연예</label>
								</div>
							</div>
						</div>
						<label for="sel1" class="form-label">학력사항</label> <select
							class="form-select" id="sel1" name="grade">
							<option value="">학력을 선택하세요</option>
							<option value="ele">초등학교</option>
							<option value="mid">중학교</option>
							<option value="hig">고등학교</option>
							<option value="uni">대학교</option>
						</select> <label for="comment" class="mb-3 mt-3">자기소개</label>
						<textarea class="form-control" rows="5" id="comment" name="info"></textarea>

					</div>
					<button type="submit" class="btn btn-custom">확인</button>
				</form>
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


        //성별 유효성 검사
        var genderCheck = Array.from(form.gender).some(input => input.checked);
        if (!genderCheck) {
        	  alert("성별을 선택하세요");
              e.preventDefault();
              return;
        }

        //관심사항 유효성 검사
        var interestCheck = Array.from(form.inter).filter(input => input.checked);
        if (interestCheck.length < 2) {
             alert("관심사항은 2개 이상 선택하세요");
             e.preventDefault(); 
             return;
        }

        //학력 유효성 검사
        if (!form.grade.value.trim()) {
        	 alert("학력을 선택하세요");
             e.preventDefault();
             return;
        }

        //자기소개 유효성 검사
        if (!form.info.value.trim()) {
            alert("자기소개를 입력하세요");
            e.preventDefault();
            return;
        }
    });
});
</script>
</html>
