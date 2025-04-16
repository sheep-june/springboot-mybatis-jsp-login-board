// 게시글 수정 버튼 클릭 이벤트
// 현재 게시글의 idx 값을 가져와 수정 페이지로 이동

document.getElementById("buttonUpdate").addEventListener('click', function() {
	const idx = document.getElementById("idx").value; // 게시글 고유 ID
	window.location.href = `/noticeModifyPage?idx=${idx}`; // 수정 페이지 이동
});

// 게시글 삭제 버튼 클릭 이벤트
// REST 방식으로 DELETE 요청을 보내어 게시글을 삭제함

document.getElementById("buttonDelete").addEventListener("click", function() {
	const idx = document.getElementById("idx").value; // 게시글 고유 ID

	// CSRF 보호를 위한 토큰 및 헤더 설정 (Spring Security)
	const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

	// fetch API를 사용해 비동기 DELETE 요청 전송
	fetch(`/menu/delete/${idx}`, {
		method: 'DELETE',
		headers: {
			[csrfHeader]: csrfToken // 동적 헤더로 CSRF 토큰 삽입
		}
	}).then(response => {
		if (!response.ok) {
			throw new Error("응답이 잘 안됬습니다.");
		} else {
			return response.text();
		}
	}).then(_ => {
		alert("삭제가 성공적으로 진행되었습니다.");
		window.location.href = '/'; // 메인 페이지로 이동
	}).catch(error => {
		console.log(`에러발생:${error}`); // 오류 로그 출력
	});
});
