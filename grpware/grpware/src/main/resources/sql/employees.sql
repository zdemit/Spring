use grpware;

create table grp_employees(
grp_emp_id int not null auto_increment,
grp_emp_userid varchar(20) not null unique,
grp_emp_passwd varchar(20) not null,
grp_emp_name varchar(10) not null,
grp_emp_email varchar(50) not null,
grp_emp_pnum char(13) not null, -- 휴대폰 번호
grp_emp_tel char(4), -- 내선번호
grp_emp_auth char(1) default 'N' not null, -- 승인여부
grp_emp_dept char(3) not null,
grp_emp_pos char(4) not null,
grp_emp_birth char(8) not null,
grp_emp_startdate datetime, -- 입사일
grp_emp_lastdate datetime, -- 퇴사일
grp_emp_modified datetime, -- 정보수정일
grp_emp_image_name varchar(255),
grp_emp_image_size bigint,
grp_emp_trans_name varchar(255),
primary key(grp_emp_id)
);

INSERT INTO grp_employees VALUES(NULL, 'admin', '1234', '관리자', 'mail@mail.com', '010-1234-1234', '', 'Y', '100', '1001', '19960509', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid1', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid2', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid3', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid4', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid5', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid6', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid7', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );
INSERT INTO grp_employees VALUES(NULL, 'userid8', '1234', '사용자1', 'email@email.com', '010-1234-1234', '', '', '100', '1002', '20000101', now(), now(), now(), '',0 ,'' );

select * from grp_dept D inner join grp_employees E on D.grp_dept_code = E.grp_emp_dept inner join grp_pos P on E.grp_emp_pos = P.grp_pos_code where E.grp_emp_userid = 'userid';