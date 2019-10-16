import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';

class TodoEdit extends Component {

  emptyItem = {
    name: '',
    amatuer: '',
    intermediary: '',
    professional: '',
    expert: '',
    master: ''
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== 'new') {
      const todo = await (await fetch(`/api/todo/${this.props.match.params.id}`)).json();
      this.setState({item: todo});
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = {...this.state.item};
    item[name] = value;
    this.setState({item});
  }

  async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/todo', {
      method: (item.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(item),
    });
    this.props.history.push('/todos');
  }

  render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit List' : 'Add List'}</h2>;

    return <div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={item.name || ''}
                   onChange={this.handleChange} autoComplete="name"/>
          </FormGroup>
          <FormGroup>
            <Label for="amatuer">Amatuer</Label>
            <Input type="text" name="amatuer" id="amatuer" value={item.amatuer || ''}
                   onChange={this.handleChange} autoComplete="amatuer-level1"/>
          </FormGroup>
          <FormGroup>
            <Label for="intermediary">Intermediary</Label>
            <Input type="text" name="intermediary" id="intermediary" value={item.city || ''}
                   onChange={this.handleChange} autoComplete="intermediary-level1"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="professional">Professional</Label>
              <Input type="text" name="professional" id="professional" value={item.stateOrProvince || ''}
                     onChange={this.handleChange} autoComplete="professional-level1"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="expert">Expert</Label>
              <Input type="text" name="expert" id="expert" value={item.country || ''}
                     onChange={this.handleChange} autoComplete="expert-level1"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="master">Master</Label>
              <Input type="text" name="master" id="master" value={item.postalCode || ''}
                     onChange={this.handleChange} autoComplete="master-level1"/>
            </FormGroup>
          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/todos">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  }
}

export default withRouter(TodoEdit);