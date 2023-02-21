package org.atrem.street.serialization;

import org.atrem.street.entities.House;
import org.atrem.street.entities.Human;

import java.util.List;

public class HouseSerializer implements Serializer<House> {

    private final FlatSerializer FLAT_SERIALIZER = new FlatSerializer();

    @Override
    public String toJsonObject(House obj) {
        return "{\"number\":" + obj.getNumber() + ",\"listOfFlat\":" + FLAT_SERIALIZER.toJsonArray(obj.getListOfFlats()) + "}";
    }

    @Override
    public String toJsonArray(List<House> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        if (array != null) {
            for (House house : array) {
                jsonArray.append(toJsonObject(house)).append(", ");
            }
            jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        }
        jsonArray.append("]");
        return jsonArray.toString().replaceAll("\\s", "");
    }
}
