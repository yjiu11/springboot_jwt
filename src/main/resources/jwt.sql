/*
Navicat MySQL Data Transfer

Source Server         : MySQL_3306
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : jwt

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-12-06 09:34:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'test', '05a671c66aefea124cc08b76ea6d30bb', 'test', '1');
