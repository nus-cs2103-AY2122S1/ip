package duke.task;

/**
 * Task that stores when it is at.
 */
public class Event extends Task {
    private final String DATE_DESCRIPTION;

    /**
     * Constructor for Event.
     *
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public Event(String desc, String dateDescription) {
        super(desc);
        this.DATE_DESCRIPTION = dateDescription;

    }

    /**
     * Constructor for Event.
     *
     * @param isDone   String representation of task being done
     * @param desc     Description of Task
     * @param dateDescription Deadline for Task
     */
    public Event(String isDone, String desc, String dateDescription) {
        super(isDone, desc);
        this.DATE_DESCRIPTION = dateDescription;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + DATE_DESCRIPTION + ")";
    }

    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + DATE_DESCRIPTION;
    }
}
