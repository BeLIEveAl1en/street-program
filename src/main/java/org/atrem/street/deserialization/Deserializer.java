package org.atrem.street.deserialization;

import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.List;
import java.util.Map;

public interface Deserializer<T> {

    T convertFromJsonObject(String jsonObj);

    List<T> convertFromJsonArray(String jsonArray);

    default void validateObj(String obj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(obj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
    }

    default void validateArray(String array) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(array);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
    }

    List<String> getRequiredFields();

    default void validateFields(Map<String, String> map) {
        if (!map.keySet().containsAll(getRequiredFields())) {
            throw new IllegalStateException();
        }
    }
}
