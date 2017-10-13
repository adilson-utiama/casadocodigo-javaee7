# casadocodigo-javaee7
Projeto usando recursos do JavaEE 7

#servidor de aplicação
-Wildfly 10.0.0-FINAL

#BD
-MySql

############################DUMP
casadocodigo_javaee.sql

-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 13-Out-2017 às 03:53
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `casadocodigo_javaee`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE `autor` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `autor`
--

INSERT INTO `autor` (`id`, `nome`) VALUES
(1, 'Paulo Silveira'),
(2, 'Guilherme Silveira'),
(3, 'Sergio Lopes'),
(4, 'Rodrigo Turini'),
(5, 'Alberto Souza');

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

CREATE TABLE `livro` (
  `id` int(11) NOT NULL,
  `descricao` longtext,
  `numeroPaginas` int(11) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `dataPublicacao` date DEFAULT NULL,
  `capaPath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`id`, `descricao`, `numeroPaginas`, `preco`, `titulo`, `dataPublicacao`, `capaPath`) VALUES
(5, 'Livro de Java 8', 250, '30.00', 'Java 8 Pratico', NULL, 'livros/java8-pratico.png'),
(6, 'Certificacao Java Programmer 8', 290, '30.00', 'Certificacao Java Programmer 8', NULL, 'livros/certificacao-java.png'),
(7, 'Livro de OAuth 2.0', 400, '30.00', 'OAuth 2.0', NULL, 'livros/oauth2.jpg'),
(8, 'Livro de CodeIgniter', 250, '35.00', 'CodeIgniter', NULL, 'livros/codeigniter.jpeg'),
(9, 'Livro de Webservices', 350, '35.00', 'WEbservices', '2017-10-11', 'livros/websecurity.jpg'),
(10, 'livro de Angular 4', 300, '30.00', 'Angular 4', '2016-06-10', 'livros/angular.jpg'),
(11, 'Cangaceiro Javascript', 300, '30.00', 'Cangaceiro Javascript', '2016-06-10', 'livros/cangaceiro_javascript.jpg'),
(12, 'web semantica', 300, '35.00', 'Web sematica', '2016-12-12', 'livros/web_semantica.jpg'),
(13, 'livro de Spring MVC', 250, '35.00', 'Spring MVC', '2016-06-10', 'livros/spring-mvc.png'),
(14, 'Livro de Java 9', 256, '30.00', 'Java 9', '2017-06-11', 'livros/java9.png');

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro_autor`
--

CREATE TABLE `livro_autor` (
  `Livro_id` int(11) NOT NULL,
  `autores_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livro_autor`
--

INSERT INTO `livro_autor` (`Livro_id`, `autores_id`) VALUES
(5, 1),
(5, 4),
(6, 4),
(7, 2),
(8, 3),
(9, 2),
(10, 5),
(11, 2),
(12, 4),
(13, 2),
(14, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livro_autor`
--
ALTER TABLE `livro_autor`
  ADD KEY `FKm8wd9po92cbxh5rsa9c22ben` (`autores_id`),
  ADD KEY `FKkg7c8c8lgwq3mkospcmupp706` (`Livro_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `livro`
--
ALTER TABLE `livro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `livro_autor`
--
ALTER TABLE `livro_autor`
  ADD CONSTRAINT `FKkg7c8c8lgwq3mkospcmupp706` FOREIGN KEY (`Livro_id`) REFERENCES `livro` (`id`),
  ADD CONSTRAINT `FKm8wd9po92cbxh5rsa9c22ben` FOREIGN KEY (`autores_id`) REFERENCES `autor` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

