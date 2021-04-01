-- 회원등록
insert into MEMBERS (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_BIRTH, MEMBER_NICKNAME, MEMBER_ADDRESS1, MEMBER_ADDRESS2, MEMBER_TEL) values ('test@test.com', '1234', '홍길동', '1993-01-01', '테스터1', '울산광역시 남구 삼산로35번길 19', '5층 501호', '010-1234-5678');
insert into MEMBERS (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_BIRTH, MEMBER_NICKNAME, MEMBER_ADDRESS1, MEMBER_ADDRESS2, MEMBER_TEL) values ('test2@test.com', '1234', '전우치', '1993-02-01', '테스터2', '울산광역시 남구 삼산로35번길 19', '5층 502호', '010-1111-1111');
insert into MEMBERS (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_BIRTH, MEMBER_NICKNAME, MEMBER_ADDRESS1, MEMBER_ADDRESS2, MEMBER_TEL) values ('test3@test.com', '1234', '도깨비', '1993-02-01', '테스터3', '울산광역시 남구 삼산로35번길 19', '5층 502호', '010-2222-2222');
insert into MEMBERS (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_BIRTH, MEMBER_NICKNAME, MEMBER_ADDRESS1, MEMBER_ADDRESS2, MEMBER_TEL) values ('test4@test.com', '1234', '호랑이', '1993-02-01', '테스터3', '울산광역시 남구 삼산로35번길 19', '5층 502호', '010-3333-3333');
-- 회원조회
select count(*) from MEMBERS where MEMBER_ID = 'test2@test.com';
-- 중복확인 - 별명
select count(*) from MEMBERS where MEMBER_NICKNAME = '테스터2';
-- 중복확인 - 전화번호
select count(*) from MEMBERS where MEMBER_TEL = '010-1111-1111';
-- 아이디 찾기
select MEMBER_ID from MEMBERS where MEMBER_NAME = '전우치' and MEMBER_TEL = '010-1111-1111';
-- 비밀번호 찾기
select MEMBER_PW from MEMBERS where MEMBER_ID = 'test2@test.com' and MEMBER_TEL = '010-1111-1111';
-- 회원수정
update MEMBERS set MEMBER_NAME =  '전우치', MEMBER_BIRTH = '1993-02-02', MEMBER_NICKNAME = '테스터2', MEMBER_ADDRESS1 = '울산광역시 남구 삼산로35번길 19-1', MEMBER_ADDRESS2 = '5층 503호', MEMBER_TEL = '010-1111-1112' where MEMBER_ID = 'test2@test.com' and MEMBER_PW = '1234';
-- 회원탈퇴
delete MEMBERS where MEMBER_ID = 'test4@test.com' and MEMBER_PW = '1234';
	
-- 카페등록여부
select count(*) from CAFE where CAFE_OWNERID = 'test@test.com';
-- 카페등록
insert into CAFE (CAFE_OWNERID, CAFE_NAME, BN, CAFE_ADDRESS1, CAFE_ADDRESS2, CAFE_TEL, OPEN_TIME, CLOSE_TIME, CAFE_CONTENT) values ('test@test.com', '홍길동전', '123-45-67890', '울산광역시 남구 삼산로35번길 19', '5층 501호', '052-123-4567', '09:30', '22:00', '세련된 인테리어와 조용하고 맛있는 커피를 만드는 카페입니다.');
insert into CAFE (CAFE_OWNERID, CAFE_NAME, BN, CAFE_ADDRESS1, CAFE_ADDRESS2, CAFE_TEL, OPEN_TIME, CLOSE_TIME, CAFE_CONTENT) values ('test2@test.com', '전우치전', '123-45-67891', '울산광역시 남구 삼산로35번길 19', '5층 501호', '052-123-4567', '09:30', '22:00', '세련된 인테리어와 조용하고 맛있는 커피를 만드는 카페입니다.');
insert into CAFE (CAFE_OWNERID, CAFE_NAME, BN, CAFE_ADDRESS1, CAFE_ADDRESS2, CAFE_TEL, OPEN_TIME, CLOSE_TIME, CAFE_CONTENT) values ('test3@test.com', '홍길동전', '123-45-67892', '울산광역시 남구 삼산로35번길 19', '5층 501호', '052-123-4567', '09:30', '22:00', '세련된 인테리어와 조용하고 맛있는 커피를 만드는 카페입니다.');
-- 중복확인 - 사업자번호
select count(*) from CAFE where BN = '123-45-67890';
-- 카페조회
select count(*) from CAFE where CAFE_OWNERID = 'test@test.com';
-- 카페수정
update CAFE set CAFE_NAME = '홍길동전', CAFE_ADDRESS1 = '울산광역시 남구 삼산로35번길 19-1', CAFE_ADDRESS2 = '5층 501호', CAFE_TEL = '052-123-4567', OPEN_TIME = '10:00', CLOSE_TIME = '22:30', CAFE_CONTENT = '스타벅스 체인점이 됬습니다.' where CAFE_OWNERID = 'test@test.com';
-- 카페삭제
delete CAFE where CAFE_OWNERID = 'test3@test.com';

