package org.atrem.street.entities;

import java.util.List;
import java.util.Objects;

public class Flat {
    private final int number;
    private final List<Human> humans;

    public Flat(int number, List<Human> humans){
        this.number = number;
        this.humans = humans;
    }

    public int getNumber(){
        return number;
    }

    public List<Human> getHumans(){
        return humans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Objects.equals(number, flat.number) && humans.equals(flat.humans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, humans);
    }
}
