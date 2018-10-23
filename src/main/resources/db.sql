CREATE SCHEMA IF NOT EXISTS `university2` DEFAULT CHARACTER SET utf8 ;
USE `university` ;

-- -----------------------------------------------------
-- Table `university`.`departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university2`.`departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `university`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `university2`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `departments_id` INT NOT NULL,
  PRIMARY KEY (`id`, `departments_id`),
  INDEX `fk_students_departments_idx` (`departments_id` ASC),
  CONSTRAINT `fk_students_departments`
  FOREIGN KEY (`departments_id`)
  REFERENCES `university2`.`departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE `university2`.`student_department` (
  `student_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`student_id`, `department_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;