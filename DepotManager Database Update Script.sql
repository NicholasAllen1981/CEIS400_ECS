
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
(`mgmtID` int, `authCount` int,
PRIMARY KEY (`mgmtID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\DepotManager Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotManager;                           --  used for testing --

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO DepotManager
SELECT `mgmtID`, `authCount`
FROM temp_table
ON duplicate key update `mgmtID` = VALUES(`mgmtID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotManager;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE DepotManager
LEFT JOIN temp_Table ON DepotManager.`mgmtID` = temp_Table.`mgmtID`
SET
DepotManager.`authCount` = temp_Table.`authCount`;
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotManager;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;