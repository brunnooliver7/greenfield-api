package com.greenfieldapi.domain.exception.EntidadeEmUso;

import com.greenfieldapi.domain.exception.NegocioException;

public class EntidadeEmUsoException extends NegocioException {
 
  private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}