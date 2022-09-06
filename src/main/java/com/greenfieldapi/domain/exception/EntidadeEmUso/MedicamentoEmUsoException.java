package com.greenfieldapi.domain.exception.EntidadeEmUso;

import com.greenfieldapi.domain.exception.Messages;

public class MedicamentoEmUsoException extends EntidadeEmUsoException {
  
  private static final long serialVersionUID = 1L;

	public MedicamentoEmUsoException(String mensagem) {
		super(mensagem);
	}
	
	public MedicamentoEmUsoException(Long medicamentoId) {
		this(String.format(Messages.MSG_MEDICAMENTO_EM_USO, medicamentoId));
	}

}
