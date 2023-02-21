package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.House;

import java.util.*;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HouseDeserializer implements Deserializer {

    private final FlatDeserializer flatDeserializer = new FlatDeserializer();

    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<>() {{
        add("number");
        add("listOfFlat");
    }});

    @Override
    public House convertFromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        Map<String, String> houseMap = getMapFromJsonObj(jsonObj);
        validateFields(houseMap);
        int number = Integer.parseInt(houseMap.get("number"));
        List<Flat> flats = flatDeserializer.convertFromJsonArray(houseMap.get("listOfFlat"));
        return new House(number, flats);
    }

    @Override
    public List<House> convertFromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
        ArrayList<String> jsonHouses = splitJsonArray(jsonArray);
        ArrayList<House> houseList = new ArrayList<>();
        for (String jsonHuman : jsonHouses) {
            houseList.add(convertFromJsonObject(jsonHuman));
        }
        return houseList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}
