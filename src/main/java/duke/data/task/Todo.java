package duke.data.task;


/**
 * Class that represents a To Do task.
 *
 * @author Wang Hong Yong
 */
public class Todo extends Task {

    /**
     * Constructor for ToDo class.
     *
     * @param description Description of the task.
     */
    public Todo(String description, String[] tags) {
        super(description);
        for (String tag: tags) {
            super.addTag(tag);
        }
    }

    /**
     * Returns the string representation of the To Do task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the event task to the desirable format.
     *
     * @return A string representing the event task in the desirable format.
     */
    public String formatToWrite() {
        return String.format("T | %d | %s", (super.isDone ? 1 : 0), super.formatToWrite());
    }
}
