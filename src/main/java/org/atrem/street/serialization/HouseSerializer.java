package org.atrem.street.serialization;

import org.atrem.street.entities.House;
import org.atrem.street.entities.Human;

import java.util.List;

public class HouseSerializer implements Serializer<House> {

    private final FlatSerializer FlAT_DESERIALIZER = new FlatSerializer();
    private final String FORMAT_STRING = "{\"number\":%s,\"listOfFlat\":%s}";

    @Override
    public String toJsonObject(House obj) {
        return String.format(FORMAT_STRING, obj.getNumber(), FlAT_DESERIALIZER.toJsonArray(obj.getListOfFlats()));
    }

    @Override
    public String toJsonArray(List<House> array) {
        StringBuilder jsonArray = new StringBuilder();
        if (array == null || array.isEmpty()) {
            return "[]";
        }
        jsonArray.append("[");
        for (House house : array) {
            jsonArray.append(toJsonObject(house)).append(",");
        }
        jsonArray.delete(jsonArray.length() - 1, jsonArray.length());
        jsonArray.append("]");

        return jsonArray.toString().replaceAll("\\s", "");
    }
}
