package tiger.parser;

import tiger.exceptions.inputs.TigerSemiColonException;

import java.util.Locale;

/**
 * The {@code Parser} class takes in the user input String, and parses
 * the string in relevant substrings so that {@code Action} classes
 * can use these substrings to create {@code Tasks}.
 */

public class Parser {

    String input;

    /**
     * Constructor for the Parser class.
     *
     * @param input User input input.
     */

    public Parser(String input) throws TigerSemiColonException {
        if (input.contains(";")) {
            throw new TigerSemiColonException("");
        }
        this.input = input;
    }

    // TODO: rewrite parsing functionality with regrex

    /**
     * Get the first word of user command.
     * Example Input: event Make stuff /by 18:00.
     * Example Output: event.
     *
     * @return String representing user command.
     */

    public String getCommandKeyword() {
        String[] array = this.input.split(" ");
        return array[0].toLowerCase(Locale.ENGLISH);
    }

}
