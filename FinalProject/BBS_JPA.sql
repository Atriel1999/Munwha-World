DROP SCHEMA bbs;
CREATE SCHEMA bbs;
USE bbs;


------------------------------------------------
--------------- MEMBER 관련 테이블 ----------------
------------------------------------------------

CREATE TABLE Member (
    mno 	 INT  PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL, 
    role 	 VARCHAR(10) DEFAULT 'ROLE_USER',
    name 	 VARCHAR(15) NOT NULL,
    phone 	 VARCHAR(13),
    email 	 VARCHAR(100),
    address  VARCHAR(100),
    hobby 	 VARCHAR(100),
    status 	 VARCHAR(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')),
    enroll_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    modify_date DATETIME DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO MEMBER (
    mno, member_id, password,  role, name, 
    phone, email, address, hobby, status, 
    enroll_date, modify_date
) VALUES(
    0, 'admin', '1234', 'ROLE_ADMIN', '관리자', 
    '010-1234-4341', 'admin@test.com', '서울시 강남구 역삼동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);


INSERT INTO MEMBER (
    mno, member_id, password,  role, name, 
    phone, email, address, hobby, status, 
    enroll_date, modify_date
) VALUES(
    0, 'test5', '1234', DEFAULT, '예비개발자', 
    '010-4321-1234', 'test@test.com', '서울시 강남구 역삼동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);


INSERT INTO MEMBER (
    mno, member_id, PASSWORD,  ROLE, NAME, 
    PHONE, EMAIL, ADDRESS, HOBBY, STATUS, 
    ENROLL_DATE, MODIFY_DATE
) VALUES(
    0, 'test6', '1234', DEFAULT, '지나가는유저', 
    '010-1111-2222', 'test2@test.com', '서울시 강남구 삼성동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);

COMMIT;

SELECT * FROM MEMBER;


---------------------------------------------------
--------------- Board 카테고리 테이블 ------------------
---------------------------------------------------

CREATE TABLE BOARD_CATEGORY (	
    type VARCHAR(20),
    name VARCHAR(100),
    level INT,
    orderno INT,
    CONSTRAINT PK_BOARD_CATEGORY PRIMARY KEY(type)
);

-- 일반적으로 CODE는 약어를 사용하나 가독성을 위해 길게 표현함 NOTICE -> N,  CMM1 -> C1 
-- NOTICE, PLAIN, CMM1, CMM2, CMM3, QUESTION, TIP, BUY, SELL
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('NOTICE', '공지', 0, 1);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('PLAIN', '일반글', 3, 2);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('QUESTION', '질문', 3, 3);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('TIP', 'TIP', 3, 4);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('BUY', '삽니다', 3, 5);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('SELL', '팝니다', 3, 6);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM1', '커뮤니티A', 3, 7);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM2', '커뮤니티B', 3, 8);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM3', '커뮤니티C', 3, 9);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('NBOARD', '공지사항', 3, 10);


COMMIT;
SELECT * FROM BOARD_CATEGORY ORDER BY ORDERNO;
-------------------------------------------------
--------------- Board 관련 테이블 ------------------
-------------------------------------------------


CREATE TABLE BOARD (	
    bno INT AUTO_INCREMENT,
    member_mno INT, 
    board_category_type VARCHAR(20),
	title VARCHAR(1000), 
	content VARCHAR(2000), 
	readcount INT DEFAULT 0, 
    status VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    create_date DATETIME  DEFAULT CURRENT_TIMESTAMP, 
    modify_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_BOARD_NO PRIMARY KEY(bno),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY(member_mno) REFERENCES MEMBER(mno) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_BOARD_CATEGORY FOREIGN KEY(board_category_type) REFERENCES BOARD_CATEGORY(type) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NOTICE','[공지] 클린한 게시판 환경을 만들어주세요.','깨끗한 게시판 환경 유지에 협조 바랍니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','안녕하세요? 처음 가입한 개발자입니다.','잘 부탁드립니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'QUESTION','[질문] 질문 있습니다.','자바 어렵나요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','오늘 식사메뉴 추천드립니다.','돈까스 드세요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','내일 식사메뉴 추천드립니다.','냉면 어떠신가요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','모레 식사메뉴 추천드립니다.','스파게티 좋네요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','다음주 식사메뉴 추천드립니다.','아무거나 드세요');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.1','공지 내용입니다.1');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.2','공지 내용입니다.2');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.3','공지 내용입니다.3');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.4','공지 내용입니다.4');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.5','공지 내용입니다.5');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.6','공지 내용입니다.6');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.7','공지 내용입니다.7');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.8','공지 내용입니다.8');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.9','공지 내용입니다.9');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.10','공지 내용입니다.10');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.11','공지 내용입니다.11');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.12','공지 내용입니다.12');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.13','공지 내용입니다.13');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.14','공지 내용입니다.14');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.15','공지 내용입니다.15');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 1','커뮤니티 A활동 글입니다. 1');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 2','커뮤니티 A활동 글입니다. 2');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 3','커뮤니티 A활동 글입니다. 3');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 4','커뮤니티 A활동 글입니다. 4');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM1','커뮤니티 A글입니다. 5','커뮤니티 A활동 글입니다. 5');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 1','커뮤니티 B활동 글입니다. 1');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 2','커뮤니티 B활동 글입니다. 2');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 3','커뮤니티 B활동 글입니다. 3');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 4','커뮤니티 B활동 글입니다. 4');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 5','커뮤니티 B활동 글입니다. 5');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 1','커뮤니티 C활동 글입니다. 1');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 2','커뮤니티 C활동 글입니다. 2');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 3','커뮤니티 C활동 글입니다. 3');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 4','커뮤니티 C활동 글입니다. 4');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 5','커뮤니티 C활동 글입니다. 5');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','java는 쉽습니다. 객체만 아세요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','c언어는 어렵습니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','프로그래밍은 생각보다 쉽습니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'TIP','[팁] 팁글입니다.','개발일은 어렵습니다. 코딩만 안합니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'TIP','[팁] 팁글입니다.','문서작업은 귀찮습니다. 이게 개발일의 실체입니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');


