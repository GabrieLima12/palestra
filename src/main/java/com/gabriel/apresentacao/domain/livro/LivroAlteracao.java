package com.gabriel.apresentacao.domain.livro;

import java.time.LocalDate;

public record LivroAlteracao(
        String nome,
        LocalDate dataPublicacao,
        String autor,
        String categoria
) {
}
