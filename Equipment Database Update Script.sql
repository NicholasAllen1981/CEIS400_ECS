DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
-- single/double quotes for values, strings, etc --
-- backticks for column names --
CREATE TEMPORARY TABLE tempTable
(`itemID` int, `depotID` int, `itemName` VARCHAR(30), `itemQuantity` int, `itemAvailability` tinyint, `numOut` int, `itemPrice` int, `isConsumable` tinyint, `SkillRequired` varchar(45), `DamageType` varchar(45), `EquipNotes` varchar(45),
PRIMARY KEY (`itemID`));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Equipment Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE Equipment
INNER JOIN tempTable
ON tempTable.`itemID` = Equipment.`itemID`
SET
tempTable.`depotID` = Equipment.`depotID`,
tempTable.`itemName` = Equipment.`itemName`,
tempTable.`itemQuantity` = Equipment.`itemQuantity`,
tempTable.`itemAvailability` = Equipment.`itemAvailability`,
tempTable.`numOut` = Equipment.`numOut`,
tempTable.`itemPrice` = Equipment.`itemPrice`,
tempTable.`isConsumable` = Equipment.`isConsumable`,
tempTable.`SkillRequired` = Equipment.`SkillRequired`,
tempTable.`DamageType` = Equipment.`DamageType`,
tempTable.`EquipNotes` = Equipment.`EquipNotes`;

-- deletes the temporary table --
-- DROP TEMPORARY TABLE tempTable;
select * from tempTable;