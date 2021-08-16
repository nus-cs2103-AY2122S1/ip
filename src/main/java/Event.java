package main.java;

/**
 * Event is a task that has a date in which it is happening.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public class Event extends Task {

    private String date;

    /**
     * Constructor for Deadline task.
     *
     * @param description the description
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + this.date + ")";
    }
}
