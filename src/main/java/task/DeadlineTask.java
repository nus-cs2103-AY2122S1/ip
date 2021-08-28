package main.java.task;

import java.time.LocalDateTime;

/**
 * Task subclass that encapsulates the individual "Deadline" main.java.task passed into the main.java.bot.
 */
public class DeadlineTask extends Task {

    /**
     * Variable that holds the deadline time as a LocalDateTime object
     */
    protected LocalDateTime time;

    protected String storedTime = "";

    /**
     * Constructor for the Deadline class
     */
    public DeadlineTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the Deadline class
     */
    public DeadlineTask(String task, String storedTime, boolean done) {
        super(task, done);
        this.storedTime = storedTime;
    }

    /**
     * Retrieves the completion state of the main.java.task, followed by the main.java.task input.
     *
     * @return The String representation of the main.java.task completion state and the main.java.task input.
     */
    @Override
    public String getTaskState() {
        return "[D]" + super.getTaskState() + "(By: " + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime) + ")";
    }

    @Override
    public String convertToStorageFormat() {
        return "D,"
                + (done ? "1," : "0,")
                + task
                + ","
                + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime);
    }
}
