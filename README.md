# LunchRecommender
점심 메뉴 추천 및 맛집 소개를 위한 뉴스피드 애플리케이션입니다.  
이 프로젝트는 사용자가 점심 메뉴를 선택할 때 도움을 주기 위해 개발되었습니다.  
'점메추(점심 메뉴 추천)'라는 키워드를 중심으로, 사용자들이 자유롭게 정보를 공유할 수 있는 기능을 제공합니다.

## ✔️ Team
<details>
<summary>백원하</summary>
<div markdown="1">
<ul>
  <li>사용자 인증 기능</li>
  <li>이메일 가입 및 인증 기능</li>
  <li>Swagger 적용</li>
</ul>
</div>
</details>

<details>
<summary>박세미</summary>
<div markdown="1">
<ul>
  <li>프로필 관리 기능</li>
  <li>좋아요 기능</li>
  <li>팔로우 기능</li>
</ul>
</div>
</details>

<details>
<summary>이민정</summary>
<div markdown="1">
<ul>
  <li>뉴스피드 댓글 기능</li>
  <li>뉴스피드 페이지네이션 / 정렬 / 기간별 검색 기능</li>
  <li>HTTPS 업그레이드</li>
</ul>
</div>
</details>

<details>
<summary>주장현</summary>
<div markdown="1">
<ul>
  <li>뉴스피드 게시물 CRUD</li>
  <li>코드 리팩토링</li>
  <li>PPT 작성</li>
</ul>
</div>
</details>



## ✔️ Tech Stack
- 언어 : Java - JDK 17
- 프레임워크 : Spring Boot
- 데이터베이스 : Mysql

## ✔️ Features
- 사용자 인증 기능:
    - Spring Security와 JWT를 사용한 Access Token, Refresh Token 구현하였습니다.
    - 사용자 ID, 비밀번호를 입력하여 회원가입, 로그인할 수 있습니다.
    - 가입된 사용자의 회원 상태를 변경하여 탈퇴처리가 가능합니다.
    - 로그아웃 시, 발행한 토큰을 초기화합니다.
    - 이메일 가입 시 이메일 인증 기능을 포함합니다.
- 프로필 관리 기능:
    - 사용자 ID, 이름, 한 줄 소개, 이메일을 조회할 수 있습니다.
    - 로그인한 사용자는 본인의 사용자 정보(이름, 이메일, 한 줄 소개, 비밀번호)를 수정할 수 있습니다.
- 뉴스피드 CRUD 기능:
    - 모든 사용자가 전체 뉴스피드를 조회할 수 있습니다.
        - 페이지네이션 기능을 구현하여 각 페이지 당 뉴스피드 데이터를 10개씩 조회할 수 있습니다.
        - 생성일자 기준 최신순, 좋아요 많은 순으로 정렬할 수 있습니다.
        - 선택한 기간 별로 게시물을 조회할 수 있습니다.
    - 뉴스피드의 작성, 수정, 삭제 기능은 인가가 필요합니다.
        - 뉴스피드의 내용을 입력하여 작성할 수 있습니다.
        - 자신이 작성한 뉴스피드만을 수정, 삭제할 수 있습니다.
- 댓글 CRUD 기능:
    - 사용자는 게시물에 댓글을 작성할 수 있고, 본인의 댓글은 수정 및 삭제를 할 수 있습니다.
    - 댓글의 작성, 수정, 삭제 기능은 인가가 필요합니다.
        - 댓글의 내용을 입력하여 작성할 수 있습니다.
        - 자신이 작성한 댓글만을 수정, 삭제할 수 있습니다.
- 좋아요 기능:
    - 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있으며, 본인이 작성하지 않은 뉴스피드에만 좋아요를 누를 수 있습니다.
- 팔로우 기능:
    - 특정 사용자를 팔로우 / 언팔로우를 할 수 있습니다.
    - 뉴스피드에 팔로우하는 사용자의 게시물을 최신순으로 볼 수 있습니다.
## ✔️ 관련 자료
<details>
<summary>ERD Diagram</summary>
<div markdown="1">
    
![image](https://github.com/watual/LunchRecommender/assets/60657536/a296a53a-92df-43c7-9877-660a5ce7c618)
</div>
</details>

<details>
<summary>API 명세서</summary>
<div markdown="1">
    
![image](https://github.com/watual/LunchRecommender/assets/60657536/530f047a-4c48-456c-9ebd-b49ed4113adf)
![image](https://github.com/watual/LunchRecommender/assets/60657536/421d96d8-ce42-4af2-9eee-36e995377d33)
![image](https://github.com/watual/LunchRecommender/assets/60657536/22b53814-d387-41be-94c7-c528c6a8a324)
![image](https://github.com/watual/LunchRecommender/assets/60657536/c51e70c3-cebd-46f7-b5dc-20f1f7fea8f1)
</div>
</details>

## ✔️ Rules

### 브랜치명 규칙
- 브랜치명 = API 명세 기능명
    - feat/user_create
    - feat/post_update
    - feat/comment_delete

### GitHub issues 등록 규칙
| 작업 타입 | 작업내용 |
| --- | --- |
| FEATURE | 새 기능 구현 |
| BUG | 버그 발생, 해결 |

### commit 규칙
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

### 커밋 제목
    - ADD : 회원가입 기능 추가
    - UPDATE : 회원가입 기능 데이터 유효성 검사 기능 추가
    - BUGFIX : 로그인 Validation failed error 해결
    
### 커밋 내용

    - [ update ]
    - update controller/UserController
    - add repository/UserRepository
        - 회원가입 기능 구현
        
    - [ bugfix ]
    - update service/UserService
        - 로그인 오류 수정
