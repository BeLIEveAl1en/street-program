package org.atrem.street.deserialization;

import org.atrem.street.entities.Human;
import org.atrem.street.jsonParser.HumanParser;
import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.List;

public class HumanDeserializer implements Deserializer<Human> {
    @Override
    public Human fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }

        HumanParser humanJsonParser = new HumanParser();
        return humanJsonParser.getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Human> fromJsonArray(String jsonArray) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(jsonArray);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }

        HumanParser humanJsonParser = new HumanParser();
        return humanJsonParser.getArrayFromJsonArray(jsonArray);
    }
}
