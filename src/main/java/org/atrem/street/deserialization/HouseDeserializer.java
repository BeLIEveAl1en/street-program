package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.House;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HouseDeserializer implements Deserializer {
    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<>() {{
        add("number");
        add("listOfFlat");
    }});

    @Override
    public House fromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        HashMap<String, String> houseMap = getMapFromJsonObj(jsonObj);
        validateFields(houseMap);
        int number = Integer.parseInt(houseMap.get("number"));
        List<Flat> flats = new FlatDeserializer().fromJsonArray(houseMap.get("listOfFlat"));
        return new House(number, flats);
    }

    @Override
    public List<House> fromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
        ArrayList<String> jsonHouses = splitJsonArray(jsonArray);
        ArrayList<House> houseList = new ArrayList<>();
        for (String jsonHuman : jsonHouses) {
            houseList.add(fromJsonObject(jsonHuman));
        }
        return houseList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}
