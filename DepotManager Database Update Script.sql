DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
(`mgmtID` int, `authCount` int,
PRIMARY KEY (`mgmtID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\DepotManager Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE DepotManager
INNER JOIN tempTable
ON tempTable.`mgmtID` = DepotManager.`mgmtID`
SET
tempTable.`authCount` = DepotManager.`authCount`;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from tempTable;