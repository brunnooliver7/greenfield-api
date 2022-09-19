create table tb_medicamento (
  id  bigserial not null, 
  descricao varchar(255) not null, 
  dosagem varchar(255), 
  frequencia varchar(255), 
  quantidade int4, 
  dt_cadastro date not null,
  dt_atualizacao date not null,

  primary key (id)
);
