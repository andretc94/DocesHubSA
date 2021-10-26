package br.com.mv.doceshub.exceptions;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptions extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorApi erro = new ErrorApi();
		erro.setErro(
				String.format("o parametro '%s' nao foi informado, corrija e tente novamente ", ex.getParameterName()));
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorApi erro = new ErrorApi();
		erro.setErro(ex.getMessage());
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	// caso algum dado esteja errado
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String detail = ex.getFieldError().getDefaultMessage();
		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	// erro no formato da requisi��o
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		ex.printStackTrace();
		String detail = "O corpo da requisi��o est� inv�lido. Verifique erro de sintaxe.";
		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	// alguma propriedade ou valor da propriedade a mais
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

		String detail = String.format(
				"A propriedade '%s' não existe, " + "corrija ou remova essa propriedade e tente novamente.", path);

		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	// alguma propriedade com campo dados invalidos
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compativel ", path, ex.getValue());

		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	// alguma url invalida
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente", ex.getRequestURL());
		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(DescricaoNomeJaExisteException.class)
	public ResponseEntity<?> handleDescricaoNomeJaExisteException(DescricaoNomeJaExisteException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorApi erro = new ErrorApi();
		erro.setErro(ex.getMessage());
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleFormaPagamentoNaoEncontradaException(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorApi erro = new ErrorApi();
		erro.setErro(ex.getMessage());
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorApi erro = new ErrorApi();
		erro.setErro(ex.getMessage());
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status,
					request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String detail = String.format("O parametro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e tente novamente", ex.getName(), ex.getValue());

		ErrorApi erro = new ErrorApi();
		erro.setErro(detail);
		erro.setCod(status.value());
		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			ErrorApi erro = new ErrorApi();
			erro.setErro(ex.getMessage());
			erro.setCod(status.value());
			body = erro;
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
