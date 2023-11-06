-- MySQL dump 10.13  Distrib 8.1.0, for macos13.3 (arm64)
--
-- Host: localhost    Database: OrdersystemDb
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS OrdersystemDB;

-- Switch to the newly created database
USE OrdersystemDB;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Category`
(
    `CategoryId`   int         NOT NULL AUTO_INCREMENT,
    `CategoryName` varchar(50) NOT NULL,
    PRIMARY KEY (`CategoryId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category`
    DISABLE KEYS */;
INSERT INTO `Category`
VALUES (1, 'Smartphones'),
       (2, 'Clothing'),
       (3, 'Home and Garden');
/*!40000 ALTER TABLE `Category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer`
(
    `CustomerId`         int         NOT NULL AUTO_INCREMENT,
    `CustomerArea`       varchar(100)   DEFAULT NULL,
    `CustomerStreet`     varchar(100)   DEFAULT NULL,
    `CustomerCity`       varchar(100)   DEFAULT NULL,
    `CustomerPostalCode` varchar(10)    DEFAULT NULL,
    `CustomerDOB`        date           DEFAULT NULL,
    `CustomerCellPhone`  varchar(21) NOT NULL,
    `CustomerEmail`      varchar(30)    DEFAULT NULL unique,
    `WalletBalance`      decimal(15, 2) DEFAULT 0.00,
    `CustomerFirstName`  varchar(30)    DEFAULT NULL,
    `CustomerLastName`   varchar(30)    DEFAULT NULL,
    PRIMARY KEY (`CustomerId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer`
    DISABLE KEYS */;
INSERT INTO `Customer`
VALUES (1, 'Sample Area', '123 Main St', 'Sample City', '12345', '1990-01-15', '123-456-7890', 'john@example.com',
        1000.00, 'John', 'Doe'),
       (2, 'Another Area', '456 Elm St', 'Another City', '54321', '1985-07-20', '987-654-3210', 'jane@example.com',
        800.00, 'Jane', 'Smith'),
       (3, 'Sample Area', '123 Main St', 'Sample City', '12345', '1990-01-15', '0769852592', 'mark@example.com',
        2221.00, 'Mark', 'Doe');
/*!40000 ALTER TABLE `Customer`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Product`
(
    `ProductId`  int NOT NULL AUTO_INCREMENT,
    `Name`       varchar(255)   DEFAULT NULL,
    `Thumbnail`  varbinary(255) DEFAULT NULL,
    `CategoryID` int            DEFAULT NULL,
    `Price`      decimal(38, 2) DEFAULT NULL,
    `Details`    varchar(255)   DEFAULT NULL,
    `Quantity`   int NOT NULL,
    PRIMARY KEY (`ProductId`),
    KEY `CategoryID` (`CategoryID`),
    CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `Category` (`CategoryId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product`
    DISABLE KEYS */;
INSERT INTO `Product`
VALUES (1, 'iPhone 13', _binary 'base64_encoded_image_data1', 1, 999.99, 'Advanced smartphone.', 45),
       (2, 'Samsung Galaxy S21', _binary 'base64_encoded_image_data2', 1, 899.99, 'High-quality smartphone.', 60),
       (3, 'Smart TV', _binary 'base64_encoded_image_data3', 3, 799.99, '55-inch 4K Smart TV.', 25),
       (4, 'Huawei', _binary '�PNG\r\n\���~\�pz�\0B\0\0', 1, 2000.99, 'Zoom to the moon camera.', 12);
/*!40000 ALTER TABLE `Product`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders`
(
    `OrderId`       int          NOT NULL AUTO_INCREMENT,
    `OrderNumber`   varchar(255)   DEFAULT NULL,
    `ProductId`     int            DEFAULT NULL,
    `Thumbnail`     varbinary(255) DEFAULT NULL,
    `Quantity`      int          NOT NULL,
    `CustomerId`    int            DEFAULT NULL,
    `OrderDate`     datetime(6)    DEFAULT NULL,
    `OrderDelivery` datetime(6)    DEFAULT NULL,
    `Status`        varchar(255) NOT NULL,
    PRIMARY KEY (`OrderId`),
    UNIQUE KEY `OderNumber` (`OrderNumber`),
    KEY `ProductId` (`ProductId`),
    KEY `CustomerId` (`CustomerId`),
    CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`ProductId`) REFERENCES `Product` (`ProductId`),
    CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`CustomerId`) REFERENCES `Customer` (`CustomerId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders`
    DISABLE KEYS */;
INSERT INTO `Orders`
VALUES (1, NULL, 1, _binary 'base64_encoded_image_data4', 2, 1, '2023-09-22 00:00:00.000000',
        '2023-09-27 00:00:00.000000', 'Processing'),
       (2, NULL, 2, _binary 'base64_encoded_image_data5', 3, 2, '2023-09-23 00:00:00.000000',
        '2023-09-28 00:00:00.000000', 'Shipped'),
       (3, NULL, 3, _binary 'base64_encoded_image_data6', 1, 3, '2023-09-25 00:00:00.000000',
        '2023-09-30 00:00:00.000000', 'Delivered'),
       (5, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, NULL, NULL, 'Pending'),
       (6, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, NULL, NULL, 'Pending'),
       (7, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, NULL, NULL, 'Pending'),
       (8, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, NULL, NULL, 'Pending'),
       (13, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (14, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (15, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (16, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (17, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (18, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-09-20 02:00:00.000000',
        '2023-09-25 02:00:00.000000', 'Pending'),
       (19, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (20, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (21, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (22, NULL, 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (23, '202310040001WhT', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (24, '202310040002dyS', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (25, '202310040003mAV', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (26, '202310040004LS0', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (27, '3aff88bc-6ecd-4892-ad23-878d9fe95448', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1,
        '2023-10-04 02:00:00.000000', '2023-10-04 02:00:00.000000', 'Pending'),
       (31, '202310040000MSg', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (32, '202310040001Fnk', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (33, '202310040002qaZ', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (34, '202310040003A9X', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (35, '9a401c26-ad3f-4bbf-b7c9-5ae310eb2d38-3345717826259281944', 1,
        _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000', '2023-10-04 02:00:00.000000',
        'Pending'),
       (36, '229244d4-604d-4586-b601-3d04674bb4ac--5268059295476199112', 1,
        _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000', '2023-10-04 02:00:00.000000',
        'Pending'),
       (37, '20231005104528-0407', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (39, '20231005104946-6248', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (40, '20231005105040-6248', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (41, '20231005105055-6248', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (42, '20231005105100-6248', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (43, '20231005105108-6248', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (44, '20231005105841-6876', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (45, '20231005105850-6328', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (46, '20231005105854-6726', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending'),
       (47, '20231005162754-9037', 1, _binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0B\0\0', 1, 1, '2023-10-04 02:00:00.000000',
        '2023-10-04 02:00:00.000000', 'Pending');
/*!40000 ALTER TABLE `Orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Inventory`
(
    `InventoryId` int NOT NULL AUTO_INCREMENT,
    `Quantity`    int          DEFAULT NULL,
    `Location`    varchar(255) DEFAULT NULL,
    `ProductId`   int          DEFAULT NULL,
    PRIMARY KEY (`InventoryId`),
    KEY `OrderId` (`ProductId`),
    CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`ProductId`) REFERENCES `Orders` (`OrderId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory`
    DISABLE KEYS */;
INSERT INTO `Inventory`
VALUES (1, 10, 'Cape Town', 1);
/*!40000 ALTER TABLE `Inventory`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Review`
--

DROP TABLE IF EXISTS `Review`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Review`
(
    `ReviewId`          int          NOT NULL AUTO_INCREMENT,
    `ProductId`         int DEFAULT NULL,
    `CustomerId`        int DEFAULT NULL,
    `ReviewDescription` varchar(255) NOT NULL,
    `ReviewStars`       int DEFAULT 0,
    PRIMARY KEY (`ReviewId`),
    KEY `ProductId` (`ProductId`),
    KEY `CustomerId` (`CustomerId`),
    CONSTRAINT `review_ibfk_1` FOREIGN KEY (`ProductId`) REFERENCES `Product` (`ProductId`),
    CONSTRAINT `review_ibfk_2` FOREIGN KEY (`CustomerId`) REFERENCES `Customer` (`CustomerId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Review`
--

LOCK TABLES `Review` WRITE;
/*!40000 ALTER TABLE `Review`
    DISABLE KEYS */;
INSERT INTO `Review`
VALUES (1, 1, 1, 'This is a great product, I bought it last week and I am pleased!', 0),
       (3, 2, 2, 'I like th texture', 5);
/*!40000 ALTER TABLE `Review`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-10-09 21:39:02
