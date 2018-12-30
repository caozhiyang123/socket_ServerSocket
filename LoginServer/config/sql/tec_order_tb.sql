/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-12-30 14:22:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tec_order_tb
-- ----------------------------
DROP TABLE IF EXISTS `tec_order_tb`;
CREATE TABLE `tec_order_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tec_id` int(20) NOT NULL DEFAULT '0',
  `ord_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
