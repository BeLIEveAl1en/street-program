package org.atrem.street.serialization;

import org.atrem.street.entities.House;

import java.util.List;

public class HouseSerializer implements Serializer<House> {
    @Override
    public String toJsonObject(House obj) {
        return "{" +
                "\"number\": " + obj.getNumber() + ", \"listOfFlats\": " + new FlatSerializer().toJsonArray(obj.getListOfFlats()) + "}";
    }

    @Override
    public String toJsonArray(List<House> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for (House house : array) {
            jsonArray.append(toJsonObject(house)).append(", ");
        }
        jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
