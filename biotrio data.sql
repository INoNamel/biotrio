SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biotrio`
--

--
-- Dumping data for table `booking`
--
USE biotrio;
START TRANSACTION;
DELETE FROM booking;
DELETE FROM theater;
DELETE FROM performance;
DELETE FROM title_list;
DELETE FROM terces;
--
-- Dumping data for table `title_list`
--

INSERT INTO `title_list` (`id`, `title`, `display`, `genre`, `equipment`, `duration`, `producer`, `actors`, `description`) VALUES
(1, 'Aquaman', 1, 'movie', '3D', 120, 'Ivan', 'James McAvoy, Mark Wahlberg, Johnny Depp', 'Duis sit amet porttitor ante, ac suscipit arcu. Ut et placerat eros, dignissim tincidunt tortor. Sed id pharetra arcu, volutpat suscipit lectus. Proin vulputate ac justo nec luctus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam laoreet at elit ac lacinia. Sed at odio eget tortor bibendum egestas sit amet a lectus. Fusce ut porta sapien. Curabitur faucibus elit at quam tempor interdum. Morbi ut dolor vel diam malesuada dictum. '),
(2, 'Chocolate Prinz', 1, 'show', NULL, 30, 'Pahan', 'Robin Williams, Matt Damon', 'Integer a euismod odio. Etiam faucibus tempor massa, sed aliquet ex efficitur blandit. Morbi pharetra nisl at nisl ullamcorper bibendum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque pulvinar, sem sed efficitur faucibus, justo mauris accumsan leo, sed vulputate risus ipsum vitae justo. Nunc nec justo in velit dapibus efficitur ut vel arcu. Maecenas varius condimentum dictum. Praesent malesuada metus at libero mattis porta.'),
(3, 'Superher', 1, 'movie', NULL, 160, NULL, NULL, 'Mauris mattis feugiat porta. Vestibulum accumsan laoreet turpis, sed imperdiet sem consectetur at. Morbi mi tellus, interdum non eros a, semper tincidunt nulla. Vivamus a porttitor lacus. Integer a tempus ligula, sed molestie orci. Aliquam eu nisi accumsan, commodo risus eget, dignissim mauris.'),
(4, 'Alloe', 1, 'movie', NULL, 180, 'Olaf', 'Sandra Bullock', 'Pellentesque maximus diam vitae justo facilisis pharetra. Nunc eget elementum orci. In suscipit vestibulum nisi a dapibus. Etiam egestas vel enim non vehicula. Curabitur sem diam, convallis quis iaculis ut, egestas elementum nibh. Maecenas imperdiet sit amet libero id consectetur.'),
(5, 'X-her', 1, 'movie', NULL, 140, NULL, NULL, NULL),
(6, 'Captain Obvious', 1, 'movie', NULL, 99, 'Nordex', 'Reese Witherspoon, Ryan Gosling', NULL),
(7, 'Allah Babah', 1, 'show', NULL, 77, 'Valera', NULL, 'Ut fringilla consequat quam ac accumsan. Proin dictum interdum vehicula. Maecenas leo ipsum, ornare at urna non, rhoncus dignissim ex. Nulla pharetra nulla id metus placerat tincidunt. Quisque pharetra orci nisl, nec euismod lectus mollis vitae. '),
(8, 'Submarine X', 1, 'movie', NULL, 90, 'Aloha', NULL, NULL),
(9, 'Vobla 2', 1, 'movie', NULL, 30, NULL, 'Tom Cruise, Joseph Gordon-Levitt ', NULL),
(10, 'Valla Habibi', 1, 'musicle', NULL, 30, 'Valera', 'Aqwe, ASdoqwd, ASdAVASdq', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin scelerisque augue a tortor posuere vestibulum. Phasellus vehicula ante sit amet enim porta rutrum. Integer pulvinar quis est id aliquet. Morbi non sem lectus. Quisque tempus sollicitudin commodo. Vestibulum laoreet sed elit ac egestas. '),
(13, 'Adasdas', 1, 'music', NULL, 30, NULL, NULL, NULL),
(14, 'Omaewa', 1, 'show', NULL, 120, 'Handehok', NULL, 'Duis tristique magna arcu, sit amet tempus ante porta non. Suspendisse non hendrerit leo. Integer efficitur magna a commodo luctus. Vivamus laoreet massa urna, non maximus erat interdum eu. Nullam lorem dui, semper id neque id, vehicula commodo enim. Vivamus purus turpis, condimentum quis libero et, egestas posuere lorem.'),
(16, 'Ask4', 0, 'musicle', NULL, 70, NULL, NULL, 'AAAAAAAAAAAAAAAAAAAAA'),
(17, 'QWEQWEQWE', 0, 'yes', NULL, 36, NULL, NULL, NULL),
(18, 'Ahmed XXX', 0, 'musicc', NULL, 74, 'qwe', NULL, NULL),
(19, 'GHADS21231231', 0, 'yes', NULL, 38, NULL, NULL, NULL),
(20, 'QWqwwwwww', 0, 'show', NULL, 36, NULL, NULL, NULL),
(21, 'Pepe', 0, 'show', NULL, 34, NULL, NULL, NULL),
(22, 'QOKWpakspdasd asd qwe as d', 1, '232323', NULL, 37, NULL, NULL, NULL),
(23, 'Azer', 1, 'music', 'dolby', 35, NULL, NULL, '12312asdqweqweasdaweqe');

--
-- Dumping data for table `theater`
--

INSERT INTO `theater` (`id`, `color`, `rows`, `seats`) VALUES
(1, 'blue', 14, 20),
(2, 'red', 8, 12),
(3, 'orange', 8, 6),
(4, 'black', 26, 9),
(5, 'QQqqq', 26, 6);

--
-- Dumping data for table `performance`
--

INSERT INTO `performance` (`id`, `title_ref`, `theater_ref`, `date`) VALUES
(1, 1, 1, '2019-06-01 20:30:00'),
(2, 2, 1, '2019-06-03 21:30:00'),
(3, 3, 3, '2019-06-29 09:15:00'),
(4, 1, 1, '2019-05-31 19:30:00'),
(5, 1, 3, '2019-05-31 22:30:00'),
(6, 6, 1, '2019-05-31 11:00:00'),
(7, 5, 3, '2019-06-08 16:30:00'),
(8, 4, 2, '2019-06-11 20:00:00'),
(10, 7, 2, '2019-05-31 23:00:00'),
(11, 9, 2, '2019-06-04 17:35:00'),
(12, 3, 2, '2019-06-16 01:22:00'),
(13, 14, 2, '2019-05-31 00:48:00'),
(14, 18, 4, '2019-06-03 17:35:00'),
(15, 9, 2, '2019-06-02 17:20:00'),
(16, 9, 2, '2019-06-01 12:30:00'),
(17, 9, 2, '2019-06-01 12:00:00'),
(18, 9, 1, '2019-06-02 19:00:00'),
(19, 9, 2, '2019-06-26 13:00:00'),
(20, 9, 2, '2019-06-11 18:00:00');

INSERT INTO `booking` (`id`, `performance_ref`, `seat`, `booked_on`, `phone`) VALUES
(1, 1, 'A2', '2019-05-19 02:37:07', '77665522'),
(2, 1, 'A6', '2019-05-23 11:27:44', '123123123'),
(3, 1, 'B12', '2019-05-24 07:33:52', '5532221'),
(4, 1, 'A15', '2019-05-24 12:34:29', '888777'),
(5, 1, 'B15', '2019-05-24 12:34:29', '888777'),
(6, 1, 'E7', '2019-05-24 12:34:58', '8887774'),
(7, 1, 'E6', '2019-05-24 12:34:58', '8887774'),
(8, 1, 'F4', '2019-05-24 12:34:58', '8887774'),
(9, 1, 'F5', '2019-05-24 12:34:58', '8887774'),
(10, 1, 'C9', '2019-05-24 12:37:33', '332211'),
(12, 1, 'A20', '2019-05-24 12:38:51', '215213'),
(13, 1, 'A17', '2019-05-24 12:39:37', '215213'),
(14, 1, 'D15', '2019-05-24 12:40:48', '215213'),
(15, 1, 'D16', '2019-05-24 12:40:48', '215213'),
(16, 1, 'F15', '2019-05-24 12:40:48', '215213'),
(17, 5, 'A2', '2019-05-24 12:45:25', '756322'),
(18, 5, 'A3', '2019-05-24 12:45:25', '756322'),
(19, 5, 'A4', '2019-05-24 12:45:25', '756322'),
(20, 5, 'A5', '2019-05-24 12:45:25', '756322'),
(21, 5, 'C5', '2019-05-24 12:53:15', '777444'),
(22, 5, 'C4', '2019-05-24 12:53:15', '777444'),
(23, 5, 'D1', '2019-05-24 12:53:53', '6123123'),
(24, 5, 'D2', '2019-05-24 12:53:53', '6123123'),
(25, 5, 'E2', '2019-05-24 12:53:53', '6123123'),
(26, 5, 'B6', '2019-05-24 12:58:48', '6123123'),
(27, 5, 'C6', '2019-05-24 12:58:48', '6123123'),
(28, 5, 'B2', '2019-05-24 13:02:55', '454545'),
(29, 5, 'F5', '2019-05-24 13:16:22', '7723231'),
(30, 5, 'H4', '2019-05-24 13:29:09', '231263'),
(31, 5, 'H6', '2019-05-24 13:29:09', '231263'),
(32, 5, 'H1', '2019-05-24 13:38:59', '412312'),
(33, 5, 'G6', '2019-05-24 13:40:06', '553333'),
(34, 5, 'G1', '2019-05-24 13:41:17', '222221'),
(35, 7, 'C1', '2019-05-24 13:42:39', '231745'),
(36, 7, 'C2', '2019-05-24 13:42:39', '231745'),
(37, 7, 'B6', '2019-05-24 13:58:44', '3231231'),
(38, 7, 'B5', '2019-05-24 13:58:44', '3231231'),
(39, 7, 'A5', '2019-05-24 13:58:44', '3231231'),
(40, 7, 'A6', '2019-05-24 14:08:22', '5212312'),
(41, 7, 'A3', '2019-05-24 14:08:22', '5212312'),
(42, 7, 'A1', '2019-05-24 14:08:22', '5212312'),
(43, 7, 'C3', '2019-05-24 14:08:22', '5212312'),
(44, 7, 'D6', '2019-05-24 14:39:38', '886543'),
(45, 11, 'B6', '2019-05-24 14:42:41', '77822324'),
(46, 11, 'B7', '2019-05-24 14:42:41', '77822324'),
(47, 11, 'A7', '2019-05-24 14:42:41', '77822324'),
(48, 11, 'A3', '2019-05-24 14:42:41', '77822324'),
(49, 7, 'F6', '2019-05-24 15:09:09', '2323232'),
(50, 7, 'G6', '2019-05-24 15:09:09', '2323232'),
(51, 7, 'E1', '2019-05-24 15:12:04', '2323232'),
(52, 7, 'F4', '2019-05-24 15:19:26', '641232'),
(53, 7, 'F3', '2019-05-24 15:19:26', '641232'),
(55, 7, 'E5', '2019-05-24 16:54:00', '2323222'),
(56, 7, 'E6', '2019-05-24 16:54:13', '33321122'),
(57, 4, 'A1', '2019-05-26 09:23:01', '65623434'),
(58, 4, 'C3', '2019-05-26 09:27:58', '434333'),
(59, 4, 'B6', '2019-05-26 09:49:13', '343433'),
(60, 4, 'B5', '2019-05-26 09:49:13', '343433'),
(61, 4, 'A18', '2019-05-26 09:50:38', '34341232'),
(62, 4, 'A19', '2019-05-26 09:53:16', '4535345'),
(63, 4, 'A17', '2019-05-26 09:53:16', '4535345'),
(64, 4, 'A16', '2019-05-26 09:53:16', '4535345'),
(65, 4, 'A20', '2019-05-26 09:53:16', '4535345'),
(66, 4, 'F12', '2019-05-26 09:53:48', '2323123'),
(67, 4, 'F11', '2019-05-26 09:53:48', '2323123'),
(68, 4, 'F10', '2019-05-26 09:53:48', '2323123'),
(69, 4, 'C7', '2019-05-26 10:08:12', '7542123'),
(72, 4, 'A9', '2019-05-26 11:37:28', '6666333'),
(73, 4, 'A12', '2019-05-26 11:42:31', '43434343'),
(74, 4, 'B11', '2019-05-26 11:42:31', '43434343'),
(75, 4, 'C13', '2019-05-26 11:47:56', '5666555'),
(76, 4, 'C16', '2019-05-26 11:48:05', '8888888'),
(77, 4, 'A13', '2019-05-26 11:51:43', '2222222'),
(78, 4, 'C20', '2019-05-26 11:52:00', '776662'),
(79, 4, 'A15', '2019-05-26 11:54:49', '223232323'),
(82, 4, 'E16', '2019-05-26 12:09:44', '888887'),
(83, 4, 'D9', '2019-05-26 12:09:57', '541232'),
(84, 4, 'A6', '2019-05-26 12:14:06', '3215123'),
(85, 4, 'A8', '2019-05-26 12:14:29', '663212'),
(86, 4, 'A3', '2019-05-26 12:14:51', '78323123'),
(87, 4, 'A4', '2019-05-26 12:14:51', '78323123'),
(90, 7, 'A2', '2019-05-26 12:53:16', '434344'),
(91, 7, 'C6', '2019-05-26 12:53:24', '434343443'),
(92, 7, 'B1', '2019-05-26 12:54:12', '343432424'),
(93, 7, 'H1', '2019-05-26 13:20:12', '6767676'),
(94, 7, 'H2', '2019-05-26 13:20:23', '4545454'),
(95, 7, 'G1', '2019-05-26 13:20:23', '4545454'),
(96, 7, 'H6', '2019-05-26 13:24:46', '4535345'),
(97, 7, 'H4', '2019-05-26 14:17:51', '3231222'),
(98, 2, 'A2', '2019-05-26 14:33:02', '231323'),
(99, 2, 'A1', '2019-05-26 14:33:13', '2323213'),
(100, 2, 'B1', '2019-05-26 14:33:13', '2323213'),
(101, 2, 'C1', '2019-05-26 14:33:13', '2323213'),
(102, 2, 'E1', '2019-05-26 14:33:24', '313123123'),
(104, 2, 'D7', '2019-05-26 14:33:24', '313123123'),
(109, 7, 'F5', '2019-05-26 15:40:47', '432434'),
(111, 1, 'M18', '2019-05-26 15:43:15', '312313'),
(112, 1, 'M19', '2019-05-26 15:43:15', '312313'),
(113, 4, 'E19', '2019-05-26 17:49:31', '445454'),
(114, 4, 'E20', '2019-05-26 17:49:31', '445454'),
(115, 7, 'D3', '2019-05-27 14:26:30', '4234234'),
(116, 7, 'D1', '2019-05-27 14:28:10', '212312'),
(117, 14, 'B4', '2019-05-30 23:50:21', '343434324'),
(118, 14, 'D3', '2019-05-30 23:50:28', '23232111'),
(119, 14, 'C1', '2019-05-30 23:50:28', '23232111'),
(120, 14, 'C2', '2019-05-30 23:50:28', '23232111'),
(121, 14, 'D8', '2019-05-30 23:50:38', '512312312'),
(122, 14, 'C8', '2019-05-30 23:50:38', '512312312'),
(123, 14, 'D6', '2019-05-30 23:50:38', '512312312'),
(124, 14, 'C7', '2019-05-30 23:50:38', '512312312'),
(125, 14, 'A8', '2019-05-31 00:20:59', '3123123'),
(126, 14, 'A4', '2019-05-31 00:21:16', '8786785'),
(127, 14, 'D7', '2019-05-31 00:21:40', '11111111'),
(128, 19, 'B8', '2019-05-31 00:37:45', '4324234'),
(129, 19, 'B6', '2019-05-31 00:37:45', '4324234'),
(130, 19, 'A9', '2019-05-31 00:40:29', '123123'),
(131, 19, 'C10', '2019-05-31 00:40:29', '123123'),
(132, 19, 'C9', '2019-05-31 00:40:29', '123123'),
(133, 19, 'D12', '2019-05-31 00:42:55', '365123123'),
(134, 19, 'E9', '2019-05-31 00:42:55', '365123123'),
(135, 19, 'E8', '2019-05-31 00:42:55', '365123123'),
(136, 3, 'B12', '2019-05-31 00:58:10', '12312312'),
(137, 3, 'B9', '2019-05-31 00:58:10', '12312312'),
(138, 3, 'D9', '2019-05-31 00:59:26', '123123'),
(139, 3, 'D8', '2019-05-31 00:59:26', '123123'),
(140, 3, 'D7', '2019-05-31 00:59:26', '123123'),
(141, 3, 'A6', '2019-05-31 01:02:25', '123123123'),
(142, 3, 'A7', '2019-05-31 01:02:25', '123123123'),
(143, 3, 'F11', '2019-05-31 01:04:39', '7887889'),
(144, 15, 'C4', '2019-05-31 01:47:51', '123123'),
(145, 15, 'C3', '2019-05-31 01:47:51', '123123'),
(146, 15, 'A6', '2019-05-31 01:47:58', '23231111'),
(147, 15, 'B6', '2019-05-31 01:47:58', '23231111'),
(148, 15, 'A12', '2019-05-31 01:56:53', '232323'),
(149, 15, 'B10', '2019-05-31 01:56:53', '232323'),
(150, 15, 'C8', '2019-05-31 01:58:07', '6123123'),
(151, 17, 'A8', '2019-05-31 04:39:59', '656565'),
(152, 17, 'B6', '2019-05-31 04:39:59', '656565'),
(153, 8, 'A11', '2019-06-02 22:37:29', '3123123'),
(154, 20, 'B5', '2019-06-03 00:26:02', '2313123'),
(155, 20, 'C4', '2019-06-03 00:26:02', '2313123'),
(156, 20, 'E7', '2019-06-03 01:39:06', '3213232'),
(157, 7, 'D4', '2019-06-03 01:40:26', '123131231');


--
-- Dumping data for table `terces`
--

INSERT INTO `terces` (`nimda`, `drowssap`) VALUES
(1, '12345');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
