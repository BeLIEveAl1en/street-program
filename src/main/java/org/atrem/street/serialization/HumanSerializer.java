package org.atrem.street.serialization;

import org.atrem.street.entities.Human;

import java.util.List;

public class HumanSerializer implements Serializer<Human> {
    @Override
    public String toJsonObject(Human obj) {
        return "{\"name\": " + "\"" + obj.getName() + "\", \"lastName\": " + "\"" + obj.getLastName() + "\", \"money\": " + obj.getMoney() + ", \"listOfPet\": " + new PetSerializer().toJsonArray(obj.getListOfPet()) + "}";
    }

    @Override
    public String toJsonArray(List<Human> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for (Human human : array) {
            jsonArray.append(toJsonObject(human)).append(", ");
        }
        jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        jsonArray.append("]");
        return jsonArray.toString();
    }
}