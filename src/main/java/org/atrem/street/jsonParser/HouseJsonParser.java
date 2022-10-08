package org.atrem.street.jsonParser;

import org.atrem.street.entities.House;
import org.atrem.street.entities.Pet;

import java.util.List;

public class HouseJsonParser implements Parser<House>{
    @Override
    public House getObjFromJsonObj(String map) {
        return null;
    }

    @Override
    public List<House> getArrayFromJsonArray(String jsonArr) {
        return null;
    }
}
