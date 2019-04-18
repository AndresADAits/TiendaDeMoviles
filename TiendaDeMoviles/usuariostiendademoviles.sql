-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 18-04-2019 a las 14:22:33
-- Versión del servidor: 8.0.15
-- Versión de PHP: 7.2.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `usuariostiendademoviles`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasesdeusuario`
--

CREATE TABLE `clasesdeusuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clasesdeusuario`
--

INSERT INTO `clasesdeusuario` (`id`, `nombre`) VALUES
(1, 'Vendedor'),
(2, 'Supervisor'),
(3, 'Gerente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `last_session` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `password`, `nombre`, `correo`, `id_tipo`) VALUES
(10, 'prueba', '711383a59fda05336fd2ccf70c8059d1523eb41a', 'prueba', 'prueba@gmail.com', 1),
(11, 'andres', '883768b6dd2c42aea0031b24be8a2da40fef4b64', 'andres', 'andres@gmail.com', 2),
(12, 'AGAPITO', '84cbe16c5e288249e7a59be11cbe599728c9dddf', 'AGAPITO', 'AGAPITO@GMAIL.COM', 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clasesdeusuario`
--
ALTER TABLE `clasesdeusuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clasesdeusuario`
--
ALTER TABLE `clasesdeusuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
