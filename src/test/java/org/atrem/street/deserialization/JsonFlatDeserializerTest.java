package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFlatDeserializerTest {
    private final FlatDeserializer FLAT_DESERIALIZER = new FlatDeserializer();

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
    public void shouldDeserializeFlatObj() {
        String serializedFlat = getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\flat.json");
        List<Human> humansList = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        Human human2 = new Human("андрей", "лукин", 250);
        human1.getListOfPet().add(new Pet("Шарик", AnimalType.CAT));
        human1.getListOfPet().add(new Pet("Тузик", AnimalType.DOG));
        human2.getListOfPet().add(new Pet("Кеша", AnimalType.BIRD));
        humansList.add(human1);
        humansList.add(human2);
        Flat expectedFlat = new Flat(1, humansList);
        Flat actualFlat = FLAT_DESERIALIZER.convertFromJsonObject(serializedFlat);
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
        List<Flat> actualFlats = FLAT_DESERIALIZER.convertFromJsonArray(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\flatList.json"));
        Assertions.assertEquals(expectedFlats, actualFlats);
    }
}
