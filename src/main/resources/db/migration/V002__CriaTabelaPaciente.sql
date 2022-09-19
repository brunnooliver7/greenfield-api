create table tb_paciente (
  id  bigserial not null, 
  cpf varchar(255) not null, 
  dt_nascimento date not null, 
  nome varchar(255) not null, 
  sexo varchar(255) not null, 
  dt_cadastro date not null,
  dt_atualizacao date not null,

  primary key (id)
);

alter table if exists tb_paciente 
add constraint cpf_paciente_unique 
unique (cpf);
