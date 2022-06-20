package org.atrem.street.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {

    public void shouldValidateJsonObj(String str){
        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), String.valueOf(true));
    }

    public void shouldFailValidationOfJsonArrayValidateJsonObj(String str){
        JsonObjectValidator validator = new JsonObjectValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid(), String.valueOf(true));
    }

    @Test
    public void shouldValidateJsonObjWithWhitespace(){
        shouldValidateJsonObj("{\" name \" : \" Вася \"}");
    }

    @Test
    public void shouldValidateJsonObjWithInteger(){
        shouldValidateJsonObj("{\" name \" : 55.5, \"old\" : 34}");
    }

    @Test
    public void shouldValidateJsonObjWithStringTrue(){
        shouldValidateJsonObj("{\"name\":true}");
    }

    @Test
    public void shouldValidateJsonObjWithStringNull(){
        shouldValidateJsonObj("{\"name\":null}");
    }

    @Test
    public void shouldValidateJsonObjWithStringFalse(){
        shouldValidateJsonObj("{\"name\":false}");
    }

    @Test
    public void shouldValidateJsonObjWithStringBrackets(){
        shouldValidateJsonObj("{\"name\":[thfdth],\"name\":{drgdrg}}");
    }

    @Test
    public void shouldValidateJsonObjWithTwoObj(){
        shouldValidateJsonObj("{\"name\":true," + "\"lastName\":\"fff\"" + "}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraLetter(){
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\"f:\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithTwoColon(){
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\"::\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithExtraQuotes(){
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"\"name\"\":\"Вася\"}");
    }

    @Test
    public void shouldFailValidationJsonObjWithUnexpectedEOF(){
        shouldFailValidationOfJsonArrayValidateJsonObj("{\"name\":\"Вася\"");
    }

    public void shouldValidateJsonArray(String str){
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertTrue(result.isValid(), String.valueOf(true));
    }

    public void shouldFailValidationOfJsonArray(String str){
        JsonArrayValidator validator = new JsonArrayValidator();

        ValidationResult result = validator.validate(str);

        Assertions.assertFalse(result.isValid(), String.valueOf(true));
    }

    @Test
    public void shouldValidateJsonArrayWithAllElements(){
        shouldValidateJsonArray("[[],{},\"abc\",44.7]");
    }

    @Test
    public void shouldValidateJsonArrayWithJSONObj(){
        shouldValidateJsonArray("[{fewfhwofeh}]");
    }

    @Test
    public void shouldValidateJsonArrayWithJSONArray(){
        shouldValidateJsonArray("[[sefpsef]]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithExtraDot(){
        shouldFailValidationOfJsonArray("[4..96]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithExtraQuote(){
        shouldFailValidationOfJsonArray("[\"tot\"\"]");
    }

    @Test
    public void shouldFailValidationOfJsonArrayWithEMissingBracket(){
        shouldFailValidationOfJsonArray("[{}[]");
    }
}
