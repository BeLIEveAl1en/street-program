package org.atrem.street.entities;


import java.util.List;
import java.util.Objects;

public class House {
    private final int number;
    private final List<Flat> listOfFlats;

    public House(int number, List<Flat> listOfFlats) {
        this.number = number;
        this.listOfFlats = listOfFlats;
    }

    public int getNumber() {
        return number;
    }

    public List<Flat> getListOfFlats() {
        return listOfFlats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(number, house.number) && listOfFlats.equals(house.listOfFlats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, listOfFlats);
    }
}
