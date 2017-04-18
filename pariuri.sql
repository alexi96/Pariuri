-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2017 at 03:49 PM
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
ALTER TABLE `composed_ticket` ADD CONSTRAINT fk_composed_ticket1 FOREIGN KEY (`game`) REFERENCES `game`(`id`);
ALTER TABLE `composed_ticket` ADD CONSTRAINT fk_composed_ticket2 FOREIGN KEY (`user`) REFERENCES `user`(`id`);
ALTER TABLE `ticket` ADD CONSTRAINT fk_ticket1 FOREIGN KEY (`composed_ticket`) REFERENCES `composed_ticket`(`id`);
ALTER TABLE `ticket` ADD CONSTRAINT fk_ticket2 FOREIGN KEY (`type`) REFERENCES `statistic_type`(`id`);
ALTER TABLE `team` ADD CONSTRAINT fk_team1 FOREIGN KEY (`country`) REFERENCES `country`(`id`);
ALTER TABLE `plays` ADD CONSTRAINT fk_plays1 FOREIGN KEY (`team`) REFERENCES `team`(`id`);
ALTER TABLE `plays` ADD CONSTRAINT fk_plays2 FOREIGN KEY (`game`) REFERENCES `game`(`id`);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createStatisticTypes` ()  NO SQL
BEGIN
DELETE FROM `statistic_type`;
ALTER TABLE `statistic_type` AUTO_INCREMENT = 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeKeys` ()  NO SQL
BEGIN
ALTER TABLE `result` DROP FOREIGN KEY fk_result1;
ALTER TABLE `result` DROP FOREIGN KEY fk_result2;
ALTER TABLE `composed_ticket` DROP FOREIGN KEY fk_composed_ticket1;
ALTER TABLE `composed_ticket` DROP FOREIGN KEY fk_composed_ticket2;
ALTER TABLE `ticket` DROP FOREIGN KEY fk_ticket1;
ALTER TABLE `ticket` DROP FOREIGN KEY fk_ticket2;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `composed_ticket`
--

CREATE TABLE `composed_ticket` (
  `id` int(11) NOT NULL,
  `game` int(11) NOT NULL,
  `user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `name` varchar(512) NOT NULL,
  `chance` float NOT NULL,
  `date` datetime NOT NULL,
  `descripion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plays`
--

CREATE TABLE `plays` (
  `id` int(11) NOT NULL,
  `game` int(11) NOT NULL,
  `team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Table structure for table `statistic_type`
--

CREATE TABLE `statistic_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `country` int(11) NOT NULL,
  `image` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `value` float NOT NULL,
  `operation` tinyint(4) NOT NULL,
  `composed_ticket` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(17, 'delgado2009', '123456', 'Alin', 'Ivascu', 'catal.warrior2@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `composed_ticket`
--
ALTER TABLE `composed_ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_composed_ticket1` (`game`),
  ADD KEY `fk_composed_ticket2` (`user`);

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
  ADD KEY `fk_ticket2` (`type`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `plays`
--
ALTER TABLE `plays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `statistic_type`
--
ALTER TABLE `statistic_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `composed_ticket`
--
ALTER TABLE `composed_ticket`
  ADD CONSTRAINT `fk_composed_ticket1` FOREIGN KEY (`game`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `fk_composed_ticket2` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

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
  ADD CONSTRAINT `fk_ticket2` FOREIGN KEY (`type`) REFERENCES `statistic_type` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
