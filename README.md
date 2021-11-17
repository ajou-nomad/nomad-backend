## 아주대학교 SW 캡스톤 디자인 - Team Nomad [![Build Status](https://www.travis-ci.com/ajou-nomad/nomad-backend.svg?branch=master)](https://www.travis-ci.com/ajou-nomad/nomad-backend)
### Title: Dutch Delivery

---
# 프로젝트 소개

## 더치딜리버리란?

더치딜리버리는 <b>위치 기반 공동 배달 플랫폼</b>이다.

소비자들은 동일한 위치, 시간에 여러 사람과 같이 배달에 참여하여 부담되었던 <b>배달비와 최소 주문 금액 걱정 없이</b> 배달을 즐기고, 점주는 여러 명의 주문을 한 번에 보내기 때문에 <b>기존보다 적은 수수료로 더 많은 배달 서비스</b>를 제공받으며, 배달원은 저희가 새롭게 규정한 수수료 정책에 혜택을 받아 <b>노동에 비해 더 많은 수수료를</b> 얻어가는 배달 서비스를 제공해줍니다.

## 개발 동기 및 목적

최근에 코로나 19로 인하여 외식이나 모임이 줄어들고 집에 있는 시간이 늘어남에 따라 <b>배달 서비스가 많이 이용되는 추세</b>이다. 그에 따라 개인이 배달을 시키는 경우도 늘어나게 되었는데 기존의 서비스는 <b>최소주문금액이 높아 개인이 주문을 하지 못하는 경우</b> 가 생기고 최소주문금액을 맞추었더라도 <b>배달비가 추가되어 배달 서비스를 이용할 때 재정적 부담이 예상보다 커지는 경우</b> 가 많이 일어난다. 또한 늘어나는 배달 수수료로 인하여 가게 점주들도 <b>배달 서비스를 사용하는데 경제적 부담</b> 을 가지고 있으며 배달 서비스의 사용이 늘어남에 따라 <b>배달원들의 전체 노동시간이 증가</b> 되고 있는 상황이다. 이러한 여러 이해관계자들의 불편들을 해소하기 위하여 ‘더치딜리버리’라는 서비스를 개발하였다.

## 기대효과

우리는 더치딜리버리를 통하여 기존의 배달 서비스에서 생긴 불편사항들을 해결하고자 하며 그로 인하여 배달 서비스가 더 활성화되는 것을 목표로 하고 있다.


# 1. 구현 기능

## 1.1 메인 기능

### 1.1.1 당일 모집 - 유저의 배달 그룹 생성 및 확인

당일 모집은 당일에 생성된 배달에 참여하는 기능으로 원하는 장소와 시간을 정해서 직접 당일 모집 배달을 생성할 수 있다.

<img src="https://user-images.githubusercontent.com/55270881/121771655-91268900-cbab-11eb-96a9-101e02da1aa2.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121797370-b0302400-cc5a-11eb-9931-8c0727daf2d8.gif" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121780512-a2878980-cbdb-11eb-9a21-51f6d6593844.gif" width="250">

- 당일 모집을 선택
- 지도에 생성된 배달이 없는 경우 '원하는 조건이 없나요?' 버튼을 눌러 원하는 위치를 선택
- 배달 가능한 주변 매장 목록 중 원하는 매장과 메뉴를 선택

<img src="https://user-images.githubusercontent.com/55270881/121780608-1cb80e00-cbdc-11eb-996f-2f7c90df1fe7.gif" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121781444-b339fe80-cbdf-11eb-91d1-69afc8255eb5.gif" width="250">

- 배달 받을 장소의 건물명, 배달 시간, 모집 인원을 입력
- 결제가 완료되면 생성된 배달이 지도에 마커로 표시되고, 주문 내역 페이지에서 자신의 주문 내역을 확인 가능

### 1.1.2 주간 모집 - 유저의 그룹 생성 및 참여

주간 모집은 한 주간의 배달을 미리 예약하는 기능이다.

