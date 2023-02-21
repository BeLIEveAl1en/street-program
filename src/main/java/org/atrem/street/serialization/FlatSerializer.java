package org.atrem.street.serialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.List;

public class FlatSerializer implements Serializer<Flat> {

    private final HumanSerializer HUMAN_SERIALIZER = new HumanSerializer();

    @Override
    public String toJsonObject(Flat flat) {
        return "{\"number\":" + flat.getNumber() + "," + "\"listOfHuman\":" + HUMAN_SERIALIZER.toJsonArray(flat.getHumans()) + "}";
    }

    @Override
    public String toJsonArray(List<Flat> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        if (array != null) {
            for (Flat flat : array) {
                jsonArray.append(toJsonObject(flat)).append(", ");
            }
            jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        }
        jsonArray.append("]");
        return jsonArray.toString().replaceAll("\\s", "");
    }
}
