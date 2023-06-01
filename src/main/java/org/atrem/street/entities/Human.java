package org.atrem.street.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Human {
    private final String lastName;
    private final String name;
    private int money;
    private final List<Pet> listOfPet = new ArrayList<>();

    public Human(String name, String lastName, int money) {
        this.lastName = lastName;
        this.name = name;
        this.money = money;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Pet> getListOfPet() {
        return listOfPet;
    }

    public List<Pet> addPets(List<Pet> listOfPet) {
        this.listOfPet.addAll(listOfPet);
        return listOfPet;
    }

    public int setMoney(int money) {
        return this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(lastName, human.lastName) && money == human.money && listOfPet.equals(human.listOfPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, money, listOfPet);
    }
}



