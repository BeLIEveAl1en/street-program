package org.atrem.street.serialization;

import org.atrem.street.entities.Human;

import java.util.List;

public class HumanSerializer implements Serializer<Human> {

    private static final PetSerializer PET_DESERIALIZER = new PetSerializer();
    private static final String HUMAN_TEMPLATE = "{\"name\":" + "\"%s\",\"lastName\":" + "\"%s\",\"money\":%s,\"listOfPet\":%s}";


    @Override
    public String toJsonObject(Human obj) {
        return String.format(HUMAN_TEMPLATE, obj.getName(), obj.getLastName(), obj.getMoney(), PET_DESERIALIZER.toJsonArray(obj.getListOfPet()));
    }

    @Override
    public String toJsonArray(List<Human> array) {
        StringBuilder jsonArray = new StringBuilder();
        if (array == null || array.isEmpty()) {
            return "[]";
        }
        jsonArray.append("[");
        for (Human human : array) {
            jsonArray.append(toJsonObject(human)).append(",");
        }
        jsonArray.delete(jsonArray.length() - 1, jsonArray.length());
        jsonArray.append("]");

        return jsonArray.toString().replaceAll("\\s", "");
    }
}