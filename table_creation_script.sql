CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Equipment` (
  `depotID` INT NOT NULL,
  `itemID` INT NOT NULL,
  `itemName` VARCHAR(30) NOT NULL,
  `itemQuanity` INT NOT NULL,
  `itemAvailablity` TINYINT NOT NULL,
  `itemPrice` INT NULL,
  `numOut` INT NOT NULL,
  `isConsumable` TINYINT NULL,
  `SkillRequired` VARCHAR(45) NOT NULL,
  `Quanity` INT NOT NULL,
  PRIMARY KEY (`depotID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Checkout` (
  `CheckoutID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  `EquipmentID` INT NOT NULL,
  `CheckoutDate` DATE NOT NULL,
  `ReturnDate` DATE NULL,
  PRIMARY KEY (`CheckoutID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotManager` (
  `mgmtID` INT NOT NULL,
  `authCount` INT ZEROFILL NULL,
  PRIMARY KEY (`mgmtID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`MaintEmp` (
  `maintID` INT NOT NULL,
  `empSkills` INT ZEROFILL NULL,
  `equipLostCount` INT ZEROFILL NULL,
  `equipDamagedCount` INT ZEROFILL NULL,
  `warningGiven` VARCHAR(45) NULL,
  `lastLostDate` DATETIME NULL,
  `lastDamagedDate` DATETIME NULL,
  PRIMARY KEY (`maintID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotEmp` (
  `DepotEmpID` INT NOT NULL,
  `overrideRequest` INT UNSIGNED NULL,
  `mgmtID` INT NULL,
  PRIMARY KEY (`DepotEmpID`),
  INDEX `DepotEmp_idx` (`mgmtID` ASC) VISIBLE,
  CONSTRAINT `DepotEmp`
    FOREIGN KEY (`mgmtID`)
    REFERENCES `ceis400_group_project`.`DepotManager` (`mgmtID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`Employee` (
  `empID` INT NOT NULL,
  `empPassword` VARCHAR(30) NOT NULL,
  `FirstName` VARCHAR(30) NOT NULL,
  `LastName` VARCHAR(30) NOT NULL,
  `empSkills` VARCHAR(45) NULL,
  `TerminationStatus` VARCHAR(45) BINARY NULL,
  `addEmp` VARCHAR(20) BINARY NULL,
  `terminateEmp` VARCHAR(45) BINARY NULL,
  `notifyEmp` VARCHAR(45) BINARY NULL,
  `DepotEmpID` INT NULL,
  `maintID` INT NULL,
  PRIMARY KEY (`empID`),
  INDEX `DepotEmpID_idx` (`DepotEmpID` ASC) VISIBLE,
  INDEX `maintID_idx` (`maintID` ASC) VISIBLE,
  CONSTRAINT `DepotEmpID`
    FOREIGN KEY (`DepotEmpID`)
    REFERENCES `ceis400_group_project`.`DepotEmp` (`DepotEmpID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `maintID`
    FOREIGN KEY (`maintID`)
    REFERENCES `ceis400_group_project`.`MaintEmp` (`maintID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`System` (
  `systemID` INT NOT NULL,
  `depotID` INT NOT NULL,
  `empID` INT NOT NULL,
  `CheckoutID` INT NOT NULL,
  PRIMARY KEY (`systemID`),
  INDEX `depotID_idx` (`depotID` ASC) VISIBLE,
  INDEX `empID_idx` (`empID` ASC) VISIBLE,
  INDEX `checkoutID_idx` (`CheckoutID` ASC) VISIBLE,
  CONSTRAINT `depotID`
    FOREIGN KEY (`depotID`)
    REFERENCES `ceis400_group_project`.`Equipment` (`depotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `empID`
    FOREIGN KEY (`empID`)
    REFERENCES `ceis400_group_project`.`Employee` (`empID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `checkoutID`
    FOREIGN KEY (`CheckoutID`)
    REFERENCES `ceis400_group_project`.`Checkout` (`CheckoutID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;