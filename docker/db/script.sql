-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.37 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.6.0.6765
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
CREATE DATABASE IF NOT EXISTS `db_samsung` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_samsung`;

-- Copiando estrutura para tabela db_samsung.tb_cartitens
DROP TABLE IF EXISTS `tb_cartitens`;
CREATE TABLE IF NOT EXISTS `tb_cartitens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` decimal(38,2) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnssfd8kxd6o3wp6aregmnlu7y` (`id_user`),
  KEY `FKjg2kjowcje5bqotlkx5npstrx` (`id_product`),
  CONSTRAINT `FKjg2kjowcje5bqotlkx5npstrx` FOREIGN KEY (`id_product`) REFERENCES `tb_products` (`id`),
  CONSTRAINT `FKnssfd8kxd6o3wp6aregmnlu7y` FOREIGN KEY (`id_user`) REFERENCES `tb_carts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_cartitens: ~0 rows (aproximadamente)
DELETE FROM `tb_cartitens`;

-- Copiando estrutura para tabela db_samsung.tb_carts
DROP TABLE IF EXISTS `tb_carts`;
CREATE TABLE IF NOT EXISTS `tb_carts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total` decimal(38,2) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7lb6lm179b7tcuhvvxdh9kn66` (`id_user`),
  CONSTRAINT `FK7lb6lm179b7tcuhvvxdh9kn66` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_carts: ~0 rows (aproximadamente)
DELETE FROM `tb_carts`;

-- Copiando estrutura para tabela db_samsung.tb_categories
DROP TABLE IF EXISTS `tb_categories`;
CREATE TABLE IF NOT EXISTS `tb_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_categories: ~0 rows (aproximadamente)
DELETE FROM `tb_categories`;
INSERT INTO `tb_categories` (`id`, `code`, `description`, `status`, `updated_at`, `created_at`) VALUES
	(1, 'TV', 'TV', NULL, NULL, NULL);

-- Copiando estrutura para tabela db_samsung.tb_orderitens
DROP TABLE IF EXISTS `tb_orderitens`;
CREATE TABLE IF NOT EXISTS `tb_orderitens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` decimal(38,2) DEFAULT NULL,
  `id_order` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8c3w0awhq3trwf9yw0mrgwdo8` (`id_order`),
  KEY `FK59d06abmvk5g2dgkpgtxtms55` (`id_product`),
  CONSTRAINT `FK59d06abmvk5g2dgkpgtxtms55` FOREIGN KEY (`id_product`) REFERENCES `tb_products` (`id`),
  CONSTRAINT `FK8c3w0awhq3trwf9yw0mrgwdo8` FOREIGN KEY (`id_order`) REFERENCES `tb_orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_orderitens: ~0 rows (aproximadamente)
DELETE FROM `tb_orderitens`;

-- Copiando estrutura para tabela db_samsung.tb_orders
DROP TABLE IF EXISTS `tb_orders`;
CREATE TABLE IF NOT EXISTS `tb_orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `total` decimal(38,2) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosfwkasuoed3piriay127a0w8` (`id_user`),
  CONSTRAINT `FKosfwkasuoed3piriay127a0w8` FOREIGN KEY (`id_user`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_orders: ~0 rows (aproximadamente)
DELETE FROM `tb_orders`;

-- Copiando estrutura para tabela db_samsung.tb_payments
DROP TABLE IF EXISTS `tb_payments`;
CREATE TABLE IF NOT EXISTS `tb_payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_payments: ~0 rows (aproximadamente)
DELETE FROM `tb_payments`;

-- Copiando estrutura para tabela db_samsung.tb_productimages
DROP TABLE IF EXISTS `tb_productimages`;
CREATE TABLE IF NOT EXISTS `tb_productimages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK99eieq52byvrf2qfwif6vsng0` (`id_product`),
  CONSTRAINT `FK99eieq52byvrf2qfwif6vsng0` FOREIGN KEY (`id_product`) REFERENCES `tb_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_productimages: ~0 rows (aproximadamente)
DELETE FROM `tb_productimages`;
INSERT INTO `tb_productimages` (`id`, `image`, `id_product`, `product_id`, `status`, `updated_at`, `created_at`) VALUES
	(1, 'http://example.com/keli1.jpg', 1, 0, NULL, NULL, NULL),
	(2, 'http://example.com/keli2.jpg', 1, 0, NULL, NULL, NULL);

-- Copiando estrutura para tabela db_samsung.tb_products
DROP TABLE IF EXISTS `tb_products`;
CREATE TABLE IF NOT EXISTS `tb_products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `weight` decimal(38,2) DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `id_unit` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK30e84crs97xxvrxy5ua1ydaul` (`id_category`),
  KEY `FKhy69117ww815ey922cbyef52o` (`id_unit`),
  CONSTRAINT `FK30e84crs97xxvrxy5ua1ydaul` FOREIGN KEY (`id_category`) REFERENCES `tb_categories` (`id`),
  CONSTRAINT `FKhy69117ww815ey922cbyef52o` FOREIGN KEY (`id_unit`) REFERENCES `tb_units` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_products: ~0 rows (aproximadamente)
DELETE FROM `tb_products`;
INSERT INTO `tb_products` (`id`, `description`, `price`, `weight`, `id_category`, `id_unit`, `status`, `updated_at`, `created_at`) VALUES
	(1, 'keli', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(3, 'name', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(4, 'name', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(5, 'name', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(6, 'teste', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(7, 'teste', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(8, 'temp', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(9, 'temp', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(13, 'renata', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(14, 'renata', 1.00, 1.00, 1, 1, NULL, NULL, NULL),
	(15, 'renata', 1.00, 1.00, 1, 1, NULL, NULL, NULL);

-- Copiando estrutura para tabela db_samsung.tb_units
DROP TABLE IF EXISTS `tb_units`;
CREATE TABLE IF NOT EXISTS `tb_units` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_units: ~0 rows (aproximadamente)
DELETE FROM `tb_units`;
INSERT INTO `tb_units` (`id`, `code`, `description`, `status`, `updated_at`, `created_at`) VALUES
	(1, 'U', 'Unidade', NULL, NULL, NULL);

-- Copiando estrutura para tabela db_samsung.tb_users
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE IF NOT EXISTS `tb_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.tb_users: ~0 rows (aproximadamente)
DELETE FROM `tb_users`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