-- 태그등록
INSERT INTO TAGS (TAG_NAME) VALUES ('태그명1');
INSERT INTO TAGS (TAG_NAME) VALUES ('태그명2');
INSERT INTO TAGS (TAG_NAME) VALUES ('태그명3');
INSERT INTO TAGS (TAG_NAME) VALUES ('태그명4');
INSERT INTO TAGS (TAG_NAME) VALUES ('태그명5');

-- 카페태그 등록
insert into CAFETAGS (CAFE_NO, TAG_NO) VALUES (1, 1);
insert into CAFETAGS (CAFE_NO, TAG_NO) VALUES (1, 2);
insert into CAFETAGS (CAFE_NO, TAG_NO) VALUES (1, 3);
-- 카페태그 삭제
DELETE CAFETAGS WHERE CAFE_NO = 1 AND TAG_NO = 3;

-- 카페 이미지 등록
-- insert into CAFEFILE (CAFE_NO, CFILE_NAME, CFILE_SIZE, CFILE_TYPE, CFILE_URL) values ();
-- 카페 이미지 삭제
-- delete CAFEFILE where CAFE_NO = ? and CFILE_NO = ?;
-- 카페 이미지 불러오기
-- select * from CAFEFILE where CAFE_NO = ?;
	
-- 카페 위치표시
select CAFE_NO, CAFE_NAME from CAFE;
-- 카페 검색 - 이름
select CAFE_NO from CAFE where CAFE_NAME like '%홍길동전%';
-- 카페 검색 - 태그
select CAFE_NO from CAFETAGS where TAG_NO in (1, 2) GROUP by CAFE_NO having count(CAFE_NO) >= 2;
-- 상세카페 내용 보기
select * from CAFE where CAFE_NO = 1;
	
-- 즐겨찾기 추가
insert into BOOKMARKS (MEMBER_ID, CAFE_NO) values ('test2@test.com', 1);
-- 즐겨찾기 조회
select CAFE_NO from BOOKMARKS where MEMBER_ID = 'test2@test.com';
-- 즐겨찾기 삭제
delete BOOKMARKS where MEMBER_ID = 'test2@test.com' and CAFE_NO = 1;
	
-- 카테고리 등록
INSERT INTO CATEGORYS (CATEGOGY_NAME) VALUES ('음료');
INSERT INTO CATEGORYS (CATEGOGY_NAME) VALUES ('케이크');
INSERT INTO CATEGORYS (CATEGOGY_NAME) VALUES ('기타');

