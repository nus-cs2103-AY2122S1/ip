package duke;

/**
 * Represents an Event task.
 * @author Nikki
 */
public class Event extends Task {
    static final String DONE = "[E][X] ";
    static final String NOT_DONE = "[E][ ] ";
    private String timing;

    /**
     * Creates an Event task.
     *
     * @param task Name of the event.
     * @param timing Duration of the event.
     */
    public Event(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    /**
     * Returns a String representation of an Event task.
     *
     * @return String representation of this Event task.
     */
    @Override
    String printTask() {
        String result = "";
        if (isComplete) {
            result = DONE;
        } else {
            result = NOT_DONE;
        }
        return result + task + " (at: " + this.timing + ")";
    }
}
