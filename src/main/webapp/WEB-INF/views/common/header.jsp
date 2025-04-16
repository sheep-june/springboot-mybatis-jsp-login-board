<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!--
	JSP 상단 설정:
	- page 디렉티브: JSP의 인코딩 및 언어 설정
	- taglib 디렉티브: JSTL Core 라이브러리를 사용하기 위한 선언 (if/choose 등의 JSTL 태그 사용 가능)
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 공통 헤더 스타일 적용 (header.css) -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common/header.css">
</head>
<body>

<!-- 상단 헤더 영역 구성 -->
<div id="header">
	<!-- 좌측 사이트 제목 -->
	<div style="color:white;text-align:left;float:left;cursor:pointer">
		Spring_Project_Study_日本語のページ
	</div>

	<!-- 사용자 인증 여부에 따라 로그인/로그아웃 링크 표시 (JSTL choose 사용) -->
	<c:choose>
		<!-- 로그인 된 경우 -->
		<c:when test="${isAuthenticated != null && isAuthenticated == true}">
			<div style="float:right;">
				<!-- 로그아웃 링크 (Spring Security 처리 경로) -->
				<a href="${pageContext.request.contextPath}/logout" style="color:white;margin-right:15px;text-decoration:none;font-size:15px;">ログアウト</a>
			</div>
		</c:when>
		<!-- 로그인 안된 경우 -->
		<c:otherwise>
			<div style="float:right;">
				<!-- 로그인 페이지로 이동 -->
				<a href="${pageContext.request.contextPath}/loginPage" style="color:white;margin-right:15px;text-decoration:none;font-size:15px;">ログイン</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>