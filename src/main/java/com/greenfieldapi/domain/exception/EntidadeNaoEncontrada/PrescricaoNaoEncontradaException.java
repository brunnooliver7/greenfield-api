package com.greenfieldapi.domain.exception.EntidadeNaoEncontrada;

import com.greenfieldapi.domain.exception.Messages;

public class PrescricaoNaoEncontradaException extends EntidadeNaoEncontradaException {
  
  private static final long serialVersionUID = 1L;

	public PrescricaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PrescricaoNaoEncontradaException(Long prescricaoId) {
		this(String.format(Messages.MSG_PRESCRICAO_NAO_ENCONTRADA, prescricaoId));
	}

}
