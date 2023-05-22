-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bonvoyage
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bonvoyage` ;

-- -----------------------------------------------------
-- Schema bonvoyage
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bonvoyage` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `bonvoyage` ;

-- -----------------------------------------------------
-- Table `bonvoyage`.`sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`sido` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`sido` (
  `sido_code` INT(11) NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bonvoyage`.`gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`gugun` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`gugun` (
  `gugun_code` INT(11) NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT(11) NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC),
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `bonvoyage`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bonvoyage`.`attraction_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`attraction_info` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`attraction_info` (
  `content_id` INT(11) NOT NULL,
  `content_type_id` INT(11) NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT(11) NULL DEFAULT NULL,
  `sido_code` INT(11) NULL DEFAULT NULL,
  `gugun_code` INT(11) NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC),
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC),
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC),
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `bonvoyage`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `bonvoyage`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `bonvoyage`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bonvoyage`.`attraction_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`attraction_description` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`attraction_description` (
  `content_id` INT(11) NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `bonvoyage`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bonvoyage`.`attraction_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`attraction_detail` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`attraction_detail` (
  `content_id` INT(11) NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `bonvoyage`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bonvoyage`.`hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`hashtag` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`hashtag` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tag_name` VARCHAR(20) NULL DEFAULT NULL,
  `tag_icon` CHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`user` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_pw` VARCHAR(225) NULL DEFAULT NULL,
  `user_name` VARCHAR(20) NULL DEFAULT NULL,
  `user_email` VARCHAR(50) NULL DEFAULT NULL,
  `profile_img_src` VARCHAR(125) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`image_board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`image_board` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`image_board` (
  `image_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `title` VARCHAR(20) NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `image_url` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  INDEX `FK_user_TO_image_board` (`user_id` ASC),
  CONSTRAINT `FK_user_TO_image_board`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`image_board_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`image_board_comment` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`image_board_comment` (
  `comment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `image_id` INT(11) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  `regist_time` DATE NULL DEFAULT NULL,
  `comment` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `FK_image_board_TO_image_board_comment` (`image_id` ASC),
  INDEX `FK_user_TO_image_board_comment` (`user_id` ASC),
  CONSTRAINT `FK_image_board_TO_image_board_comment`
    FOREIGN KEY (`image_id`)
    REFERENCES `bonvoyage`.`image_board` (`image_id`),
  CONSTRAINT `FK_user_TO_image_board_comment`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`route` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`route` (
  `route_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NULL DEFAULT NULL,
  `title` VARCHAR(50) NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  INDEX `FK_user_TO_route` (`user_id` ASC),
  CONSTRAINT `FK_user_TO_route`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`route_has_attraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`route_has_attraction` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`route_has_attraction` (
  `route_id` INT(11) NOT NULL,
  `content_id` INT(11) NOT NULL,
  `day` INT(11) NOT NULL,
  `simple_desc` VARCHAR(45) NULL DEFAULT NULL,
  `order` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`route_id`, `content_id`),
  INDEX `fk_route_has_attraction_info_attraction_info1_idx` (`content_id` ASC),
  INDEX `fk_route_has_attraction_info_route1_idx` (`route_id` ASC),
  CONSTRAINT `fk_route_has_attraction_info_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `bonvoyage`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_has_attraction_info_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `bonvoyage`.`route` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`route_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`route_hashtag` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`route_hashtag` (
  `route_id` INT(11) NOT NULL,
  `tag_id` INT(11) NOT NULL,
  PRIMARY KEY (`route_id`, `tag_id`),
  INDEX `FK_hashtag_TO_route_hashtag` (`tag_id` ASC),
  CONSTRAINT `FK_hashtag_TO_route_hashtag`
    FOREIGN KEY (`tag_id`)
    REFERENCES `bonvoyage`.`hashtag` (`tag_id`),
  CONSTRAINT `FK_route_TO_route_hashtag`
    FOREIGN KEY (`route_id`)
    REFERENCES `bonvoyage`.`route` (`route_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`user_attraction_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`user_attraction_like` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`user_attraction_like` (
  `user_id` INT(11) NOT NULL,
  `content_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `content_id`),
  INDEX `fk_user_has_attraction_info_attraction_info1_idx` (`content_id` ASC),
  INDEX `fk_user_has_attraction_info_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_attraction_info_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `bonvoyage`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_attraction_info_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`user_image_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`user_image_like` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`user_image_like` (
  `image_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`image_id`, `user_id`),
  INDEX `FK_user_TO_user_image_like` (`user_id` ASC),
  CONSTRAINT `FK_image_board_TO_user_image_like`
    FOREIGN KEY (`image_id`)
    REFERENCES `bonvoyage`.`image_board` (`image_id`),
  CONSTRAINT `FK_user_TO_user_image_like`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`user_route_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`user_route_like` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`user_route_like` (
  `route_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`route_id`, `user_id`),
  INDEX `FK_user_TO_user_route_like` (`user_id` ASC),
  CONSTRAINT `FK_route_TO_user_route_like`
    FOREIGN KEY (`route_id`)
    REFERENCES `bonvoyage`.`route` (`route_id`),
  CONSTRAINT `FK_user_TO_user_route_like`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`user_route_participate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`user_route_participate` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`user_route_participate` (
  `route_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`route_id`, `user_id`),
  INDEX `FK_user_TO_user_route_participate` (`user_id` ASC),
  CONSTRAINT `FK_route_TO_user_route_participate`
    FOREIGN KEY (`route_id`)
    REFERENCES `bonvoyage`.`route` (`route_id`),
  CONSTRAINT `FK_user_TO_user_route_participate`
    FOREIGN KEY (`user_id`)
    REFERENCES `bonvoyage`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `bonvoyage`.`image_board_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bonvoyage`.`image_board_hashtag` ;

CREATE TABLE IF NOT EXISTS `bonvoyage`.`image_board_hashtag` (
  `image_board_image_id` INT(11) NOT NULL,
  `hashtag_tag_id` INT(11) NOT NULL,
  PRIMARY KEY (`image_board_image_id`, `hashtag_tag_id`),
  INDEX `fk_image_board_has_hashtag_hashtag1_idx` (`hashtag_tag_id` ASC),
  INDEX `fk_image_board_has_hashtag_image_board1_idx` (`image_board_image_id` ASC),
  CONSTRAINT `fk_image_board_has_hashtag_image_board1`
    FOREIGN KEY (`image_board_image_id`)
    REFERENCES `bonvoyage`.`image_board` (`image_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_board_has_hashtag_hashtag1`
    FOREIGN KEY (`hashtag_tag_id`)
    REFERENCES `bonvoyage`.`hashtag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- hashtag는 정적으로 제공한다.
INSERT INTO `bonvoyage`.`hashtag`(tag_name, tag_icon) VALUES ('산', 'fa-mountain'),
															 ('바다', 'fa-umbrella-beach'),
                                                             ('호캉스', 'fa-hotel'),
                                                             ('촌캉스', 'fa-tractor'),
                                                             ('액티비티', 'fa-person-snowboarding'),
                                                             ('힐링', 'fa-tree'),
                                                             ('도심', 'fa-city'),
                                                             ('가족과 함께', 'fa-children'),
                                                             ('연인과 함께', 'fa-heart'),
                                                             ('친구와 함께', 'fa-user-group');