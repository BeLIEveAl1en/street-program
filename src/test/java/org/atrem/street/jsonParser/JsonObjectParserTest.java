package org.atrem.street.jsonParser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class JsonObjectParserTest {

    HashMap<String, String> map = new HashMap<>();

    private void shouldParsJsonObj(String str) {
        JsonParser jsonParser = new JsonParser();
        HashMap<String, String> mapFromJson = JsonParser.getMapFromJsonObj(str);

        Assertions.assertEquals(mapFromJson, map);
    }

    private void shouldFailParsJsonObj(String str) {
        JsonParser jsonParser = new JsonParser();
        HashMap<String, String> mapFromJson = JsonParser.getMapFromJsonObj(str);

        Assertions.assertNotEquals(mapFromJson, map);
    }

    @Test
    public void shouldParsJsonObjectWith() {
        map.put("name", "false");
        shouldParsJsonObj("{\"name\":false}");
    }

    @Test
    public void shouldParsJsonHuman() {
        map.put("name", "вася");
        map.put("listOfPet", "[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
        shouldParsJsonObj("{\"name\":\"вася\",\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}");
    }

    @Test
    public void shouldFailParsWithUnexpectedEOF() {
        map.put("name", "вася");
        map.put("listOfPet", "[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
        shouldFailParsJsonObj("{\"name\":\"вася\",\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]");
    }
}
