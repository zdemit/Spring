use tb_koreait;

create table kor_employees(
kor_emp_id int not null auto_increment,
kor_emp_email varchar(50) not null unique,
kor_emp_passwd varchar(20) not null,
kor_emp_name varchar(10) not null,
kor_emp_gender char(1),
kor_emp_dept char(3) not null,
kor_emp_pos char(3) not null,
kor_emp_level int default 1,
kor_emp_auth enum('Y', 'N'), -- 승인여부
kor_emp_created datetime,
kor_emp_modified datetime,
kor_emp_image_name varchar(255),
kor_emp_image_size bigint,
kor_emp_trans_name varchar(255),
primary key(kor_emp_id)
);

--admin 계정은 회원가입이 아니라 미리 생성해야 함
INSERT INTO kor_employees VALUES(NULL, 'mail@mail.com', '1234', '관리자', 'W', '100', '101', 7, 'Y',now(), now(), '', 0, '');

--email check
select count(kor_emp_email) as result from kor_employees where kor_emp_email = 'mail@mail.com';
select count(*) from kor_employees where kor_emp_email = 'mail@mail.com';


