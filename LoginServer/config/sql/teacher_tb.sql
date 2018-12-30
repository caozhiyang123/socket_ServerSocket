/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-12-30 14:20:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher_tb
-- ----------------------------
DROP TABLE IF EXISTS `teacher_tb`;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
