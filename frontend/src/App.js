import React, { useState } from 'react';
import LoginForm from './components/LoginForm';

function App() {
  // Login state
  const [user, setUser] = useState(null);
  
  // Expense state
  const [expenses, setExpenses] = useState([]);
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');

  // Handle login from LoginForm component
  const handleLogin = (username) => {
    setUser(username);
  };

  // Logout function
  const logout = () => {
    setUser(null);
  };

  const addExpense = async () => {
    if (!name || !amount) {
      alert('Fill both fields!');
      return;
    }

    try {
      await fetch('/api/expenses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: name,
          amount: parseFloat(amount),
          expenseDate: new Date().toISOString()
        })
      });
      
      setName('');
      setAmount('');
      alert('Added!');
      showExpenses();
    } catch (error) {
      alert('Error!');
    }
  };

  const showExpenses = async () => {
    try {
      const response = await fetch('/api/expenses');
      const data = await response.json();
      setExpenses(data);
    } catch (error) {
      alert('Error loading!');
    }
  };

  const deleteAll = async () => {
    if (window.confirm('Delete all?')) {
      try {
        await fetch('/api/expenses', { method: 'DELETE' });
        setExpenses([]);
        alert('Deleted!');
      } catch (error) {
        alert('Error!');
      }
    }
  };

  const deleteExpense = async (id) => {
    if (window.confirm('Delete this expense?')) {
      try {
        await fetch(`/api/expenses/${id}`, { method: 'DELETE' });
        showExpenses(); // Refresh the list
        alert('Expense deleted!');
      } catch (error) {
        alert('Error deleting expense!');
      }
    }
  };

  // If not logged in, show login component
  if (!user) {
    return <LoginForm onLogin={handleLogin} />;
  }

  // If logged in, show expense tracker
  return (
    <div>
      <h1>Expense Tracker</h1>
      <p>Welcome, {user}! <button onClick={logout}>Logout</button></p>
      
      <h2>Add Expense</h2>
      <input 
        type="text" 
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Name"
      />
      <input 
        type="number" 
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        placeholder="Amount"
      />
      <button onClick={addExpense}>Add</button>
      
      <br /><br />
      
      <button onClick={showExpenses}>Show All</button>
      <button onClick={deleteAll}>Delete All</button>
      
      <h2>Expenses ({expenses.length})</h2>
      {expenses.length === 0 ? (
        <p>No expenses</p>
      ) : (
        expenses.map((expense, index) => (
          <div key={index}>
            {expense.name} - ${expense.amount}
            <button onClick={() => deleteExpense(expense.id)}>Delete</button>
          </div>
        ))
      )}
    </div>
  );
}

export default App;
