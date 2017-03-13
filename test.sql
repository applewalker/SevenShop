# Host: localhost  (Version: 5.5.40)
# Date: 2016-07-30 20:44:29
# Generator: MySQL-Front 5.3  (Build 4.120)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "address"
#

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `smearedAddress` varchar(255) DEFAULT NULL,
  `detailAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

#
# Data for table "address"
#

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (43,'宝宝',1,'广东省,河源市,枞阳县','炸弹小区'),(45,'你离',30,'山东省,枣庄市,海城市',''),(46,'白涛',47,'湖北省,武汉市,洪山区','华中农业大学'),(47,'王晟',51,'湖北省,武汉市,洪山区','华中农业大学');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

#
# Structure for table "browserecord"
#

DROP TABLE IF EXISTS `browserecord`;
CREATE TABLE `browserecord` (
  `browserecord_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`browserecord_id`)
) ENGINE=MyISAM AUTO_INCREMENT=402 DEFAULT CHARSET=utf8;

#
# Data for table "browserecord"
#

/*!40000 ALTER TABLE `browserecord` DISABLE KEYS */;
INSERT INTO `browserecord` VALUES (1,29,1),(4,29,2),(5,29,1),(6,29,1),(7,29,1),(8,29,1),(9,29,1),(10,29,1),(11,29,1),(12,29,1),(13,29,1),(14,29,1),(15,29,2),(16,29,1),(17,29,2),(18,29,1),(19,29,1),(20,29,2),(21,29,1),(22,29,2),(23,29,1),(24,29,2),(25,29,1),(26,29,2),(27,29,1),(28,29,2),(29,29,1),(30,29,2),(31,29,1),(32,29,2),(33,29,1),(34,29,2),(35,29,1),(36,29,2),(37,29,1),(38,29,1),(39,29,2),(40,29,1),(41,29,2),(42,29,1),(43,29,2),(44,29,1),(45,29,1),(46,29,22),(47,29,1),(48,29,103),(49,29,85),(50,30,1),(51,29,52),(52,29,1),(53,29,2),(54,29,1),(55,29,2),(56,29,2),(57,29,54),(58,29,54),(59,29,52),(60,29,47),(61,29,23),(62,29,2),(63,29,2),(64,29,1),(65,29,1),(66,29,1),(67,29,1),(68,29,100),(69,29,53),(70,29,51),(71,29,1),(72,30,1),(73,30,107),(74,30,114),(75,30,114),(76,30,1),(77,30,1),(78,30,1),(79,1,2),(80,1,2),(81,1,2),(82,1,1),(83,1,1),(84,1,2),(85,1,1),(86,1,1),(87,1,1),(88,1,1),(89,1,1),(90,1,1),(91,1,1),(92,1,1),(93,1,1),(94,1,2),(95,1,2),(96,1,2),(97,1,2),(98,1,2),(99,1,2),(100,1,2),(101,1,2),(102,1,1),(103,1,1),(104,1,1),(105,1,2),(106,1,2),(107,1,1),(108,1,2),(109,1,2),(110,1,1),(111,1,2),(112,1,2),(113,30,1),(114,1,2),(115,1,2),(116,1,2),(117,1,2),(118,1,2),(119,1,2),(120,1,1),(121,1,1),(122,1,1),(123,1,1),(124,1,1),(125,1,1),(126,1,1),(127,1,1),(128,1,2),(129,1,2),(130,1,1),(131,1,1),(132,1,1),(133,1,1),(134,1,1),(135,1,1),(136,1,22),(137,1,2),(138,1,18),(139,1,2),(140,1,2),(141,1,2),(142,1,2),(143,1,22),(144,1,22),(145,1,21),(146,1,19),(147,1,1),(148,1,1),(149,30,18),(150,30,1),(151,30,1),(152,1,2),(153,30,2),(154,1,1),(155,1,1),(156,1,1),(157,1,2),(158,30,1),(159,1,2),(160,1,1),(161,30,2),(162,1,2),(163,30,2),(164,30,18),(165,30,18),(166,30,1),(167,1,1),(168,1,2),(169,1,1),(170,1,2),(171,1,2),(172,1,1),(173,1,2),(174,1,1),(175,1,2),(176,1,2),(177,1,1),(178,30,1),(179,30,2),(180,1,2),(181,1,17),(182,1,18),(183,1,19),(184,1,18),(185,1,18),(186,1,2),(187,1,2),(188,1,1),(189,1,1),(190,1,1),(191,1,20),(192,1,19),(193,1,20),(194,1,22),(195,1,21),(196,1,18),(197,1,17),(198,1,19),(199,1,20),(200,1,21),(201,1,53),(202,1,53),(203,1,2),(204,1,1),(205,1,2),(206,1,2),(207,1,1),(208,1,2),(209,1,1),(210,1,2),(211,1,2),(212,1,2),(213,1,2),(214,30,11),(215,30,14),(216,30,14),(217,30,54),(218,30,1),(219,1,2),(220,1,2),(221,1,1),(222,30,17),(223,30,1),(224,30,1),(225,1,1),(226,30,2),(227,1,2),(228,1,2),(229,1,2),(230,1,1),(231,1,1),(232,1,1),(233,1,1),(234,1,1),(235,1,1),(236,1,2),(237,1,2),(238,1,2),(239,1,2),(240,1,2),(241,30,1),(242,30,1),(243,30,1),(244,1,46),(245,1,1),(246,1,117),(247,1,46),(248,1,46),(249,1,1),(250,1,17),(251,1,117),(252,1,2),(253,1,1),(254,1,20),(255,1,17),(256,1,46),(257,1,1),(258,1,2),(259,1,1),(260,1,2),(261,1,117),(262,1,46),(263,1,1),(264,1,5),(265,1,1),(266,1,46),(267,1,1),(268,1,1),(269,1,1),(270,1,1),(271,1,1),(272,1,1),(273,1,46),(274,1,1),(275,1,1),(276,1,2),(277,1,1),(278,1,2),(279,1,1),(280,1,2),(281,1,1),(282,1,1),(283,1,1),(284,1,1),(285,1,17),(286,1,1),(287,1,2),(288,1,46),(289,1,46),(290,1,1),(291,1,46),(292,1,46),(293,1,2),(294,1,2),(295,1,2),(296,1,2),(297,1,2),(298,1,2),(299,1,1),(300,1,1),(301,1,1),(302,1,1),(303,1,126),(304,1,2),(305,1,2),(306,1,1),(307,30,1),(308,1,1),(309,30,1),(310,30,1),(311,30,1),(312,30,1),(313,1,1),(314,1,1),(315,1,1),(316,1,47),(317,1,48),(318,1,1),(319,1,1),(320,1,1),(321,1,1),(322,1,1),(323,1,1),(324,1,1),(325,1,2),(326,1,117),(327,1,1),(328,1,1),(329,1,46),(330,47,1),(331,47,117),(332,47,1),(333,47,2),(334,47,1),(335,47,2),(336,47,46),(337,47,1),(338,47,46),(339,47,1),(340,47,19),(341,47,1),(342,47,5),(343,47,25),(344,47,26),(345,47,45),(346,47,36),(347,47,46),(348,51,1),(349,51,118),(350,51,46),(351,51,46),(352,51,47),(353,51,48),(354,51,7),(355,51,18),(356,51,17),(357,51,76),(358,51,17),(359,51,1),(360,51,17),(361,51,17),(362,51,2),(363,51,1),(364,51,19),(365,51,18),(366,51,53),(367,51,1),(368,51,2),(369,51,2),(370,51,2),(371,51,2),(372,51,2),(373,51,1),(374,51,46),(375,51,1),(376,51,2),(377,51,1),(378,51,1),(379,51,1),(380,51,1),(381,51,1),(382,51,1),(383,51,1),(384,51,1),(385,51,1),(386,51,117),(387,51,117),(388,51,117),(389,51,117),(390,51,117),(391,51,117),(392,51,1),(393,51,1),(394,51,1),(395,51,1),(396,51,1),(397,51,1),(398,51,1),(399,51,1),(400,51,2),(401,51,1);
/*!40000 ALTER TABLE `browserecord` ENABLE KEYS */;

