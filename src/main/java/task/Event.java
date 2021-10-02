package task;


import java.util.ArrayList;

/**
 * A type of task that requires event details and extends from Task
 *
 * @author: Wei Yangken
 */
public class Event extends Task {

    private static final String TASK_CAT = "event";
    private String eventDetails;
    private ArrayList<Tag> tags;

    /**
     *  Constructor to create an EVENT task
     * @param name Name of task
     * @param eventDetails Location or time of event
     */
    public Event(String name, String eventDetails, boolean isDone) {
        super(name, TASK_CAT, isDone);
        this.eventDetails = eventDetails;
        this.tags = new ArrayList<>();
    }

    public Event(String name, String eventDetails, boolean isDone, ArrayList<Tag> tags) {
        super(name, TASK_CAT, isDone, tags);
        this.eventDetails = eventDetails;
        this.tags = new ArrayList<>();
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return String.format("[E][X] %s (at: %s)", this.getName(), this.eventDetails);
        } else {
            return String.format("[E][ ] %s (at: %s)", this.getName(), this.eventDetails);
        }
    }

    /**
     * @return Formatted event details of task
     */
    @Override
    public String getDetail() {
        return "/at " + this.eventDetails;
    }

    /**
     * @return Raw event details of task
     */
    public String getEventDetails() {
        return this.eventDetails;
    }

    /**
     * @param o Object to be compared to
     * @return Whether the tasks share the same name and event
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event task = (Event) o;
        // Compare the data members and return accordingly
        return this.getName().equals(task.getName())
                && this.eventDetails.equals(task.getEventDetails());
    }
}
