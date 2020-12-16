package com.app.MainVault;

import javax.persistence.*;

@Entity
@Table(name = "budgetCategories")
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private String name;
    private int monthlyAllocation;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlyAllocation() {
        return monthlyAllocation;
    }

    public void setMonthlyAllocation(int monthlyAllocation) {
        this.monthlyAllocation = monthlyAllocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
