import './App.css';
import React from "react";
import TransactionList from './TransactionList';
import Summary from './Summary';
import BudgetCategorySelector from './BudgetCategorySelector';
var moment = require("moment");
var apiURL = 'http://localhost:8080';


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading : true,
      Transactions: [],
      Summary: {
        transactionSum: 0,
        budgetSum: 0
      },
      budgetCategories: [],
    }
  }

  componentDidMount = async() => {
    const transactions = await this.fetchTransactions();
    const summary = await this.fetchSummary();
    const newBudgetCategories = await this.fetchBudgetCategories();
    this.setState({
      Transactions: transactions,
      Summary: summary,
      budgetCategories: newBudgetCategories,
      isLoading: false
    });
  }

  componentDidUpdate = async() => {
    const newTansactionList = await this.fetchTransactions();
    const summary = await this.fetchSummary();
    const newBudgetCategories = await this.fetchBudgetCategories();
    this.setState({
      Transactions: newTansactionList,
      Summary: summary,
      budgetCategories: newBudgetCategories
    });
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
    return body;
  }

  fetchSummary = async() => {
    let url = apiURL + '/summary/overall?user_id=71';
    const response = await fetch(url, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    const body = await response.json();
    return body;
  }

  fetchBudgetCategories = async() => {
    let url = apiURL + '/budget';
    const response = await fetch(url, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
    const body = await response.json();
    return body;
  }

  submitTransaction = async(e) => {
    e.preventDefault();
    let form = e.target.elements;
    let budgetCategory = document.getElementsByTagName("select").namedItem("BudgetCategorySelector").value;
    let budgetCategoryId = this.state.budgetCategories.find(category => category.name === budgetCategory).id;
    let transaction = {
      "memo": form.memo.value,
      "value": form.value.value * 100,
      "budgetCategoryId": budgetCategoryId,
      "dateTransaction": form.dateTransaction.value,
      "dateCreated": moment().format('YYYY-MM-DD')
    }
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
    form.dateTransaction.value = "";
  }

  handleDeleteTransaction = async(e) => {
    let url = apiURL + '/transactions/' + e.target.value;
    await fetch(url, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
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
        <Summary
          transactionSum={this.state.Summary.transactionSum}
          moneyPrint={this.convertToPrettyMoney}
        />
        <form onSubmit={this.submitTransaction}>
          <label id="memo">Memo:</label>
          <input type="text" id="memo"/>
          <label id="dateTransaction">Date of Transaction:</label>
          <input type="date" id="dateTransaction"/>
          <label id="value">Amount:</label>
          <input type="number" id="value" step="0.01"/>
          <label id="budgetCategory">Category:</label>
          <BudgetCategorySelector BudgetCategories={this.state.budgetCategories}/>
          <button type="submit">Submit</button>
        </form>
        <TransactionList
          Transactions={this.state.Transactions}
          BudgetCategories={this.state.budgetCategories}
          moneyPrint={this.convertToPrettyMoney}
          deleteFunction={this.handleDeleteTransaction}
        />
      </div>
    );
  }
}

export default App;