COMMIT;
SELECT * FROM BOARD;


------------------------------------------------------------------
--------------------------- 첨부파일 관련 ----------------------------
------------------------------------------------------------------

CREATE TABLE BOARD_ATTACH_FILE (
	fno INT AUTO_INCREMENT, 
    board_bno INT,
    original_filename VARCHAR(200), 
	renamed_filename VARCHAR(200), 
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP, 
    CONSTRAINT PK_BOARD_ATTACH_FILE PRIMARY KEY(fno),
    CONSTRAINT FK_BOARD_BO FOREIGN KEY(board_bno) REFERENCES BOARD(bno) ON DELETE CASCADE
);

INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 1, '첨부파일2','TEST_FILE2', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);

COMMIT;
SELECT * FROM BOARD_ATTACH_FILE;
SELECT * FROM BOARD;

------------------------------------------------------------------
------------------------- REPLY 관련 테이블 -------------------------
------------------------------------------------------------------

CREATE TABLE BOARD_REPLY(
  rno INT PRIMARY KEY AUTO_INCREMENT,
  board_bno INT,
  member_mno INT,
  content VARCHAR(1000),
  status VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  modify_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (board_bno) REFERENCES BOARD(bno) ON DELETE CASCADE,
  FOREIGN KEY (member_mno) REFERENCES MEMBER(mno) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 2, '안녕하세요.');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 1');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 2');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 3');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 4');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 2, 2, '안녕하세요.');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 2, 3, '반갑습니다.');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 3, 3, '안녕하세요.');
INSERT INTO BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 3, 2, '반갑습니다.');


COMMIT;

SELECT * FROM BOARD_REPLY;

----------------------------------- DDL 끝-------------------------------------------
