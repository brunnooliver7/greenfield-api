package com.greenfieldapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ExceptionType {
  
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível");

	private String title;
	private String uri;
	
	ExceptionType(String path, String title) {
		this.uri = "https://greenfield.com.br" + path;
		this.title = title;
	}

}
