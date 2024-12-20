// src/components/LivroForm.js
import React, { useState } from 'react';
import { adicionarLivro } from '../services/livroService';

const LivroForm = () => {
  const [titulo, setTitulo] = useState('');
  const [autor, setAutor] = useState('');
  const [genero, setGenero] = useState('');
  const [anoPublicacao, setAnoPublicacao] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const livro = {
      titulo,
      autor,
      genero,
      anoPublicacao: parseInt(anoPublicacao),
      emprestado: false,
    };

    await adicionarLivro(livro);
    setTitulo('');
    setAutor('');
    setGenero('');
    setAnoPublicacao('');
  };

  return (
    <div>
      <h2>Adicionar Livro</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Título"
          value={titulo}
          onChange={(e) => setTitulo(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Autor"
          value={autor}
          onChange={(e) => setAutor(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Gênero"
          value={genero}
          onChange={(e) => setGenero(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Ano de Publicação"
          value={anoPublicacao}
          onChange={(e) => setAnoPublicacao(e.target.value)}
          required
        />
        <button type="submit">Adicionar Livro</button>
      </form>
    </div>
  );
};

export default LivroForm;
