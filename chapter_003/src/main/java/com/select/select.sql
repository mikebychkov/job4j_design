CREATE TABLE Type (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE Product (
	id serial primary key,
	name varchar(500),
	type_id int references Type(id),
	expired_date timestamp,
	price decimal(10, 2)
);

INSERT INTO Type(name) VALUES('МОЛОКО'); --1
INSERT INTO Type(name) VALUES('СЫР'); --2
INSERT INTO Type(name) VALUES('МОРОЖЕНОЕ'); --3

INSERT INTO Product(name, type_id, expired_date, price) VALUES('МОЛОКО ЖИРНОСТЬ 1%', 1, '20200605', 10.5);
INSERT INTO Product(name, type_id, expired_date, price) VALUES('МОЛОКО ЖИРНОСТЬ 2%', 1, '20200601', 14.5);
INSERT INTO Product(name, type_id, expired_date, price) VALUES('СЫР ТОПЛЕНЫЙ', 2, '20201105', 32.1);
INSERT INTO Product(name, type_id, expired_date, price) VALUES('СЫР СЛИВОЧНЫЙ', 2, '20201005', 31.3);
INSERT INTO Product(name, type_id, expired_date, price) VALUES('Мороженое ПЛОМБИР', 3, '20200705', 5.55);

SELECT * FROM Product;

-- (1)
SELECT p.name
FROM product AS p
	INNER JOIN type AS t
	ON p.type_id = t.id
	AND t.name = 'СЫР'
;
-- (2)
SELECT p.name
FROM product AS p
WHERE p.name LIKE '%Мороженое%'
;
-- (3)
SELECT p.name, p.expired_date
FROM product AS p
WHERE p.expired_date 
	BETWEEN (date_trunc('month', current_timestamp) + '1 month') 
		AND (date_trunc('month', current_timestamp) + '2 month')
;
-- (4)
SELECT p.name, p.price
FROM product AS p
WHERE p.price = (SELECT MAX(pp.price) FROM product AS pp)
;
-- (5)
SELECT t.name, COUNT(DISTINCT p.id) AS prod_count
FROM product AS p
	INNER JOIN type AS t
	ON p.type_id = t.id
GROUP BY t.name
;
-- (6)
SELECT p.name
FROM product AS p
	INNER JOIN type AS t
	ON p.type_id = t.id
	AND t.name IN ('СЫР', 'МОЛОКО')
;
-- (7)
SELECT t.name, COUNT(DISTINCT p.id) AS prod_count
FROM product AS p
	INNER JOIN type AS t
	ON p.type_id = t.id
GROUP BY t.name HAVING COUNT(DISTINCT p.id) < 10
;
-- (8)
SELECT p.name, t.name
FROM product AS p
	LEFT JOIN type AS t
	ON p.type_id = t.id
;