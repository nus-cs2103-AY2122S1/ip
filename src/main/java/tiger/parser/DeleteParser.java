package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerInvalidInputException;
import tiger.exceptions.inputs.TigerTooManyInputsException;
import tiger.utils.RemoveSpaces;

public class DeleteParser extends Parser {

    public int index;

    public DeleteParser(String input) throws TigerInvalidInputException {
        super(input);
        String[] array =
                new RemoveSpaces().removeBackAndFrontSpaces(input).split(" ");
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
}
