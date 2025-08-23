-- Step 1: Create a demo database and table
CREATE DATABASE IF NOT EXISTS check_demo;
USE check_demo;

CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    CONSTRAINT chk_age CHECK (age >= 18 AND age <= 130)
);

-- Step 2: Insert valid data (works fine)
INSERT INTO students VALUES (1, 'Alice', 20);
INSERT INTO students VALUES (2, 'Bob', 100);

-- Step 3: Try inserting invalid data (will FAIL)
-- Too young
-- INSERT INTO students VALUES (3, 'Charlie', 10);

-- Too old
-- INSERT INTO students VALUES (4, 'David', 150);

-- Step 4: Drop the CHECK constraint
ALTER TABLE students DROP CHECK chk_age;

-- Step 5: Now invalid data will be allowed
INSERT INTO students VALUES (3, 'Charlie', 10);
INSERT INTO students VALUES (4, 'David', 150);

-- Step 6: View all data
SELECT * FROM students;
