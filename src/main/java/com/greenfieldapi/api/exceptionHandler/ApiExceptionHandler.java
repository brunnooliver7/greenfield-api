package com.greenfieldapi.api.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
  public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ExceptionType exceptionType = ExceptionType.ERRO_NEGOCIO;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBodyBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    ExceptionType exceptionType = ExceptionType.ENTIDADE_NAO_ENCONTRADA;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBodyBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

    HttpStatus status = HttpStatus.CONFLICT;
    ExceptionType exceptionType = ExceptionType.ENTIDADE_EM_USO;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBodyBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    ExceptionType exceptionType = ExceptionType.MENSAGEM_INCOMPREENSIVEL;
    String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

    ExceptionBody exceptionBody = createExceptionBodyBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if (body == null) {
      body = ExceptionBody.builder()
          .title(status.getReasonPhrase())
          .status(status.value())
          .build();
    } else if (body instanceof String) {
      body = ExceptionBody.builder()
          .title((String) body)
          .status(status.value())
          .build();
    }

    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private ExceptionBody.ExceptionBodyBuilder createExceptionBodyBuilder(HttpStatus status,
      ExceptionType exceptionType, String detail) {

    return ExceptionBody.builder()
        .status(status.value())
        .type(exceptionType.getUri())
        .title(exceptionType.getTitle())
        .detail(detail);
  }

}
