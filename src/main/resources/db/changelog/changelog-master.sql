--liquibase formatted sql
--changeset author:Mateusz-Klimek
create table users (
    id int primary key,
    email varchar(255),
    password varchar(255),
    token varchar(255)
);