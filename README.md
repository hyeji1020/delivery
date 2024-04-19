# 배달 API

### <고객>
1. 회원가입
- 이름, 이메일(아이디), 비밀번호, 권한(USER)

2. 로그인
- JWT으로 로그인 검증
  
3. 배달 음식 조회
- 음식 아이디로 조회
- 가게에 저장된 음식 조회
  
4. 배달 음식 주문하기
- 가게, 음식 종류, 음식 수량으로 주문
  
5. 결제하기
- 결제 방법 선택(카드, 현금)
- 결제하기

### <판매자>
1. 회원가입
- 이름, 이메일(아이디), 비밀번호, 권한(ADMIN)
  
2. 로그인
- JWT으로 로그인 검증
     
4. 가게 & 음식 등록, 수정, 삭제
5. 주문현황 관리(주문접수(수락, 거절), 배달(시작, 완료))

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
![배달테이블](https://github.com/hyeji1020/delivery/assets/148170765/3f4b4f0e-badf-45b0-addb-a5780d0eeeff)








