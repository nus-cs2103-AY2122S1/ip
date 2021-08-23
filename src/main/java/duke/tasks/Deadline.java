package duke.tasks;

public class Deadline extends Task {
    protected final String by;

    /**
     * Contructor. Default having the isDone parameter to be set as false.
     * 
     * @param description description of the current Deadline object.
     * @param by          description of the date and time of the current Deadline
     *                    object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description of the current Deadline object.
     * @param by          description of the date and time of the current Deadline
     *                    object.
     * @param isDone      determine whether the task is completed or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
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
     * Retrieves the description of the date and time of the current Deadline
     * object.
     * 
     * @return a String which describes the date and time of the current Deadline
     *         object.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Marks the current Deadline as done.
     * 
     * @return a new Deadline object with the same description and by, but setting
     *         isDone property to be true
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getBy(), true);
    }

    /**
     * Describes the current Deadline object.
     * 
     * @return a description of the current Deadline object.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.getBy() + ")";
    }
}
