package duke.task;

import duke.util.DukeException;

/**
 * Event is created by 'todo eat breakfast'
 * Events are a type of Task.
 */
public class ToDo extends Task {
    public static final char SYMBOL = 'T';

    // Messages
    private static final String MISSING_TODO_MESSAGE = "Please input text after the todo command";
    private static final String INVALID_SAVE_MESSAGE = "Todo save is given in the wrong format";

    /**
     * Acts as the constructor for the todo object.
     *
     * @param text The description of the todo
     * @param isDone Whether the todo is finished
     */
    private ToDo(String text, boolean isDone) {
        super(text, isDone);
    }

    /**
     * Acts as the factory method for creating a todo object.
     *
     * @param input The remaining input after the initial 'todo' string
     * @return A Todo object
     * @throws DukeException An exception thrown according to the message given
     */
    public static ToDo createNewTodo(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(MISSING_TODO_MESSAGE);
        }
        String todoText = input.trim();

        return new ToDo(todoText, false);
    }

    /**
     * Acts as the factory method for creating a todo object from taskList.txt.
     *
     * @param input The remaining string after the 'T |' string.
     * @return A Todo object
     * @throws DukeException An exception thrown according to the message given
     */
    public static ToDo createNewToDoFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 2) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String doneString = inputArr[0].trim();
        String text = inputArr[1].trim();
        return new ToDo(text, doneString.equals("1"));
    }

    /**
     * Gets the format of the Todo in taskList.txt
     *
     * @return The String format of the Todo in taskList.txt
     */
    public String getSaveFormat() {
        return String.format("%c | %d | %s", SYMBOL, super.getDoneInt(), this.getText());
    }

    /**
     * Gets the format of the todo in console.
     *
     * @return The String format of the todo in console.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s", SYMBOL, super.toString());
    }
}
