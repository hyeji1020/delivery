## 배달 API
배달어플의 기능을 구현한 배달 REST API 개인 프로젝트입니다.


## 📺 skills
<img src="https://img.shields.io/badge/java-007396?style=flat&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white"/>
<img src="https://img.shields.io/badge/IntelliJ-A100FF?style=flat&logo=intellijidea&logoColor=white"/>

## 구현 기능
1. 회원가입
* 이름, 이메일(아이디), 비밀번호, 권한(OWNER, CUSTOMER)
     
2. 로그인
* JWT 토큰으로 권한 인증, 인가
* 토큰 활용하여 권한에 따라 접근

3. 주문
* 한 가게에서만 여러개 음식 주문 가능
* 주문 수량에 따른 소계 합, 총합 계산
  
### OWNER(판매자)
* 음식, 가게 등록
* 주문 접수 들어왔을 때
  - ACCEPTED(수락) or REJECT(거절) 가능
  
  
### CUSTOMER(사용자)
* 가게, 음식 조회하여 주문
* 주문 접수
  - 가게, 음식 종류, 음식 수량 으로 주문
  - 주문 상태 현황 : ACCEPTING(Default) or CANCEL(취소) 가능



------------   
### REST API 설계
#### 음식
![image](https://github.com/hyeji1020/delivery/assets/148170765/e2007078-c2b8-4390-b82b-52f04e78b7df)
#### 주문
![image](https://github.com/hyeji1020/delivery/assets/148170765/d6e195f5-97c3-4962-8125-6514ca8fb39d)
#### 회원
![image](https://github.com/hyeji1020/delivery/assets/148170765/37bd9bfd-081e-45b0-b9e6-75f5ff0a3e91)
#### 가게
![image](https://github.com/hyeji1020/delivery/assets/148170765/10a53be6-3192-4449-9b22-d01d2bafa0fe)

------------
### DB 테이블 설계
![image](https://github.com/hyeji1020/delivery/assets/148170765/953c517f-be5f-49ca-8b87-8b4be404faa2)









