/*
Navicat MySQL Data Transfer

Source Server         : ali_mysql
Source Server Version : 50715
Source Host           : 101.132.229.69:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-11-17 16:20:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `commentId` varchar(10) NOT NULL COMMENT '评论Id',
  `songId` varchar(10) NOT NULL COMMENT '歌曲Id',
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `linkedCount` int(10) NOT NULL COMMENT '点赞数',
  `content` varchar(500) DEFAULT NULL COMMENT '专辑',
  `time` varchar(15) DEFAULT NULL COMMENT '评论时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';
