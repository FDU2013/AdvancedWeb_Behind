/*
 Navicat Premium Data Transfer

 Source Server         : zjxDesktop
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : adweb

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 12/06/2023 10:40:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_num` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_6qx4ifat9nj13lt1lqmxigllc`(`stu_num`) USING BTREE,
  UNIQUE INDEX `UK_53lyyc8d8ig635u2bp1gbqst`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, 'zjx', 'User', '20302010032', 'zjx');
INSERT INTO `accounts` VALUES (2, 'wjf', 'User', '20307130082', 'wjf');

-- ----------------------------
-- Table structure for cd_key
-- ----------------------------
DROP TABLE IF EXISTS `cd_key`;
CREATE TABLE `cd_key`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `get_time` datetime(0) NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods` bigint(0) NULL DEFAULT NULL,
  `user` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKcesf8gmslw3fo3hrxkki5j418`(`goods`) USING BTREE,
  INDEX `FKs7155yt7awcmumt0xecbdtxil`(`user`) USING BTREE,
  CONSTRAINT `FKcesf8gmslw3fo3hrxkki5j418` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKs7155yt7awcmumt0xecbdtxil` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_key
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `cost` int(0) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 50, '300克高档卡纸，插套塑封设计，外壳覆膜防磨损', 'https://s1.ax1x.com/2023/06/11/pCVxiv9.jpg', '梵高明信片');
INSERT INTO `goods` VALUES (2, 100, '集智慧与可爱于一身', 'https://s1.ax1x.com/2023/06/11/pCVxQvd.jpg', '胖艺术家盲盒');
INSERT INTO `goods` VALUES (3, 50, '创意艺术可记事台历', 'https://s1.ax1x.com/2023/06/11/pCVxwvj.jpg', '油画台历');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sequence` int(0) NULL DEFAULT NULL,
  `option_aa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_ba` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `option_ca` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `topic` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKji956s8yddymekjn3s7xxy438`(`topic`) USING BTREE,
  CONSTRAINT `FKji956s8yddymekjn3s7xxy438` FOREIGN KEY (`topic`) REFERENCES `topic` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'A', 'https://s1.ax1x.com/2023/06/11/pCVXZE8.jpg', 1, '文艺复兴时期', '哥特时期', '巴洛克时期', '书房中的圣哲罗姆的作者\r\n安托内罗·达·梅西那是以下哪个时期的艺术家？', 1);
INSERT INTO `question` VALUES (2, 'B', 'https://s1.ax1x.com/2023/06/11/pCVXQvn.jpg', 2, '治愈遭诅咒的年轻人', '死而复生的妇女', '盲人复明', '桑德罗·波提切利的作品《圣泽诺比乌斯的三个奇迹》没有描绘了以下哪个故事？', 1);
INSERT INTO `question` VALUES (3, 'A', 'https://s1.ax1x.com/2023/06/11/pCVXeUS.jpg', 3, '神圣的爱和耶稣的受难c', '神圣的爱和耶稣的受难', '圣母的忧郁情绪', '拉斐尔的作品《圣母子与施洗者圣约翰（加瓦圣母）》中，康乃馨象征着什么？', 1);
INSERT INTO `question` VALUES (4, 'A', 'https://s1.ax1x.com/2023/06/11/pCVX3D0.jpg', 4, '父亲失落的王国', '画家的名字', '地理位置的谜题', '扬·戈塞特的作品《年轻公主（丹麦多萝西娅？）》中，小女孩手拿的浑天仪可能暗指什么？', 1);
INSERT INTO `question` VALUES (5, 'C', 'https://s1.ax1x.com/2023/06/11/pCVXK3j.jpg', 5, '帮助她的妹妹玛莎准备食物', '打扫厨房', '倾听耶稣的教导', '在画作《四元素：火》中，玛丽正在做什么？', 1);
INSERT INTO `question` VALUES (6, 'A', 'https://s1.ax1x.com/2023/06/11/pCVXkut.jpg', 6, '耶稣复活后向信徒显现的场景', '耶稣行走在水面上的场景', '稣将水变成酒的场景', '在画作《四元素：水》中，背景中的场景描绘了以下哪个圣经奇迹？', 1);
INSERT INTO `question` VALUES (7, 'B', 'https://s1.ax1x.com/2023/06/11/pCVXm4g.jpg', 7, '对宗教主题和神秘元素的强调', '对日常生活主题的描绘和重视观察自然', '对古典美学和理想化形象的追求', '《被蜥蜴咬伤的男孩》的作者卡拉瓦乔的作品在艺术史上的主要特点是：', 1);
INSERT INTO `question` VALUES (8, 'A', 'https://s1.ax1x.com/2023/06/11/pCVXPgA.jpg', 8, '他们的财富和精致生活', '他们的海外旅行经历', '他们的宗教信仰', '《约翰·斯图亚特勋爵与其兄弟》这幅画中的约翰·斯图亚特勋爵和伯纳德·斯图亚特勋爵的特点是：', 1);
INSERT INTO `question` VALUES (9, 'C', 'https://s1.ax1x.com/2023/06/11/pCVXMgs.jpg', 9, '方尖塔桥c', '方尖塔桥', '圣格雷米亚教堂的钟楼', '《威尼斯：卡纳雷吉欧区入口》这幅画中最明显的建筑物是：', 1);
INSERT INTO `question` VALUES (10, 'A', 'https://s1.ax1x.com/2023/06/11/pCVXijI.jpg', 10, '乔瓦尼·安东尼奥·卡纳尔', '安东尼·凡·戴克', '扬·戈塞特', '《威尼斯：城堡区圣伯多禄圣殿》的作者是？', 1);
INSERT INTO `question` VALUES (11, 'C', 'https://s1.ax1x.com/2023/06/11/pCVX1uq.jpg', 11, '小餐馆中的顾客用餐', '花园音乐会的现场表演', '咖啡馆的女服务员端着许多杯啤酒', '在爱德华·马奈的画作《咖啡厅演奏会的一角》中，画面主要描绘了以下哪种场景？', 1);
INSERT INTO `question` VALUES (12, 'B', 'https://s1.ax1x.com/2023/06/11/pCVXEHf.jpg', 12, '《红衣男孩》是劳伦斯爵士最后一幅作品。', '《红衣男孩》是英国绘画的代表作之一。', '《红衣男孩》是英国国家美术馆的藏品中最小的画作。', '以下哪一项是关于《红衣男孩》的真实陈述？', 1);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_mbunn9erv8nmf5lk1r2nu0nex`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '画展');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `credit` int(0) NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `today` int(0) NULL DEFAULT NULL,
  `account` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKm89052yp1pj6limcmp7m0838y`(`account`) USING BTREE,
  CONSTRAINT `FKm89052yp1pj6limcmp7m0838y` FOREIGN KEY (`account`) REFERENCES `accounts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 50, '20302010032@fudan.edu.cn', 'https://s1.ax1x.com/2023/06/11/pCVzwFK.jpg', '张佳洵', '13906638321', 50, 1);
INSERT INTO `users` VALUES (2, 100, '20307030082@fudan.edu.cn', 'https://s1.ax1x.com/2023/06/11/pCVzwFK.jpg', '王骏飞', '13706638321', 0, 2);

SET FOREIGN_KEY_CHECKS = 1;
