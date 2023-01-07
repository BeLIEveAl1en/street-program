package org.atrem.street.validation;

public class JsonObjectValidator {
    private char[] str = new char[0];
    private int counter = 0;
    private int position = 0;
    private int counterOfQuotes = 0;
    private boolean flagOfCurlyBrackets = true;
    private int counterOfSquareBrackets = 0;
    private boolean flagOfBuf = true;
    private boolean flagOfDot = true;
    private final State state = new State(0);

    public ValidationResult validate(String inputStr) {
        str = inputStr.toCharArray();
        ValidationResult result;
        for (position = 0; position < str.length; position++) {
            if (!validationSymbol())
                return ValidationResult.unexpectedSymbol(str[position], position);
        }
        if (flagOfCurlyBrackets && counterOfQuotes == 0 && counter == 0) {
            result = ValidationResult.valid();
        } else {
            result = ValidationResult.unexpectedEOF();
        }
        return result;
    }

    private boolean validationSymbol() {
        char symbol = str[position];
        switch (state.getState()) {
            case 0:
                if (symbol == '{' && flagOfCurlyBrackets) {
                    flagOfCurlyBrackets = false;
                    state.setState(1);
                } else {
                    return false;
                }
                return true;

            case 1:
                if (symbol == '"' && counterOfQuotes == 0) {
                    counterOfQuotes++;
                    state.setState(2);
                } else if (symbol == ',') {
                    flagOfBuf = true;
                } else if (symbol == 't') {
                    state.setState(3);
                } else if (symbol == 'f') {
                    state.setState(4);
                } else if (symbol == 'n') {
                    state.setState(5);
                } else if (Character.isDigit(symbol)) {
                    flagOfDot = true;
                    state.setState(6);
                } else if (symbol == ':' && flagOfBuf) {
                    flagOfBuf = false;
                    state.setState(1);
                } else if (symbol == '{') {
                    state.setState(7);
                } else if (symbol == '[') {
                    counterOfSquareBrackets++;
                    state.setState(8);
                } else if (symbol == '}' && !flagOfCurlyBrackets) {
                    flagOfCurlyBrackets = true;
                    state.setState(1);
                } else if (!Character.isWhitespace(symbol)) {
                    return false;
                }
                return true;

            case 2:
                if (Character.isAlphabetic(symbol) || Character.isWhitespace(symbol) || Character.isDigit(symbol)) {
                    state.setState(2);
                } else if (symbol == '"' && counterOfQuotes == 1) {
                    counterOfQuotes--;
                    state.setState(1);
                } else {
                    return false;
                }
                return true;

            case 3:
                if (symbol == 'r' && counter == 0) {
                    counter++;
                } else if (symbol == 'u' && counter == 1) {
                    counter++;
                } else if (symbol == 'e' && counter == 2) {
                    counter = 0;
                    state.setState(9);
                } else {
                    return false;
                }
                return true;

            case 4:
                if (symbol == 'a' && counter == 0) {
                    counter++;
                } else if (symbol == 'l' && counter == 1) {
                    counter++;
                } else if (symbol == 's' && counter == 2) {
                    counter++;
                } else if (symbol == 'e' && counter == 3) {
                    counter = 0;
                    state.setState(9);
                } else {
                    return false;
                }
                return true;

            case 5:
                if (symbol == 'u' && counter == 0) {
                    counter++;
                } else if (symbol == 'l' && counter == 1) {
                    counter++;
                } else if (symbol == 'l' && counter == 2) {
                    counter = 0;
                    state.setState(9);
                } else {
                    return false;
                }
                return true;

            case 6:
                if (Character.isDigit(symbol)) {
                    state.setState(6);
                } else if (symbol == '.' && flagOfDot) {
                    flagOfDot = false;
                } else if (symbol == ',') {
                    flagOfBuf = true;
                    state.setState(1);
                } else if (symbol == '}') {
                    flagOfCurlyBrackets = true;
                } else {
                    return false;
                }
                return true;

            case 7:
                if (symbol == '}') {
                    state.setState(9);
                }
                return true;

            case 8:
                if (symbol == ']')
                    counterOfSquareBrackets--;
                else if (symbol == '[')
                    counterOfSquareBrackets++;
                if (counterOfSquareBrackets == 0)
                    state.setState(9);
                return true;

            case 9:
                if (symbol == '}' && !flagOfCurlyBrackets) {
                    flagOfCurlyBrackets = true;
                    state.setState(1);
                } else if (symbol == ',') {
                    flagOfBuf = true;
                    state.setState(1);
                } else if (!Character.isWhitespace(symbol)) {
                    return false;
                }
                return true;
        }
        return false;
    }
}
