package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeserializerTest {

    @Test
    public void shouldDeserializePetObj(){
        String serializedPet = "{\"name\": \"толя\", \"type\": \"CAT\"}";
        Pet expectedPet = new Pet("толя", AnimalType.CAT);
        Pet actualPet = new PetDeserializer().fromJsonObject(serializedPet);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldDeserializePetArray(){
        String serializedPetArray = "[{\"name\": \"толя\", \"type\": \"CAT\"}, {\"name\": \"шарик\", \"type\": \"DOG\"}, {\"name\": \"куку\", \"type\": \"BIRD\"}]";
    }
}
