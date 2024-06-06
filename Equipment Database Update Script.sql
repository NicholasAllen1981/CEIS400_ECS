DROP TEMPORARY TABLE IF EXISTS tempTable;

-- creates a temporary table to store update data --
CREATE TEMPORARY TABLE tempTable
(itemID int, depotID int, itemName VARCHAR(30), itemQuantity int, itemAvailability tinyint, numOut int, itemPrice int, isConsumable tinyint, SkillRequired varchar(45),
PRIMARY KEY (itemID));

-- loads update data from the .csv file in the specific directory, into the temporary table --
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\Equipment Table Test Info.csv'
INTO TABLE tempTable
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

-- updates the SQL table from the data in the temporary table --
UPDATE equipment
INNER JOIN tempTable
ON tempTable.itemID = equipment.itemID
SET
tempTable.depotID = equipment.depotID,
tempTable.itemName = equipment.itemName,
tempTable.itemQuantity = equipment.itemQuantity,
tempTable.itemAvailability = equipment.itemAvailability,
tempTable.numOut = equipment.numOut,
tempTable.itemPrice = equipment.itemPrice,
tempTable.isConsumable = equipment.isConsumable,
tempTable.SkillRequired = equipment.SkillRequired;

-- deletes the temporary table --
DROP TEMPORARY TABLE tempTable;