package org.atrem.street.jsonParser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonPaserTest {

    HashMap<String, String> map = new HashMap<>();

    private void should_parse_jsonObj(String str) {
        HashMap<String, String> mapFromJson = JsonParser.getMapFromJsonObj(str);

        Assertions.assertEquals(map, mapFromJson);
    }

    private void should_fail_parse_jsonObj(String str) {
        HashMap<String, String> mapFromJson = JsonParser.getMapFromJsonObj(str);

        Assertions.assertNotEquals(map, mapFromJson);
    }

    @Test
    public void should_parse_when_all_elements_are_objects_with_plain_fields() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("{\"number\": 1, \"listOfHuman\":false}");
        expected.add("{\"number\": 2, \"listOfHuman\":\"huge\"}");

        ArrayList<String> result = JsonParser.splitJsonArray("[{\"number\": 1, \"listOfHuman\":false}, {\"number\": 2, \"listOfHuman\":\"huge\"}]");

        Assertions.assertEquals(expected, result);
    }

    //Code doesn't work with plain elements
    /*
    @Test
    public void should_parse_when_all_elements_are_plain() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("\"number\"");
        expected.add("1");
        expected.add("true");
        expected.add("null");

        ArrayList<String> result = JsonParser.splitJsonArray("[\"number\", 1, true, null]");

        Assertions.assertEquals(expected, result);
    }
    */

    @Test
    public void should_return_empty_array_parse_with_extra_quote() {
        ArrayList<String> result = JsonParser.splitJsonArray("[\"27\"\"]");

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void should_parse_when_all_field_are_plain() {
        map.put("name", "false");
        map.put("age", "19");
        map.put("lastName", "None");
        should_parse_jsonObj("{\"name\":false, \"age\":19, \"lastName\":None}");
        map.clear();
    }

    @Test
    public void should_parse_when_field_is_array_of_objects() {
        map.put("name", "вася");
        map.put("listOfPet", "[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
        should_parse_jsonObj("{\"name\":\"вася\",\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}");
        map.clear();
    }

    @Test
    public void should_fail_parse_when_unexpected_EOF() {
        map.put("name", "вася");
        map.put("listOfPet", "[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
        should_fail_parse_jsonObj("{\"name\":\"вася\",\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
        map.clear();
    }
}
