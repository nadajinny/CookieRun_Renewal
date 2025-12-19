
# 자바 스윙 쿠키런




## 기능

- 체력게이지
- 점수
- 점프
- 더블점프
- 슬라이드
- 낙하
- 일시정지

젤리를 먹으면 이펙트가 생기고 점점 사라진다

일정 거리를 달리면 체력이 조금씩 줄어든다

물약을 먹으면 체력이 일정량 늘어난다

발판, 공중발판 - 발판이 있으면 떨어지지 않는다

배경화면이 천천히 흐른다

맵이 바뀌면 배경화면이 페이드아웃 페이드인 된다

장애물에 부딛히면 캐릭터가 반투명해지며 무적상태가 된다

esc를 누르면 일시정지되고 esc를 다시 누르면 게임이 진행된다




## 프로젝트 구조

```
.
├── src
│   ├── main
│   │   ├── Main.java               (실행 진입점, 모드/화면 전환)
│   │   └── listenAdapter.java      (리스너 어댑터)
│   ├── Client
│   │   ├── chatClient.java         (로비/채팅/Ready 처리)
│   │   ├── MultiClient.java        (2인 3각 클라이언트)
│   │   └── RelayClient.java        (2:2 이어달리기 클라이언트)
│   ├── Server
│   │   ├── chatServer.java         (로비 서버, Ready 동기화)
│   │   ├── MultiServer.java        (2인 3각 동기화 서버)
│   │   └── RelayServer.java        (2:2 이어달리기 서버)
│   ├── panels
│   │   ├── IntroPanel.java         (인트로 화면)
│   │   ├── ModeSelectPanel.java    (모드 선택 화면)
│   │   ├── LoginPanel.java         (로그인 화면)
│   │   ├── m1SelectPanel.java      (솔로 캐릭터 선택)
│   │   ├── m1GamePanel.java        (솔로 게임 진행)
│   │   ├── m1EndPanel.java         (솔로 결과 화면)
│   │   ├── MultiSelectPanel.java   (2인 3각 캐릭터 선택)
│   │   ├── MultiGamePanel.java     (2인 3각 게임 진행)
│   │   ├── MultiEndPanel.java      (2인 3각 결과 화면)
│   │   ├── RelayIntroPanel.java    (2:2 이어달리기 인트로)
│   │   ├── RelayGamePanel.java     (2:2 이어달리기 게임)
│   │   └── RelayEndPanel.java      (2:2 이어달리기 결과)
│   ├── ingame
│   │   ├── Cookie.java             (캐릭터 로직)
│   │   ├── Jelly.java              (젤리 오브젝트)
│   │   ├── Field.java              (발판)
│   │   ├── Tacle.java              (장애물)
│   │   ├── Back.java               (배경 스크롤)
│   │   ├── MapObjectImg.java       (맵 리소스 묶음)
│   │   └── CookieImg.java          (캐릭터 이미지 묶음)
│   └── util
│       └── Util.java               (렌더링/시간 유틸)
├── img                              (게임 이미지/버튼/배경)
├── lib                              (외부 라이브러리, 있을 경우)
└── bin                              (컴파일 산출물)
```


## 프레젠테이션
https://docs.google.com/presentation/d/11-A-YDGr12ncdO9ve9ShfkVe7af7aTftjNtVILobRZU/edit?usp=sharing




## 영상
https://www.youtube.com/watch?v=ekxUPLb1EjA&feature=youtu.be




## 설명 블로그

1.준비 : https://ondolroom.tistory.com/297 

2.전역공간 : https://ondolroom.tistory.com/298 

3.JPanel생성자 : https://ondolroom.tistory.com/299 

4.mapMove메서드 : https://ondolroom.tistory.com/300 

5.hit메서드 : https://ondolroom.tistory.com/301 

6.fall메서드 : https://ondolroom.tistory.com/302 

7.jump메서드 : https://ondolroom.tistory.com/303 

8.paintComponent 및 결과 : https://ondolroom.tistory.com/304




## 기능별 연구 블로그

프로그램을 함수화 하기 : https://ondolroom.tistory.com/265

클릭으로 이미지 변경하기 : https://ondolroom.tistory.com/280

쓰레드를 이용하여 repaint 무한반복하기 : https://ondolroom.tistory.com/281

배경화면이 흐르도록 만들기 : https://ondolroom.tistory.com/284

배경화면이 무한반복 하도록 만들기 : https://ondolroom.tistory.com/285

이미지 2개가 만나면 하나 없어지도록 하기 : https://ondolroom.tistory.com/286

이미지 점프 + 낙하 하게 만들기 : https://ondolroom.tistory.com/287

더블점프 구현하기 : https://ondolroom.tistory.com/288

발판 구현하기 : https://ondolroom.tistory.com/289

다중발판 구현하기 : https://ondolroom.tistory.com/294

이미지로 발판 및 젤리 구현하기 : https://ondolroom.tistory.com/296

동작마다 이미지 변경하기 : https://ondolroom.tistory.com/290

페이드 아웃 페이드 인 구현하기 : https://ondolroom.tistory.com/291

더블버퍼링 : https://ondolroom.tistory.com/292

투명화 구현하기 : https://ondolroom.tistory.com/293










