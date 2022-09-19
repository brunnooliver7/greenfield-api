create table tb_medico (
  id bigserial not null, 
  cpf varchar(255) not null, 
  crm varchar(255) not null, 
  dt_nascimento date not null, 
  email varchar(255) not null, 
  estado varchar(255) not null, 
  estado_registro_crm varchar(255) not null, 
  nome varchar(255) not null, 
  senha varchar(255) not null, 
  sexo varchar(255) not null,
  dt_cadastro timestamp not null,
  dt_atualizacao timestamp not null,
  
  primary key (id)
);

alter table if exists tb_medico 
add constraint cpf_medico_unique 
unique (cpf);

alter table if exists tb_medico 
add constraint crm_unique 
unique (crm);

alter table if exists tb_medico 
add constraint email_medico_unique 
unique (email);
