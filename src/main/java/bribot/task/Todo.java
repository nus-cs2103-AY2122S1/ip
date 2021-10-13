package bribot.task;

/**
 * Represents a task that has to be completed.
 */

public class Todo extends Task {
    /**
     * Creates the todo with the given description
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of the task to be written onto the text file during saving.
     * @return the type of the task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the date and time.
     * @return the string representation of the date and time.
     */
    @Override
    public String getDateTimeString() {
        return "";
    }

    /**
     * The String representation of the task.
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline || o instanceof Event) {
            return -1;
        } else {
            return this.description.compareTo(o.description);
        }
    }
}
