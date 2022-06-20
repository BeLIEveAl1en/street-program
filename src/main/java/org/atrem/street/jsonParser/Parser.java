package org.atrem.street.jsonParser;

import java.util.HashMap;

public interface Parser<T>{
    T getObjFromJsonObj(String map);

    T getArrayFromJsonArray(String arg);

    default String getClearJsonObj(String arg){
        StringBuilder  jsonObj = new StringBuilder(arg);

        for (int i = 0; i < jsonObj.length(); i++){
            char element = jsonObj.charAt(i);

            if(element == '{' || element == '}' || element == '"'){
                jsonObj.deleteCharAt(i);
                i = -1;
            }
        }
        return jsonObj.toString();
    }

    default String clearJsonArray(String arg){
        StringBuilder jsonArray = new StringBuilder(arg);
        for (int i = 0; i < jsonArray.length(); i++){
            char element = jsonArray.charAt(i);

            if(element == '{' || element == '}' || element == '"'|| element == '[' || element == ']'){
                jsonArray.deleteCharAt(i);
                i = -1;
            }
        }
        return jsonArray.toString();
    }
}
