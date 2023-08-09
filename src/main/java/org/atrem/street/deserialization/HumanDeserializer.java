package org.atrem.street.deserialization;

import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.atrem.street.jsonParser.JsonParser.getMapFromJsonObj;
import static org.atrem.street.jsonParser.JsonParser.splitJsonArray;

public class HumanDeserializer implements Deserializer<Human> {

    private static final PetDeserializer PET_DESERIALIZER = new PetDeserializer();
    private final static List<String> requiredFields = List.of("name", "lastName", "money", "listOfPet");

    @Override
    public Human convertFromJsonObject(String jsonObj) {
        validateObj(jsonObj);
        HashMap<String, String> humanMap = getMapFromJsonObj(jsonObj);
        validateFields(humanMap);
        String name = humanMap.get("name");
        String lastName = humanMap.get("lastName");
        int money = Integer.parseInt(humanMap.get("money"));
        List<Pet> listOfPet = PET_DESERIALIZER.convertFromJsonArray(humanMap.get("listOfPet"));
        Human human = new Human(name, lastName, money);
        human.addPets(listOfPet);
        return human;
    }

    @Override
    public List<Human> convertFromJsonArray(String jsonArray) {
        validateArray(jsonArray);
        ArrayList<String> jsonHumans = splitJsonArray(jsonArray);
        ArrayList<Human> humanList = new ArrayList<>();

        for (String jsonHuman : jsonHumans) {
            humanList.add(convertFromJsonObject(jsonHuman));
        }
        return humanList;
    }

    @Override
    public List<String> getRequiredFields() {
        return requiredFields;
    }
}
