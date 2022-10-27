package org.atrem.street.deserialization;

import org.atrem.street.entities.Pet;
import org.atrem.street.jsonParser.PetParser;
import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.List;

public class PetDeserializer implements Deserializer<Pet> {

    @Override
    public Pet fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }

        PetParser petJsonParser = new PetParser();
        return petJsonParser.getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Pet> fromJsonArray(String jsonArray) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(jsonArray);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }

        PetParser petJsonParser = new PetParser();
        return petJsonParser.getArrayFromJsonArray(jsonArray);
    }
}
