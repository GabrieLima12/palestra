package com.gabriel.apresentacao.service;

import com.gabriel.apresentacao.domain.livro.Livro;
import com.gabriel.apresentacao.domain.livro.LivroAlteracao;
import com.gabriel.apresentacao.domain.livro.LivroCadastro;
import com.gabriel.apresentacao.domain.livro.LivroDTO;
import com.gabriel.apresentacao.exception.LivroException;
import com.gabriel.apresentacao.repository.LivroRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroDTO> listarLivros(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return livroRepository.findAll(pageable).stream().map(LivroDTO::new).toList();
    }

    public List<LivroDTO> listarLivrosContains(
            int page,
            int size,
            String nome
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return livroRepository.findByNomeContaining(pageable, nome).stream().map(LivroDTO::new).toList();
    }

    public LivroDTO livroPorId(Integer id){
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null)
            return new LivroDTO(livro);
        throw new LivroException();
    }

    public LivroDTO cadastrarLivro(LivroCadastro livroCadastro){
        Livro livro = new Livro(livroCadastro);
        return new LivroDTO(livroRepository.save(livro));
    }

    public LivroDTO alterarLivro(Integer id, LivroAlteracao livroAlteracao) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro != null) {
            Optional.ofNullable(livroAlteracao.nome()).ifPresent(livro::setNome);
            Optional.ofNullable(livroAlteracao.dataPublicacao()).ifPresent(livro::setDataPublicacao);
            Optional.ofNullable(livroAlteracao.autor()).ifPresent(livro::setAutor);
            Optional.ofNullable(livroAlteracao.categoria()).ifPresent(livro::setCategoria);
            return new LivroDTO(livroRepository.save(livro));
        }
        throw new LivroException();
    }

    public void deletarLivro(Integer id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();
            livroRepository.delete(livro);
        } else {
            throw new LivroException();
        }
    }
}
