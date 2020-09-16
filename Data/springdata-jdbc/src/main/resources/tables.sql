--Customer
DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer
(
   id 				SERIAL PRIMARY KEY,
   name 			VARCHAR (50),
   sex 				VARCHAR (10)
);

--Address
DROP TABLE IF EXISTS Address;
CREATE TABLE Address
(
   customer 		INTEGER,
   customer_key 	VARCHAR (50),
   street 			VARCHAR (50),
   city 			VARCHAR (50)
);

--Book
DROP TABLE IF EXISTS Book;
CREATE TABLE Book 
(
	id				SERIAL PRIMARY KEY,
	title			VARCHAR (50)
);

--Book Author
DROP TABLE IF EXISTS Book_Author;
CREATE TABLE Book_Author
(
	book			INTEGER,
	author			INTEGER
);

--Author
CREATE TABLE Author
CREATE TABLE Author
(
	id				SERIAL PRIMARY KEY,
	name			VARCHAR (50)
);

--Clean console
\! cls
