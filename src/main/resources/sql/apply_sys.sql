-- MySQL dump 10.13  Distrib 5.7.16, for Win32 (AMD64)
--
-- Host: localhost    Database: apply_sys
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apply` (
  `apply_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `period_name` varchar(45) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `recommend` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `anticipate` varchar(45) DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `website` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
INSERT INTO `apply` VALUES (1,NULL,'阿里',1,'2017-09-06',NULL,NULL,'111',NULL,NULL,'ada',NULL),(3,NULL,'腾讯',1,'2017-09-06',NULL,NULL,'等待笔试',NULL,NULL,'ada','campus.tencent.com'),(4,NULL,'阿里',5,'2017-09-06','2017-09-06',NULL,'结束',NULL,NULL,'baihui0330','baidu.com'),(5,NULL,'阿里爸爸',6,'2017-09-06','2017-09-06','內推人','等待笔试','7','拿到','abam','campus.alibaba.com'),(7,NULL,'阿里',6,'2017-09-06',NULL,NULL,'等待',NULL,NULL,'abam','https://campus.alibaba.com/index.htm');
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `industry` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_ticket`
--

DROP TABLE IF EXISTS `login_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `expired` datetime DEFAULT NULL,
  `ticket` varchar(45) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_ticket`
--

LOCK TABLES `login_ticket` WRITE;
/*!40000 ALTER TABLE `login_ticket` DISABLE KEYS */;
INSERT INTO `login_ticket` VALUES (1,3,'2017-09-06 15:07:07','80ed50323ff849b7a773355a9bacceaf',0),(2,4,'2017-09-06 15:13:13','da084bb5af724543934429a0744bea38',0),(3,5,'2017-09-06 16:22:08','824f49c03ce74aeb8ef20a6e0bd0fc69',0),(4,6,'2017-09-06 16:37:21','ee493d3d93fb4485bfc0e0bc0f81aac4',0),(5,7,'2017-09-06 16:42:06','a5afe0880e68490fbba73c698689e4dc',0),(6,1,'2017-09-06 18:52:53','3617f5916e30481ab3f60b7a5b77b5af',0),(7,2,'2017-09-06 19:15:22','751975a2e2ae44b8a076c9adedb81da4',0),(8,3,'2017-09-06 19:38:17','211f99b55d424eb9b66a323b7956dbf1',0),(9,4,'2017-09-06 19:45:50','d418906698314c929a9c9bf8b5778e6d',0),(10,3,'2017-09-06 19:56:46','efc695e123c24de6858151d3a98697d2',0),(11,3,'2017-09-06 20:01:18','344126fd36394c85b3d0ba59a9807682',0),(12,5,'2017-09-06 20:07:07','a0b55b5a4dfd4a12b63fc53c8d3f8327',0),(13,6,'2017-09-06 20:11:52','ec2fef6ae80741988be00e1c2aa4ac79',0),(14,7,'2017-09-06 20:16:22','a0dc2aa3c7824cd189fad2a819dd2cdc',0),(15,1,'2017-09-06 20:29:32','3da5ea5e7e3b473baf8b7980aaa00b5f',0),(16,1,'2017-09-06 20:34:47','ce93022dc3e444bbb7edac374b09a220',0),(17,2,'2017-09-06 20:36:08','4fc3f2abc65f4eff8bb5d898468bae77',0),(18,1,'2017-09-06 20:39:02','06b501ac31504ad69b42234acced4723',0),(19,1,'2017-09-06 20:40:01','dcea39c5231e4ae6879f65cb1d7149fa',0),(20,1,'2017-09-06 20:40:43','c513f51accdf45018408b6e594b6c4f8',0),(21,1,'2017-09-06 20:41:36','69cbdf097d5c4c3bb9ff9e483bc2c6ee',0),(22,1,'2017-09-06 20:42:54','55d817d3eaa841e99b796899cde29e7b',0),(23,1,'2017-09-06 21:01:26','fdab31daf4f8483189e40356a3ca4e4c',0),(24,1,'2017-09-06 21:03:37','925402142cc94c94b4be24f1f2d27c1c',0),(25,1,'2017-09-06 21:06:18','386c7af281014bf8a2622fd1216f9bdf',0),(26,1,'2017-09-06 21:07:07','bc086f8b2b1a4471882078f3f3b97204',0),(27,1,'2017-09-06 21:07:39','7e77fd12f7bc4007bf64976546dd177f',0),(28,1,'2017-09-06 21:09:00','248515184b7d4b9cb5467af641a77287',0),(29,1,'2017-09-06 21:11:51','7ae9c888716941609635bdbdf7cedb11',0),(30,1,'2017-09-06 21:12:44','4b83c680a5894317aaf13484fc429760',0),(31,1,'2017-09-06 21:16:40','265e87e4521e4187917217c4ea6a6875',0),(32,1,'2017-09-06 21:46:01','6751e321988249ffb164d267eefeadd1',0),(33,1,'2017-09-06 21:48:47','7ef3374bffb14a4aafb7ce02d8c9dd80',0),(34,1,'2017-09-06 21:53:02','353d0e366fbf4c16b7c325c9e7091253',0),(35,1,'2017-09-06 23:15:19','c4599185e5c44ff884a9f49474ea06f4',0),(36,1,'2017-09-06 23:17:32','0b204a6dd3d94bbbb54f4e1e21e31c32',0),(37,1,'2017-09-06 23:23:55','e7415ef5d28648fd9688fb913f47ad7f',0),(38,1,'2017-09-07 08:23:33','03823999548649bb9f60805fc0df1a80',0),(39,1,'2017-09-07 08:24:14','afbab7385de549a9b6db5d67df249d55',0),(40,1,'2017-09-07 08:34:39','ef38d8ad5c6449c08b979377fece3f8d',0),(41,1,'2017-09-07 08:36:50','4e404f1031a947ed91eb3bac6c99dc15',0),(42,1,'2017-09-07 08:41:46','ad9f02a098874be2b524f47d64f52bea',0),(43,3,'2017-09-07 09:02:47','c4fc72fc08c5471cbf438ea0028084d7',0),(44,3,'2017-09-07 09:11:18','752c2f04c73c43c8890ff723448fe13c',0),(45,3,'2017-09-07 09:13:42','8ad38d111c0b4e11851708695f432633',0),(46,3,'2017-09-07 09:16:15','213e738cb2a04dc5b2a3c39b2cfd05c1',0),(47,3,'2017-09-07 09:18:26','a09d7d5c20024a388e32b0225728d94c',0),(48,3,'2017-09-07 09:26:30','f51e6e93ee5e44d485c404fdf84f6bc6',0),(49,3,'2017-09-07 09:32:59','9c8a0db4d38a4daca1798c15a3930e95',0),(50,3,'2017-09-07 09:33:43','ecdd282689ce4fb0a88f9e02d7118ada',0),(51,3,'2017-09-07 09:43:37','81a2a2abfe414224ab8f35b669a19b8d',1),(52,4,'2017-09-07 09:58:03','674afc84ae114b83bcccec9adda7aa1b',1),(53,4,'2017-09-07 10:00:23','54a5001c81274942af918d805e55eb98',0),(54,4,'2017-09-07 10:03:24','f9088d3b7c454ba7a76de1f44b58bb4d',1),(55,4,'2017-09-07 10:07:23','ca0796e46d9d45d8a3e6384c62e5008a',1),(56,4,'2017-09-07 10:53:25','53d1587803b34e73b0a35d1344b387ab',1),(57,4,'2017-09-07 11:02:17','cc9fef97d008405e9aec41f6cb62ec11',1),(58,4,'2017-09-07 11:03:29','31abf7c9ae604ec5b2c69349f7d3dc74',1),(59,4,'2017-09-07 11:05:03','96815c4487a84bd48b214276892460ea',1),(60,4,'2017-09-07 11:08:09','fffe39b6f88d41a0a2f258ce5c0d6c9c',1),(61,4,'2017-09-07 12:18:01','c55430ac5183494c88add879a372dbbf',1),(62,1,'2017-09-07 13:43:09','a99c2429a7034d93b6c9d798785ffc06',1),(63,1,'2017-09-07 13:45:27','cf466198b5c246b88b3655904b4b0366',1),(64,1,'2017-09-07 13:48:49','ce9e91551d9e4584b2ca17288253a7eb',1),(65,1,'2017-09-07 13:49:05','bd0171163fb14f5e8f1cfbf766c4de2d',0),(66,1,'2017-09-07 13:49:21','f39f7bdaff58493293aedd31e36d9b4a',1),(67,1,'2017-09-07 19:34:02','25e6d91d2ac049eb9de5c104dff08b8f',0),(68,1,'2017-09-07 19:44:45','d5d0a27899e1498588bf593ed2b0b25b',1),(69,1,'2017-09-07 19:54:50','61a76783af6044da817ea6ca3d608c26',1),(70,1,'2017-09-07 19:58:20','18cac5e95cd048618a20a4743182da22',1),(71,1,'2017-09-07 20:10:40','451a617e56684a178d1e9eca7ebb237e',1),(72,1,'2017-09-07 20:19:59','bd56d8b0a0174b5183e0566ac79d6a6e',1),(73,1,'2017-09-07 20:22:03','1b1b1caa67cc422bb184d98b1bf3eb0b',1),(74,1,'2017-09-07 20:22:47','80844b3346e44c8584f4b0cdfe0f6045',1),(75,1,'2017-09-07 20:23:25','6516071d81a2482eae574cf4f52f1f78',1),(76,1,'2017-09-07 20:26:13','243fd29a077442afa71532a4eb6c2949',1),(77,5,'2017-09-07 20:28:02','8140ece1cbc74dcca86b48d858d58d05',1),(78,1,'2017-09-07 20:31:58','f2cee3d93e6a47909277e48f5771ede3',1),(79,1,'2017-09-07 20:33:03','d1c2d5f469d34706a5c5816bf8ab633e',1),(80,1,'2017-09-07 20:33:42','b7628abc67f247a4ab5864d6da2c47e4',1),(81,1,'2017-09-07 20:42:46','5d04f2bafe5249d383a309bad7710ade',1),(82,1,'2017-09-07 20:45:01','0204eb07f35b4371817487c6045f608d',1),(83,1,'2017-09-07 20:54:40','d24accd3378545ea86d625545e76c7b5',1),(84,6,'2017-09-07 20:56:49','b1eb1a7c1ed24a43984ac29cb4a8e145',1),(85,6,'2017-09-07 21:00:47','90fdadce2edd4d03a0671c9e38fbaf6e',0);
/*!40000 ALTER TABLE `login_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `period_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`period_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'f14e7d213cb82e1e30c41caafd9b864d','f14d3','0:0:0:0:0:0:0:1','ada'),(2,NULL,'9b7e063b50b67116e5e594cb35a9d518','cc809','0:0:0:0:0:0:0:1','adam'),(3,NULL,'0a67c0498c091069becd51011e8a83fa','54053','0:0:0:0:0:0:0:1','adamm'),(4,NULL,'4ec85dc3645df551780a9cd5ab49008d','4ebaa','0:0:0:0:0:0:0:1','aba'),(5,NULL,'40bac6ca483e22fb07ce309e0dd3c66e','12328','0:0:0:0:0:0:0:1','baihui0330'),(6,NULL,'56d9d90483fc853d71cbb12b6a031eb9','3fb4f','0:0:0:0:0:0:0:1','abam');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-06 21:12:28
