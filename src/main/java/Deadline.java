/**
 * This class creates deadline instances which are to be done by a certain time.
 */
public class Deadline extends Task {
    // The time to complete the deadline by.
    protected String time;

    /***
     * Constructor to create a deadline.
     *
     * @param name The name of the deadline.
     * @param time The time to complete the deadline by.
     */
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    /***
     * Returns the string representation of the deadline.
     *
     * @return The name of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}