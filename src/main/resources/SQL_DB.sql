/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Dima
 * Created: 07.10.2016
 */

-- phpMyAdmin SQL Dump
-- version 4.6.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 07, 2016 at 08:22 AM
-- Server version: 5.6.32
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tcs`
--

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL,
  `carId` bigint(20) DEFAULT NULL,
  `cardNumber` char(20) NOT NULL,
  `state` int(11) NOT NULL,
  `accessLevel` int(11) NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `carId`, `cardNumber`, `state`, `accessLevel`, `createDate`) VALUES
(1, 10, '216,50218', 0, 1, '2016-10-03 09:08:56'),
(2, 11, '201,46106', 0, 1, '2016-10-03 09:23:17'),
(3, 12, '056,22570', 0, 1, '2016-10-04 09:05:33'),
(4, 13, '071,56778', 0, 1, '2016-10-04 09:58:44'),
(5, 14, '187,44874', 0, 1, '2016-10-04 09:59:50');

-- --------------------------------------------------------

--
-- Table structure for table `cargos`
--

CREATE TABLE `cargos` (
  `id` bigint(20) NOT NULL,
  `sampleId` bigint(20) DEFAULT NULL,
  `weightIn` int(11) DEFAULT NULL,
  `weightOut` int(11) DEFAULT NULL,
  `dischargingPlace` char(20) DEFAULT NULL,
  `dischargeDate` timestamp NULL DEFAULT NULL,
  `loadingPlace` char(20) DEFAULT NULL,
  `loadingDate` timestamp NULL DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cargos`
--

INSERT INTO `cargos` (`id`, `sampleId`, `weightIn`, `weightOut`, `dischargingPlace`, `dischargeDate`, `loadingPlace`, `loadingDate`) VALUES
(1, 23, 0, 0, 'R02', '2016-09-30 11:21:43', NULL, NULL),
(2, 24, 0, 0, 'R02', '2016-09-30 10:57:18', NULL, NULL),
(3, 26, 0, 0, 'R02', '2016-09-30 11:22:23', NULL, NULL),
(4, 25, 0, 0, 'R01', '2016-09-30 11:21:40', NULL, NULL),
(5, 27, 0, 0, 'R01', '2016-09-30 11:30:06', NULL, NULL),
(6, NULL, 0, 0, NULL, NULL, NULL, NULL),
(7, 28, 0, 0, 'R01', '2016-09-30 11:38:57', NULL, NULL),
(8, 29, 0, 0, 'R01', '2016-10-03 04:24:25', NULL, NULL),
(9, 30, 0, 0, 'R01', '2016-10-03 07:32:33', NULL, NULL),
(10, 31, 0, 0, 'R01', '2016-10-06 05:16:02', NULL, NULL),
(11, 32, 0, 0, 'R01', '2016-10-07 12:18:34', NULL, NULL),
(12, NULL, 0, 0, NULL, NULL, NULL, NULL),
(13, NULL, 0, 0, NULL, NULL, NULL, NULL),
(14, NULL, 0, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `id` bigint(20) NOT NULL,
  `cargoId` bigint(20) NOT NULL,
  `driverId` bigint(20) NOT NULL,
  `siloNumber` varchar(10) DEFAULT NULL,
  `destination` char(10) DEFAULT NULL,
  `carNumber` char(10) NOT NULL,
  `ttnNumber` char(10) NOT NULL,
  `nomenclature` varchar(20) DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `leaveDate` timestamp NULL DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`id`, `cargoId`, `driverId`, `siloNumber`, `destination`, `carNumber`, `ttnNumber`, `nomenclature`, `createDate`, `leaveDate`) VALUES
(14, 14, 14, NULL, NULL, '4343', 're', 'er', '2016-10-04 09:59:50', NULL),
(13, 13, 13, NULL, NULL, '879', '789', 'rer', '2016-10-04 09:58:44', NULL),
(12, 12, 12, NULL, NULL, '2134 HE-7', '555889', 'seno', '2016-10-04 09:05:33', NULL),
(11, 11, 11, '2', 'R01', '4783-BB7', '123123', 'psheno', '2016-10-03 09:23:17', NULL),
(10, 10, 10, '5', 'R02', '4700-em1', '2342342', 'psheno', '2016-10-03 06:08:55', NULL),
(9, 9, 9, '12', 'R01', '132231', '123123', 'gger', '2016-10-03 01:13:05', '2016-10-03 07:32:47'),
(8, 8, 8, '2', 'R01', '23423', '32423', 'rere', '2016-10-03 03:52:23', '2016-10-03 05:40:33');

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `Id` bigint(20) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `mobileNumber` char(13) DEFAULT NULL,
  `organization` char(30) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`Id`, `firstName`, `lastName`, `mobileNumber`, `organization`) VALUES
