package com.guilhermeonizio.biblioteca.repository;

import com.guilhermeonizio.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
