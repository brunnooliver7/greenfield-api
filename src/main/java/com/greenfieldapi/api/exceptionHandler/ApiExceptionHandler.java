package com.greenfieldapi.api.exceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.greenfieldapi.domain.exception.NegocioException;
import com.greenfieldapi.domain.exception.EntidadeEmUso.EntidadeEmUsoException;
import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ExceptionType exceptionType = ExceptionType.ERRO_DE_SISTEMA;
    String detail = "Ocorreu um erro interno inesperado no sistema. "
        + "Tente novamente e se o problema persistir, entre em contato "
        + "com o administrador do sistema.";
    ex.printStackTrace();

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ExceptionType exceptionType = ExceptionType.ERRO_NEGOCIO;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    ExceptionType exceptionType = ExceptionType.ENTIDADE_NAO_ENCONTRADA;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

    HttpStatus status = HttpStatus.CONFLICT;
    ExceptionType exceptionType = ExceptionType.ENTIDADE_EM_USO;
    String detail = ex.getMessage();

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ExceptionType exceptionType = ExceptionType.URL_INVALIDA;
    String detail = String.format("A URL da requisição é inválida. Verifique erro de sintaxe.",
        ex.getRequestURL());

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Throwable rootCause = ExceptionUtils.getRootCause(ex);

    if (rootCause instanceof InvalidFormatException) {
      return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
    }

    ExceptionType exceptionType = ExceptionType.MENSAGEM_INCOMPREENSIVEL;
    String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    String path = ex.getPath().stream()
        .map(ref -> ref.getFieldName())
        .collect(Collectors.joining("."));

    ExceptionType exceptionType = ExceptionType.MENSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
        path, ex.getValue(), ex.getTargetType().getSimpleName());

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if (ex instanceof MethodArgumentTypeMismatchException) {
      return handleMethodArgumentTypeMismatch(
          (MethodArgumentTypeMismatchException) ex, headers, status, request);
    }

    return super.handleTypeMismatch(ex, headers, status, request);
  }

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    ExceptionType exceptionType = ExceptionType.PARAMETRO_INVALIDO;

    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
        ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail).build();

    return handleExceptionInternal(ex, exceptionBody, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ExceptionType exceptionType = ExceptionType.DADOS_INVALIDOS;
    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

    BindingResult bindingResult = ex.getBindingResult();

    List<ExceptionBody.Field> exceptionFields = bindingResult.getFieldErrors().stream()
        .map(fieldError -> ExceptionBody.Field.builder()
            .name(fieldError.getField())
            .userMessage(fieldError.getDefaultMessage())
            .build())
        .collect(Collectors.toList());

    ExceptionBody exceptionBody = createExceptionBuilder(status, exceptionType, detail)
        .userMessage(detail)
        .fields(exceptionFields)
        .build();

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

  private ExceptionBody.ExceptionBodyBuilder createExceptionBuilder(HttpStatus status,
      ExceptionType exceptionType, String detail) {

    return ExceptionBody.builder()
        .status(status.value())
        .type(exceptionType.getUri())
        .title(exceptionType.getTitle())
        .detail(detail);
  }

}
