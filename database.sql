CREATE DATABASE `user-interview` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `user-interview`

-- `user-interview`.roles definition

CREATE TABLE `roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- `user-interview`.tb_usuario definition

CREATE TABLE `tb_usuario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(3000) NOT NULL,
  `senha` varchar(3000) NOT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `DATA_CRIACAO` datetime NOT NULL,
  `DATA_ATUALIZACAO` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`email`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- `user-interview`.user_roles definition

CREATE TABLE `user_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `tb_usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;