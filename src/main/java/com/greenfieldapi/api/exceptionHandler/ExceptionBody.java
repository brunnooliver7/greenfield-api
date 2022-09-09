package com.greenfieldapi.api.exceptionHandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ExceptionBody {

  private Integer status;
  private String title;
  private String detail;
  private String type;
	private String userMessage;
	private List<Field> fields;

  @Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;		
	}

}
