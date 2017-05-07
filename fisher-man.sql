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
  `village` bigint(20) DEFAULT NULL,
  `taluk` varchar(45) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  `socity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fkvillage_idx` (`village`),
  KEY `fk_socity_idx` (`socity`),
  CONSTRAINT `fk_socity` FOREIGN KEY (`socity`) REFERENCES `societies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkvillage` FOREIGN KEY (`village`) REFERENCES `villages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form`
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;
INSERT INTO `form` VALUES (13,'a','b',1,'c','9191d3048','2017-04-30 14:15:43',NULL,'accepted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'sample name','sample fathers name',1,'sample section','c6b5a8ab1','2017-04-30 14:47:35','2017-05-02 16:08:32','accepted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'new1name','new1f',1,'','8f9b2592b','2017-05-02 13:43:19','2017-05-04 18:10:48','rejected','','','','','','','','','','',0,NULL,0,0,NULL,'',NULL,'',NULL,NULL,NULL,NULL,NULL),(16,'new2name','new2f',1,'new2section','c15a4eef2','2017-05-02 13:48:22','2017-05-02 15:14:15','submitted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'adfadf','asdf',1,'adf','21c40c52e','2017-05-02 15:27:13',NULL,'submitted',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'adfadf','asdf',1,'adf','2faf0bb60','2017-05-02 15:27:23','2017-05-03 18:44:12','submitted',NULL,'','','','','','','','','',0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'asdf','asdf',1,'dfa','2372a07b6','2017-05-03 20:56:48',NULL,'submitted',NULL,'adf','asdf','asdfasdf','adsf','adsf','adsf','asdf','asdf','asdf',1,'2015',1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'spl_dhs','spl_dhs_f',1,'bc','f84d2d','2017-05-04 17:47:09','2017-05-04 17:50:43','submitted','spl_dhs_a','spl_dhs_attai','spl_dhs_ration','spl_dhs_vot','spl_dhs_athar','spl_dhs_photo_bank','spl_dhs_group_ac','spl_dhs_sangam','spl_dhs_nala variam_updated','spl_dhs_padagu',1,'2017',1,1,'spl_allowance','',NULL,'',NULL,NULL,NULL,NULL,NULL),(21,'ban_dhs_name','ban_dhs_fat',1,'bc','6a967c','2017-05-04 18:02:41',NULL,'submitted','ban_dhs_add','ban_dhs_id','ban_dhs_ration','ban_dhs_vot','ban_dhs_athar','ban_dhs_bankac','ban_dhs_goup_ac','ban_dhs_sang',NULL,'ban_dhs_type',NULL,NULL,NULL,NULL,'fishing_ban_relief','ban_dhs_age',NULL,'ban_dhs_form',1,NULL,NULL,NULL,NULL),(23,'name','fathers name',1,'bc','358f31','2017-05-04 19:03:12','2017-05-04 19:08:25','submitted','address','id card no','56654','asdfdsf','adf','df','adsf','asdf','adsf','meen pidi',1,'asdf',1,1,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'taluk','district',NULL),(24,'erersr','erer',0,'sc','c9ce8f','2017-05-04 19:21:03','2017-05-04 19:21:22','submitted','ere','rewer','rer','erere','erer','rere','ere','','','',0,NULL,0,1,'nfsrs','',NULL,'',NULL,NULL,'erser','dsrsdr',NULL),(25,'Sankaranarayanan','Kumaresan',1,'mbc','c32441','2017-05-04 19:29:02',NULL,'accepted','343/78, Nallikuppam Nadu theru\nRK Nagar Chennai','TNFSH-098/567','TN-GH-878','TN0712387998','787649082323','NOT AVAILABALE','NOT AVAILABLE','RK NAGAR SOUTH SOCIETY','32480','COUNTRY BOAT',1,'2013',NULL,1,'nfsrs','32','TN/09/34-989','Attached the application form',NULL,NULL,'Chennai North','Chennai',NULL),(26,'','',NULL,'','605de1','2017-05-04 19:56:57',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'nfsrs','','','',NULL,NULL,'','',NULL),(27,'c','c',1,'bc','a9ddab','2017-05-05 01:47:41',NULL,'submitted','c','c','c','c','c','c','c','c',NULL,'c',1,'test',NULL,1,'nfsrs_woman','cc',NULL,'c',NULL,NULL,'adf','asdfcc',NULL),(28,'tst','asdfasdf',1,'bc','f418b5','2017-05-05 03:03:28','2017-05-05 03:19:13','submitted','','asdfsd','dsaf','adf','asdfasdf','asfd','asdf','adsfasdf','asdf','asdf',1,'asdfa',1,1,'spl_allowance',NULL,NULL,NULL,0,1,'test','test',1),(29,'','',NULL,'','02/null/85bd80','2017-05-05 03:33:18',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','',NULL),(30,'','',NULL,'','02/null/67eb7a','2017-05-05 03:33:48',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,2,'','',15),(31,'','',NULL,'','02/null/a7681c','2017-05-05 03:37:54',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,2,'','',15),(32,'','',NULL,'','02/null/44e216','2017-05-05 03:38:58',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'nfsrs','','','',NULL,2,'','',17),(33,'','',NULL,'','02/X-264/7edeec','2017-05-05 03:40:36',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,1,'','',1),(34,'','',NULL,'','02/FMC-39/34FB08','2017-05-05 09:34:07',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'nfsrs','','','',NULL,1,'','',7),(35,'‡Æ§‡Æ©‡Æö‡Øá‡Æï‡Æ∞‡Æ©‡Øç ','‡Æ§‡Æ©‡Æö‡Øá‡Æï‡Æ∞‡Æ©‡Øç ',0,'bc','02/FMC-26/DF1E85','2017-05-05 09:54:18','2017-05-07 10:30:33','submitted','','','','','','','','','','',0,NULL,0,0,'spl_allowance',NULL,NULL,NULL,0,2,'‡Æ§‡Æ©‡Æö‡Øá‡Æï‡Æ∞‡Æ©‡Øç ','Chennai',19),(36,'','',NULL,'','02/\r\nFMC-17\r\n/051EF8','2017-05-05 10:05:41',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,2,'asdfa','asdf',26),(37,'','',NULL,'','02/X-288/1B26A2','2017-05-05 10:06:00',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,1,'asdf','asdf',6),(38,'','',NULL,'','02/FMC-26/0ED704','2017-05-05 10:06:38',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,1,'asdfa','',3),(39,'asdf','',NULL,'','02/FMC-2/4757BF','2017-05-05 10:07:18',NULL,'submitted','','','','','','','','',NULL,'',NULL,NULL,NULL,NULL,'nfsrs_woman','',NULL,'',NULL,2,'asdf','asdf',27),(40,'adsf','',NULL,'','02/FMCS-6\r\n/7FF7A9','2017-05-05 10:07:46',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,1,'asdfa','asdf',5),(41,'asdf','asdf',NULL,'','02/FMCS-5/3B3D21','2017-05-05 10:09:37',NULL,'submitted','adf','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,2,'adf','asdf',23),(42,'','',NULL,'','02/FMC-2/550C06','2017-05-05 10:34:46',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,2,'‡Æ§‡Æ©‡Æö‡Øá‡Æï‡Æ∞‡Æ©‡Øç ','Chennai',27),(43,'','',NULL,'','02/null/3E1C5C','2017-05-07 10:16:14',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(44,'','',NULL,'','02/null/744DDE','2017-05-07 10:23:12',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(45,'','',NULL,'','02/null/F08D06','2017-05-07 10:27:12',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(46,'','',NULL,'','02/null/959FD2','2017-05-07 10:30:10',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(47,'','',NULL,'','02/null/1CA7B5','2017-05-07 10:45:34',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(48,'','',NULL,'','02/null/9DBA2F','2017-05-07 10:46:11',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(49,'','',NULL,'','02/null/8C7FA7','2017-05-07 11:02:07',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(50,'','',NULL,'','02/null/9EBF06','2017-05-07 11:11:01',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL),(51,'','',NULL,'','02/null/B4B6A1','2017-05-07 11:13:47',NULL,'submitted','','','','','','','','','','',NULL,NULL,NULL,NULL,'spl_allowance',NULL,NULL,NULL,NULL,NULL,'','Chennai',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_review_history`
--

LOCK TABLES `form_review_history` WRITE;
/*!40000 ALTER TABLE `form_review_history` DISABLE KEYS */;
INSERT INTO `form_review_history` VALUES (1,'asdfsadfas','rejected',5,13,'2017-05-02 16:06:37'),(2,'','accepted',5,13,'2017-05-02 16:07:31'),(3,'dsDAD','rejected',5,14,'2017-05-02 16:08:37'),(4,'asdfsdaf','rejected',5,14,'2017-05-03 20:51:34'),(5,'asdfasdf','rejected',5,14,'2017-05-04 16:16:12'),(6,'','accepted',5,14,'2017-05-04 16:24:57'),(7,'adsasdf','rejected',5,15,'2017-05-04 18:10:16'),(8,'','accepted',5,25,'2017-05-04 20:10:11'),(9,'asdfasdf','rejected',5,15,'2017-05-05 10:43:02');
/*!40000 ALTER TABLE `form_review_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societies`
--

DROP TABLE IF EXISTS `societies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `societies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `village_id` bigint(20) DEFAULT NULL,
  `for_male` tinyint(1) DEFAULT '0',
  `for_female` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_VILLAGE_SOCIETY` (`village_id`),
  CONSTRAINT `FK_VILLAGE_SOCIETY` FOREIGN KEY (`village_id`) REFERENCES `villages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societies`
--

LOCK TABLES `societies` WRITE;
/*!40000 ALTER TABLE `societies` DISABLE KEYS */;
INSERT INTO `societies` VALUES (1,'\r\nRoyapuram Fishermen Co-operative society , X-264\r\n','X-264',1,1,0),(2,'\r\nBendlemen Garden Fishermen Co-operative society, FMC(M) 38\r\n','\r\nFMC(M) 38\r\n',1,1,0),(3,'\r\nKamaraj Kattumaram Fishermen Co-operative society, FMC-26 \r\n','FMC-26',1,1,0),(4,'\r\nPanaimaraithotti Fishermen Co-operative society, FMC-15\r\n','FMC-15',1,1,0),(5,'\r\nKasithottam Fishermen Co-operative society, FMCS-6\r\n','FMCS-6\r\n',1,1,0),(6,'\r\nKasimedu Parathavar Fishermen Co-operative society, X-288\r\n','X-288',1,1,0),(7,'\r\nThirowpathi Amman Fishermen    Co-operative society, FMC-39 \r\n','FMC-39',1,1,0),(8,'\r\nJeevarathinam Nagar Fishermen Co-operative society, FMC-8 \r\n','FMC-8',1,1,0),(9,'\r\nNaatical & Engineering Fishermen Co-operative society, FMC(M)35\r\n','\r\nFMC(M)35\r\n',1,1,0),(10,'\r\nSigapputthullamanai Fishermen Co-operative society, FMCS-40\r\n','FMCS-40',1,1,0),(11,'\r\nAttapalayam Fishermen Co-operative society, X-393\r\n','X-393',1,1,0),(12,'\r\nAnnai Sathya Fisherwomen Co-operative society, XMW-5\r\n','XMW-5',1,0,1),(13,'\r\nAringnar Anna  Fisherwomen Co-operative society, FWCS-18\r\n','\r\nFWCS-18\r\n',1,0,1),(14,'\r\nIndira Nagar  Fisherwomen Co-operative society, FWCS-34\r\n','\r\nFWCS-34\r\n',1,0,1),(15,'\r\nKasimanagar  Fishermen Co-operative society, X-388 \r\n','X-388',2,1,0),(16,'\r\nSingaravelarkuppam  Fishermen Co-operative society, XM-10 \r\n','XM-10',2,1,0),(17,'\r\nDr.M.G.R Fishermen Co-operative society XC-19\r\n','XC-19',2,1,0),(18,'\r\nTondiarpettai Fishermen Co-operative society, X-407\r\n','X-407',2,1,0),(19,'\r\nM.H.U.Colony  Fishermen Co-operative society, FMC-26\r\n','FMC-26',2,1,0),(20,'\r\nV.O.Chidambaranar  Fishermen Co-operative society, FMCS-46\r\n','FMCS-46',2,1,0),(21,'\r\nNorth Chennai Jeevarathinam  Fishermen Co-operative society, FMC-10\r\n','FMC-10',2,1,0),(22,'\r\nKasipuram  Fishermen Co-operative society, X-236\r\n','X-236',2,1,0),(23,'\r\nV.O.C.Nagar  Fishermen Co-operative society, FMCS-5\r\n','FMCS-5',2,1,0),(24,'\r\nAringnar Anna  Fishermen Co-operative society FMCS-\r\n','FMCS-',2,1,0),(25,'\r\nAshok Nagar  Fishermen Co-operative society, FMC-24\r\n','FMC-24',2,1,0),(26,'\r\nBurma Agathigal Fishermen co-operative Society FMC-17\r\n','\r\nFMC-17\r\n',2,1,0),(27,'Jeevarathinam Fishermen Co-operative society, FMC-2, \r\nKasipuram ‚ÄòA‚Äô Block & ‚ÄòB‚Äô Block Chennai-81','FMC-2',2,1,0),(28,'Poondi Thangammal Colony  Fishermen Co-operative society, \r\nFM(M)-11\r\n','\r\nFM(M)-11\r\n',2,1,0),(29,'Poongavanam Kuppam  Fishermen Co-operative society, FMC(W)-52 ','FMC(W)-52 ',2,1,0),(30,'\r\nAnnai Anjugam  Fisherwomen co op society, FMC(W)48\r\n','FMC(W)48\r\n',2,0,1),(31,'\r\nAnnai Theresa Fisherwomen co op society, FMC(W)49\r\n',' FMC(W)49\r\n',2,0,1),(32,'\r\nJeevarathinam Fisherwomen co op society, FMWC 22\r\n',' FMWC 22',2,0,1),(33,'\r\nAnnai Soniya Gandhi Fisherwomen co op society, FWCS-54\r\n','FWCS-54\r\n',2,0,1),(34,'\r\nOtthavadai Fisherwomen co op society, FWCS-58\r\n','FWCS-58\r\n',2,0,1),(35,'\r\nSingaravelar Nagar Fisherwomen co op society, FWCS-6\r\n','FWCS-6\r\n',2,0,1),(36,'\r\nKasipuram Fisherwomen co op society, FWCS-36\r\n','FWCS-36\r\n',2,0,1),(37,'\r\nNagooran Thottam Fisherwomen co op society, FWCS-53\r\n','FWCS-53\r\n',2,0,1),(38,'\r\nV.O.C.Nagar Fisherwomen co op society, FWCS-56\r\n','FWCS-56\r\n',2,0,1),(39,'Vadachennai  Eraal Meen Virpanai Kooturavu Inayam -Fisherwomen co op society, W-97\r\n','W-97',2,0,1),(40,'\r\nSigapputthullamanai Fisherwomen co op society, XM(W)-14\r\n','XM(W)-14\r\n',2,0,1),(41,'\r\nPoongavanam Kuppam Fisherwomen co op society, FMC(W)-52 \r\n','FMC(W)-52 \r\n',2,0,1),(42,'\r\nArasu Meenava Magalir Fisherwomen co op society, FWCS-57\r\n','FWCS-57\r\n',2,0,1),(43,'\r\nNagooran Thottam  Fishermen Co-operative society, FMC-32(M)\r\n','\r\nFMC-32(M)\r\n',2,1,0);
/*!40000 ALTER TABLE `societies` ENABLE KEYS */;
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
INSERT INTO `users` VALUES (5,'admin','\…iv18)\n†®÷´F#´øw','2017-05-07 10:08:39',1,NULL,NULL,NULL,1),(6,'test','\…iv18)\n†®÷´F#´øw','2017-05-07 10:55:15',0,'testtest',5,5,0),(8,'testtest','≥Ø@õ∏B1á\«^l[h9',NULL,0,'test',5,NULL,0),(9,'dhans','\…iv18)\n†®÷´F#´øw','2017-05-07 10:53:04',0,'dhans',5,NULL,1),(10,'dhans01','\…iv18)\n†®÷´F#´øw',NULL,0,'dhans',5,NULL,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `villages`
--

DROP TABLE IF EXISTS `villages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `villages` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `villages`
--

LOCK TABLES `villages` WRITE;
/*!40000 ALTER TABLE `villages` DISABLE KEYS */;
INSERT INTO `villages` VALUES (1,'Royapuram'),(2,'RK Nagar');
/*!40000 ALTER TABLE `villages` ENABLE KEYS */;
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

-- Dump completed on 2017-05-07 11:28:10
