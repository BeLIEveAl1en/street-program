package org.atrem.street.serialization;

import org.atrem.street.entities.Human;

import java.util.List;

public class HumanSerializer implements Serializer<Human>{
    @Override
    public String toJsonObject(Human obj) {
        String jsonObj = "{" +
                "\"lastName\":" + "\"" + obj.getLastName() + "\"" +
                "\"name\":" + "\"" + obj.getName() + "\"" +
                "\"money\":" + "\"" + obj.getMoney() + "\"" +
                "}";
        return jsonObj;
    }

    @Override
    public String toJsonArray(List<Human> array) {
        StringBuilder jsonArray = new StringBuilder();
        jsonArray.append("[");
        for(Human human: array){
            if (!human.equals(array.get(array.size() - 1)))
                jsonArray.append("{" + "\"name\": " + "\"").append(human.getName()).append("\"" + ", ").append("\"lastName\": " + "\"").append(human.getLastName()).append("\"" + ", ").append("\"money\": " + "\"").append(human.getMoney()).append("\"" + ", ").append("\"listOfPet\": ").append(new PetSerializer().toJsonArray(human.listOfPet)).append("}" + ", ");//new PetSerializer().toJsonArray()
            else
                jsonArray.append("{" + "\"name\": " + "\"").append(human.getName()).append("\"" + ", ").append("\"lastName\": " + "\"").append(human.getLastName()).append("\"" + ", ").append("\"money\": " + "\"").append(human.getMoney()).append("\"" + ", ").append("\"listOfPet\": ").append(new PetSerializer().toJsonArray(human.listOfPet)).append("}");
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }
}