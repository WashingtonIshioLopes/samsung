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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.carts: ~3 rows (aproximadamente)
DELETE FROM `carts`;
INSERT INTO `carts` (`id`, `created_at`, `status`, `total`, `updated_at`, `id_user`) VALUES
	(1, '2024-07-02 15:40:00.412736', 'open', 0.00, NULL, 1),
	(2, '2024-07-02 15:40:00.412736', 'open', 0.00, NULL, 1),
	(3, '2024-07-02 15:40:00.412736', 'open', 0.00, NULL, 1);

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
  UNIQUE KEY `UK_sagcfg5y99qwv8yv3klftpv66` (`id_product`),
  KEY `FKp24esl2ojj532cvu9wlodsdea` (`id_cart`),
  CONSTRAINT `FKp24esl2ojj532cvu9wlodsdea` FOREIGN KEY (`id_cart`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKpyox15fuwr8g7fjlt14xnq36b` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.cart_itens: ~0 rows (aproximadamente)
DELETE FROM `cart_itens`;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.categories: ~8 rows (aproximadamente)
DELETE FROM `categories`;
INSERT INTO `categories` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, 'Smartphone', '2024-06-30 17:53:55.000000', 'Smartphone', NULL, NULL),
	(2, 'Tablet', NULL, 'Tablet', NULL, NULL),
	(3, 'Notebook', NULL, 'Notebook', NULL, NULL),
	(4, 'TV & AV', NULL, 'TV & AV', NULL, NULL),
	(5, 'Ar Condicionado', NULL, 'Ar Condicionado', NULL, NULL),
	(6, 'Geladeira', NULL, 'Geladeira', NULL, NULL),
	(7, 'Lavanderia', NULL, 'Lavanderia', NULL, NULL),
	(8, 'Cozinha', NULL, 'Cozinha', NULL, NULL);

-- Copiando estrutura para tabela db_samsung.checkout
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE IF NOT EXISTS `checkout` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id_cart` bigint DEFAULT NULL,
  `id_payment` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.checkout: ~0 rows (aproximadamente)
DELETE FROM `checkout`;

-- Copiando estrutura para tabela db_samsung.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `id_checkout` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
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

-- Copiando dados para a tabela db_samsung.person: ~1 rows (aproximadamente)
DELETE FROM `person`;
INSERT INTO `person` (`id`, `document`, `email`, `name`, `password`) VALUES
	(1, '17832088800', 'washington@cis.com.br', 'washington ishio lopes', '$2a$10$yjLsHaJUSXl3BPvN8W/SGeCo/ql1KwKFL2Eb4tsAlLWV6cfgsZKlu');

-- Copiando estrutura para tabela db_samsung.person_address
DROP TABLE IF EXISTS `person_address`;
CREATE TABLE IF NOT EXISTS `person_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1pxiryjx6u5n17cp3s1fg1mvh` (`id_user`),
  CONSTRAINT `FK1pxiryjx6u5n17cp3s1fg1mvh` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.person_address: ~0 rows (aproximadamente)
DELETE FROM `person_address`;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.products: ~7 rows (aproximadamente)
DELETE FROM `products`;
INSERT INTO `products` (`id`, `created_at`, `description`, `name`, `price`, `status`, `updated_at`, `weight`, `id_category`, `id_unit`) VALUES
	(1, NULL, 'Bespoke Design: Flat Design compacto que se ajusta em qualquer lugar', 'Lava e Seca Bespoke WD14B 14kg Black 127V', 7999.00, NULL, NULL, 1.00, 7, 1),
	(2, NULL, 'Lava 13kg e seca 7kg: maior capacidade para lavar suas roupas', 'Lava e Seca WD13T Smart 13kg', 4499.00, NULL, NULL, 10.00, 7, 1),
	(3, NULL, 'Forno elétrico de Porta Dupla com Função Air Fry e Wi-Fi 76L', 'Forno elétrico de Porta Dupla com Função Air Fry e Wi-Fi 76L', 9999.00, NULL, NULL, 5.00, 8, 1),
	(4, NULL, 'Cooktop Inox 5 Queimadores com Wi-Fi e Dupla Chama', 'Cooktop Inox 5 Queimadores com Wi-Fi e Dupla Chama', 9999.00, NULL, NULL, 4.00, 8, 1),
	(5, NULL, 'Smart TV 50" QLED 4K 50Q60D 2024', 'Smart TV 50" QLED 4K 50Q60D 2024', 2697.00, NULL, NULL, 3.00, 4, 1),
	(6, NULL, 'Robô Aspirador de Pó VR3M', 'Robô Aspirador de Pó VR3M', 3299.00, NULL, NULL, 1.00, 7, 1),
	(7, NULL, 'Celular Samsung Galaxy S24', 'Celular Samsung Galaxy S24', 5399.00, NULL, NULL, 1.00, 1, 1);

-- Copiando estrutura para tabela db_samsung.product_favorite
DROP TABLE IF EXISTS `product_favorite`;
CREATE TABLE IF NOT EXISTS `product_favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcsvoekxnrokmrss3wo0rlq015` (`id_product`),
  KEY `FKhqt3o8mvv8567i2piw9jua5xo` (`id_user`),
  CONSTRAINT `FKcsvoekxnrokmrss3wo0rlq015` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`),
  CONSTRAINT `FKhqt3o8mvv8567i2piw9jua5xo` FOREIGN KEY (`id_user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.product_favorite: ~0 rows (aproximadamente)
DELETE FROM `product_favorite`;

-- Copiando estrutura para tabela db_samsung.product_featured
DROP TABLE IF EXISTS `product_featured`;
CREATE TABLE IF NOT EXISTS `product_featured` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mhgvxfl1auekdqlmf12bxikbp` (`id_product`),
  CONSTRAINT `FKaoy06b2uc0xhnhtsgh3vkryqb` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.product_featured: ~7 rows (aproximadamente)
DELETE FROM `product_featured`;
INSERT INTO `product_featured` (`id`, `created_at`, `status`, `updated_at`, `id_product`) VALUES
	(1, NULL, NULL, NULL, 1),
	(2, NULL, NULL, NULL, 2),
	(3, NULL, NULL, NULL, 3),
	(4, NULL, NULL, NULL, 4),
	(5, NULL, NULL, NULL, 5),
	(6, NULL, NULL, NULL, 6),
	(7, NULL, NULL, NULL, 7);

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela db_samsung.product_images: ~24 rows (aproximadamente)
DELETE FROM `product_images`;
INSERT INTO `product_images` (`id`, `created_at`, `image`, `status`, `updated_at`, `id_product`) VALUES
	(1, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/wd14bb944dgbaz/gallery/br-wd9400b-wd14bb944dgbaz-540261312?$2052_1641_PNG$', NULL, NULL, 1),
	(2, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/wd13t704dbh-az/gallery/br-combo-wd10t704dbxtl-wd13t704dbh-az-540261512?$2052_1641_PNG$', NULL, NULL, 2),
	(3, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/nv7b4545sak-bz/gallery/br-nv7000b-nv7b4550vab-nv7b4545sak-bz-539112328?$2052_1641_PNG$', NULL, NULL, 3),
	(4, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/na30n6555ts-az/gallery/br-na9300k-gas-cooktop-with-19k-btu-dual-burner-na30n6555ts-az-538948883?$2052_1641_PNG$', NULL, NULL, 4),
	(5, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/qn50q60dagxzd/gallery/br-qled-q60d-qn50q60dagxzd-540451114?$2052_1641_PNG$', NULL, NULL, 5),
	(6, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/vr3mb77312k-fz/gallery/br-power-bot-vr7700-vr3mb77312k-fz-541690669?$2052_1641_PNG$', NULL, NULL, 6),
	(7, NULL, 'https://samsungbrshop.vtexassets.com/arquivos/ids/228347-600-auto?v=638411051051530000&width=600&height=auto&aspect=true', NULL, NULL, 7),
	(15, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/nv7b4545sak-bz/gallery/br-nv7000b-nv7b4550vab-nv7b4545sak-bz-539112328?$2052_1641_PNG$', NULL, NULL, 3),
	(16, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/na30n6555ts-az/gallery/br-na9300k-gas-cooktop-with-19k-btu-dual-burner-na30n6555ts-az-538948883?$2052_1641_PNG$', NULL, NULL, 4),
	(20, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/wd14bb944dgbaz/gallery/br-wd9400b-wd14bb944dgbaz-538048163?$2052_1641_PNG$', NULL, NULL, 1),
	(21, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/wd13t704dbh-az/gallery/br-combo-wd10t704dbxtl-wd13t704dbh-az-531740913?$2052_1641_PNG$', NULL, NULL, 2),
	(22, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/wd13t704dbh-az/gallery/br-combo-wd10t704dbxtl-wd13t704dbh-az-531741320?$2052_1641_PNG$', NULL, NULL, 2),
	(23, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/nv7b4545sak-bz/gallery/br-nv7000b-nv7b4550vab-nv7b4545sak-bz-538938910?$2052_1641_PNG$', NULL, NULL, 3),
	(25, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/na30n6555ts-az/gallery/br-na9300k-gas-cooktop-with-19k-btu-dual-burner-na30n6555ts-az-538948863?$2052_1641_PNG$', NULL, NULL, 4),
	(26, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/na30n6555ts-az/gallery/br-na9300k-gas-cooktop-with-19k-btu-dual-burner-na30n6555ts-az-538948864?$684_547_PNG$', NULL, NULL, 4),
	(27, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/na30n6555ts-az/gallery/br-na9300k-gas-cooktop-with-19k-btu-dual-burner-na30n6555ts-az-538948866?$684_547_PNG$', NULL, NULL, 4),
	(28, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/qn50q60dagxzd/gallery/br-qled-q60d-qn50q60dagxzd-540451140?$684_547_PNG$', NULL, NULL, 5),
	(29, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/qn50q60dagxzd/gallery/br-qled-q60d-qn50q60dagxzd-540451142?$684_547_PNG$', NULL, NULL, 5),
	(30, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/vr3mb77312k-fz/gallery/br-power-bot-vr7700-vr3mb77312k-fz-541690674?$2052_1641_PNG$', NULL, NULL, 6),
	(31, NULL, 'https://images.samsung.com/is/image/samsung/p6pim/br/vr3mb77312k-fz/gallery/br-power-bot-vr7700-vr3mb77312k-fz-541690677?$2052_1641_PNG$', NULL, NULL, 6),
	(32, NULL, 'https://samsungbrshop.vtexassets.com/arquivos/ids/227229-600-auto?v=638405122912630000&width=600&height=auto&aspect=true', NULL, NULL, 7),
	(33, NULL, 'https://samsungbrshop.vtexassets.com/arquivos/ids/227227-600-auto?v=638405122903900000&width=600&height=auto&aspect=true', NULL, NULL, 7),
	(34, NULL, 'https://samsungbrshop.vtexassets.com/arquivos/ids/227226-600-auto?v=638405122899400000&width=600&height=auto&aspect=true', NULL, NULL, 7),
	(35, NULL, 'https://samsungbrshop.vtexassets.com/arquivos/ids/227225-600-auto?v=638405122894370000&width=600&height=auto&aspect=true', NULL, NULL, NULL);

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

-- Copiando dados para a tabela db_samsung.units: ~1 rows (aproximadamente)
DELETE FROM `units`;
INSERT INTO `units` (`id`, `code`, `created_at`, `description`, `status`, `updated_at`) VALUES
	(1, 'Geral', '2024-06-30 17:54:46.000000', 'Geral', NULL, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
