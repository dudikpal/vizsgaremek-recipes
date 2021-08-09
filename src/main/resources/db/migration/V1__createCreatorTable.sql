create table creators (id bigint auto_increment,
                       name varchar(50),
                       ssn varchar(20) unique ,
                       primary key (id));