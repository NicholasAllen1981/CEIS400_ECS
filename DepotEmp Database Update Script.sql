DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
(`depotEmpID` int, `overrideRequest` int, `mgmtID` int,
PRIMARY KEY (`depotEmpID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\DepotEmp Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE DepotEmp
INNER JOIN tempTable
ON tempTable.`depotEmpID` = DepotEmp.`depotEmpID`
SET
tempTable.`overrideRequest` = DepotEmp.`overrideRequest`,
tempTable.`mgmtID` = DepotEmp.`mgmtID`;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from tempTable;