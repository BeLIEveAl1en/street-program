package org.atrem.street.jsonParser;

import org.junit.jupiter.api.Assumptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class jsonArrayPaserTest {
    ArrayList<String> jsonArray = new ArrayList<>();

    private void shouldParsJsonArray(String str) {
        JsonParser jsonParser = new JsonParser();
        ArrayList<String> splitJsonArray = JsonParser.splitJsonArray(str);

        Assertions.assertEquals(splitJsonArray, jsonArray);
    }

    private void shouldFailParsJsonArray(String str) {
        JsonParser jsonParser = new JsonParser();
        ArrayList<String> splitJsonArray = JsonParser.splitJsonArray(str);

        Assertions.assertNotEquals(splitJsonArray, jsonArray);
    }

    @Test
    public void shouldParsJsonArray() {
        jsonArray.add("{\"number\": 1, \"listOfHuman\":false}");
        jsonArray.add("{\"number\": 2, \"listOfHuman\":null}");
        shouldParsJsonArray("[{\"number\": 1, \"listOfHuman\":false}, {\"number\": 2, \"listOfHuman\":null}]");
    }

    @Test
    public void shouldFailParsJsonArray() {
        jsonArray.add("27");
        shouldFailParsJsonArray("\"23\"\"");
    }
}
