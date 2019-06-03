-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2019 at 11:09 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: biotrio
--
DROP DATABASE IF EXISTS biotrio;
CREATE DATABASE IF NOT EXISTS biotrio DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE biotrio;

-- --------------------------------------------------------

--
-- Table structure for table booking
--

CREATE TABLE booking (
  id int(11) NOT NULL,
  performance_ref int(11) NOT NULL,
  seat varchar(4) NOT NULL,
  booked_on datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  phone varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table performance
--

CREATE TABLE performance (
  id int(11) NOT NULL,
  title_ref int(11) NOT NULL,
  theater_ref int(11) NOT NULL,
  date datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table terces
--

CREATE TABLE terces (
  nimda int(11) NOT NULL DEFAULT '1',
  drowssap varchar(25) NOT NULL DEFAULT '12345'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table theater
--

CREATE TABLE theater (
  id int(11) NOT NULL,
  color varchar(15) DEFAULT NULL,
  rows tinyint(3) UNSIGNED NOT NULL DEFAULT '1',
  seats tinyint(3) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers theater
--
DELIMITER $$
CREATE TRIGGER `q1` BEFORE INSERT ON `theater` FOR EACH ROW BEGIN
 IF (NEW.rows > 26) THEN
  SET NEW.rows = 26;
 END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `q2` BEFORE UPDATE ON `theater` FOR EACH ROW BEGIN
 IF (NEW.rows > 26) THEN
  SET NEW.rows = 26;
 END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table title_list
--

CREATE TABLE title_list (
  id int(11) NOT NULL,
  title varchar(50) NOT NULL,
  display tinyint(1) NOT NULL DEFAULT '1',
  genre varchar(80) NOT NULL DEFAULT 'movie',
  equipment varchar(30) DEFAULT NULL,
  duration smallint(5) UNSIGNED NOT NULL DEFAULT '30',
  producer varchar(50) DEFAULT NULL,
  actors varchar(150) DEFAULT NULL,
  description varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers title_list
--
DELIMITER $$
CREATE TRIGGER `w1` BEFORE INSERT ON `title_list` FOR EACH ROW BEGIN
 IF (NEW.duration > 999) THEN
  SET NEW.duration = 999;
 END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `w2` BEFORE UPDATE ON `title_list` FOR EACH ROW BEGIN
 IF (NEW.duration > 999) THEN
  SET NEW.duration = 999;
 END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table booking
--
ALTER TABLE booking
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY performance_id (performance_ref,seat);

--
-- Indexes for table performance
--
ALTER TABLE performance
  ADD PRIMARY KEY (id),
  ADD KEY performance_id (title_ref),
  ADD KEY theater_id (theater_ref);

--
-- Indexes for table theater
--
ALTER TABLE theater
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY color (color);

--
-- Indexes for table title_list
--
ALTER TABLE title_list
  ADD PRIMARY KEY (id);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table booking
--
ALTER TABLE booking
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table performance
--
ALTER TABLE performance
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table theater
--
ALTER TABLE theater
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table title_list
--
ALTER TABLE title_list
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table booking
--
ALTER TABLE booking
  ADD CONSTRAINT booking_ibfk_1 FOREIGN KEY (performance_ref) REFERENCES performance (id) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table performance
--
ALTER TABLE performance
  ADD CONSTRAINT performance_ibfk_1 FOREIGN KEY (title_ref) REFERENCES title_list (id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT performance_ibfk_2 FOREIGN KEY (theater_ref) REFERENCES theater (id) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
