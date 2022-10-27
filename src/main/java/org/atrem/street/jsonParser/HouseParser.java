package org.atrem.street.jsonParser;

import org.atrem.street.entities.House;

import java.util.List;

public class HouseParser implements JsonParser<House> {
    @Override
    public House getObjFromJsonObj(String map) {
        return null;
    }

    @Override
    public List<House> getArrayFromJsonArray(String jsonArr) {
        return null;
    }
}
