CREATE TABLE TOGETHER_BOARD_CATEGORY (	
    type VARCHAR(20),
    name VARCHAR(100),
    level INT,
    orderno INT,
    CONSTRAINT PK_BOARD_CATEGORY PRIMARY KEY(type)
);

CREATE TABLE TOGETHER_BOARD (	
    bno INT AUTO_INCREMENT,
    boardcno INT,
    member_mno INT, 
    board_category_type VARCHAR(20),
	title VARCHAR(1000), 
	content VARCHAR(2000), 
    winx VARCHAR(500) null, 
    winy VARCHAR(500) null, 
    cost INT DEFAULT 0,
    memberlist text null, 
    membercount INT DEFAULT 0, 
	read_count INT DEFAULT 0, 
    bestcount INT DEFAULT 0, 
    status VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    create_date DATETIME  DEFAULT CURRENT_TIMESTAMP, 
    modify_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_BOARD_NO PRIMARY KEY(bno),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY(member_mno) REFERENCES MEMBER(mno) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_BOARD_TOGETHER FOREIGN KEY(boardcno) REFERENCES together(cno) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_BOARD_CATEGORY FOREIGN KEY(board_category_type) REFERENCES TOGETHER_BOARD_CATEGORY(type) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TOGETHER_BOARD_ATTACH_FILE (
	fno INT AUTO_INCREMENT, 
    board_bno INT,
    original_filename VARCHAR(200), 
	renamed_filename VARCHAR(200), 
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP, 
    CONSTRAINT PK_BOARD_ATTACH_FILE PRIMARY KEY(fno),
    CONSTRAINT FK_BOARD_BO FOREIGN KEY(board_bno) REFERENCES TOGETHER_BOARD(bno) ON DELETE CASCADE
);

CREATE TABLE TOGETHER_BOARD_REPLY(
  rno INT PRIMARY KEY AUTO_INCREMENT,
  board_bno INT,
  member_mno INT,
  content VARCHAR(1000),
  status VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  modify_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (board_bno) REFERENCES TOGETHER_BOARD(bno) ON DELETE CASCADE,
  FOREIGN KEY (member_mno) REFERENCES MEMBER(mno) ON UPDATE CASCADE ON DELETE CASCADE
);



INSERT INTO MEMBER (
    mno, member_id, password,  role, name, 
    phone, email, address, address_sido, status, codeno,
    enroll_date, modify_date
) VALUES(
    0, 'admin', '1234', 'ROLE_ADMIN', '관리자', 
    '010-1234-4341', 'admin@test.com', '서울시 강남구 역삼동','sido',  DEFAULT, '200',
    DEFAULT, DEFAULT
);


INSERT INTO MEMBER (
    mno, member_id, password,  role, name, 
    phone, email, address, address_sido, status, codeno,
    enroll_date, modify_date
) VALUES(
    0, 'test5', '1234', DEFAULT, '예비개발자', 
    '010-4321-1234', 'test@test.com', '서울시 강남구 역삼동','sido',  DEFAULT, '200',
    DEFAULT, DEFAULT
);


INSERT INTO MEMBER (
    mno, member_id, password,  role, name, 
    phone, email, address, address_sido, status, codeno,
    enroll_date, modify_date
) VALUES(
    0, 'test6', '1234', DEFAULT, '지나가는유저', 
    '010-1111-2222', 'test2@test.com', '서울시 강남구 삼성동','sido',  DEFAULT, '200',
    DEFAULT, DEFAULT
);

INSERT INTO TOGETHER_BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('NOTICE', '공지', 0, 1);
INSERT INTO TOGETHER_BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('PLAIN', '일반', 3, 2);
INSERT INTO TOGETHER_BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('SCHE', '일정', 3, 3);
INSERT INTO TOGETHER_BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('REVIEW', '리뷰', 3, 4);


INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,1,'NOTICE','[공지] 클린한 게시판 환경을 만들어주세요.','깨끗한 게시판 환경 유지에 협조 바랍니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','커뮤니티 A글입니다. 3','커뮤니티 A활동 글입니다. 3');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','커뮤니티 A글입니다. 4','커뮤니티 A활동 글입니다. 4');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 A글입니다. 5','커뮤니티 A활동 글입니다. 5');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 B글입니다. 1','커뮤니티 B활동 글입니다. 1');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 B글입니다. 2','커뮤니티 B활동 글입니다. 2');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 B글입니다. 3','커뮤니티 B활동 글입니다. 3');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 B글입니다. 4','커뮤니티 B활동 글입니다. 4');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'PLAIN','커뮤니티 B글입니다. 5','커뮤니티 B활동 글입니다. 5');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','커뮤니티 C글입니다. 1','커뮤니티 C활동 글입니다. 1');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','커뮤니티 C글입니다. 2','커뮤니티 C활동 글입니다. 2');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','커뮤니티 C글입니다. 3','커뮤니티 C활동 글입니다. 3');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','커뮤니티 C글입니다. 4','커뮤니티 C활동 글입니다. 4');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','커뮤니티 C글입니다. 5','커뮤니티 C활동 글입니다. 5');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','커뮤니티 C글입니다. 3','커뮤니티 C활동 글입니다. 3');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','커뮤니티 C글입니다. 4','커뮤니티 C활동 글입니다. 4');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','커뮤니티 C글입니다. 5','커뮤니티 C활동 글입니다. 5');

INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','[후기] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'SCHE','[후기] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','[후기] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[후기] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[후기] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[후기] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[후기] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','[후기] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[후기] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');


INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','안녕하세요 신입 입니다.잘부탁드립니다.','잘 부탁드립니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','[질문] 질문 있습니다.','답변해주시면 감사하겠습니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','다음주 일정 괜찮으신분 참여해주세요.','좋은 일정있습니다..');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','내일 어디로 가면될까요?','냉면 어떠신가요?');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','모레 식사메뉴 추천드립니다.','스파게티 좋네요.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'SCHE','서울 탐방 후기입니다.','어떻게하면 좋을까요?');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','오늘 재미있는 일이 있었습니다.','아무거나 드세요');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','문화재 활동 즐거웠습니다. ','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','스케줄 잡아보려고 하는데 소재가 있을까요','커뮤니티 A활동 글입니다. 1');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'PLAIN','어제 알아본게 있는데요','커뮤니티 A활동 글입니다. 2');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,3,'REVIEW','문화재 활동 후기입니다.','즐거웠습니다. 다시놀아요~');
INSERT INTO TOGETHER_BOARD (bno, member_mno, board_category_type, TITLE, CONTENT) VALUES(0,2,'REVIEW','최근 일정 후기에요~.','즐거웠습니다. 다시놀아요~');

INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 1, '첨부파일2','TEST_FILE2', DEFAULT);
INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
-- INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
-- INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
-- INSERT INTO TOGETHER_BOARD_ATTACH_FILE (fNO, board_bno, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);

INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 2, '안녕하세요.');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 1');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 2');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 3');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 1, 3, '댓글 테스트 입니다. 4');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 2, 2, '안녕하세요.');
INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 2, 3, '반갑습니다.');
-- INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 3, 3, '안녕하세요.');
-- INSERT INTO TOGETHER_BOARD_REPLY (rno, board_bno, member_mno, content) VALUES(0, 3, 2, '반갑습니다.');

commit;