-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `teacher_tb`
--

DROP TABLE IF EXISTS `teacher_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `age` tinyint(3) DEFAULT NULL,
  `can_teacher_area` varchar(200) DEFAULT NULL,
  `can_teacher_grade` varchar(200) DEFAULT NULL,
  `can_teacher_subject` varchar(200) DEFAULT NULL,
  `certification` varchar(200) DEFAULT NULL,
  `college` varchar(100) DEFAULT NULL,
  `create_at` varchar(50) NOT NULL,
  `update_at` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  `name` varchar(25) NOT NULL,
  `other_imports` varchar(200) DEFAULT NULL,
  `phone_num` varchar(11) DEFAULT NULL,
  `profession` varchar(100) DEFAULT NULL,
  `qq_num` varchar(20) DEFAULT NULL,
  `sex` tinyint(2) DEFAULT NULL,
  `teach_experience` varchar(200) DEFAULT NULL,
  `we_chat_num` varchar(100) DEFAULT NULL,
  `updated` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_num` (`phone_num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
