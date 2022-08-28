create table Tb_Medico (
  id serial  PRIMARY KEY,
  cpf varchar(100) unique not null,
  nome varchar(100) not null,
  email varchar(100) unique null,
  dt_nascimento timestamp,
  crm varchar(20) unique not null,
  estado_registro_crm varchar(20) not null,
  estado varchar(2),
  sexo varchar(1),
  password varchar(200) not null
);