-- Step 1: Create a database
DROP DATABASE IF EXISTS shop_demo;
CREATE DATABASE shop_demo;
USE shop_demo;

-- Step 2: Create Category table
CREATE TABLE Category (
    cID INT PRIMARY KEY,
    cName VARCHAR(100) NOT NULL,
    cDesc VARCHAR(255)
);

-- Step 3: Create Product table with Foreign Key (ODC + OUC enabled)
CREATE TABLE Product (
    pID INT PRIMARY KEY,
    pName VARCHAR(100) NOT NULL,
    pDesc VARCHAR(255),
    cID INT,
    FOREIGN KEY (cID) REFERENCES Category(cID)
        ON DELETE CASCADE    -- ODC: delete category → delete products
        ON UPDATE CASCADE    -- OUC: update category id → update products
);

-- Step 4: Insert categories
INSERT INTO Category VALUES (1, 'Electronics', 'Devices and gadgets');
INSERT INTO Category VALUES (2, 'Clothing', 'Wearables and fashion');

-- Step 5: Insert products linked to categories
INSERT INTO Product VALUES (101, 'Smartphone', 'Android phone', 1);
INSERT INTO Product VALUES (102, 'Laptop', 'High performance laptop', 1);
INSERT INTO Product VALUES (201, 'T-Shirt', 'Cotton T-Shirt', 2);
INSERT INTO Product VALUES (202, 'Jeans', 'Denim jeans', 2);

-- Step 6: Show initial data
SELECT 'Initial Category Table' AS info;
SELECT * FROM Category;
SELECT 'Initial Product Table' AS info;
SELECT * FROM Product;

-- 🔹 Demonstration of ON DELETE CASCADE (ODC)
-- Step 7: Delete category 'Clothing' (cID = 2)
DELETE FROM Category WHERE cID = 2;

-- Step 8: Show results after ODC
SELECT 'After ODC (Category 2 deleted, its products auto-removed)' AS info;
SELECT * FROM Category;
SELECT * FROM Product;

-- 🔹 Demonstration of ON UPDATE CASCADE (OUC)
-- Step 9: Update category id from 1 → 10
UPDATE Category SET cID = 10 WHERE cID = 1;

-- Step 10: Show results after OUC
SELECT 'After OUC (Category 1 updated to 10, Products auto-updated)' AS info;
SELECT * FROM Category;
SELECT * FROM Product;
