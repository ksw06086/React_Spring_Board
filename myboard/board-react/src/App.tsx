import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';

function App() {
  const [connection, setConnection] = useState<string>('');

  const connectionTest = () => {
    axios.get('http://localhost:4000/').then((res) => {
      setConnection(res.data);
    }).catch((err) => {
      setConnection(err.message);
    });
  }

  useEffect(()=>{
    connectionTest();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>{connection}</p>
      </header>
    </div>
  );
}

export default App;
