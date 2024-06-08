-- these two lines take care of any issues coming from modifying the foreign keys and table attributes. Ensure that the final line resets the foreign key checks --
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;

-- this script initializes the blank tables for every class --

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Equipment` (
  `itemID` INT NOT NULL,
  `depotID` INT NOT NULL,
  `itemName` VARCHAR(30) NOT NULL,
  `itemQuantity` INT NOT NULL COMMENT 'Max Available number',
  `itemAvailability` TINYINT NOT NULL COMMENT '0 = no items available\n1 = items available',
  `numOut` INT NOT NULL COMMENT 'How many are currently checked out',
  `itemPrice` INT NULL,
  `isConsumable` TINYINT NOT NULL COMMENT '0 = false\n1 = true',
  `SkillRequired` VARCHAR(45) NOT NULL COMMENT 'Sheetmetal, Electrical, Woodworking, or None',
  `DamageType` VARCHAR(45) NULL,
  `EquipNotes` VARCHAR(45) NULL,
  PRIMARY KEY (`itemID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Checkout` (
  `TransactionID` INT NOT NULL,
  `empID` INT NOT NULL,
  `EquipmentID` INT NOT NULL,
  `CheckoutDate` DATE NOT NULL,
  `ReturnDate` DATE NOT NULL,
  PRIMARY KEY (`TransactionID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`MaintEmp` (
  `maintID` INT NOT NULL,
  `empSkills` VARCHAR(45) NULL,
  `equipLostCount` INT NULL,
  `equipDamagedCount` INT NULL,
  `warningGiven` TINYINT NULL COMMENT '0 = no warning given\n1 = warning given',
  `lastLostDate` DATE NULL,
  `lastDamagedDate` DATE NULL,
  PRIMARY KEY (`maintID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotManager` (
  `mgmtID` INT NOT NULL,
  `authCount` INT NULL,
  PRIMARY KEY (`mgmtID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotEmp` (
  `DepotEmpID` INT NOT NULL,
  `overrideRequest` INT UNSIGNED NULL,
  `mgmtID` INT NULL,
  PRIMARY KEY (`DepotEmpID`),
  INDEX `mgmtID_idx` (`mgmtID` ASC) VISIBLE,
  CONSTRAINT `mgmtID`
    FOREIGN KEY (`mgmtID`)
    REFERENCES `group_project`.`DepotManager` (`mgmtID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Employee` (
  `empID` INT NOT NULL,
  `empPassword` VARCHAR(30) NOT NULL,
  `FirstName` VARCHAR(30) NOT NULL,
  `LastName` VARCHAR(30) NOT NULL,
  `empAddress` VARCHAR(45) NOT NULL,
  `empCity` VARCHAR(45) NOT NULL,
  `empState` TEXT(2) NOT NULL,
  `empZip` VARCHAR(10) NOT NULL,
  `empPhone` INT(10) NULL,
  `empEmail` VARCHAR(45) NULL,
  `empSkills` VARCHAR(45) NOT NULL,
  `TerminationStatus` VARCHAR(45) NOT NULL,
  `DepotEmpID` INT NOT NULL,
  `maintID` INT NOT NULL,
  PRIMARY KEY (`empID`),
  INDEX `DepotEmpID_idx` (`DepotEmpID` ASC) VISIBLE,
  INDEX `maintID_idx` (`maintID` ASC) VISIBLE,
  CONSTRAINT `DepotEmpID`
    FOREIGN KEY (`DepotEmpID`)
    REFERENCES `group_project`.`DepotEmp` (`DepotEmpID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `maintID`
    FOREIGN KEY (`maintID`)
    REFERENCES `group_project`.`MaintEmp` (`maintID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`System` (
  `systemID` INT NOT NULL,
  `empID` INT NOT NULL,
  `TransactionID` INT NOT NULL,
  `itemID` INT NOT NULL,
  PRIMARY KEY (`systemID`),
  INDEX `itemID_idx` (`itemID` ASC) VISIBLE,
  INDEX `TransactionID_idx` (`TransactionID` ASC) VISIBLE,
  INDEX `empID_idx` (`empID` ASC) VISIBLE,
  CONSTRAINT `itemID`
    FOREIGN KEY (`itemID`)
    REFERENCES `group_project`.`Equipment` (`itemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TransactionID`
    FOREIGN KEY (`TransactionID`)
    REFERENCES `group_project`.`Checkout` (`TransactionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `empID`
    FOREIGN KEY (`empID`)
    REFERENCES `group_project`.`Employee` (`empID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- resets the foreign key checks back to normal --
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;