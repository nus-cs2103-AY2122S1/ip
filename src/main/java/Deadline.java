/**
 * a class to encapsulate tasks with deadline.
 */

public class Deadline extends Task {

    protected String by;

    /**
     * constructor for Deadline class.
     * @param description the task description.
     * @param by the time by which the task must be completed.
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * method to print out the deadline task,
     * overrides toString in Task.
     * @return string format of the deadline task, consisting of
     * the task marker "[D]", task description and deadline of the task.
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
