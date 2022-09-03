create database employees;
create table employee (
      id serial primary key,
      firstname varchar(50) not null,
      lastname varchar(50)not null,
      occupation varchar(50)not null,
      username varchar(50)not null unique,
      password varchar(60)not null
      );
select * from employee where username= 'sara@gmail.com' ;

create table ticket(
    id serial primary key,
    amount decimal not null,
    description varchar(50) not null,
    status varchar(50),
    employeeid int references employee(id));
select* from ticket ;
insert into ticket (id,amount,description,status,employeeid)values(default,300,'test3','pending',1);
insert into ticket (id,amount,description,status,employeeid)values(default,500,'test4','pending',2);

insert into ticket (id,amount,description,status,employeeid)values(default,250,'test5','pending',5);
update ticket set status= 'approved'  where id = 2;
alter table ticket drop COLUMN amount ;
drop table ticket ;
select * from ticket where status = 'pending';
select * from ticket order by employeeid asc;

