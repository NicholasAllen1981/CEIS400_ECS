
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
-- temporary tables cant accept NULL values so default is 1111-11-11 for dates in the .csv file --
(`TransactionID` int, `empID` int, `EquipmentID` int, `CheckoutDate` DATE, `ReturnDate` DATE,
PRIMARY KEY (`TransactionID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Checkout Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;                               --  used for testing --
-- select * from Checkout;                                --  used for testing --

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO Checkout
SELECT `TransactionID`, `empID`, `EquipmentID`, `CheckoutDate`, `ReturnDate`
FROM temp_table
ON duplicate key update `TransactionID` = VALUES(`TransactionID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from Checkout;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE Checkout
LEFT JOIN temp_Table ON Checkout.`TransactionID` = temp_Table.`TransactionID`
SET
Checkout.`empID` = temp_Table.`empID`, 
Checkout.`EquipmentID` = temp_Table.`EquipmentID`, 
Checkout.`CheckoutDate` = temp_Table.`CheckoutDate`, 
Checkout.`ReturnDate` = temp_Table.`ReturnDate`;
-- select * from temp_Table;                               --  used for testing --
-- select * from Checkout;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;