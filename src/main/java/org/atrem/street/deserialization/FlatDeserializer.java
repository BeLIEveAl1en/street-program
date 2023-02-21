package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.*;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class FlatDeserializer implements Deserializer<Flat> {

    private final HumanDeserializer humanDeserializer = new HumanDeserializer();

    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<>() {{
        add("number");
        add("listOfHuman");
    }});

    @Override
    public Flat convertFromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        Map<String, String> flatMap = getMapFromJsonObj(jsonObj);
        validateFields(flatMap);
        int number = Integer.parseInt(flatMap.get("number"));
        List<Human> humans = humanDeserializer.convertFromJsonArray(flatMap.get("listOfHuman"));
        return new Flat(number, humans);
    }

    @Override
    public List<Flat> convertFromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
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

