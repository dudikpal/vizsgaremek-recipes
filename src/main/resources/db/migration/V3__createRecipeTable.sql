create table recipes (id bigint auto_increment,
                          name varchar(50) unique,
                          description varchar(2000),
                          primary key (id));