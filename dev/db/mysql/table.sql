/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.101
Source Server Version : 50711
Source Host           : 192.168.1.101:3306
Source Database       : nssc

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-11-01 12:28:32
*/

-- ----------------------------
-- Table structure for ns_address
-- ----------------------------
DROP TABLE IF EXISTS ns_address;
CREATE TABLE ns_address (
  id bigint(20) NOT NULL,
  uerid bigint(20) NOT NULL,
  address varchar(200) NOT NULL,
  name varchar(100) NOT NULL,
  post varchar(10) DEFAULT NULL,
  phonenumb varchar(20) DEFAULT NULL,
  telnumb varchar(20) DEFAULT NULL,
  isuse int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_address
-- ----------------------------

-- ----------------------------
-- Table structure for ns_advertise
-- ----------------------------
DROP TABLE IF EXISTS ns_advertise;
CREATE TABLE ns_advertise (
  id bigint(20) NOT NULL,
  name varchar(100) DEFAULT NULL,
  memo text,
  imgurl varchar(45) DEFAULT NULL,
  linkkind bigint(20) DEFAULT NULL,
  imglink varchar(45) DEFAULT NULL,
  ordernumb int(11) DEFAULT '0',
  type int(11) DEFAULT NULL,
  isuse int(11) DEFAULT NULL,
  kind bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_advertise
-- ----------------------------

-- ----------------------------
-- Table structure for ns_cart
-- ----------------------------
DROP TABLE IF EXISTS ns_cart;
CREATE TABLE ns_cart (
  userid bigint(20) NOT NULL,
  goodsid bigint(20) NOT NULL,
  count int(11) DEFAULT '1',
  price decimal(10,2) DEFAULT '0.00',
  id bigint(20) NOT NULL,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  commission_charge decimal(10,2) DEFAULT '0.00',
  cash decimal(10,2) DEFAULT '0.00',
  account_amount decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_cart
-- ----------------------------

-- ----------------------------
-- Table structure for ns_comment
-- ----------------------------
DROP TABLE IF EXISTS ns_comment;
CREATE TABLE ns_comment (
  id bigint(20) NOT NULL,
  goodid bigint(20) NOT NULL,
  comment varchar(500) DEFAULT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  score int(11) DEFAULT '0',
  userid bigint(20) DEFAULT NULL,
  ishidden int(11) DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ns_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS ns_dictionaries;
CREATE TABLE ns_dictionaries (
  id bigint(20) NOT NULL,
  name varchar(150) DEFAULT NULL,
  parentid bigint(20) DEFAULT NULL,
  memo varchar(200) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_dictionaries
-- ----------------------------

-- ----------------------------
-- Table structure for ns_eventsinfo
-- ----------------------------
DROP TABLE IF EXISTS ns_eventsinfo;
CREATE TABLE ns_eventsinfo (
  id bigint(20) NOT NULL,
  name varchar(50) DEFAULT NULL,
  isuse int(11) DEFAULT '1',
  memo text,
  minpicture varchar(100) DEFAULT NULL,
  picture varchar(100) DEFAULT NULL,
  goods text,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_eventsinfo
-- ----------------------------

-- ----------------------------
-- Table structure for ns_goods
-- ----------------------------
DROP TABLE IF EXISTS ns_goods;
CREATE TABLE `ns_goods` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`gname` VARCHAR(50) NULL DEFAULT NULL,
	`price` DECIMAL(12,2) NULL DEFAULT NULL,
	`category` BIGINT(20) NULL DEFAULT NULL,
	`kind` BIGINT(20) NULL DEFAULT NULL,
	`brand` BIGINT(20) NULL DEFAULT NULL,
	`detail` TEXT NULL,
	`goodimglist` VARCHAR(2000) NULL DEFAULT NULL,
	`isuser` INT(11) NULL DEFAULT '1',
	`Gfullname` VARCHAR(150) NULL DEFAULT NULL,
	`storenumb` INT(11) NULL DEFAULT '0',
	`goodimg` VARCHAR(500) NULL DEFAULT NULL,
	`img1` VARCHAR(500) NULL DEFAULT NULL,
	`img2` VARCHAR(500) NULL DEFAULT NULL,
	`img3` VARCHAR(500) NULL DEFAULT NULL,
	`img4` VARCHAR(500) NULL DEFAULT NULL,
	`img5` VARCHAR(500) NULL DEFAULT NULL,
	`freazes` INT(11) NULL DEFAULT '0',
	`costprice` DECIMAL(10,0) NULL DEFAULT '0',
	`sellnumb` INT(11) NULL DEFAULT '0',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_goods
-- ----------------------------

-- ----------------------------
-- Table structure for ns_goods_category
-- ----------------------------
DROP TABLE IF EXISTS ns_goods_category;
CREATE TABLE ns_goods_category (
  id int(11) NOT NULL AUTO_INCREMENT,
  cate_name varchar(100) DEFAULT NULL,
  desc_ varchar(100) DEFAULT NULL,
  cate_order int(11) DEFAULT NULL,
  level int(11) DEFAULT NULL,
  logo varchar(100) DEFAULT NULL,
  url varchar(100) DEFAULT NULL,
  isuser char(1) DEFAULT '1',
  parent_id int(11) DEFAULT NULL,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  update_time datetime DEFAULT CURRENT_TIMESTAMP,
  flag char(1) DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ns_order
-- ----------------------------
DROP TABLE IF EXISTS ns_order;
CREATE TABLE ns_order (
  id bigint(20) NOT NULL,
  userid bigint(20) NOT NULL,
  total decimal(10,0) NOT NULL DEFAULT '0',
  counts int(11) NOT NULL DEFAULT '1',
  paytype bigint(20) DEFAULT NULL,
  outway varchar(45) DEFAULT NULL,
  orderstatus bigint(20) DEFAULT NULL,
  delivery_numb varchar(150) DEFAULT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  delivery_time datetime DEFAULT NULL,
  paynumb varchar(150) DEFAULT NULL,
  positionid bigint(20) DEFAULT NULL,
  address varchar(150) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  postcode varchar(45) DEFAULT NULL,
  contactnumb varchar(45) DEFAULT NULL,
  invoice int(11) DEFAULT NULL,
  companyname varchar(200) DEFAULT NULL,
  content varchar(45) DEFAULT NULL,
  remark varchar(200) DEFAULT NULL,
  commision_charge decimal(10,2) DEFAULT '0.00',
  cash decimal(10,2) DEFAULT '0.00',
  account_amount decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_order
-- ----------------------------

-- ----------------------------
-- Table structure for ns_order_detail
-- ----------------------------
DROP TABLE IF EXISTS ns_order_detail;
CREATE TABLE ns_order_detail (
  id bigint(20) NOT NULL,
  orderid bigint(20) NOT NULL,
  goodsid bigint(20) NOT NULL,
  count int(11) NOT NULL DEFAULT '1',
  price decimal(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ns_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS sys_code;
CREATE TABLE sys_code (
  ID varchar(36) NOT NULL,
  TYPE varchar(100) DEFAULT NULL,
  CODE varchar(100) DEFAULT NULL,
  LABEL varchar(200) DEFAULT NULL,
  REMARK varchar(200) DEFAULT NULL,
  FLAG char(1) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_pb_datasource
-- ----------------------------
DROP TABLE IF EXISTS t_pb_datasource;
CREATE TABLE t_pb_datasource (
  ID varchar(36) NOT NULL,
  SOURCE_NAME varchar(100) DEFAULT NULL,
  SOURCE_REMARK varchar(600) DEFAULT NULL,
  SOURCE_SQL varchar(4000) DEFAULT NULL,
  CREATE_USER_ID varchar(36) NOT NULL,
  WHERE_SQL varchar(4000) DEFAULT NULL,
  FLAG char(1) DEFAULT '1',
  CREATE_TIME datetime NOT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pb_datasource
-- ----------------------------

-- ----------------------------
-- Table structure for t_pb_uploadfile
-- ----------------------------
DROP TABLE IF EXISTS t_pb_uploadfile;
CREATE TABLE t_pb_uploadfile (
  FILE_ID varchar(36) NOT NULL,
  PARENT_FILE_ID int(11) DEFAULT NULL,
  FILE_NAME varchar(240) NOT NULL,
  FILE_TYPE varchar(24) DEFAULT NULL,
  FILE_SIZE int(11) NOT NULL DEFAULT '0',
  FILE_PATH varchar(4000) NOT NULL,
  UP_TYPE varchar(24) DEFAULT NULL,
  LRRY_DM varchar(50) NOT NULL,
  USER_TYPE varchar(12) NOT NULL,
  XZFW varchar(50) DEFAULT NULL,
  YX_BJ char(1) NOT NULL DEFAULT 'N',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  GROUP_ID varchar(36) DEFAULT NULL,
  PRIMARY KEY (FILE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pb_uploadfile
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_audit_log
-- ----------------------------
DROP TABLE IF EXISTS t_xt_audit_log;
CREATE TABLE t_xt_audit_log (
  id int(10) NOT NULL AUTO_INCREMENT,
  loginname varchar(10) DEFAULT NULL COMMENT '登录名',
  logdetail varchar(2000) DEFAULT NULL,
  logsource varchar(200) DEFAULT NULL,
  ip varchar(30) DEFAULT NULL COMMENT 'IP地址',
  createdate datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_cache_table
-- ----------------------------
DROP TABLE IF EXISTS t_xt_cache_table;
CREATE TABLE t_xt_cache_table (
  ID varchar(36) NOT NULL,
  CACHE_TYPE varchar(200) DEFAULT NULL,
  TABLE_NAME varchar(60) DEFAULT NULL,
  QUERY_SQL varchar(4000) DEFAULT NULL,
  CREATE_USER_ID varchar(36) DEFAULT NULL,
  CREATE_ORG_ID varchar(36) DEFAULT NULL,
  FLAG char(1) DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_cache_table
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_emp
-- ----------------------------
DROP TABLE IF EXISTS t_xt_emp;
CREATE TABLE t_xt_emp (
  ID varchar(36) NOT NULL,
  USER_ID varchar(36) NOT NULL,
  USER_PWD varchar(36) DEFAULT NULL,
  USER_NAME varchar(200) DEFAULT NULL,
  ORG_ID varchar(36) DEFAULT NULL,
  WORK_ADDR varchar(2000) DEFAULT NULL,
  TELPHONE varchar(50) DEFAULT NULL,
  MOBILE varchar(11) DEFAULT NULL,
  FAX varchar(50) DEFAULT NULL,
  SEX char(1) DEFAULT NULL,
  EMAIL varchar(50) DEFAULT NULL,
  REMARK varchar(500) DEFAULT NULL,
  POSITION_ID varchar(36) DEFAULT NULL,
  VALIDATE_DOMAIN varchar(72) DEFAULT NULL,
  VALIDATE_IP varchar(15) DEFAULT NULL,
  PCUSERNAME varchar(100) DEFAULT NULL,
  FLAG char(1) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_group
-- ----------------------------
DROP TABLE IF EXISTS t_xt_group;
CREATE TABLE t_xt_group (
  ID varchar(36) NOT NULL,
  GROUP_NAME varchar(60) NOT NULL,
  BUSINESS_TYPE varchar(60) NOT NULL,
  FLAG char(1) NOT NULL DEFAULT '1',
  CREATE_TIME datetime NOT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_group
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_group_user
-- ----------------------------
DROP TABLE IF EXISTS t_xt_group_user;
CREATE TABLE t_xt_group_user (
  ID varchar(36) NOT NULL,
  USER_ID varchar(36) NOT NULL,
  USER_TYPE char(1) NOT NULL,
  GROUP_ID varchar(36) DEFAULT NULL,
  BUSINESS_TYPE varchar(60) NOT NULL,
  FLAG char(1) NOT NULL DEFAULT '1',
  CREATE_TIME datetime NOT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_group_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_key_value
-- ----------------------------
DROP TABLE IF EXISTS t_xt_key_value;
CREATE TABLE t_xt_key_value (
  KV_KEY varchar(200) NOT NULL,
  KV_VALUE varchar(2000) DEFAULT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  PRIMARY KEY (KV_KEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_key_value
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_menu
-- ----------------------------
DROP TABLE IF EXISTS t_xt_menu;
CREATE TABLE t_xt_menu (
  MENU_ID varchar(36) NOT NULL,
  MENU_NAME varchar(400) NOT NULL,
  MENU_PARENT_ID varchar(36) NOT NULL,
  MENU_PARENT_IDS varchar(2000) NOT NULL,
  URL varchar(2000) DEFAULT NULL,
  PX_XH int(11) DEFAULT NULL,
  OPEN_METHOD varchar(24) DEFAULT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  LOGO varchar(200) DEFAULT NULL,
  PRIMARY KEY (MENU_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_message
-- ----------------------------
DROP TABLE IF EXISTS t_xt_message;
CREATE TABLE t_xt_message (
  ID varchar(36) NOT NULL,
  BATCH_ID varchar(36) DEFAULT NULL,
  REPLY_MESSAGE_ID varchar(36) DEFAULT NULL,
  TO_USER_ID varchar(36) NOT NULL,
  TITLE varchar(36) NOT NULL,
  MESSAGE varchar(4000) NOT NULL,
  MESSAGE_TYPE char(1) NOT NULL,
  MESSAGE_STATUS int(11) NOT NULL DEFAULT '0',
  MESSAGE_LEVEL int(11) NOT NULL,
  URL varchar(1000) DEFAULT NULL,
  CREATE_USER_ID varchar(36) NOT NULL,
  FLAG char(1) DEFAULT '1',
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_xt_org
-- ----------------------------
DROP TABLE IF EXISTS t_xt_org;
CREATE TABLE t_xt_org (
  ID varchar(36) NOT NULL,
  ORG_ID varchar(50) DEFAULT NULL,
  ORG_NAME varchar(500) DEFAULT NULL,
  PARENT_ORG_ID varchar(36) DEFAULT NULL,
  ORDER_ int(11) DEFAULT NULL,
  ROOT char(1) DEFAULT NULL,
  ORG_CODE varchar(36) DEFAULT NULL,
  SHORT_NAME varchar(500) DEFAULT NULL,
  ORG_PATH varchar(1000) DEFAULT NULL,
  ORG_LEVEL int(11) DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_position
-- ----------------------------
DROP TABLE IF EXISTS t_xt_position;
CREATE TABLE t_xt_position (
  POSITION_ID varchar(36) NOT NULL,
  POSITION_NAME varchar(200) DEFAULT NULL,
  POSITION_DESC varchar(200) DEFAULT NULL,
  PRIMARY KEY (POSITION_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_role
-- ----------------------------
DROP TABLE IF EXISTS t_xt_role;
CREATE TABLE t_xt_role (
  ROLE_ID varchar(36) NOT NULL,
  ROLE_NAME varchar(200) NOT NULL,
  ROLE_TYPE char(1) NOT NULL,
  ROLE_REMARK varchar(2000) DEFAULT NULL,
  ROLE_STATE_VALUE varchar(12) DEFAULT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  PRIMARY KEY (ROLE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_role_menu
-- ----------------------------
DROP TABLE IF EXISTS t_xt_role_menu;
CREATE TABLE t_xt_role_menu (
  ROLE_ID varchar(36) NOT NULL,
  MENU_ID varchar(36) NOT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  PRIMARY KEY (ROLE_ID,MENU_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_role_user
-- ----------------------------
DROP TABLE IF EXISTS t_xt_role_user;
CREATE TABLE t_xt_role_user (
  ROLE_ID varchar(36) NOT NULL,
  U_ID varchar(36) NOT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  PRIMARY KEY (ROLE_ID,U_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_user
-- ----------------------------
DROP TABLE IF EXISTS t_xt_user;
CREATE TABLE t_xt_user (
  U_ID varchar(36) NOT NULL,
  U_PWD varchar(36) DEFAULT NULL,
  U_NAME varchar(200) DEFAULT NULL,
  U_REMARK varchar(2000) DEFAULT NULL,
  YX_BJ char(1) DEFAULT '1',
  LR_SJ datetime NOT NULL,
  XG_SJ datetime DEFAULT NULL,
  ORDER_ int(11) DEFAULT NULL,
  PRIMARY KEY (U_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_xt_workday
-- ----------------------------
DROP TABLE IF EXISTS t_xt_workday;
CREATE TABLE t_xt_workday (
  DAY varchar(10) NOT NULL,
  WORKDAY char(1) DEFAULT NULL,
  REMARK varchar(200) DEFAULT NULL,
  FLAG char(1) DEFAULT '1',
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (DAY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xt_workday
-- ----------------------------

-- ----------------------------
-- Records of ns_goods_category
-- ----------------------------
INSERT INTO ns_goods_category VALUES ('1', '全部商品分类', '全部商品分类', '0', '0', null, null, '0', '-1', now(), now(), '0');

-- ----------------------------
-- Records of t_xt_user
-- ----------------------------
INSERT INTO t_xt_user VALUES ('admin', '123456', '系统管理员', null, '1', '2016-10-13 14:01:08', null, null);

-- ----------------------------
-- Records of t_xt_emp
-- ----------------------------
INSERT INTO t_xt_emp VALUES ('d6ce7860-a122-41bd-80d7-cb80f437044c', 'admin', '123456', '系统管理员', '81277dc5-6c4b-4246-97b9-47aafcb3d573', null, null, null, null, '0', null, null, '5bfad0ff-8227-4979-a5ad-6435564b020b', 'YANGCW-PC', '127.0.0.1', null, '1');

-- ----------------------------
-- Records of t_xt_menu
-- ----------------------------
/*INSERT INTO t_xt_menu VALUES ('007ac06f-aadc-4eb5-9af9-d085ad56aafc', '人工退单管理', '59a326f9-31a5-4bb7-b973-e51fe379b48c', 'root,xt,59a326f9-31a5-4bb7-b973-e51fe379b48c,007ac06f-aadc-4eb5-9af9-d085ad56aafc', '1', '4', null, '1', '2016-10-18 12:21:54', null, null);*/
INSERT INTO t_xt_menu VALUES ('115e3d38-c04d-43b7-bbd6-1f970783724c', '广告管理', 'xt', 'root,xt,115e3d38-c04d-43b7-bbd6-1f970783724c', '', '4', null, '1', '2016-10-18 12:22:54', null, null);
INSERT INTO t_xt_menu VALUES ('1893b8a1-6fd6-4ec1-bc28-a9c67b1e4a01', '商品类别维护', '9779cbff-cb36-4598-aaab-f9be42349c74', 'root,xt,9779cbff-cb36-4598-aaab-f9be42349c74,1893b8a1-6fd6-4ec1-bc28-a9c67b1e4a01', '/view/goodsManager/goodsCategory.jsp', '4', null, '1', '2016-10-19 15:58:07', null, null);
INSERT INTO t_xt_menu VALUES ('310628ba-3e28-4766-a187-ad732544b2a9', '操作角色管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,310628ba-3e28-4766-a187-ad732544b2a9', '/view/system/role.jsp', '4', null, '1', '2016-10-13 15:20:06', '2016-10-13 15:25:20', null);
INSERT INTO t_xt_menu VALUES ('32cc9f12-65c1-4f0d-bcc2-6bbc43190dcf', '系统菜单管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,32cc9f12-65c1-4f0d-bcc2-6bbc43190dcf', '/view/system/tree.jsp', '1', null, '1', '2016-10-13 15:24:40', '2016-10-13 15:32:53', null);
INSERT INTO t_xt_menu VALUES ('41606f0c-e3f4-4363-8dcb-62f1f534d3ea', '评论管理', 'xt', 'root,xt,41606f0c-e3f4-4363-8dcb-62f1f534d3ea', '', '3', null, '1', '2016-10-18 12:22:12', null, null);
INSERT INTO t_xt_menu VALUES ('4995b0ca-2523-4fcb-a886-89c515b08a39', '商品维护', '9779cbff-cb36-4598-aaab-f9be42349c74', 'root,xt,9779cbff-cb36-4598-aaab-f9be42349c74,4995b0ca-2523-4fcb-a886-89c515b08a39', '/view/goodsManager/goods.jsp', '2', null, '1', '2016-10-13 15:36:52', '2016-10-18 11:26:19', null);
INSERT INTO t_xt_menu VALUES ('53fc11af-d8f7-49bc-a35a-98df87cc987e', '操作权限管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,53fc11af-d8f7-49bc-a35a-98df87cc987e', '/view/system/role_menu_config.jsp', '3', null, '1', '2016-10-13 15:21:16', null, null);
INSERT INTO t_xt_menu VALUES ('59a326f9-31a5-4bb7-b973-e51fe379b48c', '订单管理', 'xt', 'root,xt,59a326f9-31a5-4bb7-b973-e51fe379b48c', '', '2', null, '1', '2016-10-13 14:12:52', '2016-10-13 14:13:01', null);
INSERT INTO t_xt_menu VALUES ('5a7bfa0e-05cc-4204-b3c9-1e9a7c796398', '商品入库', '9779cbff-cb36-4598-aaab-f9be42349c74', 'root,xt,9779cbff-cb36-4598-aaab-f9be42349c74,5a7bfa0e-05cc-4204-b3c9-1e9a7c796398', '/view/goodsManager/goodsStorage.jsp', '3', null, '1', '2016-10-18 12:21:36', '2016-11-01 12:13:25', null);
INSERT INTO t_xt_menu VALUES ('65c489f1-6f86-4d82-a381-45be4dedf09b', '订单状态变更', '59a326f9-31a5-4bb7-b973-e51fe379b48c', 'root,xt,59a326f9-31a5-4bb7-b973-e51fe379b48c,65c489f1-6f86-4d82-a381-45be4dedf09b', '1', '3', null, '1', '2016-10-13 15:51:54', null, null);
INSERT INTO t_xt_menu VALUES ('6c0167ad-89da-4857-865e-54c7fe8dde4c', '基础设置', 'xt', 'root,xt,6c0167ad-89da-4857-865e-54c7fe8dde4c', '', '5', null, '1', '2016-10-13 15:52:17', '2016-10-18 12:22:05', null);
INSERT INTO t_xt_menu VALUES ('6e5174f8-29c5-4cc2-9ec3-4b7f5608bfc3', '查询统计', 'xt', 'root,xt,6e5174f8-29c5-4cc2-9ec3-4b7f5608bfc3', '', '6', null, '1', '2016-10-18 12:24:07', null, null);
INSERT INTO t_xt_menu VALUES ('727df0ce-48c9-4744-b8ec-4c3115fca915', '活动管理', '115e3d38-c04d-43b7-bbd6-1f970783724c', 'root,xt,115e3d38-c04d-43b7-bbd6-1f970783724c,727df0ce-48c9-4744-b8ec-4c3115fca915', '1', '2', null, '1', '2016-10-18 12:23:28', null, null);
INSERT INTO t_xt_menu VALUES ('7846b141-65c8-4eb9-9fc5-ae63bb236836', '权限管理', 'xt', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836', '', '9', null, '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', null);
INSERT INTO t_xt_menu VALUES ('80cbe605-3dc6-445b-a79d-5d20f404cb2f', '订单查询', '59a326f9-31a5-4bb7-b973-e51fe379b48c', 'root,xt,59a326f9-31a5-4bb7-b973-e51fe379b48c,80cbe605-3dc6-445b-a79d-5d20f404cb2f', '1', '1', null, '1', '2016-10-13 14:13:50', '2016-10-13 15:51:16', null);
INSERT INTO t_xt_menu VALUES ('9779cbff-cb36-4598-aaab-f9be42349c74', '商品管理', 'xt', 'root,xt,9779cbff-cb36-4598-aaab-f9be42349c74', '', '1', null, '1', '2016-10-13 14:14:57', '2016-10-13 15:36:13', null);
INSERT INTO t_xt_menu VALUES ('ab88f2b9-d108-4f8b-9d8a-c43181c62ae9', '字典管理', '6c0167ad-89da-4857-865e-54c7fe8dde4c', 'root,xt,6c0167ad-89da-4857-865e-54c7fe8dde4c,ab88f2b9-d108-4f8b-9d8a-c43181c62ae9', '1', '1', null, '1', '2016-10-13 15:52:44', null, null);
INSERT INTO t_xt_menu VALUES ('afa5485d-5d1b-45fb-8d8d-57b7a83fbbe5', '操作日志查询', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,afa5485d-5d1b-45fb-8d8d-57b7a83fbbe5', '/view/log/log.jsp', '6', null, '1', '2016-10-13 14:15:31', '2016-10-13 15:20:23', null);
INSERT INTO t_xt_menu VALUES ('c690495e-d8b5-4765-8dc5-6e771b246c24', '评论维护', '41606f0c-e3f4-4363-8dcb-62f1f534d3ea', 'root,xt,41606f0c-e3f4-4363-8dcb-62f1f534d3ea,c690495e-d8b5-4765-8dc5-6e771b246c24', '1', '1', null, '1', '2016-10-18 12:22:35', null, null);
INSERT INTO t_xt_menu VALUES ('de21e498-6d9e-47d1-b724-0205263609fd', '操作角色人员管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,de21e498-6d9e-47d1-b724-0205263609fd', '/view/system/role_user_config.jsp', '5', null, '1', '2016-10-13 15:33:59', '2016-10-13 15:34:07', null);
INSERT INTO t_xt_menu VALUES ('e40ec219-da7b-441f-9f41-cd95aa79e874', '物流信息录入', '59a326f9-31a5-4bb7-b973-e51fe379b48c', 'root,xt,59a326f9-31a5-4bb7-b973-e51fe379b48c,e40ec219-da7b-441f-9f41-cd95aa79e874', '1', '2', null, '1', '2016-10-13 15:51:26', '2016-10-13 15:51:29', null);
INSERT INTO t_xt_menu VALUES ('f4e5a984-a02e-4c12-b903-3801d6d8e3c8', '操作人员管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,f4e5a984-a02e-4c12-b903-3801d6d8e3c8', '/view/system/user.jsp', '2', null, '1', '2016-10-13 15:19:23', '2016-10-13 15:32:58', null);
INSERT INTO t_xt_menu VALUES ('f72fb176-0bba-452f-95a1-dd82c91dd374', '广告录入', '115e3d38-c04d-43b7-bbd6-1f970783724c', 'root,xt,115e3d38-c04d-43b7-bbd6-1f970783724c,f72fb176-0bba-452f-95a1-dd82c91dd374', '1', '1', null, '1', '2016-10-18 12:23:07', null, null);
INSERT INTO t_xt_menu VALUES ('fa8b8df9-2559-4f48-b1c3-5ee8b21a58f1', '商品录入', '9779cbff-cb36-4598-aaab-f9be42349c74', 'root,xt,9779cbff-cb36-4598-aaab-f9be42349c74,fa8b8df9-2559-4f48-b1c3-5ee8b21a58f1', '/view/goodsManager/goodsAdd.jsp', '1', null, '1', '2016-10-13 14:15:07', '2016-10-13 15:36:36', null);
INSERT INTO t_xt_menu VALUES ('xt', '系统', 'root', 'root,xt', null, '1', null, '1', '2016-10-13 14:01:05', null, null);
/*INSERT INTO t_xt_menu VALUES ('0ff033aa-51fe-4600-a113-d771a03f08fa', '菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,0ff033aa-51fe-4600-a113-d771a03f08fa', '/view/system/tree.jsp', '3', 'tab', '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', '/images/logos/caidanguanli.png');*/
/*INSERT INTO t_xt_menu VALUES ('2456aacc-2776-49ad-b8ce-b7a893703033', '角色管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,2456aacc-2776-49ad-b8ce-b7a893703033', '/view/system/role.jsp', '4', 'tab', '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', '/images/logos/jiaoseguanli.png');*/
/*INSERT INTO t_xt_menu VALUES ('48d1dc9a-e401-417e-91fe-2bd7baab78c8', '账户管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,48d1dc9a-e401-417e-91fe-2bd7baab78c8', '/view/system/user.jsp', '1', null, '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', null);*/
/*INSERT INTO t_xt_menu VALUES ('b782816d-cb7e-40ce-beab-949d6d7b8512', '角色菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,b782816d-cb7e-40ce-beab-949d6d7b8512', '/view/system/role_menu_config.jsp', '5', 'tab', '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', '/images/logos/jiaosecaidanguanli.png');*/
/*INSERT INTO t_xt_menu VALUES ('ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '人员权限管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '', '99', null, '1', '2016-10-13 14:01:05', '2016-10-13 15:20:17', null);*/
/*INSERT INTO t_xt_menu VALUES ('e9f55cad-2593-427c-9427-48ebeee935cf', '角色人员管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,e9f55cad-2593-427c-9427-48ebeee935cf', '/view/system/role_user_config.jsp', '6', 'tab', '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', '/images/logos/jiaoserenyuanguanli.png');*/
/*INSERT INTO t_xt_menu VALUES ('fd8fc71c-d011-4484-9e39-bedd4bcd7506', '机构管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,fd8fc71c-d011-4484-9e39-bedd4bcd7506', '/view/system/orgtree.jsp', '2', 'tab', '1', '2016-10-13 14:01:05', '2016-10-13 14:01:05', '/images/logos/jigouguanli.png');*/

-- ----------------------------
-- Records of t_xt_org
-- ----------------------------
INSERT INTO t_xt_org VALUES ('097e835f-52cd-412a-984b-8c8b06e0fcca', '001002', '人力资源部', '001', '2', null, null, '人力资源部', '企业1/人力资源部', '2');
INSERT INTO t_xt_org VALUES ('81277dc5-6c4b-4246-97b9-47aafcb3d573', '001', '所有机构', 'root', '1', '1', null, '所有机构', '所有机构', '0');
INSERT INTO t_xt_org VALUES ('9ee3fed0-d4cb-414b-959b-e381dc0f18b5', '001001', '办公室', '001', '1', null, null, '办公室', '企业1/办公室', '2');

-- ----------------------------
-- Records of t_xt_position
-- ----------------------------
INSERT INTO t_xt_position VALUES ('5bfad0ff-8227-4979-a5ad-6435564b020b', '系统管理员', '系统管理员');

-- ----------------------------
-- Records of t_xt_role
-- ----------------------------
INSERT INTO t_xt_role VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '普通用户', '0', '普通用户', null, '1', '2016-10-13 14:01:06', '2016-10-13 14:01:06');
INSERT INTO t_xt_role VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '系统管理员', '0', null, null, '1', '2016-10-13 14:01:06', '2016-10-13 14:01:06');

-- ----------------------------
-- Records of t_xt_role_menu
-- ----------------------------
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '4995b0ca-2523-4fcb-a886-89c515b08a39', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '59a326f9-31a5-4bb7-b973-e51fe379b48c', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '65c489f1-6f86-4d82-a381-45be4dedf09b', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '6c0167ad-89da-4857-865e-54c7fe8dde4c', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '80cbe605-3dc6-445b-a79d-5d20f404cb2f', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '9779cbff-cb36-4598-aaab-f9be42349c74', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'ab88f2b9-d108-4f8b-9d8a-c43181c62ae9', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'e40ec219-da7b-441f-9f41-cd95aa79e874', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'fa8b8df9-2559-4f48-b1c3-5ee8b21a58f1', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'xt', '1', '2016-10-13 16:15:37', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '007ac06f-aadc-4eb5-9af9-d085ad56aafc', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '115e3d38-c04d-43b7-bbd6-1f970783724c', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '1893b8a1-6fd6-4ec1-bc28-a9c67b1e4a01', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '310628ba-3e28-4766-a187-ad732544b2a9', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '32cc9f12-65c1-4f0d-bcc2-6bbc43190dcf', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '41606f0c-e3f4-4363-8dcb-62f1f534d3ea', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '4995b0ca-2523-4fcb-a886-89c515b08a39', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '53fc11af-d8f7-49bc-a35a-98df87cc987e', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '59a326f9-31a5-4bb7-b973-e51fe379b48c', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '5a7bfa0e-05cc-4204-b3c9-1e9a7c796398', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '65c489f1-6f86-4d82-a381-45be4dedf09b', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '6c0167ad-89da-4857-865e-54c7fe8dde4c', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '6e5174f8-29c5-4cc2-9ec3-4b7f5608bfc3', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '727df0ce-48c9-4744-b8ec-4c3115fca915', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '7846b141-65c8-4eb9-9fc5-ae63bb236836', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '80cbe605-3dc6-445b-a79d-5d20f404cb2f', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', '9779cbff-cb36-4598-aaab-f9be42349c74', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'ab88f2b9-d108-4f8b-9d8a-c43181c62ae9', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'afa5485d-5d1b-45fb-8d8d-57b7a83fbbe5', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'c690495e-d8b5-4765-8dc5-6e771b246c24', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'de21e498-6d9e-47d1-b724-0205263609fd', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'e40ec219-da7b-441f-9f41-cd95aa79e874', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'f4e5a984-a02e-4c12-b903-3801d6d8e3c8', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'f72fb176-0bba-452f-95a1-dd82c91dd374', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'fa8b8df9-2559-4f48-b1c3-5ee8b21a58f1', '1', '2016-11-01 12:22:30', null);
INSERT INTO t_xt_role_menu VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'xt', '1', '2016-11-01 12:22:30', null);

-- ----------------------------
-- Records of t_xt_role_user
-- ----------------------------
INSERT INTO t_xt_role_user VALUES ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'admin', '0', '2016-10-13 14:01:08', '2016-10-13 16:15:21');
INSERT INTO t_xt_role_user VALUES ('e97b1283-8fc2-44d5-9627-18795097df31', 'admin', '1', '2016-10-13 14:01:08', null);