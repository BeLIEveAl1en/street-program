package org.atrem.street.serialization;

import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;

import java.util.List;

public class PetSerializer implements Serializer<Pet> {

    private static final String PET_TEMPLATE = "{\"name\":" + "\"%s\",\"type\":" + "\"%s\"}";

    @Override
    public String toJsonObject(Pet pet) {
        return String.format(PET_TEMPLATE, pet.getName(), pet.getType().name());
    }

    @Override
    public String toJsonArray(List<Pet> array) {
        StringBuilder jsonArray = new StringBuilder();
        if (array == null || array.isEmpty()) {
            return "[]";
        }
        jsonArray.append("[");
        for (Pet pet : array) {
            jsonArray.append(toJsonObject(pet)).append(",");
        }
        jsonArray.delete(jsonArray.length() - 1, jsonArray.length());
        jsonArray.append("]");

        return jsonArray.toString().replaceAll("\\s", "");
    }
}
