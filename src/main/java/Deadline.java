package main.java;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to create a new Deadline object
     * @param description the description of the task with a deadline
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
