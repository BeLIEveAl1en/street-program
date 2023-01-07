package org.atrem.street.deserialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class jsonArrayDeserializerTest {

    private final String HUMAN_LIST = "[{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]},{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}]";
    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}, {\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";
    private final String HOUSE_LIST = "[{" + "\"number\": 1, \"listOfFlat\": " + FLAT_LIST + "}, {\"number\": 2, \"listOfFlat\": " + FLAT_LIST + "}]";


    @Test
    public void shouldDeserializePetArray() {
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
    public void shouldDeserializeHumanArray() {
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        List<Human> humanDeserializer = new HumanDeserializer().fromJsonArray(HUMAN_LIST);
        Assertions.assertEquals(humansList, humanDeserializer);
    }

    @Test
    public void shouldDeserializeFlatArray() {
        ArrayList<Flat> expectedFlats = new ArrayList<>();
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        expectedFlats.add(new Flat(1, humansList));
        expectedFlats.add(new Flat(2, humansList));
        List<Flat> actualFlats = new FlatDeserializer().fromJsonArray(FLAT_LIST);
        Assertions.assertEquals(expectedFlats, actualFlats);
    }

    @Test
    public void shouldDeserializeHouseArray() {
        List<House> expectedHousesList = new ArrayList<>();
        List<Flat> flatList = new ArrayList<>();
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        flatList.add(new Flat(1, humansList));
        flatList.add(new Flat(2, humansList));
        expectedHousesList.add(new House(1, flatList));
        expectedHousesList.add(new House(2, flatList));
        List<House> actualHouses = new HouseDeserializer().fromJsonArray(HOUSE_LIST);
        Assertions.assertEquals(expectedHousesList, actualHouses);
    }
}
