/*
Navicat MySQL Data Transfer

Source Server         : ali_mysql
Source Server Version : 50715
Source Host           : 101.132.229.69:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-11-22 18:58:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for artist
-- ----------------------------
DROP TABLE IF EXISTS `artist`;
CREATE TABLE `artist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `artistId` int(10) NOT NULL COMMENT '歌手Id',
  `name` varchar(100) NOT NULL COMMENT '歌手名称',
  `alia` varchar(100) DEFAULT NULL COMMENT '歌手别名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `artist_idx` (`artistId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌手表';
