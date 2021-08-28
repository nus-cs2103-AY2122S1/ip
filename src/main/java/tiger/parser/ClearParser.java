package tiger.parser;

import tiger.exceptions.inputs.TigerInvalidInputException;

/**
 * Practically, {@code ClearParser} does nothing as there is nothing to parse for the {@code Clear} command. This is
 * a filler class in case some additional arguments is added to the {@code Clear} command in the future.
 */

public class ClearParser extends Parser {

    private String input;

    /**
     * Constructor of the {@code ClearParser class}
     *
     * @param input Input of the user.
     */

    public ClearParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {

    }
}
