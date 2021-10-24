package org.atrem.street.serialization;

import org.atrem.street.entities.AnimalType;
import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SerializerTest {

    @Test
    public void shouldSerializePetObject(){
        Pet pet = new Pet("толя", AnimalType.CAT);
        Serializer<Pet> petSerializer = new PetSerializer();
        String jsonPet = petSerializer.toJsonObject(pet);
        String expected = "{\"name\":\"толя\",\"type\":\"CAT\"}";
        Assertions.assertEquals(expected, jsonPet);
    }

    @Test
    public void shouldSerializePetList() throws Exception {
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("толя", AnimalType.CAT));
        pets.add(new Pet("шарик", AnimalType.DOG));
        pets.add(new Pet("куку", AnimalType.BIRD));
        Serializer<Pet> petSerializer = new PetSerializer();
        String jsonPetArray = petSerializer.toJsonArray(pets);
        String expected = "[{\"name\": \"толя\", \"type\": \"CAT\"}, {\"name\": \"шарик\", \"type\": \"DOG\"}, {\"name\": \"куку\", \"type\": \"BIRD\"}]";
        Assertions.assertEquals(expected, jsonPetArray);
    }

    @Test
    public void shouldSerializeHumanList(){
        List<Human> people = new ArrayList<>();
        Human human1 = new Human("вася", "пупкин", 100);
        people.add(human1);
        human1.listOfPet.add(new Pet("Шарик", AnimalType.CAT));
        human1.listOfPet.add(new Pet("Тузик", AnimalType.DOG));
        Serializer<Human> humanSerializerSerializer = new HumanSerializer();
        String jsonPetArray = humanSerializerSerializer.toJsonArray(people);
        String expected = "[{\"name\": \"вася\", \"lastName\": \"пупкин\", \"money\": \"100\", \"listOfPet\": [{\"name\": \"Шарик\", \"type\": \"CAT\"}, {\"name\": \"Тузик\", \"type\": \"DOG\"}]}]";
        Assertions.assertEquals(expected, jsonPetArray);
    }

    @Test
    public void shouldSerializeFlatObject(){
        Flat flat = new Flat(1, new ArrayList());
        Serializer<Flat> flatSerializer = new FlatSerializer();
        String jsonFlat = flatSerializer.toJsonObject(flat);
        String expected = "{" + "\"number\": 1\"" + "}";
        Assertions.assertEquals(expected, jsonFlat);
    }

    @Test
    public void shouldSerializeFlatList(){
        List<Flat> flats = new ArrayList<>();
        Human human = null;
        List<Human> listOfHuman;
        Flat flat = new Flat(1, listOfHuman = new ArrayList());
        flats.add(flat);
        listOfHuman.add(human = new Human("Вася", "Пупкин", 10_000));//, human.listOfPet.add((new Pet("Шарик", AnimalType.CAT)))
        Serializer<Flat> flatSerializer = new FlatSerializer();
        String jsonFlat = flatSerializer.toJsonObject((Flat) flats);
        String expected = "[{\"number\":  \"1\", \"listOfHuman\": [{\"name\": \"Вася\", \"latName\": \"Пупкин\", \"money\": \"10000\", \"listOfPet\": [ {\"type\": \"CAT\", \"name\": \"Шарик\"} ]} ]}]";
        Assertions.assertEquals(expected, jsonFlat);
    }
}
