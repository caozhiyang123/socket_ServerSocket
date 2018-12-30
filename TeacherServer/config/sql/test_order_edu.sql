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
-- Table structure for table `order_edu`
--

DROP TABLE IF EXISTS `order_edu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_edu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(30) DEFAULT NULL,
  `cost` varchar(10) DEFAULT NULL,
  `create_at` varchar(50) NOT NULL,
  `message_resource` varchar(200) DEFAULT NULL,
  `order_id` varchar(5) NOT NULL,
  `other_importants` varchar(40) DEFAULT NULL,
  `parents_name` varchar(10) DEFAULT NULL,
  `phone_num` varchar(11) DEFAULT NULL,
  `qq_num` varchar(20) DEFAULT NULL,
  `student_age` tinyint(3) DEFAULT NULL,
  `student_grade` varchar(10) DEFAULT NULL,
  `student_name` varchar(10) DEFAULT NULL,
  `student_sex` tinyint(2) DEFAULT NULL,
  `student_subject` varchar(20) DEFAULT NULL,
  `we_chat_num` varchar(100) DEFAULT NULL,
  `update_at` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
