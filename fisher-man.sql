CREATE DATABASE  IF NOT EXISTS `fisher_man` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fisher_man`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: fisher_man
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `form`
--

DROP TABLE IF EXISTS `form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `fathers_name` varchar(200) DEFAULT NULL,
  `is_married` tinyint(1) DEFAULT NULL,
  `section` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `address` text,
  `biometric_id` varchar(20) DEFAULT NULL,
  `ration_card_id` varchar(20) DEFAULT NULL,
  `voter_id` varchar(20) DEFAULT NULL,
  `adhaar_no` varchar(50) DEFAULT NULL,
  `national_bank_details` varchar(500) DEFAULT NULL,
  `group_acc_dtls` varchar(500) DEFAULT NULL,
  `coop_bank_dtl` varchar(100) DEFAULT NULL,
  `tn_fish_association_dtl` varchar(100) DEFAULT NULL,
  `work_type` varchar(50) DEFAULT NULL,
  `is_prev_year_benefitter` tinyint(1) DEFAULT '0',
  `benefitted_year` varchar(10) DEFAULT NULL,
  `is_benefitter` tinyint(1) DEFAULT '0',
  `is_getting_relief_fund` tinyint(1) DEFAULT '0',
  `type` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `income_det` varchar(500) DEFAULT NULL,
  `bio_metric_app_form_det` varchar(500) DEFAULT NULL,
  `is_full_time_fisherman` tinyint(4) DEFAULT NULL,
  `village` varchar(45) DEFAULT NULL,
  `taluk` varchar(45) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form`
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;
INSERT INTO `form` VALUES (13,'a','b',1,'c','9191d3048','2017-04-30 14:15:43',NULL,'accepted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'sample name','sample fathers name',1,'sample section','c6b5a8ab1','2017-04-30 14:47:35','2017-05-02 16:08:32','accepted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'new1name','new1f',1,'','8f9b2592b','2017-05-02 13:43:19','2017-05-04 18:10:48','submitted','','','','','','','','','','',0,NULL,0,0,NULL,'',NULL,'',NULL,NULL,NULL,NULL),(16,'new2name','new2f',1,'new2section','c15a4eef2','2017-05-02 13:48:22','2017-05-02 15:14:15','submitted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'adfadf','asdf',1,'adf','21c40c52e','2017-05-02 15:27:13',NULL,'submitted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'adfadf','asdf',1,'adf','2faf0bb60','2017-05-02 15:27:23','2017-05-03 18:44:12','submitted',NULL,'','','','','','','','','',0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'asdf','asdf',1,'dfa','2372a07b6','2017-05-03 20:56:48',NULL,'submitted',NULL,'adf','asdf','asdfasdf','adsf','adsf','adsf','asdf','asdf','asdf',1,'2015',1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'spl_dhs','spl_dhs_f',1,'bc','f84d2d','2017-05-04 17:47:09','2017-05-04 17:50:43','submitted','spl_dhs_a','spl_dhs_attai','spl_dhs_ration','spl_dhs_vot','spl_dhs_athar','spl_dhs_photo_bank','spl_dhs_group_ac','spl_dhs_sangam','spl_dhs_nala variam_updated','spl_dhs_padagu',1,'2017',1,1,'spl_allowance','',NULL,'',NULL,NULL,NULL,NULL),(21,'ban_dhs_name','ban_dhs_fat',1,'bc','6a967c','2017-05-04 18:02:41',NULL,'submitted','ban_dhs_add','ban_dhs_id','ban_dhs_ration','ban_dhs_vot','ban_dhs_athar','ban_dhs_bankac','ban_dhs_goup_ac','ban_dhs_sang',NULL,'ban_dhs_type',NULL,NULL,NULL,NULL,'fishing_ban_relief','ban_dhs_age',NULL,'ban_dhs_form',1,NULL,NULL,NULL),(23,'name','fathers name',1,'bc','358f31','2017-05-04 19:03:12','2017-05-04 19:08:25','submitted','address','id card no','56654','asdfdsf','adf','df','adsf','asdf','adsf','meen pidi',1,'asdf',1,1,'spl_allowance',NULL,NULL,NULL,NULL,'village','taluk','district'),(24,'erersr','erer',0,'sc','c9ce8f','2017-05-04 19:21:03','2017-05-04 19:21:22','submitted','ere','rewer','rer','erere','erer','rere','ere','','','',0,NULL,0,1,'nfsrs','',NULL,'',NULL,'efser','erser','dsrsdr'),(25,'Sankaranarayanan','Kumaresan',1,'mbc','c32441','2017-05-04 19:29:02',NULL,'accepted','343/78, Nallikuppam Nadu theru\nRK Nagar Chennai','TNFSH-098/567','TN-GH-878','TN0712387998','787649082323','NOT AVAILABALE','NOT AVAILABLE','RK NAGAR SOUTH SOCIETY','32480','COUNTRY BOAT',1,'2013',NULL,1,'nfsrs','32','TN/09/34-989','Attached the application form',NULL,'RK Nagar','Chennai North','Chennai'),(26,'','',NULL,'','605de1','2017-05-04 19:56:57',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'nfsrs','','','',NULL,'','','');
/*!40000 ALTER TABLE `form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_review_history`
--

DROP TABLE IF EXISTS `form_review_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `form_review_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(2000) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `reviewed_by` int(11) DEFAULT NULL,
  `form` int(11) DEFAULT NULL,
  `reviewed_on` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_form_reviewed_by_user_id_idx` (`reviewed_by`),
  KEY `fk_form_review_his_form_id_idx` (`form`),
  CONSTRAINT `fk_form_review_his_form_id` FOREIGN KEY (`form`) REFERENCES `form` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_reviewed_by_user_id` FOREIGN KEY (`reviewed_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_review_history`
--

LOCK TABLES `form_review_history` WRITE;
/*!40000 ALTER TABLE `form_review_history` DISABLE KEYS */;
INSERT INTO `form_review_history` VALUES (1,'asdfsadfas','rejected',5,13,'2017-05-02 16:06:37'),(2,'','accepted',5,13,'2017-05-02 16:07:31'),(3,'dsDAD','rejected',5,14,'2017-05-02 16:08:37'),(4,'asdfsdaf','rejected',5,14,'2017-05-03 20:51:34'),(5,'asdfasdf','rejected',5,14,'2017-05-04 16:16:12'),(6,'','accepted',5,14,'2017-05-04 16:24:57'),(7,'adsasdf','rejected',5,15,'2017-05-04 18:10:16'),(8,'','accepted',5,25,'2017-05-04 20:10:11');
/*!40000 ALTER TABLE `form_review_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` longblob,
  `last_login_time` datetime DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `name` varchar(200) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'admin','\Éiv18)\n ¨Ö«F#«¿w','2017-05-04 20:50:04',1,NULL,NULL,NULL,1),(6,'test','³¯@›¸B1‡\Ç^l[h9','2017-05-02 21:26:04',0,'testtest',5,5,1),(8,'testtest','³¯@›¸B1‡\Ç^l[h9',NULL,0,'test',5,NULL,0),(9,'dhans','\Éiv18)\n ¨Ö«F#«¿w','2017-05-04 20:49:51',0,'dhans',5,NULL,1),(10,'dhans01','\Éiv18)\n ¨Ö«F#«¿w',NULL,0,'dhans',5,NULL,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'fisher_man'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-04 20:55:12
