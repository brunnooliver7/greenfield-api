package com.greenfieldapi.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.greenfieldapi.domain.exception.NegocioException;
import com.greenfieldapi.domain.exception.EntidadeEmUso.EntidadeEmUsoException;
import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if (body == null) {
      body = ExceptionBody.builder()
          .dataHora(LocalDateTime.now())
          .mensagem(status.getReasonPhrase())
          .build();
    } else if (body instanceof String) {
      body = ExceptionBody.builder()
          .dataHora(LocalDateTime.now())
          .mensagem((String) body)
          .build();
    }

    return super.handleExceptionInternal(ex, body, headers, status, request);
  }
}