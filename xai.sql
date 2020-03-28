/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : xai

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-22 13:23:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ai_bd_dish
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_dish`;
CREATE TABLE `ai_bd_dish` (
  `icrId` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `resultNum` int(11) DEFAULT NULL,
  `calorie` varchar(255) DEFAULT NULL,
  `hasCalorie` varchar(100) DEFAULT NULL,
  `dishName` varchar(255) DEFAULT NULL,
  `probability` varchar(255) DEFAULT NULL,
  `imagePath` varchar(1000) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL COMMENT '入口类型 web wcs',
  `baikeUrl` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`icrId`)
) ENGINE=InnoDB AUTO_INCREMENT=618 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_bd_face
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_face`;
CREATE TABLE `ai_bd_face` (
  `faceId` int(11) NOT NULL AUTO_INCREMENT,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `logId` varchar(255) DEFAULT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  `cached` int(255) DEFAULT NULL,
  `faceNum` int(11) DEFAULT NULL,
  `faceToken` varchar(255) DEFAULT NULL,
  `faceProbability` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `beauty` varchar(255) DEFAULT NULL COMMENT '美丑打分，范围0-100，越大表示越美。',
  `expressionType` varchar(255) DEFAULT NULL COMMENT 'none:不笑；smile:微笑；laugh:大笑',
  `faceShapeType` varchar(255) DEFAULT NULL COMMENT '脸型 square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形',
  `gender` varchar(255) DEFAULT NULL COMMENT 'male:男性 female:女性',
  `glassesType` varchar(255) DEFAULT NULL COMMENT 'none:无眼镜，common:普通眼镜，sun:墨镜',
  `raceType` varchar(255) DEFAULT NULL COMMENT 'yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人',
  `openId` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `nikeName` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `imagePath` varchar(1000) DEFAULT NULL COMMENT '可以是本地路径 远程路径 具体根据实际业务来',
  PRIMARY KEY (`faceId`)
) ENGINE=InnoDB AUTO_INCREMENT=11279 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_bd_icrfuse
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_icrfuse`;
CREATE TABLE `ai_bd_icrfuse` (
  `icrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `resultNum` int(11) DEFAULT NULL,
  `icrName` varchar(255) DEFAULT NULL,
  `score` varchar(100) DEFAULT NULL,
  `Pyear` varchar(100) DEFAULT NULL,
  `colorResult` varchar(100) DEFAULT NULL,
  `localWidth` int(11) DEFAULT NULL,
  `localHeight` int(11) DEFAULT NULL,
  `localTop` int(11) DEFAULT NULL,
  `localLeft` int(11) DEFAULT NULL,
  `logoType` varchar(50) DEFAULT NULL,
  `probability` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(100) DEFAULT NULL,
  `nikeName` varchar(500) DEFAULT NULL,
  `enterType` varchar(50) DEFAULT NULL,
  `apiType` varchar(50) DEFAULT NULL,
  `baikeUrl` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`icrId`)
) ENGINE=InnoDB AUTO_INCREMENT=14318 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_bd_ocr
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_ocr`;
CREATE TABLE `ai_bd_ocr` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `words` varchar(2000) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB AUTO_INCREMENT=2543 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_bd_ocrbankcard
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_ocrbankcard`;
CREATE TABLE `ai_bd_ocrbankcard` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `bankCardNumber` varchar(255) DEFAULT NULL,
  `bankCardType` varchar(255) DEFAULT NULL,
  `bankName` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_bd_ocridcard
