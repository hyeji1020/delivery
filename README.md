<div align="center">
    
### 🛵 delivery 🛵
배달어플의 기능을 구현한 배달 REST API 개인 프로젝트입니다

### 📌 서비스 개요
사용자 중심의 접근 방식을 통해 유명 배달 어플리케이션의 주요 기능을 REST API로 구현한 개인 프로젝트입니다. <br>
이 프로젝트는 **이커머스 플랫폼**에서 필수적인 **상품 관리, 주문 처리, 결제, 사용자 인증** 등의<br>
핵심 기능을 배달 서비스 관점에서 설계 및 개발하는 데 중점을 두었습니다.

### 🛠️ 기술 스택
![Java](https://img.shields.io/badge/Java-18-blue?logo=openjdk&logoColor=white)       
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.1-brightgreen?logo=springboot) 
![JPA](https://img.shields.io/badge/JPA-ORM-orange)                                  
![Spring Security](https://img.shields.io/badge/Spring%20Security-auth-green?logo=springsecurity) 
![MySQL](https://img.shields.io/badge/MySQL-8.0.32-blue?logo=mysql&logoColor=white)  
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white"/>
<img src="https://img.shields.io/badge/IntelliJ-A100FF?style=flat&logo=intellijidea&logoColor=white"/>
<img src="https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=white"/>

### ⏰ 개발 기간
2024-03 ~ 2024-03(4주)

</div>

## 구현 기능
**1. 회원가입**
* 사용자 정보를 입력하여 계정을 생성 (이름, 이메일, 비밀번호)
* 회원 권한 설정:
     * **OWNER**: 판매자
     * **CUSTOMER**: 사용자
     
**2. 로그인**
* **JWT 토큰**을 활용하여 사용자 인증 및 인가를 구현
* 권한(OWNER, CUSTOMER)에 따라 접근 가능한 API를 분리하여 보안 강화

**3. 주문 처리**
* 한 가게에서 여러 개의 음식 주문 가능
  - 사용자 주문 시, 주문 수량에 따른 소계 및 **총합 자동 계산**.
* 주문 상태 관리
  - 초기 상태: ACCEPTING(Default)
  - 사용자는 주문 상태를 CANCEL(취소)로 변경 가능
  
4. **OWNER(판매자)**
* 음식 및 가게 등록, 수정 기능 제공
* **주문 관리**
  - 판매자는 접수된 주문을 확인하고, 상태를 다음과 같이 변경 가능
     - **ACCEPTED**: 주문 수락
     - **REJECT**: 주문 거절
  
5. **CUSTOMER(사용자)**
* **가게 및 음식 조회**
* **주문 생성**
  - 음식 종류, 수량, 가게를 선택하여 주문 생성
  - 주문 진행 상태를 실시간으로 확인 가능(예: ACCEPTING, CANCEL)



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









