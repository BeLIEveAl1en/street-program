package org.atrem.street.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonObjectValidatorTest {

    private final String HUMAN_1 = "{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}";
    private final String HUMAN_2 = "{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}";
    private final String HUMAN_LIST = "[" + HUMAN_1 + "," + HUMAN_2 + "]";

    public void shouldValidateJsonObj(String str) {
        //String testJsonString = String.join("", Files.readAllLines(Path.of(path)));

        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), String.valueOf(true));
    }

    public void shouldFailValidationOfJsonArrayValidateJsonObj(String str) {
        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid(), String.valueOf(true));
    }

    @Test
    public void should_validate_when_nested_array_contains_two_obj() {
        shouldValidateJsonObj(HUMAN_1);
    }

    @Test
    public void should_validate_human_list() {
        shouldValidateJsonObj("{" + "\"number\": 1,\"listOfHuman\":" + HUMAN_LIST + "}");
    }

    @Test
    public void should_Validate_Json_Obj_With_Whitespace() {
        shouldValidateJsonObj("{\"name\":\"Вася\",\"old\":34, \"list\":[iriirir]}");
    }

    @Test
    public void should_Validate_Json_Obj_With_Integer() {
        shouldValidateJsonObj("{\" name \" : 55.5, \"old\" : 34}");
    }

    @Test
    public void should_Validate_Json_Obj_With_String_Null() {
        shouldValidateJsonObj("{\"name\":null}");
    }

    @Test
    public void shouldValidateJsonObjWithStringFalse() {
        shouldValidateJsonObj("{\"name\":false}");
    }

    @Test
    public void shouldValidateJsonObjWithStringBrackets() {
        shouldValidateJsonObj("{\"name\":[{thfd.th}, {srg}],\"name\":{drgdrg}}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraLetter() {
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\"f:\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithTwoColon() {
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\"::\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraQuotes() {
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"\"name\"\":\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithUnexpectedEOF() {
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\":\"Вася\"");
    }
}
