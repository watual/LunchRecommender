# LunchRecommender

### **깃허브 규칙**

### 브랜치명 규칙

- 브랜치명 = API 명세 기능명
    - feat/user_create
    - feat/post_update
    - feat/comment_delete

### 깃헙 이슈 규칙

| 작업 타입 | 작업내용 |
| --- | --- |
| FEATURE | 새 기능 구현 |
| BUG | 버그 발생, 해결 |

### 깃헙 커밋 규칙

| 작업 타입 | 작업내용 |
| --- | --- |
| ✨ update   | 해당 파일에 새로운 기능이 생김 |
| 🎉 add | 없던 파일을 생성함, 초기 세팅 |
| 🐛 bugfix | 버그 수정 |
| ♻️ refactor | 코드 리팩토링 |
| 🩹 fix | 코드 수정 |
| 🚚 move | 파일 옮김/정리 |
| 🔥 del | 기능/파일을 삭제 |
| 🍻 test | 테스트 코드를 작성 |
| 💄 style | css |
| 🙈 gitfix | gitignore 수정 |
| 🔨script | package.json 변경(npm 설치 등) |

커밋 제목
    - update : 회원가입 데이터 유효성 검사
    - bugfix : 로그인 Validation failed error 해결
    
커밋 내용

    - [ update ]
    - update controller/UserController
    - add repository/UserRepository
        - 회원가입 기능 구현
        
    - [ bugfix ]
    - update service/UserService
        - 로그인 오류 수정
