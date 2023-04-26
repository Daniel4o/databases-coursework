CREATE DATABASE  IF NOT EXISTS `workout_notes_directory`;
USE `workout_notes_directory`;


DROP TABLE IF EXISTS `workout-notes`;
DROP TABLE IF EXISTS `workout-notes-old`;

CREATE TABLE `workout-notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_id` int NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `deactive` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `workout-notes-old` (
  `id` int NOT NULL AUTO_INCREMENT,
  `object_id` int NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `deactive` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

