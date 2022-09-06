package com.greenfieldapi.domain.exception.EntidadeNaoEncontrada;

import com.greenfieldapi.domain.exception.Messages;

public class PacienteNaoEncontradoException extends EntidadeNaoEncontradaException {
  
  private static final long serialVersionUID = 1L;

	public PacienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PacienteNaoEncontradoException(Long pacienteId) {
		this(String.format(Messages.MSG_PACIENTE_NAO_ENCONTRADO, pacienteId));
	}

}
