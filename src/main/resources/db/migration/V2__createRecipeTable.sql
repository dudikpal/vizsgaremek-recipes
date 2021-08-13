create table recipes (id bigint auto_increment,
                          name varchar(255) unique,
                          description varchar(2000),
                          primary key (id));

alter table ingredients
    add recipe_id bigint,
    add constraint foreign key (recipe_id) references recipes (id);