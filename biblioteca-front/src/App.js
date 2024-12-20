// src/App.js
import React from 'react';
import LivroList from './components/LivroList';
import LivroForm from './components/LivroForm';
import EmprestarLivroForm from './components/EmprestarLivroForm';

function App() {
  return (
    <div className="App">
      <h1>Sistema de Biblioteca</h1>
      <LivroForm />
      <EmprestarLivroForm />
      <LivroList />
    </div>
  );
}

export default App;
