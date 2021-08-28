package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.inputs.TigerTooManyInputsException;
import tiger.utils.StringUtils;

/**
 * The {@code DeleteParser} parser class takes in an input String and parses it, so that the {@code DeleteAction}
 * class can access the class fields and understand user input.
 */

public class DeleteParser extends Parser {

    private int index;
    private String input;

    /**
     * Constructor of the {@code DeleteParser class}
     *
     * @param input Input of the user.
     */

    public DeleteParser(String input) {
        this.input = input;
    }

    @Override
    public void parse() throws TigerInvalidInputException {
        String[] array =
                new StringUtils().removeBackAndFrontSpaces(this.input).split(" ");
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
            assert(this.index > 0);
            if (array.length > 2) {
                throw new TigerTooManyInputsException("");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Delete index");
        } catch (NumberFormatException e) {
            throw new TigerInvalidArgumentException(array[1], "Delete");
        } catch (AssertionError e) {
            throw new TigerInvalidArgumentException(array[1], "Delete");
        }
    }

    /**
     * Gets the index of the task to delete.
     *
     * @return the index of the task to delete.
     */

    public int getIndex() {
        return this.index;
    }
}
