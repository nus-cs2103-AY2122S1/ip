package tiger.parser;

import java.util.Locale;

import tiger.exceptions.inputs.TigerInvalidInputException;

/**
 * The {@code Parser} class takes in the user input String, and parses
 * the string in relevant substrings so that {@code Action} classes
 * can use these substrings to create {@code Tasks}.
 */

public abstract class Parser {

    /**
     * Get the first word of user command.
     *
     * @param input Input string.
     * @return String representing user command.
     */

    public static String getCommandKeyword(String input) {
        String[] array = input.split(" ");
        return array[0].toLowerCase(Locale.ENGLISH);
    }

    /**
     * Parses the user input.
     *
     * @throws TigerInvalidInputException if the input is invalid.
     */
    public abstract void parse() throws TigerInvalidInputException;

    /**
     * Checks if entered user string contains a semicolon.
     *
     * @param input User input.
     * @return Boolean indicating if string contains semicolon
     */
    public static boolean isValid(String input) {
        if (input.contains(";")) {
            return false;
        } else {
            return true;
        }
    }

}
