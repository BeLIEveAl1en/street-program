package org.atrem.street.serialization;

<<<<<<< HEAD
=======
import org.atrem.street.entities.Human;
>>>>>>> 64c1928ca8b69c5033cbd52d7ef9c209d9dcea6a
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
