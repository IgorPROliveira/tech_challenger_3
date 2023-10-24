CREATE table IF NOT exists  usuario(
        id 		 SERIAL       PRIMARY KEY,
        cpf	 	 VARCHAR(14)  NOT NULL,
   	nome	 	 VARCHAR(255) NOT NULL,
   	telefone_principal 	 VARCHAR(30) ,
	telefone _recado	 VARCHAR(30) ,
   	email 	         VARCHAR(30)  NOT NULL,
   	senha 	         VARCHAR(255) NOT NULL,
   	dataNascimento    DATE       NOT NULL,
   	sexo 		  VARCHAR(9)  NOT NULL,
);


CREATE TABLE IF NOT exists  endereco
(
    	id 		 	 SERIAL       PRIMARY KEY,
    	pais	     VARCHAR(255)  NOT NULL,
   	estado	 	 CHAR(2)       NOT NULL,
   	municipio 	 VARCHAR(255),
   	bairro 	     VARCHAR(255)  NOT NULL,
   	cep 	     VARCHAR(30)   NOT NULL,
   	complemento   VARCHAR(30),
FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);



CREATE TABLE IF NOT exists  veiculo
(
    	id 		 	 SERIAL       PRIMARY KEY,
    	modelo 			VARCHAR(255)  NOT NULL,
   	marca	 		VARCHAR(255)  NOT NULL,
   	ano	 		INTEGER	      NOT NULL,
   	placa	 		VARCHAR(255)  NOT NULL,
FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);	


CREATE TABLE IF NOT exists  forma_pagamento
(
    	id 		 	 SERIAL       PRIMARY KEY,
    	tipo			VARCHAR(255)  NOT NULL,
   	numero_cartao		VARCHAR(255)  NOT NULL,
   	validade		DATE       NOT NULL,
   	codigo_segurenca	VARCHAR(255)  NOT NULL,
FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT exists  estacionamento
(
    	id 		 	 SERIAL       PRIMARY KEY,
    	modalidade		VARCHAR(255)  NOT NULL,
	tempo			DOUBLE NOT NULL,
   	dh_incio		DATE  NOT NULL,
   	dh_fim			DATE       NOT NULL,
   	valor			VARCHAR(255)  NOT NULL,
FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
FOREIGN KEY (idVeiculo) REFERENCES veiculo(id) ON DELETE CASCADE
FOREIGN KEY (idPagamento) REFERENCES forma_pagamento(id) ON DELETE CASCADE
);