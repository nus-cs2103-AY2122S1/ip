package duke;

/**
 * Subclass of Task without any date/time attached.
 */
public class ToDo extends Task {

    //Constructor

    /**
     * Constructs a To Do object.
     * @param description Description of the To Do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the To Do.
     *
     * @return String Representation of To Do.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
