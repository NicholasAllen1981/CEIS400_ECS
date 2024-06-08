
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
(`depotEmpID` int, `overrideRequest` int, `mgmtID` int,
PRIMARY KEY (`depotEmpID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\DepotEmp Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotEmp;                                 --  used for testing --

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO DepotEmp
SELECT `depotEmpID`, `overrideRequest`, `mgmtID`
FROM temp_table
ON duplicate key update `depotEmpID` = VALUES(`depotEmpID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotEmp;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE DepotEmp
LEFT JOIN temp_Table ON DepotEmp.`depotEmpID` = temp_Table.`depotEmpID`
SET
DepotEmp.`overrideRequest` = temp_Table.`overrideRequest`, 
DepotEmp.`mgmtID` = temp_Table.`mgmtID`;
-- select * from temp_Table;                               --  used for testing --
-- select * from DepotEmp;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;