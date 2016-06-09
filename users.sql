create table users (
id  int not null primary key auto_increment,
login_id varchar(20) not null,
password varchar(255) not null,
name varchar(10) not null,
branch_id int not null,
department_id int not null,
suspention int default 0,
insert_date timestamp,
update_date timestamp
);

 