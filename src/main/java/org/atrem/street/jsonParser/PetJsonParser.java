package org.atrem.street.jsonParser;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;

import java.util.HashMap;

public class PetJsonParser implements Parser<Pet>{
    @Override
    public Pet getObjFromJsonObj(String str) {
        HashMap<String, String> petMap = getMapFromJsonObj(str);

        String name = petMap.get("name");
        AnimalType type = AnimalType.valueOf(petMap.get("type"));

        return new Pet(name, type);
    }

    @Override
    public Pet getArrayFromJsonArray(String arg) {
        return null;
    }

    private HashMap<String, String> getMapFromJsonObj(String arg){
        arg = getClearJsonObj(arg);
        HashMap<String, String> petMap = new HashMap<>();

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        int state = 0;

        for (int i =  0; i < arg.length(); i++){
            char element =  arg.charAt(i);

            if(element == ':'){
                state = 1;
            }
            else if(element == ',' || i == arg.length() - 1){
                state = 2;
            }

            switch (state){
                case 0:
                    key.append(element);
                    break;

                case 1:
                    if (!(element == ':'))
                        value.append(element);
                    break;

                case 2:
                    if(i == arg.length() - 1)
                        value.append(element);
                    petMap.put(key.toString().strip(), value.toString().strip());
                    key.delete(0, key.length());
                    value.delete(0, value.length());
                    state = 0;
                    break;
            }
        }
        return petMap;
    }
}
