package myjournal.task;

/**
 * Creates task with the type of Deadline.
 *
 * @author Felissa Faustine.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Constructs the Deadline object.
     *
     * @param taskName The name of the task.
     * @param time The deadline.
     */
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns the time of the Deadline task.
     *
     * @return The time of the Deadline task.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Gets the symbol of deadline.
     *
     * @return The string representation of the deadline symbol.
     */
    public String getSymbol() {
        return "D";
    }

    /**
     * Sets the deadline of the task.
     *
     * @param time The deadline of the task.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + getSymbol() + "]" + super.toString() + "(by:" + this.time + ")";
    }
}
