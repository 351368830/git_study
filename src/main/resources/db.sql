DROP TABLE IF EXISTS `test_db_sql`;
CREATE TABLE `test_db_sql`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41430 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;