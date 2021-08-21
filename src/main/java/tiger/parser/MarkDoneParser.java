package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;

public class MarkDoneParser extends Parser {

    public int index;

    public MarkDoneParser(String input) {
        super(input);
        String[] array = input.split(" ");
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Done index");
        } catch (NumberFormatException e) {
            throw new TigerInvalidArgumentException(array[1], "Done");
        }
    }
}
