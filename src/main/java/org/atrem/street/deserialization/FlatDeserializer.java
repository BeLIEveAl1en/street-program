package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class FlatDeserializer implements Deserializer<Flat> {
    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<>() {{
        add("number");
        add("listOfHuman");
    }});

    @Override
    public Flat fromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        HashMap<String, String> flatMap = getMapFromJsonObj(jsonObj);
        validateFields(flatMap);
        int number = Integer.parseInt(flatMap.get("number"));
        List<Human> humans = new HumanDeserializer().fromJsonArray(flatMap.get("listOfHuman"));
        return new Flat(number, humans);
    }

    @Override
    public List<Flat> fromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
        ArrayList<String> jsonFlats = splitJsonArray(jsonArray);
        ArrayList<Flat> flatList = new ArrayList<>();

        for (String jsonFlat : jsonFlats) {
            flatList.add(fromJsonObject(jsonFlat));
        }
        return flatList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}

