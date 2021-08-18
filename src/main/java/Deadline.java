/**
 * Deadline class used to represent a task that has an end date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String done, String description, String date) {
        super(description);
        this.date = date;
        this.isDone = (done.equals("X")) ? true : false;
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
