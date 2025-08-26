CREATE DATABASE employee_demo_db;
USE employee_demo_db;

-- Drop table if already exists
DROP TABLE IF EXISTS employees;

-- Create employees table
CREATE TABLE employees (
    eID INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    salary INT,
    dept VARCHAR(30)
);

INSERT INTO employees (eID, firstName, lastName, salary, dept) VALUES
(1, 'Alice',   'Smith',   95000,  'HR'),
(2, 'Bob',     'Johnson', 120000, 'Admin'),
(3, 'Charlie', 'Brown',   180000, 'IT'),
(4, 'Victor',  'White',   250000, 'Marketing'),
(5, 'Vikram',  'Sharma',  75000,  'Operations'),
(6, 'Diana',   'Prince',  300000, 'Admin'),
(7, 'Ethan',   'Hunt',    50000,  'HR'),
(8, 'Vera',    'Black',   150000, 'IT'),
(9, 'Varun',   'Kumar',   210000, 'Finance'),
(10,'Sophia',  'Lopez',   135000, 'IT');


-- 1. IN (HR or Admin employees)
SELECT * FROM employees 
WHERE dept IN ('HR', 'Admin');

-- 2. NOT IN (Exclude HR and Admin)
SELECT * FROM employees 
WHERE dept NOT IN ('HR', 'Admin');

-- 3. BETWEEN (salary range)
SELECT * FROM employees 
WHERE salary BETWEEN 80000 AND 200000;

-- 4. NOT BETWEEN (outside salary range)
SELECT * FROM employees 
WHERE salary NOT BETWEEN 80000 AND 200000;

-- 5. MIN (lowest salary)
SELECT MIN(salary) AS Lowest_Salary FROM employees;

-- 6. MAX (highest salary)
SELECT MAX(salary) AS Highest_Salary FROM employees;

-- 7. SUM (total salary)
SELECT SUM(salary) AS Total_Salary FROM employees;

-- 8. COUNT (number of employees)
SELECT COUNT(*) AS Total_Employees FROM employees;

-- 9. AVG (average salary)
SELECT AVG(salary) AS Average_Salary FROM employees;

-- 10. LIKE (names starting with 'V')
SELECT * FROM employees 
WHERE firstName LIKE 'V%';

-- 11. GROUP BY (avg salary per department)
SELECT dept, AVG(salary) AS Avg_Salary 
FROM employees 
GROUP BY dept;

-- 12. GROUP BY with COUNT (how many employees per department)
SELECT dept, COUNT(*) AS Employee_Count
FROM employees
GROUP BY dept;

-- 13. HAVING (departments with avg salary > 150000)
SELECT dept, AVG(salary) AS Avg_Salary
FROM employees
GROUP BY dept
HAVING AVG(salary) > 150000;

-- 14. UNION (unique employees from HR & Admin)
SELECT firstName, dept FROM employees WHERE dept = 'HR'
UNION
SELECT firstName, dept FROM employees WHERE dept = 'Admin';

-- 15. UNION ALL (allow duplicates)
SELECT firstName, dept FROM employees WHERE dept = 'HR'
UNION ALL
SELECT firstName, dept FROM employees WHERE dept = 'Admin';

-- 16. ORDER BY (sort by salary descending)
SELECT * FROM employees
ORDER BY salary DESC;

-- 17. DISTINCT (unique departments)
SELECT DISTINCT dept FROM employees;

