import './App.css';
import React from "react";

class TransactionList extends React.Component {
  render() {
    return (
      <table>
        <thead>
          <tr>
            <th>Memo</th>
            <th className="transactionInfo">Transaction Date</th>
            <th className="moneyColumn">Amount</th>
            <th className="transactionInfo">Budget Category</th>
          </tr>
        </thead>
        <tbody>
          {this.props.Transactions.map(transaction => {
            let budgetCategory = this.props.BudgetCategories.find(category => category.id === transaction.budgetCategoryId);
            return (
              <tr key={transaction.id}>
                <td>{transaction.memo}</td>
                <td className="transactionInfo">{transaction.dateTransaction}</td>
                <td className="moneyColumn">{this.props.moneyPrint(transaction.value)}</td>
                <td className="transactionInfo">{budgetCategory.name}</td>
                <td>
                  <button
                    type="button"
                    value = {transaction.id}
                    id="deleteTransaction"
                    onClick={this.props.deleteFunction}>
                      X
                  </button>
                </td>
              </tr>
            )
          })}
        </tbody>
      </table>
    );
  }
}

export default TransactionList;
