package main.java;

public class Deadline extends Task {

    /**
     * Variable that holds the deadline time as String
     */
    protected String by;

    /**
     * Constructor for the Deadline class
     */
    Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[D]" + (this.done ? "[X] " + this.task : "[ ] " + this.task) + "(By: " + this.by + ")";
    }
}
