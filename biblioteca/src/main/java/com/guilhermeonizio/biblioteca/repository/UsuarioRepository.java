package com.guilhermeonizio.biblioteca.repository;

import com.guilhermeonizio.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
