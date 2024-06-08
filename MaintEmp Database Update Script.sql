
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
-- temporary tables cant accept NULL values so default is 1111-11-11 for dates in the .csv file --
(`maintID` int, `empSkills` varchar(45), `equipLostCount` int, `equipDamagedCount` int, `warningGiven` tinyint, `lastLostDate` date, `lastDamagedDate` date,
PRIMARY KEY (`maintID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\MaintEmp Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;                               --  used for testing --
-- select * from MaintEmp;                                --  used for testing --

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO MaintEmp
SELECT `maintID`, `empSkills`, `equipLostCount`, `equipDamagedCount`, `warningGiven`, `lastLostDate`, `lastDamagedDate`
FROM temp_table
ON duplicate key update `maintID` = VALUES(`maintID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from MaintEmp;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE MaintEmp
LEFT JOIN temp_Table ON MaintEmp.`maintID` = temp_Table.`maintID`
SET
MaintEmp.`empSkills` = temp_Table.`empSkills`, 
MaintEmp.`equipLostCount` = temp_Table.`equipLostCount`, 
MaintEmp.`equipDamagedCount` = temp_Table.`equipDamagedCount`, 
MaintEmp.`warningGiven` = temp_Table.`warningGiven`, 
MaintEmp.`lastLostDate` = temp_Table.`lastLostDate`, 
MaintEmp.`lastDamagedDate` = temp_Table.`lastDamagedDate`;
-- select * from temp_Table;                               --  used for testing --
-- select * from MaintEmp;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;
