create table tb_prescricao (
  id  bigserial not null, 
  medico_id int8 not null, 
  paciente_id int8 not null, 
  dt_cadastro timestamp not null,
  dt_atualizacao timestamp not null,

  primary key (id)
);

alter table if exists tb_prescricao 
add constraint tb_prescricao_id_medico 
foreign key (medico_id) references tb_medico;

alter table if exists tb_prescricao 
add constraint tb_prescricao_id_paciente 
foreign key (paciente_id) references tb_paciente;
