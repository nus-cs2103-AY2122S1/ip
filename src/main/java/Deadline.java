/**
 * Deadline class that inherits from Task and includes a Date (the deaadline of the Task)
 */

public class Deadline extends Task {

    private String date;

    /**
     * Constructor.
     * @param title the Task title
     * @param date the deadline
     */
    public Deadline(String title, String date) {
        super(title);
        this.date = date;
    }

    /**
     * Retrieve info of the deadline.
     * @return a String
     */
    @Override
    public String getInfo() {
        return "[D][ ]" + this.getTitle() + "(by: " + this.date + ")";
    }

    /**
     * toString method.
     * @return a String.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format("[D][ ]" + this.getTitle() + "(by: " + this.date + ")");
        } else {
            return String.format("[D][X]" + this.getTitle() + "(by: " + this.date + ")");
        }

    }
}