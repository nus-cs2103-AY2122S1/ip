package duke.task;

/**
 * Task that stores when it is at.
 */
public class Event extends Task {
    private final String dateDesc;

    /**
     * Constructor for Event.
     *
     * @param desc     Description of Task
     * @param dateDesc Deadline for Task
     */
    public Event(String desc, String dateDesc) {
        super(desc);
        this.dateDesc = dateDesc;

    }

    /**
     * Constructor for Event.
     *
     * @param isDone   String representation of task being done
     * @param desc     Description of Task
     * @param dateDesc Deadline for Task
     */
    public Event(String isDone, String desc, String dateDesc) {
        super(isDone, desc);
        this.dateDesc = dateDesc;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateDesc + ")";
    }

    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + dateDesc;
    }
}
