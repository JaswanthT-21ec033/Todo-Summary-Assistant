import React from 'react';
import TodoForm from './components/TodoForm';
import TodoList from './components/TodoList';
import SummarizeButton from './components/SummarizeButton';
import './App.css'; 

function App() {
  return (
    <div className="App">
      <h1 className="app-title">Todo Summary Assistant</h1>
      <TodoForm />
      <TodoList />
      <SummarizeButton />
    </div>
  );
}

export default App;
