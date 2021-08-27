package main.java;

import java.time.LocalDateTime;

/**
 * Task subclass that encapsulates the individual "Event" task passed into the bot.
 */
public class EventTask extends Task {

    /**
     * Variable that holds the deadline time as a LocalDateTime object
     */
    protected LocalDateTime time;

    /**
     * Constructor for the Event class
     */
    EventTask(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    /**
     * Constructor for the Event class
     */
    EventTask(String task, String at, boolean done) {
        super(task, done);
        this.at = at;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[E]" + super.getTaskState() + "(At: " + time.format(outputFormatter) + ")";
    }

    @Override
    public String convertFormat() {
        return "E,"
                + (done ? "1," : "0,")
                + task
                + ","
                + at;
    }
}
