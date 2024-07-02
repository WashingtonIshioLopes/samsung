-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.37 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para db_samsung
DROP DATABASE IF EXISTS `db_samsung`;
CREATE DATABASE IF NOT EXISTS `db_samsung` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_samsung`;

-- Copiando estrutura para tabela db_samsung.carts
DROP TABLE IF EXISTS `carts`;
CREATE TABLE IF NOT EXISTS `carts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsikdv2229wjn14kyo3dfnnc3u` (`id_user`),
  CONSTRAINT `FKsikdv2229wjn14kyo3dfnnc3u` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.carts: ~1 rows (aproximadamente)
DELETE FROM `carts`;
INSERT INTO `carts` (`id`, `created_at`, `status`, `total`, `updated_at`, `id_user`) VALUES
	(7, '2024-07-01 21:38:37.694714', 'close', 200.00, '2024-07-02 00:13:38.873420', 1),
	(8, '2024-07-02 00:17:21.081715', 'close', 100.00, '2024-07-02 00:18:36.025086', 1),
	(9, '2024-07-02 00:17:21.099725', 'open', 0.00, NULL, 1),
	(10, '2024-07-02 00:17:21.124084', 'open', 0.00, NULL, 1);

-- Copiando estrutura para tabela db_samsung.cart_itens
DROP TABLE IF EXISTS `cart_itens`;
CREATE TABLE IF NOT EXISTS `cart_itens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `quantity` decimal(38,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_cart` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp24esl2ojj532cvu9wlodsdea` (`id_cart`),
  KEY `FKpyox15fuwr8g7fjlt14xnq36b` (`id_product`),
  CONSTRAINT `FKp24esl2ojj532cvu9wlodsdea` FOREIGN KEY (`id_cart`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKpyox15fuwr8g7fjlt14xnq36b` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.cart_itens: ~2 rows (aproximadamente)
DELETE FROM `cart_itens`;
INSERT INTO `cart_itens` (`id`, `created_at`, `quantity`, `status`, `updated_at`, `id_cart`, `id_product`) VALUES
	(1, '2024-07-01 21:38:47.732760', 1.00, NULL, NULL, 7, 1),
	(2, '2024-07-01 21:38:47.732760', 1.00, NULL, NULL, 7, 1),
	(9, '2024-07-02 00:18:02.117820', 1.00, NULL, NULL, 10, 3),
	(10, '2024-07-02 00:18:21.500032', 1.00, NULL, NULL, 8, 3),
	(11, '2024-07-02 00:18:30.249978', 1.00, NULL, NULL, 8, 3);

-- Copiando estrutura para tabela db_samsung.categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.categories: ~2 rows (aproximadamente)
DELETE FROM `categories`;
INSERT INTO `categories` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, 'TV', '2024-06-30 17:53:55.000000', 'Televisao', NULL, NULL),
	(2, 'Celular', NULL, 'Celular', NULL, NULL);

-- Copiando estrutura para tabela db_samsung.checkout
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE IF NOT EXISTS `checkout` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_cart` bigint DEFAULT NULL,
  `id_payment` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jjydryq5mdldd4o6d1tok13fq` (`id_cart`),
  UNIQUE KEY `UK_4fklhyfg33b06mj5wjih8d2uo` (`id_payment`),
  KEY `FKg4d616f4iers3sma2tme6uljn` (`id_user`),
  CONSTRAINT `FK5gr186r83gu0gi6mo48li9y8r` FOREIGN KEY (`id_cart`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKg4d616f4iers3sma2tme6uljn` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`),
  CONSTRAINT `FKsgt1lmyjbyk2iapa9r2wmaawf` FOREIGN KEY (`id_payment`) REFERENCES `payments_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.checkout: ~0 rows (aproximadamente)
DELETE FROM `checkout`;

-- Copiando estrutura para tabela db_samsung.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_checkout` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_titn99eurolkmexlxhpoy3uop` (`id_checkout`),
  KEY `FKl7bmpcqta2pscf7o5713ba3g1` (`id_user`),
  CONSTRAINT `FKdhjqwr8arwy7nd4q8hgbsjohn` FOREIGN KEY (`id_checkout`) REFERENCES `checkout` (`id`),
  CONSTRAINT `FKl7bmpcqta2pscf7o5713ba3g1` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.orders: ~0 rows (aproximadamente)
DELETE FROM `orders`;

-- Copiando estrutura para tabela db_samsung.order_itens
DROP TABLE IF EXISTS `order_itens`;
CREATE TABLE IF NOT EXISTS `order_itens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `quantity` decimal(38,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_order` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmli72jwjnvrm6dbbeymng6687` (`id_order`),
  KEY `FK5keo4om82fll19yf43rgql9tf` (`id_product`),
  CONSTRAINT `FK5keo4om82fll19yf43rgql9tf` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`),
  CONSTRAINT `FKmli72jwjnvrm6dbbeymng6687` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.order_itens: ~0 rows (aproximadamente)
DELETE FROM `order_itens`;

-- Copiando estrutura para tabela db_samsung.payments_types
DROP TABLE IF EXISTS `payments_types`;
CREATE TABLE IF NOT EXISTS `payments_types` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.payments_types: ~4 rows (aproximadamente)
DELETE FROM `payments_types`;
INSERT INTO `payments_types` (`id`, `created_at`, `name`, `status`, `type`, `updated_at`) VALUES
	(1, NULL, 'VISA', NULL, 'Cartao', NULL),
	(2, NULL, 'Mastercard', NULL, 'Cartao', NULL),
	(3, NULL, 'Boleto', NULL, 'Boleto', NULL),
	(4, NULL, 'PIX', NULL, 'PIX', NULL);

-- Copiando estrutura para tabela db_samsung.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL,
  `document` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.person: ~0 rows (aproximadamente)
DELETE FROM `person`;
INSERT INTO `person` (`id`, `document`, `email`, `name`, `password`) VALUES
	(1, '17832088800', 'washington@cis.com.br', 'washington ishio lopes', '$2a$10$yjLsHaJUSXl3BPvN8W/SGeCo/ql1KwKFL2Eb4tsAlLWV6cfgsZKlu');

-- Copiando estrutura para tabela db_samsung.person_role
DROP TABLE IF EXISTS `person_role`;
CREATE TABLE IF NOT EXISTS `person_role` (
  `person_model_id` bigint NOT NULL,
  `role` int DEFAULT NULL,
  KEY `FKtic0rfyaok00gsdrvr5j4x2pb` (`person_model_id`),
  CONSTRAINT `FKtic0rfyaok00gsdrvr5j4x2pb` FOREIGN KEY (`person_model_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.person_role: ~1 rows (aproximadamente)
DELETE FROM `person_role`;
INSERT INTO `person_role` (`person_model_id`, `role`) VALUES
	(1, 1);

-- Copiando estrutura para tabela db_samsung.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `weight` decimal(38,2) DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `id_unit` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKip7b0y8ja7fsm5wl7mhmseh5n` (`id_category`),
  KEY `FKsums02yb5gvsif2quesalfk4t` (`id_unit`),
  CONSTRAINT `FKip7b0y8ja7fsm5wl7mhmseh5n` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKsums02yb5gvsif2quesalfk4t` FOREIGN KEY (`id_unit`) REFERENCES `units` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.products: ~5 rows (aproximadamente)
DELETE FROM `products`;
INSERT INTO `products` (`id`, `created_at`, `description`, `name`, `price`, `status`, `updated_at`, `weight`, `id_category`, `id_unit`) VALUES
	(1, NULL, 'Celular Samsung', 'Celular', 100.00, NULL, NULL, 1.00, 2, 1),
	(2, NULL, 'TV Samsung', 'TV', 100.00, NULL, NULL, 10.00, 1, 1),
	(3, NULL, 'Tablet Samsung', 'Tablet', 50.00, NULL, NULL, 5.00, 2, 1),
	(4, NULL, 'Geladeira Samsung', 'Geladeira', 200.00, NULL, NULL, 4.00, 1, 1),
	(5, NULL, 'Fogao Samsung', 'Fogao', 300.00, NULL, NULL, 3.00, 1, 1);

-- Copiando estrutura para tabela db_samsung.product_images
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE IF NOT EXISTS `product_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpbcjehp1361qtyyilvx87d26d` (`id_product`),
  CONSTRAINT `FKpbcjehp1361qtyyilvx87d26d` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.product_images: ~9 rows (aproximadamente)
DELETE FROM `product_images`;
INSERT INTO `product_images` (`id`, `created_at`, `image`, `status`, `updated_at`, `id_product`) VALUES
	(1, NULL, 'https://mdbootstrap.com/img/new/slides/041.jpg', NULL, NULL, 1),
	(2, NULL, 'https://mdbootstrap.com/img/new/slides/042.jpg', NULL, NULL, 2),
	(3, NULL, 'https://mdbootstrap.com/img/new/slides/043.jpg', NULL, NULL, 3),
	(4, NULL, 'https://mdbootstrap.com/img/new/slides/043.jpg', NULL, NULL, 4),
	(5, NULL, 'https://mdbootstrap.com/img/new/slides/043.jpg', NULL, NULL, 5),
	(6, NULL, 'https://mdbootstrap.com/img/new/slides/042.jpg', NULL, NULL, 1),
	(7, NULL, 'https://mdbootstrap.com/img/new/slides/043.jpg', NULL, NULL, 1),
	(8, NULL, 'https://mdbootstrap.com/img/new/slides/041.jpg', NULL, NULL, 2),
	(9, NULL, 'https://mdbootstrap.com/img/new/slides/043.jpg', NULL, NULL, 2),
	(10, NULL, 'https://mdbootstrap.com/img/new/slides/042.jpg', NULL, NULL, 3),
	(11, NULL, 'https://mdbootstrap.com/img/new/slides/041.jpg', NULL, NULL, 3);

-- Copiando estrutura para tabela db_samsung.seq_person_id
DROP TABLE IF EXISTS `seq_person_id`;
CREATE TABLE IF NOT EXISTS `seq_person_id` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.seq_person_id: ~1 rows (aproximadamente)
DELETE FROM `seq_person_id`;
INSERT INTO `seq_person_id` (`next_val`) VALUES
	(2);

-- Copiando estrutura para tabela db_samsung.units
DROP TABLE IF EXISTS `units`;
CREATE TABLE IF NOT EXISTS `units` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.units: ~0 rows (aproximadamente)
DELETE FROM `units`;
INSERT INTO `units` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, 'Geral', '2024-06-30 17:54:46.000000', 'Geral', NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
