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

INSERT INTO `workout-notes` VALUES 
	(1,1,'First Workout',NULL),
	(2,1,'Came Up Early- Finished Early','2023-03-23'),
	(3,1,'Today was too crowded','2023-02-20'),
	(4,1,'Gym Closed Earlier Today','2023-01-21'),
	(5,1,'Had a Gym Budy Today','2023-04-24'),
	(6,3,'Today Was a Good Day','2023-03-24'),
	(7,3,'Yesterday was a Bad Toaday','2023-04-23'),
	(8,1,'Tommorow Will Be Better Day','2023-04-25');

