package br.com.rest.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDto> validationHandler(MethodArgumentNotValidException exception) {
		List<FormErrorDto> list = new ArrayList<FormErrorDto>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach( e -> { 
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormErrorDto error = new FormErrorDto(e.getField(), mensagem);
			list.add(error);
		});

		return list;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler( MethodArgumentTypeMismatchException.class)
	public FormErrorDto handleConstraintViolation(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		
		return new FormErrorDto(ex.getName(), ex.getMessage());
	}
}
//TODO:IMPLEMENTAR VALIDAÇÕES GENÉRICAS PARA TIPOS
