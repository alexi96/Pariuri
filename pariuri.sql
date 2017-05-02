-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2017 at 08:43 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pariuri`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `createKeys` ()  NO SQL
BEGIN
ALTER TABLE `result` ADD CONSTRAINT fk_result1 FOREIGN KEY (`game`) REFERENCES `game`(`id`);
ALTER TABLE `result` ADD CONSTRAINT fk_result2 FOREIGN KEY (`type`) REFERENCES `statistic_type`(`id`);
ALTER TABLE `composed_ticket` ADD CONSTRAINT fk_composed_ticket1 FOREIGN KEY (`user`) REFERENCES `user`(`id`);
ALTER TABLE `ticket` ADD CONSTRAINT fk_ticket1 FOREIGN KEY (`composed_ticket`) REFERENCES `composed_ticket`(`id`);
ALTER TABLE `ticket` ADD CONSTRAINT fk_ticket2 FOREIGN KEY (`game`) REFERENCES `game`(`id`);
ALTER TABLE `ticket` ADD CONSTRAINT fk_ticket3 FOREIGN KEY (`type`) REFERENCES `statistic_type`(`id`);
ALTER TABLE `team` ADD CONSTRAINT fk_team1 FOREIGN KEY (`country`) REFERENCES `country`(`id`);
ALTER TABLE `plays` ADD CONSTRAINT fk_plays1 FOREIGN KEY (`team`) REFERENCES `team`(`id`);
ALTER TABLE `plays` ADD CONSTRAINT fk_plays2 FOREIGN KEY (`game`) REFERENCES `game`(`id`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createStatisticTypes` ()  NO SQL
BEGIN
DELETE FROM `statistic_type`;
ALTER TABLE `statistic_type` AUTO_INCREMENT = 1;
INSERT INTO `statistic_type`(`name`) VALUES ('Winner');
INSERT INTO `statistic_type`(`name`) VALUES ('Winner`s score');
INSERT INTO `statistic_type`(`name`) VALUES ('Loser`s score');
INSERT INTO `statistic_type`(`name`) VALUES ('Time');
INSERT INTO `statistic_type`(`name`) VALUES ('First kill');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeKeys` ()  NO SQL
BEGIN
ALTER TABLE `result` DROP FOREIGN KEY fk_result1;
ALTER TABLE `result` DROP FOREIGN KEY fk_result2;
ALTER TABLE `composed_ticket` DROP FOREIGN KEY fk_composed_ticket1;
ALTER TABLE `ticket` DROP FOREIGN KEY fk_ticket1;
ALTER TABLE `ticket` DROP FOREIGN KEY fk_ticket2;
ALTER TABLE `ticket` DROP FOREIGN KEY fk_ticket3;
ALTER TABLE `team` DROP FOREIGN KEY fk_team1;
ALTER TABLE `plays` DROP FOREIGN KEY fk_plays1;
ALTER TABLE `plays` DROP FOREIGN KEY fk_plays2;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `composed_ticket`
--

CREATE TABLE `composed_ticket` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `validated` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `composed_ticket`
--

INSERT INTO `composed_ticket` (`id`, `user`, `time`, `validated`) VALUES
(11, 16, '2017-04-26 00:00:00', 1),
(12, 16, '2017-04-26 00:00:00', 1),
(13, 16, '2017-04-28 00:00:00', 1),
(14, 21, '2017-04-28 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(1, 'Romania'),
(2, 'Germany'),
(3, 'Italy'),
(4, 'Russia'),
(5, 'France');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `name` varchar(512) NOT NULL,
  `chance` float NOT NULL,
  `date` datetime NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id`, `name`, `chance`, `date`, `description`) VALUES
(7, 'Bucharest dreamhack 2017', 10, '2017-01-01 08:22:00', 'Bucharest dreamhack 2017'),
(8, 'test', 123, '2017-01-01 08:22:00', 'Description here!'),
(9, 'Test 2', 50, '2017-04-28 09:02:43', 'Description here!'),
(10, 'ShooterQ 2017', 40, '2017-05-01 10:00:00', 'ShooterQ 2017 la data de 1/5/2017 10:00'),
(11, 'Death mach', 30, '2017-05-07 12:00:00', 'Death mach!!!');

-- --------------------------------------------------------

--
-- Table structure for table `plays`
--

CREATE TABLE `plays` (
  `id` int(11) NOT NULL,
  `game` int(11) NOT NULL,
  `team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plays`
--

INSERT INTO `plays` (`id`, `game`, `team`) VALUES
(3, 7, 2),
(4, 7, 1),
(5, 8, 1),
(6, 8, 2),
(7, 9, 1),
(8, 9, 2),
(9, 10, 3),
(10, 10, 4),
(11, 11, 1),
(12, 11, 2);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `value` float NOT NULL,
  `game` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`id`, `value`, `game`, `type`) VALUES
