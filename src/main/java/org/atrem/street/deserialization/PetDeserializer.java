package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.atrem.street.jsonParser.JsonParser;
//import org.atrem.street.jsonParser.PetParser;
import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetDeserializer implements Deserializer<Pet>, JsonParser<Pet> {

    @Override
    public Pet fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Pet> fromJsonArray(String jsonArray) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(jsonArray);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getArrayFromJsonArray(jsonArray);
    }

    @Override
    public Pet getObjFromJsonObj(String arg) {
        HashMap<String, String> petMap = getMapFromJsonObj(arg);

        String name = petMap.get("name");
        AnimalType type = AnimalType.valueOf(petMap.get("type"));

        return new Pet(name, type);
    }

    @Override
    public List<Pet> getArrayFromJsonArray(String arg) {
        ArrayList<String> jsonPets = splitJsonArray(arg);
        ArrayList<Pet> petList = new ArrayList<>();

        for (String pet : jsonPets) {
            petList.add(getObjFromJsonObj(pet));
        }
        return petList;
    }
}
