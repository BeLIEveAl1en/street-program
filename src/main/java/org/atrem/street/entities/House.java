package org.atrem.street.entities;


import java.util.List;

public class House {
    private final int number;
    private final List<Flat> listOfFlats;

    public House(int number, List<Flat> listOfFlats){
        this.number = number;
        this.listOfFlats = listOfFlats;
    }

    public int getNumber(){
        return number;
    }

    public List<Flat> getListOfFlats(){ return listOfFlats; }
}
