-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2017 at 08:34 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banquedatabase`
--
CREATE DATABASE IF NOT EXISTS `banquedatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `banquedatabase`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `number` varchar(250) NOT NULL,
  `creationDate` date NOT NULL,
  `initialBalance` double(16,2) NOT NULL,
  `interestRate` double(4,2) NOT NULL,
  `overdraft` double(16,2) NOT NULL,
  `alertThreshold` double(16,2) DEFAULT NULL,
  `agioRate` double(4,2) NOT NULL,
  `idCountryCode` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `idAccountType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `description`, `number`, `creationDate`, `initialBalance`, `interestRate`, `overdraft`, `alertThreshold`, `agioRate`, `idCountryCode`, `idAgency`, `idAccountType`) VALUES
(1, 'compte crée par POST', '058516851', '2017-04-05', 0.00, 2.25, 0.00, 0.00, 0.00, 3, 1, 3),
(2, 'un compte epargne', '1111 2222 3333', '2017-03-20', 2000.00, 10.00, -100.00, 100.00, 0.00, 2, 1, 2),
(3, 'un compte courant', '4321 4321 4321', '2017-04-01', 152.45, 0.00, 0.00, NULL, 0.00, 3, 1, 3),
(4, 'qwe', '058516851', '2017-04-05', 0.00, 2.25, 0.00, NULL, 0.00, 3, 1, 3),
(6, 'c\'est un compte', '0585146851', '2017-04-05', 0.00, 0.00, 0.00, NULL, 0.00, 3, 2, 3),
(7, 'rryuk', '45645645', '2017-04-04', 44.00, 0.00, 0.00, NULL, 0.00, 2, 3, 2),
(8, 'rtgrtyu', '6496', '2017-04-19', 1.00, 0.00, 0.00, NULL, 0.00, 1, 2, 3),
(9, 'zrtyzrty', '9879984987', '2017-04-05', 0.00, 0.00, 0.00, NULL, 0.00, 2, 4, 2),
(10, 'try', '45624(', '2017-01-01', 100.00, 2.25, -500.00, 10.00, 0.00, 1, 2, 2),
(12, 'try2', '34567', '2017-01-01', 100.00, 2.00, -100.00, 12.00, 15.00, 2, 3, 2),
(13, 'cpt', '1234 1234 1235', '2017-04-01', 1900.00, 2.25, 0.00, 10.00, 0.00, 1, 3, 1),
(14, 'qwewdsf', '8796451', '2017-05-01', -100.00, 0.00, 0.00, 0.00, 0.00, 3, 5, 1),
(15, 'qwe', '058516851', '2017-04-05', 0.00, 2.25, 0.00, 0.00, 0.00, 3, 1, 3),
(16, 'compte crée par POST', '058516851', '2017-04-05', 0.00, 2.25, 0.00, 0.00, 0.00, 3, 1, 3),
(17, 'compte crée par POST', '058516851', '2017-04-05', 0.00, 2.25, 0.00, 0.00, 0.00, 3, 1, 3),
(18, '987984654awdsa', '4545', '2017-06-01', 112254.00, 0.00, 0.00, 0.00, 0.00, 6, 6, 4);

-- --------------------------------------------------------

--
-- Table structure for table `accounttype`
--

CREATE TABLE `accounttype` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounttype`
--

INSERT INTO `accounttype` (`id`, `type`) VALUES
(1, 'epargne'),
(2, 'livret A'),
(3, 'courant'),
(4, 'X');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `idCpVille` int(11) NOT NULL,
  `line1` varchar(250) NOT NULL,
  `line2` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `idCpVille`, `line1`, `line2`) VALUES
