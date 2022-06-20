package org.atrem.street.deserialization;

import org.atrem.street.entities.Human;

import java.util.List;

public class HumanDeserializer implements Deserializer{
    @Override
    public Human fromJsonObject(String jsonObj) {
        return null;
    }

    @Override
    public List<Human> fromJsonArray(String jsonArray) {
        return null;
    }
}
