package org.atrem.street.deserialization;

import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public interface Deserializer<T> {

    T fromJsonObject(String jsonObj);

    List<T> fromJsonArray(String jsonArray);

    default void objValidator(String obj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(obj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
    }

    default void arrayValidator(String array) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(array);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
    }

    List<String> getRequiredFields();

    default void validateFields(HashMap<String, String> map) {
        boolean flag = true;
        for (int i = 0; i < getRequiredFields().size(); i++) {
            for (int j = 0; j < getRequiredFields().size(); j++) {
                if (!Objects.equals(map.keySet().toArray()[i], getRequiredFields().get(j)))
                    flag = false;
                else {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                throw new IllegalStateException();
        }
    }
}
