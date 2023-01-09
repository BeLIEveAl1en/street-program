package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonPetDeserializerTest {
    @Test
    public void shouldDeserializePetObj() {
        String serializedPet = "{\"name\": \"толя\", \"type\": \"CAT\"}";
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = new PetDeserializer().fromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldDeserializePetArray() {
        String serializedPetArray = "[{\"name\":\"толя\",\"type\":\"CAT\"},{\"name\":\"шарик\",\"type\":\"DOG\"},{\"name\":\"куку\",\"type\":\"BIRD\"}]";
        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("толя", AnimalType.CAT));
        petList.add(new Pet("шарик", AnimalType.DOG));
        petList.add(new Pet("куку", AnimalType.BIRD));
        List<Pet> petDeserializer = new PetDeserializer().fromJsonArray(serializedPetArray);
        System.out.println(petDeserializer);
        Assertions.assertEquals(petList, petDeserializer);
    }
}
