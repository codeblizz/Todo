import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TodoList from './TodoList';
import TodoEdit from './TodoEdit';
import { CookiesProvider } from 'react-cookie'

class App extends Component {
  render() {
    return (
      <CookiesProvider>
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/api/todos' exact={true} component={TodoList}/>
          <Route path='/api/todo/:id' component={TodoEdit}/>
        </Switch>
      </Router>
      </CookiesProvider>
    )
  }
}


export default App;