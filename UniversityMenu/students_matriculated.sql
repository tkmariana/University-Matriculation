-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: students
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `matriculated`
--

DROP TABLE IF EXISTS `matriculated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matriculated` (
  `SSN` varchar(30) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `MiddleName` char(2) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `StreetAddress` varchar(100) NOT NULL,
  `City` varchar(30) NOT NULL,
  `State` varchar(30) NOT NULL,
  `ZipCode` int(6) NOT NULL,
  `TodaysDate` varchar(100) NOT NULL,
  `YearMatriculation` int(4) NOT NULL,
  `degree` varchar(30) NOT NULL,
  `highSchoolDiploma` tinyint(1) NOT NULL,
  `immunizationrecord` tinyint(1) NOT NULL,
  PRIMARY KEY (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matriculated`
--

LOCK TABLES `matriculated` WRITE;
/*!40000 ALTER TABLE `matriculated` DISABLE KEYS */;
INSERT INTO `matriculated` VALUES ('0145021','mariana','m','Agudelo','1122 saratofa st','boston','ma',2128,'12/19/19',2009,'Associate of science',1,1),('12','1','1','1','11','1','1',1,'1',1,'Associate of science',1,1),('123','jessica','m','mal','12 jop','boston','ma ',2111,'N',0,'NN',0,1),('1234','mariana','m','agudelo','56 elm st','boston','ma',2150,'12/16/19',2019,'Associate of science',1,1),('1234567','Luz','M','Agudelo','1122 saratoga st','boston','MA',2128,'12/12/19',2012,'Associate of science',1,1);
/*!40000 ALTER TABLE `matriculated` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 19:50:22
