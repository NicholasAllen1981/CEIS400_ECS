
-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
DROP TABLE IF EXISTS temp_Table;
CREATE TEMPORARY TABLE temp_Table
(`itemID` int, `depotID` int, `itemName` VARCHAR(30), `itemQuantity` int, `itemAvailability` tinyint, `numOut` int, `itemPrice` int, `isConsumable` tinyint, `SkillRequired` varchar(45), `DamageType` varchar(45), `EquipNotes` varchar(45),
PRIMARY KEY (`itemID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Equipment Table Test Info.csv'
INTO TABLE temp_Table
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
-- select * from temp_Table;
-- select * from Equipment;

-- This code populates the database from the file. --
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO Equipment
SELECT `itemID`, `depotID`, `itemName`, `itemQuantity`, `itemAvailability`, `numOut`, `itemPrice`, `isConsumable`, `SkillRequired`, `DamageType`, `EquipNotes`
FROM temp_table
ON duplicate key update `itemID` = VALUES(`itemID`);  --  used to UPDATE a primary/unique key if there is a duplicate --
-- select * from temp_Table;                               --  used for testing --
-- select * from Equipment;                                --  used for testing --
-- DROP TABLE IF EXISTS temp_Table;

-- This code only updates the database. The above INSERT code needs to be run first to ensure that the database is populated --
UPDATE Equipment
LEFT JOIN temp_Table ON Equipment.`itemID` = temp_Table.`itemID`
SET
Equipment.`depotID` = temp_Table.`depotID`, 
Equipment.`itemName` = temp_Table.`itemName`, 
Equipment.`itemQuantity` = temp_Table.`itemQuantity`, 
Equipment.`itemAvailability` = temp_Table.`itemAvailability`, 
Equipment.`numOut` = temp_Table.`numOut`, 
Equipment.`itemPrice` = temp_Table.`itemPrice`, 
Equipment.`isConsumable` = temp_Table.`isConsumable`, 
Equipment.`SkillRequired` = temp_Table.`SkillRequired`,
Equipment.`DamageType` = temp_Table.`DamageType`, 
Equipment.`EquipNotes` = temp_Table.`EquipNotes`;
-- select * from temp_Table;                               --  used for testing --
 select * from Equipment;                                 --  used for testing --
SET FOREIGN_KEY_CHECKS = 1;
DROP TABLE IF EXISTS temp_Table;