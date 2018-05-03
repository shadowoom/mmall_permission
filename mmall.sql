-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: mmall
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mmall_cart`
--

DROP TABLE IF EXISTS `mmall_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_cart`
--

LOCK TABLES `mmall_cart` WRITE;
/*!40000 ALTER TABLE `mmall_cart` DISABLE KEYS */;
INSERT INTO `mmall_cart` VALUES (126,21,26,1,1,'2017-04-13 21:27:06','2017-04-13 21:27:06');
/*!40000 ALTER TABLE `mmall_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_category`
--

DROP TABLE IF EXISTS `mmall_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100034 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_category`
--

LOCK TABLES `mmall_category` WRITE;
/*!40000 ALTER TABLE `mmall_category` DISABLE KEYS */;
INSERT INTO `mmall_category` VALUES (100001,0,'家用电器',1,NULL,'2017-03-25 16:46:00','2017-03-25 16:46:00'),(100002,0,'数码3C',1,NULL,'2017-03-25 16:46:21','2017-03-25 16:46:21'),(100003,0,'服装箱包',1,NULL,'2017-03-25 16:49:53','2017-03-25 16:49:53'),(100004,0,'食品生鲜',1,NULL,'2017-03-25 16:50:19','2017-03-25 16:50:19'),(100005,0,'酒水饮料',1,NULL,'2017-03-25 16:50:29','2017-03-25 16:50:29'),(100006,100001,'冰箱',1,NULL,'2017-03-25 16:52:15','2017-03-25 16:52:15'),(100007,100001,'电视',1,NULL,'2017-03-25 16:52:26','2017-03-25 16:52:26'),(100008,100001,'洗衣机',1,NULL,'2017-03-25 16:52:39','2017-03-25 16:52:39'),(100009,100001,'空调',1,NULL,'2017-03-25 16:52:45','2017-03-25 16:52:45'),(100010,100001,'电热水器',1,NULL,'2017-03-25 16:52:54','2017-03-25 16:52:54'),(100011,100002,'电脑',1,NULL,'2017-03-25 16:53:18','2017-03-25 16:53:18'),(100012,100002,'手机',1,NULL,'2017-03-25 16:53:27','2017-03-25 16:53:27'),(100013,100002,'平板电脑',1,NULL,'2017-03-25 16:53:35','2017-03-25 16:53:35'),(100014,100002,'数码相机',1,NULL,'2017-03-25 16:53:56','2017-03-25 16:53:56'),(100015,100002,'3C配件',1,NULL,'2017-03-25 16:54:07','2017-03-25 16:54:07'),(100016,100003,'女装',1,NULL,'2017-03-25 16:54:44','2017-03-25 16:54:44'),(100017,100003,'帽子',1,NULL,'2017-03-25 16:54:51','2017-03-25 16:54:51'),(100018,100003,'旅行箱',1,NULL,'2017-03-25 16:55:02','2017-03-25 16:55:02'),(100019,100003,'手提包',1,NULL,'2017-03-25 16:55:09','2017-03-25 16:55:09'),(100020,100003,'保暖内衣',1,NULL,'2017-03-25 16:55:18','2017-03-25 16:55:18'),(100021,100004,'零食',1,NULL,'2017-03-25 16:55:30','2017-03-25 16:55:30'),(100022,100004,'生鲜',1,NULL,'2017-03-25 16:55:37','2017-03-25 16:55:37'),(100023,100004,'半成品菜',1,NULL,'2017-03-25 16:55:47','2017-03-25 16:55:47'),(100024,100004,'速冻食品',1,NULL,'2017-03-25 16:55:56','2017-03-25 16:55:56'),(100025,100004,'进口食品',1,NULL,'2017-03-25 16:56:06','2017-03-25 16:56:06'),(100026,100005,'白酒',1,NULL,'2017-03-25 16:56:22','2017-03-25 16:56:22'),(100027,100005,'红酒',1,NULL,'2017-03-25 16:56:30','2017-03-25 16:56:30'),(100028,100005,'饮料',1,NULL,'2017-03-25 16:56:37','2017-03-25 16:56:37'),(100029,100005,'调制鸡尾酒',1,NULL,'2017-03-25 16:56:45','2017-03-25 16:56:45'),(100030,100005,'进口洋酒',1,NULL,'2017-03-25 16:57:05','2017-03-25 16:57:05'),(100031,0,'台式电脑',1,NULL,'2018-04-16 22:32:44','2018-04-16 23:40:40'),(100032,0,'workstation',1,NULL,'2018-04-16 23:20:34','2018-04-16 23:41:22'),(100033,0,'手提电脑',1,NULL,'2018-04-16 23:20:40','2018-04-16 23:20:40');
/*!40000 ALTER TABLE `mmall_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_order`
--

DROP TABLE IF EXISTS `mmall_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_order`
--

LOCK TABLES `mmall_order` WRITE;
/*!40000 ALTER TABLE `mmall_order` DISABLE KEYS */;
INSERT INTO `mmall_order` VALUES (103,1491753014256,1,25,13998.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-09 23:50:14','2017-04-09 23:50:14'),(104,1491830695216,1,26,13998.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-10 21:24:55','2017-04-10 21:24:55'),(105,1492089528889,1,29,3299.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:18:48','2017-04-13 21:18:48'),(106,1492090946105,1,29,27894.00,1,0,20,'2017-04-13 21:42:40',NULL,NULL,NULL,'2017-04-13 21:42:26','2017-04-13 21:42:41'),(107,1492091003128,1,29,8597.00,1,0,20,'2017-04-13 21:43:38',NULL,NULL,NULL,'2017-04-13 21:43:23','2017-04-13 21:43:38'),(108,1492091051313,1,29,1999.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:11','2017-04-13 21:44:11'),(109,1492091061513,1,29,6598.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:21','2017-04-13 21:44:21'),(110,1492091069563,1,29,3299.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:29','2017-04-13 21:44:29'),(111,1492091076073,1,29,4299.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:36','2017-04-13 21:44:36'),(112,1492091083720,1,29,3299.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:43','2017-04-13 21:44:43'),(113,1492091089794,1,29,6999.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:49','2017-04-13 21:44:49'),(114,1492091096400,1,29,6598.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:44:56','2017-04-13 21:44:56'),(115,1492091102371,1,29,3299.00,1,0,10,NULL,NULL,NULL,NULL,'2017-04-13 21:45:02','2017-04-13 21:45:02'),(116,1492091110004,1,29,8598.00,1,0,40,'2017-04-13 21:55:16','2017-04-13 21:55:31',NULL,NULL,'2017-04-13 21:45:09','2017-04-13 21:55:31'),(117,1492091141269,1,29,22894.00,1,0,20,'2017-04-13 21:46:06',NULL,NULL,NULL,'2017-04-13 21:45:41','2017-04-13 21:46:07');
/*!40000 ALTER TABLE `mmall_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_order_item`
--

DROP TABLE IF EXISTS `mmall_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_order_item`
--

LOCK TABLES `mmall_order_item` WRITE;
/*!40000 ALTER TABLE `mmall_order_item` DISABLE KEYS */;
INSERT INTO `mmall_order_item` VALUES (113,1,1491753014256,26,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',6999.00,2,13998.00,'2017-04-09 23:50:14','2017-04-09 23:50:14'),(114,1,1491830695216,26,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',6999.00,2,13998.00,'2017-04-10 21:24:55','2017-04-10 21:24:55'),(115,1,1492089528889,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:18:48','2017-04-13 21:18:48'),(116,1,1492090946105,29,'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg',4299.00,2,8598.00,'2017-04-13 21:42:26','2017-04-13 21:42:26'),(117,1,1492090946105,28,'4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg',1999.00,1,1999.00,'2017-04-13 21:42:26','2017-04-13 21:42:26'),(118,1,1492090946105,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:42:26','2017-04-13 21:42:26'),(119,1,1492090946105,26,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',6999.00,2,13998.00,'2017-04-13 21:42:26','2017-04-13 21:42:26'),(120,1,1492091003128,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,2,6598.00,'2017-04-13 21:43:23','2017-04-13 21:43:23'),(121,1,1492091003128,28,'4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg',1999.00,1,1999.00,'2017-04-13 21:43:23','2017-04-13 21:43:23'),(122,1,1492091051313,28,'4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg',1999.00,1,1999.00,'2017-04-13 21:44:11','2017-04-13 21:44:11'),(123,1,1492091061513,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,2,6598.00,'2017-04-13 21:44:21','2017-04-13 21:44:21'),(124,1,1492091069563,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:44:29','2017-04-13 21:44:29'),(125,1,1492091076073,29,'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg',4299.00,1,4299.00,'2017-04-13 21:44:36','2017-04-13 21:44:36'),(126,1,1492091083720,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:44:43','2017-04-13 21:44:43'),(127,1,1492091089794,26,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',6999.00,1,6999.00,'2017-04-13 21:44:49','2017-04-13 21:44:49'),(128,1,1492091096400,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,2,6598.00,'2017-04-13 21:44:56','2017-04-13 21:44:56'),(129,1,1492091102371,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:45:02','2017-04-13 21:45:02'),(130,1,1492091110004,29,'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg',4299.00,2,8598.00,'2017-04-13 21:45:09','2017-04-13 21:45:09'),(131,1,1492091141269,26,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg',6999.00,1,6999.00,'2017-04-13 21:45:41','2017-04-13 21:45:41'),(132,1,1492091141269,27,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg',3299.00,1,3299.00,'2017-04-13 21:45:41','2017-04-13 21:45:41'),(133,1,1492091141269,29,'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg',4299.00,2,8598.00,'2017-04-13 21:45:41','2017-04-13 21:45:41'),(134,1,1492091141269,28,'4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg',1999.00,2,3998.00,'2017-04-13 21:45:41','2017-04-13 21:45:41');
/*!40000 ALTER TABLE `mmall_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_pay_info`
--

DROP TABLE IF EXISTS `mmall_pay_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_pay_info`
--

LOCK TABLES `mmall_pay_info` WRITE;
/*!40000 ALTER TABLE `mmall_pay_info` DISABLE KEYS */;
INSERT INTO `mmall_pay_info` VALUES (53,1,1492090946105,1,'2017041321001004300200116250','WAIT_BUYER_PAY','2017-04-13 21:42:33','2017-04-13 21:42:33'),(54,1,1492090946105,1,'2017041321001004300200116250','TRADE_SUCCESS','2017-04-13 21:42:41','2017-04-13 21:42:41'),(55,1,1492091003128,1,'2017041321001004300200116251','WAIT_BUYER_PAY','2017-04-13 21:43:31','2017-04-13 21:43:31'),(56,1,1492091003128,1,'2017041321001004300200116251','TRADE_SUCCESS','2017-04-13 21:43:38','2017-04-13 21:43:38'),(57,1,1492091141269,1,'2017041321001004300200116252','WAIT_BUYER_PAY','2017-04-13 21:45:59','2017-04-13 21:45:59'),(58,1,1492091141269,1,'2017041321001004300200116252','TRADE_SUCCESS','2017-04-13 21:46:07','2017-04-13 21:46:07'),(59,1,1492091110004,1,'2017041321001004300200116396','WAIT_BUYER_PAY','2017-04-13 21:55:08','2017-04-13 21:55:08'),(60,1,1492091110004,1,'2017041321001004300200116396','TRADE_SUCCESS','2017-04-13 21:55:17','2017-04-13 21:55:17');
/*!40000 ALTER TABLE `mmall_pay_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_product`
--

DROP TABLE IF EXISTS `mmall_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应mmall_category表的主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text COMMENT '图片地址,json格式,扩展用',
  `detail` text COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_product`
--

LOCK TABLES `mmall_product` WRITE;
/*!40000 ALTER TABLE `mmall_product` DISABLE KEYS */;
INSERT INTO `mmall_product` VALUES (26,100002,'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机','iPhone 7，现更以红色呈现。','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg','241997c4-9e62-4824-b7f0-7425c3c28917.jpeg,b6c56eb0-1748-49a9-98dc-bcc4b9788a54.jpeg,92f17532-1527-4563-aa1d-ed01baa0f7b2.jpeg,3adbe4f7-e374-4533-aa79-cc4a98c529bf.jpeg','<p><img alt=\"10000.jpg\" src=\"http://img.happymmall.com/00bce8d4-e9af-4c8d-b205-e6c75c7e252b.jpg\" width=\"790\" height=\"553\"><br></p><p><img alt=\"20000.jpg\" src=\"http://img.happymmall.com/4a70b4b4-01ee-46af-9468-31e67d0995b8.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"30000.jpg\" src=\"http://img.happymmall.com/0570e033-12d7-49b2-88f3-7a5d84157223.jpg\" width=\"790\" height=\"365\"><br></p><p><img alt=\"40000.jpg\" src=\"http://img.happymmall.com/50515c02-3255-44b9-a829-9e141a28c08a.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"50000.jpg\" src=\"http://img.happymmall.com/c138fc56-5843-4287-a029-91cf3732d034.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"60000.jpg\" src=\"http://img.happymmall.com/c92d1f8a-9827-453f-9d37-b10a3287e894.jpg\" width=\"790\" height=\"525\"><br></p><p><br></p><p><img alt=\"TB24p51hgFkpuFjSspnXXb4qFXa-1776456424.jpg\" src=\"http://img.happymmall.com/bb1511fc-3483-471f-80e5-c7c81fa5e1dd.jpg\" width=\"790\" height=\"375\"><br></p><p><br></p><p><img alt=\"shouhou.jpg\" src=\"http://img.happymmall.com/698e6fbe-97ea-478b-8170-008ad24030f7.jpg\" width=\"750\" height=\"150\"><br></p><p><img alt=\"999.jpg\" src=\"http://img.happymmall.com/ee276fe6-5d79-45aa-8393-ba1d210f9c89.jpg\" width=\"790\" height=\"351\"><br></p>',6999.00,9991,1,NULL,'2017-04-13 21:45:41'),(27,100006,'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用','送品牌烤箱，五一大促','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg','ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg,4bb02f1c-62d5-48cc-b358-97b05af5740d.jpeg,36bdb49c-72ae-4185-9297-78829b54b566.jpeg','<p><img alt=\"miaoshu.jpg\" src=\"http://img.happymmall.com/9c5c74e6-6615-4aa0-b1fc-c17a1eff6027.jpg\" width=\"790\" height=\"444\"><br></p><p><img alt=\"miaoshu2.jpg\" src=\"http://img.happymmall.com/31dc1a94-f354-48b8-a170-1a1a6de8751b.jpg\" width=\"790\" height=\"1441\"><img alt=\"miaoshu3.jpg\" src=\"http://img.happymmall.com/7862594b-3063-4b52-b7d4-cea980c604e0.jpg\" width=\"790\" height=\"1442\"><img alt=\"miaoshu4.jpg\" src=\"http://img.happymmall.com/9a650563-dc85-44d6-b174-d6960cfb1d6a.jpg\" width=\"790\" height=\"1441\"><br></p>',3299.00,8876,1,'2017-04-13 18:51:54','2017-04-13 21:45:41'),(28,100012,'4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春','NOVA青春版1999元','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg','0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg,13da2172-4445-4eb5-a13f-c5d4ede8458c.jpeg,58d5d4b7-58d4-4948-81b6-2bae4f79bf02.jpeg','<p><img alt=\"11TB2fKK3cl0kpuFjSsziXXa.oVXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/5c2d1c6d-9e09-48ce-bbdb-e833b42ff664.jpg\" width=\"790\" height=\"966\"><img alt=\"22TB2YP3AkEhnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/9a10b877-818f-4a27-b6f7-62887f3fb39d.jpg\" width=\"790\" height=\"1344\"><img alt=\"33TB2Yyshk.hnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/7d7fbd69-a3cb-4efe-8765-423bf8276e3e.jpg\" width=\"790\" height=\"700\"><img alt=\"TB2diyziB8kpuFjSspeXXc7IpXa_!!1777180618.jpg\" src=\"http://img.happymmall.com/1d7160d2-9dba-422f-b2a0-e92847ba6ce9.jpg\" width=\"790\" height=\"393\"><br></p>',1999.00,9994,1,'2017-04-13 18:57:18','2017-04-13 21:45:41'),(29,100008,'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体','门店机型 德邦送货','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg','173335a4-5dce-4afd-9f18-a10623724c4e.jpeg,42b1b8bc-27c7-4ee1-80ab-753d216a1d49.jpeg,2f1b3de1-1eb1-4c18-8ca2-518934931bec.jpeg','<p><img alt=\"1TB2WLZrcIaK.eBjSspjXXXL.XXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/ffcce953-81bd-463c-acd1-d690b263d6df.jpg\" width=\"790\" height=\"920\"><img alt=\"2TB2zhOFbZCO.eBjSZFzXXaRiVXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/58a7bd25-c3e7-4248-9dba-158ef2a90e70.jpg\" width=\"790\" height=\"1052\"><img alt=\"3TB27mCtb7WM.eBjSZFhXXbdWpXa_!!2114960396.jpg\" src=\"http://img.happymmall.com/2edbe9b3-28be-4a8b-a9c3-82e40703f22f.jpg\" width=\"790\" height=\"820\"><br></p>',4299.00,9993,1,'2017-04-13 19:07:47','2017-04-13 21:45:41');
/*!40000 ALTER TABLE `mmall_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_shipping`
--

DROP TABLE IF EXISTS `mmall_shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_shipping`
--

LOCK TABLES `mmall_shipping` WRITE;
/*!40000 ALTER TABLE `mmall_shipping` DISABLE KEYS */;
INSERT INTO `mmall_shipping` VALUES (4,13,'geely','010','18688888888','北京','北京市','海淀区','中关村','100000','2017-01-22 14:26:25','2017-01-22 14:26:25'),(7,17,'Rosen','13800138000','13800138000','北京','北京',NULL,'中关村','100000','2017-03-29 12:11:01','2017-03-29 12:11:01'),(29,1,'吉利','13800138000','13800138000','北京','北京','海淀区','海淀区中关村','100000','2017-04-09 18:33:32','2017-04-09 18:33:32');
/*!40000 ALTER TABLE `mmall_shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_dept`
--

DROP TABLE IF EXISTS `mmall_sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(20) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门id',
  `dept_level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `dept_seq` int(11) NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序,由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_dept`
