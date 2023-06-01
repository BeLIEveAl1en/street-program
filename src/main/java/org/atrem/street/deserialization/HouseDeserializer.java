package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.House;

import java.util.*;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HouseDeserializer implements Deserializer {

    private static final FlatDeserializer FLAT_DESERIALIZER = new FlatDeserializer();
    private final static List<String> requiredFields = List.of("number", "listOfFlat");

    @Override
    public House convertFromJsonObject(String jsonObj) {
        validateObj(jsonObj);
        Map<String, String> houseMap = getMapFromJsonObj(jsonObj);
        validateFields(houseMap);
        int number = Integer.parseInt(houseMap.get("number"));
        List<Flat> flats = FLAT_DESERIALIZER.convertFromJsonArray(houseMap.get("listOfFlat"));
        return new House(number, flats);
    }

    @Override
    public List<House> convertFromJsonArray(String jsonArray) {
        validateArray(jsonArray);
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