#
# Structure for table "evaluate"
#

DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) unsigned DEFAULT NULL,
  `evaluate` varchar(3000) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`evaluate_id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

#
# Data for table "evaluate"
#

/*!40000 ALTER TABLE `evaluate` DISABLE KEYS */;
INSERT INTO `evaluate` VALUES (35,1,'这本书很好','2016-07-29 19:48:13','12345'),(36,46,'不错','2016-07-29 20:23:45','白天睡觉'),(37,46,'hao','2016-07-29 20:31:25','白天睡觉'),(38,1,'哈哈哈哈回家哼哼唧唧','2016-07-30 16:07:04','白天睡觉'),(39,2,'呵呵哈哈哈','2016-07-30 16:30:40','白天睡觉'),(40,1,'哈哈哈哈','2016-07-30 16:32:31','白天睡觉'),(41,1,'哈哈哈','2016-07-30 16:35:08','白天睡觉'),(42,2,'哈哈','2016-07-30 16:39:26','白天睡觉'),(43,46,'好','2016-07-30 19:47:00','这'),(44,1,'好','2016-07-30 19:50:50','这'),(45,2,'好','2016-07-30 20:17:29','不'),(46,46,'好','2016-07-30 20:18:29','不');
/*!40000 ALTER TABLE `evaluate` ENABLE KEYS */;

#
# Structure for table "goods"
#

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `fare` int(11) unsigned DEFAULT NULL,
  `salesvolume` int(11) unsigned DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  `mainprice` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=MyISAM AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

#
# Data for table "goods"
#

/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'书1',1,'http://192.168.1.100:80/test/goodpicture/0.jpg',12,100,'广东','阿斯顿发士大夫撒发生',10.00),(2,'书2',1,'http://192.168.1.100:80/test/goodpicture/1.jpg',100,NULL,NULL,NULL,NULL),(5,'平板1',6,'https://img.alicdn.com/bao/uploaded/i3/TB1nNtVGFXXXXbpXpXXD_6iFpXX_121355.jpg_b.jpg',600,51,'武汉','Apple苹果平板电脑iPad mini2 7.9英寸 银色 16G',1888.00),(7,'平板2',6,'https://img.alicdn.com/bao/uploaded/i2/TB1LtLnJFXXXXaDXXXXEdVZ8VXX_031547.jpg_b.jpg',50,1,'广州','国行正品Apple/苹果 iPad mini 4 WLAN 64GB 7.9英寸迷你平板电脑',5566.00),(8,'平板3',6,'https://img.alicdn.com/bao/uploaded/i1/TB1Nc8ZGFXXXXagXpXXkvanFFXX_123531.jpg_b.jpg',100,5,'武汉','Apple苹果平板电脑.iPad mini2.7.9英寸.银.32G',2299.00),(9,'平板4',6,'https://img.alicdn.com/bao/uploaded/i4/TB1N02uLXXXXXaFXVXXhMXF8pXX_022529.jpg_b.jpg',900,200,'广州','Xiaomi/小米 小米平板2 WIFI 64GB 安卓平板电脑',1500.00),(10,'平板5',6,'https://img.alicdn.com/bao/uploaded/i3/TB1MuGWJFXXXXaqXXXXPBu38VXX_032408.jpg_b.jpg',1000,500,'广州','12期免息Huawei/华为 PLE-703L 4G 16GB M2青春版平板电脑手机',1599.00),(11,'平板6',6,'https://img.alicdn.com/bao/uploaded/i1/TB1v.m1JFXXXXbJXFXXpvWT9pXX_041211.jpg_b.jpg',100,6,'深圳','华为M2青春版平板电脑10英寸Huawei/华为 FDR-A01w WIFI 16GB平板',1799.00),(12,'平板7',6,'https://img.alicdn.com/bao/uploaded/i5/TB1iimOLFXXXXaTXVXXBKc48FXX_030801.jpg_b.jpg',140,50,'北京','小米pad平板电脑金属Xiaomi/小米 小米平板2 windows版 WIFI 64GB',1299.00),(13,'平板8',6,'https://img.alicdn.com/bao/uploaded/i6/TB1SZ7ZLXXXXXcaXFXXZlFq9FXX_042811.jpg_b.jpg',250,45,'深圳','12期免息Huawei/华为 揽阅M2 10.0 WIFI 16GB 10英寸八核平板电脑',2288.00),(14,'平板9',6,'https://img.alicdn.com/bao/uploaded/i7/TB1Vk4rMXXXXXakaXXXPb7A_pXX_060118.jpg_b.jpg',256,98,'广州','Teclast/台电 Tbook16双系统 WIFI 64GB Win10平板电脑二合一11.6',3345.00),(17,'学习机2',9,'https://img.alicdn.com/bao/uploaded/i4/TB1jldvGXXXXXcBXXXXN6rB9XXX_035629.jpg_b.jpg',33,2,'广州','快易典H17学习机平板电脑智能家教机小学生初中高中同步英语点读',4556.00),(18,'学习机3',9,'https://img.alicdn.com/bao/uploaded/i8/TB1FQUoHpXXXXXbaXXXXEFu_pXX_054642.jpg_b.jpg',44,5,'广州','诺亚舟e6s优学派 学习机平板电脑 小学生初中高中学生电脑 家教机',1998.00),(19,'学习机4',9,'https://img.alicdn.com/bao/uploaded/i8/T1bpIiFp8cXXajbqQ__105354.jpg_b.jpg',55,5,'广州','快易典EH1英汉电子词典 英语学习机 真人发音牛津高阶辞典翻译机',298.00),(20,'学习机5',9,'https://img.alicdn.com/bao/uploaded/i2/T1OOuDFOdcXXa1MGc0_034219.jpg_b.jpg',66,2,'广州','诺亚舟E18优学派学习机平板电脑可上网家教机小学初中高中点读机',998.00),(21,'学习机6',9,'https://img.alicdn.com/bao/uploaded/i1/726424179/TB2CUsDsVXXXXXHXXXXXXXXXXXX_!!726424179.jpg_b.jpg',65,3,'广州','小霸王S1学习机儿童平板电脑10寸点读机中小学同步学生家教机',779.00),(22,'学习机7',9,'https://img.alicdn.com/bao/uploaded/i2/TB1hkFJFVXXXXXPXFXXI6VL9XXX_034404.jpg_b.jpg',32,6,'长沙','读书郎学生电脑P30小学初中高中学习机点读机全科课本同步P30S',878.00),(23,'学习机8',9,'https://img.alicdn.com/bao/uploaded/i1/735180178/TB2z_b6oFXXXXabXXXXXXXXXXXX_!!735180178.jpg_b.jpg',23,3,'郑州','金正Q1学习机 平板电脑小学生初中生课本同步英语视频家教机',398.00),(24,'学习机9',9,'https://img.alicdn.com/bao/uploaded/i2/2160055957/TB2k6cEqXXXXXXoXFXXXXXXXXXX_!!2160055957.jpg_b.jpg',36,1,'郑州','卡西欧电子词典英语E-Y200学习机英汉 牛津辞典ey200 出国翻译机',2790.00),(25,'学习机10',9,'https://img.alicdn.com/bao/uploaded/i1/TB168RGHFXXXXajXFXXsrHt8XXX_021121.jpg_b.jpg',69,1,'长沙','快易典h16s学习机平板电脑学生家教机小学生初中高中同步学习电脑',998.00),(26,'背心裙1',10,'https://img.alicdn.com/bao/uploaded/i2/2687728294/TB25q5osVXXXXbDXpXXXXXXXXXX_!!2687728294.jpg_430x430q90.jpg',12,21,'武汉','NPaia恩派雅2016秋季新款女装镂空修身显瘦无袖长款背心连衣裙',1999.00),(27,'背心裙2',10,'https://img.alicdn.com/bao/uploaded/i1/517546721/TB2XQs6sFXXXXagXpXXXXXXXXXX_!!517546721.jpg_430x430q90.jpg',13,32,'广州','NPaia恩派雅2016秋季新款女装镂空修身显瘦无袖长款背心连衣裙',1222.00),(28,'背心裙3',10,'https://img.alicdn.com/bao/uploaded/i2/2641603410/TB2dG7LsVXXXXbZXXXXXXXXXXXX_!!2641603410.jpg_430x430q90.jpg',14,44,'武汉','miamia2016秋新女高端提花修身显瘦背心式连衣裙女式A字裙',1439.00),(29,'背心裙4',10,'https://img.alicdn.com/bao/uploaded/i1/363554159/TB2arFjtXXXXXcdXXXXXXXXXXXX_!!363554159.jpg_430x430q90.jpg',12,52,'长沙','宝丝露2016秋季新款专柜正品波点图案印花高腰圆领无袖背心连衣裙',518.00),(30,'背心裙5',10,'https://img.alicdn.com/bao/uploaded/i2/2780730635/TB2V54AsVXXXXayXXXXXXXXXXXX_!!2780730635.jpg_430x430q90.jpg',16,23,'武汉','SOIREE/奢瑞小黑裙 2016春夏款气质修身时尚简约欧美潮A字背心裙',366.00),(31,'背心裙6',10,'https://img.alicdn.com/bao/uploaded/i4/2189255098/TB2CWxAsFXXXXaRXXXXXXXXXXXX_!!2189255098.jpg_430x430q90.jpg',12,36,'武汉','宝丝露秋季新款 纯色A型褶皱背心裙甜美公主裙无袖背心连衣裙短款',555.00),(32,'背心裙7',10,'https://img.alicdn.com/bao/uploaded/i4/2189255098/TB2CWxAsFXXXXaRXXXXXXXXXXXX_!!2189255098.jpg_430x430q90.jpg',36,53,'广州','2016夏季新款黑白竖条针织背心连衣裙女腰后镂空扣带鱼尾',223.00),(33,'背心裙8',10,'https://img.alicdn.com/bao/uploaded/i1/1638219391/TB2CkfYsFXXXXa0XXXXXXXXXXXX_!!1638219391.jpg_430x430q90.jpg',32,56,'广州','阿瑪施女装2016新款修身甜美小碎花中裙百搭背心款',500.00),(34,'背心裙9',10,'https://img.alicdn.com/bao/uploaded/i4/2208915000/TB2j8EKsFXXXXXJXFXXXXXXXXXX_!!2208915000.jpg_430x430q90.jpg',69,53,'杭州','LaluceIvan2016夏季新性感露肩金属环绑带背心吊带连衣裙',355.00),(35,'背心裙10',10,'https://img.alicdn.com/bao/uploaded/i2/2656326499/TB2CnJrtXXXXXb6XXXXXXXXXXXX_!!2656326499.jpg_430x430q90.jpg',36,59,'武汉','2016春秋新款夏 背心裙欧美街头改良旗袍裙子套装篷篷裙小连衣裙',369.00),(36,'短裙1',11,'https://img.alicdn.com/bao/uploaded/i4/TB1PMWpKVXXXXalXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',12,34,'广州','Vero Moda2016秋冬新款不规则前身设计通勤款半裙短裙',233.00),(37,'短裙2',11,'https://img.alicdn.com/bao/uploaded/i1/272205633/TB2Lzd4oFXXXXc5XXXXXXXXXXXX_!!272205633.jpg_430x430q90.jpg',45,63,'广州','ochirly欧时力2016新女装高腰A字纯色短裙百褶半身裙',455.00),(38,'短裙3',11,'https://img.alicdn.com/bao/uploaded/i2/685140573/TB2os.BkXXXXXXfXpXXXXXXXXXX-685140573.jpg_430x430q90.jpg',31,51,'广州','Five Plus2016新品女春装纯色提花半身高腰短裙A字裙',199.00),(39,'短裙4',11,'https://img.alicdn.com/bao/uploaded/i3/528296282/TB2jpjVjVXXXXbZXpXXXXXXXXXX_!!528296282.jpg_430x430q90.jpg',45,34,'广州','秋水伊人2016夏季新品女装百搭纯色简约荷叶边A字裙半身裙短裙',344.00),(40,'短裙5',11,'https://img.alicdn.com/bao/uploaded/i2/1044264726/TB2jAPJlFXXXXbvXXXXXXXXXXXX_!!1044264726.jpg_430x430q90.jpg',53,45,'广州','ZK复古印花半身裙修身显瘦A字短裙蓬蓬裙伞裙女装2016春夏装新款',665.00),(41,'短裙6',11,'https://img.alicdn.com/bao/uploaded/i1/735337687/TB2sX0BkFXXXXcLXpXXXXXXXXXX_!!735337687.jpg_430x430q90.jpg',43,54,'广州','Yigue/亦谷2016夏新品通勤淑女短裙半身裙高腰A字裙女裙子',179.00),(42,'短裙7',11,'https://img.alicdn.com/bao/uploaded/i1/893978157/TB2Zpg2qFXXXXbeXpXXXXXXXXXX_!!893978157.jpg_430x430q90.jpg',76,43,'广州','缪佳2016夏季新款百搭半身裙日系牛仔裙拼接贴布绣花显瘦a字短裙',138.00),(43,'短裙8',11,'https://img.alicdn.com/bao/uploaded/i3/2356259724/TB2TlOcoXXXXXXvXpXXXXXXXXXX_!!2356259724.jpg_430x430q90.jpg',43,36,'武汉','意界可爱小清新圆领短袖蝴蝶结性感露背短裙显瘦连衣裙夏',54.00),(44,'短裙9',11,'https://img.alicdn.com/bao/uploaded/i4/710962071/TB2mRfAqpXXXXchXpXXXXXXXXXX_!!710962071.jpg_430x430q90.jpg',45,34,'杭州','艾格WEEKEND经典单排扣牛仔半身A字短裙',344.00),(45,'短裙10',11,'https://img.alicdn.com/bao/uploaded/i1/1613434166/TB2g4bgpFXXXXXkXXXXXXXXXXXX_!!1613434166.jpg_430x430q90.jpg',45,34,'广州','暖春2016夏季新款高腰百搭网纱半身裙公主百褶裙蓬蓬裙a字短裙子',455.00),(46,'连衣裙1',12,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',12,34,'武汉','ONLY夏装新蕾丝修身七分袖连衣裙',452.00),(47,'连衣裙2',12,'https://img.alicdn.com/bao/uploaded/i1/TB1M7gZKFXXXXXZXFXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',23,44,'广州','ZARA女装印花蕾丝连衣裙',5453.00),(48,'连衣裙3',12,'https://img.alicdn.com/bao/uploaded/i1/TB1sT_BKXXXXXbgaXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',34,34,'广州','VeroModa2016秋季新品褶皱底摆修身蕾丝连衣裙',231.00),(49,'连衣裙4',12,'https://img.alicdn.com/bao/uploaded/i3/TB1ftJwLXXXXXazaXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','INSUN恩裳2016夏装新款精致蕾丝红色提花七分袖连衣裙',213.00),(50,'连衣裙5',12,'https://img.alicdn.com/bao/uploaded/i4/TB1g2a4MXXXXXXrXFXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',34,53,'广州','MO&Co小圆领窗花图案镂空蕾丝两件套中袖连衣裙',454.00),(51,'连衣裙6',12,'https://img.alicdn.com/bao/uploaded/i3/TB1_mOXMFXXXXc3XXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',4,534,'广州','ochirly欧时力2016新女夏装蕾丝拼接假两件伞裙连衣裙',534.00),(52,'连衣裙7',12,'https://img.alicdn.com/bao/uploaded/i1/TB1J_KMKFXXXXcqXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',153,4,'广州','2016新款EP雅莹女装2016夏镂空圆领A字连衣裙',534.00),(53,'连衣裙8',12,'https://img.alicdn.com/bao/uploaded/i1/TB10txXKFXXXXbZXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',43,54,'广州','FivePlus2016新品女夏装蕾丝镂空荷叶边无袖连衣裙',3114.00),(54,'连衣裙9',12,'https://img.alicdn.com/bao/uploaded/i2/TB10nHwJVXXXXXLXXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','欧芮儿名媛气质A字裙短袖高腰修身V领中长款蕾丝连衣裙夏2016新款',5340.00),(55,'连衣裙10',12,'https://img.alicdn.com/bao/uploaded/i4/TB1HQHFLVXXXXXJaXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','欧芮儿名媛气质A字裙短袖高腰修身V领中长款蕾丝连衣裙夏2016新款',443.00),(56,'长裙1',14,'https://img.alicdn.com/bao/uploaded/i2/TB1CluVIXXXXXc_XpXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',32,168,'北京','YINER音儿女装专柜2016夏装新款简约黑白波点印花连衣裙',123.00),(57,'长裙2',14,'https://img.alicdn.com/bao/uploaded/i1/TB1oqYLJVXXXXbZXXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',63,43,'北京','VeroModa2016秋季新品花朵印花松紧领口百褶雪纺连衣裙',543.00),(58,'长裙3',14,'https://img.alicdn.com/bao/uploaded/i3/TB1UiEGKFXXXXc.XFXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','Ochirly欧时力2016新女夏装挂脖露肩雪纺百褶连衣裙',231.00),(59,'长裙4',14,'https://img.alicdn.com/bao/uploaded/i1/TB19sCNHVXXXXbzXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',31,113,'广州','EP雅莹女装2016夏季专柜雪纺印花双层下摆长款连衣裙',122.00),(60,'长裙5',14,'https://img.alicdn.com/bao/uploaded/i3/TB1.x3yKVXXXXawXpXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','言吾2016夏装新款中长款雪纺连衣裙女短袖系带宽松显瘦百褶a字裙',453.00),(61,'长裙6',14,'https://img.alicdn.com/bao/uploaded/i3/TB1qvj1KFXXXXbBXFXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,34,'广州','时尚印记雪纺纱连衣裙2016韩版无袖高腰A字条纹长款假两件裙子女',453.00),(62,'长裙7',14,'https://img.alicdn.com/bao/uploaded/i1/TB1HAz6MXXXXXacXpXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',534,435,'广州','对白假两件拼接无袖连衣裙 雪纺小黑裙 2016夏装新款',311.00),(63,'长裙8',14,'https://img.alicdn.com/bao/uploaded/i1/TB1IyIEJVXXXXb9XVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',34,53,'广州','连衣裙女夏季2016新款宽松条纹裙子印花V领衬衫白色雪纺a字裙短袖',453.00),(64,'长裙9',14,'https://img.alicdn.com/bao/uploaded/i4/2131407402/TB2.zoJkVXXXXc4XXXXXXXXXXXX_!!2131407402.jpg_430x430q90.jpg',43,534,'广州','MeetMetro欧美女装2016春夏季新款V领宽松大摆雪纺长裙开叉连衣裙',453.00),(65,'长裙10',14,'https://img.alicdn.com/bao/uploaded/i1/692195348/TB2jceorVXXXXcDXXXXXXXXXXXX_!!692195348.jpg_430x430q90.jpg',53,45,'广州','Koradior/珂莱蒂尔正品2016夏季新品印花钉珠气质雪纺连衣裙预售',453.00),(66,'牛仔裙1',13,'https://img.alicdn.com/bao/uploaded/i1/728443962/TB2OULjlFXXXXcmXXXXXXXXXXXX_!!728443962.jpg_430x430q90.jpg',23,13,'广州','Girdear哥弟女装2016春季新款单排扣A型牛仔半身裙',123.00),(67,'牛仔裙2',13,'https://img.alicdn.com/bao/uploaded/i3/TB1qDABKFXXXXbmapXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',453,45,'广州','满300用券减30VeroModa2016秋冬新品小A摆纯棉牛仔半裙',123.00),(68,'牛仔裙3',13,'https://img.alicdn.com/bao/uploaded/i1/2228361831/TB20zO_spXXXXcyXXXXXXXXXXXX_!!2228361831.jpg_430x430q90.jpg',45,34,'广州','ZARA女装经典款牛仔裙',134.00),(69,'牛仔裙4',13,'https://img.alicdn.com/bao/uploaded/i2/2183380830/TB24DAHlVXXXXcoXpXXXXXXXXXX_!!2183380830.jpg_430x430q90.jpg',45,34,'广州','春夏折扣 MassimoDutti女装口袋饰牛仔及膝裙',5234.00),(70,'牛仔裙5',13,'https://img.alicdn.com/bao/uploaded/i3/685140573/TB2pesNkXXXXXbgXXXXXXXXXXXX-685140573.jpg_430x430q90.jpg',34,53,'广州','Five Plus2016新品女春装棉质印花荷叶边半身牛仔裙',1534.00),(71,'牛仔裙6',13,'https://img.alicdn.com/bao/uploaded/i3/272205633/TB2XQfHpXXXXXXjXpXXXXXXXXXX_!!272205633.jpg_430x430q90.jpg',464,35,'广州','新女夏装不规则高腰A字纯棉牛仔半裙',153.00),(72,'牛仔裙7',13,'https://img.alicdn.com/bao/uploaded/i1/725653510/TB24HeHoXXXXXavXXXXXXXXXXXX_!!725653510.jpg_430x430q90.jpg',4,534,'广州','Cherrykoko2016夏韩版弹力牛仔裙A字半身裙高腰包臀纽扣短裙子女',4135.00),(73,'牛仔裙8',13,'https://img.alicdn.com/bao/uploaded/i4/819984768/TB2353bspXXXXa0XpXXXXXXXXXX_!!819984768.jpg_430x430q90.jpg',45,34,'广州','dzzit地素2016夏装新品舒适条纹无袖牛仔A字连衣裙',153.00),(74,'牛仔裙9',13,'https://img.alicdn.com/bao/uploaded/i1/188124207/TB2WdFHnFXXXXcMXXXXXXXXXXXX_!!188124207.jpg_430x430q90.jpg',4,354,'广州','唐狮夏季牛仔裙女高腰A字裙韩版牛仔短裙显瘦包臀裙单排扣半身裙',135.00),(75,'牛仔裙10',13,'https://img.alicdn.com/bao/uploaded/i3/TB1RtrWKVXXXXagapXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',45,36,'广州','Krazy俏皮优雅复古别致排扣元素纯棉牛仔裙',453.00),(76,'相机1',8,'https://img.alicdn.com/bao/uploaded/i2/T1sgiKFBdcXXbEs831_042250.jpg_b.jpg',897,8,'广东佛山','蚂蚁摄影 Sony/索尼 ILCE-6000L套机16-50 A6000 索尼微单电相机',10009.00),(77,'相机2',8,'https://img.alicdn.com/bao/uploaded/i7/TB1lvU6HpXXXXbbaXXXRL5dFpXX_120517.jpg_b.jpg',789,456,'南京','【0首付分期】蚂蚁摄影联保单反数码相机Canon/佳能 EOS 760D套机',11009.00),(78,'相机3',8,'https://img.alicdn.com/bao/uploaded/i7/T1rXjAFPtXXXciO8U3_050819.jpg_b.jpg',123,12,'广东佛山','【蚂蚁摄影】单反外观Sony/索尼 DSC-H400高清长焦射月数码照相机',10039.00),(79,'相机4',8,'https://img.alicdn.com/bao/uploaded/i6/TB11ow0LVXXXXXkaXXXJwQ_.VXX_114023.jpg_b.jpg',456,74,'广东佛山','【蚂蚁摄影送教程】单反数码相机Canon/佳能 EOS 80D套机 高清',10029.00),(80,'相机5',8,'https://img.alicdn.com/bao/uploaded/i2/201688881/TB2ff5yoFXXXXccXpXXXXXXXXXX_!!201688881.jpg_b.jpg',987,654,'广东佛山','行货单反数码相机Canon/佳能 80D套机 高清 多种镜头可选 正品',10019.00),(81,'相机6',8,'https://img.alicdn.com/bao/uploaded/i8/TB10m0yMXXXXXaKaXXX_IqIFpXX_120748.jpg_b.jpg',369,258,'广东佛山','Canon/佳能 PowerShot SX720 HS长焦数码照相机高清卡片机家用',10009.00),(82,'相机7',8,'https://img.alicdn.com/bao/uploaded/i7/TB1W2ovLVXXXXc6XVXXAX2f8XXX_021012.jpg_b.jpg',147,12,'广东广州','Casio/卡西欧 EX-ZR3600 新品小自拍神器 美颜数码相机',13900.00),(83,'相机8',8,'https://img.alicdn.com/bao/uploaded/i2/782731205/TB2EByuqpXXXXbXXpXXXXXXXXXX_!!782731205.jpg_b.jpg',459,78,'广东佛山','Sony/索尼ILCE-6000L (16-50/55-210) A6000 微单 套装',18900.00),(84,'相机9',8,'https://img.alicdn.com/bao/uploaded/i4/T1zabrFlBiXXa8LQAZ_032908.jpg_b.jpg',35,7,'广东佛山','旗舰店 Casio/卡西欧 EX-ZR55 自拍神器 数码相机 美颜相机自拍',11900.00),(85,'相机10',8,'https://img.alicdn.com/bao/uploaded/i2/TB1epw_HpXXXXbFXVXXVJyf8VXX_031714.jpg_b.jpg',596,456,'广东深圳','Huawei/华为 AM116原装耳机入耳式耳塞荣耀6plus苹果小米通用正品【分期购】Canon/佳能 EOS 750D 18-55 套机 单反相机 官方联保',10019.00),(86,'笔记本1',4,'https://img.alicdn.com/bao/uploaded/i7/TB1zr4lLXXXXXbeXFXXCQn0FFXX_094641.jpg_b.jpg',10,45,'杭州','超薄Apple/苹果 MacBook Air MMGF2CH/A13.3英寸手提笔记本电脑',6358.00),(87,'笔记本2',4,'https://img.alicdn.com/bao/uploaded/i1/TB1XuMSKFXXXXa7XpXX_Gkl.pXX_074438.jpg_b.jpg',21,987,'成都','Lenovo/联想 拯救者15-ISK I7-6700HQ四核高清游戏笔记本电脑手提',6129.00),(88,'笔记本3',4,'https://img.alicdn.com/bao/uploaded/i2/TB1HFyhJVXXXXaYXpXX5d8VFVXX_095930.jpg_b.jpg',1,213,'广州','MACHENIKE 机械师T57 D6 i7四核4G独显薄游戏本GTX965M笔记本电脑',6199.00),(89,'笔记本4',4,'https://img.alicdn.com/bao/uploaded/i5/TB122N1LFXXXXXHXFXXwJ8s.VXX_111243.jpg_b.jpg',2,894,'南京','Hasee/神舟 战神 CN15S02 Z7M-SL7D2GTX965M6代i7游戏笔记本电脑',5999.00),(90,'笔记本5',4,'https://img.alicdn.com/bao/uploaded/i7/TB1mb.fKFXXXXcoXFXXLQc_9VXX_051525.jpg_b.jpg',52,1267,'济南','Hasee/神舟 战神 K660E-i7 战斗版 4G显存GTX960M游戏本笔记本',5199.00),(91,'笔记本6',4,'https://img.alicdn.com/bao/uploaded/i2/TB1LNT6JFXXXXXtaXXXfUp5.XXX_100038.jpg_b.jpg',14,2154,'重庆','Asus/华硕 顽石 -顽石3代FL5800i7超薄笔记本手提电脑游戏本分期',4199.00),(92,'笔记本7',4,'https://img.alicdn.com/bao/uploaded/i6/TB1ZR7OKVXXXXaoaXXXY5nH7VXX_015304.jpg_b.jpg',14,123,'上海','Lenovo/联想 小新 700 I7 i5 4g独显黑白 ideapad700 游戏笔记本',4399.00),(93,'笔记本8',4,'https://img.alicdn.com/bao/uploaded/i2/TB1WiFRMFXXXXbxXVXXz3tc8pXX_022311.jpg_b.jpg',78,9814,'北京','正品国行 Apple/苹果 12 英寸 MacBook 256GB 超薄商务笔记本电脑',7499.00),(94,'笔记本9',4,'https://img.alicdn.com/bao/uploaded/i4/TB1JXKELXXXXXX_XpXX1FG7.pXX_103510.jpg_b.jpg',15,68,'武汉','MSI/微星 威龙 GE62 6QD-1077XCN 酷睿四核i7游戏笔记本电脑分期',8489.00),(95,'笔记本10',4,'https://img.alicdn.com/bao/uploaded/i2/TB1fXNlKpXXXXXsaXXXCbB.8FXX_025720.jpg_b.jpg',5,789,'深圳','游戏本RABOOK/镭波 Firebat F760S2 GTX970M独显酷睿i7电脑笔记本',9870.00),(96,'耳机1',5,'https://img.alicdn.com/bao/uploaded/i3/T1rRKiFudmXXaCab76_060759.jpg_b.jpg',5,789,'广东深圳','Audio Technica/铁三角 ATH-CLR100 手机音乐运动入耳式耳机',129.00),(97,'耳机2',5,'https://img.alicdn.com/bao/uploaded/i8/TB1ag9tIVXXXXbYaFXXOwfn_FXX_062140.jpg_b.jpg',15,987,'广东深圳','OPPO mh124原装耳机正品 Find7 R7 N3 N1 通用音乐耳机入耳式',48.00),(98,'耳机3',5,'https://img.alicdn.com/bao/uploaded/i8/T1B4AsFrliXXaf3LQV_020501.jpg_b.jpg',20,152,'广东深圳','SENNHEISER/森海塞尔 MX375 手机耳机 耳塞式重低音运动耳机erji',139.00),(99,'耳机4',5,'https://img.alicdn.com/bao/uploaded/i4/TB14tu4MpXXXXbsXVXX7gl_.FXX_105646.jpg_b.jpg',10,356,'广东深圳','Huawei/华为 AM12plus 引擎耳机原装正品入耳式通用荣耀6P8mate7',79.00),(100,'耳机5',5,'https://img.alicdn.com/bao/uploaded/i6/TB1oytxJpXXXXcqXpXXdI2t8VXX_032630.jpg_b.jpg',8,68,'广东深圳','SADES/赛德斯 A55电脑耳机头戴式重低音乐台式震动游戏耳麦带话筒',99.00),(101,'耳机6',5,'https://img.alicdn.com/bao/uploaded/i3/TB1XTrRHpXXXXcRXXXXLsU5FpXX_122545.jpg_b.jpg',7,985,'广东深圳','【12期免息】Beats Solo2 Wireless无线蓝牙运动耳麦B头戴式耳机',1798.00),(102,'耳机7',5,'https://img.alicdn.com/bao/uploaded/i1/TB1XLRNKXXXXXcBXXXXtkM08FXX_031051.jpg_b.jpg',1,35,'广东深圳','西伯利亚 V10游戏耳机带话筒头戴式PC台式电脑耳麦震动发光lol cf',139.00),(103,'耳机8',5,'https://img.alicdn.com/bao/uploaded/i1/TB1sKFLKVXXXXaRXFXXZPLS7FXX_012441.jpg_b.jpg',23,10,'广东深圳','Pisen/品胜 G201入耳式5苹果6耳机6s iphone6 5s线控4s手机耳塞式',39.00),(104,'耳机9',5,'https://img.alicdn.com/bao/uploaded/i4/TB1A7tJKpXXXXXyaXXXSmrj9FXX_044354.jpg_b.jpg',2,36,'广东深圳','QCY qy19魅影运动音乐4.1无线蓝牙耳机跑步挂耳通用4.0双入耳塞式',99.90),(105,'耳机10',5,'https://img.alicdn.com/bao/uploaded/i6/TB1g2yTKXXXXXbrXXXX2FuYFFXX_124120.jpg_b.jpg',8,68,'广东深圳','Audio Technica/铁三角 ATH-CK330IS线控带麦入耳式耳机手机',189.00),(106,'手机1',7,'https://img.alicdn.com/bao/uploaded/i3/TB1gwO9KFXXXXbmXpXXPxs7.pXX_105124.jpg_b.jpg',8,98,'广东深圳','现货送魔镜 Xiaomi/小米 小米Max 高配版128G版全网通4G智能手机',782.00),(107,'手机2',7,'https://img.alicdn.com/bao/uploaded/i5/TB1g6HBKpXXXXbZXpXXWbPt.pXX_103710.jpg_b.jpg',7,15,'广东深圳','红米3S双卡双待金属智能手机包邮Xiaomi/小米 红米手机3S 高配版',1564.00),(108,'手机3',7,'https://img.alicdn.com/bao/uploaded/i5/TB1yapqMFXXXXaDXFXX.MVQ9pXX_040356.jpg_b.jpg',5,12,'广东深圳','发货到20点【送耳机+壳+膜】Meizu/魅族 魅蓝note3 全网通4G手机',2665.00),(109,'手机4',7,'https://img.alicdn.com/bao/uploaded/i3/TB1rcjdKXXXXXaeaXXXx0z69FXX_044737.jpg_b.jpg',16,123,'广东深圳','现货【送电源+耳机】Meizu/魅族 魅蓝note3 32G高配版手机魅族MX6',3548.00),(110,'手机5',7,'https://img.alicdn.com/bao/uploaded/i3/TB1BGoSJVXXXXcLXXXXCLtWFFXX_093307.jpg_220x220.jpg',8,560,'广东深圳','Apple/苹果 iPhone6s 64G 全网通4G智能手机 苏宁直发正品国行',6879.00),(111,'手机6',7,'https://img.alicdn.com/bao/uploaded/i5/TB1k_cxJVXXXXXNXVXXoZ41FFXX_093328.jpg_220x220.jpg',7,695,'广东深圳','原封国行【全新未激活】Apple/苹果 iPhone 6s 4.7英寸 4G手机',7894.00),(112,'手机7',7,'https://img.alicdn.com/bao/uploaded/i1/TB17HAKJVXXXXb1XpXXCw8QFFXX_093245.jpg_220x220.jpg',7,1526,'广东深圳','【64G特价】Apple/苹果 iPhone 6s 4.7英寸 全网通手机 原封国行',9851.00),(113,'手机8',7,'https://img.alicdn.com/bao/uploaded/i3/TB1Y9jhJVXXXXcPXVXXaYtr.pXX_102354.jpg_b.jpg',5,1234,'广东深圳','12期免息 Samsung/三星 Galaxy C5 SM-C5000 智能手机 全网通',2150.00),(114,'手机9',7,'https://img.alicdn.com/bao/uploaded/i6/TB1D0enHVXXXXXgaXXXhM4mFFXX_093017.jpg_b.jpg',6,412,'广东深圳','【直降1400元】Samsung/三星 Galaxy S6 Edge SM-G9250公开版手机',3541.00),(115,'手机10',7,'https://img.alicdn.com/bao/uploaded/i8/TB1HkU_MXXXXXakaXXXgLaK.VXX_112208.jpg_b.jpg',9,95,'广东深圳','【华为官方 买就送转接头 陶瓷白限量抢 】Huawei/华为 P9全网通',6520.00),(117,'特价2',15,'https://img.alicdn.com/bao/uploaded/i4/TB1vDRzKVXXXXbmXFXXXXXXXXXX_!!2-item_pic.png_b.jpg',20,200,'深圳','DanielWellington男表潮男式腕表丹尼尔惠灵顿皮带dw手表男石英表',1490.00),(118,'特价3',15,'https://img.alicdn.com/bao/uploaded/i5/TB19c6yGVXXXXXNaXXX.u.__XXX_054448.jpg_b.jpg',0,1000,'广州','赫恩男士洗面奶控油祛痘印去黑头深层清洁 保湿美白洁面乳护肤品',59.00),(119,'特价4',15,'https://img.alicdn.com/bao/uploaded/i3/1955207089/T26CaaXP4XXXXXXXXX_!!1955207089.jpg_b.jpg',0,123,'广州','大力人血木柄 老式 剃刀 刮脸刀 剃须刀 手动 刮胡刀 剃头刀锋利',126.00),(120,'特价5',15,'https://img.alicdn.com/bao/uploaded/i4/877629054/TB2PtkMspXXXXcsXpXXXXXXXXXX_!!877629054.jpg_b.jpg',10,520,'上海','好孩子婴儿推车高景观 可躺可坐宝宝手推车 一拎折叠减震童车C450',659.00),(121,'特价6',15,'https://img.alicdn.com/bao/uploaded/i3/TB1LTpoLXXXXXbsXXXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',10,157,'福建','千纸鹤男装夏季薄款纳米全棉商务男士休闲裤男直筒长裤子宽松男裤',148.00),(122,'特价7',15,'https://img.alicdn.com/bao/uploaded/i4/TB1Ex4TLXXXXXXOXFXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',5,752,'杭州','森马夹克 2016秋装新款 男士纯色连帽拉链休闲卫衣针织茄克外套潮',139.90),(123,'特价8',15,'https://img.alicdn.com/bao/uploaded/i8/TB1nRo_JVXXXXbPXXXXdvki8FXX_030420.jpg_b.jpg',0,98,'深圳','猎狐台式笔记本通用电脑USB防水有线键盘背光游戏键盘机械手感',25.80),(124,'特价9',15,'https://img.alicdn.com/bao/uploaded/i1/TB184hDLXXXXXb3XVXXXXXXXXXX_!!0-item_pic.jpg_320x320.jpg',0,8510,'上海','酣畅原切素颜牛排套餐3份团购家庭牛肉原味非腌制牛排尝鲜装',79.00),(125,'特价10',15,'https://img.alicdn.com/bao/uploaded/i1/1757702284/TB2GQqPtpXXXXc5XpXXXXXXXXXX_!!1757702284.jpg_b.jpg',10,890,'北京','中酒网 五粮液股份公司A级珍品52度500ml*2浓香型国产白酒双支装',169.00),(126,'特价11',15,'https://img.alicdn.com/bao/uploaded/i2/2177039307/TB25dsOqVXXXXcCXXXXXXXXXXXX_!!2177039307.jpg_b.jpg',5,9452,'广东广州','雷士照明led吸顶灯 客厅灯 简约现代长方形大气卧室房间餐厅灯具',246.00);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

#
# Structure for table "goodscar"
#

DROP TABLE IF EXISTS `goodscar`;
CREATE TABLE `goodscar` (
  `goodscar_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `imagepath` varchar(255) DEFAULT NULL,
  `goodsdescribe` varchar(255) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`goodscar_id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

#
# Data for table "goodscar"
#

/*!40000 ALTER TABLE `goodscar` DISABLE KEYS */;
INSERT INTO `goodscar` VALUES (66,1,1,6,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:红大小:100','书',144.00),(67,2,1,3,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:133333','hh',11.00),(68,1,30,1,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:200','书',333.00),(69,1,30,1,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:200','书',333.00),(70,1,30,4,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:200','书',333.00),(71,1,1,16,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:红大小:200','书',322.00),(72,2,1,8,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:133333','hh',11.00),(73,1,0,5,0,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:100','书1',111.00),(74,46,1,6,1,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',' 大小:18号颜色:粉色款式:中国版','连衣裙1',125.00),(75,46,1,7,1,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',' 大小:18号颜色:粉色款式:中国版','连衣裙1',125.00),(76,2,1,5,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:蓝色','书2',11.00),(77,2,1,1,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:蓝色','书2',11.00),(78,2,1,1,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:蓝色','书2',11.00),(79,2,1,7,1,'http://192.168.1.100:80/test/goodpicture/1.jpg','大小:蓝色','书2',11.00),(80,1,1,6,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:100','书1',111.00),(81,1,1,5,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:200','书1',111.00),(82,1,1,4,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:100','书1',111.00),(83,1,1,6,0,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:100','书1',111.00),(84,46,47,5,1,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',' 大小:20号颜色:栗色款式:美国版','连衣裙1',125.00),(85,1,51,4,1,'http://192.168.1.100:80/test/goodpicture/0.jpg','颜色:黄大小:100','书1',111.00),(86,46,51,1,1,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',' 大小:17号颜色:栗色款式:美国版','连衣裙1',125.00),(87,46,51,6,1,'https://img.alicdn.com/bao/uploaded/i3/TB1h2m1KVXXXXXaXVXXXXXXXXXX_!!0-item_pic.jpg_b.jpg',' 大小:20号颜色: 红色款式:美国版','连衣裙1',121.00);
/*!40000 ALTER TABLE `goodscar` ENABLE KEYS */;

#
# Structure for table "myorder"
#

DROP TABLE IF EXISTS `myorder`;
CREATE TABLE `myorder` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_state` int(11) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `total_price` float(10,2) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

