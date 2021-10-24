package org.atrem.street.deserialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Pet;

import java.util.List;

public class PetDeserializer implements Deserializer<Pet>{

    @Override
    public Pet fromJsonObject(String jsonObj) {
        return new Pet("толя", AnimalType.CAT);
    }

    @Override
    public List<Pet> fromJsonArray(String jsonArray) {
        return null;
    }
}
