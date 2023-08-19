use grpware;

create table grp_memo(
grp_num int auto_increment,
grp_content varchar(30) not null,
primary key(grp_num)
);

ALTER TABLE grp_memo AUTO_INCREMENT = 1;
SET @count=0;
update grp_memo set grp_num=@count:=@count+1;

ALTER TABLE grp_memo ALTER COLUMN grp_num RESTART WITH 1;

insert into grp_memo values(null, '오늘 메모 끝!!');
insert into grp_memo values(null, '오늘 메모 끝인가!!');
insert into grp_memo values(null, '오늘 메모 끝이겠지???!');