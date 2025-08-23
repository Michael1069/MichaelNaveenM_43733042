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

-- Step 3: Create Product table with Foreign Key referencing Category
CREATE TABLE Product (
    pID INT PRIMARY KEY,
    pName VARCHAR(100) NOT NULL,
    pDesc VARCHAR(255),
    cID INT,
    FOREIGN KEY (cID) REFERENCES Category(cID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- Step 4: Insert categories
INSERT INTO Category VALUES (1, 'Electronics', 'Devices and gadgets');
INSERT INTO Category VALUES (2, 'Clothing', 'Wearables and fashion');

-- Step 5: Insert products linked to categories
INSERT INTO Product VALUES (101, 'Smartphone', 'Android phone', 1);
INSERT INTO Product VALUES (102, 'Laptop', 'High performance laptop', 1);
INSERT INTO Product VALUES (201, 'T-Shirt', 'Cotton T-Shirt', 2);
INSERT INTO Product VALUES (202, 'Jeans', 'Denim jeans', 2);

-- Step 6: Check data
SELECT * FROM Category;
SELECT * FROM Product;
