package duke;

/**
 * DoAfter class that extends from Task class
 */
public class DoAfter extends Task {

    protected String after;

    /**
     * Creates a DoAfter object
     *
     * @param description takes in a String describing the task description
     * @param after takes in a String representing the task to do after
     */
    public DoAfter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * gets task to do after
     *
     * @return a String representing task to do after
     */
    public String getAfter() {
        return after;
    }

    @Override
    public String toString() {
        return "[DA]" + super.toString() + "(DoAfter:" + after + ")";
    }
}
