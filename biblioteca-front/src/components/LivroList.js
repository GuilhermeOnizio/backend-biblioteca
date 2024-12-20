// src/components/LivroList.js
import React, { useEffect, useState } from 'react';
import { listarLivros } from '../services/livroService';

const LivroList = () => {
  const [livros, setLivros] = useState([]); // Inicializa o estado como um array vazio
  const [loading, setLoading] = useState(true); // Estado para controlar o carregamento
  const [error, setError] = useState(null); // Estado para capturar erros

  useEffect(() => {
    const fetchLivros = async () => {
        try {
          const data = await listarLivros();
          console.log(data); // Verifique o que está sendo retornado aqui
          setLivros(data);
        } catch (error) {
          console.error('Erro ao carregar os livros:', error);
          setError('Erro ao carregar os livros.');
        } finally {
          setLoading(false);
        }
      };

    fetchLivros();
  }, []);

  if (loading) {
    return <p>Carregando...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      <h2>Livros Disponíveis</h2>
      <ul>
        {livros.map((livro) => (
          <li key={livro.id}>
            <strong>{livro.titulo}</strong> - {livro.autor} ({livro.anoPublicacao})
            {livro.emprestado ? (
              <span> - Emprestado</span>
            ) : (
              <span> - Disponível</span>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LivroList;
