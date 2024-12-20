package com.guilhermeonizio.biblioteca.controller;

import com.guilhermeonizio.biblioteca.model.Livro;
import com.guilhermeonizio.biblioteca.repository.LivroRepository;
import com.guilhermeonizio.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "http://localhost:3000")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarLivros() {
        try {
            return livroService.listarLivros();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar livros", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id) {
        Livro livro = livroService.buscarLivro(id); // O método agora retorna diretamente o objeto Livro
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.adicionarLivro(livro);
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {
        livroService.excluirLivro(id);
    }

    @PutMapping("/emprestar/{livroId}/usuario/{usuarioId}")
    public Livro emprestarLivro(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        return livroService.emprestarLivro(livroId, usuarioId);
    }

}