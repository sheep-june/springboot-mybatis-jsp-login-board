<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring study 001</title>

<!-- 회원가입 전용 스타일 적용 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/register/style.css">
</head>
<body>

<!-- 공통 헤더 include -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 회원가입 폼 컨테이너 -->
<div id="register-container-wrapper">
	<div class="register-container">
		<h2>회원가입</h2>
		<!-- POST 방식 회원가입 요청 (Spring Security 연동됨) -->
		<form action="${pageContext.request.contextPath}/register" method="post">

			<!-- CSRF 보안 토큰 hidden 필드 -->
            <input type="hidden" name="_csrf" value="${_csrf.token}">

			<!-- 사용자 ID 입력 -->
			<div class="input-group">
				<label for="username">아이디</label>
				<input type="text" id="username" name="username" required>
			</div>

			<!-- 비밀번호 입력 -->
			<div class="input-group">
				<label for="password">비밀번호</label>
				<input type="password" id="password" name="password" required>
			</div>

			<!-- 작성자 (닉네임 또는 이름) 입력 -->
			<div class="input-group">
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" required>
			</div>

			<!-- 회원가입 버튼 -->
			<div class="input-group">
				<button type="submit" class="register-button">회원가입</button>
			</div>

			<!-- 로그인 페이지 링크 -->
			<div class="login-link">
				<a href="${pageContext.request.contextPath}/loginPage">이미 계정이 있으신가요?</a>
			</div>
		</form>
	</div>
</div>

<!-- 공통 푸터 include -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>