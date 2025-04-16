<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>spring study 001</title>

    <!-- CSRF 보안 처리용 메타 태그 (Spring Security에서 fetch API로 토큰 전달 시 사용) -->
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">

    <!-- 공지사항 작성 페이지 전용 스타일 적용 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeAdd/style.css">
</head>
<body>

    <!-- 공통 상단 헤더 포함 -->
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <!-- 공지사항 작성 폼 (REST 방식으로 자바스크립트에서 처리됨) -->
    <form id="menuForm">
        <div id="container">
            <div id="menuAdmin">
                <h2 id="menuAdminH2">공지사항 작성</h2>

                <!-- 로그인된 사용자 정보는 readonly 처리하여 고정값으로 표시 -->
                <label for="memID">회원아이디</label>
                <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${username}" readonly>

                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목" maxlength="10">

                <label for="content">내용</label>
                <input type="text" id="content" name="content" placeholder="내용" maxlength="30">

                <label for="writer">작성자</label>
                <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${writer}" readonly>

                <!-- 작성일은 서버에서 자동 설정될 수도 있고 JS에서 세팅 가능 -->
                <input type="hidden" id="indate" name="indate">

                <!-- 확인 버튼 (submit 아님, 자바스크립트에서 fetch 호출) -->
                <button type="button" id="buttonSubmit">확인</button>
            </div>
        </div>
    </form>

    <!-- 공통 하단 푸터 포함 -->
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    <!-- 자바스크립트 파일 로드: 게시글 작성 기능 담당 -->
    <script src="${pageContext.request.contextPath}/resources/js/noticeAdd/script.js"></script>

</body>
</html>