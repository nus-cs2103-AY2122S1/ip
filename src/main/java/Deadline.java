/**
 * A type of Task. Inherits from Task, takes in a deadline that
 * specifies when task has to be completed by.
 */
public class Deadline extends Task{
    private String deadline;

    /**
     * Constructor for Deadline.
     * Takes in a description and a deadline.
     *
     * @param description The description of the task
     * @param deadline The deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline.
     * Takes in a description, deadline, and the status of the task.
     *
     * @param description The description of the task
     * @param deadline The deadline of the task
     * @param isDone Indicates if the task is done
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.deadline = deadline;
        super.setDone(isDone);
    }

    /**
     * Returns the string representation of the Deadline class.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Returns a string formatted for writing into file.
     *
     * @return String representation of the task for file writing
     */
    @Override
    public String saveString() {
        return "D," + super.saveString() + "," + this.deadline;
    }
}
