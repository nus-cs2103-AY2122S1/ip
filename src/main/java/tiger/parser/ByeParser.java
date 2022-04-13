package tiger.parser;

import tiger.exceptions.inputs.TigerInvalidInputException;

/**
 * Practically, {@code ByeParser} does nothing as there is nothing to parse for the {@code Bye} command. This is a
 * filler class in case some additional arguments is added to the {@code Bye} command in the future.
 */

public class ByeParser extends Parser {

    private String input;

    /**
     * Constructor of the {@code ByeParser class}
     *
     * @param input Input of the user.
     */

    public ByeParser(String input) {
        // does nothing, nothing to parse
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {

    }
}
