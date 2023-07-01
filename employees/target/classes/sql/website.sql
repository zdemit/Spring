use tb_koreait;

create table website(
kor_web_adv varchar(500),
kor_web_logo varchar(255), -- 로고 이미지 업로드
kor_web_title varchar(100), -- 사이트 title
kor_web_menus varchar(100), -- 사이트 메뉴 => Intro;Course;Project;Counsel
kor_web_url varchar(255), -- 메뉴 이동 주소
kor_web_hero_name varchar(255), -- 메인 이미지 원본
kor_web_hero_size bigint, -- 메인 이미지 용량
kor_web_hero_trans varchar(255), -- 메인 이미지 변환 이름
kor_web_copyright varchar(255) -- 사이트 카피라이터
);


INSERT INTO website VALUES('Korea id Adv.', '', '코리아아이티 아카데미에 오신 것을 환영합니다.', 'Home;Docs;Download;GitHub;Contact', '', '', 0, '', '부산 광역시 부산진구 서면');

UPDATE website SET kor_web_copyright = '부산광역시 부산진구 서면 123-456 Tel) 051-123-1234';

UPDATE website SET kor_web_menus = 'home1; home2; home3';