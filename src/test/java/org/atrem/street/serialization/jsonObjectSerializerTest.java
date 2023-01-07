package org.atrem.street.serialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class jsonObjectSerializerTest {
    private final String HUMAN_LIST = "[{\"name\": \"вася\", \"lastName\": \"пупкин\", \"money\": 100, \"listOfPet\": [{\"name\": \"Шарик\", \"type\": \"CAT\"}, {\"name\": \"Тузик\", \"type\": \"DOG\"}]}]";
    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}, {\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";

    @Test
    public void shouldSerializePetObject() {
        Pet pet = new Pet("толя", AnimalType.CAT);
        Serializer<Pet> petSerializer = new PetSerializer();
        String jsonPet = petSerializer.toJsonObject(pet);
        String expected = "{\"name\": \"толя\", \"type\": \"CAT\"}";
        Assertions.assertEquals(expected, jsonPet);
    }

    @Test
    public void shouldSerializeHuman() {
        Human human1 = new Human("вася", "пупкин", 100);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        Serializer<Human> humanSerializer = new HumanSerializer();
        String jsonHuman = humanSerializer.toJsonObject(human1);
        String expected = "{\"name\": \"вася\", \"lastName\": \"пупкин\", \"money\": 100, \"listOfPet\": [{\"name\": \"Шарик\", \"type\": \"CAT\"}, {\"name\": \"Тузик\", \"type\": \"DOG\"}]}";
        Assertions.assertEquals(expected, jsonHuman);
    }

    @Test
    public void shouldSerializeFlatObject() {
        List<Human> people = mockHumanList();
        Flat flat = new Flat(1, people);
        Serializer<Flat> flatSerializer = new FlatSerializer();
        String jsonFlat = flatSerializer.toJsonObject(flat);
        String expected = "{" + "\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}";
        Assertions.assertEquals(expected, jsonFlat);
    }

    @Test
    public void shouldSerializeHouseObject() {
        List<Flat> flats = mockFlatList();
        House house = new House(1, flats);
        Serializer<House> houseSerializer = new HouseSerializer();
        String jsonHouse = houseSerializer.toJsonObject(house);
        String expected = "{" + "\"number\": 1, \"listOfFlats\": " + FLAT_LIST + "}";
        Assertions.assertEquals(expected, jsonHouse);
    }

    private List<Human> mockHumanList() {
        List<Human> people = new ArrayList<>();
        Human human = new Human("вася", "пупкин", 100);
        people.add(human);
        human.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        return people;
    }

    private List<Flat> mockFlatList() {
        List<Flat> flats = new ArrayList<>();
        List<Human> people = mockHumanList();
        Flat flat1 = new Flat(1, people);
        Flat flat2 = new Flat(2, people);
        flats.add(flat1);
        flats.add(flat2);
        return flats;
    }
}
