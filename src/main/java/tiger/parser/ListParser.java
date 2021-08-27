package tiger.parser;

public class ListParser extends Parser {

    /**
     * Practically, {@ListParser} does nothing as there is nothing to parse
     * for the {@List} command. This is a filler class in case some additional
     * arguments is added to the {@List} command in the future.
     *
     * @param input String to be parsed.
     */

    public ListParser(String input) {
        // does nothing, nothing to parse
        super(input);
    }
}
