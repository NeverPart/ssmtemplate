/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : ssmtemplate

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-05-13 17:03:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer
-- ----------------------------
INSERT INTO `tb_customer` VALUES ('1', '涂陌', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('2', '逗瓜', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('3', '愤青', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('4', '咸鱼', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('5', '小白', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('6', '菜鸡', '123456789', '你猜', '不想写备注');
INSERT INTO `tb_customer` VALUES ('7', '张三', '111111', '南昌', '你好');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin');
