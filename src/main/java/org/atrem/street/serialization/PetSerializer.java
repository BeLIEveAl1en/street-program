package org.atrem.street.serialization;
import org.atrem.street.entities.Pet;

import java.util.List;

public class PetSerializer implements Serializer<Pet>{

    @Override
    public String toJsonObject(Pet pet) {
        String jsonObj = "{" +
                "\"name\":" + "\"" + pet.getName() + "\"" +
                "," +
                "\"type\":" + "\"" + pet.getType().name() + "\"" +
                "}";
        return jsonObj;
    }

    @Override
    public String toJsonArray(List<Pet> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for(Pet pet: array){
            if (!pet.equals(array.get(array.size() - 1)))
                jsonArray.append("{" + "\"name\": " + "\"").append(pet.getName()).append("\"").append(", ").append("\"type\": ").append("\"").append(pet.getType().name()).append("\"").append("}, ");
            else
                jsonArray.append("{" + "\"name\": " + "\"").append(pet.getName()).append("\"").append(", ").append("\"type\": ").append("\"").append(pet.getType().name()).append("\"").append("}");
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
