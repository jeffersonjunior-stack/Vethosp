CREATE DATABASE IF NOT EXISTS vethosp_db;
USE vethosp_db;

CREATE TABLE setor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    capacidade_max INT NOT NULL CHECK (capacidade_max > 0),
    boxes_ocupados INT DEFAULT 0
);

CREATE TABLE box (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    identificador VARCHAR(50) NOT NULL,
    status ENUM('LIVRE', 'OCUPADO') NOT NULL DEFAULT 'LIVRE',
    setor_id BIGINT NOT NULL,
    FOREIGN KEY (setor_id) REFERENCES setor(id) ON DELETE CASCADE
);
