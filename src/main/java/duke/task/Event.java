package duke.task;

/**
 * Class that extends Task. This task is an event and has the additional
 * information of a location labeled as "at".
 */
public class Event extends Task {
    private String at;
    private String tag = "E";

    /**
     * Constructor method for Event.
     *
     * @param taskName The main details of the task.
     * @param at The additional information of the task.
     */
    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    /**
     * Getter method for obtaining the additional information of the task.
     *
     * @return The additional information of the task which is the location of the task.
     */
    @Override
    public String getAdditionalInfo() {
        return at;
    }

    /**
     * Getter method to obtain the tag of the task.
     *
     * @return "E" to denote event.
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * To String method for tasks.
     *
     * @return The task in String format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