<img src="https://user-images.githubusercontent.com/55270881/121782725-fbf4b600-cbe5-11eb-8a8e-61844e54f35c.png" width="230"> <img src="https://user-images.githubusercontent.com/55270881/121782912-e8961a80-cbe6-11eb-9b9a-cea216b00e4b.png" width="230"> <img src="https://user-images.githubusercontent.com/55270881/121783010-7eca4080-cbe7-11eb-84dc-f22bec880e84.gif" width="230"> <img src="https://user-images.githubusercontent.com/55270881/121797759-4a916700-cc5d-11eb-8838-f6ba268ef6f3.gif" width="230">

- 주간 모집 선택
- 원하는 배달 장소 선택
- 캘린더를 통해 생성된 배달 그룹을 확인하고, 원하는 배달 그룹이 없는 경우 직접 생성
- 건물명, 배달 날짜 및 시간, 모집 인원 입력
- 당일 모집과 동일하게 결제를 완료하면 생성된 배달이 표시되고, 주문 내역 페이지에서 자신의 주문 내역을 확인 가능

### 1.1.3 매장의 주문 접수

<img src="https://user-images.githubusercontent.com/55270881/121783843-e84c4e00-cbeb-11eb-9fa1-1389f21e0c02.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121783840-e71b2100-cbeb-11eb-83dc-5ed83917ee6b.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121783844-e8e4e480-cbeb-11eb-9b5c-a41e3c6bf913.png" width="250">

- 매장은 최소 모집 인원이 충족된 주문 건들을 볼 수 있고, 주문 접수 가능
- 조리가 완료되면 배달 가능한 상태로 변경

### 1.1.4 배달원의 배달 선택

<img src="https://user-images.githubusercontent.com/55270881/121784289-2480ae00-cbee-11eb-9956-ff0b605902ad.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121784293-25b1db00-cbee-11eb-8e69-694cb785df4b.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121784294-264a7180-cbee-11eb-8a39-f101aea23be7.png" width="250">

- 배달원은 자신의 위치를 기반으로 배달 가능 목록을 확인 가능
- 원하는 배달 선택

### 1.1.5 배달원의 메시지 전달

<img src="https://user-images.githubusercontent.com/55270881/121784699-5a269680-cbf0-11eb-9e54-ec985526cc83.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121784701-5abf2d00-cbf0-11eb-8290-a052f836233f.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121784702-5b57c380-cbf0-11eb-8ac4-87f04f85ba08.png" width="250">

- 배달을 선택하게 되면 배달원과 배달에 참여한 유저들과 채팅방으로 연결
- 배달 현황을 전달
- 배달 완료 버튼을 통해 배달 마무리 가능

## 1.2 부가기능

<details>
      <summary>자세히 보기</summary>

### 1.2.1 유저

<img src="https://user-images.githubusercontent.com/55270881/121799375-b1674e00-cc66-11eb-9404-041f66ebb817.gif" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121799458-32bee080-cc67-11eb-973c-96015d07e09e.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121799459-33f00d80-cc67-11eb-85e7-57c411fdae3b.png" width="250">

유저는 배달 완료된 주문 건에 대해서 리뷰 작성 가능

### 1.2.2 점주

- 메뉴 관리 수정 기능

<img src="https://user-images.githubusercontent.com/55270881/121804915-c1d9f180-cc83-11eb-8497-7ba2b3db55c5.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121804917-c30b1e80-cc83-11eb-9768-7c2de0df3629.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121804918-c3a3b500-cc83-11eb-9547-d80d7974227b.png" width="250">

팥인절미 1인 빙수 가격 변경

- 메뉴 관리 삭제 기능

<img src="https://user-images.githubusercontent.com/55270881/121805025-3e6cd000-cc84-11eb-9e93-34636a45875e.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121805026-3f9dfd00-cc84-11eb-8f0d-4d22131f5e7e.png" width="250">

아인슈페너 ICE 삭제

