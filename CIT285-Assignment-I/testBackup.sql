-- MySQL dump 10.13  Distrib 5.6.20, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `SSN` varchar(11) DEFAULT NULL,
  `COURSE_NUMBER` varchar(6) DEFAULT NULL,
  `COURSE_NAME` varchar(40) DEFAULT NULL,
  `COURSE_INFO` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('123-45-6789','CMP100','Introduction to Computers','M 6:30-8:45 D211'),('123-45-6789','OIM220','Keybording I','TU 5:00-6:15 B101'),('123-45-6789','ENG111','English I','W   8:30 - 11:45 C202'),('014-56-6689','CMP100','Introduction to Computers','M 6:30-8:45 D211'),('014-56-6689','OIM220','Keybording I','TU 5:00-6:15 B101'),('014-56-6689','ENG111','English I','W   8:30 - 11:45 C202'),('567-88-9001','CMP100','Introduction to Computers','M 6:30-8:45 D211'),('567-88-9001','OIM220','Keybording I','TU 5:00-6:15 B101'),('567-88-9001','ENG111','English I','W   8:30 - 11:45 C202'),('333-71-5412','NC100','Basic Cookie Baking','M 6:00-8:00 E415'),('333-71-5412','NC200','Advanced Tire Inflation','W 6:00 - 8:00 D100'),('333-71-5412','NC300','Intro to Simusitis','W 6:00 - 8:00 B345'),('111-32-4545','CMP100','Introduction to Computers','M 6:30-8:45 D211'),('111-32-4545','OIM220','Keybording I','TU 5:00-6:15 B101'),('111-32-4545','ENG111','English I','W   8:30 - 11:45 C202'),('111-32-4545','CMP545','Web Programming','F 6:30-9:15 D117');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demographics`
--

DROP TABLE IF EXISTS `demographics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demographics` (
  `SSN` varchar(11) NOT NULL,
  `FIRST_NAME` varchar(15) DEFAULT NULL,
  `LAST_NAME` varchar(15) DEFAULT NULL,
  `MIDDLE_I` char(1) DEFAULT NULL,
  `ADDRESS` varchar(60) DEFAULT NULL,
  `CITY` varchar(15) DEFAULT NULL,
  `STATE` varchar(3) DEFAULT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `DATE` varchar(10) DEFAULT NULL,
  `YEAR` varchar(10) DEFAULT NULL,
  `DEGREE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demographics`
--

LOCK TABLES `demographics` WRITE;
/*!40000 ALTER TABLE `demographics` DISABLE KEYS */;
INSERT INTO `demographics` VALUES ('   -  -    ','','','','','','AL','','10/11/2014','Freshman','Associate of Science in Computer Programming'),('014-56-6689','Rusty','Gates','A','10 Jones Street','Medford','MA','02155','10/11/2014','Freshman','Associate of Arts in Humanities'),('111-32-4545','Red','Sails','S','123 Upham St','Malden','MA','02148','10/11/2014','Sophmore','Associate of Arts in Humanities'),('123-45-6789','Sally','Forth','A','100 Mason Street','Boston','MA','02111','10/11/2014','Junior','Associate of Science in Computer Programming'),('333-71-5412','Sandy','Beach','A','2 Judd Street','Everett','MA','02149',NULL,'Non-Matr.',NULL),('567-88-9001','Buck ','Passer','A','38 Lund Lane','Somerville','MA','02145','10/11/2014','Senior','Associate of Science in Computer Programming');
/*!40000 ALTER TABLE `demographics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financial_information`
--

DROP TABLE IF EXISTS `financial_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financial_information` (
  `SSN` varchar(11) DEFAULT NULL,
  `COURSE_NUMBER` varchar(6) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financial_information`
--

LOCK TABLES `financial_information` WRITE;
/*!40000 ALTER TABLE `financial_information` DISABLE KEYS */;
INSERT INTO `financial_information` VALUES ('123-45-6789','CMP100',285),('123-45-6789','OIM220',285),('123-45-6789','ENG111',285),('014-56-6689','CMP100',285),('014-56-6689','OIM220',285),('014-56-6689','ENG111',285),('567-88-9001','CMP100',300),('567-88-9001','OIM220',300),('567-88-9001','ENG111',300),('333-71-5412','NC100',150),('333-71-5412','NC200',150),('333-71-5412','NC300',150),('111-32-4545','CMP100',285),('111-32-4545','OIM220',285),('111-32-4545','ENG111',285),('111-32-4545','CMP545',265);
/*!40000 ALTER TABLE `financial_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-10 12:49:56
