package commands;

import exceptions.DukeEmptyStringException;
import exceptions.DukeInvalidArgumentException;
import utils.RemoveLastSpaces;

public class ToDoCommand extends Command {

    public String todo = "";

    public ToDoCommand(String input) throws DukeEmptyStringException {
        try {
            String[] array = input.split(" ");
            for (int i = 1; i < array.length; i++) {
                this.todo += (array[i] + " ");
            }
            RemoveLastSpaces removeLastSpaces = new RemoveLastSpaces();
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeEmptyStringException("ToDo description");
        }
    }
}