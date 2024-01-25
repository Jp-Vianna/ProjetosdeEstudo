-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-27 23:28:08.374

-- tables
-- Table: avaliacao
CREATE TABLE avaliacao (
    idAvaliacao bigint  NOT NULL,
    idReserva bigint  NOT NULL,
    titulo varchar(40)  NULL,
    descricao varchar(500)  NULL,
    nota int  NOT NULL,
    motivo varchar(80)  NULL,
    CONSTRAINT avaliacao_pk PRIMARY KEY (idAvaliacao)
);

-- Table: cliente
CREATE TABLE cliente (
    idCliente bigint NOT NULL,
    tipoCliente ENUM('PessoaFisica', 'PessoaJuridica') NOT NULL,
    telefone varchar(20) NULL,
    e_email varchar(60) NULL,
    cnpj varchar(50) NULL,
    razaoSocial varchar(80) NULL,
    cpf varchar(11) NULL,
    nome varchar(100) NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (idCliente)
);

-- Table: endereco
CREATE TABLE endereco (
    idEndereco bigint  NOT NULL,
    idCliente bigint  NOT NULL,
    cep varchar(8)  NOT NULL,
    rua varchar(60)  NOT NULL,
    numero int  NOT NULL,
    Complemento varchar(40)  NULL,
    CONSTRAINT endereco_pk PRIMARY KEY (idEndereco)
);

-- Table: funcionario
CREATE TABLE funcionario (
    idFunc bigint  NOT NULL,
    nome varchar(60)  NOT NULL,
    salario double  NOT NULL,
    cargaHoraria int  NOT NULL,
    cpf varchar(11)  NOT NULL,
    tipo_servico varchar(20)  NOT NULL,
    turno varchar(10)  NOT NULL,
    CONSTRAINT funcionario_pk PRIMARY KEY (idFunc)
);

-- Table: infoPagamento
CREATE TABLE infoPagamento (
    idInfoPagamento bigint  NOT NULL,
    idCliente bigint  NOT NULL,
    numCartao varchar(16)  NULL,
    numConta varchar(10)  NULL,
    CONSTRAINT infoPagamento_pk PRIMARY KEY (idInfoPagamento)
);

-- Table: quarto
CREATE TABLE quarto (
    numQuarto bigint  NOT NULL,
    descQuarto varchar(500)  NOT NULL,
    maxPessoas int  NOT NULL,
    valorDiaria double  NOT NULL,
    CONSTRAINT quarto_pk PRIMARY KEY (numQuarto)
);

-- Table: reserva
CREATE TABLE reserva (
    idReserva bigint  NOT NULL,
    idCliente bigint  NOT NULL,
    numQuarto bigint  NOT NULL,
    dataCheckIn datetime  NOT NULL,
    dataCheckOut datetime  NOT NULL,
    numHospedes int  NOT NULL,
    statusReserva varchar(20)  NOT NULL,
    CONSTRAINT reserva_pk PRIMARY KEY (idReserva)
);

-- Table: servico
CREATE TABLE servico (
    idServico bigint  NOT NULL,
    idReserva bigint  NOT NULL,
    idFunc bigint  NOT NULL,
    CONSTRAINT servico_pk PRIMARY KEY (idServico)
);

-- foreign keys
-- Reference: Reserva_Cliente (table: reserva)
ALTER TABLE reserva ADD CONSTRAINT Reserva_Cliente FOREIGN KEY Reserva_Cliente (idCliente)
    REFERENCES cliente (idCliente);

-- Reference: Reserva_Quarto (table: reserva)
ALTER TABLE reserva ADD CONSTRAINT Reserva_Quarto FOREIGN KEY Reserva_Quarto (numQuarto)
    REFERENCES quarto (numQuarto);

-- Reference: Servico_Reserva (table: servico)
ALTER TABLE servico ADD CONSTRAINT Servico_Reserva FOREIGN KEY Servico_Reserva (idReserva)
    REFERENCES reserva (idReserva);

-- Reference: Servico_funcionario (table: servico)
ALTER TABLE servico ADD CONSTRAINT Servico_funcionario FOREIGN KEY Servico_funcionario (idFunc)
    REFERENCES funcionario (idFunc);

-- Reference: avaliacao_reserva (table: avaliacao)
ALTER TABLE avaliacao ADD CONSTRAINT avaliacao_reserva FOREIGN KEY avaliacao_reserva (idReserva)
    REFERENCES reserva (idReserva);

-- Reference: endereco_Cliente (table: endereco)
ALTER TABLE endereco ADD CONSTRAINT endereco_Cliente FOREIGN KEY endereco_Cliente (idCliente)
    REFERENCES cliente (idCliente);

-- Reference: infoPagamento_Cliente (table: infoPagamento)
ALTER TABLE infoPagamento ADD CONSTRAINT infoPagamento_Cliente FOREIGN KEY infoPagamento_Cliente (idCliente)
    REFERENCES cliente (idCliente);

-- End of file.

