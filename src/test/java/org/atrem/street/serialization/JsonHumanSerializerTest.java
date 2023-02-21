package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHumanSerializerTest {
    private final HumanSerializer HUMAN_SERIALIZER = new HumanSerializer();

    private String getExpectedJSON(String file) {
        String expectedJSON = "";
        try (FileReader reader = new FileReader(file)) {
            int symbol;
            while ((symbol = reader.read()) != -1) {
                expectedJSON += (char) symbol;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expectedJSON.replaceAll("\\s", "");
    }

    @Test
    public void shouldSerializeHumanList() {
        List<Human> people = mockHumanList();
        String jsonHuman = HUMAN_SERIALIZER.toJsonArray(people);
        Assertions.assertEquals(getExpectedJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\humanList.json"), jsonHuman);
    }

    @Test
    public void shouldSerializeHuman() {
        Human human1 = new Human("вася", "пупкин", 100);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        String jsonHuman = HUMAN_SERIALIZER.toJsonObject(human1);
        Assertions.assertEquals(getExpectedJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\human.json"), jsonHuman);
    }

    private List<Human> mockHumanList() {
        List<Human> people = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        people.add(human1);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        Human human2 = new Human("андрей", "лукин", 250);
        people.add(human2);
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        return people;
    }
}
