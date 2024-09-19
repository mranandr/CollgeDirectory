import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../Login.css'

function Login({ setUserRole }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    
    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/users/login', {
                username,
                password
            });

            if (response.status === 200) {
                const { data } = response;

                if (data.token && data.role) {
                    localStorage.setItem('token', data.token);
                    localStorage.setItem('role', data.role);
                    setUserRole(data.role);

                    switch (data.role) {
                        case 'STUDENT':
                            navigate('/student-dashboard');
                            break;
                        case 'FACULTY':
                            navigate('/faculty-dashboard');
                            break;
                        case 'ADMIN':
                            navigate('/admin-dashboard');
                            break;
                        default:
                            navigate('/');
                            break;
                    }
                } else {
                    setError('Unexpected response format');
                }
            } else {
                setError('Login failed: ' + (response.data.message || 'Unknown error'));
            }
        } catch (error) {
            console.error('Error during login:', error);
            setError('Login failed: ' + (error.response?.data.message || 'An unknown error occurred'));
        }
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            <form onSubmit={handleLogin}>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {error && <p className="error-message">{error}</p>}
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;
