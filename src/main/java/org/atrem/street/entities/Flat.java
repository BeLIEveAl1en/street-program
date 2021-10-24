package org.atrem.street.entities;

import java.util.ArrayList;
import java.util.List;

public class Flat {
    private final int number;
    public List listOfHuman;

    public Flat(int number, List listOfHuman){
        this.number = number;
        this.listOfHuman = listOfHuman;
    }

    public int getNumber(){
        return number;
    }

    public List getListOfHuman(int index){
        return (ArrayList) listOfHuman.get(index);
    }
}
