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
-- Table structure for song_tb
-- ----------------------------
DROP TABLE IF EXISTS `song_tb`;
CREATE TABLE `song_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation` varchar(200) DEFAULT NULL,
  `title` varchar(200)  DEFAULT NULL,
  `singer`varchar(200)  DEFAULT NULL,
  `album`varchar(200)  DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `local_path` varchar(200) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
