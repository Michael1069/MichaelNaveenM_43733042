-- Step 1: Create a demo database and table
CREATE DATABASE IF NOT EXISTS pk_demo;
USE pk_demo;

CREATE TABLE students (
    roll_no INT PRIMARY KEY,    -- Primary Key
    name VARCHAR(50),
    age INT
);

-- Step 2: Insert some rows
INSERT INTO students VALUES (1, 'Alice', 20);
INSERT INTO students VALUES (2, 'Bob', 21);

-- Step 3: Drop the PRIMARY KEY
ALTER TABLE students DROP PRIMARY KEY;

-- Step 4: Now insert duplicate roll_no (works because PK is removed)
INSERT INTO students VALUES (1, 'Charlie', 22);

-- Step 5: View final data
SELECT * FROM students;
