package org.atrem.street.serialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHouseSerializerTest {
    private final HouseSerializer HOUSE_SERIALIZER = new HouseSerializer();

    private String getJSON(String file) {
        StringBuilder expectedJSON = new StringBuilder();
        Path path = Paths.get(file);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String s : lines) {
                expectedJSON.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expectedJSON.toString().replaceAll("\\s", "");
    }

    private List<Human> mockHumanList() {
        List<Human> people = new ArrayList<>();
        Human human = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        people.add(human);
        people.add(human2);
        human.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
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
        String jsonHouse = HOUSE_SERIALIZER.toJsonObject(house);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\house.json"), jsonHouse);
    }

    @Test
    public void shouldSerializeHouseList() {
        List<House> houses = new ArrayList<>();
        List<Flat> flats = mockFlatList();
        House house1 = new House(1, flats);
        House house2 = new House(2, flats);
        houses.add(house1);
        houses.add(house2);
        String jsonHouse = HOUSE_SERIALIZER.toJsonArray(houses);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\houseList.json"), jsonHouse);
    }
}
