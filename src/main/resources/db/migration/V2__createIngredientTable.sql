create table ingredients (id bigint auto_increment,
                          name varchar(50) unique,
                          type varchar(20),
                          quantity decimal,
                          primary key (id));