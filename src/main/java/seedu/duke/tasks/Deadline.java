package seedu.duke.tasks;

public class Deadline extends Task {

    /**
     * Constructor. Default having the isDone parameter to be set as false.
     * 
     * @param description description of the current {@code Deadline} object.
     * @param dateTime    is the description of the location and time for the
     *                    {@code Deadline}.
     */
    public Deadline(String description, String dateTime) {
        super(description, dateTime);
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description is the description of the {@code Deadline}.
     * @param dateTime    is the description of the location and time for the
     *                    current {@code Deadline}.
     * @param isDone      determine whether the current {@code Deadline} is
     *                    completed or not.
     */
    public Deadline(String description, String dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    /**
     * Retrieves the symbol of the current object. Different object and child have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "D".
     */
    @Override
    public String getSymbol() {
        return "D";
    }

    /**
     * Marks the current {@code Deadline} as done.
     * 
     * @return a new {@code Deadline} object with the same description and by, but
     *         setting isDone property to be true
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getDateTime(), true);
    }

    /**
     * Describes the current {@code Deadline}.
     * 
     * @return a description of the current {@code Deadline}.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.getDateTime() + ")";
    }
}
