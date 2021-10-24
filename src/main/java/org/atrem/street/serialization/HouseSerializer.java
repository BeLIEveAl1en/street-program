package org.atrem.street.serialization;

import org.atrem.street.entities.House;
import org.atrem.street.entities.Human;

import java.util.List;

public class HouseSerializer implements Serializer<House>{
    @Override
    public String toJsonObject(House obj) {
        String jsonObj = "{" +
                "\"number\": " + obj.getNumber() + ", \"listOfFlats\": " + new FlatSerializer().toJsonArray(obj.getListOfFlats()) + "}";
        return jsonObj;
    }

    @Override
    public String toJsonArray(List<House> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for(House house: array){
            jsonArray.append(toJsonObject(house)).append(", ");
        }
        jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
