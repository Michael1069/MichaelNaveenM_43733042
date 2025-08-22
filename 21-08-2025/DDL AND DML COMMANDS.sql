show databases;
use michael;
create table ECE (
sid int,
sname varchar(30),
smarks int,
slocation varchar(30)
);
show tables from michael;
desc ECE;
select * from ECE;
alter table ECE;
INSERT INTO ECE (sid, sname, smarks, slocation)
VALUES 
(2, 'John', 88, chennai),
(3, 'Sarah', 92, banglore),
(4, 'David', 76, mumbai);
SELECT * FROM ECE;

alter table ECE rename column
	smark1 to smarks;

update ECE SET smarks=12
WHERE sname='john';
