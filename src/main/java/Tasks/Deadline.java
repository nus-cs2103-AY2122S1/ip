package Tasks;

/**
 * Deadline task which contains the task description, and the date of the deadline
 */
public class Deadline extends Task {

    private String date;

    /**
     * A public constructor to create a Deadline task
     *
     * @param description description of the deadline task
     * @param date        the date stated after "/by " portion
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * A public toString method to add the task type [D] in front of the checkbox, and the date behind the task description
     *
     * @return the string representation of a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
