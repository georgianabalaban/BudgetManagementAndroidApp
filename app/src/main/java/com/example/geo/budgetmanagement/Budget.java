package com.example.geo.budgetmanagement;

/**
 * Created by geo on 24.11.2017.
 */

public class Budget {
    private double salary;
    private double chcasnice;
    private double chmancare;
    private double chtransport;
    public Budget(){

    }

    public Budget(double salary, double chcasnice, double chmancare, double chtransport) {
        this.salary = salary;
        this.chcasnice = chcasnice;
        this.chmancare = chmancare;
        this.chtransport = chtransport;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getChcasnice() {
        return chcasnice;
    }

    public void setChcasnice(double chcasnice) {
        this.chcasnice = chcasnice;
    }

    public double getChmancare() {
        return chmancare;
    }

    public void setChmancare(double chmancare) {
        this.chmancare = chmancare;
    }

    public double getChtransport() {
        return chtransport;
    }

    public void setChtransport(double chtransport) {
        this.chtransport = chtransport;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "salary=" + salary +
                ", chcasnice=" + chcasnice +
                ", chmancare=" + chmancare +
                ", chtransport=" + chtransport +
                '}';
    }

}
