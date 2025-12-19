# CONTRIBUTION

## 이진선

### 팀 프로젝트 기여 내용
- 팀 내 **코드 병합 작업에 주도적으로 참여**
- **2인 3각(멀티플레이) 모드** 전체 구조 설계 및 구현 담당

---

### 담당 파일
- `src/Client/MultiClient.java`
- `src/Server/MultiServer.java`
- `src/panels/MultiSelectPanel.java`
- `src/panels/MultiGamePanel.java`
- `src/panels/MultiEndPanel.java`

---

### 주요 기술적 구현 사항
- **서버 기반 입력 공유 구조**를 통해 두 명의 클라이언트가 하나의 캐릭터를 동시에 조작
- 서버에서 **역할 분담(점프 / 슬라이드)**을 관리하여 입력 충돌 방지
- `CardLayout`을 활용해 **선택 → 게임 → 종료 화면 전환을 자연스럽게 처리**

---

### 2인 3각 모드 동작 흐름 (코드 기준)

#### 1. 서버 및 로비 준비
- `src/Server/chatServer.java`의 `main` 실행 시  
  → 채팅 서버와 함께 **멀티 전용 서버(`MultiServer`, 포트 7875)** 동시 기동
- `ModeSelectPanel`에서 **2인 3각 모드 선택**
  - `Main.setMode("multi")` 호출
  - `chatClient` 실행
- `chatClient`에서 Ready 버튼 클릭
  - 서버로 `"ready"` 전송
  - 모든 클라이언트 Ready 시  
    `chatServer.checkAllReady()` → `"ready"` 브로드캐스트
  - `changemode()` 호출로 멀티 모드 진입

---

#### 2. 멀티 서버 연결 및 역할 분배
- `MultiClient`가 `localhost:7875`로 접속
- `CardLayout` 기반 멀티 화면 구조 초기화
- `MultiServer`는 **클라이언트 2명 접속 완료 시 역할 자동 분배**
  - 첫 번째 클라이언트 → `"i'm jump"`
  - 두 번째 클라이언트 → `"i'm slide"`
- 이후 `"gotoSelect"` 메시지를 전송하여 캐릭터 선택 화면으로 이동

---

#### 3. 캐릭터 선택 및 게임 시작
- `MultiSelectPanel`
  - 캐릭터 선택 후 Ready 버튼 클릭
  - `"Ready"` 메시지 서버로 전송
  - 버튼 비활성화 및 대기 상태 표시
- 서버의 `ClientManager.gameReady()`에서 전체 Ready 확인
  - `"gameStart"` 브로드캐스트
- `MultiClient`는 선택된 `CookieImg`를 기반으로  
  `MultiGamePanel` 생성 후 게임 시작

---

#### 4. 입력 공유 및 서버 필터링
- `MultiGamePanel`에서 키 입력 및 이벤트 발생 시 서버로 메시지 전송
  - 스페이스, ↓, ESC, 충돌 이벤트 등
- `MultiServer`에서 **역할 기반 입력 필터링**
  - Jump 역할 (role 0)
    - `jump`, `jumpBtn`
  - Slide 역할 (role 1)
    - `Down`, `downKeyOn`
  - 공통 처리
    - `hit`, `EscOn`, `fall` 등
- `MultiGamePanel.handleMessageactive()`에서
  - `switch` 문으로 메시지를 분기 처리하여
  - 점프, 슬라이드, 피격, 일시정지 동작 수행

---

#### 5. 종료 및 점수 처리
- 캐릭터 낙하 또는 체력 0 도달 시
  - `condition = -1`로 게임 종료 처리
- `MultiGamePanel`
  - `"Score:점수"` 메시지를 서버로 전송
- `MultiServer`
  - 점수 정보를 모든 클라이언트에 브로드캐스트
- `MultiClient`
  - 점수 수신 시 `MultiEndPanel`로 전환
  - 결과 화면 출력
  - `endAccept` 버튼 클릭 시 멀티 프레임 종료
- 추가로 서버의 `"givemescore"` 요청에 점수를 회신하는 로직도 구현

---

### 메시지 흐름 요약

**Client → Server**
- `Ready`
- `jump`, `jumpBtn`
- `Down`, `downKeyOn`
- `EscOn`
- `hit`
- `Score:...`

**Server → Client**
- `gotoSelect`
- `gameStart`
- 역할 분배 메시지 (`"i'm jump"`, `"i'm slide"`)
- `EscOn`
- 점수 및 종료 관련 메시지
