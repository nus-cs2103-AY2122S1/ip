package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.inputs.TigerTooManyInputsException;
import tiger.utils.StringUtils;

/**
 * The {@code MarkDoneParser} parser takes in an input String and parses it, so that the {@code MarkDoneAction} class
 * can access the class fields and understand user input.
 */

public class MarkDoneParser extends Parser {

    private int index;
    private String input;

    /**
     * Constructor for the {@code MarkDoneParser} class.
     *
     * @param input String to be parsed.
     */

    public MarkDoneParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
        StringUtils removeSpaces = new StringUtils();
        String[] array = removeSpaces.removeBackAndFrontSpaces(input).split(
                " ");
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
            assert (this.index > 0);
            if (array.length > 2) {
                throw new TigerTooManyInputsException("");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Done index");
        } catch (NumberFormatException e) {
            throw new TigerInvalidArgumentException(array[1], "Done");
        } catch (AssertionError e) {
            throw new TigerInvalidArgumentException(array[1], "Done");
        }
    }

    /**
     * Gets the index of the task to mark done.
     *
     * @return the index of the task to mark done.
     */

    public int getIndex() {
        return this.index;
    }
}
