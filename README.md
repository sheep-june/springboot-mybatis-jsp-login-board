# Spring Study 001 - 会員登録および掲示板ウェブアプリケーション

## 概要
本プロジェクトは、Spring Boot、Spring Security、MyBatis、JSPをベースに作成されたウェブアプリケーションです。  
基本的な会員登録、ログイン、お知らせ掲示板のCRUD機能を提供し、RBAC（Role-Based Access Control）方式の権限システムを適用しています。

## 主な機能
- 会員登録（BCryptによるパスワード暗号化）
- ログインおよびログアウト（Spring Securityセッション認証）
- お知らせ掲示板
  - 投稿の作成、編集、削除、閲覧機能
  - 管理者/マネージャー権限によるアクセス制御
- REST API（掲示板CRUD用）
- Swagger（OpenAPI）ドキュメント連携（APIテスト用）
- CSRF（Cross-Site Request Forgery）対策適用
- CORS（Cross-Origin Resource Sharing）ポリシー設定

## 使用技術スタック
- **バックエンド**
  - Java 17
  - Spring Boot 3
  - Spring Security
  - Spring MVC
  - MyBatis
- **フロントエンド**
  - JSP + JSTL
  - HTML5, CSS3, JavaScript
- **データベース**
  - MySQL
- **その他**
  - Swagger（springdoc-openapi-ui）

## プロジェクト構成
project  
├── config  
│   ├── SecurityConfig.java（セキュリティ設定）  
│   └── SwaggerConfig.java（Swagger設定）  
├── controller  
│   ├── MenuRestController.java（REST API - 掲示板）  
│   ├── PageController.java（ページレンダリング）  
│   └── UserController.java（会員登録処理）  
├── entity  
│   ├── CustomUser.java  
│   ├── Menu.java  
│   ├── Role.java  
│   └── User.java  
├── mapper  
│   ├── MenuRestMapper.java  
│   └── UserMapper.java  
├── service  
│   ├── MenuRestService.java  
│   └── UserService.java  
└── SpringProject1Application.java（メイン実行ファイル）

- `resources/static/css`, `resources/static/js`：各ページ用のフロントエンドリソース
- `resources/templates` または `/WEB-INF/views/`：JSPファイル
- `application.properties`：データベース接続設定

## 実行方法
1. MySQLデータベースを作成し、テーブルを準備
2. `application.properties`にDB接続情報を設定
3. プロジェクトを実行（`SpringProject1Application`）
4. ブラウザでアクセス
   - http://localhost:8080/
   - （Swagger UI: http://localhost:8080/swagger-ui/index.html）

## 注意事項
- POST、PUT、DELETEリクエストを送信する際には、必ずCSRFトークンを一緒に送信してください。
- 管理者（Admin）またはマネージャー（Manager）権限を持つアカウントのみ、投稿の編集・削除が可能です。



# Spring Study 001 - 회원가입 및 게시판 웹 애플리케이션

## 개요
이 프로젝트는 Spring Boot, Spring Security, MyBatis, JSP를 기반으로 제작된 웹 애플리케이션입니다.  
기본적인 회원가입, 로그인, 공지사항 게시판 CRUD 기능을 제공하며, RBAC(Role-Based Access Control) 권한 시스템을 적용했습니다.

## 주요 기능
- 회원가입 (BCrypt 암호화 적용)
- 로그인 및 로그아웃 (Spring Security 세션 기반 인증)
- 공지사항 게시판
  - 게시글 작성, 수정, 삭제, 조회 기능
  - 관리자/매니저 권한에 따른 접근 제어
- REST API (게시판 CRUD용)
- Swagger(OpenAPI) 문서 연동 (API 테스트용)
- CSRF(Cross-Site Request Forgery) 보안 적용
- CORS(Cross-Origin Resource Sharing) 정책 설정

## 사용 기술 스택
- **Backend**
  - Java 17
  - Spring Boot 3
  - Spring Security
  - Spring MVC
  - MyBatis
- **Frontend**
  - JSP + JSTL
  - HTML5, CSS3, JavaScript
- **Database**
  - MySQL
- **기타**
  - Swagger (springdoc-openapi-ui)

## 프로젝트 구조
project<br>
├── config
│ ├── SecurityConfig.java (보안 설정)
│ └── SwaggerConfig.java (Swagger 설정)
├── controller
│ ├── MenuRestController.java (REST API - 게시판)
│ ├── PageController.java (페이지 렌더링)
│ └── UserController.java (회원가입 처리)
├── entity
│ ├── CustomUser.java
│ ├── Menu.java
│ ├── Role.java
│ └── User.java
├── mapper
│ ├── MenuRestMapper.java
│ └── UserMapper.java
├── service
│ ├── MenuRestService.java
│ └── UserService.java
└── SpringProject1Application.java (메인 실행 파일)


- `resources/static/css`, `resources/static/js` : 페이지별 프론트엔드 리소스
- `resources/templates` 또는 `/WEB-INF/views/` : JSP 파일
- `application.properties` : 데이터베이스 연결 및 설정

## 실행 방법
1. MySQL 데이터베이스 생성 및 테이블 준비
2. `application.properties`에 DB 연결 정보 설정
3. 프로젝트 실행 (`SpringProject1Application`)
4. 브라우저 접속
   - http://localhost:8080/
   - (Swagger UI: http://localhost:8080/swagger-ui/index.html)

## 참고
- CSRF 보호를 위해 모든 POST, PUT, DELETE 요청 시 CSRF 토큰을 함께 전송해야 합니다.
- 관리자(Admin) 또는 매니저(Manager) 권한을 가진 계정만 게시글 수정 및 삭제가 가능합니다.

