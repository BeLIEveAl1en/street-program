package org.atrem.street.jsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface JsonParser<T> {
    T getObjFromJsonObj(String map);

    List<T> getArrayFromJsonArray(String jsonArr);

    default ArrayList<HashMap<String, String>> getMapArrayFromJsonArr(String arg) {
        ArrayList<HashMap<String, String>> hashMapsArray = new ArrayList<>();
        StringBuilder map = new StringBuilder();

        int state = 0;

        for (int i = 0; i < arg.length(); i++) {
            char element = arg.charAt(i);

            switch (state) {
                case 0:
                    if (element == '{')
                        state = 1;
                    break;

                case 1:
                    if (element == '}') {
                        state = 2;
                        break;
                    }
                    map.append(element);
                    break;

                case 2:
                    hashMapsArray.add(getMapFromJsonObj(map.toString()));
                    map.setLength(0);
                    state = 0;
                    break;
            }
        }
        return hashMapsArray;
    }

    default HashMap<String, String> getMapFromJsonObj(String arg) {
        final int KEY_APPEND_STATE = 0;
        final int VALUE_APPEND_STATE = 1;
        final int ENTRY_VALUE_AND_KEY = 2;

        HashMap<String, String> map = new HashMap<>();

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        int state = 0;
        int counterOfSquareBrackets = 0;

        for (int i = 0; i < arg.length(); i++) {
            char element = arg.charAt(i);

            if (element == ':') {
                state = 1;
            } else if (element == ']') {
                counterOfSquareBrackets--;
            } else if (element == '[') {
                counterOfSquareBrackets++;
            } else if ((element == ',' || i == arg.length() - 1) && counterOfSquareBrackets == 0) {
                state = 2;
            }

            switch (state) {
                case KEY_APPEND_STATE:
                    if (!(element == '{' || element == '"'))
                        key.append(element);
                    break;

                case VALUE_APPEND_STATE:
                    if (counterOfSquareBrackets > 0 || !(element == ':' || element == '"'))
                        value.append(element);
                    break;

                case ENTRY_VALUE_AND_KEY:
                    if (i == arg.length() - 1 && element != '}' && element != '"')
                        value.append(element);
                    map.put(key.toString().strip(), value.toString().strip());
                    key.delete(0, key.length());
                    value.delete(0, value.length());
                    state = 0;
                    break;
            }
        }
        return map;
    }

    default ArrayList<String> splitJsonArray(String arg){
        ArrayList<String> jsons = new ArrayList<>();

        StringBuilder map = new StringBuilder();

        int state = 0;
        int countOfSquareBrackets = 0;

        for (int i = 0; i < arg.length(); i++) {
            char element = arg.charAt(i);

            switch (state) {
                case 0:
                    if (element == '{') {
                        map.append(element);
                        state = 1;
                    }
                    break;

                case 1:
                    if (element == ']')
                        countOfSquareBrackets--;
                    else if (element == '[')
                        countOfSquareBrackets++;
                    if (element == '}' && countOfSquareBrackets == 0) {
                        map.append(element);
                        state = 2;
                    }
                    else {
                        map.append(element);
                    }
                    break;

                case 2:
                    jsons.add(map.toString());
                    map.setLength(0);
                    state = 0;
                    break;
            }
        }
        return jsons;
    }
}
