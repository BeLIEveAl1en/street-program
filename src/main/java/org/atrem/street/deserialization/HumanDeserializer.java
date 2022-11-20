package org.atrem.street.deserialization;

import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HumanDeserializer implements Deserializer<Human> {
    final static List<String> requiredFields = Collections.unmodifiableList(new ArrayList<String>() {{
        add("name");
        add("lastName");
        add("money");
        add("listOfPet");
    }});

    @Override
    public Human fromJsonObject(String jsonObj) {
        objValidator(jsonObj);
        HashMap<String, String> humanMap = getMapFromJsonObj(jsonObj);
        validateFields(humanMap);
        String name = humanMap.get("name");
        String lastName = humanMap.get("lastName");
        int money = Integer.parseInt(humanMap.get("money"));
        List<Pet> listOfPet = new PetDeserializer().fromJsonArray(humanMap.get("listOfPet"));
        Human human = new Human(name, lastName, money);
        human.setListOfPet(listOfPet);

        return human;
    }

    @Override
    public List<Human> fromJsonArray(String jsonArray) {
        arrayValidator(jsonArray);
        ArrayList<String> jsonHumans = splitJsonArray(jsonArray);
        ArrayList<Human> humanList = new ArrayList<>();

        for (String jsonHuman : jsonHumans) {
            humanList.add(fromJsonObject(jsonHuman));
        }
        return humanList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}
