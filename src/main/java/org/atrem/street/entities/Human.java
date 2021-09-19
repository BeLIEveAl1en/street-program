package org.atrem.street.entities;

public class Human {
    private final String lastName;
    private final String name;
    private int money;

    public Human(String name, String lastName, int money){
        this.lastName = lastName;
        this.name = name;
        this.money = money;
    }

    public String getLastName(){
        return lastName;
    }

    public String getName(){
        return name;
    }

    public int getMoney(){
        return money;
    }

    public int setMoney(int money){
        return this.money = money;
    }
}



