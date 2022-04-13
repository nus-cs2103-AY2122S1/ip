package duke.task;

import duke.util.DukeException;

/**
 * TimedToDos are created by 'todo eat breakfast /for 2 hours'.
 */
public class TimedToDo extends Task {
    public static final char SYMBOL = 'F';

    public static final String TIMED_TODO_DELIMITER = "/for";

    // Messages
    private static final String INVALID_TIMED_TODO_MESSAGE = "Invalid use of timed todo command."
            + " Use 'todo <text> /for <duration>'";
    private static final String MISSING_TIMED_TODO_MESSAGE = "Some arguments are missing. Use 'todo <text> /for <duration>'";
    private static final String INVALID_SAVE_MESSAGE = "Timed todo save is given in the wrong format";

    private String duration;

    /**
     * Constructor for the TimedToDo object.
     *
     * @param text The description of the TimedTodo.
     * @param duration The amount of time.
     * @param isDone Whether the TimedTodo is finished.
     */
    private TimedToDo(String text, String duration, boolean isDone) throws DukeException {
        super(text, isDone);
        this.duration = duration;
    }

    /**
     * Acts as the factory method for creating a timed todo object.
     *
     * @param input The remaining input after the initial 'todo' string.
     * @param isDone Whether the Timed Todo is finished.
     * @return A timed todo object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static TimedToDo createNewTimedTodo(String input, boolean isDone) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_TIMED_TODO_MESSAGE);
        }

        String[] todoInfo = input.split(TIMED_TODO_DELIMITER);
        if (todoInfo.length < 2) {
            throw new DukeException(INVALID_TIMED_TODO_MESSAGE);
        }
        String duration = todoInfo[1].trim();
        String todoText = todoInfo[0].trim();
        return new TimedToDo(todoText, duration, isDone);
    }

    /**
     * Acts as the factory method for creating a timed todo object from taskList.txt.
     *
     * @param input The remaining string after the 'F |' string.
     * @return A Timed Todo object.
     * @throws DukeException An exception thrown according to the message given.
     */
    public static TimedToDo createNewTimedTodoFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String doneString = inputArr[0].trim();
        String todoText = inputArr[1].trim();
        String todo = inputArr[2].trim();
        return new TimedToDo(todoText, todo, doneString.equals("1"));
    }

    /**
     * Gets the format of the Timed Todo in taskList.txt
     * @return The String format of the Timed Todo in taskList.txt
     */
    public String getSaveFormat() {
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(), this.getText(),
                this.duration);
    }

    /**
     * Gets the format of the Timed Todo in console.
     * @return The String format of the Timed Todo in console.
     */
    @Override
    public String toString() {
        return String.format("[F]%s (for: %s)", super.toString(), this.duration);
    }
}
