--liquibase formatted sql
--changeset author:Mateusz-Klimek
create table users (
    id int primary key generated always as identity,
    email varchar(255),
    password varchar(255),
    token varchar(255)
);
create table authorities (
    id int primary key generated always as identity,
    user_id int,
    authority varchar(50) not null
);

ALTER TABLE authorities
ADD FOREIGN KEY (user_id) REFERENCES users(id);