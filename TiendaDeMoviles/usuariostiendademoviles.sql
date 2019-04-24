-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3309
-- Tiempo de generación: 24-04-2019 a las 07:04:30
-- Versión del servidor: 5.6.42
-- Versión de PHP: 5.6.40

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
-- Estructura de tabla para la tabla `stock`
--

CREATE TABLE `stock` (
  `idmovil` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `marca` varchar(15) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `precio` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `pantalla` decimal(2,1) NOT NULL,
  `bateria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`idmovil`, `stock`, `marca`, `modelo`, `precio`, `capacidad`, `pantalla`, `bateria`) VALUES
(1, 100, 'SAMSUNG', 'S10', 1183, 512, '6.4', 4100),
(2, 200, 'APPLE', 'XS_MAX', 1399, 256, '5.8', 4000),
(3, 300, 'XIAOMI', 'REDMI_NOTE7', 249, 64, '6.3', 4000),
(4, 300, 'HUAWEI', 'P20_LITE', 289, 64, '5.8', 3000);

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

INSERT INTO `usuario` (`id`, `usuario`, `password`, `nombre`, `correo`, `last_session`, `id_tipo`) VALUES
(10, 'prueba', '711383a59fda05336fd2ccf70c8059d1523eb41a', 'prueba', 'prueba@gmail.com', '2019-04-22 13:38:06', 1),
(11, 'andres', '883768b6dd2c42aea0031b24be8a2da40fef4b64', 'andres', 'andres@gmail.com', '2019-04-22 13:38:06', 2),
(12, 'AGAPITO', '84cbe16c5e288249e7a59be11cbe599728c9dddf', 'AGAPITO', 'AGAPITO@GMAIL.COM', '2019-04-22 16:12:38', 3),
(13, 'Gabriel', 'aaac72d4824650c176db2fc2451be237f164ea98', 'Gabriel', 'Gabriel@Gabriel.COM', '2019-04-24 15:35:03', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clasesdeusuario`
--
ALTER TABLE `clasesdeusuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`idmovil`);

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
-- AUTO_INCREMENT de la tabla `stock`
--
ALTER TABLE `stock`
  MODIFY `idmovil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
