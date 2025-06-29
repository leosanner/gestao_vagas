package br.com.leonardosanner.gestao_vagas.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

@ControllerAdvice // Mostra que a classe vai tratar de exceções
public class ExceptionHandlerController {
    
    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // Lida com a exceção passada como classe
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err->{
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error =  new ErrorMessageDTO(message, err.getField());
            dto.add(error); // ResponseEntity retorna Classe como json
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessageDTO> handleAuthrnticationException(AuthenticationException e) {

        ErrorMessageDTO dto = new ErrorMessageDTO(e.getMessage(), e.getClass().getName());

        return ResponseEntity.badRequest().body(dto);
    }
}
