// 작성 버튼 클릭 시 실행되는 이벤트 등록
// REST API 호출 방식으로 게시글 데이터를 서버에 전송함

document.getElementById("buttonSubmit").addEventListener("click", function() {
	// 입력된 폼 데이터를 JavaScript 객체로 구성 (JSON 형태 준비)
	const formData = {
	    memID: document.getElementById("memID").value,
	    title: document.getElementById("title").value,
	    content: document.getElementById("content").value,
	    writer: document.getElementById("writer").value,
	};

	console.log(formData); // 디버깅용 콘솔 출력

	// index.jsp 또는 layout 헤더에 삽입된 CSRF 메타 태그를 자바스크립트에서 추출함
	const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

	// fetch API를 사용한 비동기 POST 요청 (REST 방식의 Create 처리)
	fetch('/menu/add', {
	    method: 'POST',
	    headers: {
			'Content-Type': 'application/json', // 요청 본문 타입 지정
	        [csrfHeader]: csrfToken // CSRF 보호용 헤더 추가 (Spring Security 연동)
	    },
	    body: JSON.stringify(formData) // 객체를 JSON 문자열로 변환하여 전송
	})
	.then(response => {
	    if (!response.ok) {
	        throw new Error('Network response was not ok');
	    }
	    return response.text(); // 서버 응답을 텍스트로 받음 (간단한 메시지 처리)
	})
	.then(data => {
	    console.log('Success:', data);
	    // 등록 후 메인 페이지(공지사항 목록 등)로 이동
	    window.location.href = '/';
	})
	.catch(error => {
	    console.error('Error:', error); // 네트워크 또는 처리 오류 출력
	});
});
