package org.thanos.modelo.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.thanos.connection.Conexion;

public class ModelRepository {
	public static ArrayList<String> SQL = new ArrayList<>();
	
	public static void CreateTables() throws SQLException {
		SQL.add("CREATE TABLE IF NOT EXISTS `user_type` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`description` VARCHAR(45) NOT NULL,\r\n" + 
				"	`defaul_permission` VARCHAR(100) NULL DEFAULT NULL,\r\n" + 
				"	PRIMARY KEY (`id`)\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `block` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`description` VARCHAR(45) NOT NULL,\r\n" + 
				"	`num_room` INT(11) NULL DEFAULT NULL,\r\n" + 
				"	PRIMARY KEY (`id`)\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `classroom` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`description` VARCHAR(45) NOT NULL,\r\n" + 
				"	`capacity` INT(11) NOT NULL DEFAULT '0',\r\n" + 
				"	`computer` CHAR(1) NOT NULL DEFAULT 'N',\r\n" + 
				"	`video_beams` CHAR(1) NOT NULL DEFAULT 'N',\r\n" + 
				"	`block_id` INT(11) NOT NULL,\r\n" + 
				"	PRIMARY KEY (`id`),\r\n" + 
				"	INDEX `fk_classroom_block_idx` (`block_id`),\r\n" + 
				"	CONSTRAINT `fk_classroom_block` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `person` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`name` VARCHAR(45) NOT NULL,\r\n" + 
				"	`last_name` VARCHAR(45) NULL DEFAULT NULL,\r\n" + 
				"	`code` VARCHAR(15) NOT NULL,\r\n" + 
				"	`email` VARCHAR(45) NOT NULL,\r\n" + 
				"	`phone` VARCHAR(15) NULL DEFAULT NULL,\r\n" + 
				"	PRIMARY KEY (`id`)\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `reservation` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`date_init` VARCHAR(45) NOT NULL,\r\n" + 
				"	`date_end` VARCHAR(45) NOT NULL,\r\n" + 
				"	`observations` TEXT NULL,\r\n" + 
				"	PRIMARY KEY (`id`)\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `user` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`user` VARCHAR(45) NOT NULL,\r\n" + 
				"	`password` VARCHAR(50) NOT NULL,\r\n" + 
				"	`permissions` VARCHAR(100) NULL DEFAULT NULL,\r\n" + 
				"	`user_type_id` INT(11) NOT NULL,\r\n" + 
				"	`person_id` INT(11) NOT NULL,\r\n" + 
				"	PRIMARY KEY (`id`),\r\n" + 
				"	INDEX `fk_user_user_type1_idx` (`user_type_id`),\r\n" + 
				"	INDEX `fk_user_person1_idx` (`person_id`),\r\n" + 
				"	CONSTRAINT `fk_user_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,\r\n" + 
				"	CONSTRAINT `fk_user_user_type1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;\r\n" + 
				"");
		SQL.add("CREATE TABLE IF NOT EXISTS `user_reservation` (\r\n" + 
				"	`user_id` INT(11) NOT NULL,\r\n" + 
				"	`classroom_id` INT(11) NOT NULL,\r\n" + 
				"	`reservation_id` INT(11) NOT NULL,\r\n" + 
				"	INDEX `fk_user_reservation_user1_idx` (`user_id`),\r\n" + 
				"	INDEX `fk_user_reservation_classroom1_idx` (`classroom_id`),\r\n" + 
				"	INDEX `fk_user_reservation_reservation1_idx` (`reservation_id`),\r\n" + 
				"	CONSTRAINT `fk_user_reservation_classroom1` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,\r\n" + 
				"	CONSTRAINT `fk_user_reservation_reservation1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,\r\n" + 
				"	CONSTRAINT `fk_user_reservation_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		SQL.add("CREATE TABLE IF NOT EXISTS `audit` (\r\n" + 
				"	`id` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"	`user` VARCHAR(45) NOT NULL,\r\n" + 
				"	`description` VARCHAR(200) NULL DEFAULT '',\r\n" + 
				"	`datetime` VARCHAR(45) NOT NULL DEFAULT '0000-00-00 00:00:00',\r\n" + 
				"	PRIMARY KEY (`id`)\r\n" + 
				")\r\n" + 
				"COLLATE='latin1_swedish_ci'\r\n" + 
				"ENGINE=InnoDB;");
		
		SQL.add("INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (1, '30', 12);");
		SQL.add("INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (2, '31', 24);");
		SQL.add("INSERT INTO `block` (`id`, `description`, `num_room`) VALUES (3, '32', 25);");

		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (1, '301', 30, 'N', 'N', 2);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (2, '101', 29, 'N', 'N', 2);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (6, '202', 22, 'N', 'N', 1);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (7, '203', 29, 'N', 'N', 1);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (3, '102', 29, 'N', 'N', 2);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (4, '102', 29, 'N', 'N', 3);");
		SQL.add("INSERT INTO `classroom` (`id`, `description`, `capacity`, `computer`, `video_beams`, `block_id`) VALUES (5, '402', 30, 'N', 'N', 2);");

		SQL.add("INSERT INTO `person` (`id`, `name`, `last_name`, `code`, `email`, `phone`) VALUES (1, 'Juan', 'Admin', '', 'jarbol18@gmail.com', NULL);");
		SQL.add("INSERT INTO `person` (`id`, `name`, `last_name`, `code`, `email`, `phone`) VALUES (2, 'Juan', 'Martin', '215441545475', 'ju@gmail.com', NULL);");

		SQL.add("INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (1, 'Admin', 'YYYYY');");
		SQL.add("INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (2, 'Student', 'YYYNN');");
		SQL.add("INSERT INTO `user_type` (`id`, `description`, `defaul_permission`) VALUES (3, 'Teacher', 'YYYYN');");

		SQL.add("INSERT INTO `user` (`id`, `user`, `password`, `permissions`, `user_type_id`, `person_id`) VALUES (1, 'admin', 'admin', 'YYYY', 1, 1);");
		SQL.add("INSERT INTO `user` (`id`, `user`, `password`, `permissions`, `user_type_id`, `person_id`) VALUES (2, 'student', 'student', 'YYNN', 2, 2);");
		
		Connection conn = Conexion.getConexion();
        Statement cn = conn.createStatement();
        for(int i=0;i<SQL.size();i++) {
        	cn.executeUpdate(SQL.get(i));
        }
		
	}
}