(1, 'Dima', 'Lopop', '+232323', 'Avectis'),
(2, 'Ivan', 'Pal', '5555669', 'Av'),
(3, 'йцу', 'йцу', 'йцу', 'йцуц'),
(4, 'Pavel', 'pol', '13123', 'asa'),
(5, 'Dima', 'Poplavsky', '2558852', 'avectis'),
(6, 'ivan', 'palc', '234234', 'av'),
(7, 'fre', 'fff', '234234', 'sasf'),
(8, 'rer', 'ere', '32423', 'rerr'),
(9, 'Linkoln', 'Barovs', '+37558814', 'pobeg'),
(10, 'dede', 'ded', '44887878', 'eded'),
(11, 'ivan', 'palch', '4474546', 'avectis'),
(12, 'Dima', 'Wew', '323232', 'rer'),
(13, 'Fun', 'lol', '789', 'pop'),
(14, 'fe', 'fdf', '343', 'fdf');

-- --------------------------------------------------------

--
-- Table structure for table `queues`
--

CREATE TABLE `queues` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `queues`
--

INSERT INTO `queues` (`id`, `name`, `type`) VALUES
(1, 'MainTable', 'INFO'),
(2, 'Buffer', 'BUFFER'),
(3, 'R01', 'DOCK'),
(4, 'R02', 'DOCK'),
(5, 'R03', 'DOCK');

-- --------------------------------------------------------

--
-- Table structure for table `queues_cards`
--

CREATE TABLE `queues_cards` (
  `queue_id` bigint(20) NOT NULL,
  `cards_id` bigint(20) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `queues_cards`
--

INSERT INTO `queues_cards` (`queue_id`, `cards_id`, `order_id`) VALUES
(3, 2, 0),
(4, 1, 0),
(1, 1, 0),
(1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sample`
--

CREATE TABLE `sample` (
  `id` bigint(20) NOT NULL,
  `name` char(20) NOT NULL,
  `weediness` float DEFAULT NULL,
  `gluten` float DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `nomenclature` char(20) DEFAULT NULL,
  `cultureClass` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sample`
--

INSERT INTO `sample` (`id`, `name`, `weediness`, `gluten`, `humidity`, `nomenclature`, `cultureClass`) VALUES
(23, 'de4', 11, 22, 33, 'Пшено', '3'),
(24, 'рр6', 0, 0, 80, 'Пшено', '1'),
(25, 'dede', 0, 0, 0, '4', '4'),
(26, 'ree', 0, 0, 324, '34', '234'),
(27, 'проба1', 0, 0, 40, 'пшеница', '3'),
(28, '234', 0, 0, 324, '324', '234'),
(29, 'wew', 0, 0, 12, '11', '22'),
(30, 'ger', 0, 0, 12, '12', '12'),
(31, 'gg5', 0, 0, 50, 'psheno', '4'),
(32, 'rr1', 0, 0, 22, 'sese', '3');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('alex', '$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', 1),
('dima', '$2a$12$fmZfmJ3f8HGfTqKjxUBL9eTbjNd0.Q.g2LdBSMpZcwPGIvmBcZyRW', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `userRoleId` bigint(20) NOT NULL,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`userRoleId`, `username`, `role`) VALUES
(4, 'dima', 'ROLE_ADMIN'),
(3, 'alex', 'ROLE_USER'),
(5, 'dima', 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `carId` (`carId`);

--
-- Indexes for table `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sampleId` (`sampleId`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcarToCargo` (`cargoId`),
  ADD KEY `FKcarToDriver` (`driverId`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `queues`
--
ALTER TABLE `queues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `queues_cards`
--
ALTER TABLE `queues_cards`
  ADD KEY `FKqueues_cards_to_queue` (`queue_id`),
  ADD KEY `FKqueues_cards_to_cards` (`cards_id`);

--
-- Indexes for table `sample`
--
ALTER TABLE `sample`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`userRoleId`),
  ADD UNIQUE KEY `uni_username_role` (`role`,`username`),
  ADD KEY `fk_username_idx` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `cargos`
--
ALTER TABLE `cargos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2002;
--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2002;
--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2002;
--
-- AUTO_INCREMENT for table `queues`
--
ALTER TABLE `queues`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sample`
--
ALTER TABLE `sample`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `userRoleId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `queues_cards`
--
ALTER TABLE `queues_cards`
  ADD CONSTRAINT `FKqueues_cards_to_cards` FOREIGN KEY (`cards_id`) REFERENCES `cards` (`id`),
  ADD CONSTRAINT `FKqueues_cards_to_queue` FOREIGN KEY (`queue_id`) REFERENCES `queues` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
