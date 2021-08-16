import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Parser class that contains static methods to parse raw text.
 */
public class Parser {

    /**
     * Parses raw user input. Returns a Map of keywords to values, where the '/' character is
     * parsed as a keyword.
     * @param text The user input to be parsed
     * @return Map of string to string, of keyword to value pairs.
     */
    public static Map<String, String> parseCommand(String text) {
        HashMap<String, String> inputMap = new HashMap<>();
        String[] splitted = text.split("\\s");

        String key = splitted[0];
        StringBuilder arguments = new StringBuilder();
        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].startsWith("/")) {
                if (arguments.length() > 0) {
                    arguments.deleteCharAt(arguments.length() - 1);
                    inputMap.put(key, arguments.toString());
                    arguments.setLength(0);
                    key = splitted[i];
                } else {
                    inputMap.put(key, null);
                    key = splitted[i];
                }
            } else {
                arguments.append(String.format("%s ", splitted[i]));
            }
        }
        if (arguments.length() > 0) {
            arguments.deleteCharAt(arguments.length() - 1);
            inputMap.put(key, arguments.toString());
        } else {
            if (!inputMap.containsKey(key)) {
                inputMap.put(key, null);
            }
        }
        return inputMap;
    }

    /**
     * Tries to parse an int.
     * @param text A supposed numeric string.
     * @return Optional of the parsed string. If the string is erroneous,
     * returns an empty optional.
     */
    public static Optional<Integer> parseInt(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
