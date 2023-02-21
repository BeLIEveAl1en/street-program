package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHumanDeserializerTest {
    private final HumanDeserializer HUMAN_DESERIALIZER = new HumanDeserializer();

    private String getJSON(String file) {
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
    public void shouldDeserializeHumanObj() {
        String serializedHuman = getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\human.json");
        Human expectedHuman = new Human("вася", "пупкин", 100);
        expectedHuman.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        expectedHuman.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        Human actualHuman = HUMAN_DESERIALIZER.convertFromJsonObject(serializedHuman);
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
        List<Human> humanDeserializer = HUMAN_DESERIALIZER.convertFromJsonArray(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\humanList.json"));
        Assertions.assertEquals(humansList, humanDeserializer);
    }
}
