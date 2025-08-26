CREATE DATABASE IF NOT EXISTS company_demo;

USE company_demo;

CREATE TABLE employees (
    eID INT PRIMARY KEY,
    eName VARCHAR(50),
    age INT,
    salary INT
);

INSERT INTO employees VALUES
(1, 'Karan', 28, 40000),
(2, 'Divya', 32, 55000),
(3, 'Rahul', 41, 30000),
(4, 'Arun', 40, 20000),
(5, 'Meera', 35, 25000),
(6, 'Vikram', 45, 18000);

SELECT eName, salary + 5000 AS salaryWithBonus FROM employees;
SELECT eName, salary = 9000 AS salaryWithBonus FROM employees;
SELECT eName, salary - 10000 AS reducedSalary FROM employees;
SELECT eName, age * 12 AS ageInMonths FROM employees;
SELECT eName, salary / age AS salaryPerYearOfAge FROM employees;
SELECT eName, salary % 10000 AS remainderSalary FROM employees;

select * from employees 
where eid in (1,2,4) and salary > 30000;
select * from employees 
where ename in ('Rahul','Arjun') and age > 40;

select * from employees 
where eid not in (1,2,4) and salary < 30000;
select * from employees 
where ename not in ('Rahul','Arjun') and age < 40;

select * from employees;
