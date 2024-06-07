DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
(`empID` int, `empPassword` varchar(30), `FirstName` varchar(30), `LastName` varchar(30), `empAddress` varchar(45), `empPhone` int(11), `empEmail` varchar(45), `empSkills` varchar(45),
`TerminationStatus` varchar(45), `DepotEmpID` int, `maintID` int,
PRIMARY KEY (`empID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Employee Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE Employee
INNER JOIN tempTable
ON tempTable.`empID` = Employee.`empID`
SET
tempTable.`empPassword` = Employee.`empPassword`,
tempTable.`FirstName` = Employee.`FirstName`,
tempTable.`LastName` = Employee.`LastName`,
tempTable.`empAddress` = Employee.`empAddress`,
tempTable.`empPhone` = Employee.`empPhone`,
tempTable.`empEmail` = Employee.`empEmail`,
tempTable.`empSkills` = Employee.`empSkills`,
tempTable.`TerminationStatus` = Employee.`TerminationStatus`,
tempTable.`DepotEmpID` = Employee.`DepotEmpID`,
tempTable.`maintID` = Employee.`maintID`;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from tempTable;