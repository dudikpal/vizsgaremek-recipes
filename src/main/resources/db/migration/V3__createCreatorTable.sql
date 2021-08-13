create table creators (id bigint auto_increment,
                       name varchar(50),
                       ssn varchar(20) ,
                       primary key (id));


alter table recipes
    add creator_id bigint,
    add constraint foreign key (creator_id) references creators (id);