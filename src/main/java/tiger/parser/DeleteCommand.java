package tiger.parser;

import tiger.exceptions.TigerEmptyStringException;
import tiger.exceptions.TigerInvalidArgumentException;

public class DeleteCommand extends Command {

    public int index;

    public DeleteCommand(String input) {
        String[] array = input.split(" ");
        // TODO: ensure that assertions work
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("Delete index");
        } catch (NumberFormatException e) {
            throw new TigerInvalidArgumentException(array[1], "Delete");
        }
    }
}
