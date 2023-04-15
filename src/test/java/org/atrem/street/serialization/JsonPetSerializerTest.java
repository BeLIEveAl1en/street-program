package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonPetSerializerTest {

    private final PetSerializer PET_SERIALIZER = new PetSerializer();

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
    public void shouldSerializePetObject() {
        Pet pet = new Pet("толя", AnimalType.CAT);
        String jsonPet = PET_SERIALIZER.toJsonObject(pet);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\pet.json"), jsonPet);
    }

    @Test
    public void shouldSerializePetList() {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("толя", AnimalType.CAT));
        pets.add(new Pet("шарик", AnimalType.DOG));
        pets.add(new Pet("куку", AnimalType.BIRD));
        String jsonPetArray = PET_SERIALIZER.toJsonArray(pets);
        Assertions.assertEquals(getJSON("C:\\java\\programs\\street-program\\src\\test\\resources\\petList.json"), jsonPetArray);
    }
}
