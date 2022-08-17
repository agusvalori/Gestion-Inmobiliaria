-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.8-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para gestion-inmobiliaria
CREATE DATABASE IF NOT EXISTS `gestion-inmobiliaria` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gestion-inmobiliaria`;

-- Volcando estructura para tabla gestion-inmobiliaria.contrato
CREATE TABLE IF NOT EXISTS `contrato` (
  `id_contrato` int(11) NOT NULL AUTO_INCREMENT,
  `id_inquilino` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_garante` int(11) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `duracion_meses` int(11) DEFAULT NULL,
  `monto_inicial` bigint(20) DEFAULT NULL,
  `aumentos_porcentaje` int(11) DEFAULT NULL,
  `aumentos_periodo` int(11) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `observacion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_contrato`),
  KEY `contrato_inquilinoFK1` (`id_inquilino`),
  KEY `contrato id_inmueble` (`id_inmueble`),
  CONSTRAINT `contrato id_inmueble` FOREIGN KEY (`id_inmueble`) REFERENCES `inmueble` (`id_inmueble`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.contrato: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
REPLACE INTO `contrato` (`id_contrato`, `id_inquilino`, `id_inmueble`, `id_garante`, `fecha_inicio`, `duracion_meses`, `monto_inicial`, `aumentos_porcentaje`, `aumentos_periodo`, `estado`, `observacion`) VALUES
	(2, 14, 11, NULL, '2022-08-12', 12, 30000, 50, 4, '1', NULL),
	(3, 9, 10, NULL, '2022-08-12', 12, 200000, 60, 12, '1', NULL);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.empleado
CREATE TABLE IF NOT EXISTS `empleado` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `cargo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `empleado_persona_FK1` (`id_persona`),
  CONSTRAINT `empleado_persona_FK1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.empleado: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
REPLACE INTO `empleado` (`id_empleado`, `id_persona`, `cargo`) VALUES
	(1, 15, 'Aprendiz'),
	(2, 18, 'Vendedor'),
	(4, 22, 'Vendedor'),
	(6, 29, 'Vendedor'),
	(10, 34, 'Vendedor');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.garante
CREATE TABLE IF NOT EXISTS `garante` (
  `id_garante` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  PRIMARY KEY (`id_garante`),
  KEY `garante_persona_FK1` (`id_persona`),
  CONSTRAINT `garante_persona_FK1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.garante: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `garante` DISABLE KEYS */;
/*!40000 ALTER TABLE `garante` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.inmueble
CREATE TABLE IF NOT EXISTS `inmueble` (
  `id_inmueble` int(11) NOT NULL AUTO_INCREMENT,
  `id_propietario` int(11) NOT NULL,
  `tipo_inmueble` varchar(100) NOT NULL,
  `estado_inmueble` varchar(100) DEFAULT NULL,
  `zona` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `localidad` varchar(200) DEFAULT NULL,
  `provincia` varchar(100) DEFAULT NULL,
  `caracteristicas` varchar(200) DEFAULT NULL,
  `monto_inicial` bigint(20) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_inmueble`),
  KEY `inmueble_propietario_FK1` (`id_propietario`),
  CONSTRAINT `inmueble_propietario_FK1` FOREIGN KEY (`id_propietario`) REFERENCES `propietario` (`id_propietario`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.inmueble: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `inmueble` DISABLE KEYS */;
REPLACE INTO `inmueble` (`id_inmueble`, `id_propietario`, `tipo_inmueble`, `estado_inmueble`, `zona`, `direccion`, `localidad`, `provincia`, `caracteristicas`, `monto_inicial`, `estado`) VALUES
	(9, 10, 'Casa', 'Nueva', 'Centro', 'Pedernera 10', 'Villa Mercedes', 'San Luis', '', 10000, 1),
	(10, 10, 'Casa', 'Nueva', 'Centro', 'Lavalle 60', 'Villa Mercedes', 'San Luis', '', 200000, 1),
	(11, 11, 'Departamento', 'Nueva', 'Centro', 'Pringles 10', 'Villa Mercedes', 'San Luis', '', 30000, 1),
	(12, 10, 'Casa', 'Nueva', 'Las Miranda', 'W Gez y Sto Baigorria', 'Villa Mercedes', 'San Luis', '', 15000, 1);
/*!40000 ALTER TABLE `inmueble` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.inquilino
CREATE TABLE IF NOT EXISTS `inquilino` (
  `id_inquilino` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) DEFAULT NULL,
  `condicion` varchar(100) DEFAULT 'Nuevo',
  `cant_renovacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_inquilino`) USING BTREE,
  KEY `inquilino_persona_FK1` (`id_persona`),
  CONSTRAINT `inquilino_persona_FK1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.inquilino: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `inquilino` DISABLE KEYS */;
REPLACE INTO `inquilino` (`id_inquilino`, `id_persona`, `condicion`, `cant_renovacion`) VALUES
	(4, 15, 'Renovante', 0),
	(5, 18, 'Nuevo', NULL),
	(7, 20, 'Renovante', 0),
	(9, 22, 'Nuevo', NULL),
	(10, 13, 'Renovante', 0),
	(14, 29, 'Nuevo', 0),
	(16, 33, 'Nuevo', 0),
	(17, 35, 'Nuevo', 0);
/*!40000 ALTER TABLE `inquilino` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` bigint(20) NOT NULL,
  `cuit` bigint(20) unsigned DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefono` bigint(20) unsigned DEFAULT NULL,
  `estado` binary(2) DEFAULT NULL,
  `calificacion_inquilino` varchar(100) DEFAULT 'Ninguna',
  `calificacion_propietario` varchar(100) DEFAULT 'Ninguna',
  `calificacion_garante` varchar(100) DEFAULT 'Ninguna',
  `calificacion_empleado` varchar(100) DEFAULT 'Ninguna',
  PRIMARY KEY (`id_persona`) USING BTREE,
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.persona: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
REPLACE INTO `persona` (`id_persona`, `nombre`, `apellido`, `dni`, `cuit`, `email`, `telefono`, `estado`, `calificacion_inquilino`, `calificacion_propietario`, `calificacion_garante`, `calificacion_empleado`) VALUES
	(13, 'Esteban', 'Perez', 11111111, 222222222, 'email1@gmail.com', 333333333, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(15, 'Agustin', 'Garcias', 22222222, 20222222221, 'agustin@gmail.com', 2657112233, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(18, 'Dalina', 'Rodriguez', 33333333, 20, 'dalina@gmail', 2657222222, _binary 0x3100, 'Excelente', 'Buena', 'Mala', 'Ninguna'),
	(19, 'Delicia', 'Gonzales', 44444444, 1021312312, 'delia@gmail', 12312312312, _binary 0x3100, 'Buena', 'Excelente', 'Buena', 'Excelente'),
	(20, 'Maximiliano', 'Perez', 55555555, 123456789, 'maximiliano@gmail.com', 12345678, _binary 0x3100, 'Buena', 'Excelente', 'Mala', 'Ninguna'),
	(22, 'Gonzalo', 'Torres', 66666666, 20202022021, 'gonzalito@gmail.com', 2000020002, _binary 0x3100, 'Excelente', 'Mala', 'Mala', 'Excelente'),
	(23, 'Javier', 'Garcias', 77777777, 10101102, 'javier@gmail.com', 10101103, _binary 0x3100, 'Mala', 'Excelente', 'Excelente', 'Mala'),
	(29, 'Gustavo', 'Hernandez', 88888888, 20505055051, 'gustavo@gmail.com', 2664112233, _binary 0x3100, 'Excelente', 'Buena', 'Mala', 'Excelente'),
	(30, 'Vero', 'Hernandez', 99999999, 20606066061, 'vero@gmail.com', 0, _binary 0x3100, 'Excelente', 'Buena', 'Mala', 'Ninguna'),
	(31, 'Tobias', 'Rodiguez', 10101101, 207077071, 'tobias@gmail.com', 0, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(33, 'Juan Carlos', 'Gutierrez', 20202202, 0, NULL, 0, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(34, 'Jean Carlos', 'Alcalaz', 30303303, 0, NULL, 0, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(35, 'Carlos', 'Gimenez', 40404404, 0, '', 0, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna'),
	(36, 'Ricardo', 'Iorio', 50505505, 0, NULL, 0, _binary 0x3100, 'Ninguna', 'Ninguna', 'Ninguna', 'Ninguna');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

-- Volcando estructura para tabla gestion-inmobiliaria.propietario
CREATE TABLE IF NOT EXISTS `propietario` (
  `id_propietario` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_propietario`),
  KEY `propietario_persona_FK1` (`id_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla gestion-inmobiliaria.propietario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `propietario` DISABLE KEYS */;
REPLACE INTO `propietario` (`id_propietario`, `id_persona`) VALUES
	(11, 15),
	(13, 18),
	(10, 29);
/*!40000 ALTER TABLE `propietario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
