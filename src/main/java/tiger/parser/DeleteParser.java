package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.inputs.TigerTooManyInputsException;
import tiger.utils.StringUtils;

public class DeleteParser extends Parser {

    private int index;

    public DeleteParser(String input) {
        super(input);
    }

    /**
     * The {@code DeleteParser} parser class takes in an input String and
     * parses it, so that the {@code DeleteAction} class can access the
     * class fields and understand user input.
     *
     * @throws TigerInvalidInputException If input is invalid.
     */

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

    public int getIndex() {
        return this.index;
    }
}
