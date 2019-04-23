SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `d_no` int(11) NOT NULL AUTO_INCREMENT,
  `d_name` varchar(50) DEFAULT NULL,
  `d_location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`d_no`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', 'ACCOUNTING', 'ShangHai');
INSERT INTO `dept` VALUES ('20', 'RESEARCH ', 'BeiJing ');
INSERT INTO `dept` VALUES ('30', 'SALES ', 'ShenZhen ');
INSERT INTO `dept` VALUES ('40', 'OPERATIONS ', 'FuJian ');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `e_no` int(11) NOT NULL,
  `e_name` varchar(100) NOT NULL,
  `e_gender` char(2) NOT NULL,
  `dept_no` int(11) NOT NULL,
  `e_job` varchar(100) NOT NULL,
  `e_salary` smallint(6) NOT NULL,
  `hireDate` date DEFAULT NULL,
  PRIMARY KEY (`e_no`),
  KEY `dno_fk` (`dept_no`),
  CONSTRAINT `dno_fk` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`d_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1001', 'SMITH', 'm', '20', 'CLERK', '800', '2005-11-12');
INSERT INTO `employee` VALUES ('1002', 'ALLEN', 'f', '30', 'SALESMAN', '1600', '2003-05-12');
INSERT INTO `employee` VALUES ('1003', 'WARD', 'f', '30', 'SALESMAN', '1250', '2003-05-12');
INSERT INTO `employee` VALUES ('1004', 'JONES', 'm', '20', 'MANAGER', '2975', '1998-05-18');
INSERT INTO `employee` VALUES ('1005', 'MARTIN', 'm', '30', 'SALESMAN', '1250', '2001-06-12');
INSERT INTO `employee` VALUES ('1006', 'BLAKE', 'f', '30', 'MANAGER', '2850', '1997-02-15');
INSERT INTO `employee` VALUES ('1007', 'CLARK', 'm', '10', 'MANAGER', '2450', '2002-09-12');
INSERT INTO `employee` VALUES ('1008', 'SCOTT', 'm', '20', 'ANALYST', '3000', '2003-05-12');
INSERT INTO `employee` VALUES ('1009', 'KING', 'f', '10', 'PRESIDENT', '5000', '1995-01-01');
INSERT INTO `employee` VALUES ('1010', 'TURNER', 'f', '30', 'SALESMAN', '1500', '1997-10-12');
INSERT INTO `employee` VALUES ('1011', 'ADAMS', 'm', '20', 'CLERK', '1100', '1999-10-05');
INSERT INTO `employee` VALUES ('1012', 'JAMES', 'm', '30', 'CLERK', '950', '2008-06-15');

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('1', '郭富城', '11', '男');
INSERT INTO `p_user` VALUES ('2', '范冰冰', '12', '女');
INSERT INTO `p_user` VALUES ('3', '刘德华', '13', '男');
INSERT INTO `p_user` VALUES ('4', '张学友', '14', '男');
INSERT INTO `p_user` VALUES ('5', 'klz', '25', '男');

