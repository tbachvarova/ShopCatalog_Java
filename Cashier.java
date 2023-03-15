package com.CITB408_2021;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cashier implements Serializable {
    private String name;
    private int id;
    private static int cashierCounter;
    private BigDecimal salary;


    public Cashier(String name, BigDecimal salary) {
        cashierCounter++;
        this.name = name;
        this.salary = salary;
        this.id = cashierCounter;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getCashierCounter() {
        return cashierCounter;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }
}
