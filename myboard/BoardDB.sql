## 검색을 편의를 위해 역정규화 했지만 수정할 수 있는 컬럼이기에 무결성에 위배됨
# User (user_email[PK], user_password, user_nickname, user_phone_number, user_address, user_profile)
Create table User (
	user_email varchar(50) primary key,
    user_password varchar(20) not null,
    user_nickname varchar(30) not null,
    user_phone_number varchar(15) not null,
    user_address text not null,
    user_profile text 
);
# Board (board_number[PK], board_title, board_content, board_image, board_video, board_file,
#		 board_writer[FK-User(user_email)], board_writer_profile, board_writer_nickname, 
#		 board_write_date, board_click_count, board_like_count, board_comment_count)
create table Board (
	board_number int primary key auto_increment,
    board_title varchar(200) not null,
    board_content text not null,
    board_image text,
    board_video text,
    board_file text,
    board_writer_email varchar(50) not null,
    board_writer_profile text,
    board_writer_nickname varchar(30) not null,
    board_write_date date not null,
    board_click_count int default 0,
    board_like_count int default 0,
    board_comment_count int default 0
);
# PopularSearch (popular_term[PK], popular_search_count)
create table PopularSearch(
	popular_term varchar(200) primary key,
    popular_search_count int default 1
);
# Liky(board_number[FK-Board(board_number)], user_email[FK-User(user_email)], 
#	   like_user_profile, like_user_nickname)
create table Liky(
	like_id int auto_increment primary key,
	board_number int not null,
    user_email varchar(50) not null,
    like_user_profile text,
    like_user_nickname varchar(30) not null
);
# Comment(board_number[FK-Board(board_number)], user_email[FK-User(user_email)], 
#		  comment_user_profile, comment_user_nickname, comment_content, comment_write_date)
create table Comment(
	comment_id int auto_increment primary key,
	board_number int not null,
    user_email varchar(50) not null,
    comment_user_profile text,
    comment_user_nickname varchar(30) not null,
    comment_write_date date not null,
    comment_content text not null
);
drop table Liky;
drop table Comment;

# User - Board 관계
# 1. User기 Board를 작성한다.  		1 : n
# 2. User가 Board에 좋아요를 행한다. n : m
# 3. User가 Board에 댓글을 단다.	n : m

