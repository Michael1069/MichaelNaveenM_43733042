-- Step 1: Create database
CREATE DATABASE company_demo;
USE company_demo;

-- Step 2: Create employees table
CREATE TABLE employees (
    eID INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    salary INT,
    dept VARCHAR(30)
);

-- Step 3: Insert sample employees
INSERT INTO employees (first_name, last_name, salary, dept) VALUES
('Vikram', 'Sharma', 220000, 'HR'),
('Aarav', 'Mehta', 180000, 'Admin'),
('Varun', 'Patel', 90000,  'IT'),
('Sneha', 'Rao', 120000,  'Marketing'),
('Rohan', 'Iyer', 75000,  'Operations'),
('Vishal', 'Kumar', 250000, 'Admin'),
('Neha', 'Singh', 60000,  'HR');

-- Step 4: Check inserted data
SELECT * FROM employees;

-- -------------------------------
-- Queries
-- -------------------------------

-- 1. Find workers not in HR or Admin with salary between 70000 and 300000
SELECT * 
FROM employees
WHERE dept NOT IN ('HR', 'Admin')
  AND salary BETWEEN 70000 AND 300000;

-- 2. Find workers with first name starting with 'V' and salary >= 200000
SELECT * 
FROM employees
WHERE first_name LIKE 'V%'
  AND salary >= 200000;

-- 3. Find workers not in Admin with salary < 100000
SELECT * 
FROM employees
WHERE dept <> 'Admin'
  AND salary < 100000;

-- 4. Average salary in the Admin department
SELECT AVG(salary) AS avg_admin_salary
FROM employees
WHERE dept = 'Admin';

-- 5. Total salary for HR and Admin departments combined
SELECT SUM(salary) AS total_hr_admin_salary
FROM employees
WHERE dept IN ('HR', 'Admin');

-- 6. Count employees whose first name starts with 'V'
SELECT COUNT(*) AS count_v_names
FROM employees
WHERE first_name LIKE 'V%';

-- 7. Total salary of employees with salary between 50000 and 200000
SELECT SUM(salary) AS total_salary_range
FROM employees
WHERE salary BETWEEN 50000 AND 200000;
