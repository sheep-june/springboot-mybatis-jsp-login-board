<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<!-- 로그인 페이지 전용 CSS 적용 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/login/style.css">
</head>
<body>

<!-- 공통 헤더 포함 (JSP include) -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 로그인 폼 컨테이너 -->
<div id="login-container-wrapper">
	<div id="login-container">
		<h2>ログイン</h2>

		<!-- 로그인 실패 시 오류 메시지 표시 (JSTL 조건문 사용) -->
		<c:if test="${not empty param.error}">
			<p style="color:red;">入力されたIDまたはパスワードが正しくありません。</p>
		</c:if>

		<!-- Spring Security 로그인 처리용 form (POST /login) -->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<!-- CSRF 보호 토큰 hidden 필드 (Spring Security) -->
			<input type="hidden" name="_csrf" value="${_csrf.token}">

			<!-- 사용자 ID 입력 -->
			<div class="input-group">
				<label for="username">ユーザーID</label>
				<input type="text" id="username" name="username" required/>
			</div>

			<!-- 비밀번호 입력 -->
			<div class="input-group">
				<label for="password">パスワード</label>
				<input type="password" id="password" name="password" required/>
			</div>

			<!-- 로그인 버튼 -->
			<button type="submit" id="login-button">ログイン</button>
		</form>

		<!-- 회원가입 링크 -->
		<div id="register-link">
			<a href="${pageContext.request.contextPath}/registerPage">新規登録</a> 
		</div>
	</div>
</div>

<!-- 공통 푸터 포함 -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
