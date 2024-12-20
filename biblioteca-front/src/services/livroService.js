// src/services/livroService.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/livros',
});

export const listarLivros = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/livros');
    return response.data;
  } catch (error) {
    console.error('Erro ao listar livros:', error.response ? error.response.data : error.message);
    throw error; // Lançar erro novamente para ser capturado no componente
  }
};

export const buscarLivro = async (id) => {
  try {
    const response = await api.get(`/${id}`);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar livro', error);
  }
};

export const emprestarLivro = async (livroId, usuarioId) => {
  try {
    const response = await api.post(`/${livroId}/emprestar/${usuarioId}`);
    return response.data;
  } catch (error) {
    console.error('Erro ao emprestar livro', error);
  }
};

export const adicionarLivro = async (livro) => {
  try {
    const response = await api.post('/', livro);
    return response.data;
  } catch (error) {
    console.error('Erro ao adicionar livro', error);
  }
};
