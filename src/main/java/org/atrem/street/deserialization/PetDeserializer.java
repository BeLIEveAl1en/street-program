package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class PetDeserializer implements Deserializer<Pet> {
    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<>() {{
        add("name");
        add("type");
    }});

    @Override
    public Pet fromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        HashMap<String, String> petMap = getMapFromJsonObj(jsonObj);
        validateFields(petMap);
        String name = petMap.get("name");
        AnimalType type = AnimalType.valueOf(petMap.get("type"));

        return new Pet(name, type);
    }

    @Override
    public List<Pet> fromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
        ArrayList<String> jsonPets = splitJsonArray(jsonArray);
        ArrayList<Pet> petList = new ArrayList<>();

        for (String pet : jsonPets) {
            petList.add(fromJsonObject(pet));
        }
        return petList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}
