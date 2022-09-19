create table tb_medicamento (
  id  bigserial not null, 
  descricao varchar(255) not null, 
  dosagem varchar(255), 
  frequencia varchar(255), 
  quantidade int4, 
  dt_cadastro timestamp not null,
  dt_atualizacao timestamp not null,

  primary key (id)
);
