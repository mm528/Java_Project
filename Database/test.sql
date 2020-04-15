# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.24)
# Database: test
# Generation Time: 2015-05-15 02:13:23 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Attendance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Attendance`;

CREATE TABLE `Attendance` (
  `userID` smallint(255) NOT NULL,
  `eventID` int(255) NOT NULL,
  `attending` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userID`,`eventID`),
  KEY `Attendance_FK2` (`eventID`),
  CONSTRAINT `Attendance_FK` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`),
  CONSTRAINT `Attendance_FK2` FOREIGN KEY (`eventID`) REFERENCES `Event` (`eventID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;

INSERT INTO `Attendance` (`userID`, `eventID`, `attending`)
VALUES
	(1,3,NULL),
	(2,3,NULL),
	(2,4,NULL);

/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Event`;

CREATE TABLE `Event` (
  `eventID` int(255) NOT NULL AUTO_INCREMENT,
  `userID` smallint(255) NOT NULL,
  `eventName` varchar(150) NOT NULL DEFAULT '',
  `eventDescription` varchar(1000) NOT NULL DEFAULT '',
  `eventDate` varchar(10) NOT NULL DEFAULT '',
  `startTime` varchar(8) NOT NULL DEFAULT '',
  `endTime` varchar(8) NOT NULL DEFAULT '',
  `location` varchar(10) NOT NULL DEFAULT '',
  `allDayEvent` tinyint(1) NOT NULL,
  PRIMARY KEY (`eventID`),
  KEY `userID` (`userID`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;

INSERT INTO `Event` (`eventID`, `userID`, `eventName`, `eventDescription`, `eventDate`, `startTime`, `endTime`, `location`, `allDayEvent`)
VALUES
	(3,2,'Work Meeting','Some kind of activities...','2015-04-27','08:00:00','16:00:00','WQ565',0),
	(4,2,'Work Meeting','Some kind of activities...','2015-04-29','08:00:00','16:00:00','WQ565',0),
	(5,2,'Work Meeting','Some kind of activities...','2015-04-29','08:00:00','16:00:00','WQ565',0),
	(6,2,'Testing','...','2015-04-29','08:00:00','16:00:00','WQ565',0);

/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Notification`;

CREATE TABLE `Notification` (
  `eventID` int(255) NOT NULL,
  `userID` smallint(11) NOT NULL,
  PRIMARY KEY (`userID`,`eventID`),
  KEY `eventID` (`eventID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`eventID`) REFERENCES `Event` (`eventID`),
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;

INSERT INTO `Notification` (`eventID`, `userID`)
VALUES
	(3,1),
	(4,2);

/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Reminder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Reminder`;

CREATE TABLE `Reminder` (
  `userID` smallint(255) NOT NULL,
  `eventID` int(255) NOT NULL,
  `reminderTime` tinyint(11) NOT NULL,
  PRIMARY KEY (`userID`,`eventID`,`reminderTime`),
  KEY `Reminder_FK2` (`eventID`),
  CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `User` (`userID`),
  CONSTRAINT `reminder_ibfk_2` FOREIGN KEY (`eventID`) REFERENCES `Event` (`eventID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table User
# ------------------------------------------------------------

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `userID` smallint(255) NOT NULL AUTO_INCREMENT,
  `isSuperUser` tinyint(1) NOT NULL,
  `userName` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `forename` varchar(100) NOT NULL DEFAULT '',
  `surname` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(150) NOT NULL DEFAULT '',
  `phoneNum` int(20) NOT NULL,
  `officeNum` int(20) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userNameConstraint` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;

INSERT INTO `User` (`userID`, `isSuperUser`, `userName`, `password`, `forename`, `surname`, `email`, `phoneNum`, `officeNum`)
VALUES
	(1,0,'User1','1234','Darren','VissionWard','email@gmail.com',734455345,NULL),
	(2,0,'User02','evrevev','Sean','Keathing','email2@gmail.com',734323423,NULL),
	(12,0,'User03','evrevev','fwefw','Keathing','email2@gmail.com',734323423,NULL);

/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping routines (PROCEDURE) for database 'test'
--
DELIMITER ;;

# Dump of PROCEDURE create_event
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `create_event` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`127.0.0.1`*/ /*!50003 PROCEDURE `create_event`(IN pr_userID smallint(255),
  														   IN pr_eventName varchar(150),
  														   IN pr_eventDescription varchar(150),
  														   IN pr_eventDate date,
  														   IN pr_startTime time,
  														   IN pr_endTime time,
  														   IN pr_location varchar(10),
  														   IN pr_allDayEvent tinyint(1))
BEGIN
  INSERT INTO `Event` (userID,eventName,eventDescription,eventDate,startTime,endTime,location,allDayEvent)
  VALUES (pr_userID,pr_eventName,pr_eventDescription,pr_eventDate,pr_startTime,pr_endTime,pr_location,pr_allDayEvent);
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE delete_event
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `delete_event` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`127.0.0.1`*/ /*!50003 PROCEDURE `delete_event`(IN pr_eventID int(255),
								IN pr_userID smallint(255))
BEGIN
	DELETE FROM `Notification`
	WHERE eventID = pr_eventID;
	DELETE FROM `Reminder`
	WHERE eventID = pr_eventID;
	DELETE FROM `Attendance`
	WHERE eventID = pr_eventID;
  	DELETE FROM `Event`
  	WHERE eventID = pr_eventID AND userID = pr_userID;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE delete_invite
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `delete_invite` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`127.0.0.1`*/ /*!50003 PROCEDURE `delete_invite`(IN pr_userID smallint(255),
								 IN pr_eventID int(255))
BEGIN
	DELETE FROM `Reminder`
	WHERE userID = pr_userID && eventID = pr_eventID;
	DELETE FROM `Attendance`
	WHERE userID = pr_userID && eventID = pr_eventID;
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE insert_user
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `insert_user` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`127.0.0.1`*/ /*!50003 PROCEDURE `insert_user`(IN pr_isSuperUser tinyint(1),
  														  IN pr_userName varchar(100),
  														  IN pr_password varchar(100),
  														  IN pr_forename varchar(100),
  														  IN pr_surname varchar(100),
 														  IN pr_email varchar(150),
  														  IN pr_phoneNum int(20),
  														  IN pr_officeNum int(20))
BEGIN
  INSERT INTO `User` (isSuperUser,userName,`password`,forename,surname,email,phoneNum,officeNum)
  VALUES (pr_isSuperUser,pr_userName,pr_password,pr_forename,pr_surname,pr_email,pr_phoneNum,pr_officeNum);
END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
