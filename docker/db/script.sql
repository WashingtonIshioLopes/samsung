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
CREATE DATABASE IF NOT EXISTS `db_samsung` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_samsung`;

-- Copiando estrutura para tabela db_samsung.carts
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.carts: ~0 rows (aproximadamente)
DELETE FROM `carts`;

-- Copiando estrutura para tabela db_samsung.cart_itens
CREATE TABLE IF NOT EXISTS `cart_itens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `quantity` decimal(38,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7l7en5erqjt069mk9u06hopo7` (`id_user`),
  KEY `FKpyox15fuwr8g7fjlt14xnq36b` (`id_product`),
  CONSTRAINT `FK7l7en5erqjt069mk9u06hopo7` FOREIGN KEY (`id_user`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKpyox15fuwr8g7fjlt14xnq36b` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.cart_itens: ~0 rows (aproximadamente)
DELETE FROM `cart_itens`;

-- Copiando estrutura para tabela db_samsung.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.categories: ~8 rows (aproximadamente)
DELETE FROM `categories`;
INSERT INTO `categories` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, '1000', NULL, 'Smartphones', NULL, NULL),
	(2, '2000', NULL, 'Tablets', NULL, NULL),
	(3, '3000', NULL, 'Notebooks', NULL, NULL),
	(4, '4000', NULL, 'TV & AV', NULL, NULL),
	(5, '5000', NULL, 'Ar-condicionado', NULL, NULL),
	(6, '6000', NULL, 'Geladeiras', NULL, NULL),
	(7, '7000', NULL, 'Lavanderia', NULL, NULL),
	(8, '8000', NULL, 'Cozinha', NULL, NULL);

-- Copiando estrutura para tabela db_samsung.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl7bmpcqta2pscf7o5713ba3g1` (`id_user`),
  CONSTRAINT `FKl7bmpcqta2pscf7o5713ba3g1` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.orders: ~0 rows (aproximadamente)
DELETE FROM `orders`;

-- Copiando estrutura para tabela db_samsung.order_itens
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.order_itens: ~0 rows (aproximadamente)
DELETE FROM `order_itens`;

-- Copiando estrutura para tabela db_samsung.payments_types
CREATE TABLE IF NOT EXISTS `payments_types` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.payments_types: ~5 rows (aproximadamente)
DELETE FROM `payments_types`;
INSERT INTO `payments_types` (`id`, `created_at`, `name`, `status`, `type`, `updated_at`) VALUES
	(1, NULL, 'Mastercard', NULL, 'Cartão', NULL),
	(2, NULL, 'Visa', NULL, 'Cartão', NULL),
	(3, NULL, 'Boleto', NULL, 'Boleto', NULL),
	(4, NULL, 'PIX', NULL, 'PIX', NULL),
	(5, NULL, 'Dinners', NULL, 'Cartão', NULL);

-- Copiando estrutura para tabela db_samsung.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL,
  `document` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwmwi44u55bo4rvwsv0cln012` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.person: ~1 rows (aproximadamente)
DELETE FROM `person`;
INSERT INTO `person` (`id`, `document`, `email`, `name`, `password`) VALUES
	(3, '17832088800', 'washington@cis.com.br', 'washington', '$2a$10$azMswskbL8ytB1La2xzN9ucPOD6b5eZznk79hKWP2aHN7GfXU7ocq');

-- Copiando estrutura para tabela db_samsung.person_role
CREATE TABLE IF NOT EXISTS `person_role` (
  `person_model_id` bigint NOT NULL,
  `role` int DEFAULT NULL,
  KEY `FKtic0rfyaok00gsdrvr5j4x2pb` (`person_model_id`),
  CONSTRAINT `FKtic0rfyaok00gsdrvr5j4x2pb` FOREIGN KEY (`person_model_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.person_role: ~1 rows (aproximadamente)
DELETE FROM `person_role`;
INSERT INTO `person_role` (`person_model_id`, `role`) VALUES
	(3, 1);

-- Copiando estrutura para tabela db_samsung.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.products: ~2 rows (aproximadamente)
DELETE FROM `products`;
INSERT INTO `products` (`id`, `created_at`, `description`, `price`, `status`, `updated_at`, `weight`, `id_category`, `id_unit`) VALUES
	(1, '2024-06-28 13:03:00.131994', 'keli', 1.00, NULL, '2024-06-28 13:10:48.179783', 1.00, 1, 1),
	(3, '2024-06-28 13:05:13.659801', 'televisao', 1.00, NULL, NULL, 1.00, 1, 1);

-- Copiando estrutura para tabela db_samsung.product_images
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.product_images: ~0 rows (aproximadamente)
DELETE FROM `product_images`;

-- Copiando estrutura para tabela db_samsung.seq_person_id
CREATE TABLE IF NOT EXISTS `seq_person_id` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.seq_person_id: ~1 rows (aproximadamente)
DELETE FROM `seq_person_id`;
INSERT INTO `seq_person_id` (`next_val`) VALUES
	(4);

-- Copiando estrutura para tabela db_samsung.units
CREATE TABLE IF NOT EXISTS `units` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela db_samsung.units: ~3 rows (aproximadamente)
DELETE FROM `units`;
INSERT INTO `units` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, 'U', NULL, 'Unidade', NULL, NULL),
	(2, 'C', NULL, 'Centena', NULL, NULL),
	(3, 'M', NULL, 'Milhar', NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
