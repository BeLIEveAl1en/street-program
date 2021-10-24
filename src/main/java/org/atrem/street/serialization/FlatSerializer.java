package org.atrem.street.serialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.List;

public class FlatSerializer implements Serializer<Flat>{

    @Override
    public String toJsonObject(Flat flat) {
        String jsonObj = "{" +
                "\"number\": " + "\"" + flat.getNumber() + "\"" + "}";
        return jsonObj;
    }

    @Override
    public String toJsonArray(List<Flat> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for(Flat flat: array){
            if (!flat.equals(array.get(array.size() - 1)))
                jsonArray.append("{" + "\"number\": " + "\"").append(flat.getNumber()).append("\"listOfHuman\": ").append(new PetSerializer().toJsonArray(flat.listOfHuman)).append("}" + ", ");
            else
                jsonArray.append("{" + "\"number\": " + "\"").append(flat.getNumber()).append("\"listOfHuman\": ").append(new PetSerializer().toJsonArray(flat.listOfHuman)).append("}");
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
