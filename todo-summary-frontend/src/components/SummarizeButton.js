import React, { useState } from 'react';
import api from '../services/api';
import './Todo.css';

export default function SummarizeButton() {
  const [summary, setSummary] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSummarize = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await api.post('/summarize');
      setSummary(response.data);
    } catch (err) {
      console.error('Summarize failed:', err);
      setError('Failed to generate summary.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="summarize-container">
      <button className="summarize-button" onClick={handleSummarize} disabled={loading}>
        {loading ? 'Summarizing...' : 'Summarize Todos'}
      </button>

      {error && <p className="error-msg">{error}</p>}

      {summary && (
        <div className="summary-box">
          <h4>Summary:</h4>
          <pre>{summary}</pre>
        </div>
      )}
    </div>
  );
}
