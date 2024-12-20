package com.guilhermeonizio.biblioteca.service;

import com.guilhermeonizio.biblioteca.exception.ResourceNotFoundException;
import com.guilhermeonizio.biblioteca.exception.BusinessException;
import com.guilhermeonizio.biblioteca.model.Livro;
import com.guilhermeonizio.biblioteca.model.Usuario;
import com.guilhermeonizio.biblioteca.repository.LivroRepository;
import com.guilhermeonizio.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivro(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public void excluirLivro(Long id) {
        Livro livro = buscarLivro(id); // Garante que o livro exista antes de excluir
        livroRepository.delete(livro);
    }

    public Livro emprestarLivro(Long livroId, Long usuarioId) {
        // Verifica se o livro existe
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro com ID " + livroId + " não encontrado."));

        // Verifica se o livro já está emprestado
        if (livro.isEmprestado()) {
            throw new BusinessException("O livro já está emprestado.");
        }

        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + usuarioId + " não encontrado."));

        // Atualiza o status do livro e associa ao usuário
        livro.setEmprestado(true);
        livro.setUsuario(usuario);

        return livroRepository.save(livro);
    }
}
