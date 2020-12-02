-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 23, 2019 at 05:26 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `htdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE `assessment` (
  `assessment_id` int(11) NOT NULL,
  `create_date` date DEFAULT NULL,
  `inspetion_detail` varchar(250) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `assessment_detail` varchar(250) DEFAULT NULL,
  `inspection_id` int(11) DEFAULT NULL,
  `criterion_total` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`assessment_id`, `create_date`, `inspetion_detail`, `user_id`, `assessment_detail`, `inspection_id`, `criterion_total`) VALUES
(1, '2019-08-23', 'A', 2, 'bbbb', 1, '2'),
(2, '2019-08-23', 'A', 2, 'sssss', 1, '0');

-- --------------------------------------------------------

--
-- Table structure for table `choice`
--

CREATE TABLE `choice` (
  `choice_id` int(11) NOT NULL,
  `choice_name` varchar(100) DEFAULT NULL,
  `choice_criterion` varchar(30) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `choice`
--

INSERT INTO `choice` (`choice_id`, `choice_name`, `choice_criterion`, `question_id`, `status`) VALUES
(1, 'A', '1', 1, 'A'),
(2, 'B', '0', 1, 'A'),
(3, 'A', '1', 2, 'A'),
(4, 'B', '0', 2, 'A'),
(5, 'A', '1', 3, 'A'),
(6, 'B', '0', 3, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `criterion`
--

CREATE TABLE `criterion` (
  `criterion_id` int(11) NOT NULL,
  `criterion_start` int(8) DEFAULT NULL,
  `criterion_end` int(8) DEFAULT NULL,
  `criterion_detail` varchar(100) DEFAULT NULL,
  `inspection_id` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `criterion`
--

INSERT INTO `criterion` (`criterion_id`, `criterion_start`, `criterion_end`, `criterion_detail`, `inspection_id`, `status`) VALUES
(1, 0, 1, 'sssss', 1, 'I'),
(2, 2, 3, 'bbbb', 1, 'I'),
(3, 0, 1, 'sssss', 1, 'I'),
(4, 2, 3, 'bbbb', 1, 'I'),
(5, 4, 5, 'zzz', 1, 'I'),
(6, 0, 1, 'sssss', 1, 'A'),
(7, 2, 3, 'bbbb', 1, 'A'),
(8, 4, 5, 'zzz', 1, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `inspection`
--

CREATE TABLE `inspection` (
  `inspection_id` int(11) NOT NULL,
  `inspection_name` varchar(100) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `inspection`
--

INSERT INTO `inspection` (`inspection_id`, `inspection_name`, `status`) VALUES
(1, 'A', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `menu_main`
--

CREATE TABLE `menu_main` (
  `menu_id` int(11) NOT NULL,
  `menu_name_en` varchar(100) DEFAULT NULL,
  `menu_name_th` varchar(100) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `order_no` varchar(2) DEFAULT NULL,
  `role` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menu_main`
--

INSERT INTO `menu_main` (`menu_id`, `menu_name_en`, `menu_name_th`, `menu_url`, `order_no`, `role`) VALUES
(1, 'จัดการข้อมูลผู้สูงอายุ', 'จัดการข้อมูลผู้สูงอายุ', NULL, '1', '1'),
(2, 'จัดการข้อมูลบุคลากร', 'จัดการข้อมูลบุคลากร', NULL, '2', '1'),
(4, 'จัดการข้อมูลโรค', 'จัดการข้อมูลโรค', NULL, '3', '1'),
(5, 'ประเมินโรค', 'ประเมินโรค', '/form-assess/assess-disease', '2', '2'),
(6, 'แก้ไขข้อมูลส่วนตัว', 'แก้ไขข้อมูลส่วนตัว', '/form-elderly/add-elderly', '1', '2'),
(7, 'ประวัติผลการประเมิน', 'ประวัติผลการประเมิน', '/form-assess/report-all', '4', '1'),
(8, 'ประวัติผลการประเมิน', 'ประวัติผลการประเมิน', '/form-assess/report-all', '3', '3'),
(10, 'จัดการข้อมูลผู้สูงอายุ', 'จัดการข้อมูลผู้สูงอายุ', NULL, '1', '3'),
(11, 'จัดการข้อมูลโรค', 'จัดการข้อมูลโรค', NULL, '2', '3'),
(12, 'ประวัติผลการประเมิน', 'ประวัติผลการประเมิน', '/form-assess/report', '3', '2');

-- --------------------------------------------------------

--
-- Table structure for table `menu_sub`
--

