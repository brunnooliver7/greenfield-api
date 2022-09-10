create table tb_prescricao_medicamentos (
  prescricao_id int8 not null, 
  medicamentos_id int8 not null
);

alter table if exists tb_prescricao_medicamentos 
add constraint prescricao_medicamentos_unique 
unique (medicamentos_id);

alter table if exists tb_prescricao_medicamentos 
add constraint tb_prescricao_medicamentos_id_medicamento 
foreign key (medicamentos_id) references tb_medicamento;

alter table if exists tb_prescricao_medicamentos 
add constraint tb_prescricao_medicamentos_id_prescricao
foreign key (prescricao_id) references tb_prescricao;