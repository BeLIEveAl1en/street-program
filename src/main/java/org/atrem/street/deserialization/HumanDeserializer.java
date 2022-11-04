package org.atrem.street.deserialization;

import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.atrem.street.jsonParser.JsonParser;
import org.atrem.street.validation.JsonArrayValidator;
import org.atrem.street.validation.JsonObjectValidator;
import org.atrem.street.validation.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HumanDeserializer implements Deserializer<Human>, JsonParser<Human> {
    @Override
    public Human fromJsonObject(String jsonObj) {
        JsonObjectValidator jsonObjectValidator = new JsonObjectValidator();

        ValidationResult result = jsonObjectValidator.validate(jsonObj);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getObjFromJsonObj(jsonObj);
    }

    @Override
    public List<Human> fromJsonArray(String jsonArray) {
        JsonArrayValidator jsonArrayValidator = new JsonArrayValidator();

        ValidationResult result = jsonArrayValidator.validate(jsonArray);
        if (!result.isValid()) {
            throw new IllegalStateException(result.getComment());
        }
        return getArrayFromJsonArray(jsonArray);
    }

    @Override
    public Human getObjFromJsonObj(String str) {
        HashMap<String, String> humanMap = getMapFromJsonObj(str);

        String name = humanMap.get("name");
        String lastName = humanMap.get("lastName");
        int money = Integer.parseInt(humanMap.get("money"));
        List<Pet> listOfPet = new PetDeserializer().getArrayFromJsonArray(humanMap.get("listOfPet"));

        Human ryanGosling = new Human(name, lastName, money);
        ryanGosling.setListOfPet(listOfPet);

        return ryanGosling;
    }

    @Override
    public List<Human> getArrayFromJsonArray(String jsonArr) {
        ArrayList<String> jsonHumans = splitJsonArray(jsonArr);
        ArrayList<Human> humanList = new ArrayList<>();

        for (String jsonHuman : jsonHumans) {
            humanList.add(getObjFromJsonObj(jsonHuman));
        }
        return humanList;
    }
}
