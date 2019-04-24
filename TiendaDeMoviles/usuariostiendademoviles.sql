-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 24-04-2019 a las 14:41:31
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
  `bateria` int(11) NOT NULL,
  `camara` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`idmovil`, `stock`, `marca`, `modelo`, `precio`, `capacidad`, `pantalla`, `bateria`, `camara`) VALUES
(1, 100, 'SAMSUNG', 'S10', 1183, 512, '6.4', 4100, 16),
(2, 200, 'APPLE', 'XS_MAX', 1399, 256, '5.8', 4000, 12),
(3, 300, 'XIAOMI', 'REDMI_NOTE7', 249, 64, '6.3', 4000, 48),
(4, 300, 'HUAWEI', 'P20_LITE', 289, 64, '5.8', 3000, 16),
(5, 200, 'HUAWEI', 'P30_PRO8', 949, 128, '6.2', 4000, 40),
(6, 50, 'APPLE', 'IPHONE 8', 599, 64, '4.9', 3100, 8),
(7, 156, 'XIAOMI', 'MI8_PRO', 519, 128, '6.9', 4300, 58),
(8, 654, 'XIAOMI', 'MI9', 499, 128, '6.8', 4500, 56),
(9, 20, 'HONOR', 'VIEW_20', 530, 128, '7.2', 4200, 51),
(10, 910, 'HONOR', 'VIEW_10', 949, 128, '6.7', 3800, 38),
(11, 140, 'SAMSUNG', 'GALAXY_NOTE9', 1099, 512, '9.9', 8000, 54),
(12, 90, 'SAMSUNG', 'GALAXY_J3', 129, 16, '5.2', 1300, 8),
(13, 209, 'SAMSUNG', 'GALAXY_M20', 229, 64, '6.2', 3200, 16),
(14, 6, 'APPLE', 'IPHONE_5S', 139, 32, '4.9', 2800, 15),
(15, 987, 'APPLE', 'IPHONE_7', 599, 64, '5.9', 3100, 18),
(16, 87, 'APPLE', 'IPHONE_6S', 299, 32, '5.2', 2600, 16),
(17, 87, 'BQ', 'AQUARIS_C', 119, 16, '5.2', 2600, 16),
(18, 7, 'RAZER', 'PHONE_8', 499, 65, '6.7', 4200, 41),
(19, 8, 'BQ', 'AQUARIS_X2', 389, 64, '6.8', 3600, 32),
(20, 35, 'GOOGLE', 'PIXEL_XL', 274, 32, '6.2', 3700, 32),
(21, 952, 'ZTE', 'BLADE_V580', 136, 32, '6.1', 3100, 14),
(22, 741, 'BQ', 'AQUARIS', 109, 16, '5.2', 2600, 12),
(23, 49, 'ZTE', 'BLADE_V8LITE', 84, 16, '4.8', 1800, 8),
(24, 149, 'GOOGLE', 'NEXUS_5X', 172, 32, '5.2', 2800, 19),
(25, 68, 'SONY', 'XPERIA_10', 345, 64, '6.8', 4200, 47),
(26, 9, 'ASUS', 'ZENFONE_4', 223, 64, '5.9', 3800, 46),
(27, 7, 'HISENSE', 'C30', 153, 16, '5.8', 3800, 15),
(28, 2, 'LG', 'G4S', 245, 64, '5.8', 4200, 51),
(29, 1, 'SONY', 'XPERIA_10PLUS', 784, 64, '6.8', 4800, 58);

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
(12, 'AGAPITO', '84cbe16c5e288249e7a59be11cbe599728c9dddf', 'AGAPITO', 'AGAPITO@GMAIL.COM', 3),
(13, 'Gabriel', 'aaac72d4824650c176db2fc2451be237f164ea98', 'Gabriel', 'Gabriel@Gabriel.COM', 2);

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
  MODIFY `idmovil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
