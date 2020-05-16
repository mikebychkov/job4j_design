-- DATABASE

CREATE DATABASE mydb;


-- DROP IF EXISTS

DROP TABLE ITEM_CATEGORY;
DROP TABLE CATEGORY;
DROP TABLE ATTACHS;
DROP TABLE COMMENTS;
DROP TABLE ITEMS;
DROP TABLE STATE;
DROP TABLE ROLE_RULES;
DROP TABLE RULES;
DROP TABLE USER_ROLES;
DROP TABLE ROLES;
DROP TABLE USERS;


-- USERS & ROLES

CREATE TABLE USERS (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE ROLES (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE USER_ROLES (
	id serial primary key,
	user_id int references USERS(id),
	role_id int references ROLES(id)
);

CREATE TABLE RULES (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE ROLE_RULES (
	id serial primary key,
	role_id int references ROLES(id),
	rule_id int references RULES(id)
);


-- ITEMS

CREATE TABLE STATE (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE ITEMS (
	id serial primary key,
	name varchar(200),
	user_id int references USERS(id),
	state_id int references STATE(id)
);

CREATE TABLE COMMENTS (
	id serial primary key,
	item_id int references ITEMS(id),
	comment_text text
);

CREATE TABLE ATTACHS (
	id serial primary key,
	name varchar(200),
	item_id int references ITEMS(id)
);

CREATE TABLE CATEGORY (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE ITEM_CATEGORY (
	id serial primary key,
	item_id int references ITEMS(id),
	cat_id int references CATEGORY(id)
);


-- POPULATING TABLES

INSERT INTO USERS(name) VALUES('Alex'); --1
INSERT INTO USERS(name) VALUES('Stepan'); --2

INSERT INTO ROLES(name) VALUES('Admin'); --1
INSERT INTO ROLES(name) VALUES('User'); --2

INSERT INTO USER_ROLES(user_id, role_id) VALUES(1, 1);
INSERT INTO USER_ROLES(user_id, role_id) VALUES(2, 2);

INSERT INTO RULES(name) VALUES('write tables'); --1
INSERT INTO RULES(name) VALUES('read tables'); --2

INSERT INTO ROLE_RULES(role_id, rule_id) VALUES(1, 1);
INSERT INTO ROLE_RULES(role_id, rule_id) VALUES(1, 2);
INSERT INTO ROLE_RULES(role_id, rule_id) VALUES(2, 2);

INSERT INTO STATE(name) VALUES('open');
INSERT INTO STATE(name) VALUES('closed');

INSERT INTO ITEMS(name, user_id, state_id)
WITH CTE AS (SELECT name FROM (VALUES ('Item #1'), ('Item #3'), ('Item #2')) AS T1(name))
SELECT C.name AS name, U.id AS user_id, S.id AS state_id 
FROM USERS AS U 
	FULL JOIN STATE AS S
	ON true
	FULL JOIN CTE AS C
	ON true
;

INSERT INTO COMMENTS(item_id, comment_text) VALUES(1, 'thats interesting');
INSERT INTO COMMENTS(item_id, comment_text) VALUES(2, 'not bad');
INSERT INTO ATTACHS(item_id, name) VALUES(1, 'unknown.jpg');

INSERT INTO CATEGORY(name) VALUES('Kittens'); --1
INSERT INTO CATEGORY(name) VALUES('Puppies'); --2

INSERT INTO ITEM_CATEGORY(item_id, cat_id)
SELECT IT.id, CA.id
	FROM ITEMS AS IT
	LEFT JOIN CATEGORY AS CA
	ON IT.user_id = CA.id
;


-- CHECK TABLE VALUES

SELECT * FROM ITEM_CATEGORY;


