package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.*;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class FlatDeserializer implements Deserializer<Flat> {

    private final HumanDeserializer HUMAN_DESERIALIZER = new HumanDeserializer();
    final private static List<String> requiredFields = List.of("number", "listOfHuman");

    @Override
    public Flat convertFromJsonObject(String jsonObj) {
        validateObj(jsonObj);
        Map<String, String> flatMap = getMapFromJsonObj(jsonObj);
        validateFields(flatMap);
        int number = Integer.parseInt(flatMap.get("number"));
        List<Human> humans = HUMAN_DESERIALIZER.convertFromJsonArray(flatMap.get("listOfHuman"));
        return new Flat(number, humans);
    }

    @Override
    public List<Flat> convertFromJsonArray(String jsonArray) {
        validateArray(jsonArray);
        ArrayList<String> jsonFlats = splitJsonArray(jsonArray);
        ArrayList<Flat> flatList = new ArrayList<>();

        for (String jsonFlat : jsonFlats) {
            flatList.add(convertFromJsonObject(jsonFlat));
        }
        return flatList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}

