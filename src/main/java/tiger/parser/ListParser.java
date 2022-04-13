package tiger.parser;

import tiger.exceptions.inputs.TigerInvalidInputException;

/**
 * The {@code ListParser} parser takes in an input String and parses it, so that the {@code ListAction} class
 * can access the class fields and understand user input.
 */


public class ListParser extends Parser {

    private String input;

    /**
     * Practically, {@code ListParser} does nothing as there is nothing to parse
     * for the {@code List} command. This is a filler class in case some additional
     * arguments is added to the {@code List} command in the future.
     *
     * @param input String to be parsed.
     */

    public ListParser(String input) {
        // does nothing, nothing to parse
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {

    }
}
