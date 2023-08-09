package org.atrem.street.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonArrayValidatorTest {
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

    public void shouldValidateJsonArray(String str) {
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), "true");
    }

    public void shouldFailValidationOfJsonArray(String str) {
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid());
    }

    @Test
    public void shouldValidateJsonArrayWithAllTypesElements() {
        shouldValidateJsonArray("[[],{},\"abc\",44.7,null,true,false]");
    }

    @Test
    public void shouldValidateHumanList() {
        shouldValidateJsonArray(getJSON("src\\test\\resources\\humanList.json"));
    }

    @Test
    public void should_validate_flat_list() {
        shouldValidateJsonArray(getJSON("src\\test\\resources\\flatList.json"));
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
    public void shouldValidateJsonArrayWithSingleDigit() {
        shouldValidateJsonArray("[3]");
    }

    @Test
    public void shouldValidate() {
        shouldValidateJsonArray("[[[]]]");
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

    @Test
    public void shouldFailValidationWithoutBracket() {
        shouldFailValidationOfJsonArray("psdf]");
    }
}
