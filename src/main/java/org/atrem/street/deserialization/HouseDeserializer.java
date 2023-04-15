package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.House;

import java.util.*;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HouseDeserializer implements Deserializer {

    private final FlatDeserializer flat_deserializer = new FlatDeserializer();

    final static List<String> requiredFields = List.of("number", "listOfFlat");

    @Override
    public House convertFromJsonObject(String jsonObj) {
        validate_obj(jsonObj);
        Map<String, String> houseMap = getMapFromJsonObj(jsonObj);
        validateFields(houseMap);
        int number = Integer.parseInt(houseMap.get("number"));
        List<Flat> flats = flat_deserializer.convertFromJsonArray(houseMap.get("listOfFlat"));
        return new House(number, flats);
    }

    @Override
    public List<House> convertFromJsonArray(String jsonArray) {
        validate_array(jsonArray);
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
