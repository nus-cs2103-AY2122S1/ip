package main.java.task;

import java.time.LocalDateTime;

/**
 * Task subclass that encapsulates the individual "Event" main.java.task passed into the main.java.bot.
 */
public class EventTask extends Task {

    /**
     * Variable that holds the deadline time as a LocalDateTime object
     */
    protected LocalDateTime time;

    protected String storedTime = "";

    /**
     * Constructor for the Event class
     */
    public EventTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the Event class
     */
    public EventTask(String task, String storedTime, boolean state) {
        super(task, state);
        this.storedTime = storedTime;
    }

    /**
     * Retrieves the completion state of the main.java.task, followed by the main.java.task input.
     *
     * @return The String representation of the main.java.task completion state and the main.java.task input.
     */
    @Override
    public String getTaskState() {
        return "[E]" + super.getTaskState() + "(At: " + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime) + ")";
    }

    @Override
    public String convertToStorageFormat() {
        return "E,"
                + (state ? "1," : "0,")
                + task
                + ","
                + (storedTime.isEmpty() ? time.format(outputFormatter) : storedTime);
    }
}