CREATE TABLE `menu_sub` (
  `menu_sub_id` int(11) NOT NULL,
  `menu_sub_name_th` varchar(100) DEFAULT NULL,
  `menu_sub_name_en` varchar(100) DEFAULT NULL,
  `menu_sub_url` varchar(100) DEFAULT NULL,
  `menu_id` varchar(2) DEFAULT NULL,
  `order_no` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menu_sub`
--

INSERT INTO `menu_sub` (`menu_sub_id`, `menu_sub_name_th`, `menu_sub_name_en`, `menu_sub_url`, `menu_id`, `order_no`) VALUES
(1, 'เพิ่มข้อมูลผู้สูงอายุ', 'เพิ่มข้อมูลผู้สูงอายุ', '/form-elderly/add-elderly', '1', 1),
(2, 'ข้อมูลผู้สูงอายุ', 'ข้อมูลผู้สูงอายุ', '/form-elderly/manage-elderly', '1', 2),
(3, 'เพิ่มข้อมูลบุคลากร', 'เพิ่มข้อมูลบุคลากร', '/form-personnel/add-personnel', '2', 1),
(4, 'ข้อมูลบุคลากร', 'ข้อมูลบุคลากร', '/form-personnel/manage-personnel', '2', 2),
(5, 'ข้อมูลโรค', 'ข้อมูลโรค', '/form-assess/manage-inspection', '4', 2),
(6, 'เพิ่มข้อมูลโรค', 'เพิ่มข้อมูลโรค', '/form-assess/add-inspection', '4', 1),
(7, 'เพิ่มข้อมูลผู้สูงอายุ', 'เพิ่มข้อมูลผู้สูงอายุ', '/form-elderly/add-elderly', '10', 1),
(8, 'ข้อมูลผู้สูงอายุ', 'ข้อมูลผู้สูงอายุ', '/form-elderly/manage-elderly', '10', 2),
(9, 'ข้อมูลโรค', 'ข้อมูลโรค', '/form-assess/manage-inspection', '11', 2),
(10, 'เพิ่มข้อมูลโรค', 'เพิ่มข้อมูลโรค', '/form-assess/add-inspection', '11', 1);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `question_name` varchar(100) DEFAULT NULL,
  `inspection_id` int(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `question_name`, `inspection_id`, `status`) VALUES
(1, 'A1', 1, 'A'),
(2, 'A2', 1, 'I'),
(3, 'A2', 1, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `user_acount`
--

CREATE TABLE `user_acount` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `role` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_acount`
--

INSERT INTO `user_acount` (`user_id`, `username`, `password`, `status`, `role`) VALUES
(1, 'admin', 'admin', 'A', '1'),
(2, 'user', 'user', 'A', '2'),
(3, 'emp', 'emp', 'A', '3');

-- --------------------------------------------------------

--
-- Table structure for table `user_profile`
--

CREATE TABLE `user_profile` (
  `profile_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title_name` varchar(50) DEFAULT NULL,
  `fert_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `age` varchar(3) DEFAULT NULL,
  `phone_no` varchar(10) DEFAULT NULL,
  `card_id` varchar(13) DEFAULT NULL,
  `img` text,
  `address` varchar(250) DEFAULT NULL,
  `community` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_profile`
--

INSERT INTO `user_profile` (`profile_id`, `user_id`, `title_name`, `fert_name`, `last_name`, `age`, `phone_no`, `card_id`, `img`, `address`, `community`) VALUES
(1, 1, '', 'Admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 2, '0', '1', '', '1', '1', '1', NULL, '', '18'),
(3, 3, '1', 'emp', 'emp', '12', '0', 'emp', NULL, '123', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assessment`
--
ALTER TABLE `assessment`
  ADD PRIMARY KEY (`assessment_id`);

--
-- Indexes for table `choice`
--
ALTER TABLE `choice`
  ADD PRIMARY KEY (`choice_id`);

--
-- Indexes for table `criterion`
--
ALTER TABLE `criterion`
  ADD PRIMARY KEY (`criterion_id`);

--
-- Indexes for table `inspection`
--
ALTER TABLE `inspection`
  ADD PRIMARY KEY (`inspection_id`);

--
-- Indexes for table `menu_main`
--
ALTER TABLE `menu_main`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `menu_sub`
--
ALTER TABLE `menu_sub`
  ADD PRIMARY KEY (`menu_sub_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `user_acount`
--
ALTER TABLE `user_acount`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`profile_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assessment`
--
ALTER TABLE `assessment`
  MODIFY `assessment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `choice`
--
ALTER TABLE `choice`
  MODIFY `choice_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `criterion`
--
ALTER TABLE `criterion`
  MODIFY `criterion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `inspection`
--
ALTER TABLE `inspection`
  MODIFY `inspection_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `menu_main`
--
ALTER TABLE `menu_main`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `menu_sub`
--
ALTER TABLE `menu_sub`
  MODIFY `menu_sub_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user_acount`
--
ALTER TABLE `user_acount`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `user_profile`
--
ALTER TABLE `user_profile`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

ALTER TABLE admin_ahdbTs.question MODIFY COLUMN question_name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL;
