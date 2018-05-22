CREATE DATABASE IF NOT EXISTS `thanos_reservations`;

USE `thanos_reservations`;


CREATE TABLE IF NOT EXISTS `user_type` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(45) NOT NULL,
	`defaul_permission` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `block` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(45) NOT NULL,
	`num_room` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `classroom` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(45) NOT NULL,
	`capacity` INT(11) NOT NULL DEFAULT '0',
	`computer` CHAR(1) NOT NULL DEFAULT 'N',
	`video_beams` CHAR(1) NOT NULL DEFAULT 'N',
	`block_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_classroom_block_idx` (`block_id`),
	CONSTRAINT `fk_classroom_block` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `person` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL,
	`last_name` VARCHAR(45) NULL DEFAULT NULL,
	`code` VARCHAR(15) NOT NULL,
	`email` VARCHAR(45) NOT NULL,
	`phone` VARCHAR(15) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `reservation` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`date_init` VARCHAR(45) NOT NULL,
	`date_end` VARCHAR(45) NOT NULL,
	`observations` TEXT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user` VARCHAR(45) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`permissions` VARCHAR(100) NULL DEFAULT NULL,
	`user_type_id` INT(11) NOT NULL,
	`person_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_user_user_type1_idx` (`user_type_id`),
	INDEX `fk_user_person1_idx` (`person_id`),
	CONSTRAINT `fk_user_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `fk_user_user_type1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `user_reservation` (
	`user_id` INT(11) NOT NULL,
	`classroom_id` INT(11) NOT NULL,
	`reservation_id` INT(11) NOT NULL,
	INDEX `fk_user_reservation_user1_idx` (`user_id`),
	INDEX `fk_user_reservation_classroom1_idx` (`classroom_id`),
	INDEX `fk_user_reservation_reservation1_idx` (`reservation_id`),
	CONSTRAINT `fk_user_reservation_classroom1` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `fk_user_reservation_reservation1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `fk_user_reservation_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `audit` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user` VARCHAR(45) NOT NULL,
	`description` VARCHAR(200) NULL DEFAULT '',
	`datetime` VARCHAR(45) NOT NULL DEFAULT '0000-00-00 00:00:00',
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (1, '30', 12);
INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (2, '31', 24);
INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (3, '32', 25);

INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (1, '301', 30, 'N', 'N', 2);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (2, '101', 29, 'N', 'N', 2);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (6, '202', 22, 'N', 'N', 1);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (7, '203', 29, 'N', 'N', 1);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (3, '102', 29, 'N', 'N', 2);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (4, '102', 29, 'N', 'N', 3);
INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (5, '402', 30, 'N', 'N', 2);

INSERT INTO `person` (`id`, `name`, `last_name`, `code`, `email`, `phone`) VALUES (1, 'Juan', 'Admin', '', 'jarbol18@gmail.com', NULL);
INSERT INTO `person` (`id`, `name`, `last_name`, `code`, `email`, `phone`) VALUES (2, 'Juan', 'Martin', '215441545475', 'ju@gmail.com', NULL);

INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (1, 'Admin', 'YYYYY');
INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (2, 'Student', 'YYYNN');
INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (3, 'Teacher', 'YYYYN');

INSERT INTO `user` (`id`, `user`, `password`, `permissions`, `user_type_id`, `person_id`) VALUES (1, 'admin', 'admin', 'YYYY', 1, 1);
INSERT INTO `user` (`id`, `user`, `password`, `permissions`, `user_type_id`, `person_id`) VALUES (2, 'student', 'student', 'YYNN', 2, 2);




