CREATE DATABASE company_master;
USE company_master;

SHOW DATABASES;
-- DROP DATABASE company_master;  -- (uncomment to drop)

-- ==============================
-- TABLE CREATION (DDL)
-- ==============================
CREATE TABLE Departments (
    dID INT PRIMARY KEY,
    dName VARCHAR(50) NOT NULL UNIQUE,
    location VARCHAR(50) DEFAULT 'HQ'
);

CREATE TABLE Employees (
    eID INT PRIMARY KEY,
    fName VARCHAR(50) NOT NULL,
    lName VARCHAR(50),
    salary INT CHECK (salary > 0),
    deptID INT,
    FOREIGN KEY (deptID) REFERENCES Departments(dID)
);

CREATE TABLE Projects (
    pID INT PRIMARY KEY,
    pName VARCHAR(50) NOT NULL,
    budget INT,
    deptID INT,
    FOREIGN KEY (deptID) REFERENCES Departments(dID)
);

SHOW TABLES;
DESC Employees;

-- ==============================
-- INSERT DATA
-- ==============================
INSERT INTO Departments VALUES
(1, 'HR', 'Delhi'),
(2, 'Admin', 'Mumbai'),
(3, 'IT', 'Bangalore'),
(4, 'Marketing', 'Chennai');

INSERT INTO Employees VALUES
(101, 'Vijay', 'Kumar', 250000, 3),
(102, 'Arun', 'Sharma', 90000, 1),
(103, 'Priya', 'Singh', 120000, 2),
(104, 'Varun', 'Mehta', 180000, 4),
(105, 'Sneha', 'Nair', 75000, 1),
(106, 'Ravi', 'Raj', 300000, 3),
(107, 'Kiran', 'Verma', 95000, 2),
(108, 'Vikas', 'Das', 150000, 4);

INSERT INTO Projects VALUES
(201, 'Recruitment Drive', 500000, 1),
(202, 'Office Renovation', 300000, 2),
(203, 'AI Development', 800000, 3),
(204, 'Ad Campaign', 400000, 4);

-- ==============================
-- JOINS
-- ==============================

-- INNER JOIN
SELECT e.fName, d.dName
FROM Employees e
INNER JOIN Departments d ON e.deptID = d.dID;

-- LEFT JOIN
SELECT e.fName, d.dName
FROM Employees e
LEFT JOIN Departments d ON e.deptID = d.dID;

-- RIGHT JOIN
SELECT e.fName, d.dName
FROM Employees e
RIGHT JOIN Departments d ON e.deptID = d.dID;

-- FULL OUTER JOIN (MySQL workaround with UNION)
SELECT e.fName, d.dName
FROM Employees e
LEFT JOIN Departments d ON e.deptID = d.dID
UNION
SELECT e.fName, d.dName
FROM Employees e
RIGHT JOIN Departments d ON e.deptID = d.dID;

-- NATURAL JOIN
SELECT fName, dName
FROM Employees
NATURAL JOIN Departments;

-- CROSS JOIN
SELECT e.fName, p.pName
FROM Employees e
CROSS JOIN Projects p;

-- INTERSECTION (using INNER JOIN on common deptID)
SELECT e.fName, d.dName
FROM Employees e
INNER JOIN Departments d ON e.deptID = d.dID;

