package org.atrem.street.deserialization;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.jsonParser.JsonParser;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlatDeserializer implements Deserializer<Flat>, JsonParser<Flat> {
    @Override
    public Flat fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Flat> fromJsonArray(String jsonArray) {
        return null;
    }

    @Override
    public Flat getObjFromJsonObj(String str) {
        HashMap<String, String> flat = getMapFromJsonObj(str);

        int number = Integer.parseInt(flat.get("number"));
        List<Human> humans = new HumanDeserializer().getArrayFromJsonArray(flat.get("listOfHuman"));

        return new Flat(number, humans);
    }

    @Override
    public List<Flat> getArrayFromJsonArray(String jsonArr) {
        ArrayList<String> jsonFlats = splitJsonArray(jsonArr);
        ArrayList<Flat> flatList = new ArrayList<>();

        for (String jsonFlat : jsonFlats) {
            flatList.add(getObjFromJsonObj(jsonFlat));
        }
        return flatList;
    }
}

