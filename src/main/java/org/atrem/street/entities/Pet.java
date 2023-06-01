package org.atrem.street.entities;

import java.util.Objects;

public class Pet {
    private final String name;
    private final AnimalType type;

    public Pet(String name, AnimalType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && type == pet.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
