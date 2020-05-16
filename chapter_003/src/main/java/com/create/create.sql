-- DATABASE

create database mydb;


-- USERS & ROLES

create table USERS (
	id serial primary key
);

create table ROLES (
	id serial primary key
);

create table USER_ROLES (
	id serial primary key,
	user_id int references USERS(id),
	role_id int references ROLES(id)
);

create table RULES (
	id serial primary key
);

create table ROLE_RULES (
	id serial primary key,
	role_id int references ROLES(id),
	rule_id int references RULES(id)
);


-- ITEMS

create table STATE (
	id serial primary key
);

create table ITEMS (
	id serial primary key,
	user_id int references USERS(id),
	state_id int references STATE(id)
);

create table COMMENTS (
	id serial primary key,
	item_id int references ITEMS(id)
);

create table ATTACHS (
	id serial primary key,
	item_id int references ITEMS(id)
);

create table CATEGORY (
	id serial primary key
);

create table ITEM_CATEGORY (
	id serial primary key,
	item_id int references ITEMS(id),
	cat_id int references CATEGORY(id)
);
