/*
Navicat MySQL Data Transfer

Source Server         : ali_mysql
Source Server Version : 50715
Source Host           : 101.132.229.69:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-11-22 18:58:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for relation_artlist_music
-- ----------------------------
DROP TABLE IF EXISTS `relation_artlist_music`;
CREATE TABLE `relation_artlist_music` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `artistId` int(10) NOT NULL COMMENT '歌手Id',
  `musicId` int(10) NOT NULL COMMENT '音乐Id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `artlist_music_idx` (`artistId`,`musicId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌手_音乐关系表';