(1, 1, '114 rue Lucien Faure', NULL),
(2, 2, '1 rue truc', NULL),
(3, 1, '12 rue machin', 'batiment B\r\n'),
(5, 5, 'lu', ''),
(6, 6, 'qwe', ''),
(11, 11, 'somewhere', ''),
(12, 12, 'sdfgsdfg', ''),
(13, 13, 'd', ''),
(14, 14, 'rtuyityui', ''),
(15, 15, 'zrtyzrty', ''),
(16, 16, 'dd', ''),
(17, 17, 'qwerfg', ''),
(18, 18, 'asrdfgh', ''),
(19, 19, 'asert', ''),
(20, 23, 'XXX', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `advisor`
--

CREATE TABLE `advisor` (
  `id` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `assignmentDate` date NOT NULL,
  `phoneNumber` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advisor`
--

INSERT INTO `advisor` (`id`, `idAgency`, `name`, `firstName`, `assignmentDate`, `phoneNumber`, `email`) VALUES
(1, 1, 'Mddb', 'Mme', '2017-03-09', '0909090908', 'dfdfgh@hj.ghj'),
(3, 2, 'Sdffg', 'Sdffgdfg', '2017-04-03', '0765656565', 'sdffgsdf@dssdfg.om');

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE `agency` (
  `id` int(11) NOT NULL,
  `idAddress` int(11) NOT NULL,
  `idBank` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `counterCode` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agency`
--

INSERT INTO `agency` (`id`, `idAddress`, `idBank`, `name`, `counterCode`) VALUES
(1, 2, 1, 'hshdjne', '12345'),
(2, 3, 2, 'agence locale', '12345'),
(3, 14, 5, 'lo', 'lo'),
(4, 15, 8, 'zrtyzrtyzrt', 'zrtyzrt'),
(5, 19, 10, 'asd', '897'),
(6, 20, 11, 'Xx', '000');

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `main` varchar(250) DEFAULT NULL,
  `idOwner` int(11) NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assign`
--

INSERT INTO `assign` (`main`, `idOwner`, `idAccount`) VALUES
('askdfj', 3, 2),
('', 4, 1),
('askdfsj', 4, 4),
('', 4, 6),
(NULL, 4, 7),
(NULL, 4, 8),
(NULL, 4, 9),
(NULL, 4, 10),
(NULL, 4, 12),
(NULL, 4, 14);

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `name`, `code`) VALUES
(1, 'credit truc', '12345'),
(2, 'lionaise machin', '54321'),
(3, 'ryerty', '56756'),
(4, 'qsdfqsdf', '46464'),
(5, 'azer', '456'),
(6, 'qsdf', 'qsdf'),
(7, 'qsdf', 'qsdf'),
(8, 'fghdfgh', '654645'),
(9, 'tzeer', '34566'),
(10, 'qweqwe', '874'),
(11, 'XXX', '000');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `idParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `description`, `idParentCategory`) VALUES
(1, 'ma cate generique', NULL),
(2, 'alimentaire', NULL),
(15, 'Sport', 1),
(18, 'Velo', 15),
(19, 'Cinema', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `countrycode`
--

CREATE TABLE `countrycode` (
  `id` int(11) NOT NULL,
  `code` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `countrycode`
--

INSERT INTO `countrycode` (`id`, `code`) VALUES
(1, 'FR'),
(2, 'BE'),
(3, 'EN'),
(4, 'US'),
(5, 'ES'),
(6, 'XX');

-- --------------------------------------------------------

--
-- Table structure for table `cpville`
--

CREATE TABLE `cpville` (
  `id` int(11) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `city` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cpville`
--

INSERT INTO `cpville` (`id`, `zip`, `city`) VALUES
(1, '75000', 'Paris'),
(2, '33000', 'Bordeaux\r\n'),
(5, 'lu', 'Lu'),
(6, '001', 'Bordeaux'),
(11, '33000', 'Bordeaux'),
(12, 'sdfgsdfg', 'Sdffgsdffg'),
(13, 'd', 'D'),
(14, '9874987', 'Tyuityuity'),
(15, 'zrtyzry', 'Zrtyzrty'),
(16, '23455', 'Dd'),
(17, '6894', 'Qwfdgnh'),
(18, '9875', 'Qweqwe'),
(19, '878', 'Qwer'),
(20, '75000', 'Paris'),
(21, '75000', 'Hello'),
(22, '34577', 'Somewhere'),
(23, '000', 'XXX');

-- --------------------------------------------------------

--
-- Table structure for table `frequency`
--

CREATE TABLE `frequency` (
  `id` int(11) NOT NULL,
  `unit` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `frequency`
--

INSERT INTO `frequency` (`id`, `unit`) VALUES
(1, 'hebdomadaire'),
(2, 'mensuelle'),
(3, 'anuelle');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `phoneNumber` varchar(250) NOT NULL,
  `birthday` date NOT NULL,
  `login` varchar(250) NOT NULL,
  `pswd` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `idAddress` int(11) NOT NULL,
  `salt` varchar(250) NOT NULL,
  `newUser` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`id`, `name`, `firstName`, `phoneNumber`, `birthday`, `login`, `pswd`, `email`, `idAddress`, `salt`, `newUser`) VALUES
(1, 'Doe', 'John', '0000000000', '1986-07-28', 'mylogin', '62a5daea27481816ef8959019c78efa84d693dd3', '', 1, '', 0),
(2, 'lu', 'lu', '0678787878', '2017-03-27', 'lu', 'lu', 'lu@lu.lu', 5, 'dfsgdhf', 0),
(3, 'qwe', 'qwe', '0000000000', '2017-04-04', 'qwe', '+/yVtz/FxkMb8qgnAP3twdFbcqffZzkqGj9MBQlRZVU=', 'q@w.fr', 6, '/UE4Aq1qFBU=', 0),
(4, 'myname', 'myFirstName', '+33612345678', '2017-04-01', 'defaultUser', 'AOV1u5aSyu7dXvpDCJQdCyMp4Fo6Ut8V6LEqoz2nRYo=', 'default@truc.com', 11, '1HoGuIfDKgA=', 0),
(5, 'Dsfgsdffg', 'Sdffgsdffg', '0988888888', '2017-04-04', 'qgshdfj', 'cVUmnoGgh1ok0UyVhoLm9k2juaCbsdMnZKcNRn0U/L4=', 'd@d.dd', 12, 'XZIBN8WNdgY=', 0),
(6, 'D', 'D', '0945454545', '2017-04-05', 'd', 'Ywh/mjFaunQjAnR8NKkvXpnLKSOft0xX57DXNIuJSkE=', 'd@d.dd', 13, 'CnRSFLXvpLY=', 0),
(7, 'Asd', 'Asd', '0512345678', '2017-05-02', 'q', 'YsUmP1K1jXzVPQeaD8THdGJmCrFkcA5G+JuEbJjIPjY=', 'qwe@wer.com', 17, '5JUG7TcJZH8=', 1),
(8, 'Aswee', 'Asd', '0512345678', '2017-05-16', 'qqq', 'QxZR+4AihaD8oCtBPXeBNDhNXchNbjH9MMCr7Cuu9w8=', 'epsi@yopmail.com', 18, '9M7DJNf0jDA=', 0);

-- --------------------------------------------------------

--
-- Table structure for table `periodictransaction`
--

CREATE TABLE `periodictransaction` (
  `id` int(11) NOT NULL,
  `endDate` date NOT NULL,
  `numberDefiningPeriodicity` int(11) NOT NULL,
  `idFreq` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodictransaction`
--

INSERT INTO `periodictransaction` (`id`, `endDate`, `numberDefiningPeriodicity`, `idFreq`) VALUES
(1, '2017-04-29', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `targettransaction`
--

CREATE TABLE `targettransaction` (
  `id` int(11) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `iban` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `targettransaction`
--

INSERT INTO `targettransaction` (`id`, `summary`, `iban`) VALUES
(1, 'un destinataire', 'FR7630004000031234567890143'),
(2, 'un autre destinataire', 'FR7630001007941234567890185');

-- --------------------------------------------------------

--
-- Table structure for table `test_table`
--

CREATE TABLE `test_table` (
  `id` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test_table`
--

INSERT INTO `test_table` (`id`, `value`) VALUES
(1, 5),
(2, 57),
(3, 789),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `value` double(16,2) NOT NULL,
  `dateTransaction` date NOT NULL,
  `idAccount` int(11) NOT NULL,
  `idTransactionType` int(11) NOT NULL,
  `idTargetTransaction` int(11) DEFAULT NULL,
  `idCategory` int(11) DEFAULT NULL,
  `idPeriodicTransaction` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `description`, `value`, `dateTransaction`, `idAccount`, `idTransactionType`, `idTargetTransaction`, `idCategory`, `idPeriodicTransaction`) VALUES
(2, 'un retrait', -150.00, '2017-04-03', 2, 1, 1, 1, NULL),
(20, 'j', 100.00, '2017-03-24', 1, 1, 1, NULL, NULL),
(21, 'TR1', 344.00, '2017-03-28', 6, 4, NULL, NULL, NULL),
(22, 'TR2', 6554.00, '2017-05-06', 6, 5, NULL, NULL, NULL),
(23, 'TR2', -3444.00, '2017-03-28', 6, 1, NULL, NULL, NULL),
(24, 'TR4', -5.00, '2017-04-01', 6, 4, NULL, NULL, NULL),
(25, 'TR5', 333.00, '2017-03-29', 6, 1, NULL, NULL, NULL),
(33, 'df', 99.00, '2017-04-11', 6, 4, NULL, 15, NULL),
(36, 'zdfg', 88.00, '2017-04-15', 1, 4, NULL, 18, NULL),
(37, 'df', 3.00, '2017-04-16', 1, 4, NULL, 19, NULL),
(38, 'balancetest', -7.00, '2017-05-01', 1, 1, NULL, 15, NULL),
(39, 'qsdsdf', -800.00, '2017-06-01', 1, 1, NULL, NULL, NULL),
(40, 'sdfsdf', 9.00, '2017-05-03', 1, 4, NULL, NULL, NULL),
(42, 'RR', 100.00, '2017-01-16', 4, 1, NULL, NULL, NULL),
(43, 'erty', 800.00, '2017-05-11', 1, 1, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transactiontype`
--

CREATE TABLE `transactiontype` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactiontype`
--

INSERT INTO `transactiontype` (`id`, `description`) VALUES
(1, 'je sais plus ce que c\'est\r\n'),
(4, 'virement interne'),
(5, 'cheque');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_acc_acc` (`idAccountType`),
  ADD KEY `FK_acc_coun` (`idCountryCode`),
  ADD KEY `FK_acc_agen` (`idAgency`);

--
-- Indexes for table `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_AD_IDcpvi` (`idCpVille`);

--
-- Indexes for table `advisor`
--
ALTER TABLE `advisor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ADv_idagen` (`idAgency`);

--
-- Indexes for table `agency`
--
ALTER TABLE `agency`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ag_idad` (`idAddress`),
  ADD KEY `FK_ag_idban` (`idBank`);

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`idOwner`,`idAccount`),
  ADD KEY `FK_ass_accou` (`idAccount`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cat_wor` (`idParentCategory`);

--
-- Indexes for table `countrycode`
--
ALTER TABLE `countrycode`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cpville`
--
ALTER TABLE `cpville`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `frequency`
--
ALTER TABLE `frequency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idadress` (`idAddress`);

--
-- Indexes for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_per_freq` (`idFreq`);

--
-- Indexes for table `targettransaction`
--
ALTER TABLE `targettransaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test_table`
--
ALTER TABLE `test_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pt_acc` (`idAccount`),
  ADD KEY `FK_pt_trtyp` (`idTransactionType`),
  ADD KEY `FK_pt_tartr` (`idTargetTransaction`),
  ADD KEY `FK_pt_cat` (`idCategory`),
  ADD KEY `idPeriodicTransaction` (`idPeriodicTransaction`);

--
-- Indexes for table `transactiontype`
--
ALTER TABLE `transactiontype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `advisor`
--
ALTER TABLE `advisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `countrycode`
--
ALTER TABLE `countrycode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `cpville`
--
ALTER TABLE `cpville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `frequency`
--
ALTER TABLE `frequency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `targettransaction`
--
ALTER TABLE `targettransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `test_table`
--
ALTER TABLE `test_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_acc_acc` FOREIGN KEY (`idAccountType`) REFERENCES `accounttype` (`id`),
  ADD CONSTRAINT `FK_acc_agen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`),
  ADD CONSTRAINT `FK_acc_coun` FOREIGN KEY (`idCountryCode`) REFERENCES `countrycode` (`id`);

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_AD_IDcpvi` FOREIGN KEY (`idCpVille`) REFERENCES `cpville` (`id`);

--
-- Constraints for table `advisor`
--
ALTER TABLE `advisor`
  ADD CONSTRAINT `FK_ADv_idagen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`);

--
-- Constraints for table `agency`
--
ALTER TABLE `agency`
  ADD CONSTRAINT `FK_ag_idad` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK_ag_idban` FOREIGN KEY (`idBank`) REFERENCES `bank` (`id`);

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `FK_ass_accou` FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_ass_own` FOREIGN KEY (`idOwner`) REFERENCES `owner` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_cat_wor` FOREIGN KEY (`idParentCategory`) REFERENCES `category` (`id`);

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `FK_own_add` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`);

--
-- Constraints for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD CONSTRAINT `FK_per_freq` FOREIGN KEY (`idFreq`) REFERENCES `frequency` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_pt_acc` FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_pt_cat` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_pt_tartr` FOREIGN KEY (`idTargetTransaction`) REFERENCES `targettransaction` (`id`),
  ADD CONSTRAINT `FK_pt_trtyp` FOREIGN KEY (`idTransactionType`) REFERENCES `transactiontype` (`id`),
  ADD CONSTRAINT `FK_tr_pt` FOREIGN KEY (`idPeriodicTransaction`) REFERENCES `periodictransaction` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
