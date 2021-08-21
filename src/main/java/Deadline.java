package main.java;

/**
 * Deadline is a task that has a date to be done by.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Deadline extends Task {

    private final String date;

    /**
     * Constructor for Deadline task.
     *
     * @param description the description
     */
    public Deadline(boolean isDone, String description, String date) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.date + ")";
    }
}
