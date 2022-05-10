/* 사용자_관리자 테이블 */
drop table customer;

create table customer(
c_id varchar(45) primary key,
c_grade Nvarchar(10) not null,
c_password varchar(256) not null,
c_name Nvarchar(20) not null,
c_email varchar(45) not null,
c_call varchar(40) not null,
c_joindate date not null
);

insert into customer values('admin123' , 'Admin', 'admin123', '대구', 'qwer123@naver.com', '01012345678', '2022/05/06');
select * from customer;

/* 주소 테이블*/
drop table address;

create table address(
addr_index int auto_increment primary key,
c_id varchar(45) not null,
postcode int not null, /* 우편 번호 */
address1 Nvarchar(60) not null,
address2 Nvarchar(60) not null
);

select * from address;

/*로그인 할 때, 페이지가 나눠지는 것을 위해 등급 테이블*/
drop table grade;

create table grade(
grade varchar(15) primary key
);

insert into grade values('Normal');
insert into grade values('Admin');

select * from grade;

/* 상품 테이블*/
drop table product;

create table product( 
p_code Nvarchar(20) primary key, /*상품 코드*/
category Nvarchar(50) not null,/*종류 = 구분*/
p_name Nvarchar(45) not null,/*상품 명*/
p_price int not null,/*상품 가격*/
p_detail Nvarchar(200), /*상품 설명*/
p_status varchar(1) not null, /* y:판매할 수 있는 상태, n:판매불가상태*/
p_date date, /*관리자가 상품 올린 날짜*/
image Nvarchar(100) not null/*상품 이미지*/
);

select * from product;

/* 선수정보 테이블 */
drop table player;

create table player(
back_no int not null primary key, /*선수 등번호*/
name varchar(20) not null, /*선수 이름*/
position varchar(10) not null, /*선수 포지션*/
birth nvarchar(10) not null,/*선수 생일*/ 
height int not null, /*선수 키*/ 
weight int not null, /*선수 몸무게*/
team nvarchar(20) not null,/*선수 소속팀*/
image nvarchar(40) not null /*선수 사진*/
);

select * from player;

insert into player value('7', 'Son', 'FW', '960909', '180', '70', 'TOT', 'son.jpg');
insert into player value('13', 'Park', 'MF', '960909', '175', '70', 'MU', 'park.jpg');

commit;

drop table order_table;

/* 상품 주문 테이블 */
create table order_table(
order_num int auto_increment primary key,/*주문 번호*/
c_id varchar(45) not null, 

c_email varchar(45)not null,

order_date timestamp not null,/*주문한 날짜(하루매출조회)*/ /*timestamp를 date로 사용해도 됨*/
order_status varchar(25)not null,/*사용자 모드-상태:order(구매하기-주문한 상태) / 관리자모드 - 상태 : get(주문승인), cancel(주문취소) */
totalmoney int not null/*주문한 총금액*/
);

select * from order_table;


select sum(totalMoney) as monthTotalMoney from order_table where order_status='get';

/*상품 테이블에서 대기, 취소 [건수] 구하기 */
select * from order_table;

select count(*) from order_table where order_status='cancel';

select count(order_status) from order_table where order_status='order';

select count(order_status)
from order_table
where order_status='get';


drop table orderDetail_table;

/* 상품 주문상세보기 테이블  */
create table orderDetail_table(
datail_index int auto_increment primary key,
p_code Nvarchar(20), /*상품 코드*/
order_num int not null,/*주문 번호*/
quantity int not null,/*주문 수량*/
p_name Nvarchar(45) not null,/*상품명*/
p_price int not null /*상품 가격*/
);

select * from orderDetail_table;

drop table board;

create table board(
board_num int primary key, /* 글 번호 */
c_id varchar(45) not null, /* 글 작성자 */
board_subject varchar(50) not null, /* 글 제목 */
board_content varchar(2000) not null, /* 글 내용 */
board_file varchar(50) not null, /* 첨부 파일 */
board_count int default 0, /* 조회수 */
board_date date /* 작성일 */
);

select *  from board;





















