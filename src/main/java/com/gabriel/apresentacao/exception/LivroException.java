package com.gabriel.apresentacao.exception;

public class LivroException extends RuntimeException {

    public LivroException(String message) {
        super(message);
    }

    public LivroException() {
        super("Livro não encontrado!");
    }
}
