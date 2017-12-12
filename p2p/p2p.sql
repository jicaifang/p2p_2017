/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : p2p

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2017-11-11 17:39:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `tradePassword` varchar(255) DEFAULT NULL,
  `usableAmount` decimal(18,4) NOT NULL,
  `freezedAmount` decimal(18,4) NOT NULL,
  `remainBorrowLimit` decimal(18,4) NOT NULL,
  `version` int(11) NOT NULL,
  `unReceiveInterest` decimal(18,4) NOT NULL,
  `unReceivePrincipal` decimal(18,4) NOT NULL,
  `unReturnAmount` decimal(18,4) NOT NULL,
  `borrowLimit` decimal(18,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('24', '', '5148.9167', '5000.0000', '5000.0000', '18', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('25', '1111', '9469.0727', '0.0000', '5000.0000', '14', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('26', '', '5148.9178', '0.0000', '5000.0000', '17', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('27', '', '0.0000', '0.0000', '5000.0000', '0', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('28', '', '0.0000', '0.0000', '5000.0000', '0', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('29', '', '0.0000', '0.0000', '5000.0000', '0', '0.0000', '0.0000', '0.0000', '5000.0000');
INSERT INTO `account` VALUES ('42', '', '0.0000', '0.0000', '5000.0000', '0', '0.0000', '0.0000', '0.0000', '5000.0000');

-- ----------------------------
-- Table structure for accountflow
-- ----------------------------
DROP TABLE IF EXISTS `accountflow`;
CREATE TABLE `accountflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actionType` tinyint(4) NOT NULL,
  `amount` decimal(18,4) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `usableAmount` decimal(18,4) NOT NULL,
  `freezedAmount` decimal(18,4) NOT NULL,
  `tradeTime` datetime NOT NULL,
  `accountId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accountflow
-- ----------------------------
INSERT INTO `accountflow` VALUES ('1', '0', '5000.0000', '线下充值5000.0000元', '5000.0000', '0.0000', '2017-11-02 21:40:14', '26');
INSERT INTO `accountflow` VALUES ('2', '10', '2000.0000', '投标冻结2000元', '3000.0000', '2000.0000', '2017-11-02 22:21:16', '26');
INSERT INTO `accountflow` VALUES ('3', '10', '2000.0000', '投标冻结2000元', '1000.0000', '4000.0000', '2017-11-02 22:21:44', '26');
INSERT INTO `accountflow` VALUES ('4', '0', '10000.0000', '线下充值10000.0000元', '10000.0000', '0.0000', '2017-11-02 22:24:08', '24');
INSERT INTO `accountflow` VALUES ('5', '10', '1000.0000', '投标冻结1000元', '9000.0000', '1000.0000', '2017-11-02 22:24:53', '24');
INSERT INTO `accountflow` VALUES ('6', '10', '1000.0000', '投标冻结1000元', '8000.0000', '2000.0000', '2017-11-02 22:25:14', '24');
INSERT INTO `accountflow` VALUES ('7', '11', '2000.0000', '投标取消冻结2000.0000元', '3000.0000', '2000.0000', '2017-11-02 23:01:30', '26');
INSERT INTO `accountflow` VALUES ('8', '10', '2000.0000', '投标冻结2000元', '6000.0000', '4000.0000', '2017-11-02 23:12:15', '24');
INSERT INTO `accountflow` VALUES ('9', '10', '3000.0000', '投标冻结3000元', '3000.0000', '7000.0000', '2017-11-02 23:12:27', '24');
INSERT INTO `accountflow` VALUES ('10', '2', '4000.0000', '借款成功4000.0000元', '4000.0000', '0.0000', '2017-11-05 21:30:52', '25');
INSERT INTO `accountflow` VALUES ('11', '6', '200.0000', '支付平台借款手续费200.0000元', '3800.0000', '0.0000', '2017-11-05 21:35:50', '25');
INSERT INTO `accountflow` VALUES ('12', '3', '2000.0000', '投标成功流水,冻结金额减少2000.0000元', '3000.0000', '0.0000', '2017-11-05 21:35:57', '26');
INSERT INTO `accountflow` VALUES ('13', '3', '1000.0000', '投标成功流水,冻结金额减少1000.0000元', '3000.0000', '6000.0000', '2017-11-05 21:35:57', '24');
INSERT INTO `accountflow` VALUES ('14', '3', '1000.0000', '投标成功流水,冻结金额减少1000.0000元', '3000.0000', '5000.0000', '2017-11-05 21:35:57', '24');
INSERT INTO `accountflow` VALUES ('16', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '3444.6048', '0.0000', '2017-11-05 21:36:11', '25');
INSERT INTO `accountflow` VALUES ('17', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '3177.6976', '0.0000', '2017-11-05 21:36:11', '26');
INSERT INTO `accountflow` VALUES ('18', '7', '2.0000', '支付平台的利息管理费,可用金额减少:2.0000元', '3175.6976', '0.0000', '2017-11-05 21:36:11', '26');
INSERT INTO `accountflow` VALUES ('19', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3088.8488', '5000.0000', '2017-11-05 21:36:11', '24');
INSERT INTO `accountflow` VALUES ('20', '7', '1.0000', '支付平台的利息管理费,可用金额减少:1.0000元', '3087.8488', '5000.0000', '2017-11-05 21:36:11', '24');
INSERT INTO `accountflow` VALUES ('21', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3176.6976', '5000.0000', '2017-11-05 21:36:11', '24');
INSERT INTO `accountflow` VALUES ('22', '7', '1.0000', '支付平台的利息管理费,可用金额减少:1.0000元', '3175.6976', '5000.0000', '2017-11-05 21:36:11', '24');
INSERT INTO `accountflow` VALUES ('23', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '3089.2096', '0.0000', '2017-11-05 21:36:14', '25');
INSERT INTO `accountflow` VALUES ('24', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '3353.3952', '0.0000', '2017-11-05 21:36:14', '26');
INSERT INTO `accountflow` VALUES ('25', '7', '1.8423', '支付平台的利息管理费,可用金额减少:1.8423元', '3351.5529', '0.0000', '2017-11-05 21:36:14', '26');
INSERT INTO `accountflow` VALUES ('26', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3264.5464', '5000.0000', '2017-11-05 21:36:14', '24');
INSERT INTO `accountflow` VALUES ('27', '7', '0.9212', '支付平台的利息管理费,可用金额减少:0.9212元', '3263.6252', '5000.0000', '2017-11-05 21:36:14', '24');
INSERT INTO `accountflow` VALUES ('28', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3352.4740', '5000.0000', '2017-11-05 21:36:14', '24');
INSERT INTO `accountflow` VALUES ('29', '7', '0.9212', '支付平台的利息管理费,可用金额减少:0.9212元', '3351.5528', '5000.0000', '2017-11-05 21:36:14', '24');
INSERT INTO `accountflow` VALUES ('30', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '2733.8144', '0.0000', '2017-11-05 21:36:18', '25');
INSERT INTO `accountflow` VALUES ('31', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '3529.2505', '0.0000', '2017-11-05 21:36:18', '26');
INSERT INTO `accountflow` VALUES ('32', '7', '1.6830', '支付平台的利息管理费,可用金额减少:1.6830元', '3527.5675', '0.0000', '2017-11-05 21:36:18', '26');
INSERT INTO `accountflow` VALUES ('33', '5', '88.8489', '回款成功,可用金额增加:88.8489元', '3440.4017', '5000.0000', '2017-11-05 21:36:18', '24');
INSERT INTO `accountflow` VALUES ('34', '7', '0.8415', '支付平台的利息管理费,可用金额减少:0.8415元', '3439.5602', '5000.0000', '2017-11-05 21:36:18', '24');
INSERT INTO `accountflow` VALUES ('35', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '3528.4089', '5000.0000', '2017-11-05 21:36:18', '24');
INSERT INTO `accountflow` VALUES ('36', '7', '0.8415', '支付平台的利息管理费,可用金额减少:0.8415元', '3527.5674', '5000.0000', '2017-11-05 21:36:18', '24');
INSERT INTO `accountflow` VALUES ('37', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '2378.4192', '0.0000', '2017-11-05 21:36:20', '25');
INSERT INTO `accountflow` VALUES ('38', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '3705.2651', '0.0000', '2017-11-05 21:36:20', '26');
INSERT INTO `accountflow` VALUES ('39', '7', '1.5222', '支付平台的利息管理费,可用金额减少:1.5222元', '3703.7429', '0.0000', '2017-11-05 21:36:20', '26');
INSERT INTO `accountflow` VALUES ('40', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3616.4162', '5000.0000', '2017-11-05 21:36:20', '24');
INSERT INTO `accountflow` VALUES ('41', '7', '0.7611', '支付平台的利息管理费,可用金额减少:0.7611元', '3615.6551', '5000.0000', '2017-11-05 21:36:20', '24');
INSERT INTO `accountflow` VALUES ('42', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3704.5039', '5000.0000', '2017-11-05 21:36:20', '24');
INSERT INTO `accountflow` VALUES ('43', '7', '0.7611', '支付平台的利息管理费,可用金额减少:0.7611元', '3703.7428', '5000.0000', '2017-11-05 21:36:20', '24');
INSERT INTO `accountflow` VALUES ('44', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '2023.0240', '0.0000', '2017-11-05 21:36:22', '25');
INSERT INTO `accountflow` VALUES ('45', '5', '177.6977', '回款成功,可用金额增加:177.6977元', '3881.4406', '0.0000', '2017-11-05 21:36:22', '26');
INSERT INTO `accountflow` VALUES ('46', '7', '1.3597', '支付平台的利息管理费,可用金额减少:1.3597元', '3880.0809', '0.0000', '2017-11-05 21:36:22', '26');
INSERT INTO `accountflow` VALUES ('47', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3792.5916', '5000.0000', '2017-11-05 21:36:22', '24');
INSERT INTO `accountflow` VALUES ('48', '7', '0.6798', '支付平台的利息管理费,可用金额减少:0.6798元', '3791.9118', '5000.0000', '2017-11-05 21:36:22', '24');
INSERT INTO `accountflow` VALUES ('49', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '3880.7605', '5000.0000', '2017-11-05 21:36:22', '24');
INSERT INTO `accountflow` VALUES ('50', '7', '0.6798', '支付平台的利息管理费,可用金额减少:0.6798元', '3880.0807', '5000.0000', '2017-11-05 21:36:22', '24');
INSERT INTO `accountflow` VALUES ('51', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '1667.6288', '0.0000', '2017-11-05 21:36:26', '25');
INSERT INTO `accountflow` VALUES ('52', '5', '177.6977', '回款成功,可用金额增加:177.6977元', '4057.7786', '0.0000', '2017-11-05 21:36:26', '26');
INSERT INTO `accountflow` VALUES ('53', '7', '1.1956', '支付平台的利息管理费,可用金额减少:1.1956元', '4056.5830', '0.0000', '2017-11-05 21:36:26', '26');
INSERT INTO `accountflow` VALUES ('54', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '3968.9295', '5000.0000', '2017-11-05 21:36:26', '24');
INSERT INTO `accountflow` VALUES ('55', '7', '0.5978', '支付平台的利息管理费,可用金额减少:0.5978元', '3968.3317', '5000.0000', '2017-11-05 21:36:26', '24');
INSERT INTO `accountflow` VALUES ('56', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '4057.1804', '5000.0000', '2017-11-05 21:36:26', '24');
INSERT INTO `accountflow` VALUES ('57', '7', '0.5978', '支付平台的利息管理费,可用金额减少:0.5978元', '4056.5826', '5000.0000', '2017-11-05 21:36:26', '24');
INSERT INTO `accountflow` VALUES ('58', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '1312.2336', '0.0000', '2017-11-05 21:36:28', '25');
INSERT INTO `accountflow` VALUES ('59', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '4234.2806', '0.0000', '2017-11-05 21:36:28', '26');
INSERT INTO `accountflow` VALUES ('60', '7', '1.0298', '支付平台的利息管理费,可用金额减少:1.0298元', '4233.2508', '0.0000', '2017-11-05 21:36:28', '26');
INSERT INTO `accountflow` VALUES ('61', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '4145.4314', '5000.0000', '2017-11-05 21:36:28', '24');
INSERT INTO `accountflow` VALUES ('62', '7', '0.5149', '支付平台的利息管理费,可用金额减少:0.5149元', '4144.9165', '5000.0000', '2017-11-05 21:36:28', '24');
INSERT INTO `accountflow` VALUES ('63', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '4233.7653', '5000.0000', '2017-11-05 21:36:28', '24');
INSERT INTO `accountflow` VALUES ('64', '7', '0.5149', '支付平台的利息管理费,可用金额减少:0.5149元', '4233.2504', '5000.0000', '2017-11-05 21:36:28', '24');
INSERT INTO `accountflow` VALUES ('65', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '956.8384', '0.0000', '2017-11-05 21:36:30', '25');
INSERT INTO `accountflow` VALUES ('66', '5', '177.6977', '回款成功,可用金额增加:177.6977元', '4410.9485', '0.0000', '2017-11-05 21:36:30', '26');
INSERT INTO `accountflow` VALUES ('67', '7', '0.8625', '支付平台的利息管理费,可用金额减少:0.8625元', '4410.0860', '0.0000', '2017-11-05 21:36:30', '26');
INSERT INTO `accountflow` VALUES ('68', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '4322.0992', '5000.0000', '2017-11-05 21:36:30', '24');
INSERT INTO `accountflow` VALUES ('69', '7', '0.4312', '支付平台的利息管理费,可用金额减少:0.4312元', '4321.6680', '5000.0000', '2017-11-05 21:36:30', '24');
INSERT INTO `accountflow` VALUES ('70', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '4410.5167', '5000.0000', '2017-11-05 21:36:30', '24');
INSERT INTO `accountflow` VALUES ('71', '7', '0.4312', '支付平台的利息管理费,可用金额减少:0.4312元', '4410.0855', '5000.0000', '2017-11-05 21:36:30', '24');
INSERT INTO `accountflow` VALUES ('72', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '601.4432', '0.0000', '2017-11-05 21:36:32', '25');
INSERT INTO `accountflow` VALUES ('73', '5', '177.6976', '回款成功,可用金额增加:177.6976元', '4587.7836', '0.0000', '2017-11-05 21:36:32', '26');
INSERT INTO `accountflow` VALUES ('74', '7', '0.6934', '支付平台的利息管理费,可用金额减少:0.6934元', '4587.0902', '0.0000', '2017-11-05 21:36:32', '26');
INSERT INTO `accountflow` VALUES ('75', '5', '88.8489', '回款成功,可用金额增加:88.8489元', '4498.9344', '5000.0000', '2017-11-05 21:36:32', '24');
INSERT INTO `accountflow` VALUES ('76', '7', '0.3467', '支付平台的利息管理费,可用金额减少:0.3467元', '4498.5877', '5000.0000', '2017-11-05 21:36:32', '24');
INSERT INTO `accountflow` VALUES ('77', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '4587.4364', '5000.0000', '2017-11-05 21:36:32', '24');
INSERT INTO `accountflow` VALUES ('78', '7', '0.3467', '支付平台的利息管理费,可用金额减少:0.3467元', '4587.0897', '5000.0000', '2017-11-05 21:36:32', '24');
INSERT INTO `accountflow` VALUES ('79', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '246.0480', '0.0000', '2017-11-05 21:36:34', '25');
INSERT INTO `accountflow` VALUES ('80', '5', '177.6977', '回款成功,可用金额增加:177.6977元', '4764.7879', '0.0000', '2017-11-05 21:36:34', '26');
INSERT INTO `accountflow` VALUES ('81', '7', '0.5226', '支付平台的利息管理费,可用金额减少:0.5226元', '4764.2653', '0.0000', '2017-11-05 21:36:34', '26');
INSERT INTO `accountflow` VALUES ('82', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '4675.9385', '5000.0000', '2017-11-05 21:36:34', '24');
INSERT INTO `accountflow` VALUES ('83', '7', '0.2613', '支付平台的利息管理费,可用金额减少:0.2613元', '4675.6772', '5000.0000', '2017-11-05 21:36:34', '24');
INSERT INTO `accountflow` VALUES ('84', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '4764.5259', '5000.0000', '2017-11-05 21:36:34', '24');
INSERT INTO `accountflow` VALUES ('85', '7', '0.2613', '支付平台的利息管理费,可用金额减少:0.2613元', '4764.2646', '5000.0000', '2017-11-05 21:36:34', '24');
INSERT INTO `accountflow` VALUES ('86', '0', '10000.0000', '线下充值10000.0000元', '10246.0480', '0.0000', '2017-11-05 21:37:20', '25');
INSERT INTO `accountflow` VALUES ('87', '4', '355.3952', '还款成功,可用金额减少:355.3952元', '9890.6528', '0.0000', '2017-11-05 21:37:35', '25');
INSERT INTO `accountflow` VALUES ('88', '5', '177.6977', '回款成功,可用金额增加:177.6977元', '4941.9630', '0.0000', '2017-11-05 21:37:35', '26');
INSERT INTO `accountflow` VALUES ('89', '7', '0.3501', '支付平台的利息管理费,可用金额减少:0.3501元', '4941.6129', '0.0000', '2017-11-05 21:37:35', '26');
INSERT INTO `accountflow` VALUES ('90', '5', '88.8488', '回款成功,可用金额增加:88.8488元', '4853.1134', '5000.0000', '2017-11-05 21:37:35', '24');
INSERT INTO `accountflow` VALUES ('91', '7', '0.1751', '支付平台的利息管理费,可用金额减少:0.1751元', '4852.9383', '5000.0000', '2017-11-05 21:37:35', '24');
INSERT INTO `accountflow` VALUES ('92', '5', '88.8487', '回款成功,可用金额增加:88.8487元', '4941.7870', '5000.0000', '2017-11-05 21:37:35', '24');
INSERT INTO `accountflow` VALUES ('93', '7', '0.1751', '支付平台的利息管理费,可用金额减少:0.1751元', '4941.6119', '5000.0000', '2017-11-05 21:37:35', '24');
INSERT INTO `accountflow` VALUES ('94', '4', '421.5801', '还款成功,可用金额减少:421.5801元', '9469.0727', '0.0000', '2017-11-05 21:37:37', '25');
INSERT INTO `accountflow` VALUES ('95', '5', '210.7901', '回款成功,可用金额增加:210.7901元', '5152.4030', '0.0000', '2017-11-05 21:37:37', '26');
INSERT INTO `accountflow` VALUES ('96', '7', '3.4852', '支付平台的利息管理费,可用金额减少:3.4852元', '5148.9178', '0.0000', '2017-11-05 21:37:37', '26');
INSERT INTO `accountflow` VALUES ('97', '5', '105.3951', '回款成功,可用金额增加:105.3951元', '5047.0070', '5000.0000', '2017-11-05 21:37:37', '24');
INSERT INTO `accountflow` VALUES ('98', '7', '1.7426', '支付平台的利息管理费,可用金额减少:1.7426元', '5045.2644', '5000.0000', '2017-11-05 21:37:37', '24');
INSERT INTO `accountflow` VALUES ('99', '5', '105.3949', '回款成功,可用金额增加:105.3949元', '5150.6593', '5000.0000', '2017-11-05 21:37:37', '24');
INSERT INTO `accountflow` VALUES ('100', '7', '1.7426', '支付平台的利息管理费,可用金额减少:1.7426元', '5148.9167', '5000.0000', '2017-11-05 21:37:37', '24');

-- ----------------------------
-- Table structure for bid
-- ----------------------------
DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actualRate` decimal(8,4) NOT NULL,
  `availableAmount` decimal(18,4) NOT NULL,
  `bidrequestid` bigint(20) NOT NULL,
  `bidUser_id` bigint(20) NOT NULL,
  `bidTime` datetime NOT NULL,
  `bidRequestTitle` varchar(255) DEFAULT NULL,
  `bidRequestState` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bid
-- ----------------------------
INSERT INTO `bid` VALUES ('1', '12.0000', '2000.0000', '2', '26', '2017-11-02 22:21:16', '先生,我是卖火柴的小女孩', '8');
INSERT INTO `bid` VALUES ('2', '10.0000', '2000.0000', '5', '26', '2017-11-02 22:21:44', '我是卖报的小画家', '6');
INSERT INTO `bid` VALUES ('3', '12.0000', '1000.0000', '2', '24', '2017-11-02 22:24:53', '先生,我是卖火柴的小女孩', '8');
INSERT INTO `bid` VALUES ('4', '12.0000', '1000.0000', '2', '24', '2017-11-02 22:25:14', '先生,我是卖火柴的小女孩', '8');
INSERT INTO `bid` VALUES ('5', '10.0000', '2000.0000', '10', '24', '2017-11-02 23:12:15', '我穷啊', '4');
INSERT INTO `bid` VALUES ('6', '10.0000', '3000.0000', '10', '24', '2017-11-02 23:12:27', '我穷啊', '4');

-- ----------------------------
-- Table structure for bidrequest
-- ----------------------------
DROP TABLE IF EXISTS `bidrequest`;
CREATE TABLE `bidrequest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `bidRequestType` tinyint(4) NOT NULL,
  `bidRequestState` tinyint(4) NOT NULL,
  `bidRequestAmount` decimal(18,4) NOT NULL,
  `currentRate` decimal(8,4) NOT NULL,
  `monthes2Return` tinyint(4) NOT NULL,
  `bidCount` int(11) NOT NULL,
  `totalRewardAmount` decimal(18,4) NOT NULL,
  `currentSum` decimal(18,4) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `disableDate` datetime DEFAULT NULL,
  `createuser_id` bigint(20) NOT NULL,
  `disableDays` tinyint(4) NOT NULL,
  `minBidAmount` decimal(18,4) NOT NULL,
  `applyTime` datetime DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `returnType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bidrequest
-- ----------------------------
INSERT INTO `bidrequest` VALUES ('1', '1', '0', '10', '3000.0000', '10.0000', '6', '0', '88.1051', '0.0000', '穷,简单点', '我想上学....', null, null, '25', '5', '500.0000', '2017-10-31 19:41:15', null, '0');
INSERT INTO `bidrequest` VALUES ('2', '7', '0', '8', '4000.0000', '12.0000', '12', '3', '330.9273', '4000.0000', '先生,我是卖火柴的小女孩', '就是穷,气魄在哪里!', '大家快来关注卖火柴的小女孩!', '2017-11-04 21:19:14', '25', '4', '1000.0000', '2017-10-31 21:18:34', '2017-10-31 21:19:14', '0');
INSERT INTO `bidrequest` VALUES ('3', '1', '0', '10', '2000.0000', '10.0000', '12', '0', '109.9812', '0.0000', '我是卖报的小画家', '我是卖报的小画家.棒棒哒', null, null, '24', '5', '1000.0000', '2017-10-31 21:30:13', null, '0');
INSERT INTO `bidrequest` VALUES ('4', '1', '0', '10', '2000.0000', '10.0000', '12', '0', '109.9812', '0.0000', '我是卖报的小画家', '我是卖报的小画家.棒棒哒', null, null, '24', '5', '1000.0000', '2017-10-31 21:30:44', null, '0');
INSERT INTO `bidrequest` VALUES ('5', '3', '0', '6', '2000.0000', '10.0000', '6', '1', '58.7367', '2000.0000', '我是卖报的小画家', '我是卖报的小画家', '我是卖报的小画家', '2017-11-03 21:32:33', '24', '3', '200.0000', '2017-10-31 21:32:25', '2017-10-31 21:32:33', '0');
INSERT INTO `bidrequest` VALUES ('6', '1', '0', '10', '2000.0000', '10.0000', '1', '0', '16.6667', '0.0000', '我是马云', '我是马云', null, null, '29', '1', '666.0000', '2017-10-31 21:45:12', null, '0');
INSERT INTO `bidrequest` VALUES ('7', '1', '0', '10', '2000.0000', '10.0000', '1', '0', '16.6667', '0.0000', '我是马云', '我是马云', null, null, '29', '1', '666.0000', '2017-10-31 21:52:50', null, '0');
INSERT INTO `bidrequest` VALUES ('8', '1', '0', '10', '2000.0000', '10.0000', '12', '0', '109.9812', '0.0000', '我是马云啊', '我是马云啊', null, null, '29', '3', '600.0000', '2017-10-31 21:55:06', null, '0');
INSERT INTO `bidrequest` VALUES ('9', '1', '0', '10', '2000.0000', '10.0000', '12', '0', '109.9812', '0.0000', '我是马云啊', '我是马云啊', null, null, '29', '3', '600.0000', '2017-10-31 21:55:27', null, '0');
INSERT INTO `bidrequest` VALUES ('10', '3', '0', '4', '5000.0000', '10.0000', '6', '2', '146.8418', '5000.0000', '我穷啊', '就是穷', '确实穷', '2017-11-07 23:11:33', '42', '5', '200.0000', '2017-11-02 23:11:18', '2017-11-02 23:11:33', '0');

-- ----------------------------
-- Table structure for bidrequestaudithistory
-- ----------------------------
DROP TABLE IF EXISTS `bidrequestaudithistory`;
CREATE TABLE `bidrequestaudithistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  `bidRequestId` bigint(20) NOT NULL,
  `auditType` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bidrequestaudithistory
-- ----------------------------
INSERT INTO `bidrequestaudithistory` VALUES ('1', '2', '信息不完整', '2017-10-31 21:11:19', '2017-10-31 19:41:15', '27', '25', '1', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('2', '1', '大家快来关注卖火柴的小女孩!', '2017-10-31 21:19:14', '2017-10-31 21:18:34', '27', '25', '2', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('3', '2', '拒绝...', '2017-10-31 21:30:36', '2017-10-31 21:30:13', '27', '24', '3', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('4', '2', '我是卖报的小画家', '2017-10-31 21:31:46', '2017-10-31 21:30:44', '27', '24', '4', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('5', '1', '我是卖报的小画家', '2017-10-31 21:32:33', '2017-10-31 21:32:25', '27', '24', '5', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('6', '2', 'ewrwe', '2017-10-31 21:52:25', '2017-10-31 21:45:12', '27', '29', '6', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('7', '2', 'uy676u', '2017-10-31 21:53:12', '2017-10-31 21:52:50', '27', '29', '7', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('8', '2', '\n标题\n我是马云啊\n', '2017-10-31 21:55:18', '2017-10-31 21:55:06', '27', '29', '8', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('9', '2', '', '2017-10-31 21:55:43', '2017-10-31 21:55:27', '27', '29', '9', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('10', '2', '信息不完整.拒绝', '2017-11-02 23:01:30', '2017-11-02 23:01:30', '27', '24', '5', '1');
INSERT INTO `bidrequestaudithistory` VALUES ('11', '1', '没问题.一审通过\n', '2017-11-02 23:03:05', '2017-11-02 23:03:05', '27', '25', '2', '1');
INSERT INTO `bidrequestaudithistory` VALUES ('12', '1', '确实穷', '2017-11-02 23:11:33', '2017-11-02 23:11:18', '27', '42', '10', '0');
INSERT INTO `bidrequestaudithistory` VALUES ('13', '1', '发多少', '2017-11-05 21:30:52', '2017-11-05 21:30:52', '27', '25', '2', '2');

-- ----------------------------
-- Table structure for iplog
-- ----------------------------
DROP TABLE IF EXISTS `iplog`;
CREATE TABLE `iplog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `logintime` datetime NOT NULL,
  `usertype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iplog
-- ----------------------------
INSERT INTO `iplog` VALUES ('32', '127.0.0.1', '1', 'lanxw', '2017-10-26 23:14:00', '0');
INSERT INTO `iplog` VALUES ('33', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:16', '1');
INSERT INTO `iplog` VALUES ('34', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:17', '1');
INSERT INTO `iplog` VALUES ('35', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:17', '1');
INSERT INTO `iplog` VALUES ('36', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:18', '1');
INSERT INTO `iplog` VALUES ('37', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:18', '1');
INSERT INTO `iplog` VALUES ('38', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:18', '1');
INSERT INTO `iplog` VALUES ('39', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:19', '1');
INSERT INTO `iplog` VALUES ('40', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:19', '1');
INSERT INTO `iplog` VALUES ('41', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:28', '1');
INSERT INTO `iplog` VALUES ('42', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:29', '1');
INSERT INTO `iplog` VALUES ('43', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:29', '1');
INSERT INTO `iplog` VALUES ('44', '127.0.0.1', '1', 'admin', '2017-10-26 23:24:30', '1');
INSERT INTO `iplog` VALUES ('45', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:48', '1');
INSERT INTO `iplog` VALUES ('46', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:49', '1');
INSERT INTO `iplog` VALUES ('47', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:49', '1');
INSERT INTO `iplog` VALUES ('48', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:49', '1');
INSERT INTO `iplog` VALUES ('49', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:49', '1');
INSERT INTO `iplog` VALUES ('50', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:50', '1');
INSERT INTO `iplog` VALUES ('51', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:50', '1');
INSERT INTO `iplog` VALUES ('52', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:50', '1');
INSERT INTO `iplog` VALUES ('53', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:50', '1');
INSERT INTO `iplog` VALUES ('54', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:51', '1');
INSERT INTO `iplog` VALUES ('55', '127.0.0.1', '1', 'admin', '2017-10-26 23:25:51', '1');
INSERT INTO `iplog` VALUES ('56', '127.0.0.1', '1', 'admin', '2017-10-26 23:26:25', '1');
INSERT INTO `iplog` VALUES ('57', '127.0.0.1', '1', 'admin', '2017-10-26 23:31:02', '1');
INSERT INTO `iplog` VALUES ('58', '127.0.0.1', '1', 'admin', '2017-10-26 23:43:50', '1');
INSERT INTO `iplog` VALUES ('59', '127.0.0.1', '1', 'admin', '2017-10-27 00:02:34', '1');
INSERT INTO `iplog` VALUES ('60', '127.0.0.1', '1', 'admin', '2017-10-27 00:03:25', '1');
INSERT INTO `iplog` VALUES ('61', '127.0.0.1', '1', 'admin', '2017-10-27 00:08:55', '1');
INSERT INTO `iplog` VALUES ('62', '127.0.0.1', '1', 'lanxw', '2017-10-27 00:29:37', '0');
INSERT INTO `iplog` VALUES ('63', '127.0.0.1', '1', 'lanxw', '2017-10-27 00:37:11', '0');
INSERT INTO `iplog` VALUES ('64', '127.0.0.1', '1', 'lanxw', '2017-10-27 00:43:32', '0');
INSERT INTO `iplog` VALUES ('65', '127.0.0.1', '1', 'lanxw', '2017-10-27 08:55:00', '0');
INSERT INTO `iplog` VALUES ('66', '127.0.0.1', '1', 'lanxw', '2017-10-27 08:58:21', '0');
INSERT INTO `iplog` VALUES ('67', '127.0.0.1', '1', 'lanxw', '2017-10-27 13:22:20', '0');
INSERT INTO `iplog` VALUES ('68', '127.0.0.1', '1', 'lanxw', '2017-10-27 13:25:02', '0');
INSERT INTO `iplog` VALUES ('69', '127.0.0.1', '1', 'lanxw', '2017-10-27 13:25:11', '0');
INSERT INTO `iplog` VALUES ('70', '127.0.0.1', '1', 'lanxw', '2017-10-27 13:30:07', '0');
INSERT INTO `iplog` VALUES ('71', '127.0.0.1', '1', 'lanxw', '2017-10-27 13:31:07', '0');
INSERT INTO `iplog` VALUES ('72', '127.0.0.1', '1', 'lanxw', '2017-10-27 18:37:06', '0');
INSERT INTO `iplog` VALUES ('73', '127.0.0.1', '1', 'lanxw', '2017-10-27 18:42:30', '0');
INSERT INTO `iplog` VALUES ('74', '127.0.0.1', '1', 'lanxw', '2017-10-27 18:47:09', '0');
INSERT INTO `iplog` VALUES ('75', '127.0.0.1', '1', 'lanxw', '2017-10-27 18:50:39', '0');
INSERT INTO `iplog` VALUES ('76', '127.0.0.1', '1', 'test', '2017-10-27 18:53:20', '0');
INSERT INTO `iplog` VALUES ('77', '127.0.0.1', '1', 'lanxw', '2017-10-27 19:43:19', '0');
INSERT INTO `iplog` VALUES ('78', '127.0.0.1', '1', 'lanxw', '2017-10-27 19:44:18', '0');
INSERT INTO `iplog` VALUES ('79', '127.0.0.1', '1', 'lanxw', '2017-10-27 21:46:38', '0');
INSERT INTO `iplog` VALUES ('80', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:01:14', '0');
INSERT INTO `iplog` VALUES ('81', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:03:39', '0');
INSERT INTO `iplog` VALUES ('82', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:08:03', '0');
INSERT INTO `iplog` VALUES ('83', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:25:05', '0');
INSERT INTO `iplog` VALUES ('84', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:28:51', '0');
INSERT INTO `iplog` VALUES ('85', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:42:30', '0');
INSERT INTO `iplog` VALUES ('86', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:44:16', '0');
INSERT INTO `iplog` VALUES ('87', '127.0.0.1', '1', 'lanxw', '2017-10-27 22:47:08', '0');
INSERT INTO `iplog` VALUES ('88', '127.0.0.1', '1', 'lanxw', '2017-10-27 23:17:09', '0');
INSERT INTO `iplog` VALUES ('89', '127.0.0.1', '1', 'lanxw', '2017-10-27 23:19:16', '0');
INSERT INTO `iplog` VALUES ('90', '127.0.0.1', '0', 'lanxw', '2017-10-27 23:46:12', '0');
INSERT INTO `iplog` VALUES ('91', '127.0.0.1', '0', 'lanxw', '2017-10-27 23:47:01', '0');
INSERT INTO `iplog` VALUES ('92', '127.0.0.1', '0', 'admin', '2017-10-27 23:50:20', '1');
INSERT INTO `iplog` VALUES ('93', '127.0.0.1', '0', 'admin', '2017-10-27 23:51:34', '1');
INSERT INTO `iplog` VALUES ('94', '127.0.0.1', '0', 'lanxw', '2017-10-27 23:53:10', '0');
INSERT INTO `iplog` VALUES ('95', '127.0.0.1', '0', 'lanxw', '2017-10-28 13:37:00', '0');
INSERT INTO `iplog` VALUES ('96', '127.0.0.1', '0', 'lanxw', '2017-10-28 14:17:04', '0');
INSERT INTO `iplog` VALUES ('97', '127.0.0.1', '0', 'lanxw', '2017-10-28 14:32:00', '0');
INSERT INTO `iplog` VALUES ('98', '127.0.0.1', '0', 'lanxw', '2017-10-28 19:22:57', '0');
INSERT INTO `iplog` VALUES ('99', '127.0.0.1', '0', 'lanxw', '2017-10-28 19:26:08', '0');
INSERT INTO `iplog` VALUES ('100', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-28 19:31:06', '0');
INSERT INTO `iplog` VALUES ('101', '127.0.0.1', '0', 'lanxw', '2017-10-28 20:29:27', '0');
INSERT INTO `iplog` VALUES ('102', '127.0.0.1', '0', 'admin', '2017-10-28 20:34:18', '1');
INSERT INTO `iplog` VALUES ('103', '127.0.0.1', '0', 'admin', '2017-10-28 20:53:04', '1');
INSERT INTO `iplog` VALUES ('104', '127.0.0.1', '0', 'admin', '2017-10-28 21:02:54', '1');
INSERT INTO `iplog` VALUES ('105', '127.0.0.1', '0', 'admin', '2017-10-28 21:24:04', '1');
INSERT INTO `iplog` VALUES ('106', '127.0.0.1', '0', 'admin', '2017-10-28 21:29:30', '1');
INSERT INTO `iplog` VALUES ('107', '127.0.0.1', '0', 'admin', '2017-10-28 21:32:53', '1');
INSERT INTO `iplog` VALUES ('108', '127.0.0.1', '0', 'admin', '2017-10-28 21:48:22', '1');
INSERT INTO `iplog` VALUES ('109', '127.0.0.1', '0', 'admin', '2017-10-28 21:51:07', '1');
INSERT INTO `iplog` VALUES ('110', '127.0.0.1', '0', 'admin', '2017-10-28 22:03:16', '1');
INSERT INTO `iplog` VALUES ('111', '127.0.0.1', '0', 'admin', '2017-10-28 22:19:49', '1');
INSERT INTO `iplog` VALUES ('112', '127.0.0.1', '0', 'admin', '2017-10-28 22:23:38', '1');
INSERT INTO `iplog` VALUES ('113', '127.0.0.1', '0', 'admin', '2017-10-28 22:27:53', '1');
INSERT INTO `iplog` VALUES ('114', '127.0.0.1', '0', 'admin', '2017-10-28 22:31:12', '1');
INSERT INTO `iplog` VALUES ('115', '127.0.0.1', '0', 'admin', '2017-10-28 22:35:18', '1');
INSERT INTO `iplog` VALUES ('116', '127.0.0.1', '0', 'admin', '2017-10-28 22:36:57', '1');
INSERT INTO `iplog` VALUES ('117', '127.0.0.1', '0', 'admin', '2017-10-28 22:38:29', '1');
INSERT INTO `iplog` VALUES ('118', '127.0.0.1', '0', 'admin', '2017-10-28 22:39:35', '1');
INSERT INTO `iplog` VALUES ('119', '127.0.0.1', '0', 'admin', '2017-10-28 22:45:18', '1');
INSERT INTO `iplog` VALUES ('120', '127.0.0.1', '0', 'admin', '2017-10-28 22:49:00', '1');
INSERT INTO `iplog` VALUES ('121', '127.0.0.1', '0', 'admin', '2017-10-28 22:54:23', '1');
INSERT INTO `iplog` VALUES ('122', '127.0.0.1', '0', 'admin', '2017-10-28 22:54:23', '1');
INSERT INTO `iplog` VALUES ('123', '127.0.0.1', '0', 'admin', '2017-10-28 22:54:28', '1');
INSERT INTO `iplog` VALUES ('124', '127.0.0.1', '0', 'admin', '2017-10-28 22:59:10', '1');
INSERT INTO `iplog` VALUES ('125', '127.0.0.1', '0', 'admin', '2017-10-28 23:04:08', '1');
INSERT INTO `iplog` VALUES ('126', '127.0.0.1', '0', 'admin', '2017-10-28 23:33:17', '1');
INSERT INTO `iplog` VALUES ('127', '127.0.0.1', '0', 'admin', '2017-10-28 23:37:56', '1');
INSERT INTO `iplog` VALUES ('128', '127.0.0.1', '0', 'admin', '2017-10-28 23:39:11', '1');
INSERT INTO `iplog` VALUES ('129', '127.0.0.1', '0', 'admin', '2017-10-28 23:44:57', '1');
INSERT INTO `iplog` VALUES ('130', '127.0.0.1', '0', 'lanxw', '2017-10-28 23:46:05', '0');
INSERT INTO `iplog` VALUES ('131', '127.0.0.1', '0', 'lanxw', '2017-10-29 09:51:27', '0');
INSERT INTO `iplog` VALUES ('132', '127.0.0.1', '0', 'lanxw', '2017-10-29 09:56:32', '0');
INSERT INTO `iplog` VALUES ('133', '127.0.0.1', '0', 'lanxw', '2017-10-29 09:59:04', '0');
INSERT INTO `iplog` VALUES ('134', '127.0.0.1', '0', 'lanxw', '2017-10-29 09:59:04', '0');
INSERT INTO `iplog` VALUES ('135', '127.0.0.1', '0', 'test', '2017-10-29 09:59:57', '0');
INSERT INTO `iplog` VALUES ('136', '127.0.0.1', '0', 'test', '2017-10-29 10:00:01', '0');
INSERT INTO `iplog` VALUES ('137', '127.0.0.1', '0', 'lanxw', '2017-10-29 10:08:39', '0');
INSERT INTO `iplog` VALUES ('138', '127.0.0.1', '0', 'test', '2017-10-29 10:08:45', '0');
INSERT INTO `iplog` VALUES ('139', '127.0.0.1', '0', 'admin', '2017-10-29 10:26:56', '1');
INSERT INTO `iplog` VALUES ('140', '127.0.0.1', '0', 'lanxw', '2017-10-29 10:41:35', '0');
INSERT INTO `iplog` VALUES ('141', '127.0.0.1', '0', 'lanxw', '2017-10-29 10:41:35', '0');
INSERT INTO `iplog` VALUES ('142', '127.0.0.1', '0', 'test', '2017-10-29 10:41:56', '0');
INSERT INTO `iplog` VALUES ('143', '127.0.0.1', '0', 'lanxw', '2017-10-29 10:55:25', '0');
INSERT INTO `iplog` VALUES ('144', '127.0.0.1', '0', 'test', '2017-10-29 10:55:31', '0');
INSERT INTO `iplog` VALUES ('145', '127.0.0.1', '0', 'admin', '2017-10-29 10:59:28', '1');
INSERT INTO `iplog` VALUES ('146', '127.0.0.1', '0', 'test', '2017-10-29 10:59:56', '0');
INSERT INTO `iplog` VALUES ('147', '127.0.0.1', '0', 'test', '2017-10-29 10:59:56', '0');
INSERT INTO `iplog` VALUES ('148', '127.0.0.1', '0', 'admin', '2017-10-29 11:02:27', '1');
INSERT INTO `iplog` VALUES ('149', '127.0.0.1', '0', 'test', '2017-10-29 11:03:02', '0');
INSERT INTO `iplog` VALUES ('150', '127.0.0.1', '0', 'admin', '2017-10-29 11:11:58', '1');
INSERT INTO `iplog` VALUES ('151', '127.0.0.1', '0', 'admin', '2017-10-29 11:11:58', '1');
INSERT INTO `iplog` VALUES ('152', '127.0.0.1', '0', 'leajone', '2017-10-29 11:12:56', '0');
INSERT INTO `iplog` VALUES ('153', '127.0.0.1', '0', 'admin', '2017-10-29 11:14:03', '1');
INSERT INTO `iplog` VALUES ('154', '127.0.0.1', '0', 'admin', '2017-10-29 11:46:02', '1');
INSERT INTO `iplog` VALUES ('155', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 11:46:22', '0');
INSERT INTO `iplog` VALUES ('156', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 11:46:23', '0');
INSERT INTO `iplog` VALUES ('157', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 11:46:24', '0');
INSERT INTO `iplog` VALUES ('158', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 12:00:07', '0');
INSERT INTO `iplog` VALUES ('159', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 17:57:54', '0');
INSERT INTO `iplog` VALUES ('160', '0:0:0:0:0:0:0:1', '0', 'test', '2017-10-29 17:58:17', '0');
INSERT INTO `iplog` VALUES ('161', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 17:59:10', '0');
INSERT INTO `iplog` VALUES ('162', '0:0:0:0:0:0:0:1', '0', 'test', '2017-10-29 17:59:14', '0');
INSERT INTO `iplog` VALUES ('163', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 18:00:25', '0');
INSERT INTO `iplog` VALUES ('164', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 21:13:21', '0');
INSERT INTO `iplog` VALUES ('165', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-29 21:36:34', '0');
INSERT INTO `iplog` VALUES ('166', '127.0.0.1', '0', 'admin', '2017-10-29 22:03:20', '1');
INSERT INTO `iplog` VALUES ('167', '127.0.0.1', '0', 'admin', '2017-10-29 22:04:34', '1');
INSERT INTO `iplog` VALUES ('168', '127.0.0.1', '0', 'lanxw', '2017-10-29 22:04:42', '0');
INSERT INTO `iplog` VALUES ('169', '127.0.0.1', '0', 'test', '2017-10-29 22:05:08', '0');
INSERT INTO `iplog` VALUES ('170', '127.0.0.1', '0', 'admin', '2017-10-29 22:06:59', '1');
INSERT INTO `iplog` VALUES ('171', '127.0.0.1', '0', 'admin', '2017-10-29 22:41:33', '1');
INSERT INTO `iplog` VALUES ('172', '127.0.0.1', '0', 'admin', '2017-10-29 22:47:07', '1');
INSERT INTO `iplog` VALUES ('173', '127.0.0.1', '0', 'admin', '2017-10-29 23:05:05', '1');
INSERT INTO `iplog` VALUES ('174', '127.0.0.1', '0', 'leajone', '2017-10-29 23:09:28', '0');
INSERT INTO `iplog` VALUES ('175', '127.0.0.1', '0', 'admin', '2017-10-30 09:59:52', '1');
INSERT INTO `iplog` VALUES ('176', '127.0.0.1', '0', 'lanxw', '2017-10-30 10:16:42', '0');
INSERT INTO `iplog` VALUES ('177', '127.0.0.1', '0', 'lanxw', '2017-10-30 13:06:12', '0');
INSERT INTO `iplog` VALUES ('178', '127.0.0.1', '0', 'lanxw', '2017-10-30 15:40:32', '0');
INSERT INTO `iplog` VALUES ('179', '127.0.0.1', '1', '阿里巴巴', '2017-10-30 15:41:23', '0');
INSERT INTO `iplog` VALUES ('180', '127.0.0.1', '1', '阿里巴巴', '2017-10-30 15:41:29', '0');
INSERT INTO `iplog` VALUES ('181', '127.0.0.1', '1', '阿里巴巴', '2017-10-30 15:41:35', '0');
INSERT INTO `iplog` VALUES ('182', '127.0.0.1', '1', '阿里巴巴', '2017-10-30 15:41:40', '0');
INSERT INTO `iplog` VALUES ('183', '127.0.0.1', '1', '阿里巴巴', '2017-10-30 15:41:45', '0');
INSERT INTO `iplog` VALUES ('184', '127.0.0.1', '0', '阿里巴巴', '2017-10-30 15:42:14', '0');
INSERT INTO `iplog` VALUES ('185', '127.0.0.1', '0', 'lanxw', '2017-10-30 18:51:08', '0');
INSERT INTO `iplog` VALUES ('186', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:29:27', '0');
INSERT INTO `iplog` VALUES ('187', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:35:25', '0');
INSERT INTO `iplog` VALUES ('188', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:45:26', '0');
INSERT INTO `iplog` VALUES ('189', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:47:44', '0');
INSERT INTO `iplog` VALUES ('190', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:48:39', '0');
INSERT INTO `iplog` VALUES ('191', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:50:42', '0');
INSERT INTO `iplog` VALUES ('192', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:54:12', '0');
INSERT INTO `iplog` VALUES ('193', '127.0.0.1', '0', 'lanxw', '2017-10-30 19:59:39', '0');
INSERT INTO `iplog` VALUES ('194', '127.0.0.1', '0', 'lanxw', '2017-10-30 20:00:42', '0');
INSERT INTO `iplog` VALUES ('195', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-30 20:05:09', '0');
INSERT INTO `iplog` VALUES ('196', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:13:44', '0');
INSERT INTO `iplog` VALUES ('197', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:15:31', '0');
INSERT INTO `iplog` VALUES ('198', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:15:31', '0');
INSERT INTO `iplog` VALUES ('199', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:16:34', '0');
INSERT INTO `iplog` VALUES ('200', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:18:50', '0');
INSERT INTO `iplog` VALUES ('201', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:20:43', '0');
INSERT INTO `iplog` VALUES ('202', '127.0.0.1', '0', 'lanxw', '2017-10-30 21:49:41', '0');
INSERT INTO `iplog` VALUES ('203', '127.0.0.1', '0', 'lanxw', '2017-10-30 22:11:24', '0');
INSERT INTO `iplog` VALUES ('204', '127.0.0.1', '0', 'lanxw', '2017-10-30 22:14:22', '0');
INSERT INTO `iplog` VALUES ('205', '127.0.0.1', '0', 'lanxw', '2017-10-30 22:37:34', '0');
INSERT INTO `iplog` VALUES ('206', '127.0.0.1', '0', 'lanxw', '2017-10-30 22:39:13', '0');
INSERT INTO `iplog` VALUES ('207', '127.0.0.1', '0', 'admin', '2017-10-30 23:03:00', '1');
INSERT INTO `iplog` VALUES ('208', '127.0.0.1', '0', 'admin', '2017-10-30 23:05:13', '1');
INSERT INTO `iplog` VALUES ('209', '127.0.0.1', '0', 'admin', '2017-10-30 23:10:13', '1');
INSERT INTO `iplog` VALUES ('210', '127.0.0.1', '0', 'admin', '2017-10-30 23:15:50', '1');
INSERT INTO `iplog` VALUES ('211', '127.0.0.1', '0', 'admin', '2017-10-30 23:17:33', '1');
INSERT INTO `iplog` VALUES ('212', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-30 23:20:12', '0');
INSERT INTO `iplog` VALUES ('213', '127.0.0.1', '0', 'lanxw', '2017-10-31 09:46:00', '0');
INSERT INTO `iplog` VALUES ('214', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-31 18:57:15', '0');
INSERT INTO `iplog` VALUES ('215', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-31 18:57:17', '0');
INSERT INTO `iplog` VALUES ('216', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-31 19:40:01', '0');
INSERT INTO `iplog` VALUES ('217', '127.0.0.1', '0', 'admin', '2017-10-31 20:19:02', '1');
INSERT INTO `iplog` VALUES ('218', '127.0.0.1', '0', 'admin', '2017-10-31 20:21:36', '1');
INSERT INTO `iplog` VALUES ('219', '127.0.0.1', '0', 'admin', '2017-10-31 20:22:31', '1');
INSERT INTO `iplog` VALUES ('220', '127.0.0.1', '0', 'admin', '2017-10-31 20:28:29', '1');
INSERT INTO `iplog` VALUES ('221', '127.0.0.1', '0', 'admin', '2017-10-31 21:11:05', '1');
INSERT INTO `iplog` VALUES ('222', '127.0.0.1', '0', 'admin', '2017-10-31 21:15:58', '1');
INSERT INTO `iplog` VALUES ('223', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-31 21:17:23', '0');
INSERT INTO `iplog` VALUES ('224', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-10-31 21:21:15', '0');
INSERT INTO `iplog` VALUES ('225', '0:0:0:0:0:0:0:1', '0', 'test', '2017-10-31 21:21:52', '0');
INSERT INTO `iplog` VALUES ('226', '0:0:0:0:0:0:0:1', '1', 'lea', '2017-10-31 21:24:06', '0');
INSERT INTO `iplog` VALUES ('227', '0:0:0:0:0:0:0:1', '0', 'leajone', '2017-10-31 21:24:12', '0');
INSERT INTO `iplog` VALUES ('228', '0:0:0:0:0:0:0:1', '0', 'leajone', '2017-10-31 21:31:19', '0');
INSERT INTO `iplog` VALUES ('229', '0:0:0:0:0:0:0:1', '0', '阿里巴巴', '2017-10-31 21:38:09', '0');
INSERT INTO `iplog` VALUES ('230', '127.0.0.1', '0', 'admin', '2017-10-31 21:51:17', '1');
INSERT INTO `iplog` VALUES ('231', '127.0.0.1', '0', 'admin', '2017-11-01 10:01:30', '1');
INSERT INTO `iplog` VALUES ('232', '127.0.0.1', '0', 'admin', '2017-11-01 10:08:56', '1');
INSERT INTO `iplog` VALUES ('233', '127.0.0.1', '0', 'admin', '2017-11-01 10:17:14', '1');
INSERT INTO `iplog` VALUES ('234', '127.0.0.1', '0', 'admin', '2017-11-01 10:37:35', '1');
INSERT INTO `iplog` VALUES ('235', '127.0.0.1', '0', 'admin', '2017-11-01 10:42:09', '1');
INSERT INTO `iplog` VALUES ('236', '127.0.0.1', '0', 'admin', '2017-11-01 10:50:41', '1');
INSERT INTO `iplog` VALUES ('237', '127.0.0.1', '0', 'admin', '2017-11-01 11:08:27', '1');
INSERT INTO `iplog` VALUES ('238', '127.0.0.1', '0', 'admin', '2017-11-01 11:12:56', '1');
INSERT INTO `iplog` VALUES ('239', '127.0.0.1', '0', 'admin', '2017-11-01 11:19:24', '1');
INSERT INTO `iplog` VALUES ('240', '127.0.0.1', '0', 'lanxw', '2017-11-01 11:43:34', '0');
INSERT INTO `iplog` VALUES ('241', '127.0.0.1', '0', 'lanxw', '2017-11-01 11:43:36', '0');
INSERT INTO `iplog` VALUES ('242', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-01 11:49:36', '1');
INSERT INTO `iplog` VALUES ('243', '127.0.0.1', '0', 'admin', '2017-11-01 21:02:18', '1');
INSERT INTO `iplog` VALUES ('244', '127.0.0.1', '0', 'lanxw', '2017-11-01 21:27:23', '0');
INSERT INTO `iplog` VALUES ('245', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-01 21:30:28', '0');
INSERT INTO `iplog` VALUES ('246', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-01 21:42:17', '0');
INSERT INTO `iplog` VALUES ('247', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-01 21:51:17', '0');
INSERT INTO `iplog` VALUES ('248', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-01 21:51:46', '0');
INSERT INTO `iplog` VALUES ('249', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-01 21:51:52', '0');
INSERT INTO `iplog` VALUES ('250', '127.0.0.1', '0', 'lanxw', '2017-11-02 09:00:44', '0');
INSERT INTO `iplog` VALUES ('251', '127.0.0.1', '0', 'lanxw', '2017-11-02 09:04:00', '0');
INSERT INTO `iplog` VALUES ('252', '127.0.0.1', '0', 'lanxw', '2017-11-02 09:04:06', '0');
INSERT INTO `iplog` VALUES ('253', '127.0.0.1', '0', 'test', '2017-11-02 09:06:18', '0');
INSERT INTO `iplog` VALUES ('254', '127.0.0.1', '0', 'lanxw', '2017-11-02 09:24:52', '0');
INSERT INTO `iplog` VALUES ('255', '127.0.0.1', '0', 'admin', '2017-11-02 19:56:12', '1');
INSERT INTO `iplog` VALUES ('256', '127.0.0.1', '0', 'lanxw', '2017-11-02 20:08:30', '0');
INSERT INTO `iplog` VALUES ('257', '127.0.0.1', '0', 'lanxw', '2017-11-02 20:24:45', '0');
INSERT INTO `iplog` VALUES ('258', '127.0.0.1', '0', 'lanxw', '2017-11-02 20:27:41', '0');
INSERT INTO `iplog` VALUES ('259', '127.0.0.1', '0', 'lanxw', '2017-11-02 20:28:25', '0');
INSERT INTO `iplog` VALUES ('260', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 20:30:20', '1');
INSERT INTO `iplog` VALUES ('261', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 20:33:15', '1');
INSERT INTO `iplog` VALUES ('262', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-02 20:45:54', '0');
INSERT INTO `iplog` VALUES ('263', '127.0.0.1', '0', 'lanxw', '2017-11-02 20:52:40', '0');
INSERT INTO `iplog` VALUES ('264', '127.0.0.1', '0', 'eeee', '2017-11-02 21:07:20', '0');
INSERT INTO `iplog` VALUES ('265', '127.0.0.1', '0', 'lanxw', '2017-11-02 21:11:46', '0');
INSERT INTO `iplog` VALUES ('266', '127.0.0.1', '0', 'test', '2017-11-02 21:27:55', '0');
INSERT INTO `iplog` VALUES ('267', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 21:28:53', '1');
INSERT INTO `iplog` VALUES ('268', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 21:33:10', '1');
INSERT INTO `iplog` VALUES ('269', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 21:35:37', '1');
INSERT INTO `iplog` VALUES ('270', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 21:35:54', '1');
INSERT INTO `iplog` VALUES ('271', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 21:40:04', '1');
INSERT INTO `iplog` VALUES ('272', '0:0:0:0:0:0:0:1', '0', 'lanxw', '2017-11-02 21:40:27', '0');
INSERT INTO `iplog` VALUES ('273', '0:0:0:0:0:0:0:1', '0', 'test', '2017-11-02 21:40:51', '0');
INSERT INTO `iplog` VALUES ('274', '127.0.0.1', '0', 'lanxw', '2017-11-02 21:44:43', '0');
INSERT INTO `iplog` VALUES ('275', '127.0.0.1', '0', 'lanxw', '2017-11-02 21:45:32', '0');
INSERT INTO `iplog` VALUES ('276', '127.0.0.1', '0', 'test', '2017-11-02 22:03:26', '0');
INSERT INTO `iplog` VALUES ('277', '127.0.0.1', '0', 'lanxw', '2017-11-02 22:09:34', '0');
INSERT INTO `iplog` VALUES ('278', '127.0.0.1', '0', 'test', '2017-11-02 22:21:00', '0');
INSERT INTO `iplog` VALUES ('279', '127.0.0.1', '0', 'leajone', '2017-11-02 22:22:09', '0');
INSERT INTO `iplog` VALUES ('280', '127.0.0.1', '0', 'leajone', '2017-11-02 22:22:10', '0');
INSERT INTO `iplog` VALUES ('281', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 22:23:31', '1');
INSERT INTO `iplog` VALUES ('282', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 22:23:37', '1');
INSERT INTO `iplog` VALUES ('283', '127.0.0.1', '0', 'lanxw', '2017-11-02 22:24:19', '0');
INSERT INTO `iplog` VALUES ('284', '127.0.0.1', '0', 'leajone', '2017-11-02 22:24:32', '0');
INSERT INTO `iplog` VALUES ('285', '127.0.0.1', '0', 'lanxw', '2017-11-02 22:26:17', '0');
INSERT INTO `iplog` VALUES ('286', '127.0.0.1', '0', 'lanxw', '2017-11-02 22:27:10', '0');
INSERT INTO `iplog` VALUES ('287', '127.0.0.1', '0', 'eeee', '2017-11-02 22:27:14', '0');
INSERT INTO `iplog` VALUES ('288', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 22:29:35', '1');
INSERT INTO `iplog` VALUES ('289', '127.0.0.1', '0', 'lanxw', '2017-11-02 22:29:51', '0');
INSERT INTO `iplog` VALUES ('290', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 22:42:02', '1');
INSERT INTO `iplog` VALUES ('291', '127.0.0.1', '0', 'test', '2017-11-02 23:00:42', '0');
INSERT INTO `iplog` VALUES ('292', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 23:01:07', '1');
INSERT INTO `iplog` VALUES ('293', '127.0.0.1', '0', 'lanxw', '2017-11-02 23:02:15', '0');
INSERT INTO `iplog` VALUES ('294', '127.0.0.1', '0', 'leajone', '2017-11-02 23:02:39', '0');
INSERT INTO `iplog` VALUES ('295', '127.0.0.1', '0', 'lanxw', '2017-11-02 23:03:20', '0');
INSERT INTO `iplog` VALUES ('296', '127.0.0.1', '0', 'test', '2017-11-02 23:04:31', '0');
INSERT INTO `iplog` VALUES ('297', '127.0.0.1', '1', 'leajon', '2017-11-02 23:04:57', '0');
INSERT INTO `iplog` VALUES ('298', '127.0.0.1', '0', 'leajone', '2017-11-02 23:05:00', '0');
INSERT INTO `iplog` VALUES ('299', '127.0.0.1', '0', 'eeee', '2017-11-02 23:07:41', '0');
INSERT INTO `iplog` VALUES ('300', '127.0.0.1', '0', 'lanxw', '2017-11-02 23:11:52', '0');
INSERT INTO `iplog` VALUES ('301', '127.0.0.1', '0', 'leajone', '2017-11-02 23:12:00', '0');
INSERT INTO `iplog` VALUES ('302', '0:0:0:0:0:0:0:1', '0', 'admin', '2017-11-02 23:18:07', '1');
INSERT INTO `iplog` VALUES ('303', '127.0.0.1', '0', 'admin', '2017-11-03 18:40:59', '1');
INSERT INTO `iplog` VALUES ('304', '127.0.0.1', '0', 'admin', '2017-11-03 18:50:54', '1');
INSERT INTO `iplog` VALUES ('305', '127.0.0.1', '0', 'lanxw', '2017-11-05 21:30:01', '0');
INSERT INTO `iplog` VALUES ('306', '127.0.0.1', '0', 'admin', '2017-11-05 21:30:44', '1');
INSERT INTO `iplog` VALUES ('307', '127.0.0.1', '0', 'leajone', '2017-11-05 21:38:03', '0');
INSERT INTO `iplog` VALUES ('308', '127.0.0.1', '0', 'test', '2017-11-05 21:38:37', '0');
INSERT INTO `iplog` VALUES ('309', '127.0.0.1', '0', 'lanxw', '2017-11-05 22:26:41', '0');

-- ----------------------------
-- Table structure for logininfo
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `usertype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES ('24', 'leajone', 'b59c67bf196a4758191e42f76670ceba', '0', '0');
INSERT INTO `logininfo` VALUES ('25', 'lanxw', 'b59c67bf196a4758191e42f76670ceba', '0', '0');
INSERT INTO `logininfo` VALUES ('26', 'test', 'b59c67bf196a4758191e42f76670ceba', '0', '0');
INSERT INTO `logininfo` VALUES ('27', 'admin', 'b59c67bf196a4758191e42f76670ceba', '0', '1');
INSERT INTO `logininfo` VALUES ('28', '阿里爸爸', 'b59c67bf196a4758191e42f76670ceba', '0', '0');
INSERT INTO `logininfo` VALUES ('29', '阿里巴巴', 'b59c67bf196a4758191e42f76670ceba', '0', '0');
INSERT INTO `logininfo` VALUES ('42', 'eeee', 'b59c67bf196a4758191e42f76670ceba', '0', '0');

-- ----------------------------
-- Table structure for mailverify
-- ----------------------------
DROP TABLE IF EXISTS `mailverify`;
CREATE TABLE `mailverify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userinfoId` bigint(20) NOT NULL,
  `sendtime` datetime NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mailverify
-- ----------------------------
INSERT INTO `mailverify` VALUES ('1', '25', '2017-10-27 22:28:35', '231b12dc-b013-4183-a677-8d86ca083899', '22@qq.conm');
INSERT INTO `mailverify` VALUES ('2', '25', '2017-10-27 22:28:57', '05f9f91c-4a4c-474d-b600-74a650924f63', 'erre');
INSERT INTO `mailverify` VALUES ('3', '25', '2017-10-27 22:29:36', '200349d4-20b7-4822-b59d-b0c35f10feb3', 'erre');
INSERT INTO `mailverify` VALUES ('4', '25', '2017-10-27 22:29:49', 'c258058d-8ca0-4ec6-be71-a7ffa5e65193', 'erre');

-- ----------------------------
-- Table structure for paymentschedule
-- ----------------------------
DROP TABLE IF EXISTS `paymentschedule`;
CREATE TABLE `paymentschedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deadLine` datetime NOT NULL,
  `payDate` datetime DEFAULT NULL,
  `totalAmount` decimal(18,4) NOT NULL,
  `principal` decimal(18,4) NOT NULL,
  `interest` decimal(18,4) NOT NULL,
  `monthIndex` tinyint(4) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `bidRequestType` tinyint(4) NOT NULL,
  `returnType` tinyint(4) NOT NULL,
  `bidRequestId` bigint(20) NOT NULL,
  `borrowUser_id` bigint(20) NOT NULL,
  `bidRequestTitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paymentschedule
-- ----------------------------
INSERT INTO `paymentschedule` VALUES ('1', '2017-11-30 21:19:14', '2017-11-05 21:36:11', '355.3952', '315.3952', '40.0000', '1', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('2', '2017-12-31 21:19:14', '2017-11-05 21:36:14', '355.3952', '318.5492', '36.8460', '2', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('3', '2018-01-31 21:19:14', '2017-11-05 21:36:18', '355.3952', '321.7346', '33.6606', '3', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('4', '2018-02-28 21:19:14', '2017-11-05 21:36:20', '355.3952', '324.9520', '30.4432', '4', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('5', '2018-03-31 21:19:14', '2017-11-05 21:36:22', '355.3952', '328.2015', '27.1937', '5', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('6', '2018-04-30 21:19:14', '2017-11-05 21:36:26', '355.3952', '331.4835', '23.9117', '6', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('7', '2018-05-31 21:19:14', '2017-11-05 21:36:28', '355.3952', '334.7984', '20.5968', '7', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('8', '2018-06-30 21:19:14', '2017-11-05 21:36:30', '355.3952', '338.1463', '17.2489', '8', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('9', '2018-07-31 21:19:14', '2017-11-05 21:36:32', '355.3952', '341.5278', '13.8674', '9', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('10', '2018-08-31 21:19:14', '2017-11-05 21:36:34', '355.3952', '344.9431', '10.4521', '10', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('11', '2018-09-30 21:19:14', '2017-11-05 21:37:34', '355.3952', '348.3925', '7.0027', '11', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');
INSERT INTO `paymentschedule` VALUES ('12', '2018-10-31 21:19:14', '2017-11-05 21:37:37', '421.5801', '351.8759', '69.7042', '12', '1', '0', '0', '2', '25', '先生,我是卖火柴的小女孩');

-- ----------------------------
-- Table structure for paymentscheduledetail
-- ----------------------------
DROP TABLE IF EXISTS `paymentscheduledetail`;
CREATE TABLE `paymentscheduledetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bidamount` decimal(18,4) NOT NULL,
  `bidId` bigint(20) NOT NULL,
  `totalamount` decimal(18,4) NOT NULL,
  `principal` decimal(18,4) NOT NULL,
  `interest` decimal(18,4) NOT NULL,
  `monthindex` tinyint(4) NOT NULL,
  `deadline` datetime NOT NULL,
  `bidrequestId` bigint(20) NOT NULL,
  `paydate` datetime DEFAULT NULL,
  `returntype` tinyint(4) NOT NULL,
  `paymentScheduleId` bigint(20) NOT NULL,
  `borrowUser_id` bigint(20) DEFAULT NULL,
  `investorId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paymentscheduledetail
-- ----------------------------
INSERT INTO `paymentscheduledetail` VALUES ('1', '2000.0000', '1', '177.6976', '157.6976', '20.0000', '1', '2017-11-30 21:19:14', '2', '2017-11-05 21:36:11', '0', '1', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('2', '1000.0000', '3', '88.8488', '78.8488', '10.0000', '1', '2017-11-30 21:19:14', '2', '2017-11-05 21:36:11', '0', '1', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('3', '1000.0000', '4', '88.8488', '78.8488', '10.0000', '1', '2017-11-30 21:19:14', '2', '2017-11-05 21:36:11', '0', '1', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('4', '2000.0000', '1', '177.6976', '159.2746', '18.4230', '2', '2017-12-31 21:19:14', '2', '2017-11-05 21:36:14', '0', '2', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('5', '1000.0000', '3', '88.8488', '79.6373', '9.2115', '2', '2017-12-31 21:19:14', '2', '2017-11-05 21:36:14', '0', '2', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('6', '1000.0000', '4', '88.8488', '79.6373', '9.2115', '2', '2017-12-31 21:19:14', '2', '2017-11-05 21:36:14', '0', '2', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('7', '2000.0000', '1', '177.6976', '160.8673', '16.8303', '3', '2018-01-31 21:19:14', '2', '2017-11-05 21:36:18', '0', '3', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('8', '1000.0000', '3', '88.8489', '80.4337', '8.4152', '3', '2018-01-31 21:19:14', '2', '2017-11-05 21:36:18', '0', '3', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('9', '1000.0000', '4', '88.8487', '80.4336', '8.4151', '3', '2018-01-31 21:19:14', '2', '2017-11-05 21:36:18', '0', '3', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('10', '2000.0000', '1', '177.6976', '162.4760', '15.2216', '4', '2018-02-28 21:19:14', '2', '2017-11-05 21:36:20', '0', '4', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('11', '1000.0000', '3', '88.8488', '81.2380', '7.6108', '4', '2018-02-28 21:19:14', '2', '2017-11-05 21:36:20', '0', '4', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('12', '1000.0000', '4', '88.8488', '81.2380', '7.6108', '4', '2018-02-28 21:19:14', '2', '2017-11-05 21:36:20', '0', '4', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('13', '2000.0000', '1', '177.6977', '164.1008', '13.5969', '5', '2018-03-31 21:19:14', '2', '2017-11-05 21:36:22', '0', '5', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('14', '1000.0000', '3', '88.8488', '82.0504', '6.7984', '5', '2018-03-31 21:19:14', '2', '2017-11-05 21:36:22', '0', '5', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('15', '1000.0000', '4', '88.8487', '82.0503', '6.7984', '5', '2018-03-31 21:19:14', '2', '2017-11-05 21:36:22', '0', '5', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('16', '2000.0000', '1', '177.6977', '165.7418', '11.9559', '6', '2018-04-30 21:19:14', '2', '2017-11-05 21:36:26', '0', '6', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('17', '1000.0000', '3', '88.8488', '82.8709', '5.9779', '6', '2018-04-30 21:19:14', '2', '2017-11-05 21:36:26', '0', '6', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('18', '1000.0000', '4', '88.8487', '82.8708', '5.9779', '6', '2018-04-30 21:19:14', '2', '2017-11-05 21:36:26', '0', '6', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('19', '2000.0000', '1', '177.6976', '167.3992', '10.2984', '7', '2018-05-31 21:19:14', '2', '2017-11-05 21:36:28', '0', '7', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('20', '1000.0000', '3', '88.8488', '83.6996', '5.1492', '7', '2018-05-31 21:19:14', '2', '2017-11-05 21:36:28', '0', '7', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('21', '1000.0000', '4', '88.8488', '83.6996', '5.1492', '7', '2018-05-31 21:19:14', '2', '2017-11-05 21:36:28', '0', '7', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('22', '2000.0000', '1', '177.6977', '169.0732', '8.6245', '8', '2018-06-30 21:19:14', '2', '2017-11-05 21:36:30', '0', '8', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('23', '1000.0000', '3', '88.8488', '84.5366', '4.3122', '8', '2018-06-30 21:19:14', '2', '2017-11-05 21:36:30', '0', '8', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('24', '1000.0000', '4', '88.8487', '84.5365', '4.3122', '8', '2018-06-30 21:19:14', '2', '2017-11-05 21:36:30', '0', '8', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('25', '2000.0000', '1', '177.6976', '170.7639', '6.9337', '9', '2018-07-31 21:19:14', '2', '2017-11-05 21:36:32', '0', '9', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('26', '1000.0000', '3', '88.8489', '85.3820', '3.4669', '9', '2018-07-31 21:19:14', '2', '2017-11-05 21:36:32', '0', '9', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('27', '1000.0000', '4', '88.8487', '85.3819', '3.4668', '9', '2018-07-31 21:19:14', '2', '2017-11-05 21:36:32', '0', '9', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('28', '2000.0000', '1', '177.6977', '172.4716', '5.2261', '10', '2018-08-31 21:19:14', '2', '2017-11-05 21:36:34', '0', '10', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('29', '1000.0000', '3', '88.8488', '86.2358', '2.6130', '10', '2018-08-31 21:19:14', '2', '2017-11-05 21:36:34', '0', '10', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('30', '1000.0000', '4', '88.8487', '86.2357', '2.6130', '10', '2018-08-31 21:19:14', '2', '2017-11-05 21:36:34', '0', '10', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('31', '2000.0000', '1', '177.6977', '174.1963', '3.5014', '11', '2018-09-30 21:19:14', '2', '2017-11-05 21:37:34', '0', '11', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('32', '1000.0000', '3', '88.8488', '87.0981', '1.7507', '11', '2018-09-30 21:19:14', '2', '2017-11-05 21:37:34', '0', '11', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('33', '1000.0000', '4', '88.8487', '87.0981', '1.7506', '11', '2018-09-30 21:19:14', '2', '2017-11-05 21:37:34', '0', '11', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('34', '2000.0000', '1', '210.7901', '175.9380', '34.8521', '12', '2018-10-31 21:19:14', '2', '2017-11-05 21:37:37', '0', '12', '25', '26');
INSERT INTO `paymentscheduledetail` VALUES ('35', '1000.0000', '3', '105.3951', '87.9690', '17.4261', '12', '2018-10-31 21:19:14', '2', '2017-11-05 21:37:37', '0', '12', '25', '24');
INSERT INTO `paymentscheduledetail` VALUES ('36', '1000.0000', '4', '105.3949', '87.9689', '17.4260', '12', '2018-10-31 21:19:14', '2', '2017-11-05 21:37:37', '0', '12', '25', '24');

-- ----------------------------
-- Table structure for platformbankinfo
-- ----------------------------
DROP TABLE IF EXISTS `platformbankinfo`;
CREATE TABLE `platformbankinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bankname` varchar(255) DEFAULT NULL,
  `accountNumber` varchar(255) DEFAULT NULL,
  `accountName` varchar(255) DEFAULT NULL,
  `bankforkname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of platformbankinfo
-- ----------------------------
INSERT INTO `platformbankinfo` VALUES ('1', '1', '6238934980432878', '大法师', '杭州支行');
INSERT INTO `platformbankinfo` VALUES ('2', '2', '6329023489823343', '大师', '北京分行');

-- ----------------------------
-- Table structure for realauth
-- ----------------------------
DROP TABLE IF EXISTS `realauth`;
CREATE TABLE `realauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `realname` varchar(50) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `bornDate` varchar(50) DEFAULT NULL,
  `idNumber` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `image1` varchar(255) NOT NULL,
  `image2` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of realauth
-- ----------------------------
INSERT INTO `realauth` VALUES ('4', '蓝宝宝', '0', '1989-08-08', '28221221221303492', '北京朝阳区', '1', '', '', 'yrtu', '2017-10-28 23:45:05', '2017-10-28 19:32:05', '27', '25');
INSERT INTO `realauth` VALUES ('5', '韦小宝', '0', '1988-08-08', '177498239772834234', '北京颐和园', '2', '', '', '', '2017-10-29 10:59:45', '2017-10-29 10:59:20', '27', '26');
INSERT INTO `realauth` VALUES ('6', '韦小宝', '0', '1989-08-07', '633324786764282246286358', '北京颐和园', '1', '45a32f0e-a873-43f0-9201-d5ba14475373.png', '14b73b33-4880-4821-bed9-4952ef2c5f3b.png', '帅气的韦小宝', '2017-10-29 11:02:50', '2017-10-29 11:02:20', '27', '26');
INSERT INTO `realauth` VALUES ('7', '何老师', '0', '1899-07-06', '542392847847873248784329', '北京颐和园', '1', 'd7abc033-f46e-499e-b4f9-a65e1bad7764.jpg', '3534ceb9-a79f-4a0f-a961-50f0e39a4114.jpg', '真尼玛帅气', '2017-10-29 11:14:29', '2017-10-29 11:13:59', '27', '24');
INSERT INTO `realauth` VALUES ('8', '码云', '0', '1908-09-08', '1327382713287128', '浙江杭州', '1', 'be295262-bfa0-4420-9205-c0b37ad72eb8.png', '62b9a775-bccb-4ec9-9f1d-e23989d6e62f.png', '我也是醉了', '2017-10-31 21:40:23', '2017-10-31 21:39:01', '27', '29');
INSERT INTO `realauth` VALUES ('9', '李大本事', '0', '1990-09-07', '731289797412234', '北京三里屯', '1', '5f36077b-f068-4a49-b4a0-ca71d2d7ab9a.png', '1f0a3ea2-8eb9-4a23-9a52-c753efa62d9f.png', '李大的', '2017-11-02 22:28:54', '2017-11-02 22:28:27', '27', '42');

-- ----------------------------
-- Table structure for rechargeoffline
-- ----------------------------
DROP TABLE IF EXISTS `rechargeoffline`;
CREATE TABLE `rechargeoffline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  `tradeCode` varchar(255) NOT NULL,
  `tradeTime` datetime NOT NULL,
  `amount` decimal(18,4) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `bankinfo_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rechargeoffline
-- ----------------------------
INSERT INTO `rechargeoffline` VALUES ('1', '2', '账单异常', '2017-11-02 20:33:36', '2017-11-02 20:28:53', '27', '25', '123749999', '2017-11-02 00:00:00', '10000.0000', '我是奔波儿灞', '1');
INSERT INTO `rechargeoffline` VALUES ('2', '1', '健康良好', '2017-11-02 21:40:14', '2017-11-02 21:28:24', '27', '26', '1234567', '2017-11-02 00:00:00', '5000.0000', '请及时收钱!!!!', '1');
INSERT INTO `rechargeoffline` VALUES ('3', '1', '好吧 ,一万大洋  我收了', '2017-11-02 22:24:08', '2017-11-02 22:22:34', '27', '24', '12442323564', '2017-11-02 00:00:00', '10000.0000', '我有一万元大洋', '1');
INSERT INTO `rechargeoffline` VALUES ('4', '1', '厉害了', '2017-11-05 21:37:20', '2017-11-05 21:37:04', '27', '25', '423423234432', '2017-11-05 00:00:00', '10000.0000', '打一枪赚10000', '1');

-- ----------------------------
-- Table structure for systemaccount
-- ----------------------------
DROP TABLE IF EXISTS `systemaccount`;
CREATE TABLE `systemaccount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `usableAmount` decimal(18,4) NOT NULL,
  `freezedAmount` decimal(18,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemaccount
-- ----------------------------
INSERT INTO `systemaccount` VALUES ('1', '12', '33.0928', '0.0000');

-- ----------------------------
-- Table structure for systemaccountflow
-- ----------------------------
DROP TABLE IF EXISTS `systemaccountflow`;
CREATE TABLE `systemaccountflow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actionTime` datetime NOT NULL,
  `actionType` tinyint(4) NOT NULL,
  `amount` decimal(18,4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `usableAmount` decimal(18,4) NOT NULL,
  `freezedAmount` decimal(18,4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemaccountflow
-- ----------------------------
INSERT INTO `systemaccountflow` VALUES ('1', '2017-11-05 21:35:56', '1', '200.0000', '平台收取管理费200.0000元', '200.0000', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('2', '2017-11-05 21:36:11', '2', '2.0000', '平台收取管利息管理费2.0000元', '2.0000', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('3', '2017-11-05 21:36:11', '2', '1.0000', '平台收取管利息管理费1.0000元', '3.0000', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('4', '2017-11-05 21:36:11', '2', '1.0000', '平台收取管利息管理费1.0000元', '4.0000', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('5', '2017-11-05 21:36:14', '2', '1.8423', '平台收取管利息管理费1.8423元', '5.8423', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('6', '2017-11-05 21:36:14', '2', '0.9212', '平台收取管利息管理费0.9212元', '6.7635', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('7', '2017-11-05 21:36:14', '2', '0.9212', '平台收取管利息管理费0.9212元', '7.6847', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('8', '2017-11-05 21:36:18', '2', '1.6830', '平台收取管利息管理费1.6830元', '9.3677', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('9', '2017-11-05 21:36:18', '2', '0.8415', '平台收取管利息管理费0.8415元', '10.2092', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('10', '2017-11-05 21:36:18', '2', '0.8415', '平台收取管利息管理费0.8415元', '11.0507', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('11', '2017-11-05 21:36:20', '2', '1.5222', '平台收取管利息管理费1.5222元', '12.5729', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('12', '2017-11-05 21:36:20', '2', '0.7611', '平台收取管利息管理费0.7611元', '13.3340', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('13', '2017-11-05 21:36:20', '2', '0.7611', '平台收取管利息管理费0.7611元', '14.0951', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('14', '2017-11-05 21:36:22', '2', '1.3597', '平台收取管利息管理费1.3597元', '15.4548', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('15', '2017-11-05 21:36:22', '2', '0.6798', '平台收取管利息管理费0.6798元', '16.1346', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('16', '2017-11-05 21:36:22', '2', '0.6798', '平台收取管利息管理费0.6798元', '16.8144', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('17', '2017-11-05 21:36:26', '2', '1.1956', '平台收取管利息管理费1.1956元', '18.0100', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('18', '2017-11-05 21:36:26', '2', '0.5978', '平台收取管利息管理费0.5978元', '18.6078', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('19', '2017-11-05 21:36:26', '2', '0.5978', '平台收取管利息管理费0.5978元', '19.2056', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('20', '2017-11-05 21:36:28', '2', '1.0298', '平台收取管利息管理费1.0298元', '20.2354', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('21', '2017-11-05 21:36:28', '2', '0.5149', '平台收取管利息管理费0.5149元', '20.7503', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('22', '2017-11-05 21:36:28', '2', '0.5149', '平台收取管利息管理费0.5149元', '21.2652', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('23', '2017-11-05 21:36:30', '2', '0.8625', '平台收取管利息管理费0.8625元', '22.1277', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('24', '2017-11-05 21:36:30', '2', '0.4312', '平台收取管利息管理费0.4312元', '22.5589', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('25', '2017-11-05 21:36:30', '2', '0.4312', '平台收取管利息管理费0.4312元', '22.9901', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('26', '2017-11-05 21:36:32', '2', '0.6934', '平台收取管利息管理费0.6934元', '23.6835', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('27', '2017-11-05 21:36:32', '2', '0.3467', '平台收取管利息管理费0.3467元', '24.0302', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('28', '2017-11-05 21:36:32', '2', '0.3467', '平台收取管利息管理费0.3467元', '24.3769', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('29', '2017-11-05 21:36:34', '2', '0.5226', '平台收取管利息管理费0.5226元', '24.8995', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('30', '2017-11-05 21:36:34', '2', '0.2613', '平台收取管利息管理费0.2613元', '25.1608', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('31', '2017-11-05 21:36:34', '2', '0.2613', '平台收取管利息管理费0.2613元', '25.4221', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('32', '2017-11-05 21:37:35', '2', '0.3501', '平台收取管利息管理费0.3501元', '25.7722', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('33', '2017-11-05 21:37:35', '2', '0.1751', '平台收取管利息管理费0.1751元', '25.9473', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('34', '2017-11-05 21:37:35', '2', '0.1751', '平台收取管利息管理费0.1751元', '26.1224', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('35', '2017-11-05 21:37:37', '2', '3.4852', '平台收取管利息管理费3.4852元', '29.6076', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('36', '2017-11-05 21:37:37', '2', '1.7426', '平台收取管利息管理费1.7426元', '31.3502', '0.0000');
INSERT INTO `systemaccountflow` VALUES ('37', '2017-11-05 21:37:37', '2', '1.7426', '平台收取管利息管理费1.7426元', '33.0928', '0.0000');

-- ----------------------------
-- Table structure for systemdictionary
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionary`;
CREATE TABLE `systemdictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionary
-- ----------------------------
INSERT INTO `systemdictionary` VALUES ('1', 'incomeGrade', '月收入');
INSERT INTO `systemdictionary` VALUES ('2', 'educationBackground', '学历');
INSERT INTO `systemdictionary` VALUES ('3', 'marriage', '婚姻');
INSERT INTO `systemdictionary` VALUES ('4', 'kidCount', '子女');
INSERT INTO `systemdictionary` VALUES ('5', 'houseCondition', '住房条件');
INSERT INTO `systemdictionary` VALUES ('6', 'userFileType', '风控资料类型');

-- ----------------------------
-- Table structure for systemdictionaryitem
-- ----------------------------
DROP TABLE IF EXISTS `systemdictionaryitem`;
CREATE TABLE `systemdictionaryitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) NOT NULL,
  `title` varchar(50) NOT NULL,
  `sequence` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemdictionaryitem
-- ----------------------------
INSERT INTO `systemdictionaryitem` VALUES ('1', '1', '3000以下', '1');
INSERT INTO `systemdictionaryitem` VALUES ('2', '1', '3000~5000', '2');
INSERT INTO `systemdictionaryitem` VALUES ('3', '2', '大专以下', '1');
INSERT INTO `systemdictionaryitem` VALUES ('4', '2', '大专', '2');
INSERT INTO `systemdictionaryitem` VALUES ('5', '3', '已婚', '1');
INSERT INTO `systemdictionaryitem` VALUES ('6', '3', '未婚', '2');
INSERT INTO `systemdictionaryitem` VALUES ('7', '4', '有子女', '1');
INSERT INTO `systemdictionaryitem` VALUES ('8', '4', '无子女', '2');
INSERT INTO `systemdictionaryitem` VALUES ('9', '5', '有自有房', '1');
INSERT INTO `systemdictionaryitem` VALUES ('10', '5', '无自有房', '2');
INSERT INTO `systemdictionaryitem` VALUES ('11', '5', '租房', '3');
INSERT INTO `systemdictionaryitem` VALUES ('12', '6', '房产证正面', '1');
INSERT INTO `systemdictionaryitem` VALUES ('13', '6', '房产证反面', '2');
INSERT INTO `systemdictionaryitem` VALUES ('14', '6', '户口本', '3');
INSERT INTO `systemdictionaryitem` VALUES ('15', '6', '结婚证', '4');
INSERT INTO `systemdictionaryitem` VALUES ('16', '6', '银行流水证明', '5');
INSERT INTO `systemdictionaryitem` VALUES ('17', '6', '学位证', '6');
INSERT INTO `systemdictionaryitem` VALUES ('18', '6', '毕业证', '7');
INSERT INTO `systemdictionaryitem` VALUES ('19', '6', '电话记录', '7');

-- ----------------------------
-- Table structure for userfile
-- ----------------------------
DROP TABLE IF EXISTS `userfile`;
CREATE TABLE `userfile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  `score` tinyint(4) NOT NULL,
  `image` varchar(255) NOT NULL,
  `filetype_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userfile
-- ----------------------------
INSERT INTO `userfile` VALUES ('1', '1', '帅气', '2017-10-30 23:17:59', '2017-10-30 21:20:52', '27', '25', '5', '9bfbd16d-6285-40f4-8117-46cec59b146d.jpg', '12');
INSERT INTO `userfile` VALUES ('2', '1', '帅气', '2017-10-30 23:18:05', '2017-10-30 21:25:12', '27', '25', '5', '0b83ec46-0aa5-44f3-b254-cec4dc4dd077.jpg', '12');
INSERT INTO `userfile` VALUES ('3', '1', '帅气', '2017-10-30 23:18:12', '2017-10-30 21:25:13', '27', '25', '5', 'ae6146b0-6c63-4ba5-921b-6caa9de93bf1.jpg', '12');
INSERT INTO `userfile` VALUES ('4', '1', '帅气', '2017-10-30 23:18:19', '2017-10-30 21:25:13', '27', '25', '5', 'b8f199ea-678f-4828-891a-68c997cca405.jpg', '12');
INSERT INTO `userfile` VALUES ('5', '1', '帅气', '2017-10-30 23:18:29', '2017-10-30 22:39:26', '27', '25', '5', 'd8609608-bbdd-48d2-b8cf-66dcecc97e12.jpg', '18');
INSERT INTO `userfile` VALUES ('6', '1', '帅气', '2017-10-30 23:18:41', '2017-10-30 22:39:46', '27', '25', '5', 'e907a09d-017a-4ad2-af8e-030dd242f530.jpg', '15');
INSERT INTO `userfile` VALUES ('7', '1', '认证OK', '2017-10-31 21:27:38', '2017-10-31 21:26:35', '27', '24', '5', '89652d2f-332a-49be-a880-f455caff37d4.jpg', '15');
INSERT INTO `userfile` VALUES ('8', '1', '认证OK', '2017-10-31 21:27:46', '2017-10-31 21:26:36', '27', '24', '5', '10eda0aa-400b-4058-b328-ea8617296897.jpg', '18');
INSERT INTO `userfile` VALUES ('9', '1', '认证OK', '2017-10-31 21:27:54', '2017-10-31 21:26:36', '27', '24', '1', '7fbc40f6-815c-4002-ba07-0606c8eba0dc.jpg', '19');
INSERT INTO `userfile` VALUES ('10', '1', '认证OK', '2017-10-31 21:28:01', '2017-10-31 21:26:37', '27', '24', '1', 'df7923da-475c-4387-a6f7-ca26283e3646.jpg', '14');
INSERT INTO `userfile` VALUES ('11', '1', '认证OK', '2017-10-31 21:28:11', '2017-10-31 21:26:37', '27', '24', '5', '38f34b39-1337-4d8f-a872-ba4446661fca.jpg', '16');
INSERT INTO `userfile` VALUES ('12', '1', '认证OK', '2017-10-31 21:28:20', '2017-10-31 21:26:38', '27', '24', '5', '539d76c7-6a17-49ec-9537-372ee3b02ab8.png', '12');
INSERT INTO `userfile` VALUES ('13', '1', '认证OK', '2017-10-31 21:28:27', '2017-10-31 21:26:38', '27', '24', '5', '846bd4e5-cf0a-4cc5-b488-4436605658c1.png', '14');
INSERT INTO `userfile` VALUES ('14', '1', '认证OK', '2017-10-31 21:29:04', '2017-10-31 21:28:49', '27', '24', '5', 'ebf39c09-4a9a-4b40-8ea9-44d609b9ad9f.jpg', '12');
INSERT INTO `userfile` VALUES ('15', '1', '认证OK', '2017-10-31 21:29:11', '2017-10-31 21:28:49', '27', '24', '1', '59f0c440-0a2f-4d7c-9db9-6fc1b17ca94e.jpg', '12');
INSERT INTO `userfile` VALUES ('16', '1', '认证OK', '2017-10-31 21:29:20', '2017-10-31 21:28:50', '27', '24', '5', '9921554f-29c6-4bb8-b414-38353cf4ad26.jpg', '12');
INSERT INTO `userfile` VALUES ('17', '1', '哈哈哈审核OK', '2017-10-31 21:43:31', '2017-10-31 21:40:42', '27', '29', '5', '39791f7a-39e0-4df3-9a27-38d082b74d6c.jpg', '12');
INSERT INTO `userfile` VALUES ('18', '1', '哈哈哈审核OK', '2017-10-31 21:43:38', '2017-10-31 21:40:42', '27', '29', '5', '83efa21c-6779-41c1-8ec0-339caf815e40.jpg', '12');
INSERT INTO `userfile` VALUES ('19', '1', '哈哈哈审核OK', '2017-10-31 21:43:48', '2017-10-31 21:40:42', '27', '29', '5', '3ee348cd-d3d1-4990-907f-fe0087b38b7b.jpg', '12');
INSERT INTO `userfile` VALUES ('20', '1', '哈哈哈审核OK', '2017-10-31 21:43:56', '2017-10-31 21:40:42', '27', '29', '5', '287cd32b-cb9c-4b58-8e4c-2d4642b8b64a.jpg', '12');
INSERT INTO `userfile` VALUES ('21', '1', '哈哈哈审核OK', '2017-10-31 21:41:24', '2017-10-31 21:40:42', '27', '29', '5', '1571cef4-ea5a-4f81-9e63-9789ec1debd5.jpg', '12');
INSERT INTO `userfile` VALUES ('22', '1', '哈哈哈审核OK', '2017-10-31 21:41:39', '2017-10-31 21:40:43', '27', '29', '5', 'ac4ec502-ad34-45e3-aa03-b6c2c63b80f4.jpg', '12');
INSERT INTO `userfile` VALUES ('23', '1', '哈哈哈审核OK', '2017-10-31 21:41:53', '2017-10-31 21:40:43', '27', '29', '5', '59280030-2bbd-40c1-b9c7-5feed87f3796.jpg', '12');
INSERT INTO `userfile` VALUES ('24', '1', '哈哈哈审核OK', '2017-10-31 21:41:17', '2017-10-31 21:40:43', '27', '29', '1', '9b01c4d7-6acf-4a81-86d9-2475318e26eb.jpg', '12');
INSERT INTO `userfile` VALUES ('25', '0', null, null, '2017-11-02 23:07:59', null, '42', '0', '9c2e0387-35c5-4c56-95e5-122875fe2713.jpg', '13');
INSERT INTO `userfile` VALUES ('26', '1', '', '2017-11-02 23:08:58', '2017-11-02 23:07:59', '27', '42', '5', 'dbb5eeca-de0a-433e-8882-0dce60ada4fa.jpg', '15');
INSERT INTO `userfile` VALUES ('27', '1', '', '2017-11-02 23:09:04', '2017-11-02 23:08:00', '27', '42', '5', 'a5209a5d-5a45-457e-923a-812abd6c36bb.jpg', '14');
INSERT INTO `userfile` VALUES ('28', '1', '', '2017-11-02 23:09:10', '2017-11-02 23:08:00', '27', '42', '5', 'bf3efb7b-bf82-40cf-988c-c694eebe7581.jpg', '18');
INSERT INTO `userfile` VALUES ('29', '1', '', '2017-11-02 23:09:16', '2017-11-02 23:08:00', '27', '42', '5', 'bcdf1754-f910-4e86-81df-a75cf189c21e.jpg', '15');
INSERT INTO `userfile` VALUES ('30', '0', null, null, '2017-11-02 23:08:00', null, '42', '0', '910a25b1-d9fc-4b65-98e4-2d20c8b02aa0.jpg', '19');
INSERT INTO `userfile` VALUES ('31', '1', '', '2017-11-02 23:08:49', '2017-11-02 23:08:01', '27', '42', '5', '6116c401-a33e-43eb-8261-7fc617e24374.jpg', '16');
INSERT INTO `userfile` VALUES ('32', '1', '', '2017-11-02 23:08:42', '2017-11-02 23:08:01', '27', '42', '5', '9daf67a5-b3c3-43bd-89ad-c1c11a5ed474.jpg', '13');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL,
  `bitState` bigint(20) NOT NULL,
  `realName` varchar(30) DEFAULT NULL,
  `idNumber` varchar(30) DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `incomeGrade_id` bigint(20) DEFAULT NULL,
  `marriage_id` bigint(20) DEFAULT NULL,
  `kidCount_id` bigint(20) DEFAULT NULL,
  `educationBackground_id` bigint(20) DEFAULT NULL,
  `houseCondition_id` bigint(20) DEFAULT NULL,
  `score` int(20) DEFAULT NULL,
  `realAuthId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('23', '0', '0', '何老师', '542392847847873248784329', '1282404332', null, null, null, null, null, null, '0', null);
INSERT INTO `userinfo` VALUES ('24', '20', '28', '何老师', '542392847847873248784329', '23233245', null, '1', '5', '7', '3', '9', '38', '7');
INSERT INTO `userinfo` VALUES ('25', '21', '29', '蓝宝宝', '28221221221303492', '112223', null, '1', '5', '7', '3', '10', '30', '4');
INSERT INTO `userinfo` VALUES ('26', '7', '29', '韦小宝', '633324786764282246286358', '18820149469', null, '1', '5', '7', '3', '9', '0', '6');
INSERT INTO `userinfo` VALUES ('28', '0', '0', null, null, '34343434', null, null, null, null, null, null, '0', null);
INSERT INTO `userinfo` VALUES ('29', '21', '29', '码云', '1327382713287128', '188823923', null, '1', '5', '7', '3', '9', '36', '8');
INSERT INTO `userinfo` VALUES ('42', '11', '60', '李大本事', '731289797412234', null, null, '2', '6', '8', '3', '11', '30', '9');

-- ----------------------------
-- Table structure for vedioauth
-- ----------------------------
DROP TABLE IF EXISTS `vedioauth`;
CREATE TABLE `vedioauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` tinyint(4) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL,
  `applyTime` datetime NOT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `applier_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vedioauth
-- ----------------------------
INSERT INTO `vedioauth` VALUES ('1', '1', '视频认证ok', '2017-10-29 11:47:34', '2017-10-29 11:47:34', '27', '25');
INSERT INTO `vedioauth` VALUES ('2', '1', '长得很帅,所以视频认证OK', '2017-10-29 23:07:02', '2017-10-29 23:07:02', '27', '26');
INSERT INTO `vedioauth` VALUES ('3', '1', '长得也可以,视频认证ok\n我们的视频认证只在后台添加 \n用户页面不做操作', '2017-10-29 23:09:08', '2017-10-29 23:09:08', '27', '24');
INSERT INTO `vedioauth` VALUES ('4', '1', '视频认证ok', '2017-10-31 21:44:24', '2017-10-31 21:44:24', '27', '29');
INSERT INTO `vedioauth` VALUES ('5', '1', '视频认证ok', '2017-11-02 23:10:35', '2017-11-02 23:10:35', '27', '42');
