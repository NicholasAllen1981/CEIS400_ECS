DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
-- temporary tables cant accept NULL values so default is 1111-11-11 for dates in the .csv file --
(`maintID` int, `empSkills` varchar(45), `equipLostCount` int, `equipDamagedCount` int, `warningGiven` tinyint, `lastLostDate` date, `lastDamagedDate` date,
PRIMARY KEY (`maintID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\MaintEmp Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE MaintEmp
INNER JOIN tempTable
ON tempTable.`maintID` = MaintEmp.`maintID`
SET
tempTable.`empSkills` = MaintEmp.`empSkills`,
tempTable.`equipLostCount` = MaintEmp.`equipLostCount`,
tempTable.`equipDamagedCount` = MaintEmp.`equipDamagedCount`,
tempTable.`warningGiven` = MaintEmp.`warningGiven`,
tempTable.`lastLostDate` = MaintEmp.`lastLostDate`,
tempTable.`lastDamagedDate` = MaintEmp.`lastDamagedDate`;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from tempTable;