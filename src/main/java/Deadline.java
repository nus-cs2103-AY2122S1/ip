/**
 * Deadline class, subclass of Task
 * Encapsulates Task with end time
 */
public class Deadline extends Task {

    //String representing the deadline
    protected String time;

    /**
     * The constructor for the Deadline class
     * @param description The description of the object
     * @param endDate The endDate
     */
    public Deadline(String description, String endDate, boolean isDone) {
        super(description.trim(), isDone);
        this.time = endDate.trim();
    }

    @Override
    public String taskType() {
        return "deadline";
    }

    @Override
    public String strForSaving() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.time;
    }

    /**
     * The overridden toString method for the Deadline class
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.firstLetter(this.time) + ")";
    }
}
