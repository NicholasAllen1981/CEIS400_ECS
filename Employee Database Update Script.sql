
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
(`empID` int, `empPassword` varchar(30), `FirstName` varchar(30), `LastName` varchar(30), `empAddress` varchar(45), `empCity` varchar(45), `empState` varchar(45), `empZip` varchar(10),
`empPhone` int(11), `empEmail` varchar(45), `empSkills` varchar(45), `TerminationStatus` varchar(45), `DepotEmpID` int, `maintID` int,
PRIMARY KEY (`empID`));


LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Employee Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;
-- select * from Employee;

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO Employee
SELECT `empID`, `empPassword`, `FirstName`, `LastName`, `empAddress`, `empCity`, `empState`, `empZip`, `empPhone`, `empEmail`, `empSkills`, `TerminationStatus`, `DepotEmpID`, `maintID`
FROM temp_table
ON duplicate key update `empID` = VALUES(`empID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from MaintEmp;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE Employee
LEFT JOIN temp_Table ON Employee.`empID` = temp_Table.`empID`
SET
Employee.`empPassword` = temp_Table.`empPassword`, 
Employee.`FirstName` = temp_Table.`FirstName`, 
Employee.`LastName` = temp_Table.`LastName`, 
Employee.`empAddress` = temp_Table.`empAddress`, 
Employee.`empCity` = temp_Table.`empCity`, 
Employee.`empState` = temp_Table.`empState`, 
Employee.`empZip` = temp_Table.`empZip`,
Employee.`empPhone` = temp_Table.`empPhone`, 
Employee.`empEmail` = temp_Table.`empEmail`, 
Employee.`empSkills` = temp_Table.`empSkills`, 
Employee.`TerminationStatus` = temp_Table.`TerminationStatus`, 
Employee.`DepotEmpID` = temp_Table.`DepotEmpID`,
Employee.`maintID` = temp_Table.`maintID`;
-- select * from temp_Table;                               --  used for testing --
-- select * from Employee;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;