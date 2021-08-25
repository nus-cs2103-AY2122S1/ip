/**
 * Represents tasks with specific timing.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */

public class Event extends Task {

    protected String timing;
    private final String TASK_MARKER = "E";
    
    /**
     * Class constructor for Event class.
     * 
     * @param description the task description.
     * @param timing the duration of the event.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Print out the event task,
     * 
     * @return string format of the event task, 
     * consisting of the task marker "[D]", task description and duration of the event.
     */
    @Override
    public String toString() {
        return String.format("| %s | %s | %s", TASK_MARKER, super.toString(), timing);
    }

    /**
     * Returns task marker. 
     *
     * @return a one character string that is a marker for this task.
     */
    public String getTaskMarker() {
        return TASK_MARKER;
    }
}
