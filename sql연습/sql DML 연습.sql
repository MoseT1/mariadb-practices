select version(), current_date, now() from dual;

-- table 생성: ddl
create table pet(
	name varchar(100), 
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 
desc pet;


-- table 삭제 : DDL
drop table pet;

show tables;

-- insert : DML (C)
insert into pet 
values ('nero', '지민', 'dog', 'm', '2009-12-11', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet
set name='네로'
where name ='nero';

