package main.java;

import java.time.LocalDateTime;

/**
 * Task subclass that encapsulates the individual "Deadline" task passed into the bot.
 */
public class DeadlineTask extends Task {

    /**
     * Variable that holds the deadline time as a LocalDateTime object
     */
    protected LocalDateTime time;

    /**
     * Constructor for the Deadline class
     */
    DeadlineTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the Deadline class
     */
    DeadlineTask(String task, String by, boolean done) {
        super(task, done);
        this.by = by;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[D]" + super.getTaskState() + "(By: " + time.format(outputFormatter) + ")";
    }

    @Override
    public String convertFormat() {
        return "D,"
                + (done ? "1," : "0,")
                + task
                + ","
                + by;
    }
}
