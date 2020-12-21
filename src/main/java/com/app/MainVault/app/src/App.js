import './App.css';
import React from "react";
import TransactionList from './TransactionList';
import Summary from './Summary';
var moment = require("moment");
var apiURL = 'http://localhost:8080';


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading : true,
      App: [],
      Transactions: [],
      Summary: {
        transactionSum: 0,
        budgetSum: 0
      }
    }
  }

  componentDidMount = async() => {
    this.fetchTransactions();
    this.fetchSummary();
  }

  fetchTransactions = async() => {
    let url = apiURL + '/transactions';
    const response = await fetch(url, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    const body = await response.json();
    this.setState({Transactions: body, isLoading: false});
  }

  fetchSummary = async() => {
    let url = apiURL + '/summary/overall?user_id=36';
    const response = await fetch(url, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    const body = await response.json();
    this.setState({Summary: body, isLoading: false})
  }

  submitTransaction = async(e) => {
    e.preventDefault();
    let form = e.target.elements;
    let transaction = {
      "memo": form.memo.value,
      "value": form.value.value,
      "budgetCategoryId": form.budgetCategory.value,
      "dateTransaction": form.dateTransaction.value,
      "dateCreated": moment().format('YYYY-MM-DD')
    }
    console.log(transaction);
    let url = apiURL + '/transactions';
    await fetch(url, {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(transaction),
    });
    // clear the form fields
    form.memo.value = "";
    form.value.value = "";
    form.budgetCategory.value = "";
    form.dateTransaction.value = "";
    // update transaction list
    this.fetchTransactions();
    this.fetchSummary();
  }

  convertToPrettyMoney = (value) => {
    let str = "";
    let valueStr = value.toString();
    let length = valueStr.length;
    if (length > 2) {
      str = `${valueStr.slice(0, -2)}.${valueStr.slice(-2)}`;
    } else if (length === 2) {
      str = `0.${value}`;
    } else if (length === 1) {
      str = `0.0${value}`;
    } else {
      str = value;
    }
    str = `$${str}`
    return str;
  }

  render() {
    return (
      <div className="App-header">
        <h1>Budget App Pre-Alpha Alpha</h1>
        <Summary transactionSum={this.state.Summary.transactionSum} moneyPrint={this.convertToPrettyMoney}/>
        <form onSubmit={this.submitTransaction}>
          <label id="memo">Memo:</label>
          <input type="text" id="memo"/>
          <label id="dateTransaction">Date of Transaction:</label>
          <input type="date" id="dateTransaction"/>
          <label id="value">Amount:</label>
          <input type="number" id="value"/>
          <label id="budgetCategory">Category:</label>
          <input type="number" id="budgetCategory"/>
          <button type="submit">Submit</button>
        </form>
        <TransactionList Transactions={this.state.Transactions} moneyPrint={this.convertToPrettyMoney}/>
      </div>
    );
  }
}

export default App;
