package com.guilhermeonizio.biblioteca.controller;

import com.guilhermeonizio.biblioteca.model.Livro;
import com.guilhermeonizio.biblioteca.repository.LivroRepository;
import com.guilhermeonizio.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @GetMapping("/{id}")
    public Optional<Livro> buscarLivro(@PathVariable Long id) {
        return livroService.buscarLivro(id);
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