-- 메뉴등록
insert into MENU (MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (1, '아메리카노', 2000, '평범한 아메리카노', 1);
insert into MENU (MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (1, '카푸치노', 2000, '평범한 카푸치노', 1);
insert into MENU (MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (2, '초코케익', 2000, '달콤한 초코케익', 1);
insert into MENU (MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (3, '원두', 2000, '고급 원두', 1);
insert into MENU (MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (1, '아메리카노', 2000, '평범한 아메리카노', 2);

-- 메뉴조회 - 전체	
select * from MENU where CAFE_NO = 1;
-- 메뉴수정	
update MENU set MENU_CATEGORY = 1, MENU_NAME = '따뜻한 아이스 아메리카노', MENU_PRICE = 3000, MENU_CONTENT = '따뜻한 아이스 아메리카노 대령이오~!' where CAFE_NO = 1 and MENU_NO = 1;
-- 메뉴삭제	
delete MENU where CAFE_NO = 2 and MENU_NO = 3;

-- 온라인판매 카페 등록
INSERT INTO ONLINESALECAFE (CAFE_NO) VALUES (1);
-- 온라인판매 카페 조회
SELECT CAFE_NO FROM ONLINESALECAFE;

-- 온라인판매 메뉴 등록
insert into ONLINESALEMENU (MENU_NO, MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (1, 1, '아메리카노', 2000, '평범한 아메리카노', 1);
insert into ONLINESALEMENU (MENU_NO, MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (2, 1, '카푸치노', 2000, '평범한 카푸치노', 1);
insert into ONLINESALEMENU (MENU_NO, MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (3, 2, '초코케익', 2000, '달콤한 초코케익', 1);
insert into ONLINESALEMENU (MENU_NO, MENU_CATEGORY, MENU_NAME, MENU_PRICE, MENU_CONTENT, CAFE_NO) values (4, 3, '원두', 2000, '고급 원두', 1);
-- 온라인판매 메뉴 조회	
select * from ONLINESALEMENU where CAFE_NO = 1;
-- 온라인판매 메뉴 수정
update ONLINESALEMENU set MENU_CATEGORY = 1, MENU_NAME = '따뜻한 아이스 아메리카노', MENU_PRICE = 3000, MENU_CONTENT = '따뜻한 아이스 아메리카노 대령이오~!' where CAFE_NO = 1 and MENU_NO = 1;
-- 온라인판매 메뉴 삭제	
delete ONLINESALEMENU where CAFE_NO = 1 and MENU_NO = 2;

-- 카테고리별로 메뉴보기
SELECT CATEGOGY_NAME, MENU_NAME, MENU_PRICE, MENU_CONTENT FROM ONLINESALEMENU, CATEGORYS WHERE MENU_CATEGORY = CATEGOGY_NO;
SELECT CATEGOGY_NAME, MENU_NAME, MENU_PRICE, MENU_CONTENT FROM ONLINESALEMENU, CATEGORYS WHERE MENU_CATEGORY = CATEGOGY_NO AND MENU_CATEGORY = 1;

-- 장바구니 등록	
insert into CART (MEMBER_ID, CAFE_NO, MENU_NO, MENU_NAME, MENU_COUNT, MENU_PRICE) values ('test2@test.com', 1, 1, '따뜻한 아이스 아메리카노', 5, 3000);
insert into CART (MEMBER_ID, CAFE_NO, MENU_NO, MENU_NAME, MENU_COUNT, MENU_PRICE) values ('test2@test.com', 1, 2, '평범한 카푸치노', 5, 2000);
-- 장바구니 조회
select * from CART where MEMBER_ID = 'test2@test.com';
-- 장바구니 수정
update CART set MENU_COUNT = 10 where MEMBER_ID = 'test2@test.com' and MENU_NO = 1;
-- 장바구니 삭제	
delete CART where MEMBER_ID = 'test2@test.com' and MENU_NO = 2;

-- 주문등록
insert into ORDERS (MEMBER_ID, CAFE_NO, ORDER_PRICE, ORDER_DATE, ORDER_ADDRESS1, ORDER_ADDRESS2, ORDER_STATUS) values ('test2@test.com', 1, 30000, sysdate, '울산광역시 남구 삼산로35번길 19-1', '5층 502호', '주문접수');
-- 주문조회
select * from ORDERS where MEMBER_ID = 'test2@test.com';
-- 주문삭제
-- delete ORDERS where MEMBER_ID = 'test2@test.com' and CAFE_NO = 1 and ORDER_DATE = '00-00-00';
	
-- 상세주문등록
insert into ORDERDETAIL (ORDER_NO, MENU_NO, MENU_NAME, MENU_COUNT, MENU_PRICE) values (1, 1, '따뜻한 아이스 아메리카노', 10, 3000);
-- 상세주문조회
select * from ORDERDETAIL where ORDER_NO = 1 and MENU_NO = 1;
-- 상세주문삭제	- 전체
delete ORDERDETAIL where ORDER_NO = 1;
-- 상세주문삭제 - 낱개
delete ORDERDETAIL where ORDER_NO = 1 and MENU_NO = 1;

-- 구매목록 조회
select * from ORDERS where MEMBER_ID = 'test2@test.com';

	
-- 판매목록 조회 - 전체
select * from ORDERS where CAFE_NO = 1;
-- 판매목록 조회 - 기간
select * from ORDERS where CAFE_NO = 1 and '21-01-01' <= ORDER_DATE and ORDER_DATE <= '21-01-31';

-- 리뷰등록
insert into REVIEWS (REVIEW_TITLE, REVIEW_CONTENT, REVIEW_DATE, MEMBER_ID, CAFE_NO) values ('좋은 카페', '커피 맛있네요', sysdate, 'test2@test.com', 1);
-- 리뷰조회
select * from REVIEWS where MEMBER_ID = 'test2@test.com';
-- 리뷰수정
update REVIEWS set REVIEW_TITLE = '좋은 카페', REVIEW_CONTENT = '분위기 좋고 커피도 맛있네요', REVIEW_DATE = sysdate where MEMBER_ID = 'test2@test.com' and CAFE_NO = 1;
-- 리뷰삭제
    -- delete REVIEWS where MEMBER_ID = 'test2@test.com' and CAFE_NO = 1;
	
-- 댓글등록 가능여부 확인
SELECT COUNT(*) FROM CAFE WHERE CAFE_NO = 1;
-- 댓글등록
insert into COMMENTS (COMMENT_CONTENT, COMMENT_DATE, CAFE_NO, REVIEW_NO) values ('맛있게 드셔주셔서 감사합니다', sysdate, 1, 1);
-- 댓글조회
select * from COMMENTS where CAFE_NO = 1;
-- 댓글수정
update COMMENTS set COMMENT_CONTENT = '맛있게 드셔주셔서 감사합니다. 다음에도 이용해주세요.', COMMENT_DATE = sysdate where CAFE_NO = 1 and COMMENT_NO = 1;
-- 댓글삭제
delete COMMENTS where CAFE_NO = 1 and COMMENT_NO = 1;