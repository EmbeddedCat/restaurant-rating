CREATE TABLE rest_user 
(
    username  varchar(50),
    password  varchar(120),
    phone     varchar(12),
    address   varchar(80),
    primary key(username)
);

CREATE TABLE restaurant
(
    restaurant_owner       varchar(50) not null,
    restaurant_name        varchar(50) unique not null,
    restaurant_mo_stars    int,
    restaurant_sum_stars   int,
    restaurant_count_stars int,
    restaurant_address     varchar(80) not null,
    restaurant_phone       varchar(12) unique not null,
    restaurant_pic         varchar(500),
    primary key(restaurant_address),
    foreign key(restaurant_owner) references rest_user 
);

CREATE TABLE stared
(
    username           varchar(50) not null,
    restaurant_address varchar(80) not null,
    primary key(username, restaurant),
    foreign key(username) references rest_user,
    foreign key(restaurant) references restaurant
);



