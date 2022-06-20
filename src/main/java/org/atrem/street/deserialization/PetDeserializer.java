package org.atrem.street.deserialization;
import org.atrem.street.entities.Pet;
import org.atrem.street.jsonParser.PetJsonParser;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.List;

public class PetDeserializer implements Deserializer<Pet> {

    @Override
    public Pet fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if(!result.isValid()){
            throw new IllegalStateException(result.getComment());
        }

        PetJsonParser petJsonParser = new PetJsonParser();
        return petJsonParser.getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Pet> fromJsonArray(String jsonArray) {

        return null;
    }
}