--

LOCK TABLES `mmall_sys_dept` WRITE;
/*!40000 ALTER TABLE `mmall_sys_dept` DISABLE KEYS */;
INSERT INTO `mmall_sys_dept` VALUES (1,'技术部',0,'0',1,'技术部','system','2018-04-25 12:15:24','2018-04-25 12:15:24','127.0.0.1'),(2,'后端开发',1,'0.1',4,'后端开发','system','2018-04-25 12:17:52','2018-04-26 01:25:52','127.0.0.1'),(3,'前端开发',1,'0.1',2,'前端开发','system','2018-04-25 12:18:11','2018-04-25 14:36:27','127.0.0.1'),(4,'系统运维',1,'0.1',3,'系统运维','system','2018-04-25 12:53:59','2018-04-25 14:36:21','127.0.0.1'),(5,'UI设计',1,'0.1',1,'UI设计','system','2018-04-25 14:35:01','2018-04-26 01:26:08','127.0.0.1'),(6,'销售部',0,'0',2,'销售部','system','2018-04-26 12:58:17','2018-04-26 12:58:17','127.0.0.1');
/*!40000 ALTER TABLE `mmall_sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_log`
--

DROP TABLE IF EXISTS `mmall_sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '更新类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL DEFAULT '0' COMMENT '基于type后指定的对象Id，比如用户，权限等的主键',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前是否复原过，0：没有，1：复原过',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_log`
--

LOCK TABLES `mmall_sys_log` WRITE;
/*!40000 ALTER TABLE `mmall_sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `mmall_sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_permission`
--

DROP TABLE IF EXISTS `mmall_sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限码',
  `permission_name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `permission_module_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限所在的模块id',
  `permission_url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求的URL,可以填正则表达式',
  `permission_type` int(11) NOT NULL DEFAULT '1' COMMENT '权限类型，1：菜单，2：按钮，3：其他',
  `permission_status` int(11) NOT NULL DEFAULT '1' COMMENT '权限状态，1：正常，0：冻结，',
  `permission_seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '权限备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_permission`
--

LOCK TABLES `mmall_sys_permission` WRITE;
/*!40000 ALTER TABLE `mmall_sys_permission` DISABLE KEYS */;
INSERT INTO `mmall_sys_permission` VALUES (1,'20180430230033_0','进入产品管理界面',1,'/sys/product/product.page',1,1,1,'进入产品管理界面','销售小B','2018-04-30 23:00:34','2018-04-30 23:54:46','127.0.0.1'),(2,'20180430230832_0','查询产品列表',1,'/sys/product/page.json',2,1,2,'查询产品列表','销售小A','2018-04-30 23:08:33','2018-04-30 23:08:33','127.0.0.1'),(3,'20180430230907_0','产品上架',1,'/sys/product/online.json',2,1,3,'产品上架','销售小A','2018-04-30 23:09:07','2018-04-30 23:09:07','127.0.0.1'),(4,'20180430230937_0','产品下架',1,'/sys/product/offline.json',2,1,4,'产品下架','销售小A','2018-04-30 23:09:38','2018-05-01 00:27:20','0:0:0:0:0:0:0:1'),(5,'20180501232431_0','进入订单页',2,'/sys/order/order.page',1,1,1,'','销售小A','2018-05-01 23:24:32','2018-05-01 23:24:32','0:0:0:0:0:0:0:1'),(6,'20180501232501_0','查询订单列表',2,'/sys/order/list.json',2,1,2,'','销售小A','2018-05-01 23:25:01','2018-05-01 23:25:01','0:0:0:0:0:0:0:1'),(7,'20180501232733_0','进入权限管理页',7,'/sys/permissionModule/permission.page',1,1,1,'','zhangchen','2018-05-01 23:27:33','2018-05-02 19:58:01','0:0:0:0:0:0:0:1'),(8,'20180501232831_0','进入角色管理页',8,'/sys/role/role.page',1,1,1,'','zhangchen','2018-05-01 23:28:32','2018-05-02 20:01:53','0:0:0:0:0:0:0:1'),(9,'20180501232854_0','进入用户管理页',9,'/sys/dept/dept.page',1,1,1,'','zhangchen','2018-05-01 23:28:54','2018-05-02 19:52:47','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `mmall_sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_permission_module`
--

DROP TABLE IF EXISTS `mmall_sys_permission_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_permission_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `permission_module_name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级权限模块id',
  `permission_module_level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `permission_module_seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限模块在当前层级下的顺序,由小到大',
  `permission_module_status` int(11) NOT NULL DEFAULT '1' COMMENT '权限模块状态, 1:正常，0:冻结',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_permission_module`
--

LOCK TABLES `mmall_sys_permission_module` WRITE;
/*!40000 ALTER TABLE `mmall_sys_permission_module` DISABLE KEYS */;
INSERT INTO `mmall_sys_permission_module` VALUES (1,'产品管理',0,'0',1,1,'产品管理','销售小B','2018-04-30 15:55:39','2018-04-30 23:32:24','127.0.0.1'),(2,'订单管理',0,'0',2,1,'订单管理','测试2号','2018-04-30 15:55:51','2018-04-30 15:55:51','127.0.0.1'),(3,'公告管理',0,'0',3,1,'公告管理','测试2号','2018-04-30 15:56:05','2018-04-30 15:56:05','127.0.0.1'),(4,'出售中产品管理',1,'0.1',1,1,'出售中产品管理','测试2号','2018-04-30 15:56:39','2018-04-30 16:59:41','127.0.0.1'),(5,'下架产品管理',1,'0.1',2,1,'下架产品管理','测试2号','2018-04-30 16:11:05','2018-04-30 16:11:05','127.0.0.1'),(6,'权限管理',0,'0',4,1,'','销售小A','2018-05-01 23:25:53','2018-05-01 23:25:53','0:0:0:0:0:0:0:1'),(7,'权限管理',6,'0.6',1,1,'','销售小A','2018-05-01 23:26:18','2018-05-01 23:26:18','0:0:0:0:0:0:0:1'),(8,'角色管理',6,'0.6',2,1,'','销售小A','2018-05-01 23:26:36','2018-05-01 23:26:36','0:0:0:0:0:0:0:1'),(9,'用户管理',6,'0.6',3,1,'','销售小A','2018-05-01 23:26:49','2018-05-01 23:26:49','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `mmall_sys_permission_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_role`
--

DROP TABLE IF EXISTS `mmall_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色姓名',
  `role_type` int(11) NOT NULL DEFAULT '1' COMMENT '角色类型，1：管理员角色，2：其他',
  `role_status` int(11) NOT NULL DEFAULT '1' COMMENT '角色状态，1：可用，0：冻结',
  `role_seq` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) DEFAULT '' COMMENT '最近一次操作者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_role`
