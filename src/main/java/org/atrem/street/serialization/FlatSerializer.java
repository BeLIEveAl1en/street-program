package org.atrem.street.serialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.List;

public class FlatSerializer implements Serializer<Flat> {

    private final HumanSerializer HUMAN_DESERIALIZER = new HumanSerializer();
    private final String asd = "{\"number\":%s," + "\"listOfHuman\":%s}";

    @Override
    public String toJsonObject(Flat flat) {
        return String.format(asd, flat.getNumber(), HUMAN_DESERIALIZER.toJsonArray(flat.getHumans()));
    }

    @Override
    public String toJsonArray(List<Flat> array) {
        StringBuilder jsonArray = new StringBuilder();
        if (array == null || array.isEmpty()) {
            return "[]";
        }
        jsonArray.append("[");
        for (Flat flat : array) {
            jsonArray.append(toJsonObject(flat)).append(",");
        }
        jsonArray.delete(jsonArray.length() - 1, jsonArray.length());
        jsonArray.append("]");

        return jsonArray.toString().replaceAll("\\s", "");
    }
}
