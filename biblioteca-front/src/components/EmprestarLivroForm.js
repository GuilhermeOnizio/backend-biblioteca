// src/components/EmprestarLivroForm.js
import React, { useState } from 'react';
import { emprestarLivro } from '../services/livroService';

const EmprestarLivroForm = () => {
  const [livroId, setLivroId] = useState('');
  const [usuarioId, setUsuarioId] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    await emprestarLivro(livroId, usuarioId);
    setLivroId('');
    setUsuarioId('');
  };

  return (
    <div>
      <h2>Emprestar Livro</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="ID do Livro"
          value={livroId}
          onChange={(e) => setLivroId(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="ID do Usuário"
          value={usuarioId}
          onChange={(e) => setUsuarioId(e.target.value)}
          required
        />
        <button type="submit">Emprestar Livro</button>
      </form>
    </div>
  );
};

export default EmprestarLivroForm;
