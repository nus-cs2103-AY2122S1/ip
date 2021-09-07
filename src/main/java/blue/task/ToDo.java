package blue.task;

/**
 * Task without any date/time attached to it.
 */
public class ToDo extends Task {
    public static final String REPRESENTATION = "T";

    public ToDo(String title) {
        super(title);
    }

    /**
     * Returns the String representation of this instance.
     *
     * @return String representation of this instance.
     */
    @Override
    public String toString() {
        return "[" + REPRESENTATION + "]" + super.toString();
    }
}
