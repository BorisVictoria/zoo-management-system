-- -----------------------------------------------------
-- Schema zoo_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS zoo_db;
CREATE SCHEMA IF NOT EXISTS zoo_db;
USE zoo_db;

-- -----------------------------------------------------
-- Table enclosure
-- -----------------------------------------------------
DROP TABLE IF EXISTS enclosure;
CREATE TABLE IF NOT EXISTS enclosure (
	enc_id			INT(4),
	enc_type		VARCHAR(10) NOT NULL,
    enc_price       DECIMAL(5,2),
    PRIMARY KEY		(enc_id)
);

-- -----------------------------------------------------
-- Table animal
-- -----------------------------------------------------
DROP TABLE IF EXISTS animal;
CREATE TABLE IF NOT EXISTS animal (
	anim_id			INT(4),
    anim_name			VARCHAR(20),
    anim_type			VARCHAR(30),
    age				INT(3),
    diet			VARCHAR(20),
    enc_id			INT(4),
    PRIMARY KEY		(anim_id),
    FOREIGN KEY		(enc_id)
		REFERENCES	enclosure(enc_id)
);

-- -----------------------------------------------------
-- Table ticket
-- -----------------------------------------------------
DROP TABLE IF EXISTS ticket;
CREATE TABLE IF NOT EXISTS ticket (
	tick_id			INT(4),
    pass_type		VARCHAR(10),
    price			DECIMAL(5,2),
    p_date			DATE,
    enc_id			INT(4),
    PRIMARY KEY		(tick_id),
    FOREIGN KEY		(enc_id)
		REFERENCES	enclosure(enc_id)
);

-- -----------------------------------------------------
-- Table employee
-- -----------------------------------------------------
DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
	emp_id			INT(4),
	emp_name        VARCHAR(20),
    salary			DECIMAL(10,2),
    dob				DATE,
    age				INT(3),
    job				VARCHAR(20),
    enc_id			INT(4),
    PRIMARY KEY		(emp_id),
    FOREIGN KEY		(enc_id)
		REFERENCES	enclosure(enc_id)
);

-- -----------------------------------------------------
-- Add records to enclosure
-- -----------------------------------------------------
INSERT INTO enclosure
	VALUES(1000, 'arctic', 100.00);

INSERT INTO enclosure
VALUES(1100, 'gay', 500.25);
    

-- -----------------------------------------------------
-- Add records to animal
-- -----------------------------------------------------
INSERT INTO animal
	VALUES(1212, 'spencer',	'macaroni penguin', 8, 'krill', 1000);

INSERT INTO employee
    VALUES(1111, 'boris', '4000', '2003-07-04', '20', 'Programmer', 1000);

INSERT INTO employee
    VALUES(2222, 'chris', '4000', '2003-07-04', '20', 'Keeper', 1000);
