/**
 * a class to encapsulate tasks with specific timing.
 */

public class Event extends Task {

    protected String timing;

    /**
     * constructor for Event class.
     * @param description the task description.
     * @param timing the duration of the event.
     */

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * method to print out the event task,
     * overrides toString in Task.
     * @return string format of the event task, consisting of
     * the task marker "[D]", task description and duration of the event.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}