(1, 3, 7, 1),
(2, 3, 7, 2),
(3, 3, 7, 3),
(4, 3, 7, 4),
(5, 3, 7, 5),
(6, 1, 8, 1),
(7, 2, 8, 2),
(8, 5, 8, 3),
(9, 20, 8, 4),
(10, 2, 8, 5),
(11, 1, 9, 1),
(12, 1, 9, 2),
(13, 1, 9, 3),
(14, 5, 9, 4),
(15, 1, 9, 5),
(16, 1, 10, 1),
(17, 10, 10, 2),
(18, 30, 10, 3),
(19, 9, 10, 4),
(20, 1, 10, 5),
(21, 2, 11, 1),
(22, 50, 11, 2),
(23, 30, 11, 3),
(24, 9, 11, 4),
(25, 2, 11, 5);

-- --------------------------------------------------------

--
-- Table structure for table `statistic_type`
--

CREATE TABLE `statistic_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `deviation` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statistic_type`
--

INSERT INTO `statistic_type` (`id`, `name`, `deviation`) VALUES
(1, 'Winner', 0),
(2, 'Winner`s score', 3),
(3, 'Loser`s score', 3),
(4, 'Time', 5),
(5, 'First kill', 0);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `country` int(11) NOT NULL,
  `image` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `name`, `country`, `image`) VALUES
(1, 'Rocket', 1, NULL),
(2, 'Virtus.pro', 2, NULL),
(3, 'ViCi', 4, NULL),
(4, 'UYA', 5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `ammount` float NOT NULL,
  `value` float NOT NULL,
  `operation` tinyint(4) NOT NULL,
  `game` int(11) NOT NULL,
  `composed_ticket` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `ammount`, `value`, `operation`, `game`, `composed_ticket`, `type`) VALUES
(1, 100, 2, 0, 8, 11, 1),
(2, 500, 50, 1, 8, 11, 2),
(3, 2, 2, 0, 8, 12, 1),
(4, 500, 1, 0, 9, 13, 1),
(5, 500, 1, 0, 9, 13, 5),
(6, 500, 1, 0, 10, 14, 1),
(7, 100, 2, 0, 11, 14, 1),
(8, 50, 10, 2, 10, 14, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`) VALUES
(15, 'alexi96', 'pass', 'Radu', 'Ioan-Alexandru', 'alex_i96@yahoo.com'),
(16, 'admin', 'pass', 'Radu', 'Ioan-Alexandru', 'alex_i96@yahoo.com'),
(17, 'delgado2009', '123456', 'Alin', 'Ivascu', 'catal.warrior2@gmail.com'),
(21, 'test', 'pass', 'Radu', 'Ioan-Alexandru', 'alex_i96@yahoo.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `composed_ticket`
--
ALTER TABLE `composed_ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_composed_ticket1` (`user`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `plays`
--
ALTER TABLE `plays`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_plays1` (`team`),
  ADD KEY `fk_plays2` (`game`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_result1` (`game`),
  ADD KEY `fk_result2` (`type`);

--
-- Indexes for table `statistic_type`
--
ALTER TABLE `statistic_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_team1` (`country`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ticket1` (`composed_ticket`),
  ADD KEY `fk_ticket2` (`game`),
  ADD KEY `fk_ticket3` (`type`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `composed_ticket`
--
ALTER TABLE `composed_ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `plays`
--
ALTER TABLE `plays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `statistic_type`
--
ALTER TABLE `statistic_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `composed_ticket`
--
ALTER TABLE `composed_ticket`
  ADD CONSTRAINT `fk_composed_ticket1` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Constraints for table `plays`
--
ALTER TABLE `plays`
  ADD CONSTRAINT `fk_plays1` FOREIGN KEY (`team`) REFERENCES `team` (`id`),
  ADD CONSTRAINT `fk_plays2` FOREIGN KEY (`game`) REFERENCES `game` (`id`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `fk_result1` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `fk_result2` FOREIGN KEY (`type`) REFERENCES `statistic_type` (`id`);

--
-- Constraints for table `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `fk_team1` FOREIGN KEY (`country`) REFERENCES `country` (`id`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_ticket1` FOREIGN KEY (`composed_ticket`) REFERENCES `composed_ticket` (`id`),
  ADD CONSTRAINT `fk_ticket2` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `fk_ticket3` FOREIGN KEY (`type`) REFERENCES `statistic_type` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
