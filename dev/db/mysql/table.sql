create table SYS_CODE
(
  ID     VARCHAR(36) not null,
  TYPE   VARCHAR(100),
  CODE   VARCHAR(100),
  LABEL  VARCHAR(200),
  REMARK VARCHAR(200),
  FLAG   CHAR(1)
);
alter table SYS_CODE
  add constraint PK_SYS_CODE primary key (ID);

create table T_PB_DATASOURCE
(
  ID             VARCHAR(36) not null,
  SOURCE_NAME    VARCHAR(100),
  SOURCE_REMARK  VARCHAR(600),
  SOURCE_SQL     VARCHAR(4000),
  CREATE_USER_ID VARCHAR(36) not null,
  WHERE_SQL      VARCHAR(4000),
  FLAG           CHAR(1) default '1',
  CREATE_TIME    DATETIME not null,
  UPDATE_TIME    DATETIME
)
;
alter table T_PB_DATASOURCE
  add primary key (ID);

create table T_PB_UPLOADFILE
(
  FILE_ID        VARCHAR(36) not null,
  PARENT_FILE_ID INTEGER,
  FILE_NAME      VARCHAR(240) not null,
  FILE_TYPE      VARCHAR(24),
  FILE_SIZE      INTEGER default 0 not null,
  FILE_PATH      VARCHAR(4000) not null,
  UP_TYPE        VARCHAR(24),
  LRRY_DM        VARCHAR(50) not null,
  USER_TYPE      VARCHAR(12) not null,
  XZFW           VARCHAR(50),
  YX_BJ          CHAR(1) default 'N' not null,
  LR_SJ          DATETIME not null,
  XG_SJ          DATETIME,
  GROUP_ID       VARCHAR(36)
)
;
alter table T_PB_UPLOADFILE
  add primary key (FILE_ID);

create table T_XT_CACHE_TABLE
(
  ID             VARCHAR(36) not null,
  CACHE_TYPE     VARCHAR(200),
  TABLE_NAME     VARCHAR(60),
  QUERY_SQL      VARCHAR(4000),
  CREATE_USER_ID VARCHAR(36),
  CREATE_ORG_ID  VARCHAR(36),
  FLAG           CHAR(1),
  CREATE_TIME    DATETIME,
  UPDATE_TIME    DATETIME
)
;
alter table T_XT_CACHE_TABLE
  add primary key (ID);

create table T_XT_EMP
(
  ID              VARCHAR(36) not null,
  USER_ID         VARCHAR(36) not null,
  USER_PWD        VARCHAR(36),
  USER_NAME       VARCHAR(200),
  ORG_ID          VARCHAR(36),
  WORK_ADDR       VARCHAR(2000),
  TELPHONE        VARCHAR(50),
  MOBILE          VARCHAR(11),
  FAX             VARCHAR(50),
  SEX             CHAR(1),
  EMAIL           VARCHAR(50),
  REMARK          VARCHAR(500),
  POSITION_ID     VARCHAR(36),
  VALIDATE_DOMAIN VARCHAR(72),
  VALIDATE_IP     VARCHAR(15),
  PCUSERNAME      VARCHAR(100),
  FLAG            CHAR(1)
)
;
alter table T_XT_EMP
  add primary key (ID);

create table T_XT_GROUP
(
  ID            VARCHAR(36) not null,
  GROUP_NAME    VARCHAR(60) not null,
  BUSINESS_TYPE VARCHAR(60) not null,
  FLAG          CHAR(1) default '1' not null,
  CREATE_TIME   DATETIME not null,
  UPDATE_TIME   DATETIME
)
;
alter table T_XT_GROUP
  add primary key (ID);

create table T_XT_GROUP_USER
(
  ID            VARCHAR(36) not null,
  USER_ID       VARCHAR(36) not null,
  USER_TYPE     CHAR(1) not null,
  GROUP_ID      VARCHAR(36),
  BUSINESS_TYPE VARCHAR(60) not null,
  FLAG          CHAR(1) default '1' not null,
  CREATE_TIME   DATETIME not null,
  UPDATE_TIME   DATETIME
)
;
alter table T_XT_GROUP_USER
  add primary key (ID);

