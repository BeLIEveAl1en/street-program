package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonFlatDeserializerTest {
    private final String HUMAN_LIST = "[{\"name\":\"вася\",\"lastName\":\"пупкин\",\"money\":100,\"listOfPet\":[{\"name\":\"Шарик\",\"type\":\"CAT\"},{\"name\":\"Тузик\",\"type\":\"DOG\"}]},{\"name\":\"андрей\",\"lastName\":\"лукин\",\"money\":250,\"listOfPet\":[{\"name\":\"Кеша\",\"type\":\"BIRD\"}]}]";

    private final String FLAT_LIST = "[{\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}, {\"number\": 2, \"listOfHuman\": " + HUMAN_LIST + "}]";

    @Test
    public void shouldDeserializeFlatObj() {
        String serializedFlat = "{" + "\"number\": 1, \"listOfHuman\": " + HUMAN_LIST + "}";
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        Flat expectedFlat = new Flat(1, humansList);
        Flat actualFlat = new FlatDeserializer().fromJsonObject(serializedFlat);
        Assertions.assertEquals(expectedFlat, actualFlat);
    }

    @Test
    public void shouldDeserializeFlatArray() {
        ArrayList<Flat> expectedFlats = new ArrayList<>();
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        expectedFlats.add(new Flat(1, humansList));
        expectedFlats.add(new Flat(2, humansList));
        List<Flat> actualFlats = new FlatDeserializer().fromJsonArray(FLAT_LIST);
        Assertions.assertEquals(expectedFlats, actualFlats);
    }
}
