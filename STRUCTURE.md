# 프로젝트 파일 구조

아래는 현재 저장소 기준 전체 파일 구조와 각 파일의 역할입니다.

```text
CookieRun_Renewal/
|-- README.md - 프로젝트 개요/설명 문서
|-- STRUCTURE.md - 전체 파일 구조/역할 상세
|-- CONTRIBUTION.md - 기여 가이드 및 협업 규칙
|-- 객지프_발표자료_최종.pptx - 최종 발표 자료
|-- lib/
|   |-- lombok-1.18.8.jar - Lombok 라이브러리
|-- src/
|   |-- Client/
|   |   |-- MultiClient.java - 2인 3각 멀티 클라이언트 (연결/흐름/입력 전송)
|   |   |-- RelayClient.java - 2:2 이어달리기 클라이언트 (팀/게임 진행)
|   |   `-- chatClient.java - 채팅/대기방 클라이언트 (로그인/방/준비)
|   |-- Server/
|   |   |-- MultiServer.java - 2인 3각 서버 (역할 분배/입력 검증)
|   |   |-- RelayServer.java - 2:2 이어달리기 서버 (팀 배정/점수 처리)
|   |   `-- chatServer.java - 채팅/대기방 서버 (접속/방 관리/Ready)
|   |-- ingame/
|   |   |-- Back.java - 배경 스크롤 오브젝트
|   |   |-- Cookie.java - 쿠키 상태/좌표 모델
|   |   |-- CookieImg.java - 쿠키 스프라이트 묶음
|   |   |-- Field.java - 발판/지면 오브젝트
|   |   |-- Jelly.java - 젤리 아이템 오브젝트
|   |   |-- MapObjectImg.java - 맵 오브젝트 이미지 묶음
|   |   `-- Tacle.java - 장애물 오브젝트
|   |-- main/
|   |   |-- Main.java - 앱 엔트리, 프레임/패널 초기화
|   |   `-- listenAdapter.java - 입력 리스너 어댑터 베이스
|   |-- panels/
|   |   |-- IntroPanel.java - 인트로 화면
|   |   |-- LoginPanel.java - 서버 접속 입력 패널
|   |   |-- ModeSelectPanel.java - 모드 선택 화면
|   |   |-- MultiEndPanel.java - 멀티 결과 화면
|   |   |-- MultiGamePanel.java - 멀티 플레이 화면
|   |   |-- MultiSelectPanel.java - 멀티 캐릭터 선택/준비
|   |   |-- RelayEndPanel.java - 이어달리기 결과 화면
|   |   |-- RelayGamePanel.java - 이어달리기 플레이 화면
|   |   |-- RelayIntroPanel.java - 이어달리기 대기/로딩 화면
|   |   |-- m1EndPanel.java - 1인 모드 결과 화면
|   |   |-- m1GamePanel.java - 1인 모드 플레이 화면
|   |   `-- m1SelectPanel.java - 1인 모드 선택 화면
|   `-- util/
|       `-- Util.java - 이미지/텍스트/시간 유틸
|-- img/
|   |-- Objectimg/
|   |   |-- lifebar/
|   |   |   |-- jumpdim.png - 점프 버튼 비활성 이미지
|   |   |   |-- jumpno.png - 점프 버튼 기본 이미지
|   |   |   |-- lifeBar.png - 라이프바 기본 이미지
|   |   |   |-- lifeBar1.png - 라이프바 보조 이미지
|   |   |   |-- redBg.png - 라이프바 배경 이미지
|   |   |   |-- slidedim.png - 슬라이드 버튼 비활성 이미지
|   |   |   `-- slideno.png - 슬라이드 버튼 기본 이미지
|   |   |-- map1img/
|   |   |   |-- bg1.png - 맵1 배경 1
|   |   |   |-- bg2.png - 맵1 배경 2
|   |   |   |-- effectTest.png - 이펙트 테스트 스프라이트
|   |   |   |-- effectTest1.png - 이펙트 테스트 스프라이트 2
|   |   |   |-- fieldIc1.png - 맵1 발판 1
|   |   |   |-- fieldIc2.png - 맵1 발판 2
|   |   |   |-- jelly1.png - 젤리 1
|   |   |   |-- jelly2.png - 젤리 2
|   |   |   |-- jelly3.png - 젤리 3
|   |   |   |-- life.png - 라이프 아이콘
|   |   |   |-- speedUpEffect.png - 스피드 업 이펙트
|   |   |   |-- tacle1.gif - 맵1 장애물 애니메이션 1
|   |   |   |-- tacle2.png - 맵1 장애물 2
|   |   |   |-- tacle3.gif - 맵1 장애물 애니메이션 3
|   |   |   `-- tacle3.png - 맵1 장애물 3
|   |   |-- map2img/
|   |   |   |-- back1.png - 맵2 배경 1
|   |   |   |-- back2.png - 맵2 배경 2
|   |   |   |-- field1.png - 맵2 발판 1
|   |   |   |-- field2.png - 맵2 발판 2
|   |   |   |-- tacle1.png - 맵2 장애물 1
|   |   |   |-- tacle2.png - 맵2 장애물 2
|   |   |   `-- tacle3.png - 맵2 장애물 3
|   |   |-- map3img/
|   |   |   |-- bg.png - 맵3 배경 1
|   |   |   |-- bg2.png - 맵3 배경 2
|   |   |   |-- field.png - 맵3 발판 1
|   |   |   |-- field2.png - 맵3 발판 2
|   |   |   |-- tacle1.png - 맵3 장애물 1
|   |   |   |-- tacle2.png - 맵3 장애물 2
|   |   |   `-- tacle3.png - 맵3 장애물 3
|   |   |-- map4img/
|   |   |   |-- bback.png - 맵4 배경 1
|   |   |   |-- bback2.png - 맵4 배경 2
|   |   |   |-- ffootTest.png - 맵4 발판 1
|   |   |   |-- ffootTest2.png - 맵4 발판 2
|   |   |   |-- tacle1.png - 맵4 장애물 1
|   |   |   `-- tacle2.png - 맵4 장애물 2
|   |-- cookieimg/
|   |   |-- cookie1/
|   |   |   |-- player_attack.png - 쿠키1 공격 스프라이트
|   |   |   |-- player_doubleup.gif - 쿠키1 더블 점프
|   |   |   |-- player_down.gif - 쿠키1 슬라이드/다운
|   |   |   |-- player_jumpend.png - 쿠키1 점프 착지
|   |   |   |-- player_origin.gif - 쿠키1 기본 애니메이션
|   |   |   `-- player_up.gif - 쿠키1 점프
|   |   |-- cookie2/
|   |   |   |-- doublejump.gif - 쿠키2 더블 점프
|   |   |   |-- fall.png - 쿠키2 낙하
|   |   |   |-- hit.gif - 쿠키2 피격
|   |   |   |-- jump.gif - 쿠키2 점프
|   |   |   |-- normal.gif - 쿠키2 기본 애니메이션
|   |   |   `-- slide.gif - 쿠키2 슬라이드
|   |   |-- cookie3/
|   |   |   |-- cookie.gif - 쿠키3 기본 애니메이션
|   |   |   |-- doublejump.gif - 쿠키3 더블 점프
|   |   |   |-- fall.png - 쿠키3 낙하
|   |   |   |-- hit.png - 쿠키3 피격
|   |   |   |-- jump.png - 쿠키3 점프
|   |   |   `-- slide.gif - 쿠키3 슬라이드
|   |   `-- cookie4/
|   |       |-- kch.gif - 쿠키4 기본 애니메이션
|   |       |-- kjump.gif - 쿠키4 점프
|   |       `-- kslide.gif - 쿠키4 슬라이드
|   |-- end/
|   |   |-- Button.png - 결과 화면 버튼 이미지
|   |   `-- cookierunbg.jpg - 결과 화면 배경 이미지
|   |-- intro/
|   |   `-- intro.png - 인트로 배경 이미지
|   |-- map/
|   |   |-- map1.png - 맵1 미리보기
|   |   |-- map2.png - 맵2 미리보기
|   |   |-- map3.png - 맵3 미리보기
|   |   `-- map4.png - 맵4 미리보기
|   |-- select/
|   |   |-- GameStartBtn.png - 게임 시작 버튼
|   |   |-- selectBg.png - 선택 화면 배경
|   |   |-- selectCh1.png - 캐릭터1 선택 카드
|   |   |-- selectCh2.png - 캐릭터2 선택 카드
|   |   |-- selectCh3.png - 캐릭터3 선택 카드
|   |   |-- selectCh4.png - 캐릭터4 선택 카드
|   |   |-- selectedCh1.png - 캐릭터1 선택됨 표시
|   |   |-- selectedCh2.png - 캐릭터2 선택됨 표시
|   |   |-- selectedCh3.png - 캐릭터3 선택됨 표시
|   |   |-- selectedCh4.png - 캐릭터4 선택됨 표시
|   |   `-- selectTxt.png - 선택 화면 텍스트
|   `-- 새로 추가된 이미지/
|       |-- 1인_모드.png - 1인 모드 버튼
|       |-- 2대2_이어달리기.png - 2:2 이어달리기 버튼
|       |-- 2인_3각_모드.png - 2인 3각 모드 버튼
|       |-- background.png - 공용 배경 이미지
|       |-- cookie.png - 쿠키 UI 이미지
|       |-- login.png - 로그인 텍스트 이미지
|       |-- login_background.png - 로그인 배경 이미지 1
|       |-- loginbackground.png - 로그인 배경 이미지 2
|       |-- loginbutton.png - 로그인 버튼 이미지
|       |-- ready.png - Ready 버튼 이미지
|       |-- 전송.png - 전송 버튼 이미지
|       |-- 채팅방_만들기.png - 채팅방 생성 버튼
|       |-- 채팅방_참여.png - 채팅방 참여 버튼
|       |-- 쪽지_보내기.png - 쪽지 보내기 버튼
|       `-- 로그인.png - 로그인 제목 이미지
|-- bin/ - 컴파일된 클래스 파일 (빌드 산출물)
|   |-- Client/
|   |   |-- MultiClient.class - MultiClient 컴파일 결과
|   |   |-- RelayClient.class - RelayClient 컴파일 결과
|   |   |-- chatClient.class - chatClient 컴파일 결과
|   |   |-- chatClient$1.class - chatClient 내부/익명 클래스 컴파일 결과
|   |   |-- chatClient$2.class - chatClient 내부/익명 클래스 컴파일 결과
|   |   |-- chatClient$3.class - chatClient 내부/익명 클래스 컴파일 결과
|   |   |-- chatClient$4.class - chatClient 내부/익명 클래스 컴파일 결과
|   |   |-- chatClient$5.class - chatClient 내부/익명 클래스 컴파일 결과
|   |   `-- chatClient$6.class - chatClient 내부/익명 클래스 컴파일 결과
|   |-- Server/
|   |   |-- MultiServer.class - MultiServer 컴파일 결과
|   |   |-- MultiServer$ClientManager.class - MultiServer 내부 클래스 컴파일 결과
|   |   |-- RelayServer.class - RelayServer 컴파일 결과
|   |   |-- RelayServer$ClientManager.class - RelayServer 내부 클래스 컴파일 결과
|   |   |-- chatServer.class - chatServer 컴파일 결과
|   |   |-- chatServer$1.class - chatServer 내부/익명 클래스 컴파일 결과
|   |   |-- chatServer$RoomInfo.class - chatServer 내부 클래스 컴파일 결과
|   |   `-- chatServer$UserInfo.class - chatServer 내부 클래스 컴파일 결과
|   |-- ingame/
|   |   |-- Back.class - Back 컴파일 결과
|   |   |-- Cookie.class - Cookie 컴파일 결과
|   |   |-- CookieImg.class - CookieImg 컴파일 결과
|   |   |-- Field.class - Field 컴파일 결과
|   |   |-- Jelly.class - Jelly 컴파일 결과
|   |   |-- MapObjectImg.class - MapObjectImg 컴파일 결과
|   |   `-- Tacle.class - Tacle 컴파일 결과
|   |-- main/
|   |   |-- Main.class - Main 컴파일 결과
|   |   |-- Main$1.class - Main 내부/익명 클래스 컴파일 결과
|   |   `-- listenAdapter.class - listenAdapter 컴파일 결과
|   |-- panels/
|   |   |-- IntroPanel.class - IntroPanel 컴파일 결과
|   |   |-- ModeSelectPanel.class - ModeSelectPanel 컴파일 결과
|   |   |-- ModeSelectPanel$1.class - ModeSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- ModeSelectPanel$2.class - ModeSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- ModeSelectPanel$2$1.class - ModeSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- ModeSelectPanel$3.class - ModeSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- ModeSelectPanel$3$1.class - ModeSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiEndPanel.class - MultiEndPanel 컴파일 결과
|   |   |-- MultiGamePanel.class - MultiGamePanel 컴파일 결과
|   |   |-- MultiGamePanel$1.class - MultiGamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiGamePanel$2.class - MultiGamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiSelectPanel.class - MultiSelectPanel 컴파일 결과
|   |   |-- MultiSelectPanel$1.class - MultiSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiSelectPanel$2.class - MultiSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiSelectPanel$3.class - MultiSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiSelectPanel$4.class - MultiSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- MultiSelectPanel$5.class - MultiSelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- RelayEndPanel.class - RelayEndPanel 컴파일 결과
|   |   |-- RelayEndPanel$1.class - RelayEndPanel 내부/익명 클래스 컴파일 결과
|   |   |-- RelayGamePanel.class - RelayGamePanel 컴파일 결과
|   |   |-- RelayGamePanel$1.class - RelayGamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- RelayGamePanel$2.class - RelayGamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- RelayIntroPanel.class - RelayIntroPanel 컴파일 결과
|   |   |-- m1EndPanel.class - m1EndPanel 컴파일 결과
|   |   |-- m1GamePanel.class - m1GamePanel 컴파일 결과
|   |   |-- m1GamePanel$1.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$2.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$3.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$4.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$4$1.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$4$2.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$4$3.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$5.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$6.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1GamePanel$7.class - m1GamePanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1SelectPanel.class - m1SelectPanel 컴파일 결과
|   |   |-- m1SelectPanel$1.class - m1SelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1SelectPanel$2.class - m1SelectPanel 내부/익명 클래스 컴파일 결과
|   |   |-- m1SelectPanel$3.class - m1SelectPanel 내부/익명 클래스 컴파일 결과
|   |   `-- m1SelectPanel$4.class - m1SelectPanel 내부/익명 클래스 컴파일 결과
|   `-- util/
|       `-- Util.class - Util 컴파일 결과
```
