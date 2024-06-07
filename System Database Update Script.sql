DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
(`systemID` int, `empID`int, `TransactionID` int, `itemID` int,
PRIMARY KEY (`systemID`));

-- loads update data from the .csv file in the specific directory, into the temporary table. Location of the directory determined by the secure_file_priv Locator Script --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\System Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE `System`
INNER JOIN tempTable
ON tempTable.`systemID` = `System`.`systemID`
SET
tempTable.`empID` = `System`.`empID`,
tempTable.`TransactionID` = `System`.`TransactionID`,
tempTable.`itemID` = `System`.`itemID`;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from `System`;