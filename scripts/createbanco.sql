CREATE table IF NOT exists  usuario(
    id 	        	     SERIAL       PRIMARY KEY,
    cpf	 	             VARCHAR(14)  NOT NULL,
   	nome	 	         VARCHAR(255) NOT NULL,
   	telefone        	 VARCHAR(30) ,
   	email 	             VARCHAR(30)  NOT NULL,
   	senha 	             VARCHAR(255) NOT NULL,
   	dataNascimento       DATE       NOT NULL,
   	sexo 		         VARCHAR(9)  NOT NULL
);

CREATE TABLE IF NOT exists  endereco
(
    id 		 	 SERIAL       PRIMARY KEY,
    pais	     VARCHAR(255)  NOT NULL,
   	estado	 	 CHAR(2)       NOT NULL,
   	municipio 	 VARCHAR(255)  NOT NULL,
   	bairro 	     VARCHAR(255)  NOT NULL,
    rua 	     VARCHAR(255)  NOT NULL,
   	cep 	     VARCHAR(30)   NOT NULL,
   	complemento   VARCHAR(30),
    idUsuario         BIGINT       NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT exists  veiculo
(
    id 		 	    SERIAL       PRIMARY KEY,
    modelo 			VARCHAR(255)  NOT NULL,
   	marca	 		VARCHAR(255)  NOT NULL,
   	placa	 		VARCHAR(255)  NOT NULL,
    idUsuario         BIGINT       NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS forma_pagamento
(
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    idUsuario BIGINT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);

