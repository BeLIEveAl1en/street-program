package org.atrem.street.serialization;

import org.atrem.street.entities.Flat;

import java.util.List;

public class FlatSerializer implements Serializer<Flat> {

    private static final HumanSerializer HUMAN_DESERIALIZER = new HumanSerializer();
    private static final String FLAT_TEMPLATE = "{\"number\":%s," + "\"listOfHuman\":%s}";

    @Override
    public String toJsonObject(Flat flat) {
        return String.format(FLAT_TEMPLATE, flat.getNumber(), HUMAN_DESERIALIZER.toJsonArray(flat.getHumans()));
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
