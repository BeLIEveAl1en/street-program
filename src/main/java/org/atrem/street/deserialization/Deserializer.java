package org.atrem.street.deserialization;

import java.util.List;

public interface Deserializer<T> {

    T fromJsonObject(String jsonObj);

    List<T> fromJsonArray(String jsonArray);

}