create table T_XT_KEY_VALUE
(
  KV_KEY   VARCHAR(200) not null,
  KV_VALUE VARCHAR(2000),
  YX_BJ    CHAR(1) default '1',
  LR_SJ    DATETIME not null,
  XG_SJ    DATETIME
)
;
alter table T_XT_KEY_VALUE
  add primary key (KV_KEY);

create table T_XT_MENU
(
  MENU_ID         VARCHAR(36) not null,
  MENU_NAME       VARCHAR(400) not null,
  MENU_PARENT_ID  VARCHAR(36) not null,
  MENU_PARENT_IDS VARCHAR(2000) not null,
  URL             VARCHAR(2000),
  PX_XH           INTEGER,
  OPEN_METHOD     VARCHAR(24),
  YX_BJ           CHAR(1) default '1',
  LR_SJ           DATETIME not null,
  XG_SJ           DATETIME,
  LOGO            VARCHAR(200)
)
;
alter table T_XT_MENU
  add primary key (MENU_ID);

create table T_XT_MESSAGE
(
  ID               VARCHAR(36) primary key not null,
  BATCH_ID         VARCHAR(36),
  REPLY_MESSAGE_ID VARCHAR(36),
  TO_USER_ID       VARCHAR(36) not null,
  TITLE            VARCHAR(36) not null,
  MESSAGE          VARCHAR(4000) not null,
  MESSAGE_TYPE     CHAR(1) not null,
  MESSAGE_STATUS   INTEGER default 0 not null,
  MESSAGE_LEVEL    INTEGER not null,
  URL              VARCHAR(1000),
  CREATE_USER_ID   VARCHAR(36) not null,
  FLAG             CHAR(1) default '1',
  CREATE_TIME      DATETIME,
  UPDATE_TIME      DATETIME
)
;

create table T_XT_ORG
(
  ID            VARCHAR(36) not null,
  ORG_ID        VARCHAR(50),
  ORG_NAME      VARCHAR(500),
  PARENT_ORG_ID VARCHAR(36),
  ORDER_        int,
  ROOT          CHAR(1),
  ORG_CODE      VARCHAR(36),
  SHORT_NAME    VARCHAR(500),
  ORG_PATH      VARCHAR(1000),
  ORG_LEVEL     int
)
;
alter table T_XT_ORG
  add primary key (ID);

create table T_XT_POSITION
(
  POSITION_ID   VARCHAR(36) not null,
  POSITION_NAME VARCHAR(200),
  POSITION_DESC VARCHAR(200)
)
;
alter table T_XT_POSITION
  add primary key (POSITION_ID);

create table T_XT_ROLE
(
  ROLE_ID          VARCHAR(36) not null,
  ROLE_NAME        VARCHAR(200) not null,
  ROLE_TYPE        CHAR(1) not null,
  ROLE_REMARK      VARCHAR(2000),
  ROLE_STATE_VALUE VARCHAR(12),
  YX_BJ            CHAR(1) default '1',
  LR_SJ            DATETIME not null,
  XG_SJ            DATETIME
)
;
alter table T_XT_ROLE
  add primary key (ROLE_ID);

create table T_XT_ROLE_MENU
(
  ROLE_ID VARCHAR(36) not null,
  MENU_ID VARCHAR(36) not null,
  YX_BJ   CHAR(1) default '1',
  LR_SJ   DATETIME not null,
  XG_SJ   DATETIME
)
;
alter table T_XT_ROLE_MENU
  add constraint PK_T_XT_ROLE_MENU primary key (ROLE_ID, MENU_ID);

create table T_XT_ROLE_USER
(
  ROLE_ID VARCHAR(36) not null,
  U_ID    VARCHAR(36) not null,
  YX_BJ   CHAR(1) default '1',
  LR_SJ   DATETIME not null,
  XG_SJ   DATETIME
)
;
alter table T_XT_ROLE_USER
  add constraint PK_T_XT_ROLE_USER primary key (ROLE_ID, U_ID);

