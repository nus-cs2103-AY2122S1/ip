package tiger.parser;

import tiger.exceptions.TigerEmptyStringException;
import tiger.exceptions.TigerInvalidArgumentException;

public class DeleteParser extends Parser {

    public int index;

    public DeleteParser(String input) {
        super(input);
        String[] array = input.split(" ");
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Delete index");
        } catch (NumberFormatException e) {
            throw new TigerInvalidArgumentException(array[1], "Delete");
        }
    }
}
