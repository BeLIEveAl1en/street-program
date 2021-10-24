package org.atrem.street.serialization;

import org.atrem.street.entities.Flat;

import java.util.List;

public class FlatSerializer implements Serializer<Flat>{

    @Override
    public String toJsonObject(Flat flat) {
        String jsonObj = "{" +
                "\"number\": " + flat.getNumber() + ", " + "\"listOfHuman\": " + new HumanSerializer().toJsonArray(flat.getHumans()) + "}";
        return jsonObj;
    }

    @Override
    public String toJsonArray(List<Flat> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for(Flat flat: array){
            jsonArray.append(toJsonObject(flat)).append(", ");
        }
        jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
