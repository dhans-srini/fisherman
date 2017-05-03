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
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form`
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;
INSERT INTO `form` VALUES (13,'a','b',1,'c','9191d3048','2017-04-30 14:15:43',NULL,'accepted'),(14,'sample name','sample fathers name',1,'sample section','c6b5a8ab1','2017-04-30 14:47:35','2017-05-02 16:08:32','rejected'),(15,'new1name','new1f',1,'new1section','8f9b2592b','2017-05-02 13:43:19',NULL,'submitted'),(16,'new2name','new2f',1,'new2section','c15a4eef2','2017-05-02 13:48:22','2017-05-02 15:14:15','submitted'),(17,'adfadf','asdf',1,'adf','21c40c52e','2017-05-02 15:27:13',NULL,'submitted'),(18,'adfadf','asdf',1,'adf','2faf0bb60','2017-05-02 15:27:23',NULL,'submitted');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_review_history`
--

LOCK TABLES `form_review_history` WRITE;
/*!40000 ALTER TABLE `form_review_history` DISABLE KEYS */;
INSERT INTO `form_review_history` VALUES (1,'asdfsadfas','rejected',5,13,'2017-05-02 16:06:37'),(2,'','accepted',5,13,'2017-05-02 16:07:31'),(3,'dsDAD','rejected',5,14,'2017-05-02 16:08:37');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'admin','\Éiv18)\n ¨Ö«F#«¿w','2017-05-02 21:39:23',1,NULL,NULL,NULL,1),(6,'test','³¯@›¸B1‡\Ç^l[h9','2017-05-02 21:26:04',0,'testtest',5,5,1),(8,'testtest','³¯@›¸B1‡\Ç^l[h9',NULL,0,'test',5,NULL,0);
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

-- Dump completed on 2017-05-03 14:11:25


-- New columns added  ----------

ALTER TABLE `fisher_man`.`form`   
  ADD COLUMN `address` TEXT NULL AFTER `status`,
  ADD COLUMN `biometric_id` VARCHAR(20) NULL AFTER `address`,
  ADD COLUMN `ration_card_id` VARCHAR(20) NULL AFTER `biometric_id`,
  ADD COLUMN `voter_id` VARCHAR(20) NULL AFTER `ration_card_id`,
  ADD COLUMN `adhaar_no` VARCHAR(50) NULL AFTER `voter_id`,
  ADD COLUMN `national_bank_details` VARCHAR(500) NULL AFTER `adhaar_no`,
  ADD COLUMN `group_acc_dtls` VARCHAR(500) NULL AFTER `national_bank_details`,
  ADD COLUMN `coop_bank_dtl` VARCHAR(100) NULL AFTER `group_acc_dtls`,
  ADD COLUMN `tn_fish_association_dtl` VARCHAR(100) NULL AFTER `coop_bank_dtl`,
  ADD COLUMN `work_type` VARCHAR(50) NULL AFTER `tn_fish_association_dtl`,
  ADD COLUMN `is_prev_year_benefitter` TINYINT(1) NULL AFTER `work_type`,
  ADD COLUMN `benefitted_year` VARCHAR(10) NULL AFTER `is_prev_year_benefitter`,
  ADD COLUMN `is_benefitter` TINYINT(1) NULL AFTER `benefitted_year`,
  ADD COLUMN `is_getting_relief_fund` TINYINT(1) NULL AFTER `is_benefitter`;
  
  
  ALTER TABLE `fisher_man`.`form`   
  CHANGE `is_prev_year_benefitter` `is_prev_year_benefitter` TINYINT(1) DEFAULT 0  NULL,
  CHANGE `is_benefitter` `is_benefitter` TINYINT(1) DEFAULT 0  NULL,
  CHANGE `is_getting_relief_fund` `is_getting_relief_fund` TINYINT(1) DEFAULT 0  NULL;
