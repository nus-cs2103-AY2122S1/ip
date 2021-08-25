package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.StringUtils;

public class FindParser extends Parser {

    private String toSearchFor = "";

    public FindParser(String input) {
        super(input);
    }

    /**
     * The {@code FindParser} parser class takes in an input String and
     * parses it, so that the {@code FindAction} class can access the
     * class fields and understand user input.
     *
     * @throws TigerEmptyStringException If input is invalid.
     */

    public void parse() throws TigerEmptyStringException {
        StringUtils removeSpaces = new StringUtils();
        try {
            String[] array =
                    removeSpaces.removeBackAndFrontSpaces(input).split(" ");
            for (int i = 1; i < array.length; i++) {
                this.toSearchFor += (array[i] + " ");
            }
            this.toSearchFor = removeSpaces.removeBackAndFrontSpaces(this.toSearchFor);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Find description");
        }
    }

    public String getToSearchFor() {
        return this.toSearchFor;
    }
}