CREATE DATABASE IF NOT EXISTS db;

USE db;

CREATE TABLE rebelde (
    oid_rebelde INT NOT NULL AUTO_INCREMENT primary key,
    nome VARCHAR(50) not null,
    idade int not null,
    genero VARCHAR(1) not null,
    qtd_traicoes int not null default 0

);

CREATE TABLE localizacao (
    oid_localizacao INT NOT NULL AUTO_INCREMENT primary key,
    latitude int not null,
    longitude int not null,
    nome VARCHAR(100) not null,
    oid_rebelde INT NOT NULL,
    CONSTRAINT fk_localizacao_rebelde FOREIGN KEY (oid_rebelde)  REFERENCES rebelde(oid_rebelde)
);

CREATE TABLE item (
    oid_item INT NOT NULL AUTO_INCREMENT primary key,
    nome VARCHAR(50) not null,
    pontuacao int not null
);

CREATE TABLE rebelde_item (
    oid_rebelde_item INT NOT NULL AUTO_INCREMENT primary key,
    oid_rebelde INT NOT NULL,
    oid_item INT NOT NULL,
    quantidade int not null,
    CONSTRAINT fk_rebelde_item FOREIGN KEY (oid_rebelde)  REFERENCES rebelde(oid_rebelde),
    CONSTRAINT fk_item FOREIGN KEY (oid_item)  REFERENCES item(oid_item)
);

INSERT INTO item( nome, pontuacao )
VALUES( 'Arma', 4 );

INSERT INTO item( nome, pontuacao )
VALUES( 'Municao', 3 );

INSERT INTO item( nome, pontuacao )
VALUES( 'Agua', 2 );

INSERT INTO item( nome, pontuacao )
VALUES( 'Comida', 1 );

COMMIT;