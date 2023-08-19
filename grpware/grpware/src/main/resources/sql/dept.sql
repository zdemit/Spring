use grpware;

create table grp_dept(
grp_dept_code char(3) not null,
grp_dept_name varchar(10) not null,
primary key(grp_dept_code)
);

INSERT INTO grp_dept VALUES('100', '인사팀');
INSERT INTO grp_dept VALUES('200', '개발팀');
INSERT INTO grp_dept VALUES('300', '마케팅팀');

create table grp_pos(
grp_dept_code char(3),
grp_pos_name varchar(20) not null,
grp_pos_code char(4) not null,
primary key(grp_pos_code),
foreign key(grp_dept_code) references grp_dept(grp_dept_code) on update cascade on delete restrict
);

INSERT INTO grp_pos VALUES('100', '팀장', '1001');
INSERT INTO grp_pos VALUES('100', '대리', '1002');
INSERT INTO grp_pos VALUES('100', '사원', '1003');

INSERT INTO grp_pos VALUES('200', '팀장', '2001');
INSERT INTO grp_pos VALUES('200', '대리', '2002');
INSERT INTO grp_pos VALUES('200', '사원', '2003');

INSERT INTO grp_pos VALUES('300', '팀장', '3001');
INSERT INTO grp_pos VALUES('300', '대리', '3002');
INSERT INTO grp_pos VALUES('300', '사원', '3003');



select * from grp_dept D inner join grp_pos P on D.grp_dept_code = P.grp_dept_code;