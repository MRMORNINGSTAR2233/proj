import React, { useState } from 'react';
import './App.css';

function App() {
  const [cityName, setCityName] = useState('');
  const [weatherInfo, setWeatherInfo] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch(`http://localhost:8080/weather?cityname=${encodeURIComponent(cityName)}`)
      .then(response => response.text())
      .then(data => setWeatherInfo(data))
      .catch(error => console.error('Error:', error));
  };

  return (
    <div className="container">
      <h1 className="heading">Welcome to Weather App</h1>
      <form onSubmit={handleSubmit} className="form">
        <input
          type="text"
          value={cityName}
          onChange={e => setCityName(e.target.value)}
          placeholder="Enter the city name"
          className="input"
        />
        <button type="submit" className="button">Submit</button>
      </form>
      <div id='weather-info' className="weather-info">{weatherInfo}</div>
    </div>
  );
}

export default App;
