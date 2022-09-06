package com.greenfieldapi.domain.exception.EntidadeNaoEncontrada;

import com.greenfieldapi.domain.exception.NegocioException;

public class EntidadeNaoEncontradaException extends NegocioException {
 
  private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}  
}