create table T_XT_USER
(
  U_ID     VARCHAR(36) not null,
  U_PWD    VARCHAR(36),
  U_NAME   VARCHAR(200),
  U_REMARK VARCHAR(2000),
  YX_BJ    CHAR(1) default '1',
  LR_SJ    DATETIME not null,
  XG_SJ    DATETIME,
  ORDER_   int
)
;
alter table T_XT_USER
  add primary key (U_ID);

create table T_XT_WORKDAY
(
  DAY         VARCHAR(10) not null,
  WORKDAY     CHAR(1),
  REMARK      VARCHAR(200),
  FLAG        CHAR(1) default '1',
  CREATE_TIME DATETIME,
  UPDATE_TIME DATETIME
)
;
alter table T_XT_WORKDAY
  add primary key (DAY);

insert into T_XT_EMP (ID, USER_ID, USER_PWD, USER_NAME, ORG_ID, WORK_ADDR, TELPHONE, MOBILE, FAX, SEX, EMAIL, REMARK, POSITION_ID, VALIDATE_DOMAIN, VALIDATE_IP, PCUSERNAME, FLAG)
values ('d6ce7860-a122-41bd-80d7-cb80f437044c', 'admin', '123456', '系统管理员', '81277dc5-6c4b-4246-97b9-47aafcb3d573', null, null, null, null, '0', null, null, '5bfad0ff-8227-4979-a5ad-6435564b020b', 'YANGCW-PC', '127.0.0.1', null, '1');
commit;

insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('fd8fc71c-d011-4484-9e39-bedd4bcd7506', '机构管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,fd8fc71c-d011-4484-9e39-bedd4bcd7506', '/view/system/orgtree.jsp', 2, 'tab', '1', now(), now(), '/images/logos/jigouguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('0ff033aa-51fe-4600-a113-d771a03f08fa', '菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,0ff033aa-51fe-4600-a113-d771a03f08fa', '/view/system/tree.jsp', 3, 'tab', '1', now(),now(), '/images/logos/caidanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('b782816d-cb7e-40ce-beab-949d6d7b8512', '角色菜单管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,b782816d-cb7e-40ce-beab-949d6d7b8512', '/view/system/role_menu_config.jsp', 5, 'tab', '1', now(),now(), '/images/logos/jiaosecaidanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('2456aacc-2776-49ad-b8ce-b7a893703033', '角色管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,2456aacc-2776-49ad-b8ce-b7a893703033', '/view/system/role.jsp', 4, 'tab', '1', now(),now(), '/images/logos/jiaoseguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('e9f55cad-2593-427c-9427-48ebeee935cf', '角色人员管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,e9f55cad-2593-427c-9427-48ebeee935cf', '/view/system/role_user_config.jsp', 6, 'tab', '1', now(),now(), '/images/logos/jiaoserenyuanguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '人员权限管理', '7846b141-65c8-4eb9-9fc5-ae63bb236836', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', null, 1, null, '1', now(), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('48d1dc9a-e401-417e-91fe-2bd7baab78c8', '用户管理', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836,ca795db5-52ce-4fc8-a02d-ea0da2adb8b1,48d1dc9a-e401-417e-91fe-2bd7baab78c8', '/view/system/user.jsp', 1, 'tab', '1', now(),now(), '/images/logos/yonghuguanli.png');
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('xt', '系统', 'root', 'root,xt', null, 1, null, '1', now(), null, null);
insert into T_XT_MENU (MENU_ID, MENU_NAME, MENU_PARENT_ID, MENU_PARENT_IDS, URL, PX_XH, OPEN_METHOD, YX_BJ, LR_SJ, XG_SJ, LOGO)
values ('7846b141-65c8-4eb9-9fc5-ae63bb236836', '系统管理', 'xt', 'root,xt,7846b141-65c8-4eb9-9fc5-ae63bb236836', null, 6, 'tab', '1', now(),now(), '/images/logos/4-xitongguanli.png');
commit;

insert into T_XT_ORG (ID, ORG_ID, ORG_NAME, PARENT_ORG_ID, ORDER_, ROOT, ORG_CODE, SHORT_NAME, ORG_PATH, ORG_LEVEL)
values ('9ee3fed0-d4cb-414b-959b-e381dc0f18b5', '001001', '办公室', '001', 1, null, null, '办公室', '企业1/办公室', 2);
insert into T_XT_ORG (ID, ORG_ID, ORG_NAME, PARENT_ORG_ID, ORDER_, ROOT, ORG_CODE, SHORT_NAME, ORG_PATH, ORG_LEVEL)
values ('097e835f-52cd-412a-984b-8c8b06e0fcca', '001002', '人力资源部', '001', 2, null, null, '人力资源部', '企业1/人力资源部', 2);
insert into T_XT_ORG (ID, ORG_ID, ORG_NAME, PARENT_ORG_ID, ORDER_, ROOT, ORG_CODE, SHORT_NAME, ORG_PATH, ORG_LEVEL)
values ('81277dc5-6c4b-4246-97b9-47aafcb3d573', '001', '所有机构', 'root', 1, '1', null, '所有机构', '所有机构', 0);
commit;

insert into T_XT_POSITION (POSITION_ID, POSITION_NAME, POSITION_DESC)
values ('5bfad0ff-8227-4979-a5ad-6435564b020b', '系统管理员', '系统管理员');
commit;

insert into T_XT_ROLE (ROLE_ID, ROLE_NAME, ROLE_TYPE, ROLE_REMARK, ROLE_STATE_VALUE, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '系统管理员', '0', null, null, '1', now(),now());
insert into T_XT_ROLE (ROLE_ID, ROLE_NAME, ROLE_TYPE, ROLE_REMARK, ROLE_STATE_VALUE, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '普通用户', '0', '普通用户', null, '1', now(),now());
commit;

insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'xt', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '7846b141-65c8-4eb9-9fc5-ae63bb236836', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '48d1dc9a-e401-417e-91fe-2bd7baab78c8', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'fd8fc71c-d011-4484-9e39-bedd4bcd7506', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '0ff033aa-51fe-4600-a113-d771a03f08fa', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', '2456aacc-2776-49ad-b8ce-b7a893703033', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'b782816d-cb7e-40ce-beab-949d6d7b8512', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'e9f55cad-2593-427c-9427-48ebeee935cf', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'xt', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '7846b141-65c8-4eb9-9fc5-ae63bb236836', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'ca795db5-52ce-4fc8-a02d-ea0da2adb8b1', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '48d1dc9a-e401-417e-91fe-2bd7baab78c8', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'fd8fc71c-d011-4484-9e39-bedd4bcd7506', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '0ff033aa-51fe-4600-a113-d771a03f08fa', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', '2456aacc-2776-49ad-b8ce-b7a893703033', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'b782816d-cb7e-40ce-beab-949d6d7b8512', '1', now(), null);
insert into T_XT_ROLE_MENU (ROLE_ID, MENU_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'e9f55cad-2593-427c-9427-48ebeee935cf', '1', now(), null);
commit;

insert into T_XT_ROLE_USER (ROLE_ID, U_ID, YX_BJ, LR_SJ, XG_SJ)
values ('e97b1283-8fc2-44d5-9627-18795097df31', 'admin', '1', now(), null);
insert into T_XT_ROLE_USER (ROLE_ID, U_ID, YX_BJ, LR_SJ, XG_SJ)
values ('7ca85c1b-d710-4eb7-b284-49f607fdd586', 'admin', '1', now(),now());
commit;

insert into T_XT_USER (U_ID, U_PWD, U_NAME, U_REMARK, YX_BJ, LR_SJ, XG_SJ, ORDER_)
values ('admin', '123456', '系统管理员', null, '1', now(), null, null);
commit;