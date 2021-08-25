/**
 * This class simulates a special type of Task which has a date
 * for which it is to be completed by.
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline
     * @param description the description of the Deadline object.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns the description of the current deadline.
     * @return the description of the current deadline task.
     */
    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("deadline ", "");
        if (temp.contains("/by")) {
            temp = temp.replace("/by", "(by");
            return temp + ")";
        }
        return "(" + temp + ")";
    }

    /**
     * Marks the current Deadline as done.
     */
    @Override
    public void markedDone() {
        super.markedDone();
    }

    /**
     * Returns the current status icon of the current Deadline.
     * @return the current status icon of the deadline.
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * Returns a string containing the Deadline description
     * and its completion status.
     * @return a string containing the Deadline description
     *         and its completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
