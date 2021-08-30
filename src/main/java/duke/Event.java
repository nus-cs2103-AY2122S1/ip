package duke;

/**
 * Represents an Event task.
 * @author Nikki
 */
public class Event extends Task {

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
        if (super.complete) {
            result = "[E][X] ";
        } else {
            result = "[E][ ] ";
        }
        return result + super.task + " (at: " + this.timing + ")";
    }
}
