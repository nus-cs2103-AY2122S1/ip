package duke.task;

/**
 * The event class is a task class that has an additional parameter at.
 */
public class Event extends Task {
    String at;


    /**
     * Basic constructor.
     *
     * @param description The description of the task
     * @param at The location the task is at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overridden constructor to include isDone.
     *
     * @param description The description of the task.
     * @param at The location the task is at.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, String at, boolean isDone) {
        super(description,
                isDone);
        this.at = at;
    }

    /**
     * Overridden toString method for printing the object.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the save-friendly version of the object.
     *
     * @return String of the save-friendly version of the object.
     */
    @Override
    public String saveString() {
        return String.format("E|%s|%s|%s",
                super.description,
                this.at,
                super.isDone ? "1" : "0");
    }

}
