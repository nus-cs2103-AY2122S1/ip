package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.RemoveSpaces;

public class FindParser extends Parser {

    public String findString = "";

    /**
     * The {@code FindParser} parser class takes in an input String and
     * parses it, so that the {@code FindAction} class can access the
     * class fields and understand user input.
     *
     * @param  input String to be parsed.
     * @throws TigerEmptyStringException If input is invalid.
     */

    public FindParser(String input) throws TigerEmptyStringException {
        super(input);
        RemoveSpaces removeSpaces = new RemoveSpaces();
        try {
            String[] array =
                    removeSpaces.removeBackAndFrontSpaces(input).split(" ");
            for (int i = 1; i < array.length; i++) {
                this.findString += (array[i] + " ");
            }
            this.findString = removeSpaces.removeBackAndFrontSpaces(this.findString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Find description");
        }
    }
}