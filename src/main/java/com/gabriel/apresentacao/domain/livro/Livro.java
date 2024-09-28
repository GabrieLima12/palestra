package com.gabriel.apresentacao.domain.livro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "livro")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private LocalDate dataPublicacao;
    private String autor;
    private String categoria;

    public Livro(LivroCadastro livroCadastro) {
        this.nome = livroCadastro.nome();
        this.dataPublicacao = livroCadastro.dataPublicacao();
        this.autor = livroCadastro.autor();
        this.categoria = livroCadastro.categoria();
    }
}
