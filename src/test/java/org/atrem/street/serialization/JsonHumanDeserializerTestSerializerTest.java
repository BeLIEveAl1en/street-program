package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonHumanDeserializerTestSerializerTest {

    private final String HUMAN_LIST = "[{\"name\": \"вася\", \"lastName\": \"пупкин\", \"money\": 100, \"listOfPet\": [{\"name\": \"Шарик\", \"type\": \"CAT\"}, {\"name\": \"Тузик\", \"type\": \"DOG\"}]}]";

    @Test
    public void shouldSerializeHumanList() {
        List<Human> people = mockHumanList();
        Serializer<Human> humanSerializerSerializer = new HumanSerializer();
        String jsonPetArray = humanSerializerSerializer.toJsonArray(people);
        Assertions.assertEquals(HUMAN_LIST, jsonPetArray);
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

    private List<Human> mockHumanList() {
        List<Human> people = new ArrayList<>();
        Human human = new Human("вася", "пупкин", 100);
        people.add(human);
        human.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        return people;
    }
}
