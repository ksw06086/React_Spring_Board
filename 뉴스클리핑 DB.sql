SELECT * FROM movieboard.report;

CREATE TABLE mail_news_data
(
  idx   			INT  NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  type		        varchar(10) NOT NULL COMMENT '뉴스분류',
  crawling_date     timestamp   NOT NULL COMMENT '검출일',
  cp_id 			VARCHAR(20) NOT NULL COMMENT 'CP아이디',
  keyword 			VARCHAR(191) NOT NULL COMMENT '키워드',
  title  			VARCHAR(191) NOT NULL COMMENT '제목',
  contents     		TEXT         		  COMMENT '뉴스내용',
  url   			varchar(191) NOT NULL COMMENT '뉴스url',
  write_cp   		VARCHAR(50) 		  COMMENT '언론사명',
  writer    		VARCHAR(50) 		  COMMENT '기자명',
  sentiment			CHAR(1) 			  COMMENT '감정분류(0,1,2)',
  state   			CHAR(1) default '1'   COMMENT '상태값(0,1) 기본값 1',
  created_at   		date 	NOT NULL 	  COMMENT '등록일'
  PRIMARY KEY (idx)
) COMMENT '선택기사 테이블(기사 선택 리스트)';

CREATE TABLE mail_log
(
  idx   	 INT	     	  NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  mail_id  	 VARCHAR(50)  NOT NULL COMMENT '메일고유번호',
  sender	 varchar(20)   NOT NULL COMMENT '보내는사람이름',
  send_mail	 varchar(50)   NOT NULL COMMENT '보내는사람이메일',
  receive_mail 	varchar(50)  NOT NULL COMMENT '받는사람이메일',
  mail_group varchar(50)   		   COMMENT '메일그룹',
  title      VARCHAR(100)  NOT NULL COMMENT '메일제목',
  body   	 text 		  		   COMMENT '메일내용(HTML 코드 저장)',
  state  	 INT 	  NOT NULL COMMENT '메일상태값(정상코드,에러코드)',
  receive_date   timestamp 	  NOT NULL COMMENT '메일수신일',
  created_at   	 timestamp    NOT NULL COMMENT '메일발송일',
  PRIMARY KEY (idx)
) COMMENT '메일로그 테이블(메일 로그 리스트)';

drop table mail_log;

CREATE TABLE mail_list
(
  idx   	 INT	     	  NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  email		 varchar(50)	  NOT NULL COMMENT '이메일',
  name		 varchar(50)	  NOT NULL COMMENT '이름',
  organization varchar(50)	  NOT NULL COMMENT '소속',
  mail_group varchar(50)	  NOT NULL COMMENT '메일 그룹',
  state		 char(1)		  NOT NULL COMMENT '상태값(0,1)',
  created_at timestamp		  NOT NULL COMMENT '등록일'
  PRIMARY KEY (idx)
) COMMENT '메일계정 테이블';