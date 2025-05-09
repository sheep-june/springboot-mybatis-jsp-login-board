<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항조회</title>

<!-- CSRF 보안 토큰 설정: JavaScript에서 fetch 전송 시 사용됨 -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- 페이지 전용 CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeCheck/style.css">
</head>
<body>

<!-- 공통 상단 헤더 포함 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 공지사항 상세 조회 폼 -->
<form id="menuForm">
    <div id="container">
        <div id="menuAdmin">
            <h2 id="menuAdminH2">공지사항 조회</h2>

            <!-- 게시글 고유 ID (hidden) -->
            <label for="memID">회원아이디</label>
            <input type="hidden" id="idx" name="idx" placeholder="idx" maxlength="20" value="${menu.idx}" readonly style="background:#e0e0e0">

            <!-- 회원 ID 표시 (readonly) -->
            <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${menu.memID}" readonly style="background:#e0e0e0">

            <!-- 제목 표시 -->
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목" maxlength="10" value="${menu.title}" readonly style="background:#e0e0e0">

            <!-- 내용 표시 -->
            <label for="content">내용</label>
            <input type="text" id="content" name="content" placeholder="내용" maxlength="30" value="${menu.content}" readonly style="background:#e0e0e0">

            <!-- 작성자 표시 -->
            <label for="writer">작성자</label>
            <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${menu.writer}" readonly style="background:#e0e0e0">

            <!-- 관리자/매니저 권한일 때만 수정 및 삭제 버튼 노출 -->
            <div id="buttonContainer">
                <c:if test="${MANAGER == true}">
                    <button type="button" id="buttonUpdate">수정</button>
                    <button type="button" id="buttonDelete">삭제</button>
                </c:if>
            </div>

        </div>
    </div>
</form>

<!-- 공통 하단 푸터 포함 -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<!-- JavaScript 파일 연결: 수정/삭제 기능 처리 -->
<script src="${pageContext.request.contextPath}/resources/js/noticeCheck/script.js"></script>
</body>
</html>