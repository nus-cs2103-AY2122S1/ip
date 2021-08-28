package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.utils.StringUtils;

/**
 * The {@code FindParser} parser takes in an input String and parses it, so that the {@code FindAction} class
 * can access the class fields and understand user input.
 */

public class FindParser extends Parser {

    private String toSearchFor = "";
    private String input;

    /**
     * Constructor for the {@code FindParser} class.
     *
     * @param input String to be parsed.
     */

    public FindParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
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
