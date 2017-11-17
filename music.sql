/*
Navicat MySQL Data Transfer

Source Server         : ali_mysql
Source Server Version : 50715
Source Host           : 101.132.229.69:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-11-17 16:20:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `songId` varchar(10) NOT NULL COMMENT '歌曲Id',
  `name` varchar(400) NOT NULL COMMENT '歌名',
  `author` varchar(100) DEFAULT NULL COMMENT '歌手名',
  `album` varchar(50) DEFAULT NULL COMMENT '专辑',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='音乐表';
