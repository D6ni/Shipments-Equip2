

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de dades: `shipments`
--

-- --------------------------------------------------------

--
-- Estructura de la taula `actions`
--

DROP TABLE IF EXISTS `actions`;
CREATE TABLE IF NOT EXISTS `actions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(31) NOT NULL,
  `performer_username` varchar(255) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shipment_id` bigint NOT NULL,
  `tracking_number` int DEFAULT NULL,
  `courier_username` varchar(255) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_action_shipment_x_date` (`date` DESC,`shipment_id`),
  KEY `type` (`type`),
  KEY `fk_performer_username` (`performer_username`),
  KEY `fk_shipment_id` (`shipment_id`),
  KEY `fk_courier_username` (`courier_username`)
) ;

-- --------------------------------------------------------

--
-- Estructura de la taula `shipments`
--

DROP TABLE IF EXISTS `shipments`;
CREATE TABLE IF NOT EXISTS `shipments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(31) NOT NULL,
  `sender_id` bigint DEFAULT NULL,
  `recipient_id` bigint DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `width` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `express` tinyint(1) DEFAULT NULL,
  `fragile` tinyint(1) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recipient_id` (`recipient_id`),
  KEY `fk_sender_id` (`sender_id`)
) ;

-- --------------------------------------------------------

--
-- Estructura de la taula `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `door` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

-- --------------------------------------------------------

--
-- Estructura de la taula `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(25) NOT NULL,
  `role` varchar(31) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `extension` int DEFAULT NULL,
  `office_id` bigint DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `role` (`role`),
  KEY `full_name` (`full_name`),
  KEY `role_x_full_name` (`role`,`full_name`),
  KEY `fk_company_id` (`company_id`),
  KEY `fk_office_id` (`office_id`)
) ;

-- --------------------------------------------------------

--
-- Estructura de la taula `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE IF NOT EXISTS `companies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_company_name` (`name`)
) ;

-- --------------------------------------------------------

--
-- Estructura de la taula `offices`
--

DROP TABLE IF EXISTS `offices`;
CREATE TABLE IF NOT EXISTS `offices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_office_name` (`name`)
) ;

--
-- Restriccions per a les taules bolcades
--

--
-- Restriccions per a la taula `actions`
--
ALTER TABLE `actions`
  ADD CONSTRAINT `fk_performer_username` FOREIGN KEY (`performer_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `fk_courier_username` FOREIGN KEY (`courier_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `fk_shipment_id` FOREIGN KEY (`shipment_id`) REFERENCES `shipments` (`id`);

--
-- Restriccions per a la taula `shipments`
--
ALTER TABLE `shipments`
  ADD CONSTRAINT `fk_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `addresses` (`id`),
  ADD CONSTRAINT `fk_recipient_id` FOREIGN KEY (`recipient_id`) REFERENCES `addresses` (`id`);

--
-- Restriccions per a la taula `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_office_id` FOREIGN KEY (`office_id`) REFERENCES `offices` (`id`),
  ADD CONSTRAINT `fk_company_id` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
