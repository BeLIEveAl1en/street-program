package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.House;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.atrem.street.jsonParser.JsonParser;
import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HouseDeserializer implements Deserializer, JsonParser<House> {
    @Override
    public House fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<House> fromJsonArray(String jsonArray) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(jsonArray);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getArrayFromJsonArray(jsonArray);
    }

    @Override
    public House getObjFromJsonObj(String str) {
        HashMap<String, String> houseMap = getMapFromJsonObj(str);

        int number = Integer.parseInt(houseMap.get("number"));
        List<Flat> flats = new FlatDeserializer().getArrayFromJsonArray(houseMap.get("listOfFlat"));

        return new House(number, flats);
    }

    @Override
    public List<House> getArrayFromJsonArray(String jsonArr) {
        ArrayList<String> jsonHouses = splitJsonArray(jsonArr);
        ArrayList<House> houseList = new ArrayList<>();

        for (String jsonHuman : jsonHouses) {
            houseList.add(getObjFromJsonObj(jsonHuman));
        }
        return houseList;
    }
}
