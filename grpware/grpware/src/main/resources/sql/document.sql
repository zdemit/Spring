use grpware;

-- html : 수정 목록 : fragment > left 게시판 밑에 전자결재 부분, templates > document 폴더
-- DocDto, DocMapper, DocController

/doc/list = 문서 목록, /doc/write = 기안하기

create table grp_doc(
grp_doc_id int not null auto_increment primary key,
grp_doc_subject varchar(200),
grp_doc_writer varchar(50), -- 세션에서 이름 받기
grp_doc_writer_userid varchar(50), -- 세션에서 userid 받아오기
grp_doc_content text,
grp_doc_upload_name varchar(255),
grp_doc_upload_url varchar(30),
grp_doc_upload_size bigint,
grp_doc_upload_trans varchar(255),
grp_doc_regdate date, -- 작성일
grp_doc_regdate_sign date, -- 결재일
grp_doc_approver varchar(20), -- 결재자 id
grp_doc_accept char(1) default 'N' not null,
grp_doc_state varchar(10) default '결재대기' not null,
grp_doc_rfr text -- 반려 사유
);

insert into grp_doc values(null, '기안 제목', '작성자', '유저 아이디', '내용', '', '', 0, '', now(), null, 'userid', 'N', '결재대기', '');

select E.grp_emp_name from grp_employees E inner join grp_doc D on E.grp_emp_userid = D.grp_doc_approver where D.grp_doc_id = 6

update grp_doc set grp_doc_state = '반려', grp_doc_rfr = '양식 다시 확인하세요' where grp_doc_id = 14