package duke;

/**
 * todo task. Represents a task that is yet to be done with no specific timeframe.
 */
public class Todo extends Task {

    /**
     * Creates new todo task with description
     *
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the todo task into a string.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        if (this.tag != null) {
            s += " #" + this.tag;
        }
        return s;
    }

    /**
     * Returns save string for deadlines
     *
     * @return save string format
     */
    @Override
    public String getSaveString() {
        String s = "T" + this.getStatusIcon() + "_" + description;
        if (this.tag != null) {
            s += "_" + tag;
        }
        return s;
    }
}
