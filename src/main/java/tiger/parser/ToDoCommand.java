package tiger.parser;

import tiger.exceptions.TigerEmptyStringException;
import tiger.utils.RemoveLastSpaces;

public class ToDoCommand extends Command {

    public String todo = "";

    public ToDoCommand(String input) throws TigerEmptyStringException {
        try {
            String[] array = input.split(" ");
            for (int i = 1; i < array.length; i++) {
                this.todo += (array[i] + " ");
            }
            RemoveLastSpaces removeLastSpaces = new RemoveLastSpaces();
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("ToDo description");
        }
    }
}