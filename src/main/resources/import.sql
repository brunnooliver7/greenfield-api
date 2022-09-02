-- Médicos
INSERT INTO tb_medico (cpf, email, nome, dt_nascimento, crm, estado_registro_crm, estado, sexo, senha) VALUES ('123.456.789-01', 'andre@gmail', 'André da Silva', '2000-01-01', '001', 'ativo', 'DF', 'M', 'secreta1');
INSERT INTO tb_medico (cpf, email, nome, dt_nascimento, crm, estado_registro_crm, estado, sexo, senha) VALUES ('123.456.789-02', 'bernardo@gmail', 'Bernardo Gonçalvez', '2000-02-02', '002', 'suspenso', 'SP', 'M', 'secreta2');
INSERT INTO tb_medico (cpf, email, nome, dt_nascimento, crm, estado_registro_crm, estado, sexo, senha) VALUES ('123.456.789-03', 'camila@gmail', 'Camila Vasconcelos', '2000-03-03', '003', 'ativo', 'MG', 'F', 'secreta3');

-- Pacientes
INSERT INTO tb_paciente (cpf, nome, dt_nascimento, sexo) VALUES ('123.456.789-11', 'Augusto Borges', '2001-01-01', 'M');
INSERT INTO tb_paciente (cpf, nome, dt_nascimento, sexo) VALUES ('123.456.789-12', 'Bruna Matos', '2001-01-02', 'F');
INSERT INTO tb_paciente (cpf, nome, dt_nascimento, sexo) VALUES ('123.456.789-13', 'Carol Albuquerque', '2001-01-03', 'F');

-- Prescrições
INSERT INTO tb_prescricao (medico_id, paciente_id) VALUES (1, 1);
INSERT INTO tb_prescricao (medico_id, paciente_id) VALUES (2, 2);
INSERT INTO tb_prescricao (medico_id, paciente_id) VALUES (3, 2);
INSERT INTO tb_prescricao (medico_id, paciente_id) VALUES (3, 3);

-- Medicamentos
INSERT INTO tb_medicamento (descricao, quantidade , dosagem, frequencia) VALUES ('ibuprofeno', 2, '400mg', '3 vezes ao dia');
INSERT INTO tb_medicamento (descricao, quantidade , dosagem, frequencia) VALUES ('dipirona', 1, '40 gotas', '2 vezes ao dia');
INSERT INTO tb_medicamento (descricao, quantidade , dosagem, frequencia) VALUES ('resfenol', 2, '100mg', '3 vezes ao dia');
INSERT INTO tb_medicamento (descricao, quantidade , dosagem, frequencia) VALUES ('isoniazida', 6, '400mg', '3 vezes ao dia');
INSERT INTO tb_medicamento (descricao, quantidade , dosagem, frequencia) VALUES ('pantoprazol', 10, '20mg', '1 vez ao dia');

INSERT INTO tb_prescricao_medicamentos (prescricao_id, medicamentos_id) VALUES (1, 1);
INSERT INTO tb_prescricao_medicamentos (prescricao_id, medicamentos_id) VALUES (1, 2);
INSERT INTO tb_prescricao_medicamentos (prescricao_id, medicamentos_id) VALUES (2, 3);
INSERT INTO tb_prescricao_medicamentos (prescricao_id, medicamentos_id) VALUES (3, 4);
INSERT INTO tb_prescricao_medicamentos (prescricao_id, medicamentos_id) VALUES (4, 5);