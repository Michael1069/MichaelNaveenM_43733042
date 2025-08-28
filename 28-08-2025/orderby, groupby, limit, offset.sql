-- Create a new database
CREATE DATABASE company_demo2;
USE company_demo2;

-- Create employees table
CREATE TABLE employees (
    eID INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    salary INT,
    dept VARCHAR(30)
);

-- Insert some sample employees
INSERT INTO employees VALUES
(1, 'Alice', 'Brown', 120000, 'HR'),
(2, 'Bob', 'Smith', 80000, 'Admin'),
(3, 'Victor', 'Jones', 250000, 'IT'),
(4, 'Vera', 'Taylor', 220000, 'Marketing'),
(5, 'Charlie', 'Davis', 60000, 'Operations'),
(6, 'Diana', 'Moore', 150000, 'IT'),
(7, 'Evan', 'Clark', 95000, 'Admin'),
(8, 'Frank', 'Lopez', 300000, 'HR');

-- ================================
-- ORDER BY
-- ================================
-- List employees sorted by salary ascending
SELECT * FROM employees ORDER BY salary ASC;

-- List employees sorted by salary descending
SELECT * FROM employees ORDER BY salary DESC;

-- ================================
-- GROUP BY
-- ================================
-- Average salary by department
SELECT dept, AVG(salary) AS avg_salary
FROM employees
GROUP BY dept;

-- Total salary by department
SELECT dept, SUM(salary) AS total_salary
FROM employees
GROUP BY dept;
select * from employees;
-- ================================
-- UPPERCASE & LOWERCASE
-- ================================
-- Show first names in uppercase
SELECT UPPER(first_name) AS upper_name FROM employees;

-- Show last names in lowercase
SELECT LOWER(last_name) AS lower_name FROM employees;

-- ================================
-- LIMIT
-- ================================
-- Get top 3 highest paid employees
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 3;

-- ================================
-- OFFSET
-- ================================
-- Skip first 2 highest salaries and fetch the next 3
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 3 OFFSET 2;

-- ================================
-- TRIM METHODS
-- ================================
-- Remove spaces from both sides
SELECT TRIM(first_name) AS trimmed_first_name FROM employees;

-- Remove spaces from left side only
SELECT LTRIM(first_name) AS left_trimmed_name FROM employees;

-- Remove spaces from right side only
SELECT RTRIM(last_name) AS right_trimmed_name FROM employees;

-- problem 1
select dept, avg(salary) as avg_salary from employees where salary > 100000 
group by dept ;

-- problem 2
select dept, max(salary) as max_salary from employees where salary = 500000
group by dept;

-- problem 3
select dept, sum(salary) as total_salary, count(dept) as number_of_emp from employees where salary > 100000 
group by dept having count(dept) > 1;

-- problem 4
select dept, avg(salary) as avg_salary from employees where salary between 50000 and 200000
group by dept;
