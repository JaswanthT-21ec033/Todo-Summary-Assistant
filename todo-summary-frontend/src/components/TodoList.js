import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Todo.css';

export default function TodoList() {
  const [todos, setTodos] = useState([]);

  const loadTodos = async () => {
    try {
      const response = await api.get('/view');
      setTodos(response.data);
    } catch (error) {
      console.error('Failed to load todos:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/delete/${id}`);
      loadTodos();
    } catch (error) {
      console.error(`Delete failed for ID ${id}:`, error);
    }
  };

  const handleUpdate = async (id, currentText) => {
    const updatedText = prompt('Enter new text:', currentText);
    if (!updatedText || updatedText.trim() === '') return;

    try {
      await api.put(`/update/${id}`, { text: updatedText });
      loadTodos();
    } catch (error) {
      console.error(`Update failed for ID ${id}:`, error);
    }
  };

  useEffect(() => {
    loadTodos();
  }, []);

  return (
    <div className="todo-container">
      <h2>Todo List</h2>
      <ul className="todo-list">
        {todos.length === 0 ? (
          <p>No todos available</p>
        ) : (
          todos.map(todo => (
            <li key={todo.id} className="todo-item">
              <span>{todo.text}</span>
              <div className="btn-group">
                <button className="delete-btn" onClick={() => handleDelete(todo.id)}>Delete</button>
                <button className="update-btn" onClick={() => handleUpdate(todo.id, todo.text)}>Update</button>
              </div>
            </li>
          ))
        )}
      </ul>
    </div>
  );
}
