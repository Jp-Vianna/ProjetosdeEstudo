-- Inserindo dados na tabela cliente
INSERT INTO cliente (idCliente, tipoCliente, telefone, e_email, nome, cpf) VALUES
(1, 'PessoaFisica', '2111-1111', 'cliente1@email.com', 'João Silva', '00256467834'),
(2, 'PessoaFisica', '6222-2522', 'cliente2@email.com', 'Alano Borges', '23465489701'),
(4, 'PessoaFisica', '5644-2344', 'cliente4@email.com', 'Ricardo Soares', '12332445678'),
(5, 'PessoaFisica', '8665-5525', 'cliente5@email.com', 'Maria Bonetti', '14998092345');

INSERT INTO cliente (idCliente, tipoCliente, telefone, e_email, nome, cnpj, razaoSocial) VALUES
(3, 'PessoaJuridica', '9333-3733', 'cliente3@email.com', 'Zé impressões', '34567887692435', 'Impressões perfeitas'),
(6, 'PessoaJuridica', '2456-6666', 'cliente6@email.com', 'Ortobom', '12345678913245', 'Comforto no sono');

-- Inserindo dados na tabela endereco
INSERT INTO endereco (idEndereco, idCliente, cep, rua, numero, Complemento) VALUES
(1, 1, '22111111', 'Rua Gervásio', 461, null),
(2, 2, '22251222', 'Rua Rio Branco', 22, null),
(3, 3, '22710033', 'Rua Feira de Parnaúba', 301, null),
(4, 4, '44565784', 'Rua do Bispo', 402, 'Perto da Fornalha.'),
(5, 5, '11575895', 'Rua Bandeirantes', 57, 'Do lado da Ortobom.'),
(6, 6, '22451006', 'Rua Padre Cistino', 66, 'Em frente a Pacheco.');

-- Inserindo dados na tabela funcionario
INSERT INTO funcionario (idFunc, nome, salario, cargaHoraria, cpf, tipo_servico, turno) VALUES
(1, 'Júnior Gonçalves', 1900.00, 40, '12345678901', 'Cozinheiro', 'Manhã'),
(2, 'Francisca Vieira', 8600.00, 40, '23456789012', 'Gerente', 'Tarde'),
(3, 'Sara Menezes', 3000.00, 40, '34567890123', 'Recepcionista', 'Noite'),
(4, 'Fabricio Mineiro', 2200.00, 40, '45678901234', 'Camareiro', 'Manhã'),
(5, 'Marcia Costa', 3200.00, 40, '56789012345', 'Zeladora', 'Tarde'),
(6, 'Lúcio Mauro', 5500.00, 40, '67890123456', 'Eletricista', 'Noite');

-- Inserindo dados na tabela infoPagamento
INSERT INTO infoPagamento (idInfoPagamento, idCliente, numCartao, numConta) VALUES
(1, 1, '1111111111111111', '12345'),
(2, 2, '2222222222222222', '23456'),
(3, 3, '3333333333333333', '34567'),
(4, 4, '4444444444444444', '44444'),
(5, 5, '5555555555555555', '55555'),
(6, 6, '6666666666666666', '66666');

-- Inserindo dados na tabela quarto
INSERT INTO quarto (numQuarto, descQuarto, maxPessoas, valorDiaria) VALUES
(101, 'Quarto Panorâmico', 3, 400.00),
(102, 'Quarto Suite', 4, 340.00),
(103, 'Quarto Presidencial', 5, 550.00),
(104, 'Quarto Simples', 1, 170.00),
(105, 'Quarto Padrão', 2, 190.00),
(106, 'Quarto Padrão', 2, 190.00),
(107, 'Quarto Simples', 1, 170.00),
(108, 'Quarto Presidencial', 5, 550.00);

-- Inserindo dados na tabela reserva
INSERT INTO reserva (idReserva, idCliente, numQuarto, dataCheckIn, dataCheckOut, numHospedes, statusReserva) VALUES
(1, 5, 101, '2023-11-01', '2023-11-05', 2, 'Confirmada'),
(2, 1, 102, '2023-11-03', '2023-11-08', 3, 'Pendente'),
(3, 3, 103, '2023-11-05', '2023-11-10', 1, 'Confirmada'),
(4, 3, 104, '2023-11-15', '2023-11-20', 2, 'Confirmada'),
(5, 2, 105, '2023-11-18', '2023-11-22', 3, 'Pendente'),
(6, 6, 106, '2023-11-27', '2023-12-03', 1, 'Confirmada'),
(7, 4, 107, '2023-11-25', '2023-12-05', 1, 'Confirmada'),
(8, 6, 108, '2023-11-23', '2023-12-10', 1, 'Confirmada');

SET FOREIGN_KEY_CHECKS=0;

-- Inserindo dados na tabela servico
INSERT INTO servico (idServico, idReserva, idFunc) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 4, 2),
(10, 3, 6);

SET FOREIGN_KEY_CHECKS=1;

-- Inserindo dados na tabela avaliacao
INSERT INTO avaliacao (idAvaliacao, idReserva, titulo, descricao, nota, motivo) VALUES
(1, 1, 'Boa comida.', 'Bons temperos e com cara boa. Vou postar no stories!', 4, 'Bom cozinheiro.'),
(2, 3, 'Cama confortável.', 'O colchão é Ortobom certeza!', 4, 'Conforto.'),
(3, 8, 'Profissionais bons.', 'Todos muito simpáticos voltarei mais vezes.', 5, 'Excelente serviço.'),
(4, 4, 'Limpeza impecável.', 'O chão estava brilhando!', 4, 'Boa limpeza.'),
(5, 6, 'Comida péssima.', 'Me deu indigestão. Estou reclamando direto do banheiro!!', 3, 'Necessita melhorias.'),
(6, 7, 'Travesseiro ruim.', 'Tive que ir ao hospital por dores no pescoço...', 2, 'Sem conforto.');
