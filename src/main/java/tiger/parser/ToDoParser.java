package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.RemoveSpaces;

public class ToDoParser extends Parser {

    public String todo = "";

    public ToDoParser(String input) throws TigerEmptyStringException {
        super(input);
        try {
            String[] array = input.split(" ");
            for (int i = 1; i < array.length; i++) {
                this.todo += (array[i] + " ");
            }
            RemoveSpaces removeLastSpaces = new RemoveSpaces();
            this.todo = removeLastSpaces.removeLastSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("ToDo description");
        }
    }
}