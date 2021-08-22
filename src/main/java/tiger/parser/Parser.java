package tiger.parser;

import tiger.exceptions.inputs.TigerSemiColonException;

/**
 * The {@code Parser} class takes in the user input String, and parses
 * the string in relevant substrings so that {@code Action} classes
 * can use these substrings to create {@code Tasks}.
 */

public class Parser {

    String command;

    /**
     * Constructor for the Parser class.
     *
     * @param command User input commmand.
     */

    public Parser(String command) throws TigerSemiColonException {
        if (command.contains(";")) {
            throw new TigerSemiColonException("");
        }
        this.command = command;
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
        String[] array = this.command.split(" ");
        return array[0];
    }

}
