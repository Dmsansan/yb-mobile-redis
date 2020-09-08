/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : mobile-redis

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 08/09/2020 16:46:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for red_packet_info
-- ----------------------------
DROP TABLE IF EXISTS `red_packet_info`;
CREATE TABLE `red_packet_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `red_packet_id` bigint(11) NOT NULL DEFAULT 0 COMMENT '红包id，采⽤\ntimestamp+5位随机数',
  `total_amount` int(11) NOT NULL DEFAULT 0 COMMENT '红包总⾦额，单位分',
  `total_packet` int(11) NOT NULL DEFAULT 0 COMMENT '红包总个数',
  `remaining_amount` int(11) NOT NULL DEFAULT 0 COMMENT '剩余红包⾦额，单位\n分',
  `remaining_packet` int(11) NOT NULL DEFAULT 0 COMMENT '剩余红包个数',
  `uid` int(20) NOT NULL DEFAULT 0 COMMENT '新建红包⽤户的⽤户标识',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '红包信息\n表，新建⼀个红包插⼊⼀条记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of red_packet_info
-- ----------------------------
INSERT INTO `red_packet_info` VALUES (8, 1598373426917, 20000, 10, 20000, 10, 10086, '2020-08-26 00:37:07', '2020-08-26 00:37:06');
INSERT INTO `red_packet_info` VALUES (9, 1599443138593, 0, 0, 1000, 20, 10086, '2020-09-08 16:36:21', '2020-09-08 16:36:21');

-- ----------------------------
-- Table structure for red_packet_record
-- ----------------------------
DROP TABLE IF EXISTS `red_packet_record`;
CREATE TABLE `red_packet_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '抢到红包的⾦额',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '抢到红包的⽤户的⽤户\n名',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '抢到红包的⽤户的头像',
  `uid` int(20) NOT NULL DEFAULT 0 COMMENT '抢到红包⽤户的⽤户标识',
  `red_packet_id` bigint(11) NOT NULL DEFAULT 0 COMMENT '红包id，采⽤\ntimestamp+5位随机数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抢红包记\n录表，抢⼀个红包插⼊⼀条记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of red_packet_record
-- ----------------------------
INSERT INTO `red_packet_record` VALUES (46, 464, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (47, 3704, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (48, 511, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (49, 4152, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (50, 168, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (51, 2116, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (52, 95, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (53, 1413, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (54, 5924, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (55, 1453, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1598373426917, '2020-08-26 00:47:14', '2020-08-26 00:47:13');
INSERT INTO `red_packet_record` VALUES (56, 62, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-07 09:49:31', '2020-09-07 09:49:31');
INSERT INTO `red_packet_record` VALUES (57, 51, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-07 09:50:52', '2020-09-07 09:50:52');
INSERT INTO `red_packet_record` VALUES (58, 7, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 09:37:19', '2020-09-08 09:37:19');
INSERT INTO `red_packet_record` VALUES (59, 3, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:10:33', '2020-09-08 16:10:33');
INSERT INTO `red_packet_record` VALUES (60, 22, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:12:51', '2020-09-08 16:12:51');
INSERT INTO `red_packet_record` VALUES (61, 83, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:25:36', '2020-09-08 16:25:36');
INSERT INTO `red_packet_record` VALUES (62, 0, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:28:33', '2020-09-08 16:28:33');
INSERT INTO `red_packet_record` VALUES (63, 75, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:35:55', '2020-09-08 16:35:55');
INSERT INTO `red_packet_record` VALUES (64, 8, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:02', '2020-09-08 16:36:02');
INSERT INTO `red_packet_record` VALUES (65, 48, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:04', '2020-09-08 16:36:04');
INSERT INTO `red_packet_record` VALUES (66, 12, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:05', '2020-09-08 16:36:05');
INSERT INTO `red_packet_record` VALUES (67, 53, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:06', '2020-09-08 16:36:06');
INSERT INTO `red_packet_record` VALUES (68, 77, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:06', '2020-09-08 16:36:06');
INSERT INTO `red_packet_record` VALUES (69, 70, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:07', '2020-09-08 16:36:07');
INSERT INTO `red_packet_record` VALUES (70, 85, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:07', '2020-09-08 16:36:07');
INSERT INTO `red_packet_record` VALUES (71, 72, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:08', '2020-09-08 16:36:08');
INSERT INTO `red_packet_record` VALUES (72, 28, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:09', '2020-09-08 16:36:09');
INSERT INTO `red_packet_record` VALUES (73, 77, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:09', '2020-09-08 16:36:09');
INSERT INTO `red_packet_record` VALUES (74, 75, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:10', '2020-09-08 16:36:10');
INSERT INTO `red_packet_record` VALUES (75, 58, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:10', '2020-09-08 16:36:10');
INSERT INTO `red_packet_record` VALUES (76, 28, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:11', '2020-09-08 16:36:11');
INSERT INTO `red_packet_record` VALUES (77, 68, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:12', '2020-09-08 16:36:12');
INSERT INTO `red_packet_record` VALUES (78, 5, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:17', '2020-09-08 16:36:17');
INSERT INTO `red_packet_record` VALUES (79, 56, '陈彦斌', 'https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg', 10086, 1599443138593, '2020-09-08 16:36:21', '2020-09-08 16:36:21');

SET FOREIGN_KEY_CHECKS = 1;
