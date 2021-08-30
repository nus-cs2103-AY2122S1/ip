package duke.task;


/**
 * Event class, inherits from Task
 * Events are tasks with a description and a "/at " followed by the time and date
 */
public class Event extends Task {

    /**
     * Constructor for an Event instance
     *
     * @param desc The description of the event
     * @param at   The time and date of the event
     */
    public Event(String desc, String at) {
        super(desc);
        this.dueDate = at;
        this.taskType = "E";
    }

    public Event(String desc, String at, Boolean isDone) {
        super(desc);
        this.dueDate = at;
        this.taskType = "E";
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (at: " + dueDate + ")";
    }
}
