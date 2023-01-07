package org.atrem.street.serialization;

import org.atrem.street.entities.Pet;

import java.util.List;

public class PetSerializer implements Serializer<Pet> {

    @Override
    public String toJsonObject(Pet pet) {
        return "{\"name\": " + "\"" + pet.getName() + "\", \"type\": " + "\"" + pet.getType().name() + "\"}";
    }

    @Override
    public String toJsonArray(List<Pet> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for (Pet pet : array) {
            jsonArray.append(toJsonObject(pet)).append(", ");
        }
        jsonArray.delete(jsonArray.length() - 2, jsonArray.length());
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
