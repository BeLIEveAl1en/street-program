package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonHumanDeserializerTest {
    private final String HUMAN_LIST = "[{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]},{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}]";

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
}
