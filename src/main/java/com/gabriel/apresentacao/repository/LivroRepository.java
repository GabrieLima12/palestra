package com.gabriel.apresentacao.repository;

import com.gabriel.apresentacao.domain.livro.Livro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findByNomeContainingAndDeletadoFalse(Pageable pageable, String nome);
    List<Livro> findAllByDeletadoFalse(Pageable pageable);
}
