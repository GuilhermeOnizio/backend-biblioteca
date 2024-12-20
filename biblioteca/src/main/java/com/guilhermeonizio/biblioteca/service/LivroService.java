package com.guilhermeonizio.biblioteca.service;

import com.guilhermeonizio.biblioteca.model.Livro;
import com.guilhermeonizio.biblioteca.model.Usuario;
import com.guilhermeonizio.biblioteca.repository.LivroRepository;
import com.guilhermeonizio.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarLivro(Long id) {
        return livroRepository.findById(id);
    }

    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro emprestarLivro(Long livroId, Long usuarioId) {
        Optional<Livro> livroOptional = livroRepository.findById(livroId);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            if (!livro.isEmprestado()) {
                Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

                if (usuarioOptional.isPresent()) {
                    Usuario usuario = usuarioOptional.get();
                    livro.setEmprestado(true);
                    livro.setUsuario(usuario);

                    return livroRepository.save(livro);
                } else {
                    throw new IllegalArgumentException("Usuário com ID " + usuarioId + " não encontrado.");
                }
            } else {
                throw new IllegalStateException("O livro já está emprestado.");
            }
        } else {
            throw new IllegalArgumentException("Livro com ID " + livroId + " não encontrado.");
        }
    }
}
