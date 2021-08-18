package main.java;

public class Event extends Task {

    /**
     * Variable that holds the event time as String
     */
    protected String at;

    /**
     * Constructor for the Event class
     */
    Event(String task, String at) {
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
        return "[E]" + (this.done ? "[X] " + this.task : "[ ] " + this.task) + "(At: " + this.at + ")";
    }
}
