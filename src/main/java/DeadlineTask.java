package main.java;

/**
 * Task subclass that encapsulates the individual "Deadline" task passed into the bot.
 */
public class DeadlineTask extends Task {

    /**
     * Variable that holds the deadline time as String
     */
    protected String by;

    /**
     * Constructor for the Deadline class
     */
    DeadlineTask(String task, String by) {
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
        return "[D]" + super.getTaskState() + "(By: " + this.by + ")";
    }
}
