package org.atrem.street.entities;

import java.util.ArrayList;
import java.util.List;

public class Human {
    private final String lastName;
    private final String name;
    private int money;
    private final List<Pet> listOfPet = new ArrayList<>();

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

    public List<Pet> getListOfPet(){
        return listOfPet;
    }

    public int setMoney(int money){
        return this.money = money;
    }
}



