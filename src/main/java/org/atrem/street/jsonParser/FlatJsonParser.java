package org.atrem.street.jsonParser;

import org.atrem.street.entities.Flat;
import org.atrem.street.entities.Human;
import org.atrem.street.entities.Pet;

import java.util.HashMap;
import java.util.List;

public class FlatJsonParser implements Parser<Flat>{
    @Override
    public Flat getObjFromJsonObj(String str) {
        HashMap<String, String> flatMap = getMapFromJsonObj(str);

        int num = Integer.parseInt(flatMap.get("number"));
        //List<Human> humans = flatMap.get("humans");

        //return new Flat(num, humans);
        return null;
    }

    @Override
    public List<Flat> getArrayFromJsonArray(String jsonArr) {
        return null;
    }
}