#
# Data for table "myorder"
#

/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (134,2,'2016-07-29 16:38:54',38,44.00,1,'hh大小:133333',2),(135,2,'2016-07-29 16:40:22',38,322.00,1,'书颜色:红大小:200',1),(136,2,'2016-07-29 16:41:55',36,5152.00,1,'书颜色:红大小:200',1),(137,2,'2016-07-29 17:22:25',38,33.00,1,'hh大小:133333',2),(138,1,'2016-07-29 19:47:56',40,1332.00,30,'书颜色:黄大小:200',1),(139,2,'2016-07-29 20:23:38',38,1000.00,1,'连衣裙1 大小:18号颜色:粉色款式:中国版',46),(140,2,'2016-07-29 20:30:47',38,1240.00,1,'连衣裙1 大小:18号颜色:粉色款式:美国版',46),(141,2,'2016-07-30 09:05:57',38,88.00,1,'hh大小:133333',2),(142,2,'2016-07-30 16:06:49',43,555.00,1,'书1颜色:黄大小:100',1),(143,1,'2016-07-30 16:07:56',36,750.00,1,'连衣裙1 大小:18号颜色:粉色款式:中国版',46),(144,1,'2016-07-30 16:09:18',36,875.00,1,'连衣裙1 大小:18号颜色:粉色款式:中国版',46),(145,1,'2016-07-30 16:10:16',36,55.00,1,'书2大小:蓝色',2),(146,0,'2016-07-30 16:15:17',36,11.00,1,'书2大小:蓝色',2),(147,0,'2016-07-30 16:30:34',36,11.00,1,'书2大小:蓝色',2),(148,0,'2016-07-30 16:31:24',43,77.00,1,'书2大小:蓝色',2),(149,0,'2016-07-30 16:32:23',43,666.00,1,'书1颜色:黄大小:100',1),(150,0,'2016-07-30 16:32:49',36,555.00,1,'书1颜色:黄大小:200',1),(151,1,'2016-07-30 16:34:46',36,444.00,1,'书1颜色:黄大小:100',1),(152,0,'2016-07-30 16:39:19',36,44.00,1,'书2大小:蓝色',2),(153,1,'2016-07-30 19:46:51',46,500.00,47,'连衣裙1 大小:19号颜色:栗色款式:美国版',46),(154,0,'2016-07-30 19:50:43',46,444.00,47,'书1颜色:黄大小:200',1),(155,2,'2016-07-30 20:17:24',47,44.00,51,'书2大小:蓝色',2),(156,1,'2016-07-30 20:18:17',47,569.00,51,'连衣裙1 大小:17号颜色:栗色款式:美国版书1颜色:黄大小:100',46);
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;

