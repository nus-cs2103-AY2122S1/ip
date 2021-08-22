package tiger.parser;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.utils.RemoveSpaces;

public class ToDoParser extends Parser {

    public String todo = "";

    /**
     * The {@code ToDoParser} parser class takes in an input String and
     * parses it, so that the {@code ToDoAction} class can access the
     * class fields and understand user input.
     *
     * @param  input String to be parsed.
     * @throws TigerEmptyStringException If input is invalid.
     */

    public ToDoParser(String input) throws TigerEmptyStringException {
        super(input);
        RemoveSpaces removeSpaces = new RemoveSpaces();
        try {
            String[] array =
                    removeSpaces.removeBackAndFrontSpaces(input).split(" ");
            for (int i = 1; i < array.length; i++) {
                this.todo += (array[i] + " ");
            }
            this.todo = removeSpaces.removeBackAndFrontSpaces(this.todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TigerEmptyStringException("ToDo description");
        }
    }
}