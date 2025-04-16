<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/login/style.css">

</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="login-container-wrapper">
		<div id="login-container">
			<h2>ログイン</h2>
			
			<!-- 로그인 실패시 오류 메시지 표시 -->
			<c:if test="${not empty param.error}">
				<p style="color:red;">入力されたIDまたはパスワードが正しくありません。</p>
			</c:if>
				
			<form action="${pageContext.request.contextPath}/login" method="post">
				<input type="hidden" name="_csrf" value="${_csrf.token}">
				
			<div class="input-group">
				<label for="username">ユーザーID</label>
				<input type="text" id="username" name="username" required/>
			</div>
			<div class="input-group">
				<label for="password">パスワード</label>
				<input type="password" id="password" name="password" required/>
			</div>
				<button type="submit" id="login-button">ログイン</button>
			</form>	
			<div id="register-link">
				<a href="${pageContext.request.contextPath}/registerPage">新規登録</a> 
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>