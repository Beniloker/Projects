SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

  -- ----------------------------
-- Table structure for item
-- ----------------------------
CREATE TABLE `item` (
                      `id` int NOT NULL AUTO_INCREMENT,
                      `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
                      `expire_date` datetime NOT NULL,
                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES (1, 'Mouse', '2022-11-01 14:46:38');
INSERT INTO `item` VALUES (2, 'Laptop', '2050-11-28 14:46:53');
INSERT INTO `item` VALUES (3, 'Book - The Standard Book of Spells Grade 1 – by Miranda Goshawk', '2055-11-28 14:52:14');
INSERT INTO `item` VALUES (4, 'Book - Madcap Magic for Wacky Warlocks', '2056-11-28 14:52:56');
INSERT INTO `item` VALUES (5, 'Book - The Invisible Book of Invisibility', '2030-11-28 14:53:56');
INSERT INTO `item` VALUES (6, 'Leviathan Axe', '2022-11-09 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for bid
-- ----------------------------
CREATE TABLE `bid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bidder_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `amount` int NOT NULL,
  `item_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of bid
-- ----------------------------
BEGIN;
INSERT INTO `bid` VALUES (1, 'Tom', 15000, 1);
INSERT INTO `bid` VALUES (2, 'Jerry', 15001, 1);
INSERT INTO `bid` VALUES (3, 'Steve Jobs', 99990, 2);
INSERT INTO `bid` VALUES (4, 'Bill Gates', 100900, 2);
INSERT INTO `bid` VALUES (5, 'Thor Odinson', 44444, 6);
INSERT INTO `bid` VALUES (6, 'Kratos', 66666, 6);
INSERT INTO `bid` VALUES (7, 'Santa Monica Studio', 123456789, 6);
COMMIT;


SET FOREIGN_KEY_CHECKS = 1;