#
# Structure for table "ordergoods"
#

DROP TABLE IF EXISTS `ordergoods`;
CREATE TABLE `ordergoods` (
  `ordergoods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ordergoods_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "ordergoods"
#

/*!40000 ALTER TABLE `ordergoods` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordergoods` ENABLE KEYS */;

#
# Structure for table "scharacherist"
#

DROP TABLE IF EXISTS `scharacherist`;
CREATE TABLE `scharacherist` (
  `scharacherist_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scharacherist_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

#
# Data for table "scharacherist"
#

/*!40000 ALTER TABLE `scharacherist` DISABLE KEYS */;
INSERT INTO `scharacherist` VALUES (1,1,'颜色'),(2,1,'大小'),(3,2,'大小'),(4,5,' 机身颜色'),(5,5,'存储'),(6,7,'CPU'),(7,7,'运营商制式'),(8,7,'购买形式'),(9,46,' 大小'),(10,46,'颜色'),(11,46,'款式'),(12,76,'镜头'),(13,76,'是否带支架'),(14,77,'相机材质'),(15,77,'种类');
/*!40000 ALTER TABLE `scharacherist` ENABLE KEYS */;

#
# Structure for table "scharacteristdetial"
#

DROP TABLE IF EXISTS `scharacteristdetial`;
CREATE TABLE `scharacteristdetial` (
  `scharacteristdetial_id` int(11) NOT NULL AUTO_INCREMENT,
  `scharacherist_id` int(11) DEFAULT NULL,
  `detial` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`scharacteristdetial_id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

#
# Data for table "scharacteristdetial"
#

/*!40000 ALTER TABLE `scharacteristdetial` DISABLE KEYS */;
INSERT INTO `scharacteristdetial` VALUES (1,1,'红',100,100.00),(2,1,'黄',111,111.00),(3,2,'100',44,0.00),(4,2,'200',222,0.00),(5,3,'蓝色',111,11.00),(6,4,'深空灰',150,450.00),(7,4,'白色',450,500.00),(8,4,'金色',410,264.00),(9,5,'16g',100,0.00),(10,5,'32g',200,100.00),(11,5,'64g',444,200.00),(12,6,'联发科',200,1000.00),(13,6,'高通',200,1200.00),(14,7,'联通',100,100.00),(15,7,'移动',200,200.00),(16,7,'电信',100,1300.00),(17,8,'裸机',100,500.00),(18,8,'套餐',200,600.00),(19,9,'16号',200,100.00),(20,9,'17号',150,100.00),(21,9,'18号',123,100.00),(22,9,'19号',100,100.00),(23,9,'20号',100,100.00),(24,10,' 红色',12,12.00),(25,10,'粉色',14,15.00),(26,10,'栗色',14,16.00),(27,11,'中国版',12,10.00),(28,11,'美国版',15,9.00),(29,12,'200mm',10,1000.00),(30,12,'250mm',100,2000.00),(31,12,'300mm',200,3000.00),(32,13,'带',10,500.00),(33,13,'不带',100,0.00),(34,14,'牛皮',200,1000.00),(35,14,'革',15,500.00),(36,15,' 单发机身',10,1000.00),(37,15,'套机',12,1200.00);
/*!40000 ALTER TABLE `scharacteristdetial` ENABLE KEYS */;

#
# Structure for table "type"
#

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

#
# Data for table "type"
#

/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'书籍'),(2,'衣服'),(3,'44'),(4,'笔记本'),(5,'耳机'),(6,'平板'),(7,'手机'),(8,'相机'),(9,'学习机'),(10,'背心裙'),(11,'短裙'),(12,'连衣裙'),(13,'牛仔裙'),(14,'长裙'),(15,'特价');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  `VIP` int(5) DEFAULT NULL,
  `balance` float(10,2) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'白天睡觉','123','男',0,0.00,'0'),(24,'白天哦哦','123','男',0,0.00,'0'),(25,'111','123','男',0,0.00,'0'),(26,'222','123','男',0,0.00,'0'),(27,'你好','123','男',0,0.00,'0'),(28,'aaaa','','dddddd',0,0.00,'0'),(29,'545','shhs','额一盒',0,0.00,'0'),(30,'12345','1','阿',0,0.00,'0'),(31,'公司公司高','sgsga','男',0,0.00,'0'),(41,'1111','1','男',0,0.00,'0'),(43,'ABC','123','男',0,0.00,'0'),(44,'AA','123','男',0,0.00,'0'),(45,'123','1','1',0,0.00,'0'),(46,'v','123','123',0,0.00,'0'),(47,'这','123','男',0,0.00,'0'),(48,'把','123','男',0,0.00,'0'),(49,'梦','123','男',0,0.00,'0'),(50,'景','123','男',0,0.00,'0'),(51,'不','123','男',0,0.00,'0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
