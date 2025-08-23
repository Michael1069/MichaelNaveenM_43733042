CREATE DATABASE IF NOT EXISTS constraint_demo;
USE constraint_demo;

CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    email VARCHAR(100),
    phone VARCHAR(15),
    UNIQUE (email)   
);

INSERT INTO employees VALUES (1, 'alice@mail.com', '9999999999');
INSERT INTO employees VALUES (2, 'bob@mail.com', '8888888888');

SHOW INDEX FROM employees;

ALTER TABLE employees 
DROP INDEX email;

INSERT INTO employees VALUES (3, 'alice@mail.com', '7777777777');

SELECT * FROM employees;
