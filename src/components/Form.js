import React, { useState } from 'react';
import axios from 'axios';

const Form = ({ setWeather, setError }) => {
  const [cityName, setCityName] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/', { cityname: cityName });
      setWeather(response.data);
    } catch (error) {
      setError('Error fetching weather information');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Enter the city name"
        value={cityName}
        onChange={(e) => setCityName(e.target.value)}
      />
      <button type="submit">Submit</button>
    </form>
  );
};

export default Form;
