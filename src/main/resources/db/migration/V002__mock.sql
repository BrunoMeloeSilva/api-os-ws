INSERT INTO `os`.`cliente` (`nome`, `email`, `phone`) VALUES ('Bruno Silva', 'brunosilva@gmail.com', '5592912345678');
INSERT INTO `os`.`cliente` (`nome`, `email`, `phone`) VALUES ('Maria Clara', 'mariaclara@gmail.com', '5592909876543');
INSERT INTO `os`.`cliente` (`nome`, `email`, `phone`) VALUES ('Keule Fabiana', 'keulefabiana@gmail.com', '5592912343241');

INSERT INTO `os`.`os` (`fkcliente`, `descricao`, `status`, `abertura`, `fechamento`) VALUES (1, 'Ajuste do cadastro de centro de custo', 'ABERTA', now(), now());
INSERT INTO `os`.`os` (`fkcliente`, `descricao`, `status`, `abertura`, `fechamento`) VALUES (1, 'Criar relatorio de fluxo de caixa', 'CANCELADA', now(), now());

INSERT INTO `os`.`comentario` (`fkos`, `descricao`, `criado`) VALUES ('2', 'Atividade não é mais necessária', now());
