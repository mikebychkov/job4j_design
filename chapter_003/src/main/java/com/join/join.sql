-- CREATING TABLES

CREATE TABLE body (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE engine (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE gearbox (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE cars (
	id serial primary key,
	name varchar(200),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id),
	body_id int references body(id)
);


-- POPULATING TABLES

INSERT INTO body(name) VALUES('light');--1
INSERT INTO body(name) VALUES('solid');--2
INSERT INTO body(name) VALUES('racing');--3

INSERT INTO engine(name) VALUES('4 liter');--1
INSERT INTO engine(name) VALUES('6 liter');--2
INSERT INTO engine(name) VALUES('8 liter');--3

INSERT INTO gearbox(name) VALUES('4 speed');--1
INSERT INTO gearbox(name) VALUES('5 speed');--2
INSERT INTO gearbox(name) VALUES('6 speed');--3
INSERT INTO gearbox(name) VALUES('5 speed automatic');--4

INSERT INTO cars(name, engine_id, gearbox_id, body_id) VALUES('cheap one', 1, 1, 1);--1
INSERT INTO cars(name, engine_id, gearbox_id, body_id) VALUES('expencive one', 2, 2, 2);--1


-- (1)

SELECT c.name AS car, e.name AS engine, g.name AS gearbox, b.name AS body
FROM cars AS c
	LEFT JOIN engine AS e
	ON c.engine_id = e.id
	LEFT JOIN gearbox AS g
	ON c.gearbox_id = g.id
	LEFT JOIN body AS b
	ON c.body_id = b.id
;
-- (2)

SELECT e.name
FROM engine AS e
	LEFT JOIN cars AS c
	ON e.id = c.engine_id
WHERE c.id is null
;
SELECT g.name
FROM gearbox AS g
	LEFT JOIN cars AS c
	ON g.id = c.gearbox_id
WHERE c.id is null
;
SELECT b.name
FROM body AS b
	LEFT JOIN cars AS c
	ON b.id = c.body_id
WHERE c.id is null
;