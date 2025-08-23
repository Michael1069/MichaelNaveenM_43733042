DROP DATABASE IF EXISTS tcl_demo;
CREATE DATABASE tcl_demo;
USE tcl_demo;

CREATE TABLE accounts (
    acc_id INT PRIMARY KEY,
    acc_name VARCHAR(50),
    balance INT
);

-- Insert initial data
INSERT INTO accounts VALUES (1, 'Alice', 1000), (2, 'Bob', 1500);

-- Disable autocommit (so changes don’t apply immediately)
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- Insert a new account
INSERT INTO accounts VALUES (3, 'Charlie', 2000);

-- Create a SAVEPOINT
SAVEPOINT sp1;

-- Update balances
UPDATE accounts SET balance = balance - 200 WHERE acc_id = 1;
UPDATE accounts SET balance = balance + 200 WHERE acc_id = 2;

-- Rollback to savepoint (undoes the balance updates only, not the insert)
ROLLBACK TO sp1;

-- Commit the transaction (Charlie’s insert is saved permanently)
COMMIT;

-- Final result
SELECT * FROM accounts;
