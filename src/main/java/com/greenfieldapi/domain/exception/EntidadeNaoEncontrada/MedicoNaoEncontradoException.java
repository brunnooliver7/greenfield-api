package com.greenfieldapi.domain.exception.EntidadeNaoEncontrada;

import com.greenfieldapi.domain.exception.Messages;

public class MedicoNaoEncontradoException extends EntidadeNaoEncontradaException {
  
  private static final long serialVersionUID = 1L;

	public MedicoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public MedicoNaoEncontradoException(Long medicoId) {
		this(String.format(Messages.MSG_MEDICO_NAO_ENCONTRADO, medicoId));
	}

}
