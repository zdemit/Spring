use grpware;

create table grp_board(
grp_board_id int not null auto_increment, -- 보기, 수정, 삭제
grp_board_notice char(1) default 'N', -- 공지, 일반
grp_board_subject varchar(300), -- 제목
grp_board_writer varchar(20), -- 작성자
grp_board_content text, -- 내용
grp_board_upload_name varchar(255), -- 파일 이름
grp_board_upload_url varchar(30), -- 파일 주소
grp_board_upload_size bigint, -- 파일용량
grp_board_upload_trans varchar(255), -- 파일 바뀐 이름
grp_board_visit int, -- 조회수
grp_board_regdate date,
primary key(grp_board_id)
);

insert into grp_board values(1, 'Y', "제목입니당", "작성자입니당", "내용입니다아앙", "", "", 0, "", 1, now());
insert into grp_board values(2, 'Y', "제목입니당", "작성자입니당", "내용입니다아앙", "", "", 0, "", 1, now());


-- 일치하는 검색 =
--select * from 테이블이름 where 검색컬럼 = 검색어

select * from grp_board where grp_board_subject = '검색어'; -- 제목검색
select * from grp_board where grp_board_writer = '홍길동';


-- 유사어 검색 like : 검색어가 포함된 내용을 검색 - 속도가 느림
--select * from 테이블이름 where 검색컬럼 like %검색어%

select * from grp_board where grp_board_subject like '%제%';
select * from grp_board where grp_board_content like '%가%';







