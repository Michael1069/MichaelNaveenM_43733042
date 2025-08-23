-- Step 1: Create and use a database
CREATE DATABASE IF NOT EXISTS constraint_demo;
USE constraint_demo;

-- Step 2: Create a table with a UNIQUE constraint on email
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    email VARCHAR(100),
    phone VARCHAR(15),
    UNIQUE (email)   -- unique constraint on email
);

-- Step 3: Insert some rows (this works fine)
INSERT INTO employees VALUES (1, 'alice@mail.com', '9999999999');
INSERT INTO employees VALUES (2, 'bob@mail.com', '8888888888');

-- Step 4: Try inserting a duplicate email (this will FAIL because of UNIQUE constraint)
-- INSERT INTO employees VALUES (3, 'alice@mail.com', '7777777777');

-- Step 5: Check current indexes/constraints
SHOW INDEX FROM employees;

-- Step 6: Drop the UNIQUE constraint (email is the index name)
ALTER TABLE employees DROP INDEX email;

-- Step 7: Now try inserting duplicate email (this will WORK because constraint is dropped)
INSERT INTO employees VALUES (3, 'alice@mail.com', '7777777777');

-- Step 8: View final data
SELECT * FROM employees;
