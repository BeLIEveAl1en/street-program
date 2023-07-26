package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonPetDeserializerTest {
    private final PetDeserializer PET_DESERIALIZER = new PetDeserializer();

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
    public void shouldDeserializePetObj() {
        String serializedPet = getJSON("src\\test\\resources\\pet.json");
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = PET_DESERIALIZER.convertFromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldDeserializePetArray() {
        String serializedPetArray = getJSON("src\\test\\resources\\petList.json");
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("толя", AnimalType.CAT));
        petList.add(new Pet("шарик", AnimalType.DOG));
        petList.add(new Pet("куку", AnimalType.BIRD));
        List<Pet> petDeserializer = PET_DESERIALIZER.convertFromJsonArray(serializedPetArray);
        Assertions.assertEquals(petList, petDeserializer);
    }
}
