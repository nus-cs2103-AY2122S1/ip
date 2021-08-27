package tiger.parser;

public class ClearParser extends Parser {

    /**
     * Practically, {@code ClearParser} does nothing as there is nothing to parse
     * for the {@code Clear} command. This is a filler class in case some additional
     * arguments is added to the {@code Clear} command in the future.
     *
     * @param input String to be parsed.
     */

    public ClearParser(String input) {
        // does nothing, nothing to parse
        super(input);
    }
}
