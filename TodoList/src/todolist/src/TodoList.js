import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';

class TodoList extends Component {

  constructor(props) {
    super(props);
    this.state = {activity: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/todos')
      .then(response => response.json())
      .then(data => this.setState({activity: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/todos/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updateActivity = [...this.state.activity].filter(i => i.id !== id);
      this.setState({activity: updateActivity});
    });
  }

  render() {
    const {activity, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const todoList = activity.map(todo => {
      const address = `${todo.amatuer || ''} ${todo.intermediary || ''} ${todo.professional || ''}`;
      return <tr key={todo.id}>
        <td style={{whiteSpace: 'nowrap'}}>{todo.name}</td>
        <td>{address}</td>
        <td>{todo.events.map(event => {
          return <div key={event.id}>{new Intl.DateTimeFormat('en-US', {
            year: 'numeric',
            month: 'long',
            day: '2-digit'
          }).format(new Date(event.date))}: {event.title}</div>
        })}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/api/todos/" + todo.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(todo.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <AppNavbar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/todo/new">Add Activity</Button>
          </div>
          <h3>My Activities</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">Todo Activity</th>
              <th>Events</th>
              <th width="10%">Edit/Delete</th>
            </tr>
            </thead>
            <tbody>
            {todoList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default TodoList;