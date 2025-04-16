<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항수정페이지</title>

<!-- CSRF 토큰 설정: 자바스크립트 fetch 요청에서 사용됨 -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<!-- 수정 페이지 전용 CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeModify/style.css">
</head>
<body>

<!-- 공통 헤더 include -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 공지사항 수정 폼 -->
<form id="menuForm">
    <div id="container">
        <div id="menuAdmin">
            <h2 id="menuAdminH2">공지사항 조회</h2>

            <!-- 게시글 고유번호 (수정 시 서버로 넘기기 위해 hidden 처리) -->
            <label for="memID">회원아이디</label>
            <input type="hidden" id="idx" name="idx" placeholder="idx" maxlength="20" value="${menu.idx}" readonly style="background:#e0e0e0">

            <!-- 회원 ID는 수정 불가 (readonly) -->
            <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${menu.memID}" readonly style="background:#e0e0e0">

            <!-- 제목은 수정 가능 -->
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목" maxlength="10" value="${menu.title}" >

            <!-- 내용은 수정 가능 -->
            <label for="content">내용</label>
            <input type="text" id="content" name="content" placeholder="내용" maxlength="30" value="${menu.content}" >

            <!-- 작성자는 수정 불가 (readonly) -->
            <label for="writer">작성자</label>
            <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${menu.writer}" readonly style="background:#e0e0e0">

            <!-- 매니저 이상만 수정 버튼 노출 -->
            <div id="buttonContainer">
                <c:if test="${MANAGER == true}">
                     <button type="button" id="buttonUpdate">수정</button>
                </c:if>
            </div>

        </div>
    </div>
</form>

<!-- 공통 푸터 include -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<!-- 게시글 수정 처리용 자바스크립트 -->
<script src="${pageContext.request.contextPath}/resources/js/noticeModify/script.js"></script>
</body>
</html>