--

LOCK TABLES `mmall_sys_role` WRITE;
/*!40000 ALTER TABLE `mmall_sys_role` DISABLE KEYS */;
INSERT INTO `mmall_sys_role` VALUES (1,'产品管理员',1,1,0,'Product Manager','销售小A','2018-05-01 17:36:28','2018-05-01 18:05:26','0:0:0:0:0:0:0:1'),(2,'订单管理员',1,1,0,'Order Manager','销售小A','2018-05-01 17:43:38','2018-05-01 18:05:03','0:0:0:0:0:0:0:1'),(3,'公告管理员',1,1,0,'Notification Manager','销售小A','2018-05-01 17:43:49','2018-05-01 18:05:12','0:0:0:0:0:0:0:1'),(4,'权限管理员',1,1,0,'','销售小A','2018-05-01 23:45:33','2018-05-01 23:45:33','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `mmall_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_role_permission`
--

DROP TABLE IF EXISTS `mmall_sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_role_permission`
--

LOCK TABLES `mmall_sys_role_permission` WRITE;
/*!40000 ALTER TABLE `mmall_sys_role_permission` DISABLE KEYS */;
INSERT INTO `mmall_sys_role_permission` VALUES (6,4,7,'zhangchen','2018-05-02 20:06:59','2018-05-02 20:06:59','0:0:0:0:0:0:0:1'),(7,4,8,'zhangchen','2018-05-02 20:06:59','2018-05-02 20:06:59','0:0:0:0:0:0:0:1'),(8,4,9,'zhangchen','2018-05-02 20:06:59','2018-05-02 20:06:59','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `mmall_sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_role_user`
--

DROP TABLE IF EXISTS `mmall_sys_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_role_user`
--

LOCK TABLES `mmall_sys_role_user` WRITE;
/*!40000 ALTER TABLE `mmall_sys_role_user` DISABLE KEYS */;
INSERT INTO `mmall_sys_role_user` VALUES (21,4,2,'zhangchen','2018-05-02 20:07:08','2018-05-02 20:07:08','0:0:0:0:0:0:0:1'),(22,4,1,'zhangchen','2018-05-02 20:07:08','2018-05-02 20:07:08','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `mmall_sys_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_sys_user`
--

DROP TABLE IF EXISTS `mmall_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_telephone` varchar(13) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `user_email` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `user_password` varchar(40) NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户所在部门id',
  `user_status` int(11) NOT NULL DEFAULT '1' COMMENT '用户状态，1：正常， 0：冻结，2：删除',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次操作时间',
  `operator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最近一次操作者的ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_sys_user`
--

LOCK TABLES `mmall_sys_user` WRITE;
/*!40000 ALTER TABLE `mmall_sys_user` DISABLE KEYS */;
INSERT INTO `mmall_sys_user` VALUES (1,'admin','18612344321','helloworld@qq.com','25D55AD283AA400AF464C76D713C07AD',1,1,'admin','system','2018-04-26 00:20:27','2018-04-26 12:57:13','127.0.0.1'),(2,'zhangchen','18688886666','zhangchen@qq.com','25D55AD283AA400AF464C76D713C07AD',3,1,'前端组组长','销售小A','2018-04-26 12:23:35','2018-04-26 15:18:19','127.0.0.1'),(3,'测试主号','13188887777','test@qq.com','25D55AD283AA400AF464C76D713C07AD',5,1,'UI组组长','cissy','2018-04-26 12:56:52','2018-04-26 16:37:09','127.0.0.1'),(4,'张晓明','13888886666','782778833@gmail.com','25D55AD283AA400AF464C76D713C07AD',2,1,'后端组组长','system','2018-04-26 13:00:56','2018-04-26 13:09:40','127.0.0.1'),(5,'大军','13388886666','dajun@gmail.com','25D55AD283AA400AF464C76D713C07AD',4,1,'运维组组长','system','2018-04-26 13:10:41','2018-04-26 13:10:41','127.0.0.1'),(6,'yuanye','13312344321','yuanye@gmail.com','25D55AD283AA400AF464C76D713C07AD',3,1,'前端骨干','system','2018-04-26 13:11:52','2018-04-26 13:11:52','127.0.0.1'),(7,'测试1号','13898765432','6666@qq.com','25D55AD283AA400AF464C76D713C07AD',5,1,'UI设计骨干','admin','2018-04-26 13:15:19','2018-04-26 13:44:15','127.0.0.1'),(8,'销售小A','13199998888','aaaa@gmail.com','25D55AD283AA400AF464C76D713C07AD',6,1,'销售小A','销售小A','2018-04-26 14:59:03','2018-04-26 15:46:12','127.0.0.1'),(9,'销售小B','13566668888','bbbb@gmail.com','25D55AD283AA400AF464C76D713C07AD',6,1,'销售小B','销售小A','2018-04-26 15:09:21','2018-04-26 15:46:06','127.0.0.1'),(10,'销售小C','17788886666','cccc@e.ntu.edu.sg','25D55AD283AA400AF464C76D713C07AD',6,1,'销售小C','销售小A','2018-04-26 15:30:09','2018-04-26 15:46:21','127.0.0.1'),(11,'销售小D','13376544567','dddd@163.com','25D55AD283AA400AF464C76D713C07AD',6,1,'销售小D','销售小A','2018-04-26 15:38:35','2018-04-26 15:46:27','127.0.0.1'),(12,'测试2号','12266668888','zhangchen.happy@gmail.com','25D55AD283AA400AF464C76D713C07AD',5,1,'测试2号','销售小A','2018-04-26 15:49:59','2018-04-26 15:49:59','127.0.0.1'),(13,'测试3号','12366668888',' sales@phaccessory.com','25D55AD283AA400AF464C76D713C07AD',5,1,'测试3号','销售小A','2018-04-26 16:13:49','2018-04-26 16:13:49','127.0.0.1'),(14,'测试4号','18888888888','czhang017@e.ntu.edu.sg','25D55AD283AA400AF464C76D713C07AD',1,1,'测试4号','销售小A','2018-04-26 16:16:13','2018-04-26 16:16:13','127.0.0.1'),(15,'xiaohou','15588886666','phaccessory@gmail.com','25D55AD283AA400AF464C76D713C07AD',3,1,'xiaohou','admin','2018-04-26 16:28:34','2018-04-26 16:28:34','127.0.0.1'),(16,'cissy','19888886666','cissy@qq.com','25D55AD283AA400AF464C76D713C07AD',3,1,'cissy','admin','2018-04-26 16:30:33','2018-04-26 16:31:24','127.0.0.1'),(17,'catty','13588889999','catty@qq.com','25D55AD283AA400AF464C76D713C07AD',3,1,'catty','cissy','2018-04-26 16:32:37','2018-04-26 16:34:13','127.0.0.1'),(18,'测试4号','12345678901','649892865@qq.com','25D55AD283AA400AF464C76D713C07AD',5,1,'测试4号','cissy','2018-04-26 16:35:52','2018-04-26 16:35:52','127.0.0.1');
/*!40000 ALTER TABLE `mmall_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmall_user`
--

DROP TABLE IF EXISTS `mmall_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mmall_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mmall_user`
--

LOCK TABLES `mmall_user` WRITE;
/*!40000 ALTER TABLE `mmall_user` DISABLE KEYS */;
INSERT INTO `mmall_user` VALUES (1,'admin','427338237BD929443EC5D48E24FD2B1A','admin@happymmall.com','13800138000','问题','答案',1,'2016-11-06 16:56:45','2017-04-04 19:27:36'),(13,'geely','08E9A6EA287E70E7E3F7C982BF7923AC','geely@happymmall.com','13800138000','问题','答案',0,'2016-11-19 22:19:25','2016-11-19 22:19:25'),(17,'rosen','095AC193FE2212EEC7A93E8FEFF11902','rosen1@happymmall.com','13800138000','问题','答案',0,'2017-03-17 10:51:33','2017-04-09 23:13:26'),(21,'soonerbetter','DE6D76FE7C40D5A1A8F04213F2BEFBEE','test06@happymmall.com','13800138000','105204','105204',0,'2017-04-13 21:26:22','2017-04-13 21:26:22'),(22,'testtest','661BB8500BEB04EBA92D06C9BDD821E6','zhangchen.test@gmail.com','1234567890','This is a new answer','This is a new answer',0,'2018-04-15 16:24:31','2018-04-15 19:33:31');
/*!40000 ALTER TABLE `mmall_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-03 13:30:02
