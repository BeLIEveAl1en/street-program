package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeserializerTest {

    @Test
    public void shouldDeserializePetObj(){
        String serializedPet = "{\"name\": \"толя\", \"type\": \"CAT\"}";
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = new PetDeserializer().fromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldDeserializePetArray(){
        String serializedPetArray = "[{\"name\":\"толя\",\"type\":\"CAT\"},{\"name\":\"шарик\",\"type\":\"DOG\"},{\"name\":\"куку\",\"type\":\"BIRD\"}]";
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("толя", AnimalType.CAT));
        petList.add(new Pet("шарик", AnimalType.DOG));
        petList.add(new Pet("куку", AnimalType.BIRD));
        List<Pet> petDeserializer = new PetDeserializer().fromJsonArray(serializedPetArray);
        System.out.println(petDeserializer);
        Assertions.assertEquals(petList, petDeserializer);
    }

    @Test
    public void shouldDeserializeHumanObj(){
        String serializedHuman = "{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\": 100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}";
        Human expectedHuman = new Human("вася", "пупкин", 100);
        expectedHuman.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        expectedHuman.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        Human actualHuman = new HumanDeserializer().fromJsonObject(serializedHuman);
        Assertions.assertEquals(expectedHuman, actualHuman);
    }

    @Test
    public void shouldDeserializeHumanArray(){
        String serializedHumanArray = "[{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\": \"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]},{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}]";
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        List<Human> humanDeserializer = new HumanDeserializer().fromJsonArray(serializedHumanArray);
        Assertions.assertEquals(humansList, humanDeserializer);
    }
}
