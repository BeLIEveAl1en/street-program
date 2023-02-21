package org.atrem.street.serialization;

import org.atrem.street.entities.Human;

import java.util.List;

public class HumanSerializer implements Serializer<Human> {

    private final PetSerializer PET_SERIALIZER = new PetSerializer();

    @Override
    public String toJsonObject(Human obj) {
        return "{\"name\":" + "\"" + obj.getName() + "\",\"lastName\":" + "\"" + obj.getLastName() + "\",\"money\":" + obj.getMoney() + ",\"listOfPet\":" + PET_SERIALIZER.toJsonArray(obj.getListOfPet()) + "}";
    }

    @Override
    public String toJsonArray(List<Human> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        if (array != null) {
            for (Human human : array) {
                jsonArray.append(toJsonObject(human)).append(", ");
            }
            jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        }
        jsonArray.append("]");
        return jsonArray.toString().replaceAll("\\s", "");
    }
}