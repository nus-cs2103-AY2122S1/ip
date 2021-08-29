package blue.task;

/**
 * Task without any date/time attached to it.
 */
public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    /**
     * Returns a String that represents this class.
     *
     * @return String that represents this class.
     */
    public static String getClassRepr() {
        return "T";
    }

    /**
     * Returns the String representation of this instance.
     *
     * @return String representation of this instance.
     */
    @Override
    public String toString() {
        return "[" + getClassRepr() + "]" + super.toString();
    }
}
