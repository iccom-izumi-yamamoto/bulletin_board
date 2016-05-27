create table contributions (
id int not null primary key auto_increment,
title varchar(50) not null,
body varchar(1000) not null,
category varchar(10) not null,
user_id int not null,
insert_date timestamp not null,
update_date timestamp not null
);