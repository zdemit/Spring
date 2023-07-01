use tb_koreait;

-- 부서(대분류) : dept
create table kor_dept(
kor_dept_code char(3) not null primary key,
kor_dept_name varchar(20) not null
);

insert into kor_dept values('100','인사과');
insert into kor_dept values('200','비서실');
insert into kor_dept values('300','영업부');
insert into kor_dept values('900','임원실');

-- 직책(소분류) : pos
create table kor_pos(
kor_pos_code char(3) not null primary key,
kor_pos_name varchar(20) not null,
kor_dept_code char(3),
foreign key(kor_dept_code) references kor_dept(kor_dept_code) on update cascade on delete restrict
);

insert into kor_pos values ('101', '인사부장', '100');
insert into kor_pos values ('102', '인사과장', '100');
insert into kor_pos values ('103', '인사사원', '100');

insert into kor_pos values ('201', '비서실장', '200');
insert into kor_pos values ('202', '비서담당관', '200');
insert into kor_pos values ('203', '비서사원', '200');

insert into kor_pos values ('301', '영업부장', '300');
insert into kor_pos values ('302', '영업과장', '300');
insert into kor_pos values ('303', '영업사원', '300');

insert into kor_pos values ('901', '회장', '900');
insert into kor_pos values ('902', '대표이사', '900');
insert into kor_pos values ('903', '전무', '900');
insert into kor_pos values ('904', '본부장', '900');

select D.kor_dept_name, P.kor_pos_name from kor_dept D inner join kor_pos P on D.kor_dept_code = P.kor_dept_code;

select * from kor_dept D inner join kor_pos p on D.kor_dept_code = P.kor_dept_code;

SELECT * FROM 부모 INNER JOIN 자식 ON 부모 = 자식;