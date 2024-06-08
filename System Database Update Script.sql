
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
(`systemID` int, `empID` int, `TransactionID` int, `itemID` int,
PRIMARY KEY (`systemID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\System Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;                               --  used for testing --
-- select * from `System`;                                 --  used for testing --

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `System`
SELECT `systemID`, `empID`, `TransactionID`, `itemID`
FROM temp_table
ON duplicate key update `empID` = VALUES(`empID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from `System`;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE `System`
LEFT JOIN temp_Table ON `System`.`systemID` = temp_Table.`systemID`
SET
`System`.`empID` = temp_Table.`empID`, 
`System`.`TransactionID` = temp_Table.`TransactionID`, 
`System`.`itemID` = temp_Table.`itemID`;
-- select * from temp_Table;                               --  used for testing --
-- select * from `System`;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;