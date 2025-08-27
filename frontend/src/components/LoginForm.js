import React, { useState } from 'react';

function LoginForm({ onLogin }) {
  // Login form state
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  // Handle login submission
  const handleLogin = () => {
    if (!username || !password) {
      alert('Enter both username and password!');
      return;
    }
    if (username === 'user' && password === 'user') {
      onLogin(username); // Call parent function with username
    } else {
      alert('Invalid credentials! Use username: user, password: user');
    }
  };

  return (
    <div>
      <h1>Login</h1>
      <input 
        type="text" 
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username: user"
      />
      <input 
        type="password" 
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password: user"
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}

export default LoginForm;
