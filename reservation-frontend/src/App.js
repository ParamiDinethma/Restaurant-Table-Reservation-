// reservation-frontend/src/App.js
import React, { useState } from 'react';
import axios from 'axios';
import TableList from './TableList'; // Component we will create next
import './App.css'; // Assuming default CSS or custom styles

const API_BASE_URL = 'http://localhost:8080/api/reservations';

function App() {
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [partySize, setPartySize] = useState(2);
  const [availableTables, setAvailableTables] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setAvailableTables([]);

    try {
      const response = await axios.get(
        `${API_BASE_URL}/available?date=${date}&time=${time}&partySize=${partySize}`
      );

      // Spring returns 200 with data or 204 No Content
      if (response.data.length > 0) {
        setAvailableTables(response.data);
      } else {
         // Handle explicit empty array case if 200 is returned with no tables
         setError('No tables available for this time slot.');
      }
    } catch (err) {
      // Check for the specific 204 No Content error status if thrown by the backend/axios
      if (err.response && err.response.status === 204) {
        setError('No tables available for this time slot.');
      } else {
        console.error(err);
        setError('Error searching for tables. Ensure your backend is running.');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <h1>Restaurant Reservation System</h1>
      <form onSubmit={handleSearch} className="search-form">
        <h2>Find Your Table</h2>
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
        <input type="time" value={time} onChange={(e) => setTime(e.target.value)} required />
        <input type="number" value={partySize} onChange={(e) => setPartySize(e.target.value)} min="1" required />
        <button type="submit" disabled={loading || !date || !time}>
          {loading ? 'Searching...' : 'Search Availability'}
        </button>
      </form>

      {error && <p className="error">{error}</p>}

      {availableTables.length > 0 && (
        <TableList 
          tables={availableTables} 
          bookingDetails={{ date, time, partySize }}
        />
      )}
    </div>
  );
}

export default App;