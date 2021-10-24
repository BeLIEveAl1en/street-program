package org.atrem.street.entities;

import java.util.List;

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
}
