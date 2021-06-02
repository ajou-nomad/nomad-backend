## 아주대학교 SW 캡스톤 디자인 - Team Nomad
### Title: Dutch Delivery

---

# backend

## 데이터베이스

### 데이터베이스 전체 개요
![데이터베이스 개요](img/1.png)

[데이터베이스 사이트 바로가기](https://www.erdcloud.com/d/7AzuwXxHN6HnoKKhc)

### Detail

#### 사용자
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

#### 개인주문
![](img/memberOrder.png)
- memberOrderId: memberOrder 엔티티의 primary key
- storeId: 연관관계 매핑을 해놨지만 좀 더 간편하게 조회할 필요가 있어서 추가 등록
- totalCost: 주문 상품의 총 가격
- payMethod: 결제 장법
- orderTime: 주문 시간
- memberId: 사용자가 주문을 헀을 시, 사용자는 자신의 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 member 엔티티간의 일대다 관계 매핑을 위한 foreign key
- storeId2: 사용자가 주문을 했을 시, 해당 매장에서도 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 store 엔티티간의 일대다 관계 매핑을 위한 foreign key
- groupId: 사용자가 그룹에 참여했을 시, group 엔티티에서 그룹에 참여하고 있는 사용자들의 주문을 식별할 필요가 있기 때문에 memberOrder 엔티티와 group 엔티티 매핑을 위한 foreign key

#### 주문목록
![](img/orderItem.png)
- orderItemId: orderItem 엔티티의 primary key
- cost: 주문 상품 가격
- menuName: 주문한 메뉴 이름
- quantity: 주문 수량
- memberId: 사용자에서 memberOrder를 통해서 식별하기 보다 member를 가지고 주문 상품을 식별하는게 더 편리하다고 판단하여 member 엔티티와 orderItem 엔티티 일대다 매핑을 위한 foreign key
- memberOrderId: memberOrder에서 주문한 상품을 식별할 수 있어야 하기 때문에 memberOrder 엔티티와 orderItem 엔티티 일대다 관계 매핑을 위한 foreign key

#### 배달그룹
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

#### 매장
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

#### 찜한매장
![](img/likeStore.png)
- likeStoreId: likeStore 엔티티의 primary key
- uid: 사용자 식별을 편리하게 하기 위해 uid값 저장
- memberId: 사용자가 여러매장을 찜할 수 있으므로 member 엔티티와 likeStore 엔티티 일대다 관계 매핑을 위한 foreign key
- storeId: 한 매장을 여러 사용자가 찜할 수 있으므로 store 엔티티와 likeStore 엔티티 일대다 관계 매핑을 위한 foreign key

#### 메뉴
![](img/menu.png)
- menuId: menu 엔티티의 primary key
- menuName: 메뉴 이름
- cost: 메뉴 가격
- description: 메뉴 소개
- imgUrl: 메뉴 imgUrl
- storeId: 메뉴는 매장에 속해야 하므로 store 엔티티와 menu 엔티티 일대다 관계 매핑을 위한 foreign key

#### 프로모션메뉴
![](img/promotionMenu.png)
- promotionMenuId: promotionMenu 엔티티의 primary key
- promotionMenuName: 프로모션메뉴 이름
- cost: 프로모션메뉴 가격
- description: 프로모션메뉴 소개
- promotionDescription: 프로모션 소개
- imgUrl: 프로모션메뉴 imgUrl
- storeId: 프로모션메뉴도 매장에 속해야 하므로 store 엔티티와 promotionMenu 엔티티 일대일 관계 매핑을 위한 foreign key

#### 리뷰
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

#### 채팅방
![](img/chat.png)
- chatId: chat 엔티티의 primary key
- chatToken: 채팅방 생성을 위한 Token 값
- memberId: 한명의 사용자가 여러 채팅방에 들어갈 수 있기 때문에 member 엔티티와 chat 엔티티 일대다 관계 매핑을 위한 foreign key

## API

POST `/member`: 회원가입<br>
GET `/member` : DB에 저장되어 있는 member 정보 중에 토큰을 조회해서 회원가입이 되어있는지 확인<br>
GET `/memberList` : 가입된 회원목록 조회<br>
GET `/memberOrderList` : 사용자의 주문내역 불러오기 (주문목록, 작성리뷰 포함)<br>

POST `/deliveryGroup` : 배달그룹 생성<br>
POST `/deliveryGroupJoin` : 그룹 참여<br>
POST `/deliveryGroupOrder` : 모집 완료된 주문 상태 변경(매장에서 접수)<br>
GET `/deliveryGroupOrder` : 모집 완료된 주문 불러오기 <br>
GET `/allGroupList` : 생성된 배달 그룹 전부 불러오기<br>
GET `/dailyGroupList` : 당일 모집 배달 그룹 전부 불러오기<br>
GET `/weeklyGroupList` : 주간 모집 배달 그룹 전부 불러오기<br>
GET `/deliveryComplete` : 배달 완료된 그룹 불러오기(매장 보관용) // 수정요망<br>

POST `/store` : 매장 생성<br>
GET `/storeList` : 배달 그룹 생성할 때, 전체 매장 불러오기(메뉴, 리뷰 포함)<br>
GET `/myStoreList` : 관리하는 매장 불러오기<br>
GET `/sales` : 월별 총매출 불러오기<br>

POST `/menu` : 메뉴 등록<br>
POST `/promotionMenu` : 프로모션 메뉴 등록<br>

POST `/likeStore` : 찜한 매장 등록<br>
GET `/likeStore` : 찜한 매장 불러오기<br>

POST `/chatId` : 채팅방을 위한 ChatToken 부여<br>
GET `/chatId` : 회원이 참여하고 있는 채팅방의 토큰들 불러오기<br>

POST `/review` : 사용자의 리뷰 작성<br>
GET `/memberReview` : 사용자 로그인 시, 사용자가 작성한 리뷰 불러오기<br>
GET `/storeReview` : 매장 로그인 시, 매장을 대상으로 작성된 리뷰 불러오기<br>

POST `/delivery` : 배달 접수하기(채팅방 생성 포함)<br>
POST `/deliveryComplete` : 배달 완료<br>
GET `/delivery` : 배달이 필요한 주문들 불러오기 (WaitingForDelivery 상태인 배달들) // 수정요망<br>

삭제 및 취소는 추후 추가.

## 스케쥴러

### CASE1: 그룹 배달 시간이 되었는데 모집이 완료 되지 않은 상황
해당 배달 그룹은 모집 취소가 되며, 해당 그룹에 참여한 사람들의 결제금액은 포인트로 돌려준다.

### CASE2: `시`를 기준으로 1시간 내외가 남았는데 아직 모집이 완료되지 않은 상황
해당 배달 그룹 사람들에게 다른 배달 그룹을 추천해주며, 추천 기준은 200M 내외이고 모집된 인원이 기존 배달 그룹보다 많이 모인 그룹중에, 동일한 이름의 매장에 배달한 그룹 또는 동일한 카테고리를 가진 매장에 배달한 그룹을 추천해준다.