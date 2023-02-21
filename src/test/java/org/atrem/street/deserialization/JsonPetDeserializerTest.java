package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonPetDeserializerTest {
    private final PetDeserializer PET_DESERIALIZER = new PetDeserializer();

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
    public void shouldDeserializePetObj() {
        String serializedPet = getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\pet.json");
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = PET_DESERIALIZER.convertFromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldDeserializePetArray() {
        String serializedPetArray = getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\petList.json");
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("толя", AnimalType.CAT));
        petList.add(new Pet("шарик", AnimalType.DOG));
        petList.add(new Pet("куку", AnimalType.BIRD));
        List<Pet> petDeserializer = PET_DESERIALIZER.convertFromJsonArray(serializedPetArray);
        System.out.println(petDeserializer);
        Assertions.assertEquals(petList, petDeserializer);
    }
}
