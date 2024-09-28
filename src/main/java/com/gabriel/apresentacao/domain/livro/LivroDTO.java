package com.gabriel.apresentacao.domain.livro;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LivroDTO(
        Integer id,
        String nome,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPublicacao,
        String autor,
        String categoria
) {
    public LivroDTO(Livro livro) {
        this(livro.getId(), livro.getNome(), livro.getDataPublicacao(), livro.getAutor(), livro.getCategoria());
    }
}
