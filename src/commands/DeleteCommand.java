package commands;

import exceptions.DukeEmptyStringException;
import exceptions.DukeInvalidArgumentException;

public class DeleteCommand extends Command {

    public int index;

    public DeleteCommand(String input) {
        String[] array = input.split(" ");
        // TODO: ensure that assertions work
        try {
            this.index = Integer.valueOf(array[1].replaceAll(" ", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("Delete index");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException(array[1], "Delete");
        }
    }
}
