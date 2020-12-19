import './App.css';
import React from "react";

class Summary extends React.Component {
  render() {
    return (
      <h2>Total Spent: {this.props.moneyPrint(this.props.transactionSum)}</h2>
    );
  }
}

export default Summary;
