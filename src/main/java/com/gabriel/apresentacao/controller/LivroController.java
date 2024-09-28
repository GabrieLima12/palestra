package com.gabriel.apresentacao.controller;

import com.gabriel.apresentacao.domain.livro.LivroAlteracao;
import com.gabriel.apresentacao.domain.livro.LivroCadastro;
import com.gabriel.apresentacao.domain.livro.LivroDTO;
import com.gabriel.apresentacao.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarLivros(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String nome) {
        if (nome != null)
            return ResponseEntity.ok(livroService.listarLivrosContains(page, size, nome));
        return ResponseEntity.ok(livroService.listarLivros(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> listarLivro(@PathVariable Integer id) {
        LivroDTO livroDTO = livroService.livroPorId(id);
        return livroDTO != null ? ResponseEntity.ok(livroDTO) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> cadastrarLivro(@RequestBody LivroCadastro livroCadastro) {
        return new ResponseEntity<>(livroService.cadastrarLivro(livroCadastro), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivroDTO> alterarLivro(@PathVariable Integer id, @RequestBody LivroAlteracao livroAlteracao) {
        LivroDTO livroDTO = livroService.alterarLivro(id, livroAlteracao);
        return livroDTO != null ? ResponseEntity.ok(livroDTO) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroDTO> deletarLivro(@PathVariable Integer id) {
        livroService.deletarLivro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
