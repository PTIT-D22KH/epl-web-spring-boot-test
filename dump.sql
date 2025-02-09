-- MySQL dump 10.13  Distrib 9.2.0, for Linux (x86_64)
--
-- Host: localhost    Database: epl
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `club_coach`
--

DROP TABLE IF EXISTS `club_coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_coach` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `club_id` bigint DEFAULT NULL,
  `coach_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrcmjdnkkfy5u51r3scpbgfak` (`club_id`),
  KEY `FKd1s2kspvb3gxpbygi4u5at7yo` (`coach_id`),
  CONSTRAINT `FKd1s2kspvb3gxpbygi4u5at7yo` FOREIGN KEY (`coach_id`) REFERENCES `coaches` (`id`),
  CONSTRAINT `FKqrcmjdnkkfy5u51r3scpbgfak` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_coach`
--

LOCK TABLES `club_coach` WRITE;
/*!40000 ALTER TABLE `club_coach` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_player`
--

DROP TABLE IF EXISTS `club_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_player` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `club_id` bigint DEFAULT NULL,
  `player_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKid8en636t327e99m539ign0db` (`club_id`),
  KEY `FKhwri12awb70wik42a1157msb2` (`player_id`),
  CONSTRAINT `FKhwri12awb70wik42a1157msb2` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`),
  CONSTRAINT `FKid8en636t327e99m539ign0db` FOREIGN KEY (`club_id`) REFERENCES `clubs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_player`
--

LOCK TABLES `club_player` WRITE;
/*!40000 ALTER TABLE `club_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `club_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubs`
--

DROP TABLE IF EXISTS `clubs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubs`
--

LOCK TABLES `clubs` WRITE;
/*!40000 ALTER TABLE `clubs` DISABLE KEYS */;
/*!40000 ALTER TABLE `clubs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach_club_periods`
--

DROP TABLE IF EXISTS `coach_club_periods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach_club_periods` (
  `club_coach_id` bigint NOT NULL,
  `detail` enum('LOAN_CLUB','MAIN_CLUB') DEFAULT NULL,
  `end_date` time(6) DEFAULT NULL,
  `start_date` time(6) DEFAULT NULL,
  KEY `FK7hdqqlm3mu23vap6acr06if9o` (`club_coach_id`),
  CONSTRAINT `FK7hdqqlm3mu23vap6acr06if9o` FOREIGN KEY (`club_coach_id`) REFERENCES `club_coach` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach_club_periods`
--

LOCK TABLES `coach_club_periods` WRITE;
/*!40000 ALTER TABLE `coach_club_periods` DISABLE KEYS */;
/*!40000 ALTER TABLE `coach_club_periods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coaches`
--

DROP TABLE IF EXISTS `coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coaches` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coaches`
--

LOCK TABLES `coaches` WRITE;
/*!40000 ALTER TABLE `coaches` DISABLE KEYS */;
/*!40000 ALTER TABLE `coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_club_periods`
--

DROP TABLE IF EXISTS `player_club_periods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_club_periods` (
  `club_player_id` bigint NOT NULL,
  `detail` enum('LOAN_CLUB','MAIN_CLUB') DEFAULT NULL,
  `end_date` time(6) DEFAULT NULL,
  `start_date` time(6) DEFAULT NULL,
  KEY `FK8ti8flgv5ltcvnp96qf6j60kl` (`club_player_id`),
  CONSTRAINT `FK8ti8flgv5ltcvnp96qf6j60kl` FOREIGN KEY (`club_player_id`) REFERENCES `club_player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_club_periods`
--

LOCK TABLES `player_club_periods` WRITE;
/*!40000 ALTER TABLE `player_club_periods` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_club_periods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_position`
--

DROP TABLE IF EXISTS `player_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `player_id` bigint DEFAULT NULL,
  `position_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg5r9b5rky6f6lp19or0kps066` (`player_id`),
  KEY `FK4gfmwr87qkqpl6tmpmwcvbyrc` (`position_id`),
  CONSTRAINT `FK4gfmwr87qkqpl6tmpmwcvbyrc` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`),
  CONSTRAINT `FKg5r9b5rky6f6lp19or0kps066` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_position`
--

LOCK TABLES `player_position` WRITE;
/*!40000 ALTER TABLE `player_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `positions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `name` enum('AM','CB','CM','DM','GK','LB','LM','LW','LWB','RB','RM','RW','RWB','SS','ST') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `refreshtoken` mediumtext,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'HN',20,'2025-02-08 16:36:51.230584','anonymousUser','user@gmail.com','MALE','Duong Vu','VN','$2a$10$llrntOX1ZAowsMohOE2cMeKssEKk4hIU3xc7ZbuIRVh8UoF0Bkigy','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGdtYWlsLmNvbSIsImV4cCI6MTc0NzY3MzA0NywiaWF0IjoxNzM5MDMzMDQ3LCJ1c2VyIjp7ImlkIjoxLCJlbWFpbCI6InVzZXJAZ21haWwuY29tIiwibmFtZSI6IkhOIn19.mYMRtsZgQG_HVqPbGa9xeM7apfAEYOOBfUavR4sS0fiO1U0SbvNOfWBB8uijbhIFM-1_DoYVrMWWx9xNpf1sYA','2025-02-08 16:44:07.743699','user@gmail.com'),(2,'HN',20,'2025-02-08 16:38:57.952682','user@gmail.com','duong@gmail.com','MALE','Duong Vu','VN','$2a$10$zXgSGwcrJcwKrsi/OWD6WeZtaChusBiGmu65u35NYu5G.xSBBff3O','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW9uZ0BnbWFpbC5jb20iLCJleHAiOjE3NDc2NzMwMzMsImlhdCI6MTczOTAzMzAzMywidXNlciI6eyJpZCI6MiwiZW1haWwiOiJkdW9uZ0BnbWFpbC5jb20iLCJuYW1lIjoiSE4ifX0.xdNAzQS5AlL5Cz3u95pJaoGs7CSPjLKFir8HcX37Evd9Ppil5z4z_BKtzha9QWz6Q3frrh2CX2e-ajVjE_F0Ug','2025-02-08 16:43:53.043978','duong@gmail.com'),(3,'HN',20,'2025-02-08 16:40:14.509386','user@gmail.com','duong1@gmail.com','MALE','Duong Vu','VN','$2a$10$lmE6EbS6Fl6bMdIEmFbHr.Hdzk8SQqQTU8nXB50fWSdNydHF85Bbm','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdW9uZzFAZ21haWwuY29tIiwiZXhwIjoxNzQ3NjczMDIyLCJpYXQiOjE3MzkwMzMwMjIsInVzZXIiOnsiaWQiOjMsImVtYWlsIjoiZHVvbmcxQGdtYWlsLmNvbSIsIm5hbWUiOiJITiJ9fQ.TWAYtv40V-NbuziOTf9eVggvh_hCO_LWVcuAbqT47MHFENbWfwXsIkkeFzKUe_y7zLqvtsKqP2nVqDITJl8_WQ','2025-02-08 16:43:42.677272','duong1@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-09 14:52:48
