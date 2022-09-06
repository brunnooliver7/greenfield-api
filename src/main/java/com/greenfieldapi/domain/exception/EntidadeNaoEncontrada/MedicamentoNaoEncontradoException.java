package com.greenfieldapi.domain.exception.EntidadeNaoEncontrada;

import com.greenfieldapi.domain.exception.Messages;

public class MedicamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
  
  private static final long serialVersionUID = 1L;

	public MedicamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public MedicamentoNaoEncontradoException(Long medicamentoId) {
		this(String.format(Messages.MSG_MEDICAMENTO_NAO_ENCONTRADO, medicamentoId));
	}

}
