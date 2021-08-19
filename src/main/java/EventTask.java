package main.java;

/**
 * Task subclass that encapsulates the individual "Event" task passed into the bot.
 */
public class EventTask extends Task {

    /**
     * Variable that holds the event time as String
     */
    protected String at;

    /**
     * Constructor for the Event class
     */
    EventTask(String task, String at) {
        super(task);
        this.at = at;
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[E]" + super.getTaskState() + "(At: " + this.at + ")";
    }
}
