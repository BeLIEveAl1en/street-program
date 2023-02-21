package org.atrem.street.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonArrayValidatorTest {

    private final String HUMAN_1 = "{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}";
    private final String HUMAN_2 = "{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}";
    private final String HUMAN_LIST = "[" + HUMAN_1 + "," + HUMAN_2 + "]";

    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "},{\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";

    public void shouldValidateJsonArray(String str) {
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), "true");
    }

    public void shouldFailValidationOfJsonArray(String str) {
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid(), "true");
    }

    @Test
    public void shouldValidateJsonArrayWithAllTypesElements() {
        shouldValidateJsonArray("[[],{},\"abc\",44.7,null,true,false]");
    }

    @Test
    public void shouldValidateHumanList() {
        shouldValidateJsonArray(HUMAN_LIST);
    }

    @Test
    public void should_validate_flat_list() {
        shouldValidateJsonArray(FLAT_LIST);
    }

    @Test
    public void shouldValidateJsonArrayWithJSONObj() {
        shouldValidateJsonArray("[{fewfhwofeh}]");
    }

    @Test
    public void shouldValidateJsonArrayWithJSONArray() {
        shouldValidateJsonArray("[[sefpsef]]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithExtraDot() {
        shouldFailValidationOfJsonArray("[4..96]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithExtraQuote() {
        shouldFailValidationOfJsonArray("[\"tot\"\"]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithMissingBracket() {
        shouldFailValidationOfJsonArray("[{}[]");
    }
}
