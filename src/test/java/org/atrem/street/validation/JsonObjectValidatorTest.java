package org.atrem.street.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonObjectValidatorTest {

    private final String HUMAN_1 = "{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}";
    private final String HUMAN_2 = "{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}";
    private final String HUMAN_LIST = "[" + HUMAN_1 + "," + HUMAN_2 + "]";

    private String getJSON(String file) {
        StringBuilder expectedJSON = new StringBuilder();
        Path path = Paths.get(file);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String s : lines) {
                expectedJSON.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expectedJSON.toString().replaceAll("\\s", "");
    }

    public void shouldValidateJsonObj(String str) {
        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), "true");
    }

    public void shouldFailValidateJsonObj(String str) {
        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid());
    }

    @Test
    public void shouldValidateWhenNestedArrayContainsTwoObj() {
        shouldValidateJsonObj(HUMAN_1);
    }

    @Test
    public void shouldValidateHumanList() {
        shouldValidateJsonObj("{" + "\"number\": 1,\"listOfHuman\":" + HUMAN_LIST + "}");
    }

    @Test
    public void shouldValidateJsonObjWithWhitespace() {
        shouldValidateJsonObj("{\"name\":\"Вася\",\"old\":34, \"list\":[iriirir]}");
    }

    @Test
    public void shouldValidateJsonObjWithInteger() {
        shouldValidateJsonObj("{\" name \" : 55.5, \"old\" : 34}");
    }

    @Test
    public void shouldValidateJsonObjWithStringNull() {
        shouldValidateJsonObj("{\"name\":null}");
    }

    @Test
    public void shouldValidateJsonObjWithStringFalse() {
        shouldValidateJsonObj("{\"name\":false}");
    }

    @Test
    public void shouldValidateJsonObjWithStringTrue() {
        shouldValidateJsonObj("{\"name\":true}");
    }

    @Test
    public void shouldValidateJsonObjWithStringBrackets() {
        shouldValidateJsonObj("{\"name\":[{thfd.th}, {srg}],\"name\":{drgdrg}}");
    }

    @Test
    public void shouldValidateCurlyBrackets() {
        shouldValidateJsonObj("{}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraLetter() {
        shouldFailValidateJsonObj("{\"name\"f:\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithTwoColon() {
        shouldFailValidateJsonObj("{\"name\"::\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraQuotes() {
        shouldFailValidateJsonObj("{\"\"name\"\":\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithUnexpectedEOF() {
        shouldFailValidateJsonObj("{\"name\":\"Вася\"");
    }

    @Test
    public void should_fail_validation_when_opening_curlyBracket_missing() {
        shouldFailValidateJsonObj("\"name\":\"toshi\"}");
    }

    @Test
    public void should_fail_validation() {
        shouldFailValidateJsonObj("{rorope}");
    }
}
