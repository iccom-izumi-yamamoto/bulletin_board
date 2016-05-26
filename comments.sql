create table comments (
id int not null primary key auto_increment,
body varchar(500) not null,
insert_date timestamp not null,
update_date timestamp not null,
user_id int not null,
contribution_id int not null
);