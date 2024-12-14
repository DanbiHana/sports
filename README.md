# 축구 야구 통합사이트 IN:OUT ⚽⚾
<br>

### ✔️ 전체 파일<br>
[IN_OUT.pdf](https://github.com/user-attachments/files/17612967/IN_OUT.pdf)
<br><br><br>

## 🏆 프로젝트 소개 
축구 야구 통합 사이트 IN:OUT은 축구, 야구를 즐기는 사람들의 정보 공간을 구현한 프로젝트입니다.<br>
회원에게는 선수 및 경기 정보를 제공하고,<br>
감독에게는 전략과 훈련일지를 작성 및 관리하는 공간을 제공합니다.<br><br>
![003](https://github.com/user-attachments/assets/d1271ff4-347b-4d25-becf-98a6c687002d)
![004](https://github.com/user-attachments/assets/09833b82-0fcb-4a2d-b3c7-0f10e560d26b)
<br><br><br>

## 🏆 프로젝트 개발 기간
2024.09.23 ~ 2024.11.01
<br><br>
![006](https://github.com/user-attachments/assets/e1764464-3245-4aa5-ad04-ac7570fd4520)
<br><br><br>

## 🏆 팀 멤버
권석주, 김민성, 김규리, 이혜린, 천시아
<br><br>
![007](https://github.com/user-attachments/assets/3dd16488-53ca-475e-9482-4ed6215c040d)
<br><br><br>

## 🏆 기술 스택
✔️ HTML, CSS, JavaScript<br>
✔️ Ajax, JQuery<br>
✔️ Oracle, MyBatis, Servlets&Jsp<br>
✔️ Java, Spring Framework<br>
✔️ Git<br>
✔️ Python<br><br>  
![005](https://github.com/user-attachments/assets/48684a40-2035-4d58-83d4-eca42310f727)
<br><br><br>

## 🏆 유스케이스 다이어그램 및 ER 다이어그램
![008](https://github.com/user-attachments/assets/5a2707d7-99bf-44f8-a14c-d540030f82d6)
![012](https://github.com/user-attachments/assets/42b711f2-1e33-4878-bda8-19058cb815b9)
<br><br><br>

## 🏆 주요 기능
### ✔️ 회원 가입 및 로그인<br>
&nbsp; - 공통 : 감독, 일반 회원 구분하여 회원가입<br>
&nbsp; - 각 부문 별 유효성 검사<br>
&nbsp; - ID 중복, PW 일치 검사<br>
&nbsp; - 주소 (다음 주소 API)<br>
&nbsp; - 아이디 찾기, 비밀번호 찾기<br>
&nbsp; - 회원 정보 출력, 수정, 삭제<br>
&nbsp; - 감독 : 회원가입 시 관리자 승인 필요<br>
![017](https://github.com/user-attachments/assets/2750e805-cab1-4994-8c1a-b4a5b9e4e1c7)
![018](https://github.com/user-attachments/assets/a5b1870a-9a01-45c3-ae25-c7554f2954d3)
![019](https://github.com/user-attachments/assets/0f615c68-b0a1-41c7-ae86-94c9b95d0d32)
<br><br><br>

### ✔️ 마이페이지<br>
&nbsp; - 마이페이지 회원 정보 조회, 수정, 탈퇴<br>
&nbsp; - 마이페이지 정보 수정 시 모달창 이용한 비밀번호 확인(Ajax)<br>
![021](https://github.com/user-attachments/assets/f6f60499-8b42-4714-9dca-f43fceb23acb)
<br><br><br>

### ✔️ 선수 관리<br>
&nbsp; - 공통 : 선수 정보 조회, 상세 페이지<br>
&nbsp; - 감독, 관리자 : 선수 정보 등록, 수정, 삭제 기능 추가<br>
&nbsp; - 상세 페이지: 선수 상세 정보 조회, 댓글 및 응원 기능 부여<br>
&nbsp; - 댓글: 작성, 조회, 수정, 삭제 (페이징 처리)<br>
&nbsp; - 랭킹 : 선수 부문별 랭킹 (네이버 스포츠, K리그 크롤링)<br>
&nbsp; - 랜덤 뽑기 : 랜덤 함수 이용하여 선수 랜덤으로 뽑기 구현<br><br>
![032](https://github.com/user-attachments/assets/91641ca7-b7ea-46ed-9c8d-ebcb17b45eea)
![034](https://github.com/user-attachments/assets/5dd599ad-5153-43df-bc16-5a8acf5bce73)
![037](https://github.com/user-attachments/assets/645b33bf-0974-4bcb-8f35-511d907b5566)
<br><br><br>

### ✔️ 경기 관리<br> 
&nbsp; - 공통 : 경기 일정 조회, 경기장 확인 (카카오맵 API) <br>
&nbsp; - 팀 순위 : 팀별 경기 순위 조회 (네이버 스포츠 크롤링)<br>
&nbsp; - 하이라이트 : 팀 별 영상 , 공식 계정(K리그, KBO) 영상 조회 (유튜브 API)<br>
&nbsp; - 관리자 : 경기 일정 등록 , 조회, 수정, 삭제<br><br>
![038](https://github.com/user-attachments/assets/f76c24be-4549-4fc4-9653-3d2ad529e827)
![040](https://github.com/user-attachments/assets/35687f71-80d9-4503-84f0-f18fe3cdf41d)
<br><br><br>

### ✔️ 굿즈샵<br>
&nbsp; - 공통 : 팀 별 공식 쇼핑몰로 이동<br><br>
![041](https://github.com/user-attachments/assets/0cd1cfb8-6ea2-4b43-a53a-3407751e17c0)
<br><br><br>
 
### ✔️ 전략<br>
&nbsp; - 선수 배치하여 전략 저장 (좌표)<br>
&nbsp; - 감독 : 선수 배치, 전략 저장, 전략 조회, 전략 수정, 전략 삭제 <br>
![042](https://github.com/user-attachments/assets/1b20281c-f18d-441d-a359-f74818db9a5d)
<br><br><br>

### ✔️ 훈련 일지<br>
&nbsp; - 일지 유형, 인원, 장소, 일정, 메모 입력하여 저장하여 캘린더 관리 <br>
&nbsp; - 감독 : 일지 저장, 조회, 수정, 삭제 <br>
![043](https://github.com/user-attachments/assets/8aa58d82-a568-445b-82e4-fc692804484b)
<br><br><br>

### ✔️ 문의 게시판<br>
&nbsp; - 공통: 공개글/비밀글 작성, 조회(페이징), 수정, 삭제, 검색<br> 
&nbsp; - 비밀글 조회, 수정, 삭제(비밀번호 모달창 이용) <br>
&nbsp; - 관리자: 공개 글, 비밀 글 조회 및 삭제, 답변 가능<br>
![023](https://github.com/user-attachments/assets/a1bed746-0780-4d33-a603-6d509655cd5e)
![024](https://github.com/user-attachments/assets/c12848aa-592f-42ef-8c0f-5b40d3a5e7cc)
![028](https://github.com/user-attachments/assets/db3d8691-7259-4d87-b34a-e00fbae48d73)
![030](https://github.com/user-attachments/assets/480e1437-e654-47e7-a8e8-6f1973fc6463)
<br><br><br>

### ✔️ 회원 정보<br>
&nbsp; - 관리자 : 회원 정보 조회, 삭제 및 계정 승인, 보류 <br>
&nbsp; - 회원 연령대별 선호 종목, 종목별 팀 선호도 데이터 시각화 (파이썬)<br><br>
![046](https://github.com/user-attachments/assets/24331af7-162d-4957-8140-bbb5dd20c833)
![049](https://github.com/user-attachments/assets/8e36c915-3f0d-4fba-bf7e-a52faf670252)
<br><br><br>