-- ----------------------------
DROP TABLE IF EXISTS `ai_bd_ocridcard`;
CREATE TABLE `ai_bd_ocridcard` (
  `ocrId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `errorMsg` varchar(255) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `imageStatus` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `idCardNum` varchar(100) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `issueDate` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `expiryDate` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `riskType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_fpp_face
-- ----------------------------
DROP TABLE IF EXISTS `ai_fpp_face`;
CREATE TABLE `ai_fpp_face` (
  `fppId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `faceToken` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `skinHealth` varchar(255) DEFAULT NULL,
  `skinStain` varchar(255) DEFAULT NULL,
  `skinAcne` varchar(255) DEFAULT NULL,
  `skinDarkCircle` varchar(255) DEFAULT NULL,
  `detectDate` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fppId`)
) ENGINE=InnoDB AUTO_INCREMENT=1606 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_imageclassify_bd
-- ----------------------------
DROP TABLE IF EXISTS `ai_imageclassify_bd`;
CREATE TABLE `ai_imageclassify_bd` (
  `icrId` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(255) DEFAULT NULL,
  `resultNum` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT '无',
  `calorie` varchar(255) DEFAULT '无',
  `probability` varchar(255) DEFAULT '无',
  `score` varchar(255) DEFAULT '无',
  `left` varchar(255) DEFAULT '无',
  `top` varchar(255) DEFAULT '无',
  `width` varchar(255) DEFAULT '无',
  `height` varchar(255) DEFAULT '无',
  `type` varchar(255) DEFAULT '无',
  `apiType` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `colorResult` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`icrId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_ocr_bd
-- ----------------------------
DROP TABLE IF EXISTS `ai_ocr_bd`;
CREATE TABLE `ai_ocr_bd` (
  `ocrId` int(11) NOT NULL AUTO_INCREMENT,
  `logId` varchar(200) DEFAULT NULL,
  `wordsResultNum` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `imageStatus` varchar(200) DEFAULT NULL,
  `editTool` varchar(200) DEFAULT NULL,
  `riskType` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `birth` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `idCardNum` int(11) DEFAULT NULL,
  `sex` varchar(200) DEFAULT NULL,
  `nation` varchar(200) DEFAULT NULL,
  `issueDate` varchar(200) DEFAULT NULL,
  `authority` varchar(200) DEFAULT NULL,
  `expiryDate` varchar(200) DEFAULT NULL,
  `cardNum` int(11) DEFAULT NULL,
  `expiraDate` varchar(200) DEFAULT NULL,
  `driverModel` varchar(200) DEFAULT NULL,
  `expiraBeginDate` varchar(200) DEFAULT NULL,
  `nationality` varchar(200) DEFAULT NULL,
  `birthDate` varchar(200) DEFAULT NULL,
  `firstIssueDate` varchar(200) DEFAULT NULL,
  `brandModel` varchar(200) DEFAULT NULL,
  `dateIssue` varchar(200) DEFAULT NULL,
  `useProperty` varchar(200) DEFAULT NULL,
  `engineNum` varchar(200) DEFAULT NULL,
  `numPlate` varchar(200) DEFAULT NULL,
  `posseMan` varchar(200) DEFAULT NULL,
  `createDate` varchar(200) DEFAULT NULL,
  `vin` varchar(200) DEFAULT NULL,
  `vehicleType` varchar(200) DEFAULT NULL,
  `unitName` varchar(200) DEFAULT NULL,
  `legalPerson` varchar(200) DEFAULT NULL,
  `termValidity` varchar(200) DEFAULT NULL,
  `idNum` varchar(200) DEFAULT NULL,
  `socialCreditCode` varchar(200) DEFAULT NULL,
  `words` varchar(5000) DEFAULT NULL,
  `bankName` varchar(100) DEFAULT NULL,
  `bankCardNumber` varchar(100) DEFAULT NULL,
  `bankCardType` varchar(100) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ocrId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ai_yt_fuse
-- ----------------------------
DROP TABLE IF EXISTS `ai_yt_fuse`;
CREATE TABLE `ai_yt_fuse` (
  `youtuId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `errorcode` int(11) DEFAULT NULL,
  `errormsg` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `confidence` varchar(500) DEFAULT NULL,
  `itemstring` varchar(5000) DEFAULT NULL,
  `faceId` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `expression` int(11) DEFAULT NULL,
  `glasses` int(11) DEFAULT NULL,
  `beauty` int(11) DEFAULT NULL,
  `hat` int(11) DEFAULT NULL,
  `mask` int(11) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `nikeName` varchar(255) DEFAULT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `enterType` varchar(255) DEFAULT NULL,
  `apiType` varchar(255) DEFAULT NULL,
  `classifyCnt` int(11) DEFAULT NULL,
  PRIMARY KEY (`youtuId`)
) ENGINE=InnoDB AUTO_INCREMENT=12099 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_content`;
CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified` bigint(20) DEFAULT NULL COMMENT '最近修改人id',
  `content` text COMMENT '内容',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类',
  `hits` int(5) DEFAULT NULL,
  `commentsNum` int(5) DEFAULT '0' COMMENT '评论数量',
  `allowComment` int(1) DEFAULT '0' COMMENT '开启评论',
  `allowPing` int(1) DEFAULT '0' COMMENT '允许ping',
  `allowFeed` int(1) DEFAULT '0' COMMENT '允许反馈',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `gtmCreate` datetime DEFAULT NULL COMMENT '创建时间',
  `gtmModified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COMMENT='文章内容';

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `delFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('16', '0', 'FACE', '10', '1');
INSERT INTO `sys_dept` VALUES ('17', '16', '小学部', '11', '1');
INSERT INTO `sys_dept` VALUES ('18', '16', '中学部', '12', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  `menuType` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', 'menu/index', 'fa fa-th-list', '2', '2017/8/9 22:55:15', '', '1');
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', '', 'fa fa-desktop', '1', '2017/8/9 23:06:55', '2017/8/14 14:13:43', '0');
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', 'user/index', 'fa fa-user', '0', '2017/8/10 14:12:11', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', 'role/index', 'fa fa-paw', '1', '2017/8/10 14:13:19', '', '1');
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '', '', '0', '2017/8/14 10:51:35', '', '2');
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '', '', '0', '2017/8/14 10:52:06', '', '2');
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', '', '', '0', '2017/8/14 10:52:24', '', '2');
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '', '', '0', '2017/8/14 10:56:37', '', '2');
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '', '', '0', '2017/8/14 10:59:32', '', '2');
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '', '', '0', '2017/8/14 10:59:56', '', '2');
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '', '', '0', '2017/8/14 11:00:26', '', '2');
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '', '', '0', '2017/8/14 17:27:18', '', '2');
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', '', '', '0', '2017/8/14 17:27:43', '', '2');
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '', '', '0', '2017/8/14 17:28:34', '', '2');
INSERT INTO `sys_menu` VALUES ('27', '91', '系统日志', 'common/log', 'fa fa-warning', '0', '2017/8/14 22:11:53', '', '1');
INSERT INTO `sys_menu` VALUES ('28', '27', '刷新', '', '', '0', '2017/8/14 22:30:22', '', '2');
INSERT INTO `sys_menu` VALUES ('29', '27', '删除', '', '', '0', '2017/8/14 22:30:43', '', '2');
INSERT INTO `sys_menu` VALUES ('30', '27', '清空', '', '', '0', '2017/8/14 22:31:02', '', '2');
INSERT INTO `sys_menu` VALUES ('48', '77', '代码生成', 'common/generator', 'fa fa-code', '3', '', '', '1');
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '', '', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '', '', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('57', '91', '运行监控', 'druid/index.html', 'fa fa-caret-square-o-right', '1', '', '', '1');
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '', '', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '', '', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('72', '77', '计划任务', 'common/job', 'fa fa-hourglass-1', '4', '', '', '1');
INSERT INTO `sys_menu` VALUES ('73', '3', '部门管理', 'dept/index', 'fa fa-users', '3', '', '', '1');
INSERT INTO `sys_menu` VALUES ('74', '73', '增加', '/system/sysDept/add', '', '1', '', '', '2');
INSERT INTO `sys_menu` VALUES ('75', '73', '刪除', 'system/sysDept/remove', '', '2', '', '', '2');
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '/system/sysDept/edit', '', '3', '', '', '2');
INSERT INTO `sys_menu` VALUES ('77', '0', '系统工具', '', 'fa fa-gear', '4', '', '', '0');
INSERT INTO `sys_menu` VALUES ('84', '0', '办公管理', '', 'fa fa-laptop', '5', '', '', '0');
INSERT INTO `sys_menu` VALUES ('85', '84', '通知公告', 'oa/notify', 'fa fa-pencil-square', null, '', '', '1');
INSERT INTO `sys_menu` VALUES ('86', '85', '新增', 'oa/notify/add', 'fa fa-plus', '1', '', '', '2');
INSERT INTO `sys_menu` VALUES ('87', '85', '编辑', 'oa/notify/edit', 'fa fa-pencil-square-o', '2', '', '', '2');
INSERT INTO `sys_menu` VALUES ('88', '85', '删除', 'oa/notify/remove', 'fa fa-minus', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('89', '85', '批量删除', 'oa/notify/batchRemove', '', null, '', '', '2');
INSERT INTO `sys_menu` VALUES ('90', '84', '我的通知', 'oa/notify/selfNotify', 'fa fa-envelope-square', null, '', '', '1');
INSERT INTO `sys_menu` VALUES ('91', '0', '系统监控', '', 'fa fa-video-camera', '5', '', '', '0');
INSERT INTO `sys_menu` VALUES ('92', '91', '在线用户', 'sys/online', 'fa fa-user', null, '', '', '1');
INSERT INTO `sys_menu` VALUES ('93', '0', '百度AI', '', 'fa fa-tree', null, '', '', '0');
INSERT INTO `sys_menu` VALUES ('94', '93', '人脸识别', 'bdface/index', 'fa fa-male', null, '', '', '1');
INSERT INTO `sys_menu` VALUES ('95', '93', '菜品识别', 'bdicr/indexDish', 'fa fa-picture-o', null, '', '', '1');
INSERT INTO `sys_menu` VALUES ('100', '0', '博客管理', '', 'fa fa-feed', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('101', '100', '发布文章', 'blogmanager/bContent/add', 'fa fa-book', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('102', '100', '文章列表', 'blogmanager/bContent', '', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('103', '93', '图像识别', 'bdicr/indexFuse', '', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('104', '93', '文字识别', 'bdocr/indexOcrGeneral', '', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('105', '93', '身份证识别', 'bdocr/indexOcrIdCard', 'fa fa-address-card', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('106', '93', '银行卡识别', 'bdocr/indexOcrBankCard', '', null, null, null, '1');
INSERT INTO `sys_menu` VALUES ('107', '0', '腾讯优图', '', 'fa fa-file-image-o', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('108', '107', '融合识别', 'youtu/indexYouTuFuse', '', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('109', '0', '微信用户', '', 'fa fa-user', null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('110', '109', '微信用户信息', 'wechat/indexWeChat', '', null, null, null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `roleSign` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `userIdCreate` varchar(255) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017/8/12 00:43:52', '2017/8/12 19:14:59');
INSERT INTO `sys_role` VALUES ('48', '钻石会员', '', '消费1w块', '', '', '');
INSERT INTO `sys_role` VALUES ('49', '白金会员', '', '消费5000以上', '', '', '');
INSERT INTO `sys_role` VALUES ('52', '白银会员', '', '消费两千以上', '', '', '');
INSERT INTO `sys_role` VALUES ('56', '普通用户', '', '普通用户', '', '', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `menuId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3113 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '44', '1');
INSERT INTO `sys_role_menu` VALUES ('368', '44', '32');
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33');
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34');
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35');
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28');
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29');
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30');
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38');
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4');
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27');
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38');
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3');
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20');
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21');
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22');
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23');
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11');
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12');
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13');
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14');
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24');
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25');
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26');
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15');
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2');
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6');
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7');
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38');
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42');
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38');
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39');
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40');
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41');
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4');
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32');
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33');
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34');
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35');
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27');
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28');
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29');
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30');
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53');
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2');
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6');
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7');
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3');
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50');
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49');
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1');
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28');
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29');
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30');
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27');
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57');
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71');
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48');
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72');
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1');
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7');
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55');
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56');
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62');
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15');
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2');
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61');
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20');
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21');
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22');
INSERT INTO `sys_role_menu` VALUES ('1875', '49', '12');
INSERT INTO `sys_role_menu` VALUES ('1876', '49', '13');
INSERT INTO `sys_role_menu` VALUES ('1877', '49', '14');
INSERT INTO `sys_role_menu` VALUES ('1878', '49', '24');
INSERT INTO `sys_role_menu` VALUES ('1879', '49', '25');
INSERT INTO `sys_role_menu` VALUES ('1880', '49', '26');
INSERT INTO `sys_role_menu` VALUES ('1881', '49', '61');
INSERT INTO `sys_role_menu` VALUES ('1882', '49', '20');
INSERT INTO `sys_role_menu` VALUES ('1883', '49', '21');
INSERT INTO `sys_role_menu` VALUES ('1884', '49', '22');
INSERT INTO `sys_role_menu` VALUES ('1885', '49', '74');
INSERT INTO `sys_role_menu` VALUES ('1886', '49', '75');
INSERT INTO `sys_role_menu` VALUES ('1887', '49', '76');
INSERT INTO `sys_role_menu` VALUES ('1888', '49', '6');
INSERT INTO `sys_role_menu` VALUES ('1889', '49', '2');
INSERT INTO `sys_role_menu` VALUES ('1890', '49', '73');
INSERT INTO `sys_role_menu` VALUES ('2072', '52', '77');
INSERT INTO `sys_role_menu` VALUES ('2073', '52', '49');
INSERT INTO `sys_role_menu` VALUES ('2074', '52', '3');
INSERT INTO `sys_role_menu` VALUES ('2075', '52', '72');
INSERT INTO `sys_role_menu` VALUES ('2076', '52', '48');
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68');
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60');
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59');
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58');
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51');
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50');
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49');
INSERT INTO `sys_role_menu` VALUES ('2243', '48', '72');
INSERT INTO `sys_role_menu` VALUES ('2247', '63', '-1');
INSERT INTO `sys_role_menu` VALUES ('2248', '63', '84');
INSERT INTO `sys_role_menu` VALUES ('2249', '63', '85');
INSERT INTO `sys_role_menu` VALUES ('2250', '63', '88');
INSERT INTO `sys_role_menu` VALUES ('2251', '63', '87');
INSERT INTO `sys_role_menu` VALUES ('2252', '64', '84');
INSERT INTO `sys_role_menu` VALUES ('2253', '64', '89');
INSERT INTO `sys_role_menu` VALUES ('2254', '64', '88');
INSERT INTO `sys_role_menu` VALUES ('2255', '64', '87');
INSERT INTO `sys_role_menu` VALUES ('2256', '64', '86');
INSERT INTO `sys_role_menu` VALUES ('2257', '64', '85');
INSERT INTO `sys_role_menu` VALUES ('2258', '65', '89');
INSERT INTO `sys_role_menu` VALUES ('2259', '65', '88');
INSERT INTO `sys_role_menu` VALUES ('2260', '65', '86');
INSERT INTO `sys_role_menu` VALUES ('2262', '67', '48');
INSERT INTO `sys_role_menu` VALUES ('2263', '68', '88');
INSERT INTO `sys_role_menu` VALUES ('2264', '68', '87');
INSERT INTO `sys_role_menu` VALUES ('2265', '69', '89');
INSERT INTO `sys_role_menu` VALUES ('2266', '69', '88');
INSERT INTO `sys_role_menu` VALUES ('2267', '69', '86');
INSERT INTO `sys_role_menu` VALUES ('2268', '69', '87');
INSERT INTO `sys_role_menu` VALUES ('2269', '69', '85');
INSERT INTO `sys_role_menu` VALUES ('2270', '69', '84');
INSERT INTO `sys_role_menu` VALUES ('2271', '70', '85');
INSERT INTO `sys_role_menu` VALUES ('2272', '70', '89');
INSERT INTO `sys_role_menu` VALUES ('2273', '70', '88');
INSERT INTO `sys_role_menu` VALUES ('2274', '70', '87');
INSERT INTO `sys_role_menu` VALUES ('2275', '70', '86');
INSERT INTO `sys_role_menu` VALUES ('2276', '70', '84');
INSERT INTO `sys_role_menu` VALUES ('2277', '71', '87');
INSERT INTO `sys_role_menu` VALUES ('2278', '72', '59');
INSERT INTO `sys_role_menu` VALUES ('2279', '73', '48');
INSERT INTO `sys_role_menu` VALUES ('2280', '74', '88');
INSERT INTO `sys_role_menu` VALUES ('2281', '74', '87');
INSERT INTO `sys_role_menu` VALUES ('2282', '75', '88');
INSERT INTO `sys_role_menu` VALUES ('2283', '75', '87');
INSERT INTO `sys_role_menu` VALUES ('2284', '76', '85');
INSERT INTO `sys_role_menu` VALUES ('2285', '76', '89');
INSERT INTO `sys_role_menu` VALUES ('2286', '76', '88');
INSERT INTO `sys_role_menu` VALUES ('2287', '76', '87');
INSERT INTO `sys_role_menu` VALUES ('2288', '76', '86');
INSERT INTO `sys_role_menu` VALUES ('2289', '76', '84');
INSERT INTO `sys_role_menu` VALUES ('2292', '78', '88');
INSERT INTO `sys_role_menu` VALUES ('2293', '78', '87');
INSERT INTO `sys_role_menu` VALUES ('2294', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2295', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2296', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2308', '80', '87');
INSERT INTO `sys_role_menu` VALUES ('2309', '80', '86');
INSERT INTO `sys_role_menu` VALUES ('2310', '80', '-1');
INSERT INTO `sys_role_menu` VALUES ('2311', '80', '84');
INSERT INTO `sys_role_menu` VALUES ('2312', '80', '85');
INSERT INTO `sys_role_menu` VALUES ('2328', '79', '72');
INSERT INTO `sys_role_menu` VALUES ('2329', '79', '48');
INSERT INTO `sys_role_menu` VALUES ('2330', '79', '77');
INSERT INTO `sys_role_menu` VALUES ('2331', '79', '84');
INSERT INTO `sys_role_menu` VALUES ('2332', '79', '89');
INSERT INTO `sys_role_menu` VALUES ('2333', '79', '88');
INSERT INTO `sys_role_menu` VALUES ('2334', '79', '87');
INSERT INTO `sys_role_menu` VALUES ('2335', '79', '86');
INSERT INTO `sys_role_menu` VALUES ('2336', '79', '85');
INSERT INTO `sys_role_menu` VALUES ('2337', '79', '-1');
INSERT INTO `sys_role_menu` VALUES ('2338', '77', '89');
INSERT INTO `sys_role_menu` VALUES ('2339', '77', '88');
INSERT INTO `sys_role_menu` VALUES ('2340', '77', '87');
INSERT INTO `sys_role_menu` VALUES ('2341', '77', '86');
INSERT INTO `sys_role_menu` VALUES ('2342', '77', '85');
INSERT INTO `sys_role_menu` VALUES ('2343', '77', '84');
INSERT INTO `sys_role_menu` VALUES ('2344', '77', '72');
INSERT INTO `sys_role_menu` VALUES ('2345', '77', '-1');
INSERT INTO `sys_role_menu` VALUES ('2346', '77', '77');
INSERT INTO `sys_role_menu` VALUES ('3071', '1', '108');
INSERT INTO `sys_role_menu` VALUES ('3072', '1', '102');
INSERT INTO `sys_role_menu` VALUES ('3073', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('3074', '1', '106');
INSERT INTO `sys_role_menu` VALUES ('3075', '1', '105');
INSERT INTO `sys_role_menu` VALUES ('3076', '1', '104');
INSERT INTO `sys_role_menu` VALUES ('3077', '1', '103');
INSERT INTO `sys_role_menu` VALUES ('3078', '1', '95');
INSERT INTO `sys_role_menu` VALUES ('3079', '1', '94');
INSERT INTO `sys_role_menu` VALUES ('3080', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('3081', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('3082', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('3083', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('3084', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('3085', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('3086', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('3087', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('3088', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('3089', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('3090', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('3091', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('3092', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('3093', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('3094', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('3095', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('3096', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('3097', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('3098', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('3099', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('3100', '1', '107');
INSERT INTO `sys_role_menu` VALUES ('3101', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('3102', '1', '93');
INSERT INTO `sys_role_menu` VALUES ('3103', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('3104', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('3105', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('3106', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('3107', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3108', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('3109', '1', '109');
INSERT INTO `sys_role_menu` VALUES ('3110', '1', '110');
INSERT INTO `sys_role_menu` VALUES ('3111', '1', '-1');
INSERT INTO `sys_role_menu` VALUES ('3112', '1', '91');


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userIdCreate` varchar(255) DEFAULT NULL,
  `gmtCreate` varchar(255) DEFAULT NULL,
  `gmtModified` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '123456', '6', 'admin@example.com', '123456', '1', '1', '2017/8/15 21:40:39', '2017/8/15 21:41:00', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('137', 'xiaoshuai', '小帅丶', '4e34bc24454ebc6e7ece909696d62329', '8', 'youngxiaoshuai@163.com', '', '1', '', '', '', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('138', 'xsinfo', '小帅', '125bcea642c30012be7124af2738064f', null, 'youngxiaoshuai@163.com', '', '1', '', '', '', null, null, null, null, null);


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('97', '36', '48');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48');
INSERT INTO `sys_user_role` VALUES ('122', '133', '1');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');
INSERT INTO `sys_user_role` VALUES ('126', '138', '1');
INSERT INTO `sys_user_role` VALUES ('127', '137', '1');

-- ----------------------------
-- Table structure for wechat_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `wechat_userinfo`;
CREATE TABLE `wechat_userinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openId` varchar(255) DEFAULT NULL,
  `nickName` char(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `avatarUrl` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2797 DEFAULT CHARSET=utf8;