- 메뉴 관리 메뉴 추가 기능

<img src="https://user-images.githubusercontent.com/55270881/121805059-68be8d80-cc84-11eb-9be3-c57d2e9162a8.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121805061-69efba80-cc84-11eb-9944-a095898231b0.png" width="250">

카라멜 프로틴 밀크 추가

- 그 외 기능들

<img src="https://user-images.githubusercontent.com/55270881/121805109-a3282a80-cc84-11eb-9402-c942a4ceef9d.png" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121873811-b816c380-cd41-11eb-9626-9f32d26762fe.jpg" width="250"> <img src="https://user-images.githubusercontent.com/55270881/121805112-a4595780-cc84-11eb-9036-539918ba8b5f.png" width="250">

완료된 주문, 매출현황, 매장 관리

</details>

---

# 2. backend

## 2.1 Architecture
![](img/스택.PNG)

## 2.2 Development Tools
![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white)
![springboot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=Spring-Boot&logoColor=white)
![mar](https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white)
![in](https://img.shields.io/badge/IntelliJ-000000?style=flat-square&logo=IntelliJ-IDEA&logoColor=white)
![aws](https://img.shields.io/badge/Amazon%20AWS-232F3E?style=flat-square&logo=Amazon-AWS&logoColor=white)

## 2.3 Spring Setting
application.yml
```

# FOR AMAZON RDS // 실제 서비스 DB
spring:
  datasource:
    url: #RDS url
    username: #RDS admin
    password: #RDS password
    driver-class-name: org.mariadb.jdbc.Driver

  jpa:
    generate-ddl: true
    hibernate:
    ddl-auto: create
  properties:
    hibernate:
      show_sql: true
      format_sql: true
logging.level:
  org.hibernate.SQL: debug
  org.hibernate.type: trace
  
# FOR LOCAL DATABASE(H2) // 테스트 및 개발
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/test
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    generate-ddl: true
    hibernate:
    ddl-auto: create-drop
  properties:
    hibernate:
      show_sql: true
      format_sql: true
logging.level:
  org.hibernate.SQL: debug
  org.hibernate.type: trace


```



# 3. CI/CD

## 3.1 DevOps/Test Tools
![travis](https://img.shields.io/badge/Travis%20CI-3EAAAF?style=flat-square&logo=Travis-CI&logoColor=white)
![slack](https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=Slack&logoColor=white)
![github](https://img.shields.io/badge/Github-181717?style=flat-square&logo=Github&logoColor=white)
![s3](https://img.shields.io/badge/Amazon%20S3-569A31?style=flat-square&logo=Amazon-S3&logoColor=white)
![deploy](https://img.shields.io/badge/Amazon-CodeDeploy-green)
![postman](https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white)

## 3.2 CI/CD Architecture
![](img/cicd.png)

## 3.3 Build/Notify/Deploy 
.travis.yml : Build, Notify, Deploy의 information Setting
```
language: java
jdk:
  - openjdk11

branches:
  only:
    - master

# Travis CI 서버의 Home
cache:
  directories:
    - '$HOME/.m2/repository'
    - '$HOME/.gradle'

script: "./gradlew clean build"

before_install:
  - chmod +x gradlew
# CI 실행 완료시 메일로 알람
notifications:
  email:
    recipients:
      - gmail주소
  slack: nomad 슬랙 주소

before_deploy:
  - zip -r nomad *
  - mkdir -p deploy
  - mv nomad.zip deploy/nomad.zip
  -
deploy:
  - provider: s3
    access_key_id: $AWS_ACCESS_KEY
    secret_access_key: $AWS_SECRET_KEY
    bucket: leesk
    region: ap-northeast-2
    skip_cleanup: true
    acl: public_read
    local_dir: deploy
    wait-until-deployed: true
    on:
      repo: ajou-nomad/nomad-backend
      branch: master

  - provider: codedeploy
    access_key_id: $AWS_ACCESS_KEY # Travis repo settings에 설정된 값
    secret_access_key: $AWS_SECRET_KEY # Travis repo settings에 설정된 값
    bucket: leesk # S3 버킷
    key: nomad.zip # 빌드 파일을 압축해서 전달
    bundle_type: zip
    application: nomad # 웹 콘솔에서 등록한 CodeDeploy 어플리케이션
    deployment_group: Team-nomad # 웹 콘솔에서 등록한 CodeDeploy 배포 그룹
    region: ap-northeast-2
    wait-until-deployed: true
    on:
      repo: ajou-nomad/nomad-backend
      branch: master
```

appspec.yml : Deploy가 되고 난 후, 실행될 명령어 모집 hooks Setting
```
version: 0.0
os: linux
files:
  - source:  /
    destination: /home/ec2-user/app/travis/build

hooks:
  AfterInstall: # 배포가 끝나면 아래 명령어를 실행
    - location: execute-deploy.sh
      timeout: 180
```

# 4. 데이터베이스

## 4.1 데이터베이스 전체 개요
![데이터베이스 개요](img/2.png)

[데이터베이스 사이트 바로가기](https://www.erdcloud.com/d/7AzuwXxHN6HnoKKhc)

## 4.2 데이터베이스 Detail

### 4.2.1 사용자

![](img/member1.png)
- memberId: member 엔티티의 primary Key
- groupId: 사용자가 그룹에 참여했을시, member 엔티티와 group 엔티티의 일대다 관계 매핑을 위한 foreign key
- email: 사용자 이메일
- nickName: 사용자가 사용할 닉네임
- token: Firebase cloud messaging을 위한 토큰 값
- uid: 파이어베이스와 같이 연동해서 사용하기 위한 사용자 uid
- point: 취소, 주문 이동에 유연하게 대처하기 위한 포인트
- memberType: 사용자, 매장, 배달원을 구분하기 위한 타입값
- shopIdNumber: 매장 가입시 필요한 사업자 번호
- deliIdNumber: 배달원 가입시 필요 운전면허번호

### 4.2.2 개인주문
![](img/memberOrder.png)
- memberOrderId: memberOrder 엔티티의 primary key
- storeId: 연관관계 매핑을 해놨지만 좀 더 간편하게 조회할 필요가 있어서 추가 등록
- totalCost: 주문 상품의 총 가격
- payMethod: 결제 장법
- orderTime: 주문 시간
- memberId: 사용자가 주문을 헀을 시, 사용자는 자신의 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 member 엔티티간의 일대다 관계 매핑을 위한 foreign key
- storeId2: 사용자가 주문을 했을 시, 해당 매장에서도 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 store 엔티티간의 일대다 관계 매핑을 위한 foreign key
- groupId: 사용자가 그룹에 참여했을 시, group 엔티티에서 그룹에 참여하고 있는 사용자들의 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 group 엔티티 매핑을 위한 foreign key

### 4.2.3 주문목록
![](img/orderItem.png)
- orderItemId: orderItem 엔티티의 primary key
- cost: 주문 상품 가격
- menuName: 주문한 메뉴 이름
- quantity: 주문 수량
- memberId: 사용자에서 memberOrder를 통해서 식별하기 보다 member를 가지고 주문 상품을 식별하는게 더 편리하다고 판단하여 member 엔티티와 orderItem 엔티티 일대다 매핑을 위한 foreign key
- memberOrderId: memberOrder에서 주문한 상품을 식별할 수 있어야 하기 때문에 memberOrder 엔티티와 orderItem 엔티티 일대다 관계 매핑을 위한 foreign key

### 4.2.4 배달그룹
![](img/deliveryGroup.png)
- groupId: deliveryGroup 엔티티의 primary key
- storeId: 해당 배달의 주문이 어떤 매장에 주문했는지 알아야 되므로 storeId 저장
- latitude: 해당 배달의 목적지 위도
- longitude: 해당 배달의 목적지 경도
- address: 해당 배달의 목적지 주소
- buildingName: 건물로 배달해야 하므로 정확한 건물 이름정보
- current: 현재 배달그룹에 참여중인 인원
- maxValue: 모집해야하는 인원
- deliveryDateTime: 배달 도착 시간
- orderStatus: 주문의 현재 상태를 식별하기 위한 attribute(recruiting, recruitmentDone, recruitmentAccept, waitingForDelivery, delivering, deliveryDone)
- groupType: 그룹이 당일모집인지 주간모집인지를 식별
- chatId: 채팅방을 만들때, 배달그룹을 기준으로 속해있는 사용자들을 식별해야 하므로 deliveryGroup 엔티티와 chat 엔티티 일대일 관계 매핑을 위한 foreign key  

### 4.2.5 매장
![](img/store.png)
- storeId: store 엔티티의 primary key
- storeName: 매장 이름
- phoneNumber: 매장 전화번호
- address: 매장 주소
- latitude: 매장 위도
- longitude: 매장 경도
- openTime: 매장 오픈시간
- closeTime: 매장 닫는시간
- deliveryTip: 배달 수수료
- logoUrl: 매장 로고 url
- rate: 매장 평점
- notice: 매장 공지사항
- storeIntro: 매장 소개
- category: 매장의 카테고리 분류를 위한 attribute

### 4.2.6 찜한매장
![](img/likeStore.png)
- likeStoreId: likeStore 엔티티의 primary key
- uid: 사용자 식별을 편리하게 하기 위해 uid값 저장
- memberId: 사용자가 여러매장을 찜할 수 있으므로 member 엔티티와 likeStore 엔티티 일대다 관계 매핑을 위한 foreign key
- storeId: 한 매장을 여러 사용자가 찜할 수 있으므로 store 엔티티와 likeStore 엔티티 일대다 관계 매핑을 위한 foreign key

### 4.2.7 메뉴
![](img/menu.png)
- menuId: menu 엔티티의 primary key
- menuName: 메뉴 이름
- cost: 메뉴 가격
- description: 메뉴 소개
- imgUrl: 메뉴 imgUrl
- storeId: 메뉴는 매장에 속해야 하므로 store 엔티티와 menu 엔티티 일대다 관계 매핑을 위한 foreign key

### 4.2.8 프로모션메뉴
![](img/promotionMenu.png)
- promotionMenuId: promotionMenu 엔티티의 primary key
- promotionMenuName: 프로모션메뉴 이름
- cost: 프로모션메뉴 가격
- description: 프로모션메뉴 소개
- promotionDescription: 프로모션 소개
- imgUrl: 프로모션메뉴 imgUrl
- storeId: 프로모션메뉴도 매장에 속해야 하므로 store 엔티티와 promotionMenu 엔티티 일대일 관계 매핑을 위한 foreign key

### 4.2.9 리뷰
![](img/review.png)

- reviewId: review 엔티티의 primary key
- nickName: 리뷰 남긴 사람의 닉네임
- contents: 리뷰 내용
- uid: 리뷰 작성자를 빠르게 조회하기 위한 uid
- 메뉴사진: imgUrl
- rate: 리뷰에 남길 별점
- localDateTime: 리뷰 작성시간
- memberId: 사용자가 리뷰를 볼 수 있어야 하므로 member 엔티티와 review 엔티티의 일대다 관계 매핑을 위한 foreign key
- memberOrderId: 사용자는 주문에 대해서 리뷰를 작성해야 하기때문에 memberOrder 엔티티와 review 엔티티 일대다 관계 매핑을 위한 foreign key
- storeId: 자신의 매장과 관련된 리뷰도 확인할 수 있어야 하기때문에 store 엔티티와 review 엔티티 일대다 관계 매핑을 위한 foreign key

### 4.2.10 채팅방
![](img/chat.png)
- chatId: chat 엔티티의 primary key
- chatToken: 채팅방 생성을 위한 Token 값
- memberId: 한명의 사용자가 여러 채팅방에 들어갈 수 있기 때문에 member 엔티티와 chat 엔티티 일대다 관계 매핑을 위한 foreign key

### 4.2.11 사용자채팅방
![](img/memchat.png)
- memberChatId: memberChat 엔티티의 primary key
- memberId: 채팅방과 사용자의 다대다 매핑을 풀기 위해 member 엔티티와 memberChat 엔티티의 일대다 관계 매핑을 위한 foreign key
- chatId: 채팅방과 사용자의 다대다 매핑을 풀기 위해 chat 엔티티와 memberChat 엔티티의 일대다 관계 매핑을 위한 foreign key

# 5. API

POST `/member`: 회원가입<br>
POST `/memberOrderCancel` : 주문취소(조건: 모집완료 되지 않는 주문) <br>
GET `/member` : DB에 저장되어 있는 member 정보 중에 토큰을 조회해서 회원가입이 되어있는지 확인<br>
GET `/memberList` : 가입된 회원목록 조회<br>
GET `/memberOrderList` : 사용자의 주문내역 불러오기 (주문목록, 작성리뷰 포함)<br>

POST `/deliveryGroup` : 배달그룹 생성<br>
POST `/deliveryGroupJoin` : 그룹 참여<br>
POST `/deliveryGroupOrder` : 모집 완료된 주문 상태 변경(매장에서 접수)<br>
GET `/deliveryGroupOrder` : 모집 완료된 주문 불러오기 <br>
GET `/alldeliveryGroupOrder` : 모집중, 모집완료 주문을 제외한 배달 불러오기, 매장 관리용 <br>
GET `/allGroupList` : 생성된 배달 그룹 전부 불러오기<br>
GET `/dailyGroupList` : 당일 모집 배달 그룹 전부 불러오기<br>
GET `/weeklyGroupList` : 주간 모집 배달 그룹 전부 불러오기<br>
GET `/deliveryComplete` : 배달 완료된 그룹 불러오기(매장 보관용)

POST `/store` : 매장 생성<br>
GET `/storeList` : 배달 그룹 생성할 때, 전체 매장 불러오기(메뉴, 리뷰 포함)<br>
GET `/myStoreList` : 관리하는 매장 불러오기<br>
GET `/sales` : 월별 총매출 불러오기<br>

POST `/menu` : 메뉴 등록<br>
POST `/deleteMenu` : 메뉴 삭제<br>
POST `/modifyMenu` : 메뉴 수정<br>
POST `/addMenu` : 메뉴 추가<br>
POST `/promotionMenu` : 프로모션 메뉴 등록<br>

POST `/likeStore` : 찜한 매장 등록<br>
POST `/deleteLikeStore` : 찜한 매장 삭제<br>
GET `/likeStore` : 찜한 매장 불러오기<br>

POST `/chatId` : 채팅방을 위한 ChatToken 부여<br>
GET `/chatId` : 회원이 참여하고 있는 채팅방의 토큰들 불러오기<br>

POST `/review` : 사용자의 리뷰 작성<br>
POST `/deleteReview` : 사용자 리뷰 삭제<br>
GET `/memberReview` : 사용자 로그인 시, 사용자가 작성한 리뷰 불러오기<br>
GET `/storeReview` : 매장 로그인 시, 매장을 대상으로 작성된 리뷰 불러오기<br>

POST `/delivery` : 배달 접수하기(채팅방 생성 포함)<br>
POST `/deliveryComplete` : 배달 완료<br>
GET `/delivery` : 배달이 필요한 주문들 불러오기 (WaitingForDelivery 상태인 배달들)

# 5. 스케쥴러
- Spring의 `@Scheduled`를 사용해 구현

## 5.1 그룹 배달 시간이 되었는데 모집이 완료 되지 않은 상황
해당 배달 그룹은 모집 취소가 되며, 해당 그룹에 참여한 사람들의 결제금액은 포인트로 돌려준다.