<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- CSRF 보안 토큰: 자바스크립트 fetch 요청 시 메타 태그에서 읽음 -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>spring study 001</title>

<!-- 메인 스타일시트 로드 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

<!-- 공통 헤더 include -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 공지사항 메인 영역 -->
<div id="container">
	<div id="menuAdmin">
		<h2 class="menuAdminH2">공지사항</h2>

		<!-- 관리자 또는 매니저 권한 보유자만 작성 버튼 노출 -->
		<c:if test="${MANAGER == true}">
			<!-- 글쓰기 페이지로 이동 -->
			<button type="button" onclick="location.href=`${pageContext.request.contextPath}/noticeAddPage` ">작성</button>
		</c:if>

		<!-- 게시글 목록이 동적으로 렌더링될 위치 -->
		<div id="menuList"></div>
	</div>
</div>

<!-- 공통 푸터 include -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<!-- 자바스크립트 파일 로드: 게시글 목록 조회 및 이벤트 처리 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</body>
</html>