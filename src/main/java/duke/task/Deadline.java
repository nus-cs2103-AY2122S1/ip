package duke.task;

/**
 * This class encapsulates a deadline
 * (an event that needs to be done before a specific time)
 * e.g., submit IP by 19/8/21 2359
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    private final String dueDate;

    /**
     * Instantiates a new Deadline task.
     *
     * @param name the subject of the task.
     * @param dueDate the due date.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public String formatToSave() {
        return String.format("D | %s | %s", super.formatToSave(), dueDate);
    }

    /**
     * String representation of a deadline.
     *
     * @return String representation of a deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deadline) {
            Deadline dl = (Deadline) o;
            return dl.getTaskName().equals(this.getTaskName()) && dl.dueDate.equals(this.dueDate);
        }
        return false;
    }
}