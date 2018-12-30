/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-12-30 14:23:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_edu
-- ----------------------------
DROP TABLE IF EXISTS `order_edu`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