-- ----------------------------
-- Table structure for test_rollup
-- ----------------------------
DROP TABLE IF EXISTS `test_rollup`;
CREATE TABLE `test_rollup` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) DEFAULT NULL COMMENT '标题',
  `uid` int(11) DEFAULT NULL COMMENT 'uid',
  `money` decimal(3,0) DEFAULT '0',
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_rollup
-- ----------------------------
INSERT INTO `test_rollup` VALUES ('2', '国庆节', '2', '12', '周伯通');
INSERT INTO `test_rollup` VALUES ('3', '这次是8天假哦', '3', '33', '老顽童');
INSERT INTO `test_rollup` VALUES ('4', '这是Uid=1的第一条数据哦', '1', '70', '欧阳锋');
INSERT INTO `test_rollup` VALUES ('5', '灵白山少主', '4', '99', '欧阳克');
INSERT INTO `test_rollup` VALUES ('7', '九阴真经创始人', '3', '12', '小顽童');
INSERT INTO `test_rollup` VALUES ('8', '双手互博', '2', '56', '周伯通');
INSERT INTO `test_rollup` VALUES ('9', '销魂掌', '2', '19', '周伯通');
INSERT INTO `test_rollup` VALUES ('10', '蛤蟆功', '1', '57', '欧阳锋');
INSERT INTO `test_rollup` VALUES ('11', '绝杀掌', '3', '800', '小顽童');
INSERT INTO `test_rollup` VALUES ('12', '九阴真经', '3', '84', '老顽童');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` bigint(20) NOT NULL,
  `fullName` varchar(64) NOT NULL,
  `userType` varchar(16) NOT NULL,
  `addedTime` datetime NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '爽爽', '普通', '2018-01-21 10:20:09');
INSERT INTO `t_user` VALUES ('2', '贵贵', '普通', '2017-11-06 10:20:22');
INSERT INTO `t_user` VALUES ('3', '芬芬', 'vip', '2017-11-13 10:20:42');
INSERT INTO `t_user` VALUES ('4', '思思', 'vip', '2018-01-21 10:20:55');
INSERT INTO `t_user` VALUES ('5', '妍妍', 'vip', '2017-09-17 10:21:28');
INSERT INTO `t_user` VALUES ('6', 'chy', 'vip', '2018-11-22 14:40:28');
INSERT INTO `t_user` VALUES ('7', 'wx', 'vip', '2018-11-22 14:55:28');

-- ----------------------------
-- Table structure for wolf
-- ----------------------------
DROP TABLE IF EXISTS `wolf`;
CREATE TABLE `wolf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wolf_name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  UNIQUE KEY `idKey` (`id`),
  UNIQUE KEY `username` (`wolf_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wolf
-- ----------------------------
INSERT INTO `wolf` VALUES ('1', 'chy', '22');
INSERT INTO `wolf` VALUES ('2', 'wx', '21');
INSERT INTO `wolf` VALUES ('3', 'a1', '1');
INSERT INTO `wolf` VALUES ('5', 'a2', '2');
INSERT INTO `wolf` VALUES ('7', 'a3', '3');
INSERT INTO `wolf` VALUES ('8', 'b1', '1');
INSERT INTO `wolf` VALUES ('9', 'b2', '2');
INSERT INTO `wolf` VALUES ('10', 'b3', '3');
INSERT INTO `wolf` VALUES ('11', 'c1', '1');
INSERT INTO `wolf` VALUES ('12', 'c2', '2');
INSERT INTO `wolf` VALUES ('13', 'c3', '3');
INSERT INTO `wolf` VALUES ('14', 'c4', '4');

-- ----------------------------
-- Table structure for zilianjie
-- ----------------------------
DROP TABLE IF EXISTS `zilianjie`;
CREATE TABLE `zilianjie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `lid` smallint(6) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zilianjie
-- ----------------------------
INSERT INTO `zilianjie` VALUES ('1', '彭金福', '0', '女', '10000');
INSERT INTO `zilianjie` VALUES ('2', '赵峰', '1', '女', '8000');
INSERT INTO `zilianjie` VALUES ('3', '陈海洋', '2', '男', '3000');
INSERT INTO `zilianjie` VALUES ('4', '庞伟', '2', '男', '7000');
INSERT INTO `zilianjie` VALUES ('5', '马贺飞', '0', '男', '8000');
INSERT INTO `zilianjie` VALUES ('6', '赵旭', '5', '男', '7000');
INSERT INTO `zilianjie` VALUES ('7', '张希功', '0', '男', '9000');
INSERT INTO `zilianjie` VALUES ('8', '李鑫', '7', '男', '5000');
INSERT INTO `zilianjie` VALUES ('9', '田嘉诚', '7', '男', '4000');

-- ----------------------------
-- Procedure structure for get_user_count
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_user_count`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `get_user_count`(IN sex_id INT, OUT user_count INT)
BEGIN
IF sex_id=0 THEN
SELECT COUNT(*) FROM github.p_user WHERE p_user.sex='女' INTO user_count;
ELSE
SELECT COUNT(*) FROM github.p_user WHERE p_user.sex='男' INTO user_count;
END IF;
    END
;;
DELIMITER ;
