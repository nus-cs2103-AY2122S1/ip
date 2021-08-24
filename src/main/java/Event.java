/**
 * Event class for tasks that start at a specific time and ends at a specific time
 *
 * @author: Chen Hsiao Ting
 */

public class Event extends Task {
    protected String description;
    protected String time;

    public Event(String description, Boolean isDone) {
        super(description, isDone);
        this.description = description;
    }

    /**
     * Print the status and description of the event task.
     * @return a string representation of the event task.
     */
    public String getTask() {
        String[] splitted = description.split("/at ", 2);
        String text = splitted[0];
        time = splitted[1];
        return "[E]" + "[" + super.getStatusIcon() + "] " + text + "(at: " + time + ")";
    }
}
