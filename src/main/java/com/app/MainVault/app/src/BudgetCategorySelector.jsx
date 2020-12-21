import './App.css';
import React from "react";

class BudgetCategorySelector extends React.Component {
  render() {
    return (
      <select name="BudgetCategorySelector">
        {this.props.BudgetCategories.map(budgetCategory => {
            return (
              <option
                value={budgetCategory.name}
                key={budgetCategory.id}
              >
                {budgetCategory.name}
              </option>
            );
          })
        }
      </select>
    );
  }
}

export default BudgetCategorySelector;
