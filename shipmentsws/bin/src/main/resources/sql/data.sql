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

--
-- Bolcament de dades per a la taula `companies`
--

INSERT INTO `companies` (`id`, `name`) VALUES
(1, 'Correos'),
(2, 'SEUR'),
(3, 'UPS');

--
-- Bolcament de dades per a la taula `offices`
--

INSERT INTO `offices` (`id`, `name`) VALUES
(2, 'Cotonat'),
(1, 'Mestre Candi'),
(3, 'Pedraforca');

--
-- Bolcament de dades per a la taula `users`
--

INSERT INTO `users` (`username`, `role`, `password`, `full_name`, `extension`, `office_id`, `place`, `company_id`) VALUES
('alex', 'RECEPTIONIST', '$2a$10$D25CUjZHnolADZpCmQzWLee7eDX5LEA1jpa3chdrNZ8Ad5u4gHUbm', 'Àlex Macia Pérez', 3515, 1, 'L15', NULL),
('genis', 'COURIER', '$2a$10$fN.nOfWlRY/LLotIWiseoeh/foQ1vFCY9bnpXmK3k8.VwW7F1xoPi', 'Genís Esteve i Prats', 1515, NULL, NULL, 1),
('laia', 'LOGISTICS_MANAGER', '$2a$10$EwsBI6trHD56ncjlsxAmwuic5R/qAzx6AyekBpCafndN.CiFuwJjK', 'Laia Vives i Marsans', 1501, 1, 'L01', NULL),
('lola', 'RECEPTIONIST', '$2a$10$vTJ82FGgKP36.WHHtWAGNOvCt0bF27/0HzN9OFo1EuU722Z0PKhde', 'Lola Valls i Vilalta', 2501, 3, 'L08', NULL),
('maria', 'RECEPTIONIST', '$2a$10$EogCF6kJDxTPsfQFciZjROaSBd/8Ok3orVe49KdEebVdyVTYrCKs2', 'Maria Lopez i Castells', 3513, 1, 'L13', NULL),
('raul', 'COURIER', '$2a$10$Zt92wjlBEPx2zXwdTfA4ZeM2cFAAX4MXY4y9y1BKMEZmYbNh.8dz6', 'Raul Casanova i Ferrer', 1504, NULL, NULL, 2),
('robert', 'RECEPTIONIST', '$2a$10$H1S18hqeIbIoPgVU7ECURu6nsitQ0/sGSEJ9Z0Dw6rBV/bloAmCTS', 'Robert Planes i Pujol', 3510, 1, 'L10', NULL),
('toni', 'RECEPTIONIST', '$2a$10$T1lgKgp5XiQAuvTiq4alQeWkgCVHpsHVgmgk/X7wIkJwHypR6TMP2', 'Antoni Bosc i Cases', 2508, 3, 'L08', NULL);

--
-- Bolcament de dades per a la taula `addresses`
--

INSERT INTO `addresses` (`id`, `name`, `street`, `number`, `floor`, `door`, `city`, `province`, `postal_code`, `country`) VALUES
(1, 'Tony Wyzek', 'Carrer Història', '19', '6', '1', 'Gavà', 'Barcelona', '08850', 'Spain'),
(2, 'IKEA Hospitalet', 'Avinguda de la Granvia de l''Hospitalet', '115-133', NULL, NULL, 'L''Hospitalet de Llobregat', 'Barcelona', '08908', 'Spain'),
(3, 'Departament de Salut', 'Travessera de les Corts ', '131-159', NULL, NULL, 'Barcelona', 'Barcelona', '08028', 'Spain'),
(4, 'Maria Núñez', 'Carrer Costat', '18', '1', '', 'Santa Coloma de Cervelló', 'Barcelona', '08690', 'Spain'),
(5, 'Chino Martín', 'Carrer Oest', '19', '5', '7', 'Santa Coloma de Cervelló', 'Barcelona', '08690', 'Spain'),
(6, 'Hospital de Viladecans', 'Av. de Gavà', '38', NULL, NULL, 'Viladecans', 'Barcelona', '08840', 'Spain');

--
-- Bolcament de dades per a la taula `shipments`
--

INSERT INTO `shipments` (`id`, `category`, `sender_id`, `recipient_id`, `weight`, `height`, `width`, `length`, `express`, `fragile`, `note`) VALUES
(1, 'PARTICULAR', 1, 4, NULL, NULL, NULL, NULL, 0, 0, 'Flowers for my love'),
(2, 'COMPANY', 2, 5, 2.5, 55, 25, 25, 0, 1, 'Chinese Vase'),
(3, 'GOVERNMENT', 3, 6, 12.8, 72.4, 92.5, 75, 1, 0, 'Medical Equipment');

--
-- Bolcament de dades per a la taula `actions`
--

INSERT INTO `actions` (`id`, `type`, `performer_username`, `date`, `shipment_id`, `tracking_number`, `courier_username`, `priority`) VALUES
(1, 'RECEPTION', 'alex', '2023-10-01 08:15:31', 1, 100, NULL, NULL),
(2, 'RECEPTION', 'maria', '2023-09-24 07:54:13', 2, 101, NULL, NULL),
(3, 'RECEPTION', 'lola', '2023-09-24 17:32:04', 3, 102, NULL, NULL),
(4, 'ASSIGNMENT', 'laia', '2023-10-02 11:21:15', 1, NULL, 'raul', 4),
(5, 'DELIVERY', 'raul', '2023-10-05 19:02:00', 1, NULL, NULL, NULL),
(6, 'ASSIGNMENT', 'laia', '2023-09-26 13:39:14', 2, NULL, 'genis', 9);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
