CREATE TABLE rest_user 
(
    user_id   SERIAL, 
    username  varchar(50) unique not null,
    password  varchar(120) not null,
    salt      varchar(120) not null,
    address   varchar(80) not null,
    email     varchar(255) unique not null,
    primary key(user_id)
);

CREATE TABLE restaurant
(
    restaurant_id          SERIAL,
    restaurant_owner       int not null,
    restaurant_name        varchar(50) unique not null,
    restaurant_address     varchar(80) unique not null,
    restaurant_phone       varchar(12) unique not null,
    restaurant_pic         varchar(500),
    restaurant_filters     varchar(500),
    primary key(restaurant_id),
    foreign key(restaurant_owner) references rest_user 
);

CREATE TABLE stared
(
    user_id       int,
    restaurant_id int,
    primary key(user_id, restaurant_id),
    foreign key(user_id) references rest_user,
    foreign key(restaurant_id) references restaurant
);

CREATE TABLE app_admin 
(
    user_id int,
    primary key (user_id),
    foreign key (user_id) references rest_user
);

