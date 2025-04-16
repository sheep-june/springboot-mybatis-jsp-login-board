<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<title>spring study 001</title>
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/css/style.css">

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<div id="container">
		<div id="menuAdmin">
			<h2 class="menuAdminH2">공지사항</h2>
			<c:if test="${MANAGER == true}">
				<button type="button" onclick="location.href=`${pageContext.request.contextPath}/noticeAddPage` ">작성</button>
				<!-- location.href=`localhost:8080/noticeAdd` -->
			</c:if>
			<div id="menuList"></div>
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</body>
</html>