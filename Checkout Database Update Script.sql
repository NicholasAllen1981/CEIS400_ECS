DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
-- temporary tables cant accept NULL values so default is 1111-11-11 for dates in the .csv file --
(`TransactionID` int, `empID` int, `EquipmentID` int, `CheckoutDate` DATE, `ReturnDate` DATE,
PRIMARY KEY (`TransactionID`));

-- loads update data from the .csv file in the specific directory, into the temporary table. Location of the directory determined by the secure_file_priv Locator Script --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Checkout Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;


-- updates the SQL table from the data in the temporary table --
UPDATE Checkout
INNER JOIN tempTable
ON tempTable.`TransactionID` = Checkout.`TransactionID`
SET
tempTable.`empID` = Checkout.`empID`,
tempTable.`EquipmentID` = Checkout.`EquipmentID`,
tempTable.`CheckoutDate` = Checkout.`CheckoutDate`,
tempTable.`ReturnDate` = Checkout.`ReturnDate`;


-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;
-- select * from tempTable;