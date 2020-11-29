CREATE TABLE `cliente` (
  `pk` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(13) NOT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `os` (
  `pk` bigint NOT NULL AUTO_INCREMENT,
  `fkcliente` bigint NOT NULL,
  `descricao` text NOT NULL,
  `status` varchar(20) NOT NULL,
  `abertura` datetime NOT NULL,
  `fechamento` datetime DEFAULT NULL,
  PRIMARY KEY (`pk`),
  KEY `fk_os_cliente` (`fkcliente`),
  CONSTRAINT `fk_os_cliente` FOREIGN KEY (`fkcliente`) REFERENCES `cliente` (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comentario` (
  `pk` bigint NOT NULL AUTO_INCREMENT,
  `fkos` bigint NOT NULL,
  `descricao` text NOT NULL,
  `criado` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `fk_comentario_os` (`fkos`),
  CONSTRAINT `fk_comentario_os` FOREIGN KEY (`fkos`) REFERENCES `os` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
