-- 明星表
create table star(
		id int primary key auto_increment,
		group_id varchar(128),
		star_info varchar(128),
		face_taken varchar(128),
		img_url varchar(128),
		star_id varchar(128)
);

-- 图片表

create table img(
		img_id int PRIMARY key auto_increment,
		img_url varchar(128),
		userid int references user(userid)
);

-- 用户表
create table user(
		userid int PRIMARY key auto_increment,
		username varchar(20) unique,
		password varchar(20),
		email varchar(128),
		img_url varchar(128)

);

insert into star values(2,'asrr','xxxxxx','dsgdqwjdqwjkdqk','www.baidu.com');
insert into star values(1,'asrr','王二','dsgdqwjdqwjkdqk','www.baidu.com');

select * from star where starid = 1;