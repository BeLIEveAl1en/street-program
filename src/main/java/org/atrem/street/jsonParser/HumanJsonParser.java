package org.atrem.street.jsonParser;

import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HumanJsonParser implements Parser<Human>{
    @Override
    public Human getObjFromJsonObj(String str) {
        HashMap<String, String> humanMap = getMapFromJsonObj(str);

        String name = humanMap.get("name");
        String lastName = humanMap.get("lastName");
        Integer money = Integer.valueOf(humanMap.get("money"));
        List<Pet> listOfPet = new PetJsonParser().getArrayFromJsonArray(humanMap.get("listOfPet"));

        Human ryanGosling = new Human(name, lastName, money);
        ryanGosling.setListOfPet(listOfPet);

        return ryanGosling;
    }

    @Override
    public List<Human> getArrayFromJsonArray(String jsonArr) {
        ArrayList<HashMap<String, String>> humanMapArray = getMapArrayFromJsonArr(jsonArr);
        ArrayList<Human> humanList = new ArrayList<>();

        for (int i = 0; i < humanMapArray.size(); i++){
            String name = humanMapArray.get(i).get("name");
            String lastName = humanMapArray.get(i).get("lastName");
            int money = 0;
            if(humanMapArray.get(i).get("money") != null)
                money = Integer.parseInt(humanMapArray.get(i).get("money"));

            Human ryanGosling = new Human(name, lastName, money);

            if(humanMapArray.get(i).get("listOfPet") != null){
                List<Pet> listOfPet = new PetJsonParser().getArrayFromJsonArray(humanMapArray.get(i).get("listOfPet"));
                ryanGosling.setListOfPet(listOfPet);
            }
            humanList.add(ryanGosling);
        }
        return humanList;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMapArrayFromJsonArr(String arg){
        ArrayList<HashMap<String, String>> hashMapsArray = new ArrayList<>();
        StringBuilder map = new StringBuilder();

        int state = 0;
        boolean isSquareBrackets = false;

        for(int i = 0; i < arg.length(); i++){
            char element = arg.charAt(i);

            if(element == ']')
                isSquareBrackets = false;
            else if(element == '[')
                isSquareBrackets = true;


            switch (state){
                case 0:
                    if(element == '{') {
                        map.append(element);
                        state = 1;
                    }
                    break;

                case 1:
                    if (element == '}' && !isSquareBrackets){
                        map.append(element);
                        state = 2;
                        break;
                    }
                    map.append(element);
                    break;

                case 2:
                    hashMapsArray.add(getMapFromJsonObj(map.toString()));
                    map.setLength(0);
                    state = 0;
                    break;
            }
        }
        return hashMapsArray;
    }

}
