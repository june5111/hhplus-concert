## Description

- **`콘서트 예약 서비스`**를 구현해 봅니다.
- 대기열 시스템을 구축하고, 예약 서비스는 작업가능한 유저만 수행할 수 있도록 해야합니다.
- 사용자는 좌석예약 시에 미리 충전한 잔액을 이용합니다.
- 좌석 예약 요청시에, 결제가 이루어지지 않더라도 일정 시간동안 다른 유저가 해당 좌석에 접근할 수 없도록 합니다.

## Requirements

- 아래 5가지 API 를 구현합니다.
    - 유저 토큰 발급 API
    - 예약 가능 날짜 / 좌석 API
    - 좌석 예약 요청 API
    - 잔액 충전 / 조회 API
    - 결제 API
- 각 기능 및 제약사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.
- 대기열 개념을 고려해 구현합니다.

## API Specs

1️⃣ **`주요` 유저 대기열 토큰 기능**

- 서비스를 이용할 토큰을 발급받는 API를 작성합니다.
- 토큰은 유저의 UUID 와 해당 유저의 대기열을 관리할 수 있는 정보 ( 대기 순서 or 잔여 시간 등 ) 를 포함합니다.
- 이후 모든 API 는 위 토큰을 이용해 대기열 검증을 통과해야 이용 가능합니다.

> 기본적으로 폴링으로 본인의 대기열을 확인한다고 가정하며, 다른 방안 또한 고려해보고 구현해 볼 수 있습니다.
> 

**2️⃣ `기본` 예약 가능 날짜 / 좌석 API**

- 예약가능한 날짜와 해당 날짜의 좌석을 조회하는 API 를 각각 작성합니다.
- 예약 가능한 날짜 목록을 조회할 수 있습니다.
- 날짜 정보를 입력받아 예약가능한 좌석정보를 조회할 수 있습니다.

> 좌석 정보는 1 ~ 50 까지의 좌석번호로 관리됩니다.
> 

3️⃣ **`주요` 좌석 예약 요청 API**

- 날짜와 좌석 정보를 입력받아 좌석을 예약 처리하는 API 를 작성합니다.
- 좌석 예약과 동시에 해당 좌석은 그 유저에게 약 5분간 임시 배정됩니다. ( 시간은 정책에 따라 자율적으로 정의합니다. )
- 만약 배정 시간 내에 결제가 완료되지 않는다면 좌석에 대한 임시 배정은 해제되어야 하며 다른 사용자는 예약할 수 없어야 한다.

4️⃣ **`기본`**  **잔액 충전 / 조회 API**

- 결제에 사용될 금액을 API 를 통해 충전하는 API 를 작성합니다.
- 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
- 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

5️⃣ **`주요` 결제 API**

- 결제 처리하고 결제 내역을 생성하는 API 를 작성합니다.
- 결제가 완료되면 해당 좌석의 소유권을 유저에게 배정하고 대기열 토큰을 만료시킵니다.

### **`STEP 05`**

- 시나리오 선정  및 프로젝트 Milestone 제출
- 시나리오 요구사항 분석 자료 제출 (e.g. 시퀀스 다이어그램, 플로우 차트 등 )

### **`STEP 06`**

- ERD 설계 자료 제출
- API 명세 및 Mock API 작성
- Github Repo 제출 ( 기본 패키지 구조, 서버 Configuration 등 )

# 콘서트 예약 서비스

### [Milestone] (https://github.com/users/june5111/projects/1/views/2)
---------
### [Sequence Diagram]
<img width="668" alt="스크린샷 2024-07-05 오전 1 00 16" src="https://github.com/june5111/git_repository_test/assets/130432762/fabc7ef5-af3e-497d-9f85-b0f32eec1017">
<img width="643" alt="스크린샷 2024-07-05 오전 1 04 09" src="https://github.com/june5111/git_repository_test/assets/130432762/24432ac9-3c6c-4f6b-9f7f-e9f876cbeb68">
<img width="590" alt="스크린샷 2024-07-05 오전 1 07 24" src="https://github.com/june5111/git_repository_test/assets/130432762/deac0692-3756-4347-9f2f-c9efcac93332">
<img width="583" alt="스크린샷 2024-07-05 오전 1 08 23" src="https://github.com/june5111/git_repository_test/assets/130432762/a6a22d7f-e1d0-436c-b441-c038b12526f7">
<img width="635" alt="스크린샷 2024-07-05 오전 1 15 19" src="https://github.com/june5111/git_repository_test/assets/130432762/fd9ba107-7602-4cda-829d-fbec02b6df07">
<img width="739" alt="스크린샷 2024-07-05 오전 12 54 49" src="https://github.com/june5111/git_repository_test/assets/130432762/5b8c3ef3-02b1-4f03-bf35-0f3adccc681d">
<img width="663" alt="스크린샷 2024-07-05 오전 12 55 10" src="https://github.com/june5111/git_repository_test/assets/130432762/98cad5e4-57dc-4a6a-8be4-9ad97fb646b4">
<img width="577" alt="스크린샷 2024-07-05 오전 12 55 29" src="https://github.com/june5111/git_repository_test/assets/130432762/29abb78c-4f39-4f6d-9708-679ea1596650">
---------
### [ERD]
<img width="2138" alt="스크린샷 2024-07-05 오전 5 06 15" src="https://github.com/june5111/git_repository_test/assets/130432762/2a32f734-3570-48ea-bc01-7af7e5660ab4">
---------
### [API SPECES]
(https://github.com/june5111/hhplus-concert/wiki/API-%EB%AA%85%EC%84%B8%EC%84%9C)

