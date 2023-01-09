package org.atrem.street.serialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonHouseSerializerTest {

    private final String HUMAN_LIST = "[{\"name\": \"вася\", \"lastName\": \"пупкин\", \"money\": 100, \"listOfPet\": [{\"name\": \"Шарик\", \"type\": \"CAT\"}, {\"name\": \"Тузик\", \"type\": \"DOG\"}]}]";
    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}, {\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";

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

    @Test
    public void shouldSerializeHouseObject() {
        List<Flat> flats = mockFlatList();
        House house = new House(1, flats);
        Serializer<House> houseSerializer = new HouseSerializer();
        String jsonHouse = houseSerializer.toJsonObject(house);
        String expected = "{" + "\"number\": 1, \"listOfFlats\": " + FLAT_LIST + "}";
        Assertions.assertEquals(expected, jsonHouse);
    }

    @Test
    public void shouldSerializeHouseList() {
        List<House> houses = new ArrayList<>();
        List<Flat> flats = mockFlatList();
        House house1 = new House(1, flats);
        House house2 = new House(2, flats);
        houses.add(house1);
        houses.add(house2);
        Serializer<House> houseSerializer = new HouseSerializer();
        String jsonHouse = houseSerializer.toJsonArray(houses);
        String expected = "[{" + "\"number\": 1, \"listOfFlats\": " + FLAT_LIST + "}, {" + "\"number\": 2, \"listOfFlats\": " + FLAT_LIST + "}]";
        Assertions.assertEquals(expected, jsonHouse);
    }
}
