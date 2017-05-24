-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2017 at 08:27 PM
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
INSERT INTO `statistic_type`(`name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES ('Winner', 0, 2, 2, 2);
INSERT INTO `statistic_type`(`name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES ('Winner`s score', 3, 5, 2, 1.5);
INSERT INTO `statistic_type`(`name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES ('Loser`s score', 3, 5, 2, 1.5);
INSERT INTO `statistic_type`(`name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES ('Time', 5, 5, 3, 1.5);
INSERT INTO `statistic_type`(`name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES ('First kill', 0, 2, 2, 2);
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
  `ammount` float NOT NULL,
  `user` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `validated` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `composed_ticket`
--

INSERT INTO `composed_ticket` (`id`, `ammount`, `user`, `time`, `validated`) VALUES
(30, 1, 24, '1970-01-01 15:17:37', 1),
(31, 1, 16, '1970-01-01 15:22:40', 1),
(32, 1, 16, '1970-01-01 15:23:34', 1),
(33, 1, 24, '1970-01-01 15:29:17', 1),
(34, 100, 16, '1970-01-01 11:07:17', 1),
(35, 2, 16, '2017-05-19 11:16:24', 0);

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
(21, 'Bucharest dreamhack 2017', 45, '2017-05-07 12:00:00', 'Description here!'),
(26, 'Joc1', 50, '2017-05-08 12:00:00', 'Description here!'),
(27, 'test game', 50, '2017-05-07 12:00:00', 'Description here!'),
(28, 'test game2', 60, '2017-01-01 08:22:00', 'Description here!'),
(29, 'test game3', 40, '2017-05-08 12:00:00', 'Description here!'),
(30, 'Joc2', 25, '2017-01-01 08:22:00', 'Description here!'),
(31, 'Joc2', 40, '2017-01-01 08:22:00', 'Description here!'),
(32, 'Joc3', 50, '2017-01-01 08:22:00', 'Description here!');

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
(1, 21, 2),
(2, 21, 1),
(3, 26, 4),
(4, 26, 3),
(5, 27, 7),
(6, 27, 8),
(7, 28, 7),
(8, 28, 8),
(9, 29, 7),
(10, 29, 8),
(11, 30, 4),
(12, 30, 1),
(13, 31, 8),
(14, 31, 3),
(15, 32, 1),
(16, 32, 1);

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
(1, 2, 21, 1),
(2, 10, 21, 2),
(3, 5, 21, 3),
(4, 34, 21, 4),
(5, 2, 21, 5),
(6, 1, 26, 1),
(7, 10, 26, 2),
(8, 5, 26, 3),
(9, 20, 26, 4),
(10, 2, 26, 5),
(11, 1, 27, 1),
(12, 10, 27, 2),
(13, 5, 27, 3),
(14, 7, 27, 4),
(15, 1, 27, 5),
(16, 1, 28, 1),
(17, 10, 28, 2),
(18, 5, 28, 3),
(19, 10, 28, 4),
(20, 1, 28, 5),
(21, 1, 29, 1),
(22, 10, 29, 2),
(23, 5, 29, 3),
(24, 10, 29, 4),
(25, 1, 29, 5),
(26, 1, 30, 1),
(27, 1, 30, 2),
(28, 1, 30, 3),
(29, 1, 30, 4),
(30, 1, 30, 5),
(31, 1, 31, 1),
(32, 10, 31, 2),
(33, 10, 31, 3),
(34, 10, 31, 4),
(35, 2, 31, 5);

-- --------------------------------------------------------

--
-- Table structure for table `statistic_type`
--

CREATE TABLE `statistic_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `deviation` float NOT NULL,
  `exact_multiply` float NOT NULL,
  `medium_multiply` float NOT NULL,
  `far_multiply` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statistic_type`
--

INSERT INTO `statistic_type` (`id`, `name`, `deviation`, `exact_multiply`, `medium_multiply`, `far_multiply`) VALUES
(1, 'Winner', 0, 2, 2, 2),
(2, 'Winner`s score', 3, 5, 2, 1.5),
(3, 'Loser`s score', 3, 5, 2, 1.5),
(4, 'Time', 5, 5, 3, 1.5),
(5, 'First kill', 0, 2, 2, 2);

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
(4, 'UYA', 5, NULL),
(7, 'test1', 1, NULL),
(8, 'test2', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `value` float NOT NULL,
  `operation` tinyint(4) NOT NULL,
  `game` int(11) NOT NULL,
  `composed_ticket` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `value`, `operation`, `game`, `composed_ticket`, `type`) VALUES
(1, 1, 0, 30, 30, 1),
(2, 1, 0, 30, 30, 2),
(3, 1, 0, 30, 30, 3),
(4, 1, 0, 30, 30, 4),
(5, 1, 0, 30, 30, 5),
(6, 1, 0, 30, 31, 1),
(7, 1, 0, 30, 32, 1),
(8, 1, 0, 30, 32, 2),
(9, 1, 0, 30, 32, 3),
(10, 1, 0, 30, 32, 4),
(11, 1, 0, 30, 32, 5),
(12, 1, 0, 30, 33, 1),
(13, 1, 0, 30, 33, 2),
(14, 2, 0, 30, 33, 3),
(15, 1, 0, 30, 33, 4),
(16, 1, 0, 30, 33, 5),
(17, 1, 0, 31, 34, 1),
(18, 10, 1, 31, 34, 2),
(19, 10, 1, 31, 34, 4);

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
(22, 'ionm', '123456', 'Mircea', 'Ion', 'alex_i96@yahoo.com'),
(24, 'test', 'test', 'Radu', 'Ioan-Alexandru', 'alex_i96@yahoo.com');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `plays`
--
ALTER TABLE `plays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `statistic_type`
--
ALTER TABLE `statistic_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
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
