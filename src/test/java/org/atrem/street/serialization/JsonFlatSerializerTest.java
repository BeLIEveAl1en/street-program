package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonFlatSerializerTest {
    private final FlatSerializer FLAT_SERIALIZER = new FlatSerializer();

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
    public void shouldSerializeFlatObject() {
        List<Human> people = mockHumanList();
        Flat flat = new Flat(1, people);
        String jsonFlat = FLAT_SERIALIZER.toJsonObject(flat);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\flat.json"), jsonFlat);
    }

    @Test
    public void shouldSerializeFlatList() {
        List<Flat> flats = mockFlatList();
        String jsonFlat = FLAT_SERIALIZER.toJsonArray(flats);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\flatList.json"), jsonFlat);
    }
}
