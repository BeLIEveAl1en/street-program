package org.atrem.street.jsonParser;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetJsonParser implements Parser<Pet>{
    @Override
    public Pet getObjFromJsonObj(String arg) {
        HashMap<String, String> petMap = getMapFromJsonObj(arg);

        String name = petMap.get("name");
        AnimalType type = AnimalType.valueOf(petMap.get("type"));

        return new Pet(name, type);
    }

    @Override
    public List<Pet> getArrayFromJsonArray(String arg) {
        ArrayList<HashMap<String, String>> hashMapArray = getMapArrayFromJsonArr(arg);
        ArrayList<Pet> petList = new ArrayList<>();

        for (int i = 0; i < hashMapArray.size(); i++){
            String name = hashMapArray.get(i).get("name");
            AnimalType type = AnimalType.valueOf(hashMapArray.get(i).get("type"));
            petList.add(new Pet(name, type));
        }
        return petList;
    }
}
