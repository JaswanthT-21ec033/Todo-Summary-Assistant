import React, { useState } from 'react';
import api from '../services/api';
import './Todo.css'; 

export default function TodoForm() {
  const [text, setText] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (text.trim() === '') {
      setError('Todo cannot be empty');
      return;
    }

    try {
      await api.post('/add', { text });
      setText('');
      setError('');
    } catch (err) {
      console.error('Error adding todo:', err);
      setError('Failed to add todo. Please try again.');
    }
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <input
        className="todo-input"
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Enter a new todo"
      />
      <button type="submit" className="todo-button">Add</button>
      {error && <p className="error-msg">{error}</p>}
    </form>
  );
}
