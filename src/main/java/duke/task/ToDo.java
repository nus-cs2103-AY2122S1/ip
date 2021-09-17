package duke.task;

/**
 * This class encapsulates a ToDo.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDo extends Task {
    private static final String TODO_IDENTIFIER = "T";

    /**
     * Instantiates a new To do.
     *
     * @param description the description for todos task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo task into text format meant for persisted storage.
     * Returns the formatted string.
     *
     * @return Formatted string of task meant for persisted storage.
     */
    @Override
    public String convertToText() {
        return String.format(TODO_IDENTIFIER + " | %s", super.convertToText());
    }

    /**
     * Checks whether the date and time user input is the same as the deadline of task.
     *
     * @param dateTime the date and time that the user input.
     * @return true if deadline of task is the same as date and time of user input.
     */
    @Override
    public boolean isSameDateTime(String dateTime) {
        return false;
    }

    /**
     * String representation of Todos task.
     *
     * @return String representation of Todos task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", TODO_IDENTIFIER, super.toString());
    }
}
