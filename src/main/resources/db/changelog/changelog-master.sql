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

create table events(
    id int primary key generated always as identity,
    name varchar(50) not null,
    user_id int
);

create table invitations(
    id int primary key generated always as identity,
    user_id int,
    event_id int,
    status int
);
ALTER TABLE invitations
ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE invitations
ADD FOREIGN KEY (event_id) REFERENCES events(id);
ALTER TABLE events
ADD FOREIGN KEY (user_id) REFERENCES users(id);


INSERT INTO users (email,"password","token") VALUES
	 ('mail2@mail.com','$2a$10$wACHo4hWYG/3.RQlaZB9S.410ImC91bWT6cVwGUOJWrmdlJVd0SdC',NULL),
	 ('mail3@mail.com','$2a$10$wACHo4hWYG/3.RQlaZB9S.410ImC91bWT6cVwGUOJWrmdlJVd0SdC',NULL);
INSERT INTO authorities (user_id,authority) VALUES
	 (1,'USER');
INSERT INTO events (name,user_id) VALUES
	 ('SUPER',1),
	 ('SUPER',1);
INSERT INTO invitations (user_id,event_id,status) VALUES
     (1,2,1);
