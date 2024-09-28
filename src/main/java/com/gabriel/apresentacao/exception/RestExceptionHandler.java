package com.gabriel.apresentacao.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LivroException.class)
    private ResponseEntity<RestErroDTO> livroExceptions(LivroException exception) {
        HttpStatus status;

        if (exception.getMessage().equals("Livro não encontrado!")) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        RestErroDTO errorDTO = new RestErroDTO(exception.getMessage());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErroDTO> generalExceptions(Exception exception) {
        log.error("Ocorreu um erro: ", exception);
        RestErroDTO errorDTO = new RestErroDTO("Ocorreu um erro inesperado!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
