package org.atrem.street.serialization;

import org.atrem.street.entities.House;
<<<<<<< HEAD
=======
import org.atrem.street.entities.Human;
>>>>>>> 64c1928ca8b69c5033cbd52d7ef9c209d9dcea6a

import java.util.List;

public class HouseSerializer implements Serializer<House> {

    private static final FlatSerializer FlAT_DESERIALIZER = new FlatSerializer();
    private static final String HOUSE_TEMPLATE = "{\"number\":%s,\"listOfFlat\":%s}";

    @Override
    public String toJsonObject(House obj) {
        return String.format(HOUSE_TEMPLATE, obj.getNumber(), FlAT_DESERIALIZER.toJsonArray(obj.getListOfFlats()));
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
