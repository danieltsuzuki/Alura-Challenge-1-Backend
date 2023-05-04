create table videos(
    id          bigint auto_increment not null primary key,
    title       varchar(100),
    description varchar(255),
    url         varchar(255)
);