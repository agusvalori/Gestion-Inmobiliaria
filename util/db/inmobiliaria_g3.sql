-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-07-2022 a las 10:24:29
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inmobiliaria_g3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato_inmueble`
--

CREATE TABLE `contrato_inmueble` (
  `id_contrato` int(11) NOT NULL,
  `id_inquilino` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_final` date NOT NULL,
  `marca` varchar(200) NOT NULL,
  `observaciones` varchar(300) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `contrato_inmueble`
--

INSERT INTO `contrato_inmueble` (`id_contrato`, `id_inquilino`, `id_inmueble`, `fecha_inicio`, `fecha_final`, `marca`, `observaciones`, `activo`) VALUES
(1, 3, 1, '2022-07-01', '2022-07-15', 'Nuevo', 'primer alquiler', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inmueble`
--

CREATE TABLE `inmueble` (
  `id_inmueble` int(11) NOT NULL,
  `id_propietario` int(11) NOT NULL,
  `direccion` varchar(600) NOT NULL,
  `zona` varchar(400) NOT NULL,
  `estado_inmueble` varchar(200) NOT NULL,
  `tipo_inmueble` varchar(200) NOT NULL,
  `precio` double NOT NULL,
  `superficie` double NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inmueble`
--

INSERT INTO `inmueble` (`id_inmueble`, `id_propietario`, `direccion`, `zona`, `estado_inmueble`, `tipo_inmueble`, `precio`, `superficie`, `activo`) VALUES
(1, 1, 'Boulevard Of Broken Dreams 2400', 'La Matanza', 'Nuevo', 'Casa', 250000, 50, 1),
(2, 3, 'Newbery 205', 'Pringles', 'Nuevo', 'Casa', 25000, 25, 1),
(3, 3, 'Cualquiera 231', 'Lanus', 'Nuevo', 'Casa', 25675, 39, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inquilino`
--

CREATE TABLE `inquilino` (
  `id_inquilino` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `cuit` bigint(20) NOT NULL,
  `telefono` bigint(20) NOT NULL,
  `nombre_Garante` varchar(200) NOT NULL,
  `apellido_garante` varchar(200) NOT NULL,
  `dni_garante` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `inquilino`
--

INSERT INTO `inquilino` (`id_inquilino`, `nombre`, `apellido`, `dni`, `cuit`, `telefono`, `nombre_Garante`, `apellido_garante`, `dni_garante`, `activo`) VALUES
(3, 'Jon', 'Sistiaga', 23456238, 20234562387, 2657695849, 'Pedro', 'Sanchez', 38476532, 1),
(4, 'Sentado', 'By', 47564739, 20475647397, 11872635789, 'Elias', 'Garciarena', 41773891, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `id_propietario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `domicilio` varchar(400) NOT NULL,
  `telefono` bigint(20) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`id_propietario`, `nombre`, `apellido`, `dni`, `domicilio`, `telefono`, `activo`) VALUES
(1, 'Noel', 'Gallagher', 41773892, 'Manchester City, England', 447635457829, 1),
(3, 'Luis', 'Brandoni', 48736289, 'Calle Ficticia 829', 2664564723, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `contrato_inmueble`
--
ALTER TABLE `contrato_inmueble`
  ADD PRIMARY KEY (`id_contrato`),
  ADD KEY `id_inquilino` (`id_inquilino`),
  ADD KEY `id_propìedad` (`id_inmueble`);

--
-- Indices de la tabla `inmueble`
--
ALTER TABLE `inmueble`
  ADD PRIMARY KEY (`id_inmueble`),
  ADD KEY `id_propietario` (`id_propietario`);

--
-- Indices de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  ADD PRIMARY KEY (`id_inquilino`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`id_propietario`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contrato_inmueble`
--
ALTER TABLE `contrato_inmueble`
  MODIFY `id_contrato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `inmueble`
--
ALTER TABLE `inmueble`
  MODIFY `id_inmueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  MODIFY `id_inquilino` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `propietario`
--
ALTER TABLE `propietario`
  MODIFY `id_propietario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contrato_inmueble`
--
ALTER TABLE `contrato_inmueble`
  ADD CONSTRAINT `contrato_inmueble_ibfk_1` FOREIGN KEY (`id_inquilino`) REFERENCES `inquilino` (`id_inquilino`),
  ADD CONSTRAINT `contrato_inmueble_ibfk_2` FOREIGN KEY (`id_inmueble`) REFERENCES `inmueble` (`id_inmueble`);

--
-- Filtros para la tabla `inmueble`
--
ALTER TABLE `inmueble`
  ADD CONSTRAINT `inmueble_ibfk_1` FOREIGN KEY (`id_propietario`) REFERENCES `propietario` (`id_propietario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
