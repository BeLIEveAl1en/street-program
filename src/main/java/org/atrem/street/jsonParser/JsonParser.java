package org.atrem.street.jsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonParser {
    private static final int KEY_APPEND_STATE = 0;
    private static final int VALUE_APPEND_STATE = 1;
    private static final int ENTRY_VALUE_AND_KEY = 2;

    public static HashMap<String, String> getMapFromJsonObj(String arg) {
        HashMap<String, String> map = new HashMap<>();

        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();

        int state = KEY_APPEND_STATE;
        int counterOfSquareBrackets = 0;

        for (int i = 0; i < arg.length(); i++) {
            char element = arg.charAt(i);

            if (element == ':') {
                state = VALUE_APPEND_STATE;
            } else if (element == ']') {
                counterOfSquareBrackets--;
            } else if (element == '[') {
                counterOfSquareBrackets++;
            } else if ((element == ',' || i == arg.length() - 1) && counterOfSquareBrackets == 0) {
                state = ENTRY_VALUE_AND_KEY;
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
                    key.setLength(0);
                    value.setLength(0);
                    state = KEY_APPEND_STATE;
                    break;
            }
        }
        return map;
    }

    public static ArrayList<String> splitJsonArray(String arg) {
        ArrayList<String> jsons = new ArrayList<>();

        StringBuilder map = new StringBuilder();

        int state = KEY_APPEND_STATE;
        int countOfSquareBrackets = 0;

        for (int i = 0; i < arg.length(); i++) {
            char element = arg.charAt(i);

            switch (state) {
                case KEY_APPEND_STATE:
                    if (element == '{') {
                        map.append(element);
                        state = 1;
                    }
                    break;

                case VALUE_APPEND_STATE:
                    if (element == ']')
                        countOfSquareBrackets--;
                    else if (element == '[')
                        countOfSquareBrackets++;
                    if (element == '}' && countOfSquareBrackets == 0) {
                        map.append(element);
                        state = ENTRY_VALUE_AND_KEY;
                    } else {
                        map.append(element);
                    }
                    break;

                case ENTRY_VALUE_AND_KEY:
                    jsons.add(map.toString());
                    map.setLength(0);
                    state = KEY_APPEND_STATE;
                    break;
            }
        }
        return jsons;
    }
}
