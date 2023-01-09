package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonFlatSerializerTest {
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
    public void shouldSerializeFlatObject() {
        List<Human> people = mockHumanList();
        Flat flat = new Flat(1, people);
        Serializer<Flat> flatSerializer = new FlatSerializer();
        String jsonFlat = flatSerializer.toJsonObject(flat);
        String expected = "{" + "\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}";
        Assertions.assertEquals(expected, jsonFlat);
    }

    @Test
    public void shouldSerializeFlatList() {
        List<Flat> flats = mockFlatList();
        Serializer<Flat> flatSerializer = new FlatSerializer();
        String jsonFlat = flatSerializer.toJsonArray(flats);
        Assertions.assertEquals(FLAT_LIST, jsonFlat);
    }

}
