package tiger.parser;

import tiger.exceptions.DukeEmptyStringException;
import tiger.exceptions.DukeInvalidArgumentException;

public class DoneCommand extends Command {

    public int index;

    public DoneCommand(String input) {
        String[] array = input.split(" ");
        // TODO: ensure that assertions work
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Done index");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(array[1], "Done");
        }
    }
}
