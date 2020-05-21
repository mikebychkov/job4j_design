-- DB

CREATE DATABASE spammer;

-- TABLES

CREATE TABLE users (
	id serial primary key,
	name varchar(200),
	email varchar(200)
);