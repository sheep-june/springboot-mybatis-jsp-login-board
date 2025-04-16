// DOM 객체 연결 (JSP 또는 HTML 내부 태그를 JS에서 제어할 수 있도록 참조)
const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

// Spring Security용 CSRF 토큰과 헤더 이름을 meta 태그에서 추출
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

// 게시글 목록을 서버에서 조회하고 화면에 렌더링하는 함수 (GET 요청)
function fetchMenus() {
	fetch("/menu/all") // REST API GET 요청
	.then(response => response.json()) // JSON 응답 파싱
	.then(menus => {
		menuList.innerHTML = ''; // 기존 목록 초기화

		menus.forEach(menu => {
			console.log(menu.writer); // 디버깅용 로그

			// 개별 게시글 HTML 요소 생성
			const menuItem = document.createElement('div');
			menuItem.className = 'menu-item';
			menuItem.innerHTML = `
				<a href="#" class="menu-link" style="text-decoration:none;color:black;">
					<h3>${menu.title}</h3>
					<p>${menu.content}</p>
					<small>작성자:${menu.writer},작성일:${menu.indate},조회수:${menu.count}</small>
				</a>
				<br/>
				<br/>
			`;

			// 게시글 클릭 시 조회수 증가 후 상세 페이지로 이동
			menuItem.querySelector(".menu-link").addEventListener('click', (event) => {
				event.preventDefault(); // 링크 기본 동작 중지
				console.log(`event:${event}`);

				// 조회수 증가 → 완료 후 상세 페이지로 이동
				incrementCount(menu.idx).then(() => {
					window.location.href = `/noticeCheckPage?idx=${menu.idx}`;
				});
			});

			// 생성된 항목을 목록에 추가
			menuList.appendChild(menuItem);
		});
	});
}

// 특정 게시글의 조회수를 증가시키는 요청 (PUT 방식)
function incrementCount(idx) {
	return fetch(`/menu/count/${idx}`, {
		method: 'PUT',
		headers: {
			[csrfHeader]: csrfToken // CSRF 헤더 포함
		}
	}).then(response => {
		if (!response.ok) {
			console.log('데이터가 프론트서버에서 백엔드서버로 잘 안넘어감');
		}
	}).catch(error => {
		console.log(`Error: ${error}`);
	});
}

// 페이지가 로드되면 fetchMenus 실행하여 게시글 목록 표시
window.addEventListener('load', fetchMenus);
