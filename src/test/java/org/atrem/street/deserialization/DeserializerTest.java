package org.atrem.street.deserialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeserializerTest {

    private final String HUMAN_LIST = "[{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]},{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}]";
    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}, {\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";
    private final String HOUSE_LIST = "[{" + "\"number\": 1, \"listOfFlat\": " + FLAT_LIST + "}, {\"number\": 2, \"listOfFlat\": " + FLAT_LIST + "}]";

    @Test
    public void shouldDeserializePetObj() {
        String serializedPet = "{\"name\": \"толя\", \"type\": \"CAT\"}";
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = new PetDeserializer().fromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

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
    public void shouldDeserializeHumanObj() {
        String serializedHuman = "{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\": 100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]}";
        Human expectedHuman = new Human("вася", "пупкин", 100);
        expectedHuman.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        expectedHuman.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        Human actualHuman = new HumanDeserializer().fromJsonObject(serializedHuman);
        Assertions.assertEquals(expectedHuman, actualHuman);
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
    public void shouldDeserializeFlatObj() {
        String serializedFlat = "{" + "\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}";
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        Flat expectedFlat = new Flat(1, humansList);
        Flat actualFlat = new FlatDeserializer().fromJsonObject(serializedFlat);
        Assertions.assertEquals(expectedFlat, actualFlat);
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
    public void shouldDeserializeHouseObj() {
        String serializedFlat = "{" + "\"number\": 1, \"listOfFlat\": " + FLAT_LIST + "}";
        List<Flat> flats = new ArrayList<>();
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        flats.add(new Flat(1, humansList));
        flats.add(new Flat(2, humansList));
        House expectedHouse = new House(1, flats);
        House actualHouse = new HouseDeserializer().fromJsonObject(serializedFlat);
        Assertions.assertEquals(expectedHouse, actualHouse);
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
