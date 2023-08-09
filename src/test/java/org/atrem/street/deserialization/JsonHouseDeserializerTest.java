package org.atrem.street.deserialization;

import org.atrem.street.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
=======
import java.io.FileReader;
>>>>>>> 64c1928ca8b69c5033cbd52d7ef9c209d9dcea6a
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHouseDeserializerTest {
    private final HouseDeserializer HOUSE_DESERIALIZER = new HouseDeserializer();

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

    @Test
    public void shouldDeserializeHouseObj() {
        String serializedFlat = getJSON("src\\test\\resources\\house.json");
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
        House actualHouse = HOUSE_DESERIALIZER.convertFromJsonObject(serializedFlat);
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
        List<House> actualHouses = HOUSE_DESERIALIZER.convertFromJsonArray(getJSON("src\\test\\resources\\houseList.json"));
        Assertions.assertEquals(expectedHousesList, actualHouses);
    }
}
