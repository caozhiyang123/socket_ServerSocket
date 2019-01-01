/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-01-01 21:10:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tec_order_tb
-- ----------------------------
DROP TABLE IF EXISTS `tec_order_tb`;
CREATE TABLE `tec_order_tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tec_id` int(20) NOT NULL DEFAULT 0,
  `ord_id` varchar(20) NOT NULL,
  `start_teach_time` varchar(20) NOT NULL,
  `is_parent_payed` tinyint(2) NOT NULL DEFAULT 0,
  `parent_cost` int(6) NOT NULL DEFAULT 0,  
  `is_teacher_payed` tinyint(2) NOT NULL DEFAULT 0,
  `teacher_cost` int(6) NOT NULL DEFAULT 0,
  `create_at` varchar(20) NOT NULL,
  `update_at` varchar(20) NOT NULL,
  `updated` int(10) NOT NULL DEFAULT 0,
  `used` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
